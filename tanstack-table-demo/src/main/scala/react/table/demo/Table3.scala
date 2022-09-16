// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.table.demo

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.table.*
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
            "expander",
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
            size = 50,
            enableResizing = false
          ),
          ColDef("first", _.value.first, _ => "First", size = 100),
          ColDef("last", _.value.last, _ => "Last", size = 100),
          ColDef("age", _.value.age, _ => "Age", size = 50)
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
          getSubRows = _.subRows.toOption.map(_.toList).getOrElse(List.empty)
        )
      )
      .render((_, _, _, table) =>
        React.Fragment(
          <.h2("Table with Expanding Rows"),
          HTMLVirtualizedTable(table, containerClass = Css("container"))
        )
      )
