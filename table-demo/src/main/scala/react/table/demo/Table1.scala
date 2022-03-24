// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.table.demo

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.style.Css
import reactST.reactTable._
import reactST.reactTable.mod.SortingRule

object Table1 {
  private val SortedTableDef = TableDef[Guitar].withSortBy

  private val sortedTableState = SortedTableDef.State().setSortBy(SortingRule("model"))

  private val SortedTable =
    ScalaFnComponent
      .withHooks[List[Guitar]]
      // cols
      .useMemo(())(_ =>
        List(
          SortedTableDef
            .Column("id", _.id)
            .setCell(cellProps => <.span(s"g-${cellProps.value}"))
            .setSortByAuto
            .setHeader("Id"),
          SortedTableDef
            .Column("make", _.make)
            .setHeader("Make")
            .setCell(cell => <.span(s"${cell.value}"))
            .setSortByFn(_.toString),
          SortedTableDef.Column("model", _.model).setHeader("Model"),
          SortedTableDef
            .ColumnGroup(
              SortedTableDef.Column("year", _.details.year).setHeader("Year"),
              SortedTableDef.Column("pickups", _.details.pickups).setHeader("Pickups"),
              SortedTableDef
                .Column("color", _.details.color)
                .setHeader("Color")
                .setDisableSortBy(true)
            )
            .setHeader("Details")
        )
      )
      // rows
      .useMemoBy((guitars, _) => guitars)((_, _) => guitars => guitars)
      .useTableBy((_, cols, rows) =>
        SortedTableDef(cols, rows, Reusable.always(_.setInitialState(sortedTableState)))
      )
      .renderWithReuse((_, _, guitars, tableInstance) =>
        HTMLTable(SortedTableDef)(
          tableClass = Css("guitars"),
          headerCellFn = Some(HTMLTable.sortableHeaderCellFn()),
          footer = <.tfoot(<.tr(<.th(^.colSpan := 6, s"Guitar Count: ${guitars.length}")))
        )(tableInstance)
      )

  val component = ScalaFnComponent[List[Guitar]] { guitars =>
    React.Fragment(
      <.h2("Sortable table"),
      SortedTable(guitars),
      "Click header to sort. Shift-Click for multi-sort."
    )
  }
}
