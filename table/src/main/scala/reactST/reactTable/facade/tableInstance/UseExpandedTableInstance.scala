// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package reactST.reactTable.facade.tableInstance

import reactST.reactTable.anon.PartialTableExpandedToggl
import reactST.reactTable.facade.row.Row
import reactST.reactTable.mod.IdType
import reactST.reactTable.mod.TableExpandedToggleProps

import scala.scalajs.js

@js.native
trait UseExpandedTableInstance[D, Plugins] extends js.Object {

  var expandedDepth: Double = js.native

  var expandedRows: js.Array[Row[D, Plugins]] = js.native

  var isAllRowsExpanded: Boolean = js.native

  var preExpandedRows: js.Array[Row[D, Plugins]] = js.native

  def toggleAllRowsExpanded(): Unit               = js.native
  def toggleAllRowsExpanded(value: Boolean): Unit = js.native

  def toggleRowExpanded(id: js.Array[IdType[D]]): Unit                 = js.native
  def toggleRowExpanded(id: js.Array[IdType[D]], value: Boolean): Unit = js.native

  def getToggleAllRowsExpandedProps(): TableExpandedToggleProps                                 = js.native
  def getToggleAllRowsExpandedProps(props: PartialTableExpandedToggl): TableExpandedToggleProps =
    js.native
}
