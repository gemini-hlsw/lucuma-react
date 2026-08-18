// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import japgolly.scalajs.react.*
import japgolly.scalajs.react.test.*
import japgolly.scalajs.react.vdom.html_<^.*
import munit.FunSuite

import scalajs.js.JSConverters.*

/**
 * SSR render tests that exercise the v9 facade end-to-end against the real `@tanstack/react-table`
 * npm package (via `useLegacyTable`). Catches runtime issues that compilation/linking can't: that
 * the v9 hook accepts the v8-shaped options, that `getRowModel()`/`getValue` work, and that sorting
 * is applied.
 */
class TableRenderSuite extends FunSuite:
  case class Person(id: Int, name: String, age: Int)
  object Person:
    given Reusability[Person] = Reusability.by_==

  private val ColDef = ColumnDef[Person]

  private val columns = Reusable.always:
    List(
      ColDef(ColumnId("name"), _.name, "Name"),
      ColDef(ColumnId("age"), _.age, "Age")
    )

  private val data = Reusable.always:
    List(Person(1, "Alice", 30), Person(2, "Bob", 25))

  private def rowsHtml(table: Table[Person, ?, ?, ?]): VdomElement =
    <.table(
      <.caption(table.store.state.sorting.toList.map(_.id).mkString(",")),
      <.tbody(
        TagMod.fromTraversableOnce(
          table
            .getRowModel()
            .rows
            .map: row =>
              <.tr(
                <.td(row.getValue[String](ColumnId("name"))),
                <.td(row.getValue[Int](ColumnId("age")).toString)
              )
        )
      )
    )

  private def component(initialSorting: Option[Sorting]) = ScalaFnComponent[Unit]: _ =>
    for
      rows  <- useMemo(data)(identity)
      cols  <- useMemo(columns)(identity)
      table <- useReactTable:
                 TableOptions(
                   cols,
                   rows,
                   initialState = initialSorting.map(s => TableState(sorting = s)).orUndefined
                 )
    yield rowsHtml(table)

  test("v9 table renders its row model via useLegacyTable"):
    ReactTestUtils.withRenderedSync(component(None)()): m =>
      val expected =
        """<table><caption></caption><tbody><tr><td>Alice</td><td>30</td></tr><tr><td>Bob</td><td>25</td></tr></tbody></table>"""
      m.outerHTML.assert(expected)

  test("v9 table renders all rows, not just the default 10-row page"):
    // v9 always wires the paginated row model; without the facade's pageSize=Infinity default,
    // getRowModel() would slice to tanstack's default pageSize of 10 and drop rows 11+.
    val manyPeople   = Reusable.always((1 to 15).toList.map(i => Person(i, s"P$i", 20 + i)))
    val manyRowsComp = ScalaFnComponent[Unit]: _ =>
      for
        rows  <- useMemo(manyPeople)(identity)
        cols  <- useMemo(columns)(identity)
        table <- useReactTable(TableOptions(cols, rows))
      yield rowsHtml(table)
    ReactTestUtils.withRenderedSync(manyRowsComp()): m =>
      val expectedRows = (1 to 15).map(i => s"<tr><td>P$i</td><td>${20 + i}</td></tr>").mkString
      m.outerHTML.assert(s"<table><caption></caption><tbody>$expectedRows</tbody></table>")

  test("v9 table sorts rows by the initial sorting state"):
    // Ascending by age: Bob(25) must precede Alice(30) — different from data order (Alice, Bob),
    // so this proves the sort is actually applied. The <caption> reads getState().sorting, which
    // also exercises the v9 state-read path (getState -> table.state).
    ReactTestUtils.withRenderedSync(
      component(Some(Sorting(ColumnId("age") -> SortDirection.Ascending)))()
    ): m =>
      val expected =
        """<table><caption>age</caption><tbody><tr><td>Bob</td><td>25</td></tr><tr><td>Alice</td><td>30</td></tr></tbody></table>"""
      m.outerHTML.assert(expected)

  test("v9 custom sortFn is stored as a JS function applying the Scala comparator"):
    // TanStack v9 resolves a custom sort fn with an `instanceof Function` check and silently
    // falls back to `basic` otherwise, so the Scala comparator must be stored as a genuine JS
    // function
    import scalajs.js
    val people = Reusable.always(List(Person(1, "Alice", 30), Person(2, "Bob", 25)))
    val cols   = Reusable.always:
      List(
        ColDef(ColumnId("name"), _.name, "Name"),
        ColDef(ColumnId("age"), _.age, "Age").sortableWith((a, b) => b.compare(a))
      )
    val ageDef = cols.value(1).toJs.asInstanceOf[js.Dynamic]
    assertEquals(js.typeOf(ageDef.selectDynamic("sortFn")), "function")

    var result = Option.empty[Double]
    val comp   = ScalaFnComponent[Unit]: _ =>
      for
        rows  <- useMemo(people)(identity)
        cs    <- useMemo(cols)(identity)
        table <- useReactTable(TableOptions(cs, rows))
      yield
        val rs    = table.getRowModel().rows
        val alice = rs.find(_.getValue[Int](ColumnId("age")) == 30).get
        val bob   = rs.find(_.getValue[Int](ColumnId("age")) == 25).get
        result = Some(
          ageDef
            .applyDynamic("sortFn")(alice.toJs, bob.toJs, "age")
            .asInstanceOf[Double]
        )
        <.div()
    ReactTestUtils.withRenderedSync(comp()): _ =>
      // The comparator is reversed: (Alice=30, Bob=25) must compare negative;
      assert(result.exists(_ < 0), s"custom comparator not applied, got $result")

  test("v9 table resolves built-in sort fns through the registry"):
    // "P2" vs "P10" ascending by name: alphanumeric puts P2 first; both the basic fallback
    // and the unsorted data order put P10 first.
    val people       = Reusable.always(List(Person(1, "P10", 1), Person(2, "P2", 2)))
    val alphaNumCols = Reusable.always:
      List(
        ColDef(ColumnId("name"), _.name, "Name").sortableBuiltIn(BuiltInSorting.Alphanumeric),
        ColDef(ColumnId("age"), _.age, "Age")
      )
    val comp         = ScalaFnComponent[Unit]: _ =>
      for
        rows  <- useMemo(people)(identity)
        cols  <- useMemo(alphaNumCols)(identity)
        table <- useReactTable:
                   TableOptions(
                     cols,
                     rows,
                     initialState =
                       TableState(sorting = Sorting(ColumnId("name") -> SortDirection.Ascending))
                   )
      yield rowsHtml(table)
    ReactTestUtils.withRenderedSync(comp()): m =>
      val expected =
        """<table><caption>name</caption><tbody><tr><td>P2</td><td>2</td></tr><tr><td>P10</td><td>1</td></tr></tbody></table>"""
      m.outerHTML.assert(expected)
end TableRenderSuite
