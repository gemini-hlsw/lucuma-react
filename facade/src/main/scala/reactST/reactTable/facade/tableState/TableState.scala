package reactST.reactTable.facade.tableState

import org.scalablytyped.runtime.StObject
import reactST.reactTable.Plugin
import reactST.reactTable.mod.IdType
import reactST.reactTable.mod.SortingRule
import reactST.reactTable.mod.UseSortByState

import scala.scalajs.js

@js.native
trait TableState[D, Plugins] extends js.Object {

  var hiddenColumns: js.UndefOr[js.Array[IdType[D]]] = js.native
}
object TableState {

  @scala.inline
  def apply[D, Plugins](): TableState[D, Plugins] = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[TableState[D, Plugins]]
  }

  @scala.inline
  implicit class TableStateMutableBuilder[Self <: TableState[_, _], D, Plugins](
    val x: Self with TableState[D, Plugins]
  ) extends AnyVal {

    @scala.inline
    def setHiddenColumns(value: js.Array[IdType[D]]): Self =
      StObject.set(x, "hiddenColumns", value.asInstanceOf[js.Any])

    @scala.inline
    def setHiddenColumnsUndefined: Self = StObject.set(x, "hiddenColumns", js.undefined)

    @scala.inline
    def setHiddenColumnsVarargs(value: IdType[D]*): Self =
      StObject.set(x, "hiddenColumns", js.Array(value: _*))
  }

  @inline
  implicit def UseSortByTableStateOpts[D, Plugins](tableState: TableState[D, Plugins])(implicit
    ev:                                                        Plugins <:< Plugin.SortBy.Tag
  ): UseSortByState[D] = tableState.asInstanceOf[UseSortByState[D]]

  @inline
  implicit class UseSortByStateMutableBuilder[Self <: TableState[_, _], D, Plugins](
    val tableState: Self with TableState[D, Plugins]
  ) extends AnyVal {

    @inline
    def setSortBy(value: SortingRule[D]*)(implicit
      ev:                Plugins <:< Plugin.SortBy.Tag
    ): Self =
      StObject.set(tableState, "sortBy", js.Array(value: _*))
  }

}
