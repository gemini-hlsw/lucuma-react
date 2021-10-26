package reactST.reactTable.facade.tableOptions

import japgolly.scalajs.react.ReactMouseEventFrom
import org.scalablytyped.runtime.StObject
import org.scalajs.dom.raw.Element
import reactST.reactTable.AggregatorFn
import reactST.reactTable.Plugin
import reactST.reactTable.facade.columnOptions.ColumnOptions
import reactST.reactTable.facade.row.Row
import reactST.reactTable.facade.tableInstance.TableInstance
import reactST.reactTable.facade.tableState.TableState
import reactST.reactTable.mod.ActionType
import reactST.reactTable.mod.IdType
import reactST.reactTable.mod.Meta
import reactST.reactTable.mod.MetaBase
import reactST.reactTable.mod.SortByFn
import reactST.reactTable.mod.UseExpandedOptions
import reactST.reactTable.mod.UseSortByOptions
import reactST.std.Partial
import reactST.std.Record

import scala.scalajs.js

@js.native
trait TableOptions[D, Plugins] extends js.Object {

  var autoResetHiddenColumns: js.UndefOr[Boolean] = js.native

  var columns: js.Array[ColumnOptions[D, Plugins]] = js.native

  var data: js.Array[D] = js.native

  var defaultColumn: js.UndefOr[Partial[ColumnOptions[D, Plugins]]] = js.native

  var getRowId: js.UndefOr[
    js.Function3[
      /* originalRow */ D,
      /* relativeIndex */ Double,
      /* parent */ js.UndefOr[Row[D, Plugins]],
      String
    ]
  ] = js.native

  var getSubRows
    : js.UndefOr[js.Function2[ /* originalRow */ D, /* relativeIndex */ Double, js.Array[D]]] =
    js.native

  var initialState: js.UndefOr[Partial[TableState[D, Plugins]]] = js.native

  var stateReducer: js.UndefOr[
    js.Function4[
      /* newState */ TableState[D, Plugins],
      /* action */ ActionType,
      /* previousState */ TableState[D, Plugins],
      /* instance */ js.UndefOr[TableInstance[D, Plugins]],
      TableState[D, Plugins]
    ]
  ] = js.native

  var useControlledState: js.UndefOr[
    js.Function2[
      /* state */ TableState[D, Plugins],
      /* meta */ Meta[D, scala.Nothing, MetaBase[D]],
      TableState[D, Plugins]
    ]
  ] = js.native
}
object TableOptions {
  @inline
  implicit class UseTableOptionsMutableBuilder[Self <: TableOptions[_, _], D, Plugins](
    val x: Self with TableOptions[D, Plugins]
  ) extends AnyVal {

    @inline
    def setAutoResetHiddenColumns(value: Boolean): Self =
      StObject.set(x, "autoResetHiddenColumns", value.asInstanceOf[js.Any])

    @inline
    def setAutoResetHiddenColumnsUndefined: Self =
      StObject.set(x, "autoResetHiddenColumns", js.undefined)

    @inline
    def setColumns(value: js.Array[ColumnOptions[D, Plugins]]): Self =
      StObject.set(x, "columns", value.asInstanceOf[js.Any])

    @inline
    def setColumnsVarargs(value: ColumnOptions[D, Plugins]*): Self =
      StObject.set(x, "columns", js.Array(value: _*))

    @inline
    def setData(value: js.Array[D]): Self = StObject.set(x, "data", value.asInstanceOf[js.Any])

    @inline
    def setDataVarargs(value: D*): Self = StObject.set(x, "data", js.Array(value: _*))

    @inline
    def setDefaultColumn(value: Partial[ColumnOptions[D, Plugins]]): Self =
      StObject.set(x, "defaultColumn", value.asInstanceOf[js.Any])

    @inline
    def setDefaultColumnUndefined: Self = StObject.set(x, "defaultColumn", js.undefined)

    @inline
    def setGetRowId(
      value: (
        /* originalRow */ D, /* relativeIndex */ Double, /* parent */ js.UndefOr[Row[D, Plugins]]
      ) => String
    ): Self = StObject.set(x, "getRowId", js.Any.fromFunction3(value))

    /**
     * Sets the row id for the rows of the table based on a function.
     *
     * @param f
     *   A function from the row type to the row id.
     */
    @inline
    def setRowIdFn(f: D => String): Self =
      setGetRowId((originalRow, _, _) => f(originalRow))

    /**
     * Sets the row id for the rows of the table based on a function.
     *
     * @param f
     *   A function from the row type and index to the row id.
     */
    @inline
    def setRowIdFn(f: (D, Int) => String): Self =
      setGetRowId((originalRow, relativeIndex, _) => f(originalRow, relativeIndex.toInt))

    /**
     * Sets the row id for the rows of the table based on a function.
     *
     * @param f
     *   A function from the row type, index and parent to the row id.
     */
    @inline
    def setRowIdFn(f: (D, Int, js.UndefOr[Row[D, Plugins]]) => String): Self =
      setGetRowId((originalRow, relativeIndex, parent) =>
        f(originalRow, relativeIndex.toInt, parent)
      )

    @inline
    def setGetRowIdUndefined: Self = StObject.set(x, "getRowId", js.undefined)

    @inline
    def setGetSubRows(
      value: ( /* originalRow */ D, /* relativeIndex */ Double) => js.Array[D]
    ): Self = StObject.set(x, "getSubRows", js.Any.fromFunction2(value))

    @inline
    def setGetSubRowsUndefined: Self = StObject.set(x, "getSubRows", js.undefined)

    @inline
    def setInitialState(value: TableState[D, Plugins]): Self =
      StObject.set(x, "initialState", value.asInstanceOf[js.Any])

    @inline
    def setInitialStateUndefined: Self = StObject.set(x, "initialState", js.undefined)

    @inline
    def setStateReducer(
      value: (
        /* newState */ TableState[D, Plugins], /* action */ ActionType,
        /* previousState */ TableState[D, Plugins],
        /* instance */ js.UndefOr[TableInstance[D, Plugins]]
      ) => TableState[D, Plugins]
    ): Self = StObject.set(x, "stateReducer", js.Any.fromFunction4(value))

    @inline
    def setStateReducerUndefined: Self = StObject.set(x, "stateReducer", js.undefined)

    @inline
    def setUseControlledState(
      value: (
        /* state */ TableState[D, Plugins], /* meta */ Meta[D, scala.Nothing, MetaBase[D]]
      ) => TableState[D, Plugins]
    ): Self = StObject.set(x, "useControlledState", js.Any.fromFunction2(value))

    @inline
    def setUseControlledStateUndefined: Self = StObject.set(x, "useControlledState", js.undefined)

    // useGroupBy Options
    @inline
    def setAggregations(value: Record[String, AggregatorFn[D, Plugins]])(implicit
      ev:                      Plugins <:< Plugin.GroupBy.Tag
    ): Self =
      StObject.set(x, "aggregations", value.asInstanceOf[js.Any])

    @inline
    def setAggregationsUndefined(implicit ev: Plugins <:< Plugin.GroupBy.Tag): Self =
      StObject.set(x, "aggregations", js.undefined)

    @inline
    def setAutoResetGroupBy(value: Boolean)(implicit ev: Plugins <:< Plugin.GroupBy.Tag): Self =
      StObject.set(x, "autoResetGroupBy", value.asInstanceOf[js.Any])

    @inline
    def setAutoResetGroupByUndefined(implicit ev: Plugins <:< Plugin.GroupBy.Tag): Self =
      StObject.set(x, "autoResetGroupBy", js.undefined)

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

    @inline
    def setGroupByFn(
      value:       (
        /* rows */ js.Array[Row[D, Plugins]],
        /* columnId */ IdType[D]
      ) => Record[String, js.Array[Row[D, Plugins]]]
    )(implicit ev: Plugins <:< Plugin.GroupBy.Tag): Self =
      StObject.set(x, "groupByFn", js.Any.fromFunction2(value))

    @inline
    def setGroupByFnUndefined(implicit ev: Plugins <:< Plugin.GroupBy.Tag): Self =
      StObject.set(x, "groupByFn", js.undefined)

    @inline
    def setManualGroupBy(value: Boolean)(implicit ev: Plugins <:< Plugin.GroupBy.Tag): Self =
      StObject.set(x, "manualGroupBy", value.asInstanceOf[js.Any])

    @inline
    def setManualGroupByUndefined(implicit ev: Plugins <:< Plugin.GroupBy.Tag): Self =
      StObject.set(x, "manualGroupBy", js.undefined)

    // useExpanded Options
    @inline
    def setAutoResetExpanded(value: Boolean)(implicit ev: Plugins <:< Plugin.Expanded.Tag): Self =
      StObject.set(x, "autoResetExpanded", value.asInstanceOf[js.Any])

    @inline
    def setAutoResetExpandedUndefined(implicit ev: Plugins <:< Plugin.Expanded.Tag): Self =
      StObject.set(x, "autoResetExpanded", js.undefined)

    @inline
    def setExpandSubRows(value: Boolean)(implicit ev: Plugins <:< Plugin.Expanded.Tag): Self =
      StObject.set(x, "expandSubRows", value.asInstanceOf[js.Any])

    @inline
    def setExpandSubRowsUndefined(implicit ev: Plugins <:< Plugin.Expanded.Tag): Self =
      StObject.set(x, "expandSubRows", js.undefined)

    @inline
    def setManualExpandedKey(value: IdType[D])(implicit ev: Plugins <:< Plugin.Expanded.Tag): Self =
      StObject.set(x, "manualExpandedKey", value.asInstanceOf[js.Any])

    @inline
    def setManualExpandedKeyUndefined(implicit ev: Plugins <:< Plugin.Expanded.Tag): Self =
      StObject.set(x, "manualExpandedKey", js.undefined)

    @inline
    def setPaginateExpandedRows(value: Boolean)(implicit
      ev:                              Plugins <:< Plugin.Expanded.Tag
    ): Self =
      StObject.set(x, "paginateExpandedRows", value.asInstanceOf[js.Any])

    @inline
    def setPaginateExpandedRowsUndefined(implicit ev: Plugins <:< Plugin.Expanded.Tag): Self =
      StObject.set(x, "paginateExpandedRows", js.undefined)

    // useSortBy Options
    @inline
    def setAutoResetSortBy(value: Boolean)(implicit ev: Plugins <:< Plugin.SortBy.Tag): Self =
      StObject.set(x, "autoResetSortBy", value.asInstanceOf[js.Any])

    @inline
    def setAutoResetSortByUndefined(implicit ev: Plugins <:< Plugin.SortBy.Tag): Self =
      StObject.set(x, "autoResetSortBy", js.undefined)

    @inline
    def setDefaultCanSort(value: Boolean)(implicit ev: Plugins <:< Plugin.SortBy.Tag): Self =
      StObject.set(x, "defaultCanSort", value.asInstanceOf[js.Any])

    @inline
    def setDefaultCanSortUndefined(implicit ev: Plugins <:< Plugin.SortBy.Tag): Self =
      StObject.set(x, "defaultCanSort", js.undefined)

    @inline
    def setDisableMultiSort(value: Boolean)(implicit ev: Plugins <:< Plugin.SortBy.Tag): Self =
      StObject.set(x, "disableMultiSort", value.asInstanceOf[js.Any])

    @inline
    def setDisableMultiSortUndefined(implicit ev: Plugins <:< Plugin.SortBy.Tag): Self =
      StObject.set(x, "disableMultiSort", js.undefined)

    @inline
    def setDisableSortBy(value: Boolean)(implicit ev: Plugins <:< Plugin.SortBy.Tag): Self =
      StObject.set(x, "disableSortBy", value.asInstanceOf[js.Any])

    @inline
    def setDisableSortByUndefined(implicit ev: Plugins <:< Plugin.SortBy.Tag): Self =
      StObject.set(x, "disableSortBy", js.undefined)

    @inline
    def setDisableSortRemove(value: Boolean)(implicit ev: Plugins <:< Plugin.SortBy.Tag): Self =
      StObject.set(x, "disableSortRemove", value.asInstanceOf[js.Any])

    @inline
    def setDisableSortRemoveUndefined(implicit ev: Plugins <:< Plugin.SortBy.Tag): Self =
      StObject.set(x, "disableSortRemove", js.undefined)

    @inline
    def setDisabledMultiRemove(value: Boolean)(implicit ev: Plugins <:< Plugin.SortBy.Tag): Self =
      StObject.set(x, "disabledMultiRemove", value.asInstanceOf[js.Any])

    @inline
    def setDisabledMultiRemoveUndefined(implicit ev: Plugins <:< Plugin.SortBy.Tag): Self =
      StObject.set(x, "disabledMultiRemove", js.undefined)

    @inline
    def setIsMultiSortEvent(value: /* e */ ReactMouseEventFrom[Element] => Boolean)(implicit
      ev:                          Plugins <:< Plugin.SortBy.Tag
    ): Self =
      StObject.set(x, "isMultiSortEvent", js.Any.fromFunction1(value))

    @inline
    def setIsMultiSortEventUndefined(implicit ev: Plugins <:< Plugin.SortBy.Tag): Self =
      StObject.set(x, "isMultiSortEvent", js.undefined)

    @inline
    def setManualSortBy(value: Boolean)(implicit ev: Plugins <:< Plugin.SortBy.Tag): Self =
      StObject.set(x, "manualSortBy", value.asInstanceOf[js.Any])

    @inline
    def setManualSortByUndefined(implicit ev: Plugins <:< Plugin.SortBy.Tag): Self =
      StObject.set(x, "manualSortBy", js.undefined)

    @inline
    def setMaxMultiSortColCount(value: Double)(implicit ev: Plugins <:< Plugin.SortBy.Tag): Self =
      StObject.set(x, "maxMultiSortColCount", value.asInstanceOf[js.Any])

    @inline
    def setMaxMultiSortColCountUndefined(implicit ev: Plugins <:< Plugin.SortBy.Tag): Self =
      StObject.set(x, "maxMultiSortColCount", js.undefined)

    @inline
    def setOrderByFn(
      value:       (
        /* rows */ js.Array[Row[D, Plugins]],
        /* sortFns */ js.Array[SortByFn[D]],
        /* directions */ js.Array[Boolean]
      ) => js.Array[Row[D, Plugins]]
    )(implicit ev: Plugins <:< Plugin.SortBy.Tag): Self =
      StObject.set(x, "orderByFn", js.Any.fromFunction3(value))

    @inline
    def setOrderByFnUndefined(implicit ev: Plugins <:< Plugin.SortBy.Tag): Self =
      StObject.set(x, "orderByFn", js.undefined)

    @inline
    def setSortTypes(value: Record[String, SortByFn[D]])(implicit
      ev:                   Plugins <:< Plugin.SortBy.Tag
    ): Self =
      StObject.set(x, "sortTypes", value.asInstanceOf[js.Any])

    @inline
    def setSortTypesUndefined(implicit ev: Plugins <:< Plugin.SortBy.Tag): Self =
      StObject.set(x, "sortTypes", js.undefined)
  }

  // The "conv" mechanism is mainly to get the implicit conversion to kick in when we have a Reusable.
  @inline
  implicit def toGroupByTableOptions[D, Plugins, Self](tableOptions: Self)(implicit
    conv:                                                            Self => TableOptions[D, Plugins],
    ev:                                                              Plugins <:< Plugin.GroupBy.Tag
  ): Self with UseGroupByTableOptions[D, Plugins] =
    conv(tableOptions).asInstanceOf[Self with UseGroupByTableOptions[D, Plugins]]

  @inline
  implicit def toExpandedTableOptions[D, Plugins, Self](tableOptions: Self)(implicit
    conv:                                                             Self => TableOptions[D, Plugins],
    ev:                                                               Plugins <:< Plugin.Expanded.Tag
  ): Self with UseExpandedOptions[D] =
    conv(tableOptions).asInstanceOf[Self with UseExpandedOptions[D]]

  @inline
  implicit def toSortByTableOptions[D, Plugins, Self](tableOptions: Self)(implicit
    conv:                                                           Self => TableOptions[D, Plugins],
    ev:                                                             Plugins <:< Plugin.SortBy.Tag
  ): Self with UseSortByOptions[D] =
    conv(tableOptions).asInstanceOf[Self with UseSortByOptions[D]]
}
