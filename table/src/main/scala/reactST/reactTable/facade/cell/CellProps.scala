package reactST.reactTable.facade.cell

import reactST.reactTable.facade.column.Column
import reactST.reactTable.facade.row.Row
import reactST.reactTable.facade.tableInstance.TableInstance
import reactST.reactTable.mod.CellValue

import scala.scalajs.js

@js.native
trait CellProps[D, V, Plugins] extends js.Object with TableInstance[D, Plugins] {

  var cell: Cell[D, V, Plugins] = js.native

  var column: Column[D, Plugins] = js.native

  var row: Row[D, Plugins] = js.native

  var value: CellValue[V] = js.native
}
