// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package reactST.reactTable.facade.row

import reactST.reactTable.anon.PartialTableExpandedToggl
import reactST.reactTable.mod.TableExpandedToggleProps

import scala.scalajs.js

@js.native
trait UseExpandedRow[D, Plugins] extends js.Object {

  var canExpand: Boolean = js.native

  var depth: Double = js.native

  def getToggleRowExpandedProps(): TableExpandedToggleProps                                 = js.native
  def getToggleRowExpandedProps(props: PartialTableExpandedToggl): TableExpandedToggleProps =
    js.native

  var isExpanded: js.UndefOr[Boolean] = js.native

  var subRows: js.Array[Row[D, Plugins]] = js.native

  def toggleRowExpanded(): Unit               = js.native
  def toggleRowExpanded(value: Boolean): Unit = js.native
}
