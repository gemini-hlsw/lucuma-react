// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.table.demo

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.style.Css
import reactST.reactTable.HTMLTable
import reactST.reactTable.TableDef
import reactST.reactTable._

object Table4 {

  private val SortedVariableVirtualizedTableDef = TableDef[Person].withSortBy.withBlockLayout

  private val SortedVariableVirtualizedTable =
    ScalaFnComponent
      .withHooks[List[Person]]
      // cols
      .useMemo(())(_ =>
        List(
          SortedVariableVirtualizedTableDef.Column("id", _.id).setHeader("Id").setWidth(50),
          SortedVariableVirtualizedTableDef
            .Column("first", _.first)
            .setHeader("First")
            .setWidth(100),
          SortedVariableVirtualizedTableDef.Column("last", _.last).setHeader("Last").setWidth(100),
          SortedVariableVirtualizedTableDef.Column("age", _.age).setHeader("Age").setWidth(75)
        )
      )
      // rows
      .useMemoBy((people, _) => people)((_, _) => people => people)
      .useTableBy((_, cols, rows) => SortedVariableVirtualizedTableDef(cols, rows))
      .render((_, _, _, tableInstance) =>
        HTMLTable.virtualized(SortedVariableVirtualizedTableDef)(
          tableClass = Css("virtualized"),
          bodyHeight = Some(300), // make this one a different height
          headerCellFn = Some(HTMLTable.sortableHeaderCellFn(useDiv = true)),
          rowClassFn = (_: Int, p: Person) => if (p.id % 2 == 0) Css("") else Css("big")
        )(tableInstance)
      )

  val component = ScalaFnComponent[List[Person]] { people =>
    React.Fragment(
      <.h2("Sortable Variable Row Height Virtualized Table"),
      <.h3("Rows with odd id's are taller via CSS."),
      SortedVariableVirtualizedTable(people)
    )
  }
}
