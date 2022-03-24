// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package reactST.reactTable.facade.tableInstance

import reactST.reactTable.facade.row.Row
import reactST.reactTable.mod.IdType
import reactST.std.Record

import scala.scalajs.js

@js.native
trait UseGroupByTableInstance[D, Plugins] extends js.Object {
  var flatRows: js.Array[Row[D, Plugins]] = js.native

  var groupedFlatRows: js.Array[Row[D, Plugins]] = js.native

  var groupedRows: js.Array[Row[D, Plugins]] = js.native

  var groupedRowsById: Record[String, Row[D, Plugins]] = js.native

  var nonGroupedFlatRows: js.Array[Row[D, Plugins]] = js.native

  var nonGroupedRowsById: Record[String, Row[D, Plugins]] = js.native

  var onlyGroupedFlatRows: js.Array[Row[D, Plugins]] = js.native

  var onlyGroupedRowsById: Record[String, Row[D, Plugins]] = js.native

  var preGroupedFlatRows: js.Array[Row[D, Plugins]] = js.native

  var preGroupedRows: js.Array[Row[D, Plugins]] = js.native

  var preGroupedRowsById: Record[String, Row[D, Plugins]] = js.native

  var rows: js.Array[Row[D, Plugins]] = js.native

  var rowsById: Record[String, Row[D, Plugins]] = js.native

  def toggleGroupBy(columnId: IdType[D]): Unit                 = js.native
  def toggleGroupBy(columnId: IdType[D], value: Boolean): Unit = js.native
}
