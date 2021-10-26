package reactST.reactTable.facade.row

import org.scalablytyped.runtime.StObject
import reactST.reactTable.facade.cell.Cell
import reactST.reactTable.mod.CellValue
import reactST.reactTable.mod.IdType
import reactST.reactTable.mod.RowPropGetter
import reactST.reactTable.mod.TableRowProps
import reactST.std.Record

import scala.scalajs.js

@js.native
trait Row[D, Plugins] extends StObject {

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
