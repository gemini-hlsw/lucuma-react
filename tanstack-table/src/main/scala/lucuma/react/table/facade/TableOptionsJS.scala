// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table.facade

import lucuma.typed.tanstackTableCore as raw

import scalajs.js

/**
 * @tparam T
 *   The type of the row.
 * @tparam TM
 *   The type of the metadata for the table.
 * @tparam CM
 *   The type of the metadata for the column.
 */
trait TableOptionsJs[T, TM, CM] extends js.Object:
  var columns: js.Array[ColumnDefJs[T, ?, CM]]
  var data: js.Array[T]

  var getCoreRowModel: js.Function1[raw.buildLibTypesMod.Table[T], js.Function0[
    raw.buildLibTypesMod.RowModel[T]
  ]]

  var getRowId: js.UndefOr[js.Function3[T, Int, js.UndefOr[T], String]]                           = js.undefined
  var onStateChange: js.UndefOr[raw.buildLibTypesMod.OnChangeFn[raw.buildLibTypesMod.TableState]] =
    js.undefined
  var renderFallbackValue: js.UndefOr[Any]                                                        = js.undefined
  var state: js.UndefOr[raw.anon.PartialTableState]                                               = js.undefined
  var initialState: js.UndefOr[raw.buildLibTypesMod.InitialTableState]                            = js.undefined
  var meta: js.UndefOr[TM]                                                                        = js.undefined

  // Column Sizing // TODO Rest of the properties
  var enableColumnResizing: js.UndefOr[Boolean]                                          = js.undefined
  var columnResizeMode: js.UndefOr[raw.buildLibFeaturesColumnSizingMod.ColumnResizeMode] =
    js.undefined
  var onColumnSizingChange: js.UndefOr[
    raw.buildLibTypesMod.OnChangeFn[raw.buildLibFeaturesColumnSizingMod.ColumnSizingState]
  ] = js.undefined
  var onColumnSizingInfoChange: js.UndefOr[
    raw.buildLibTypesMod.OnChangeFn[raw.buildLibFeaturesColumnSizingMod.ColumnSizingInfoState]
  ] =
    js.undefined

  // Column Visibility
  var enableHiding: js.UndefOr[Boolean] = js.undefined
  var onColumnVisibilityChange: js.UndefOr[
    raw.buildLibTypesMod.OnChangeFn[raw.buildLibFeaturesColumnVisibilityMod.VisibilityState]
  ] =
    js.undefined

  // Sorting
  var enableMultiRemove: js.UndefOr[Boolean]                            = js.undefined
  var enableMultiSort: js.UndefOr[Boolean]                              = js.undefined
  var enableSorting: js.UndefOr[Boolean]                                = js.undefined
  var enableSortingRemoval: js.UndefOr[Boolean]                         = js.undefined
  var getSortedRowModel: js.UndefOr[
    js.Function1[raw.buildLibTypesMod.Table[T], js.Function0[raw.buildLibTypesMod.RowModel[T]]]
  ] = js.undefined
  var isMultiSortEvent: js.UndefOr[js.Function1[ /* e */ Any, Boolean]] = js.undefined
  var manualSorting: js.UndefOr[Boolean]                                = js.undefined
  var maxMultiSortColCount: js.UndefOr[Double]                          = js.undefined
  var onSortingChange
    : js.UndefOr[raw.buildLibTypesMod.OnChangeFn[raw.buildLibFeaturesRowSortingMod.SortingState]] =
    js.undefined
  var sortDescFirst: js.UndefOr[Boolean]                                = js.undefined

  // Selection
  var enableRowSelection: js.UndefOr[Boolean]      = js.undefined
  var enableMultiRowSelection: js.UndefOr[Boolean] = js.undefined
  var onRowSelectionChange: js.UndefOr[
    raw.buildLibTypesMod.OnChangeFn[raw.buildLibFeaturesRowSelectionMod.RowSelectionState]
  ] =
    js.undefined

  // Expanding
  var enableExpanding: js.UndefOr[Boolean]                                  = js.undefined
  var getExpandedRowModel: js.UndefOr[
    js.Function1[raw.buildLibTypesMod.Table[T], js.Function0[raw.buildLibTypesMod.RowModel[T]]]
  ] = js.undefined
  var getSubRows: js.UndefOr[js.Function2[T, Int, js.UndefOr[js.Array[T]]]] = js.undefined

  // Pinning
  var enablePinning: js.UndefOr[Boolean] = js.undefined

  // Column Pinning
  var enableColumnPinning: js.UndefOr[Boolean] = js.undefined
  var onColumnPinningChange: js.UndefOr[
    raw.buildLibTypesMod.OnChangeFn[raw.buildLibFeaturesColumnPinningMod.ColumnPinningState]
  ] =
    js.undefined

  // Row Pinning
  var enableRowPinning: js.UndefOr[Boolean | js.Function1[raw.buildLibTypesMod.Row[T], Boolean]] =
    js.undefined
  var keepPinnedRows: js.UndefOr[Boolean]                                                        =
    js.undefined
  var onRowPinningChange: js.UndefOr[
    raw.buildLibTypesMod.OnChangeFn[raw.buildLibFeaturesRowPinningMod.RowPinningState]
  ] =
    js.undefined

  // Column Filtering
  var enableFilters: js.UndefOr[Boolean]        = js.undefined
  var enableColumnFilters: js.UndefOr[Boolean]  = js.undefined
  var onColumnFiltersChange: js.UndefOr[
    raw.buildLibTypesMod.OnChangeFn[raw.buildLibFeaturesColumnFilteringMod.ColumnFiltersState]
  ] =
    js.undefined
  var filterFromLeafRows: js.UndefOr[Boolean]   = js.undefined
  var maxLeafRowFilterDepth: js.UndefOr[Double] = js.undefined
  var manualFiltering: js.UndefOr[Boolean]      = js.undefined
  var getFilteredRowModel: js.UndefOr[
    js.Function1[raw.buildLibTypesMod.Table[T], js.Function0[raw.buildLibTypesMod.RowModel[T]]]
  ] = js.undefined

  // Global Filtering
  var enableGlobalFilter: js.UndefOr[Boolean]                                                 = js.undefined
  var globalFilterFn: js.UndefOr[String | raw.buildLibFeaturesColumnFilteringMod.FilterFn[T]] =
    js.undefined
  var onGlobalFilterChange: js.UndefOr[
    raw.buildLibTypesMod.OnChangeFn[raw.buildLibFeaturesGlobalFilteringMod.GlobalFilterTableState]
  ] = js.undefined
  var getColumnCanGlobalFilter: js.UndefOr[raw.buildLibTypesMod.Column[T, Any] => Boolean]    =
    js.undefined
  var getPreFilteredRowModel: js.UndefOr[
    js.Function1[raw.buildLibTypesMod.Table[T], js.Function0[raw.buildLibTypesMod.RowModel[T]]]
  ] = js.undefined
