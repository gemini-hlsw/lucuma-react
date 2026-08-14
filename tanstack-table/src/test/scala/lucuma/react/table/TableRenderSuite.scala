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
end TableRenderSuite
