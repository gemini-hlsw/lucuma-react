// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.table.demo

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.table.*
import react.common.*
import react.common.style.Css

object Table2:
  private val ColDef = ColumnDef[Person]

  private def rowClassEvenOdd[T]: (Int, T) => Css = (i, _) =>
    if (i % 2 == 0) Css("even") else Css("odd")

  val component =
    ScalaFnComponent
      .withHooks[List[Person]]
      // cols
      .useMemo(())(_ =>
        List(
          ColDef("first", _.first, _ => "First", size = 100),
          ColDef("last", _.last, _ => "Last", size = 100),
          ColDef("age", _.age, _ => "Age", size = 50)
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
            containerClass = Css("container"),
            rowClassFn = rowClassEvenOdd,
            estimateRowHeightPx = _ => 24
          )
        )
      )
