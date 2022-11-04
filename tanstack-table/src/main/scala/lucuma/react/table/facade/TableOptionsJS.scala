// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table.facade

import japgolly.scalajs.react.*
import reactST.{tanstackTableCore => raw}

import scalajs.js
import scalajs.js.JSConverters.*

trait TableOptionsJs[T] extends js.Object:
  var columns: js.Array[ColumnDefJs[T, ?]]
  var data: js.Array[T]
  var getCoreRowModel: js.Function1[raw.mod.Table[T], js.Function0[raw.mod.RowModel[T]]]

  var getRowId: js.UndefOr[js.Function3[T, Int, js.UndefOr[T], String]]          = js.undefined
  var onStateChange: js.UndefOr[raw.mod.Updater[raw.mod.TableState] => Callback] = js.undefined
  var renderFallbackValue: js.UndefOr[Any]                                       = js.undefined
  var state: js.UndefOr[raw.anon.PartialTableState]                              = js.undefined
  var initialState: js.UndefOr[raw.mod.InitialTableState]                        = js.undefined

  // Column Sizing // TODO Rest of the properties
  var enableColumnResizing: js.UndefOr[Boolean]              = js.undefined
  var columnResizeMode: js.UndefOr[raw.mod.ColumnResizeMode] = js.undefined

  // Column Visibility
  var enableHiding: js.UndefOr[Boolean]                                                 = js.undefined
  var onColumnVisibilityChange: js.UndefOr[raw.mod.OnChangeFn[raw.mod.VisibilityState]] =
    js.undefined

  // Sorting
  var enableMultiRemove: js.UndefOr[Boolean]                                = js.undefined
  var enableMultiSort: js.UndefOr[Boolean]                                  = js.undefined
  var enableSorting: js.UndefOr[Boolean]                                    = js.undefined
  var enableSortingRemoval: js.UndefOr[Boolean]                             = js.undefined
  var getSortedRowModel: js.UndefOr[
    js.Function1[raw.mod.Table[T], js.Function0[raw.mod.RowModel[T]]]
  ]                                                                         = js.undefined
  var isMultiSortEvent: js.UndefOr[js.Function1[ /* e */ Any, Boolean]]     = js.undefined
  var manualSorting: js.UndefOr[Boolean]                                    = js.undefined
  var maxMultiSortColCount: js.UndefOr[Double]                              = js.undefined
  var onSortingChange: js.UndefOr[raw.mod.OnChangeFn[raw.mod.SortingState]] = js.undefined
  var sortDescFirst: js.UndefOr[Boolean]                                    = js.undefined

  // Selection
  var enableRowSelection: js.UndefOr[Boolean]                                         = js.undefined
  var enableMultiRowSelection: js.UndefOr[Boolean]                                    = js.undefined
  var onRowSelectionChange: js.UndefOr[raw.mod.OnChangeFn[raw.mod.RowSelectionState]] =
    js.undefined
  // Expanding
  var enableExpanding: js.UndefOr[Boolean]                                            = js.undefined
  var getExpandedRowModel: js.UndefOr[
    js.Function1[raw.mod.Table[T], js.Function0[raw.mod.RowModel[T]]]
  ]                                                                                   = js.undefined
  var getSubRows: js.UndefOr[js.Function2[T, Int, js.UndefOr[js.Array[T]]]]           = js.undefined
