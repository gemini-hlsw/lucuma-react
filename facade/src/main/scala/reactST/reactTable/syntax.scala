package reactST.reactTable

import japgolly.scalajs.react.facade
import reactST.reactTable.mod.Cell

object syntax {
  implicit class CellOps[Self <: Cell[_, _]](val cell: Self)             extends AnyVal {
    def renderCell: facade.React.Node = cell.render_Cell(reactTableStrings.Cell)
  }

  implicit class ColumnObjectOps[Self <: ColumnObject[_]](val col: Self) extends AnyVal {
    def renderHeader: facade.React.Node = col.render_Header(reactTableStrings.Header)
    def renderFooter: facade.React.Node = col.render_Footer(reactTableStrings.Footer)
  }
}
