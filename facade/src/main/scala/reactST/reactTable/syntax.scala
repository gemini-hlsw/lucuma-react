package reactST.reactTable

import japgolly.scalajs.react.raw
import reactST.reactTable.mod.Cell

object syntax {
  implicit class CellOps[Self <: Cell[_, _]](val cell: Self) extends AnyVal {
    def renderCell: raw.React.Node = cell.render_Cell(reactTableStrings.Cell)
  }

  implicit class ColumnObjectOps[Self <: ColumnObject[_]](val col: Self) extends AnyVal {
    def renderHeader: raw.React.Node = col.render_Header(reactTableStrings.Header)
    def renderFooter: raw.React.Node = col.render_Footer(reactTableStrings.Footer)
  }
}
