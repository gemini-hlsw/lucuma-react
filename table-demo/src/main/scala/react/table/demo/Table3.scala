// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.table.demo

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.style.Css
import reactST.reactTable.HTMLTable
import reactST.reactTable.TableDef
import reactST.reactTable._

object Table3 {

  private val SortedVirtualizedTableDef = TableDef[Person].withSortBy.withBlockLayout

  private val SortedVirtualizedTable =
    ScalaFnComponent
      .withHooks[List[Person]]
      // cols
      .useMemo(())(_ =>
        List(
          SortedVirtualizedTableDef.Column("first", _.first).setHeader("First").setWidth(100),
          SortedVirtualizedTableDef.Column("last", _.last).setHeader("Last").setWidth(100),
          SortedVirtualizedTableDef.Column("age", _.age).setHeader("Age").setWidth(75)
        )
      )
      // rows
      .useMemoBy((people, _) => people)((_, _) => people => people)
      .useTableBy((_, cols, rows) => SortedVirtualizedTableDef(cols, rows))
      .render((_, _, _, tableInstance) =>
        HTMLTable.virtualized(SortedVirtualizedTableDef)(
          tableClass = Css("virtualized"),
          headerCellFn = Some(HTMLTable.sortableHeaderCellFn(useDiv = true))
        )(tableInstance)
      )

  val component = ScalaFnComponent[List[Person]] { people =>
    React.Fragment(
      <.h2("Sortable Virtualized Table"),
      SortedVirtualizedTable(people)
    )
  }
}
