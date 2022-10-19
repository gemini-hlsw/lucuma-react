// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.table.demo

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.table.*
import lucuma.react.syntax.*
import react.common.*
import react.common.style.Css
import reactST.{tanstackTableCore => raw}

object Table3:
  private val ColDef = ColumnDef[Expandable[Person]]

  val component =
    ScalaFnComponent
      .withHooks[List[Expandable[Person]]]
      // cols
      .useMemo(())(_ =>
        List(
          ColDef(
            ColumnId("expander"),
            header = header =>
              <.span(^.cursor.pointer)(
                ^.onClick ==> (e => Callback(header.table.getToggleAllRowsExpandedHandler()(e))),
                if (header.table.getIsAllRowsExpanded()) "👇" else "👉"
              ),
            cell = cell =>
              if (cell.row.getCanExpand())
                <.span(^.cursor.pointer)(
                  ^.onClick --> Callback(cell.row.getToggleExpandedHandler()()),
                  if (cell.row.getIsExpanded()) "👇" else "👉"
                )
              else "",
            size = 50.toPx,
            enableResizing = false
          ),
          ColDef(ColumnId("first"), _.value.first, _ => "First", size = 100.toPx),
          ColDef(ColumnId("last"), _.value.last, _ => "Last", size = 100.toPx),
          ColDef(ColumnId("age"), _.value.age, _ => "Age", size = 50.toPx)
        )
      )
      // rows
      .useMemoBy((people, _) => people)((_, _) => identity)
      .useReactTableBy((_, cols, rows) =>
        TableOptions(
          cols,
          rows,
          enableExpanding = true,
          columnResizeMode = raw.mod.ColumnResizeMode.onChange,
          getSubRows = (row, _) => row.subRows.toOption.map(_.toList)
        )
      )
      .render((_, _, _, table) =>
        React.Fragment(
          <.h2("Table with Expanding Rows"),
          HTMLVirtualizedTable(
            table,
            containerMod = Css("container"),
            estimateRowHeight = _ => 24.toPx
          )
        )
      )
