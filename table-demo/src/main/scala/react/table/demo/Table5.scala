// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.table.demo

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.style.Css
import reactST.reactTable._

object Table5 {
  private val ExpandedTableDef =
    TableDef[Expandable[Person]].withSortBy.withExpanded.withBlockLayout

  private val ExpandedTable =
    ScalaFnComponent
      .withHooks[List[Expandable[Person]]]
      // cols
      .useMemo(())(_ =>
        List(
          ExpandedTableDef
            .Column("expander")
            .setHeader(headerProps =>
              <.span(headerProps.getToggleAllRowsExpandedProps(),
                     if (headerProps.isAllRowsExpanded) "👇" else "👉"
              )
            )
            .setCell(cell =>
              if (cell.row.canExpand)
                <.span(cell.row.getToggleRowExpandedProps(),
                       if (cell.row.isExpanded.contains(true)) "👇" else "👉"
                )
              else ""
            )
            .setWidth(50),
          ExpandedTableDef.Column("first", _.value.first).setHeader("First").setWidth(100),
          ExpandedTableDef.Column("last", _.value.last).setHeader("Last").setWidth(100),
          ExpandedTableDef.Column("age", _.value.age).setHeader("Age").setWidth(50)
        )
      )
      // rows
      .useMemoBy((people, _) => people)((_, _) => people => people)
      .useTableBy((_, cols, rows) => ExpandedTableDef(cols, rows))
      .render((_, _, _, tableInstance) =>
        HTMLTable.virtualized(ExpandedTableDef)(
          tableClass = Css("virtualized"),
          headerCellFn = Some(HTMLTable.sortableHeaderCellFn(useDiv = true))
        )(tableInstance)
      )

  val component = ScalaFnComponent[List[Expandable[Person]]] { people =>
    React.Fragment(
      <.h2("Table with Expanding Rows"),
      ExpandedTable(people)
    )
  }
}
