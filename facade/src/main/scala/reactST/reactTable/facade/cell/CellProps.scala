package reactST.reactTable.facade.cell

import reactST.reactTable.mod.CellValue
import reactST.reactTable.mod.ColumnInstance
import reactST.reactTable.mod.Row
import reactST.reactTable.mod.TableInstance

import scala.scalajs.js

@js.native
trait CellProps[D, V, Plugins] extends js.Object with TableInstance[D] {

  var cell: Cell[D, V, Plugins] = js.native

  var column: ColumnInstance[D] = js.native

  var row: Row[D] = js.native

  var value: CellValue[V] = js.native
}
