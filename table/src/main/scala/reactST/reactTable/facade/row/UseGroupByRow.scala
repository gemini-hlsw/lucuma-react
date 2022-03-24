// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package reactST.reactTable.facade.row

import reactST.reactTable.mod.AggregatedValue
import reactST.reactTable.mod.IdType
import reactST.std.Record

import scala.scalajs.js

@js.native
trait UseGroupByRow[D, Plugins] extends js.Object {

  var depth: Double = js.native

  var groupByID: IdType[D] = js.native

  var groupByVal: String = js.native

  var id: String = js.native

  var index: Double = js.native

  var isGrouped: Boolean = js.native

  var leafRows: js.Array[Row[D, Plugins]] = js.native

  var subRows: js.Array[Row[D, Plugins]] = js.native

  var values: Record[IdType[D], AggregatedValue] = js.native
}
