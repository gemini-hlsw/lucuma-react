// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package reactST.reactTable.facade.tableOptions

import reactST.reactTable.AggregatorFn
import reactST.reactTable.facade.row.Row
import reactST.reactTable.mod.CellValue
import reactST.reactTable.mod.IdType
import reactST.std.Record

import scala.scalajs.js

@js.native
trait UseGroupByTableOptions[D, Plugins] extends js.Object {

  var aggregations: js.UndefOr[Record[String, AggregatorFn[D, Plugins]]] = js.native

  var autoResetGroupBy: js.UndefOr[Boolean] = js.native

  var defaultCanGroupBy: js.UndefOr[Boolean] = js.native

  var disableGroupBy: js.UndefOr[Boolean] = js.native

  var groupByFn: js.UndefOr[
    js.Function2[
      /* rows */ js.Array[Row[D, Plugins]],
      /* columnId */ IdType[D],
      Record[String, js.Array[Row[D, Plugins]]]
    ]
  ] = js.native

  var manualGroupBy: js.UndefOr[Boolean] = js.native
}
