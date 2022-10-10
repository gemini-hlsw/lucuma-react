// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import japgolly.scalajs.react.*
import lucuma.react.table.facade.ColumnDefJS
import lucuma.react.table.facade.TableOptionsJS
import reactST.{tanstackReactTable => rawReact}
import reactST.{tanstackTableCore => raw}

import scalajs.js
import scalajs.js.JSConverters.*

case class TableOptions[T](
  columns:                  Reusable[List[ColumnDef[T, ?]]],
  data:                     Reusable[List[T]],
  getRowId:                 js.UndefOr[(T, Int, Option[T]) => String] = js.undefined,
  getCoreRowModel:          js.UndefOr[raw.mod.Table[T] => js.Function0[raw.mod.RowModel[T]]] = js.undefined,
  onStateChange:            js.UndefOr[raw.mod.Updater[raw.mod.TableState] => Callback] = js.undefined,
  renderFallbackValue:      js.UndefOr[Any] = js.undefined,
  state:                    js.UndefOr[raw.anon.PartialTableState] = js.undefined,
  initialState:             js.UndefOr[raw.mod.InitialTableState] = js.undefined,
  // Column Sizing
  enableColumnResizing:     js.UndefOr[Boolean] = js.undefined,
  columnResizeMode:         js.UndefOr[raw.mod.ColumnResizeMode] = js.undefined,
  // Column Visibility
  enableHiding:             js.UndefOr[Boolean] = js.undefined,
  onColumnVisibilityChange: js.UndefOr[raw.mod.VisibilityState => Callback] = js.undefined,
  // Sorting
  enableSorting:            js.UndefOr[Boolean] = js.undefined,
  enableMultiSort:          js.UndefOr[Boolean] = js.undefined,
  enableSortingRemoval:     js.UndefOr[Boolean] = js.undefined,
  enableMultiRemove:        js.UndefOr[Boolean] = js.undefined,
  getSortedRowModel:        js.UndefOr[raw.mod.Table[T] => js.Function0[raw.mod.RowModel[T]]] =
    js.undefined,
  isMultiSortEvent:         js.UndefOr[js.Function1[ /* e */ Any, Boolean]] = js.undefined,
  manualSorting:            js.UndefOr[Boolean] = js.undefined,
  maxMultiSortColCount:     js.UndefOr[Double] = js.undefined,
  onSortingChange:          js.UndefOr[raw.mod.OnChangeFn[raw.mod.SortingState]] = js.undefined,
  sortDescFirst:            js.UndefOr[Boolean] = js.undefined,
  // Expanding
  enableExpanding:          js.UndefOr[Boolean] = js.undefined,
  getExpandedRowModel:      js.UndefOr[raw.mod.Table[T] => js.Function0[raw.mod.RowModel[T]]] =
    js.undefined,
  getSubRows:               js.UndefOr[(T, Int) => Option[List[T]]] = js.undefined
) {
  // Memoized columns and data must be passed.
  def toJS(cols: js.Array[ColumnDefJS[T, ?]], rows: js.Array[T]): TableOptionsJS[T] =
    TableOptionsJS(
      columns = cols,
      data = rows,
      getRowId =
        getRowId.map(fn => (originalRow, index, parent) => fn(originalRow, index, parent.toOption)),
      getCoreRowModel = getCoreRowModel.map(fn => fn), // Forces conversion to js.Function
      onStateChange = onStateChange,
      renderFallbackValue = renderFallbackValue,
      state = state,
      initialState = initialState,
      // Column Sizing
      enableColumnResizing = enableColumnResizing,
      columnResizeMode = columnResizeMode,
      // Column Visibility
      enableHiding = enableHiding,
      onColumnVisibilityChange = onColumnVisibilityChange,
      // Sorting
      enableSorting = enableSorting,
      getSortedRowModel = getSortedRowModel.map(fn => fn),
      // Expanding
      enableExpanding = enableExpanding,
      getExpandedRowModel = getExpandedRowModel.map(fn => fn),
      getSubRows = getSubRows.map(fn =>
        (originalRow, index) => fn(originalRow, index).map(_.toJSArray).orUndefined
      )
    )
}
