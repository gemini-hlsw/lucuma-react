package reactST.reactTable.facade.columnOptions

import japgolly.scalajs.react.ReactMouseEventFrom
import org.scalablytyped.runtime.StObject
import org.scalajs.dom.raw.Element
import reactST.reactTable.facade.row.Row
import reactST.reactTable.facade.tableInstance.TableInstance
import reactST.reactTable.facade.tableState.TableState
import reactST.reactTable.mod.ActionType
import reactST.reactTable.mod.Meta
import reactST.reactTable.mod.MetaBase
import reactST.reactTable.mod.SortByFn
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

  @scala.inline
  def apply[D, Plugins](
    columns: js.Array[ColumnOptions[D, Plugins]],
    data:    js.Array[D]
  ): TableOptions[D, Plugins] = {
    val __obj =
      js.Dynamic.literal(columns = columns.asInstanceOf[js.Any], data = data.asInstanceOf[js.Any])
    __obj.asInstanceOf[TableOptions[D, Plugins]]
  }

  @scala.inline
  implicit class UseTableOptionsMutableBuilder[Self <: TableOptions[_, _], D, Plugins](
    val x: Self with TableOptions[D, Plugins]
  ) extends AnyVal {

    @scala.inline
    def setAutoResetHiddenColumns(value: Boolean): Self =
      StObject.set(x, "autoResetHiddenColumns", value.asInstanceOf[js.Any])

    @scala.inline
    def setAutoResetHiddenColumnsUndefined: Self =
      StObject.set(x, "autoResetHiddenColumns", js.undefined)

    @scala.inline
    def setColumns(value: js.Array[ColumnOptions[D, Plugins]]): Self =
      StObject.set(x, "columns", value.asInstanceOf[js.Any])

    @scala.inline
    def setColumnsVarargs(value: ColumnOptions[D, Plugins]*): Self =
      StObject.set(x, "columns", js.Array(value: _*))

    @scala.inline
    def setData(value: js.Array[D]): Self = StObject.set(x, "data", value.asInstanceOf[js.Any])

    @scala.inline
    def setDataVarargs(value: D*): Self = StObject.set(x, "data", js.Array(value: _*))

    @scala.inline
    def setDefaultColumn(value: Partial[ColumnOptions[D, Plugins]]): Self =
      StObject.set(x, "defaultColumn", value.asInstanceOf[js.Any])

    @scala.inline
    def setDefaultColumnUndefined: Self = StObject.set(x, "defaultColumn", js.undefined)

    @scala.inline
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

    @scala.inline
    def setGetRowIdUndefined: Self = StObject.set(x, "getRowId", js.undefined)

    @scala.inline
    def setGetSubRows(
      value: ( /* originalRow */ D, /* relativeIndex */ Double) => js.Array[D]
    ): Self = StObject.set(x, "getSubRows", js.Any.fromFunction2(value))

    @scala.inline
    def setGetSubRowsUndefined: Self = StObject.set(x, "getSubRows", js.undefined)

    @scala.inline
    def setInitialState(value: TableState[D, Plugins]): Self =
      StObject.set(x, "initialState", value.asInstanceOf[js.Any])

    @scala.inline
    def setInitialStateUndefined: Self = StObject.set(x, "initialState", js.undefined)

    @scala.inline
    def setStateReducer(
      value: (
        /* newState */ TableState[D, Plugins], /* action */ ActionType,
        /* previousState */ TableState[D, Plugins],
        /* instance */ js.UndefOr[TableInstance[D, Plugins]]
      ) => TableState[D, Plugins]
    ): Self = StObject.set(x, "stateReducer", js.Any.fromFunction4(value))

    @scala.inline
    def setStateReducerUndefined: Self = StObject.set(x, "stateReducer", js.undefined)

    @scala.inline
    def setUseControlledState(
      value: (
        /* state */ TableState[D, Plugins], /* meta */ Meta[D, scala.Nothing, MetaBase[D]]
      ) => TableState[D, Plugins]
    ): Self = StObject.set(x, "useControlledState", js.Any.fromFunction2(value))

    @scala.inline
    def setUseControlledStateUndefined: Self = StObject.set(x, "useControlledState", js.undefined)

// useSortBy Options
    @scala.inline
    def setAutoResetSortBy(value: Boolean): Self =
      StObject.set(x, "autoResetSortBy", value.asInstanceOf[js.Any])

    @scala.inline
    def setAutoResetSortByUndefined: Self = StObject.set(x, "autoResetSortBy", js.undefined)

    @scala.inline
    def setDefaultCanSort(value: Boolean): Self =
      StObject.set(x, "defaultCanSort", value.asInstanceOf[js.Any])

    @scala.inline
    def setDefaultCanSortUndefined: Self = StObject.set(x, "defaultCanSort", js.undefined)

    @scala.inline
    def setDisableMultiSort(value: Boolean): Self =
      StObject.set(x, "disableMultiSort", value.asInstanceOf[js.Any])

    @scala.inline
    def setDisableMultiSortUndefined: Self = StObject.set(x, "disableMultiSort", js.undefined)

    @scala.inline
    def setDisableSortBy(value: Boolean): Self =
      StObject.set(x, "disableSortBy", value.asInstanceOf[js.Any])

    @scala.inline
    def setDisableSortByUndefined: Self = StObject.set(x, "disableSortBy", js.undefined)

    @scala.inline
    def setDisableSortRemove(value: Boolean): Self =
      StObject.set(x, "disableSortRemove", value.asInstanceOf[js.Any])

    @scala.inline
    def setDisableSortRemoveUndefined: Self = StObject.set(x, "disableSortRemove", js.undefined)

    @scala.inline
    def setDisabledMultiRemove(value: Boolean): Self =
      StObject.set(x, "disabledMultiRemove", value.asInstanceOf[js.Any])

    @scala.inline
    def setDisabledMultiRemoveUndefined: Self = StObject.set(x, "disabledMultiRemove", js.undefined)

    @scala.inline
    def setIsMultiSortEvent(value: /* e */ ReactMouseEventFrom[Element] => Boolean): Self =
      StObject.set(x, "isMultiSortEvent", js.Any.fromFunction1(value))

    @scala.inline
    def setIsMultiSortEventUndefined: Self = StObject.set(x, "isMultiSortEvent", js.undefined)

    @scala.inline
    def setManualSortBy(value: Boolean): Self =
      StObject.set(x, "manualSortBy", value.asInstanceOf[js.Any])

    @scala.inline
    def setManualSortByUndefined: Self = StObject.set(x, "manualSortBy", js.undefined)

    @scala.inline
    def setMaxMultiSortColCount(value: Double): Self =
      StObject.set(x, "maxMultiSortColCount", value.asInstanceOf[js.Any])

    @scala.inline
    def setMaxMultiSortColCountUndefined: Self =
      StObject.set(x, "maxMultiSortColCount", js.undefined)

    @scala.inline
    def setOrderByFn(
      value: (
        /* rows */ js.Array[Row[D, Plugins]], /* sortFns */ js.Array[
          SortByFn[D]
        ], /* directions */ js.Array[
          Boolean
        ]
      ) => js.Array[Row[D, Plugins]]
    ): Self = StObject.set(x, "orderByFn", js.Any.fromFunction3(value))

    @scala.inline
    def setOrderByFnUndefined: Self = StObject.set(x, "orderByFn", js.undefined)

    @scala.inline
    def setSortTypes(value: Record[String, SortByFn[D]]): Self =
      StObject.set(x, "sortTypes", value.asInstanceOf[js.Any])

    @scala.inline
    def setSortTypesUndefined: Self = StObject.set(x, "sortTypes", js.undefined)
  }
}
