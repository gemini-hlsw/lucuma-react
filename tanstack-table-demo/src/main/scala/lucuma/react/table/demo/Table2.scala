// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table.demo

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.common.*
import lucuma.react.common.style.Css
import lucuma.react.syntax.*
import lucuma.react.table.*

object Table2:
  private val ColDef = ColumnDef[Person]

  val component =
    ScalaFnComponent
      .withHooks[List[Person]]
      // cols
      .useMemo(())(_ =>
        List(
          ColDef(ColumnId("first"), _.first, _ => "First", size = 100.toPx),
          ColDef(ColumnId("last"), _.last, _ => "Last", size = 100.toPx),
          ColDef(ColumnId("age"), _.age, _ => "Age", size = 50.toPx)
        )
      )
      // rows
      .useMemoBy((people, _) => people)((_, _) => identity)
      .useReactTableBy((_, cols, rows) =>
        TableOptions(cols, rows, enableSorting = true, enableColumnResizing = false)
      )
      .render((_, _, _, table) =>
        React.Fragment(
          <.h2("Sortable Virtualized Table"),
          HTMLVirtualizedTable(
            table,
            containerMod = Css("container"),
            tableMod = Css("striped"),
            estimateSize = _ => 24.toPx
          )
        )
      )
