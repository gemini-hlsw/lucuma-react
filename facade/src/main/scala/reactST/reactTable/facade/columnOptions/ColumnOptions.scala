package reactST.reactTable.facade.columnOptions

import japgolly.scalajs.react.vdom.VdomElement
import org.scalablytyped.runtime.StObject
import reactST.reactTable.Plugin
import reactST.reactTable.SingleColumnOptions
import reactST.reactTable.facade.row.Row
import reactST.reactTable.mod.DefaultSortTypes
import reactST.reactTable.mod.HeaderProps
import reactST.reactTable.mod.IdType
import reactST.reactTable.mod.Renderer
import reactST.reactTable.mod.SortByFn
import reactST.reactTable.mod.UseSortByColumnOptions

import scala.scalajs.js
import scala.scalajs.js.`|`

@js.native
trait ColumnOptions[D, Plugins] extends js.Object {

  var Header: js.UndefOr[Renderer[HeaderProps[D]]] = js.native

  var id: js.UndefOr[IdType[D]] = js.native

  var maxWidth: js.UndefOr[Double] = js.native

  var minWidth: js.UndefOr[Double] = js.native

  var width: js.UndefOr[Double | String] = js.native
}

object ColumnOptions {

  implicit def toSortByColumnOptions[D, Plugins, Self <: SingleColumnOptions[D, Plugins]](
    col: Self
  )(implicit
    ev:  Plugins <:< Plugin.SortBy.Tag
  ): Self with UseSortByColumnOptions[D] =
    col.asInstanceOf[Self with UseSortByColumnOptions[D]]

  @scala.inline
  implicit class UseTableColumnOptionsMutableBuilder[Self <: ColumnOptions[
    _,
    _
  ], D, Plugins](val x: Self with ColumnOptions[D, Plugins])
      extends AnyVal {

    @scala.inline
    def setHeader(value: Renderer[HeaderProps[D]]): Self =
      StObject.set(x, "Header", value.asInstanceOf[js.Any])

    @scala.inline
    def setHeaderUndefined: Self = StObject.set(x, "Header", js.undefined)

    @scala.inline
    def setHeaderVdomElement(value: VdomElement): Self =
      StObject.set(x, "Header", value.rawElement.asInstanceOf[js.Any])

    @scala.inline
    def setId(value: IdType[D]): Self = StObject.set(x, "id", value.asInstanceOf[js.Any])

    @scala.inline
    def setIdUndefined: Self = StObject.set(x, "id", js.undefined)

    @scala.inline
    def setMaxWidth(value: Double): Self = StObject.set(x, "maxWidth", value.asInstanceOf[js.Any])

    @scala.inline
    def setMaxWidthUndefined: Self = StObject.set(x, "maxWidth", js.undefined)

    @scala.inline
    def setMinWidth(value: Double): Self = StObject.set(x, "minWidth", value.asInstanceOf[js.Any])

    @scala.inline
    def setMinWidthUndefined: Self = StObject.set(x, "minWidth", js.undefined)

    @scala.inline
    def setWidth(value: Double | String): Self =
      StObject.set(x, "width", value.asInstanceOf[js.Any])

    @scala.inline
    def setWidthUndefined: Self = StObject.set(x, "width", js.undefined)

    // useSortBy Options
    @scala.inline
    def setDefaultCanSort(value: Boolean)(implicit
      ev:                        Plugins <:< Plugin.SortBy.Tag
    ): Self =
      StObject.set(x, "defaultCanSort", value.asInstanceOf[js.Any])

    @scala.inline
    def setDefaultCanSortUndefined(implicit
      ev: Plugins <:< Plugin.SortBy.Tag
    ): Self = StObject.set(x, "defaultCanSort", js.undefined)

    @scala.inline
    def setDisableSortBy(value: Boolean)(implicit
      ev:                       Plugins <:< Plugin.SortBy.Tag
    ): Self =
      StObject.set(x, "disableSortBy", value.asInstanceOf[js.Any])

    @scala.inline
    def setDisableSortByUndefined(implicit
      ev: Plugins <:< Plugin.SortBy.Tag
    ): Self = StObject.set(x, "disableSortBy", js.undefined)

    @scala.inline
    def setSortDescFirst(value: Boolean)(implicit
      ev:                       Plugins <:< Plugin.SortBy.Tag
    ): Self =
      StObject.set(x, "sortDescFirst", value.asInstanceOf[js.Any])

    @scala.inline
    def setSortDescFirstUndefined(implicit
      ev: Plugins <:< Plugin.SortBy.Tag
    ): Self = StObject.set(x, "sortDescFirst", js.undefined)

    @scala.inline
    def setSortInverted(value: Boolean)(implicit
      ev:                      Plugins <:< Plugin.SortBy.Tag
    ): Self =
      StObject.set(x, "sortInverted", value.asInstanceOf[js.Any])

    @scala.inline
    def setSortInvertedUndefined(implicit
      ev: Plugins <:< Plugin.SortBy.Tag
    ): Self = StObject.set(x, "sortInverted", js.undefined)

    @scala.inline
    def setSortType(value: SortByFn[D] | DefaultSortTypes | String)(implicit
      ev:                  Plugins <:< Plugin.SortBy.Tag
    ): Self =
      StObject.set(x, "sortType", value.asInstanceOf[js.Any])

    @scala.inline
    def setSortTypeFunction4(
      value: (
        /* rowA */ Row[D, Plugins], /* rowB */ Row[D, Plugins], /* columnId */ IdType[D],
        /* desc */ js.UndefOr[
          Boolean
        ]
      ) => Double
    )(implicit
      ev:    Plugins <:< Plugin.SortBy.Tag
    ): Self = StObject.set(x, "sortType", js.Any.fromFunction4(value))

    @scala.inline
    def setSortTypeUndefined(implicit
      ev: Plugins <:< Plugin.SortBy.Tag
    ): Self = StObject.set(x, "sortType", js.undefined)
  }

}
