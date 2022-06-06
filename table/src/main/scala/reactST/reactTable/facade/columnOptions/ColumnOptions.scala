// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package reactST.reactTable.facade.columnOptions

import japgolly.scalajs.react.facade.{React => ReactRaw}
import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.VdomNode
import org.scalablytyped.runtime.StObject
import reactST.reactTable.Aggregator
import reactST.reactTable.Plugin
import reactST.reactTable.SingleColumnOptions
import reactST.reactTable.facade.cell.CellProps
import reactST.reactTable.facade.column.HeaderProps
import reactST.reactTable.facade.row.Row
import reactST.reactTable.mod.AggregatedValue
import reactST.reactTable.mod.CellValue
import reactST.reactTable.mod.DefaultSortTypes
import reactST.reactTable.mod.IdType
import reactST.reactTable.mod.Renderer
import reactST.reactTable.mod.SortByFn
import reactST.reactTable.mod.UseSortByColumnOptions

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait ColumnOptions[D, Plugins] extends js.Object {

  var Header: js.UndefOr[Renderer[HeaderProps[D, Plugins]]] = js.native

  var id: js.UndefOr[IdType[D]] = js.native

  var maxWidth: js.UndefOr[Double] = js.native

  var minWidth: js.UndefOr[Double] = js.native

  var width: js.UndefOr[Double | String] = js.native
}

object ColumnOptions {

  @inline
  implicit class UseTableColumnOptionsMutableBuilder[Self <: ColumnOptions[
    ?,
    ?
  ], D, Plugins](val x: Self with ColumnOptions[D, Plugins])
      extends AnyVal {

    @inline
    def setHeader(value: HeaderProps[D, Plugins] => VdomNode): Self =
      StObject.set(
        x,
        "Header",
        value.andThen(_.rawNode): js.Function1[HeaderProps[D, Plugins], ReactRaw.Node]
      )

    @inline
    def setHeader(value: Renderer[HeaderProps[D, Plugins]]): Self =
      StObject.set(x, "Header", value.asInstanceOf[js.Any])

    @inline
    def setHeaderUndefined: Self = StObject.set(x, "Header", js.undefined)

    @inline
    def setHeaderVdomElement(value: VdomElement): Self =
      StObject.set(x, "Header", value.rawElement.asInstanceOf[js.Any])

    @inline
    def setId(value: IdType[D]): Self = StObject.set(x, "id", value.asInstanceOf[js.Any])

    @inline
    def setIdUndefined: Self = StObject.set(x, "id", js.undefined)

    @inline
    def setMaxWidth(value: Double): Self = StObject.set(x, "maxWidth", value.asInstanceOf[js.Any])

    @inline
    def setMaxWidthUndefined: Self = StObject.set(x, "maxWidth", js.undefined)

    @inline
    def setMinWidth(value: Double): Self = StObject.set(x, "minWidth", value.asInstanceOf[js.Any])

    @inline
    def setMinWidthUndefined: Self = StObject.set(x, "minWidth", js.undefined)

    @inline
    def setWidth(value: Double | String): Self =
      StObject.set(x, "width", value.asInstanceOf[js.Any])

    @inline
    def setWidthUndefined: Self = StObject.set(x, "width", js.undefined)

    // useGroupBy Options
    @inline
    def setAggregate(value: Aggregator[D, Plugins])(implicit
      ev:                   Plugins <:< Plugin.GroupBy.Tag
    ): Self =
      StObject.set(x, "aggregate", value.asInstanceOf[js.Any])

    @inline
    def setAggregateFunction3(
      value:       (
        /* columnValues */ js.Array[CellValue[js.Any]],
        /* rows */ js.Array[Row[D, Plugins]],
        /* isAggregated */ Boolean
      ) => AggregatedValue
    )(implicit ev: Plugins <:< Plugin.GroupBy.Tag): Self =
      StObject.set(x, "aggregate", js.Any.fromFunction3(value))

    @inline
    def setAggregateUndefined(implicit ev: Plugins <:< Plugin.GroupBy.Tag): Self =
      StObject.set(x, "aggregate", js.undefined)

    @inline
    def setAggregated(value: Renderer[CellProps[D, js.Any, Plugins]])(implicit
      ev:                    Plugins <:< Plugin.GroupBy.Tag
    ): Self =
      StObject.set(x, "Aggregated", value.asInstanceOf[js.Any])

    @inline
    def setAggregatedUndefined(implicit ev: Plugins <:< Plugin.GroupBy.Tag): Self =
      StObject.set(x, "Aggregated", js.undefined)

    @inline
    def setAggregatedVdomElement(value: VdomElement)(implicit
      ev:                               Plugins <:< Plugin.GroupBy.Tag
    ): Self =
      StObject.set(x, "Aggregated", value.rawElement.asInstanceOf[js.Any])

    @inline
    def setDefaultCanGroupBy(value: Boolean)(implicit ev: Plugins <:< Plugin.GroupBy.Tag): Self =
      StObject.set(x, "defaultCanGroupBy", value.asInstanceOf[js.Any])

    @inline
    def setDefaultCanGroupByUndefined(implicit ev: Plugins <:< Plugin.GroupBy.Tag): Self =
      StObject.set(x, "defaultCanGroupBy", js.undefined)

    @inline
    def setDisableGroupBy(value: Boolean)(implicit ev: Plugins <:< Plugin.GroupBy.Tag): Self =
      StObject.set(x, "disableGroupBy", value.asInstanceOf[js.Any])

    @inline
    def setDisableGroupByUndefined(implicit ev: Plugins <:< Plugin.GroupBy.Tag): Self =
      StObject.set(x, "disableGroupBy", js.undefined)

    // useSortBy Options
    @inline
    def setDefaultCanSort(value: Boolean)(implicit
      ev:                        Plugins <:< Plugin.SortBy.Tag
    ): Self =
      StObject.set(x, "defaultCanSort", value.asInstanceOf[js.Any])

    @inline
    def setDefaultCanSortUndefined(implicit ev: Plugins <:< Plugin.SortBy.Tag): Self =
      StObject.set(x, "defaultCanSort", js.undefined)

    @inline
    def setDisableSortBy(value: Boolean)(implicit
      ev:                       Plugins <:< Plugin.SortBy.Tag
    ): Self =
      StObject.set(x, "disableSortBy", value.asInstanceOf[js.Any])

    @inline
    def setDisableSortByUndefined(implicit
      ev: Plugins <:< Plugin.SortBy.Tag
    ): Self = StObject.set(x, "disableSortBy", js.undefined)

    @inline
    def setSortDescFirst(value: Boolean)(implicit
      ev:                       Plugins <:< Plugin.SortBy.Tag
    ): Self =
      StObject.set(x, "sortDescFirst", value.asInstanceOf[js.Any])

    @inline
    def setSortDescFirstUndefined(implicit
      ev: Plugins <:< Plugin.SortBy.Tag
    ): Self = StObject.set(x, "sortDescFirst", js.undefined)

    @inline
    def setSortInverted(value: Boolean)(implicit
      ev:                      Plugins <:< Plugin.SortBy.Tag
    ): Self =
      StObject.set(x, "sortInverted", value.asInstanceOf[js.Any])

    @inline
    def setSortInvertedUndefined(implicit
      ev: Plugins <:< Plugin.SortBy.Tag
    ): Self = StObject.set(x, "sortInverted", js.undefined)

    @inline
    def setSortType(value: SortByFn[D] | DefaultSortTypes | String)(implicit
      ev:                  Plugins <:< Plugin.SortBy.Tag
    ): Self =
      StObject.set(x, "sortType", value.asInstanceOf[js.Any])

    @inline
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

    @inline
    def setSortTypeUndefined(implicit
      ev: Plugins <:< Plugin.SortBy.Tag
    ): Self = StObject.set(x, "sortType", js.undefined)
  }

  // The "conv" mechanism is mainly to get the implicit conversion to kick in when we have a Reusable.
  @inline
  implicit def toGroupByColumnOptions[D, Plugins, Self](colOptions: Self)(implicit
    conv:                                                           Self => SingleColumnOptions[D, Plugins],
    ev:                                                             Plugins <:< Plugin.GroupBy.Tag
  ): Self with UseGroupByColumnOptions[D, Plugins] =
    conv(colOptions).asInstanceOf[Self with UseGroupByColumnOptions[D, Plugins]]

  @inline
  implicit def toSortByColumnOptions[D, Plugins, Self](colOptions: Self)(implicit
    conv:                                                          Self => SingleColumnOptions[D, Plugins],
    ev:                                                            Plugins <:< Plugin.SortBy.Tag
  ): Self with UseSortByColumnOptions[D] =
    conv(colOptions).asInstanceOf[Self with UseSortByColumnOptions[D]]
}
