// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import cats.syntax.all.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.facade.SyntheticEvent
import lucuma.react.table.facade.ColumnDefJs
import lucuma.react.table.facade.TableOptionsJs
import lucuma.typed.tanstackReactTable as rawReact
import lucuma.typed.tanstackTableCore as raw
import org.scalajs.dom

import scalajs.js
import scalajs.js.JSConverters.*

/**
 * @tparam T
 *   The type of the row.
 * @tparam A
 *   The type of the column.
 * @tparam TM
 *   The type of the metadata for the table.
 * @tparam CM
 *   The type of the metadata for the column.
 * @tparam TF
 *   The type of the global filter.
 */
sealed trait TableOptions[T, TM, CM, TF]:
  def columns: Reusable[List[ColumnDef[T, ?, TM, CM, TF, ?, ?]]]
  def data: Reusable[List[T]]
  private[table] def toJsBase: TableOptionsJs[T, TM, CM]

  private[table] def columnsJs: js.Array[ColumnDefJs[T, ?, CM]] =
    columns.toJSArray.map(_.toJs)

  private[table] def dataJs: js.Array[T] = data.toJSArray

  private[table] def toJs(
    cols: js.Array[ColumnDefJs[T, ?, CM]],
    rows: js.Array[T]
  ): TableOptionsJs[T, TM, CM] =
    toJsBase.columns = cols
    toJsBase.data = rows
    toJsBase

  private def copy(toJsMod: TableOptionsJs[T, TM, CM] => Unit): TableOptions[T, TM, CM, TF] =
    val self = this
    new TableOptions[T, TM, CM, TF]:
      def columns                 = self.columns
      def data                    = self.data
      private[table] def toJsBase = { toJsMod(self.toJsBase); self.toJsBase }

  lazy val getRowId: js.UndefOr[(T, Int, Option[T]) => RowId] =
    toJsBase.getRowId.map(fn =>
      (originalRow, index, parent) => RowId(fn(originalRow, index, parent.orUndefined))
    )

  /** WARNING: This mutates the object in-place. */
  def setGetRowId(getRowId: js.UndefOr[(T, Int, Option[T]) => RowId]): TableOptions[T, TM, CM, TF] =
    copy(_.getRowId =
      getRowId.map(fn =>
        (originalRow, index, parent) => fn(originalRow, index, parent.toOption).value
      )
    )

  lazy val onStateChange: js.UndefOr[Updater[TableState[TF]] => Callback] =
    toJsBase.onStateChange.map(fn =>
      u =>
        Callback(fn((u match
          case Updater.Set(v)   => Updater.Set(v.toJs)
          case Updater.Mod(mod) =>
            Updater.Mod((v: raw.buildLibTypesMod.TableState) => mod(TableState(v)).toJs)
        ).toJs))
    )

  /** WARNING: This mutates the object in-place. */
  def setOnStateChange(
    onStateChange: js.UndefOr[Updater[TableState[TF]] => Callback]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.onStateChange =
      onStateChange.map(fn =>
        u =>
          fn(
            Updater.fromJs(u) match
              case Updater.Set(v)   => Updater.Set(TableState(v))
              case Updater.Mod(mod) => Updater.Mod(v => TableState(mod(v.toJs)))
          ).runNow()
      )
    )

  lazy val getCoreRowModel: Table[T, TM, CM, TF] => () => RowModel[T, TM, CM, TF] =
    t =>
      (toJsBase
        .getCoreRowModel(t.toJs): (() => raw.buildLibTypesMod.RowModel[T])).map(RowModel(_))

  /** WARNING: This mutates the object in-place. */
  def setGetCoreRowModel(
    getCoreRowModel: js.UndefOr[Table[T, TM, CM, TF] => () => RowModel[T, TM, CM, TF]]
  ): TableOptions[T, TM, CM, TF] =
    copy:
      _.getCoreRowModel = getCoreRowModel
        .map: fn =>
          ((t: raw.buildLibTypesMod.Table[T]) => fn(Table(t)).map(_.toJs)): js.Function1[
            raw.buildLibTypesMod.Table[T],
            js.Function0[raw.buildLibTypesMod.RowModel[T]]
          ]
        .getOrElse(rawReact.mod.getCoreRowModel())

  lazy val renderFallbackValue: js.UndefOr[Any] = toJsBase.renderFallbackValue

  /** WARNING: This mutates the object in-place. */
  def setRenderFallbackValue(renderFallbackValue: js.UndefOr[Any]): TableOptions[T, TM, CM, TF] =
    copy(_.renderFallbackValue = renderFallbackValue)

  lazy val state: js.UndefOr[PartialTableState] =
    toJsBase.state.map(PartialTableState.apply)

  /** WARNING: This mutates the object in-place. */
  def setState(state: js.UndefOr[PartialTableState]): TableOptions[T, TM, CM, TF] =
    copy(_.state = state.map(_.toJs))

  lazy val initialState: js.UndefOr[TableState[TF]] =
    toJsBase.initialState.map(v => TableState(v.asInstanceOf[raw.buildLibTypesMod.TableState]))

  /** WARNING: This mutates the object in-place. */
  def setInitialState(initialState: js.UndefOr[TableState[TF]]): TableOptions[T, TM, CM, TF] =
    copy(_.initialState =
      initialState.map(_.toJs.asInstanceOf[raw.buildLibTypesMod.InitialTableState])
    )

  lazy val meta: js.UndefOr[TM] = toJsBase.meta

  /** WARNING: This mutates the object in-place. */
  def setMeta(meta: js.UndefOr[TM]): TableOptions[T, TM, CM, TF] =
    copy(_.meta = meta)

  // Column Sizing
  lazy val enableColumnResizing: js.UndefOr[Boolean] = toJsBase.enableColumnResizing

  /** WARNING: This mutates the object in-place. */
  def setEnableColumnResizing(
    enableColumnResizing: js.UndefOr[Boolean]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.enableColumnResizing = enableColumnResizing)

  lazy val columnResizeMode: js.UndefOr[ColumnResizeMode] =
    toJsBase.columnResizeMode.map(ColumnResizeMode.fromJs)

  /** WARNING: This mutates the object in-place. */
  def setColumnResizeMode(
    columnResizeMode: js.UndefOr[ColumnResizeMode]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.columnResizeMode = columnResizeMode.map(_.toJs))

  lazy val onColumnSizingChange: js.UndefOr[Updater[ColumnSizing] => Callback] =
    toJsBase.onColumnSizingChange.map(fn =>
      u =>
        Callback(fn((u match
          case Updater.Set(v)   => Updater.Set(v.toJs)
          case Updater.Mod(mod) =>
            Updater.Mod((v: raw.buildLibFeaturesColumnSizingMod.ColumnSizingState) =>
              mod(ColumnSizing.fromJs(v)).toJs
            )
        ).toJs))
    )

  /** WARNING: This mutates the object in-place. */
  def setOnColumnSizingChange(
    onColumnSizingChange: js.UndefOr[Updater[ColumnSizing] => Callback]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.onColumnSizingChange =
      onColumnSizingChange.map(fn =>
        u =>
          fn(
            Updater.fromJs(u) match
              case Updater.Set(v)   => Updater.Set(ColumnSizing.fromJs(v))
              case Updater.Mod(mod) => Updater.Mod(v => ColumnSizing.fromJs(mod(v.toJs)))
          ).runNow()
      )
    )

  lazy val onColumnSizingInfoChange: js.UndefOr[Updater[ColumnSizingInfo] => Callback] =
    toJsBase.onColumnSizingInfoChange.map(fn =>
      u =>
        Callback(fn((u match
          case Updater.Set(v)   => Updater.Set(v.toJs)
          case Updater.Mod(mod) =>
            Updater.Mod((v: raw.buildLibFeaturesColumnSizingMod.ColumnSizingInfoState) =>
              mod(ColumnSizingInfo.fromJs(v)).toJs
            )
        ).toJs))
    )

  /** WARNING: This mutates the object in-place. */
  def setOnColumnSizingInfoChange(
    onColumnSizingInfoChange: js.UndefOr[Updater[ColumnSizingInfo] => Callback]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.onColumnSizingInfoChange =
      onColumnSizingInfoChange.map(fn =>
        u =>
          fn(
            Updater.fromJs(u) match
              case Updater.Set(v)   => Updater.Set(ColumnSizingInfo.fromJs(v))
              case Updater.Mod(mod) => Updater.Mod(v => ColumnSizingInfo.fromJs(mod(v.toJs)))
          ).runNow()
      )
    )

  // Column Visibility
  lazy val enableHiding: js.UndefOr[Boolean] = toJsBase.enableHiding

  /** WARNING: This mutates the object in-place. */
  def setEnableHiding(enableHiding: js.UndefOr[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.enableHiding = enableHiding)

  // Column Visibility
  lazy val onColumnVisibilityChange: js.UndefOr[Updater[ColumnVisibility] => Callback] =
    toJsBase.onColumnVisibilityChange.map(fn =>
      u =>
        Callback(fn((u match
          case Updater.Set(v)   => Updater.Set(v.toJs)
          case Updater.Mod(mod) =>
            Updater.Mod((v: raw.buildLibFeaturesColumnVisibilityMod.VisibilityState) =>
              mod(ColumnVisibility.fromJs(v)).toJs
            )
        ).toJs))
    )

  /** WARNING: This mutates the object in-place. */
  def setOnColumnVisibilityChange(
    onColumnVisibilityChange: js.UndefOr[Updater[ColumnVisibility] => Callback]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.onColumnVisibilityChange =
      onColumnVisibilityChange.map(fn =>
        u =>
          fn(
            Updater.fromJs(u) match
              case Updater.Set(v)   => Updater.Set(ColumnVisibility.fromJs(v))
              case Updater.Mod(mod) => Updater.Mod(v => ColumnVisibility.fromJs(mod(v.toJs)))
          ).runNow()
      )
    )

  // Sorting
  lazy val enableSorting: Boolean = toJsBase.enableSorting.getOrElse(true)

  /** WARNING: This mutates the object in-place. */
  def setEnableSorting(enableSorting: js.UndefOr[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.enableSorting = enableSorting)

  lazy val enableMultiSort: js.UndefOr[Boolean] = toJsBase.enableMultiSort

  /** WARNING: This mutates the object in-place. */
  def setEnableMultiSort(enableMultiSort: js.UndefOr[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.enableMultiSort = enableMultiSort)

  lazy val enableSortingRemoval: js.UndefOr[Boolean] = toJsBase.enableSortingRemoval

  /** WARNING: This mutates the object in-place. */
  def setEnableSortingRemoval(
    enableSortingRemoval: js.UndefOr[Boolean]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.enableSortingRemoval = enableSortingRemoval)

  lazy val enableMultiRemove: js.UndefOr[Boolean] = toJsBase.enableMultiRemove

  /** WARNING: This mutates the object in-place. */
  def setEnableMultiRemove(enableMultiRemove: js.UndefOr[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.enableMultiRemove = enableMultiRemove)

  lazy val getSortedRowModel: js.UndefOr[Table[T, TM, CM, TF] => () => RowModel[T, TM, CM, TF]] =
    toJsBase.getSortedRowModel.map(fn =>
      t => (fn(t.toJs): (() => raw.buildLibTypesMod.RowModel[T])).map(RowModel(_))
    )

  /** WARNING: This mutates the object in-place. */
  def setGetSortedRowModel(
    getSortedRowModel: js.UndefOr[Table[T, TM, CM, TF] => () => RowModel[T, TM, CM, TF]]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.getSortedRowModel =
      getSortedRowModel
        .map(fn =>
          ((t: raw.buildLibTypesMod.Table[T]) => fn(Table(t)).map(_.toJs)): js.Function1[
            raw.buildLibTypesMod.Table[T],
            js.Function0[raw.buildLibTypesMod.RowModel[T]]
          ]
        )
        .getOrElse(rawReact.mod.getSortedRowModel())
    )

  lazy val isMultiSortEvent: js.UndefOr[SyntheticEvent[dom.Node] => Boolean] =
    toJsBase.isMultiSortEvent.map(identity)

  /** WARNING: This mutates the object in-place. */
  def setIsMultiSortEvent(
    isMultiSortEvent: js.UndefOr[SyntheticEvent[dom.Node] => Boolean]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.isMultiSortEvent =
      isMultiSortEvent.map(fn => e => fn(e.asInstanceOf[SyntheticEvent[dom.Node]]))
    )

  lazy val manualSorting: js.UndefOr[Boolean] = toJsBase.manualSorting

  /** WARNING: This mutates the object in-place. */
  def setManualSorting(manualSorting: js.UndefOr[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.manualSorting = manualSorting)

  lazy val maxMultiSortColCount: js.UndefOr[Int] =
    toJsBase.maxMultiSortColCount.map(_.toInt)

  /** WARNING: This mutates the object in-place. */
  def setMaxMultiSortColCount(maxMultiSortColCount: js.UndefOr[Int]): TableOptions[T, TM, CM, TF] =
    copy(_.maxMultiSortColCount = maxMultiSortColCount.map(_.toDouble))

  lazy val onSortingChange: js.UndefOr[Updater[Sorting] => Callback] =
    toJsBase.onSortingChange.map(fn =>
      u =>
        Callback(fn((u match
          case Updater.Set(v)   => Updater.Set(v.toJs)
          case Updater.Mod(mod) =>
            Updater.Mod((v: raw.buildLibFeaturesRowSortingMod.SortingState) =>
              mod(Sorting.fromJs(v)).toJs
            )
        ).toJs))
    )

  /** WARNING: This mutates the object in-place. */
  def setOnSortingChange(
    onSortingChange: js.UndefOr[Updater[Sorting] => Callback]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.onSortingChange =
      onSortingChange.map(fn =>
        u =>
          fn(
            Updater.fromJs(u) match
              case Updater.Set(v)   => Updater.Set(Sorting.fromJs(v))
              case Updater.Mod(mod) => Updater.Mod(v => Sorting.fromJs(mod(v.toJs)))
          ).runNow()
      )
    )

  lazy val sortDescFirst: js.UndefOr[Boolean] = toJsBase.sortDescFirst

  /** WARNING: This mutates the object in-place. */
  def setSortDescFirst(sortDescFirst: js.UndefOr[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.sortDescFirst = sortDescFirst)

  // Selection
  lazy val enableRowSelection: js.UndefOr[Boolean] = toJsBase.enableRowSelection

  /** WARNING: This mutates the object in-place. */
  def setEnableRowSelection(enableRowSelection: js.UndefOr[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.enableRowSelection = enableRowSelection)

  lazy val enableMultiRowSelection: js.UndefOr[Boolean] = toJsBase.enableRowSelection

  /** WARNING: This mutates the object in-place. */
  def setEnableMultiRowSelection(
    enableMultiRowSelection: js.UndefOr[Boolean]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.enableMultiRowSelection = enableMultiRowSelection)

  lazy val onRowSelectionChange: js.UndefOr[Updater[RowSelection] => Callback] =
    toJsBase.onRowSelectionChange.map(fn =>
      u =>
        Callback(fn((u match
          case Updater.Set(v)   => Updater.Set(v.toJs)
          case Updater.Mod(mod) =>
            Updater.Mod((v: raw.buildLibFeaturesRowSelectionMod.RowSelectionState) =>
              mod(RowSelection.fromJs(v)).toJs
            )
        ).toJs))
    )

  /** WARNING: This mutates the object in-place. */
  def setOnRowSelectionChange(
    onRowSelectionChange: js.UndefOr[Updater[RowSelection] => Callback]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.onRowSelectionChange =
      onRowSelectionChange.map(fn =>
        u =>
          fn(
            Updater.fromJs(u) match
              case Updater.Set(v)   => Updater.Set(RowSelection.fromJs(v))
              case Updater.Mod(mod) => Updater.Mod(v => RowSelection.fromJs(mod(v.toJs)))
          ).runNow()
      )
    )

  // Expanding
  lazy val enableExpanding: js.UndefOr[Boolean] = toJsBase.enableExpanding

  /** WARNING: This mutates the object in-place. */
  def setEnableExpanding(enableExpanding: js.UndefOr[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.enableExpanding = enableExpanding)

  lazy val getExpandedRowModel: js.UndefOr[Table[T, TM, CM, TF] => () => RowModel[T, TM, CM, TF]] =
    toJsBase.getExpandedRowModel.map(fn =>
      t => (fn(t.toJs): (() => raw.buildLibTypesMod.RowModel[T])).map(RowModel(_))
    )

  /** WARNING: This mutates the object in-place. */
  def setGetExpandedRowModel(
    getExpandedRowModel: js.UndefOr[Table[T, TM, CM, TF] => () => RowModel[T, TM, CM, TF]]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.getExpandedRowModel =
      getExpandedRowModel
        .map(fn =>
          ((t: raw.buildLibTypesMod.Table[T]) => fn(Table(t)).map(_.toJs)): js.Function1[
            raw.buildLibTypesMod.Table[T],
            js.Function0[raw.buildLibTypesMod.RowModel[T]]
          ]
        )
        .getOrElse(rawReact.mod.getExpandedRowModel())
    )

  lazy val getSubRows: js.UndefOr[(T, Int) => Option[List[T]]] =
    toJsBase.getSubRows.map(fn => (row, idx) => fn(row, idx).toOption.map(_.toList))

  /** WARNING: This mutates the object in-place. */
  def setGetSubRows(
    getSubRows: js.UndefOr[(T, Int) => Option[List[T]]]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.getSubRows =
      getSubRows.map(fn => (row, idx) => fn(row, idx).map(_.toJSArray).orUndefined)
    )

  // Pinning
  lazy val enablePinning: js.UndefOr[Boolean] = toJsBase.enablePinning

  /** WARNING: This mutates the object in-place. */
  def setEnablePinning(enablePinning: js.UndefOr[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.enablePinning = enablePinning)

  // Column Pinning
  lazy val enableColumnPinning: js.UndefOr[Boolean] = toJsBase.enableColumnPinning

  /** WARNING: This mutates the object in-place. */
  def setEnableColumnPinning(
    enableColumnPinning: js.UndefOr[Boolean]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.enableColumnPinning = enableColumnPinning)

  lazy val onColumnPinningChange: js.UndefOr[Updater[ColumnPinning] => Callback] =
    toJsBase.onColumnPinningChange.map: fn =>
      u =>
        Callback(fn((u match
          case Updater.Set(v)   => Updater.Set(v.toJs)
          case Updater.Mod(mod) =>
            Updater.Mod((v: raw.buildLibFeaturesColumnPinningMod.ColumnPinningState) =>
              mod(ColumnPinning.fromJs(v)).toJs
            )
        ).toJs))

  /** WARNING: This mutates the object in-place. */
  def setOnColumnPinningChange(
    onColumnPinningChange: js.UndefOr[Updater[ColumnPinning] => Callback]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.onColumnPinningChange = onColumnPinningChange.map: fn =>
      u =>
        fn(
          Updater.fromJs(u) match
            case Updater.Set(v)   => Updater.Set(ColumnPinning.fromJs(v))
            case Updater.Mod(mod) => Updater.Mod(v => ColumnPinning.fromJs(mod(v.toJs)))
        ).runNow())

  // Row Pinning
  lazy val enableRowPinning: js.UndefOr[RowPinningEnabled[T, TM, CM, TF]] =
    toJsBase.enableRowPinning.map(RowPinningEnabled.fromJs)

  /** WARNING: This mutates the object in-place. */
  def setEnableRowPinning(
    enableRowPinning: js.UndefOr[RowPinningEnabled[T, TM, CM, TF]]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.enableRowPinning = enableRowPinning.map(_.toJs))

  lazy val keepPinnedRows: js.UndefOr[Boolean] = toJsBase.keepPinnedRows

  /** WARNING: This mutates the object in-place. */
  def setKeepPinnedRows(keepPinnedRows: js.UndefOr[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.keepPinnedRows = keepPinnedRows)

  lazy val onRowPinningChange: js.UndefOr[Updater[RowPinning] => Callback] =
    toJsBase.onRowPinningChange.map: fn =>
      u =>
        Callback(fn((u match
          case Updater.Set(v)   => Updater.Set(v.toJs)
          case Updater.Mod(mod) =>
            Updater.Mod((v: raw.buildLibFeaturesRowPinningMod.RowPinningState) =>
              mod(RowPinning.fromJs(v)).toJs
            )
        ).toJs))

  /** WARNING: This mutates the object in-place. */
  def setOnRowPinningChange(
    onRowPinningChange: js.UndefOr[Updater[RowPinning] => Callback]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.onRowPinningChange = onRowPinningChange.map: fn =>
      u =>
        fn(
          Updater.fromJs(u) match
            case Updater.Set(v)   => Updater.Set(RowPinning.fromJs(v))
            case Updater.Mod(mod) => Updater.Mod(v => RowPinning.fromJs(mod(v.toJs)))
        ).runNow())

  // Column Filtering
  lazy val filterFromLeafRows: js.UndefOr[Boolean] = toJsBase.filterFromLeafRows

  /** WARNING: This mutates the object in-place. */
  def setFilterFromLeafRows(filterFromLeafRows: js.UndefOr[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.filterFromLeafRows = filterFromLeafRows)

  lazy val maxLeafRowFilterDepth: js.UndefOr[Double] = toJsBase.maxLeafRowFilterDepth

  /** WARNING: This mutates the object in-place. */
  def setMaxLeafRowFilterDepth(
    maxLeafRowFilterDepth: js.UndefOr[Double]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.maxLeafRowFilterDepth = maxLeafRowFilterDepth)

  lazy val enableFilters: js.UndefOr[Boolean] = toJsBase.enableFilters

  /** WARNING: This mutates the object in-place. */
  def setEnableFilters(enableFilters: js.UndefOr[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.enableFilters = enableFilters)

  lazy val manualFiltering: js.UndefOr[Boolean] = toJsBase.manualFiltering

  /** WARNING: This mutates the object in-place. */
  def setManualFiltering(manualFiltering: js.UndefOr[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.manualFiltering = manualFiltering)

  lazy val onColumnFiltersChange: js.UndefOr[Updater[ColumnFilters] => Callback] =
    toJsBase.onColumnFiltersChange.map: fn =>
      u =>
        Callback(fn((u match
          case Updater.Set(v)   => Updater.Set(v.toJs)
          case Updater.Mod(mod) =>
            Updater.Mod((v: raw.buildLibFeaturesColumnFilteringMod.ColumnFiltersState) =>
              mod(ColumnFilters.fromJs(v)).toJs
            )
        ).toJs))

  /** WARNING: This mutates the object in-place. */
  def setOnColumnFiltersChange(
    onColumnFiltersChange: js.UndefOr[Updater[ColumnFilters] => Callback]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.onColumnFiltersChange = onColumnFiltersChange.map: fn =>
      u =>
        fn(
          Updater.fromJs(u) match
            case Updater.Set(v)   => Updater.Set(ColumnFilters.fromJs(v))
            case Updater.Mod(mod) => Updater.Mod(v => ColumnFilters.fromJs(mod(v.toJs)))
        ).runNow())

  lazy val enableColumnFilters: js.UndefOr[Boolean] = toJsBase.enableColumnFilters

  /** WARNING: This mutates the object in-place. */
  def setEnableColumnFilters(
    enableColumnFilters: js.UndefOr[Boolean]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.enableColumnFilters = enableColumnFilters)

  lazy val getFilteredRowModel: js.UndefOr[Table[T, TM, CM, TF] => () => RowModel[T, TM, CM, TF]] =
    toJsBase.getFilteredRowModel.map(fn =>
      t => (fn(t.toJs): (() => raw.buildLibTypesMod.RowModel[T])).map(RowModel(_))
    )

  /** WARNING: This mutates the object in-place. */
  def setGetFilteredRowModel(
    getFilteredRowModel: js.UndefOr[Table[T, TM, CM, TF] => () => RowModel[T, TM, CM, TF]]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.getFilteredRowModel =
      getFilteredRowModel
        .map(fn =>
          ((t: raw.buildLibTypesMod.Table[T]) => fn(Table(t)).map(_.toJs)): js.Function1[
            raw.buildLibTypesMod.Table[T],
            js.Function0[raw.buildLibTypesMod.RowModel[T]]
          ]
        )
        .getOrElse(rawReact.mod.getFilteredRowModel())
    )

  // Global Filtering
  lazy val enableGlobalFilter: js.UndefOr[Boolean] = toJsBase.enableGlobalFilter

  /** WARNING: This mutates the object in-place. */
  def setEnableGlobalFilter(enableGlobalFilter: js.UndefOr[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.enableGlobalFilter = enableGlobalFilter)

  lazy val globalFilterFn: js.UndefOr[BuiltInFilter[TF] | FilterFn[T, TM, CM, TF, TF, Any]] =
    toJsBase.globalFilterFn
      .map: v =>
        js.typeOf(v) match
          case "string" =>
            BuiltInFilter.fromJs(v.asInstanceOf[String]).asInstanceOf[BuiltInFilter[TF]]
          case fn       =>
            FilterFn.fromJs(fn.asInstanceOf[raw.buildLibFeaturesColumnFilteringMod.FilterFn[T]])

  /** WARNING: This mutates the object in-place. */
  def setGlobalFilterFn[TF1](
    globalFilterFn: js.UndefOr[
      BuiltInFilter[TF1] | FilterFn[T, TM, CM, TF1, TF1, Any] |
        FilterFn.Type[T, TM, CM, TF1, TF1, Any]
    ]
  ): TableOptions[T, TM, CM, TF1] =
    copy(_.globalFilterFn =
      globalFilterFn
        .map: fn =>
          fn match
            case builtIn: BuiltInFilter[TF1] => builtIn.toJs
            case ff @ FilterFn(_, _, _)      => ff.asInstanceOf[FilterFn[T, TM, CM, TF1, TF1, Any]].toJs
            case fn                          =>
              FilterFn(fn.asInstanceOf[FilterFn.Type[T, TM, CM, TF1, TF1, Any]], none, none).toJs
    ).asInstanceOf[TableOptions[T, TM, CM, TF1]]

  lazy val onGlobalFilterChange: js.UndefOr[Updater[TF] => Callback] =
    toJsBase.onGlobalFilterChange.map: fn =>
      u =>
        Callback(fn((u match
          case Updater.Set(v)   =>
            Updater.Set(raw.buildLibFeaturesGlobalFilteringMod.GlobalFilterTableState(v))
          case Updater.Mod(mod) =>
            Updater.Mod((v: raw.buildLibFeaturesGlobalFilteringMod.GlobalFilterTableState) =>
              raw.buildLibFeaturesGlobalFilteringMod.GlobalFilterTableState(
                mod(v.globalFilter.asInstanceOf[TF])
              )
            )
        ).toJs))

  /** WARNING: This mutates the object in-place. */
  def setOnGlobalFilterChange(
    onGlobalFilterChange: js.UndefOr[Updater[TF] => Callback]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.onGlobalFilterChange = onGlobalFilterChange.map: fn =>
      u =>
        fn(
          Updater.fromJs(u) match
            case Updater.Set(v)   => Updater.Set(v.globalFilter.asInstanceOf[TF])
            case Updater.Mod(mod) =>
              Updater.Mod(v =>
                mod(raw.buildLibFeaturesGlobalFilteringMod.GlobalFilterTableState(v)).globalFilter
                  .asInstanceOf[TF]
              )
        ).runNow())

  lazy val getColumnCanGlobalFilter: js.UndefOr[Column[T, Any, TM, CM, TF, Any, Any] => Boolean] =
    toJsBase.getColumnCanGlobalFilter.map: fn =>
      col => fn(col.toJs)

  /** WARNING: This mutates the object in-place. */
  def setGetColumnCanGlobalFilter(
    getColumnCanGlobalFilter: js.UndefOr[Column[T, ?, TM, CM, TF, ?, ?] => Boolean]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.getColumnCanGlobalFilter = getColumnCanGlobalFilter.map: fn =>
      col => fn(Column(col)))

end TableOptions

object TableOptions:
  def apply[T, TM, CM, TF](
    columns_                : Reusable[List[ColumnDef[T, ?, TM, CM, TF, ?, ?]]],
    data_                   : Reusable[List[T]],
    getCoreRowModel:          js.UndefOr[Table[T, TM, CM, TF] => () => RowModel[T, TM, CM, TF]] =
      js.undefined,
    getRowId:                 js.UndefOr[(T, Int, Option[T]) => RowId] = js.undefined,
    onStateChange:            js.UndefOr[Updater[TableState[TF]] => Callback] = js.undefined,
    renderFallbackValue:      js.UndefOr[Any] = js.undefined,
    state:                    js.UndefOr[PartialTableState] = js.undefined,
    initialState:             js.UndefOr[TableState[TF]] = js.undefined,
    meta:                     js.UndefOr[TM] = js.undefined,
    // Column Sizing
    enableColumnResizing:     js.UndefOr[Boolean] = js.undefined,
    columnResizeMode:         js.UndefOr[ColumnResizeMode] = js.undefined,
    onColumnSizingChange:     js.UndefOr[Updater[ColumnSizing] => Callback] = js.undefined,
    onColumnSizingInfoChange: js.UndefOr[Updater[ColumnSizingInfo] => Callback] = js.undefined,
    // Column Visibility
    enableHiding:             js.UndefOr[Boolean] = js.undefined,
    onColumnVisibilityChange: js.UndefOr[Updater[ColumnVisibility] => Callback] = js.undefined,
    // Sorting
    enableSorting:            js.UndefOr[Boolean] = js.undefined,
    enableMultiSort:          js.UndefOr[Boolean] = js.undefined,
    enableSortingRemoval:     js.UndefOr[Boolean] = js.undefined,
    enableMultiRemove:        js.UndefOr[Boolean] = js.undefined,
    getSortedRowModel:        js.UndefOr[Table[T, TM, CM, TF] => () => RowModel[T, TM, CM, TF]] =
      js.undefined,
    isMultiSortEvent:         js.UndefOr[SyntheticEvent[dom.Node] => Boolean] = js.undefined,
    manualSorting:            js.UndefOr[Boolean] = js.undefined,
    maxMultiSortColCount:     js.UndefOr[Int] = js.undefined,
    onSortingChange:          js.UndefOr[Updater[Sorting] => Callback] = js.undefined,
    sortDescFirst:            js.UndefOr[Boolean] = js.undefined,
    // Selection
    enableRowSelection:       js.UndefOr[Boolean] = js.undefined,
    enableMultiRowSelection:  js.UndefOr[Boolean] = js.undefined,
    onRowSelectionChange:     js.UndefOr[Updater[RowSelection] => Callback] = js.undefined,
    // Expanding
    enableExpanding:          js.UndefOr[Boolean] = js.undefined,
    getExpandedRowModel:      js.UndefOr[Table[T, TM, CM, TF] => () => RowModel[T, TM, CM, TF]] =
      js.undefined,
    getSubRows:               js.UndefOr[(T, Int) => Option[List[T]]] = js.undefined,
    // Pinning
    enablePinning:            js.UndefOr[Boolean] = js.undefined,
    // Column Pinning
    enableColumnPinning:      js.UndefOr[Boolean] = js.undefined,
    onColumnPinningChange:    js.UndefOr[Updater[ColumnPinning] => Callback] = js.undefined,
    // Row Pinning
    enableRowPinning:         js.UndefOr[RowPinningEnabled[T, TM, CM, TF]] = js.undefined,
    keepPinnedRows:           js.UndefOr[Boolean] = js.undefined,
    onRowPinningChange:       js.UndefOr[Updater[RowPinning] => Callback] = js.undefined,
    // Column Filtering
    enableFilters:            js.UndefOr[Boolean] = js.undefined,
    enableColumnFilters:      js.UndefOr[Boolean] = js.undefined,
    filterFromLeafRows:       js.UndefOr[Boolean] = js.undefined,
    maxLeafRowFilterDepth:    js.UndefOr[Double] = js.undefined,
    manualFiltering:          js.UndefOr[Boolean] = js.undefined,
    onColumnFiltersChange:    js.UndefOr[Updater[ColumnFilters] => Callback] = js.undefined,
    getFilteredRowModel:      js.UndefOr[Table[T, TM, CM, TF] => () => RowModel[T, TM, CM, TF]] =
      js.undefined,
    // Global Filtering
    enableGlobalFilter:       js.UndefOr[Boolean] = js.undefined,
    globalFilterFn:           js.UndefOr[
      BuiltInFilter[TF] | FilterFn[T, TM, CM, TF, TF, Any] | FilterFn.Type[T, TM, CM, TF, TF, Any]
    ] = js.undefined,
    onGlobalFilterChange:     js.UndefOr[Updater[TF] => Callback] = js.undefined,
    getColumnCanGlobalFilter: js.UndefOr[Column[T, ?, TM, CM, TF, ?, ?] => Boolean] = js.undefined
  ): TableOptions[T, TM, CM, TF] =
    val autoEnableSorting: Boolean         = !enableSorting.contains(false) && (
      enableSorting.contains(true) ||
        enableMultiSort.contains(true) ||
        enableSortingRemoval.contains(true) ||
        enableMultiRemove.contains(true) ||
        getSortedRowModel.isDefined ||
        isMultiSortEvent.isDefined ||
        manualSorting.contains(true) ||
        maxMultiSortColCount.isDefined ||
        onSortingChange.isDefined ||
        sortDescFirst.contains(true)
    )
    val autoEnableExpanding: Boolean       = !enableExpanding.contains(false) && (
      enableExpanding.contains(true) ||
        getExpandedRowModel.isDefined ||
        getSubRows.isDefined
    )
    val autoEnableColumnFiltering: Boolean =
      !enableColumnFilters.contains(false) && (
        enableColumnFilters.contains(true) ||
          filterFromLeafRows.contains(true) ||
          maxLeafRowFilterDepth.isDefined ||
          manualFiltering.contains(true) ||
          onColumnFiltersChange.isDefined ||
          getFilteredRowModel.isDefined
      )
    val autoEnableGlobalFiltering: Boolean =
      !enableGlobalFilter.contains(false) && (
        enableGlobalFilter.contains(true) ||
          globalFilterFn.isDefined ||
          onGlobalFilterChange.isDefined
      )
    val autoEnableFiltering: Boolean       =
      !enableFilters.contains(false) && (enableFilters.contains(
        true
      ) || autoEnableColumnFiltering || autoEnableGlobalFiltering)

    new TableOptions[T, TM, CM, TF] {
      val columns                 = columns_.asInstanceOf[Reusable[List[ColumnDef[T, ?, TM, CM, TF, ?, ?]]]]
      val data                    = data_
      private[table] val toJsBase = new TableOptionsJs[T, TM, CM] {
        var columns         = null // Undefined on purpose, should not be accessible
        var data            = null // Undefined on purpose, should not be accessible
        var getCoreRowModel = null
      }
    }
      .setGetCoreRowModel(getCoreRowModel)
      .applyWhen(autoEnableSorting, _.setGetSortedRowModel(getSortedRowModel))
      .applyWhen(autoEnableExpanding, _.setGetExpandedRowModel(getExpandedRowModel))
      .applyWhen(autoEnableFiltering, _.setGetFilteredRowModel(getFilteredRowModel))
      .applyOrNot(getRowId, _.setGetRowId(_))
      .applyOrNot(onStateChange, _.setOnStateChange(_))
      .applyOrNot(renderFallbackValue, _.setRenderFallbackValue(_))
      .applyOrNot(state, _.setState(_))
      .applyOrNot(initialState, _.setInitialState(_))
      .applyOrNot(meta, _.setMeta(_))
      .applyOrNot(enableColumnResizing, _.setEnableColumnResizing(_))
      .applyOrNot(columnResizeMode, _.setColumnResizeMode(_))
      .applyOrNot(onColumnSizingChange, _.setOnColumnSizingChange(_))
      .applyOrNot(onColumnSizingInfoChange, _.setOnColumnSizingInfoChange(_))
      .applyOrNot(enableHiding, _.setEnableHiding(_))
      .applyOrNot(onColumnVisibilityChange, _.setOnColumnVisibilityChange(_))
      .applyOrNot(enableSorting, _.setEnableSorting(_))
      .applyOrNot(enableMultiSort, _.setEnableMultiSort(_))
      .applyOrNot(enableSortingRemoval, _.setEnableSortingRemoval(_))
      .applyOrNot(enableMultiRemove, _.setEnableMultiRemove(_))
      .applyOrNot(isMultiSortEvent, _.setIsMultiSortEvent(_))
      .applyOrNot(manualSorting, _.setManualSorting(_))
      .applyOrNot(maxMultiSortColCount, _.setMaxMultiSortColCount(_))
      .applyOrNot(onSortingChange, _.setOnSortingChange(_))
      .applyOrNot(sortDescFirst, _.setSortDescFirst(_))
      .applyOrNot(enableRowSelection, _.setEnableRowSelection(_))
      .applyOrNot(enableMultiRowSelection, _.setEnableMultiRowSelection(_))
      .applyOrNot(onRowSelectionChange, _.setOnRowSelectionChange(_))
      .applyOrNot(enableExpanding, _.setEnableExpanding(_))
      .applyOrNot(getSubRows, _.setGetSubRows(_))
      .applyOrNot(enablePinning, _.setEnablePinning(_))
      .applyOrNot(enableColumnPinning, _.setEnableColumnPinning(_))
      .applyOrNot(onColumnPinningChange, _.setOnColumnPinningChange(_))
      .applyOrNot(enableRowPinning, _.setEnableRowPinning(_))
      .applyOrNot(keepPinnedRows, _.setKeepPinnedRows(_))
      .applyOrNot(onRowPinningChange, _.setOnRowPinningChange(_))
      .applyOrNot(filterFromLeafRows, _.setFilterFromLeafRows(_))
      .applyOrNot(maxLeafRowFilterDepth, _.setMaxLeafRowFilterDepth(_))
      .applyOrNot(enableFilters, _.setEnableFilters(_))
      .applyOrNot(manualFiltering, _.setManualFiltering(_))
      .applyOrNot(onColumnFiltersChange, _.setOnColumnFiltersChange(_))
      .applyOrNot(enableColumnFilters, _.setEnableColumnFilters(_))
      .applyOrNot(enableGlobalFilter, _.setEnableGlobalFilter(_))
      .applyOrNot(globalFilterFn, _.setGlobalFilterFn(_))
      .applyOrNot(onGlobalFilterChange, _.setOnGlobalFilterChange(_))
      .applyOrNot(getColumnCanGlobalFilter, _.setGetColumnCanGlobalFilter(_))

  private[table] def fromJs[T, TM, CM, TF](
    raw: TableOptionsJs[T, TM, CM]
  ): TableOptions[T, TM, CM, TF] =
    new TableOptions[T, TM, CM, TF]:
      lazy val columns            = Reusable.always(raw.columns.toList.map(ColumnDef.fromJs))
      lazy val data               = Reusable.always(raw.data.toList)
      private[table] val toJsBase = raw
