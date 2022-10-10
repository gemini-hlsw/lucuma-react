// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.table.demo

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.table.*
import react.common.*
import reactST.tanstackTableCore.mod.ColumnSort
import reactST.tanstackTableCore.mod.InitialTableState

import scalajs.js

object Table1:
  private val ColDef = ColumnDef[Guitar]

  val component =
    ScalaFnComponent
      .withHooks[List[Guitar]]
      // cols
      .useMemo(())(_ =>
        List(
          ColDef("id", _.id, "Id", ctx => s"g-${ctx.value}").sortable,
          ColDef("make", _.make, _ => "Make"),
          ColDef("model", _.model, _ => "Model").sortableBy(_.length),
          ColDef.group(
            "details",
            _ => <.div(^.textAlign.center)("Details"),
            List(
              ColDef("year", _.details.year, _ => "Year"),
              ColDef("pickups", _.details.pickups, _ => "Pickups"),
              ColDef("color", _.details.color, _ => "Color", enableSorting = false)
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
            InitialTableState().setSorting(js.Array(ColumnSort(desc = true, id = "model")))
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
