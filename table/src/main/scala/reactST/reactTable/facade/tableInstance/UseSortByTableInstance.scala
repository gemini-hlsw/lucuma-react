// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package reactST.reactTable.facade.tableInstance

import reactST.reactTable.facade.row.Row
import reactST.reactTable.mod.IdType
import reactST.reactTable.mod.SortingRule

import scala.scalajs.js

@js.native
trait UseSortByTableInstance[D, Plugins] extends js.Object {
  var preSortedRows: js.Array[Row[D, Plugins]] = js.native

  var rows: js.Array[Row[D, Plugins]] = js.native

  def setSortBy(sortBy: js.Array[SortingRule[D]]): Unit = js.native

  def toggleSortBy(columnId: IdType[D]): Unit                                        = js.native
  def toggleSortBy(columnId: IdType[D], descending: Boolean): Unit                   = js.native
  def toggleSortBy(columnId: IdType[D], descending: Boolean, isMulti: Boolean): Unit = js.native
  def toggleSortBy(columnId: IdType[D], descending: Unit, isMulti: Boolean): Unit    = js.native
}
