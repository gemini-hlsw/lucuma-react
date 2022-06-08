package reactST.reactTable.facade.columnOptions

import japgolly.scalajs.react.vdom.VdomElement
import org.scalablytyped.runtime.StObject
import reactST.reactTable.facade.cell.CellProps
import reactST.reactTable.mod.Renderer

import scala.scalajs.js

@js.native
trait ColumnInterfaceBasedOnValue[D, V, Plugins] extends js.Object {

  var Cell: js.UndefOr[Renderer[CellProps[D, V, Plugins]]] = js.native
}
object ColumnInterfaceBasedOnValue {

  @scala.inline
  def apply[D, V, Plugins](): ColumnInterfaceBasedOnValue[D, V, Plugins] = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[ColumnInterfaceBasedOnValue[D, V, Plugins]]
  }

  @scala.inline
  implicit class ColumnInterfaceBasedOnValueMutableBuilder[Self <: ColumnInterfaceBasedOnValue[_,
                                                                                               _,
                                                                                               _
  ], D, V, Plugins](val col: Self with (ColumnInterfaceBasedOnValue[D, V, Plugins]))
      extends AnyVal {

    @scala.inline
    def setCell(value: Renderer[CellProps[D, V, Plugins]]): Self =
      StObject.set(col, "Cell", value.asInstanceOf[js.Any])

    @scala.inline
    def setCellUndefined: Self = StObject.set(col, "Cell", js.undefined)

    @scala.inline
    def setCellVdomElement(value: VdomElement): Self =
      StObject.set(col, "Cell", value.rawElement.asInstanceOf[js.Any])
  }
}
