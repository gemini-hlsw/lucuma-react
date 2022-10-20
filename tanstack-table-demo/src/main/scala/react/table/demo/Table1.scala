// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.table.demo

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.table.*
import react.common.*

import scalajs.js

object Table1:
  private val ColDef = ColumnDef[Guitar]

  val component =
    ScalaFnComponent
      .withHooks[List[Guitar]]
      // cols
      .useMemo(())(_ =>
        List(
          ColDef(ColumnId("id"), _.id, "Id", ctx => s"g-${ctx.value}").sortable,
          ColDef(ColumnId("make"), _.make, _ => "Make"),
          ColDef(ColumnId("model"), _.model, _ => "Model").sortableBy(_.length),
          ColDef.group(
            ColumnId("details"),
            _ => <.div(^.textAlign.center)("Details"),
            List(
              ColDef(ColumnId("year"), _.details.year, _ => "Year"),
              ColDef(ColumnId("pickups"), _.details.pickups, _ => "Pickups"),
              ColDef(ColumnId("color"), _.details.color, _ => "Color", enableSorting = false)
            )
          )
        )
      )
      // rows
      .useMemoBy((guitars, _) => guitars)((_, _) => identity)
      // table
      .useReactTableBy { (_, cols, rows) =>
        TableOptions(
          cols,
          rows,
          enableSorting = true,
          enableColumnResizing = true,
          initialState =
            TableState(sorting = Sorting(ColumnId("model") -> SortDirection.Descending))
        )
      }
      .render { (_, _, _, table) =>
        React.Fragment(
          <.h2("Sortable table"),
          HTMLTable(
            table,
            Css("guitars")
          ),
          "Click header to sort. Shift-Click for multi-sort."
        )
      }
