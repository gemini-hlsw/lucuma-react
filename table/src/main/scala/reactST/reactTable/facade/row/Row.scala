// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package reactST.reactTable.facade.row

import reactST.reactTable.Plugin
import reactST.reactTable.facade.cell.Cell
import reactST.reactTable.mod.CellValue
import reactST.reactTable.mod.IdType
import reactST.reactTable.mod.RowPropGetter
import reactST.reactTable.mod.TableRowProps
import reactST.std.Record

import scala.scalajs.js

@js.native
trait Row[D, Plugins] extends js.Object {

  var allCells: js.Array[Cell[D, js.Any, Plugins]] = js.native

  var cells: js.Array[Cell[D, js.Any, Plugins]] = js.native

  def getRowProps(): TableRowProps                             = js.native
  def getRowProps(propGetter: RowPropGetter[D]): TableRowProps = js.native

  var id: String = js.native

  var index: Double = js.native

  var original: D = js.native

  var subRows: js.Array[Row[D, Plugins]] = js.native

  var values: Record[IdType[D], CellValue[js.Any]] = js.native
}

object Row {
  // The "conv" mechanism is mainly to get the implicit conversion to kick in when we have a Reusable.
  @inline
  implicit def toGroupByRow[D, Plugins, Self](row: Self)(implicit
    conv:                                          Self => Row[D, Plugins],
    ev:                                            Plugins <:< Plugin.GroupBy.Tag
  ): Self with UseGroupByRow[D, Plugins] =
    conv(row).asInstanceOf[Self with UseGroupByRow[D, Plugins]]

  @inline
  implicit def toExpandedRow[D, Plugins, Self](row: Self)(implicit
    conv:                                           Self => Row[D, Plugins],
    ev:                                             Plugins <:< Plugin.Expanded.Tag
  ): Self with UseExpandedRow[D, Plugins] =
    conv(row).asInstanceOf[Self with UseExpandedRow[D, Plugins]]
}
