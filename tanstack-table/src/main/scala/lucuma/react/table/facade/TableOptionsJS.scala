// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table.facade

import japgolly.scalajs.react.*
import reactST.{tanstackReactTable => rawReact}
import reactST.{tanstackTableCore => raw}

import scalajs.js
import scalajs.js.JSConverters.*

trait TableOptionsJS[T] extends js.Object:
  var columns: js.Array[ColumnDefJS[T, ?]]
  var data: js.Array[T]
  var getCoreRowModel: js.Function1[raw.mod.Table[T], js.Function0[raw.mod.RowModel[T]]]
  var onStateChange: js.UndefOr[raw.mod.Updater[raw.mod.TableState] => Callback] = js.undefined
  var renderFallbackValue: js.UndefOr[Any]                                       = js.undefined
  var state: js.UndefOr[raw.anon.PartialTableState]                              = js.undefined
  var initialState: js.UndefOr[raw.mod.InitialTableState]                        = js.undefined

  // Column Sizing // TODO Rest of the properties
  var enableColumnResizing: js.UndefOr[Boolean]              = js.undefined
  var columnResizeMode: js.UndefOr[raw.mod.ColumnResizeMode] = js.undefined

  // Column Visibility
  var enableHiding: js.UndefOr[Boolean]                                                 = js.undefined
  var onColumnVisibilityChange: js.UndefOr[js.Function1[raw.mod.VisibilityState, Unit]] =
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

  // Expanding
  var enableExpanding: js.UndefOr[Boolean]                 = js.undefined
  var getExpandedRowModel: js.UndefOr[
    js.Function1[raw.mod.Table[T], js.Function0[raw.mod.RowModel[T]]]
  ]                                                        = js.undefined
  var getSubRows: js.UndefOr[js.Function1[T, js.Array[T]]] = js.undefined // Undocumented!

object TableOptionsJS:
  def apply[T](
    columns:                  js.Array[ColumnDefJS[T, ?]],
    data:                     js.Array[T],
    getCoreRowModel:          js.UndefOr[
      js.Function1[raw.mod.Table[T], js.Function0[raw.mod.RowModel[T]]]
    ] = js.undefined,
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
    enableMultiRemove:        js.UndefOr[Boolean] = js.undefined,
    enableMultiSort:          js.UndefOr[Boolean] = js.undefined,
    enableSorting:            js.UndefOr[Boolean] = js.undefined,
    enableSortingRemoval:     js.UndefOr[Boolean] = js.undefined,
    getSortedRowModel:        js.UndefOr[
      js.Function1[raw.mod.Table[T], js.Function0[raw.mod.RowModel[T]]]
    ] = js.undefined,
    isMultiSortEvent:         js.UndefOr[js.Function1[ /* e */ Any, Boolean]] = js.undefined,
    manualSorting:            js.UndefOr[Boolean] = js.undefined,
    maxMultiSortColCount:     js.UndefOr[Double] = js.undefined,
    onSortingChange:          js.UndefOr[raw.mod.OnChangeFn[raw.mod.SortingState]] = js.undefined,
    sortDescFirst:            js.UndefOr[Boolean] = js.undefined,
    // Expanding
    enableExpanding:          js.UndefOr[Boolean] = js.undefined,
    getExpandedRowModel:      js.UndefOr[
      js.Function1[raw.mod.Table[T], js.Function0[raw.mod.RowModel[T]]]
    ] = js.undefined,
    getSubRows:               js.UndefOr[js.Function1[T, js.Array[T]]] = js.undefined // Undocumented!
  ): TableOptionsJS[T] = {
    val p: TableOptionsJS[T] = new js.Object().asInstanceOf[TableOptionsJS[T]]
    p.columns = columns
    p.data = data
    p.getCoreRowModel = getCoreRowModel.getOrElse(rawReact.mod.getCoreRowModel())
    onStateChange.foreach(fn => p.onStateChange = fn)
    renderFallbackValue.foreach(v => p.renderFallbackValue = v)
    state.foreach(v => p.state = v)
    initialState.foreach(v => p.initialState = v)

    // Column Sizing
    // TODO MISSING PROPS!!!
    enableColumnResizing.foreach(v => p.enableColumnResizing = v)
    columnResizeMode.foreach(v => p.columnResizeMode = v)

    // Column Visibility
    enableHiding.foreach(v => p.enableHiding = v)
    onColumnVisibilityChange.foreach(fn => p.onColumnVisibilityChange = fn.andThen(_.runNow()))

    // : js.UndefOr[raw.mod.VisibilityState => Callback] = js.undefined,

    // Sorting
    // TODO MISSING PROPS!!!
    // We are always defining a sortedRowModel
    p.enableSorting = enableSorting.getOrElse(false)
    p.getSortedRowModel = getSortedRowModel.getOrElse(rawReact.mod.getSortedRowModel())

    // Expanding
    // TODO MISSING PROPS!!!
    p.enableExpanding = enableExpanding.getOrElse(false)
    p.getExpandedRowModel = getExpandedRowModel.getOrElse(rawReact.mod.getExpandedRowModel())
    getSubRows.foreach(fn => p.getSubRows = fn)
    p
  }
