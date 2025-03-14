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

  lazy val getRowId: Option[(T, Int, Option[T]) => RowId] =
    toJsBase.getRowId.toOption.map(fn =>
      (originalRow, index, parent) => RowId(fn(originalRow, index, parent.orUndefined))
    )

  /** WARNING: This mutates the object in-place. */
  def setGetRowId(getRowId: Option[(T, Int, Option[T]) => RowId]): TableOptions[T, TM, CM, TF] =
    copy(_.getRowId =
      getRowId.orUndefined.map(fn =>
        (originalRow, index, parent) => fn(originalRow, index, parent.toOption).value
      )
    )

  lazy val onStateChange: Option[Updater[TableState] => Callback] =
    toJsBase.onStateChange.toOption.map(fn =>
      u =>
        Callback(fn((u match
          case Updater.Set(v)   => Updater.Set(v.toJs)
          case Updater.Mod(mod) =>
            Updater.Mod((v: raw.buildLibTypesMod.TableState) => mod(TableState(v)).toJs)
        ).toJs))
    )

  /** WARNING: This mutates the object in-place. */
  def setOnStateChange(
    onStateChange: Option[Updater[TableState] => Callback]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.onStateChange =
      onStateChange.orUndefined.map(fn =>
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
    getCoreRowModel: Option[Table[T, TM, CM, TF] => () => RowModel[T, TM, CM, TF]]
  ): TableOptions[T, TM, CM, TF] =
    copy:
      _.getCoreRowModel = getCoreRowModel
        .map: fn =>
          ((t: raw.buildLibTypesMod.Table[T]) => fn(Table(t)).map(_.toJs)): js.Function1[
            raw.buildLibTypesMod.Table[T],
            js.Function0[raw.buildLibTypesMod.RowModel[T]]
          ]
        .getOrElse(rawReact.mod.getCoreRowModel())

  lazy val renderFallbackValue: Option[Any] = toJsBase.renderFallbackValue.toOption

  /** WARNING: This mutates the object in-place. */
  def setRenderFallbackValue(renderFallbackValue: Option[Any]): TableOptions[T, TM, CM, TF] =
    copy(_.renderFallbackValue = renderFallbackValue.orUndefined)

  lazy val state: Option[PartialTableState] = toJsBase.state.toOption.map(PartialTableState.apply)

  /** WARNING: This mutates the object in-place. */
  def setState(state: Option[PartialTableState]): TableOptions[T, TM, CM, TF] =
    copy(_.state = state.map(_.toJs).orUndefined)

  lazy val initialState: Option[TableState] =
    toJsBase.initialState.toOption.map(v =>
      TableState(v.asInstanceOf[raw.buildLibTypesMod.TableState])
    )

  /** WARNING: This mutates the object in-place. */
  def setInitialState(initialState: Option[TableState]): TableOptions[T, TM, CM, TF] =
    copy(_.initialState =
      initialState.map(_.toJs.asInstanceOf[raw.buildLibTypesMod.InitialTableState]).orUndefined
    )

  lazy val meta: Option[TM] = toJsBase.meta.toOption

  /** WARNING: This mutates the object in-place. */
  def setMeta(meta: Option[TM]): TableOptions[T, TM, CM, TF] =
    copy(_.meta = meta.orUndefined)

  // Column Sizing
  lazy val enableColumnResizing: Option[Boolean] = toJsBase.enableColumnResizing.toOption

  /** WARNING: This mutates the object in-place. */
  def setEnableColumnResizing(enableColumnResizing: Option[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.enableColumnResizing = enableColumnResizing.orUndefined)

  lazy val columnResizeMode: Option[ColumnResizeMode] =
    toJsBase.columnResizeMode.map(ColumnResizeMode.fromJs).toOption

  /** WARNING: This mutates the object in-place. */
  def setColumnResizeMode(columnResizeMode: Option[ColumnResizeMode]): TableOptions[T, TM, CM, TF] =
    copy(_.columnResizeMode = columnResizeMode.map(_.toJs).orUndefined)

  lazy val onColumnSizingChange: Option[Updater[ColumnSizing] => Callback] =
    toJsBase.onColumnSizingChange.toOption.map(fn =>
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
    onColumnSizingChange: Option[Updater[ColumnSizing] => Callback]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.onColumnSizingChange =
      onColumnSizingChange.orUndefined.map(fn =>
        u =>
          fn(
            Updater.fromJs(u) match
              case Updater.Set(v)   => Updater.Set(ColumnSizing.fromJs(v))
              case Updater.Mod(mod) => Updater.Mod(v => ColumnSizing.fromJs(mod(v.toJs)))
          ).runNow()
      )
    )

  lazy val onColumnSizingInfoChange: Option[Updater[ColumnSizingInfo] => Callback] =
    toJsBase.onColumnSizingInfoChange.toOption.map(fn =>
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
    onColumnSizingInfoChange: Option[Updater[ColumnSizingInfo] => Callback]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.onColumnSizingInfoChange =
      onColumnSizingInfoChange.orUndefined.map(fn =>
        u =>
          fn(
            Updater.fromJs(u) match
              case Updater.Set(v)   => Updater.Set(ColumnSizingInfo.fromJs(v))
              case Updater.Mod(mod) => Updater.Mod(v => ColumnSizingInfo.fromJs(mod(v.toJs)))
          ).runNow()
      )
    )

  // Column Visibility
  lazy val enableHiding: Option[Boolean] = toJsBase.enableHiding.toOption

  /** WARNING: This mutates the object in-place. */
  def setEnableHiding(enableHiding: Option[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.enableHiding = enableHiding.orUndefined)

  // Column Visibility
  lazy val onColumnVisibilityChange: Option[Updater[ColumnVisibility] => Callback] =
    toJsBase.onColumnVisibilityChange.toOption.map(fn =>
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
    onColumnVisibilityChange: Option[Updater[ColumnVisibility] => Callback]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.onColumnVisibilityChange =
      onColumnVisibilityChange.orUndefined.map(fn =>
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
  def setEnableSorting(enableSorting: Option[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.enableSorting = enableSorting.orUndefined)

  lazy val enableMultiSort: Option[Boolean] = toJsBase.enableMultiSort.toOption

  /** WARNING: This mutates the object in-place. */
  def setEnableMultiSort(enableMultiSort: Option[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.enableMultiSort = enableMultiSort.orUndefined)

  lazy val enableSortingRemoval: Option[Boolean] = toJsBase.enableSortingRemoval.toOption

  /** WARNING: This mutates the object in-place. */
  def setEnableSortingRemoval(enableSortingRemoval: Option[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.enableSortingRemoval = enableSortingRemoval.orUndefined)

  lazy val enableMultiRemove: Option[Boolean] = toJsBase.enableMultiRemove.toOption

  /** WARNING: This mutates the object in-place. */
  def setEnableMultiRemove(enableMultiRemove: Option[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.enableMultiRemove = enableMultiRemove.orUndefined)

  lazy val getSortedRowModel: Option[Table[T, TM, CM, TF] => () => RowModel[T, TM, CM, TF]] =
    toJsBase.getSortedRowModel.toOption.map(fn =>
      t => (fn(t.toJs): (() => raw.buildLibTypesMod.RowModel[T])).map(RowModel(_))
    )

  /** WARNING: This mutates the object in-place. */
  def setGetSortedRowModel(
    getSortedRowModel: Option[Table[T, TM, CM, TF] => () => RowModel[T, TM, CM, TF]]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.getSortedRowModel =
      getSortedRowModel.orUndefined
        .map(fn =>
          ((t: raw.buildLibTypesMod.Table[T]) => fn(Table(t)).map(_.toJs)): js.Function1[
            raw.buildLibTypesMod.Table[T],
            js.Function0[raw.buildLibTypesMod.RowModel[T]]
          ]
        )
        .getOrElse(rawReact.mod.getSortedRowModel())
    )

  lazy val isMultiSortEvent: Option[SyntheticEvent[dom.Node] => Boolean] =
    toJsBase.isMultiSortEvent.toOption.map(identity)

  /** WARNING: This mutates the object in-place. */
  def setIsMultiSortEvent(
    isMultiSortEvent: Option[SyntheticEvent[dom.Node] => Boolean]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.isMultiSortEvent =
      isMultiSortEvent.orUndefined.map(fn => e => fn(e.asInstanceOf[SyntheticEvent[dom.Node]]))
    )

  lazy val manualSorting: Option[Boolean] = toJsBase.manualSorting.toOption

  /** WARNING: This mutates the object in-place. */
  def setManualSorting(manualSorting: Option[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.manualSorting = manualSorting.orUndefined)

  lazy val maxMultiSortColCount: Option[Int] = toJsBase.maxMultiSortColCount.toOption.map(_.toInt)

  /** WARNING: This mutates the object in-place. */
  def setMaxMultiSortColCount(maxMultiSortColCount: Option[Int]): TableOptions[T, TM, CM, TF] =
    copy(_.maxMultiSortColCount = maxMultiSortColCount.orUndefined.map(_.toDouble))

  lazy val onSortingChange: Option[Updater[Sorting] => Callback] =
    toJsBase.onSortingChange.toOption.map(fn =>
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
    onSortingChange: Option[Updater[Sorting] => Callback]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.onSortingChange =
      onSortingChange.orUndefined.map(fn =>
        u =>
          fn(
            Updater.fromJs(u) match
              case Updater.Set(v)   => Updater.Set(Sorting.fromJs(v))
              case Updater.Mod(mod) => Updater.Mod(v => Sorting.fromJs(mod(v.toJs)))
          ).runNow()
      )
    )

  lazy val sortDescFirst: Option[Boolean] = toJsBase.sortDescFirst.toOption

  /** WARNING: This mutates the object in-place. */
  def setSortDescFirst(sortDescFirst: Option[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.sortDescFirst = sortDescFirst.orUndefined)

  // Selection
  lazy val enableRowSelection: Option[Boolean] = toJsBase.enableRowSelection.toOption

  /** WARNING: This mutates the object in-place. */
  def setEnableRowSelection(enableRowSelection: Option[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.enableRowSelection = enableRowSelection.orUndefined)

  lazy val enableMultiRowSelection: Option[Boolean] = toJsBase.enableRowSelection.toOption

  /** WARNING: This mutates the object in-place. */
  def setEnableMultiRowSelection(
    enableMultiRowSelection: Option[Boolean]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.enableMultiRowSelection = enableMultiRowSelection.orUndefined)

  lazy val onRowSelectionChange: Option[Updater[RowSelection] => Callback] =
    toJsBase.onRowSelectionChange.toOption.map(fn =>
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
    onRowSelectionChange: Option[Updater[RowSelection] => Callback]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.onRowSelectionChange =
      onRowSelectionChange.orUndefined.map(fn =>
        u =>
          fn(
            Updater.fromJs(u) match
              case Updater.Set(v)   => Updater.Set(RowSelection.fromJs(v))
              case Updater.Mod(mod) => Updater.Mod(v => RowSelection.fromJs(mod(v.toJs)))
          ).runNow()
      )
    )

  // Expanding
  lazy val enableExpanding: Option[Boolean] = toJsBase.enableExpanding.toOption

  /** WARNING: This mutates the object in-place. */
  def setEnableExpanding(enableExpanding: Option[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.enableExpanding = enableExpanding.orUndefined)

  lazy val getExpandedRowModel: Option[Table[T, TM, CM, TF] => () => RowModel[T, TM, CM, TF]] =
    toJsBase.getExpandedRowModel.toOption.map(fn =>
      t => (fn(t.toJs): (() => raw.buildLibTypesMod.RowModel[T])).map(RowModel(_))
    )

  /** WARNING: This mutates the object in-place. */
  def setGetExpandedRowModel(
    getExpandedRowModel: Option[Table[T, TM, CM, TF] => () => RowModel[T, TM, CM, TF]]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.getExpandedRowModel =
      getExpandedRowModel.orUndefined
        .map(fn =>
          ((t: raw.buildLibTypesMod.Table[T]) => fn(Table(t)).map(_.toJs)): js.Function1[
            raw.buildLibTypesMod.Table[T],
            js.Function0[raw.buildLibTypesMod.RowModel[T]]
          ]
        )
        .getOrElse(rawReact.mod.getExpandedRowModel())
    )

  lazy val getSubRows: Option[(T, Int) => Option[List[T]]] =
    toJsBase.getSubRows.toOption.map(fn => (row, idx) => fn(row, idx).toOption.map(_.toList))

  /** WARNING: This mutates the object in-place. */
  def setGetSubRows(getSubRows: Option[(T, Int) => Option[List[T]]]): TableOptions[T, TM, CM, TF] =
    copy(_.getSubRows =
      getSubRows.orUndefined.map(fn => (row, idx) => fn(row, idx).map(_.toJSArray).orUndefined)
    )

  // Pinning
  lazy val enablePinning: Option[Boolean] = toJsBase.enablePinning.toOption

  /** WARNING: This mutates the object in-place. */
  def setEnablePinning(enablePinning: Option[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.enablePinning = enablePinning.orUndefined)

  // Column Pinning
  lazy val enableColumnPinning: Option[Boolean] = toJsBase.enableColumnPinning.toOption

  /** WARNING: This mutates the object in-place. */
  def setEnableColumnPinning(enableColumnPinning: Option[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.enableColumnPinning = enableColumnPinning.orUndefined)

  lazy val onColumnPinningChange: Option[Updater[ColumnPinning] => Callback] =
    toJsBase.onColumnPinningChange.toOption.map: fn =>
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
    onColumnPinningChange: Option[Updater[ColumnPinning] => Callback]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.onColumnPinningChange = onColumnPinningChange.orUndefined.map: fn =>
      u =>
        fn(
          Updater.fromJs(u) match
            case Updater.Set(v)   => Updater.Set(ColumnPinning.fromJs(v))
            case Updater.Mod(mod) => Updater.Mod(v => ColumnPinning.fromJs(mod(v.toJs)))
        ).runNow())

  // Row Pinning
  lazy val enableRowPinning: Option[RowPinningEnabled[T, TM, CM, TF]] =
    toJsBase.enableRowPinning.toOption.map(RowPinningEnabled.fromJs)

  /** WARNING: This mutates the object in-place. */
  def setEnableRowPinning(
    enableRowPinning: Option[RowPinningEnabled[T, TM, CM, TF]]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.enableRowPinning = enableRowPinning.map(_.toJs).orUndefined)

  lazy val keepPinnedRows: Option[Boolean] = toJsBase.keepPinnedRows.toOption

  /** WARNING: This mutates the object in-place. */
  def setKeepPinnedRows(keepPinnedRows: Option[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.keepPinnedRows = keepPinnedRows.orUndefined)

  lazy val onRowPinningChange: Option[Updater[RowPinning] => Callback] =
    toJsBase.onRowPinningChange.toOption.map: fn =>
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
    onRowPinningChange: Option[Updater[RowPinning] => Callback]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.onRowPinningChange = onRowPinningChange.orUndefined.map: fn =>
      u =>
        fn(
          Updater.fromJs(u) match
            case Updater.Set(v)   => Updater.Set(RowPinning.fromJs(v))
            case Updater.Mod(mod) => Updater.Mod(v => RowPinning.fromJs(mod(v.toJs)))
        ).runNow())

  // Column Filtering
  lazy val filterFromLeafRows: Option[Boolean] = toJsBase.filterFromLeafRows.toOption

  /** WARNING: This mutates the object in-place. */
  def setFilterFromLeafRows(filterFromLeafRows: Option[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.filterFromLeafRows = filterFromLeafRows.orUndefined)

  lazy val maxLeafRowFilterDepth: Option[Double] = toJsBase.maxLeafRowFilterDepth.toOption

  /** WARNING: This mutates the object in-place. */
  def setMaxLeafRowFilterDepth(maxLeafRowFilterDepth: Option[Double]): TableOptions[T, TM, CM, TF] =
    copy(_.maxLeafRowFilterDepth = maxLeafRowFilterDepth.orUndefined)

  lazy val enableFilters: Option[Boolean] = toJsBase.enableFilters.toOption

  /** WARNING: This mutates the object in-place. */
  def setEnableFilters(enableFilters: Option[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.enableFilters = enableFilters.orUndefined)

  lazy val manualFiltering: Option[Boolean] = toJsBase.manualFiltering.toOption

  /** WARNING: This mutates the object in-place. */
  def setManualFiltering(manualFiltering: Option[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.manualFiltering = manualFiltering.orUndefined)

  lazy val onColumnFiltersChange: Option[Updater[ColumnFilters] => Callback] =
    toJsBase.onColumnFiltersChange.toOption.map: fn =>
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
    onColumnFiltersChange: Option[Updater[ColumnFilters] => Callback]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.onColumnFiltersChange = onColumnFiltersChange.orUndefined.map: fn =>
      u =>
        fn(
          Updater.fromJs(u) match
            case Updater.Set(v)   => Updater.Set(ColumnFilters.fromJs(v))
            case Updater.Mod(mod) => Updater.Mod(v => ColumnFilters.fromJs(mod(v.toJs)))
        ).runNow())

  lazy val enableColumnFilters: Option[Boolean] = toJsBase.enableColumnFilters.toOption

  /** WARNING: This mutates the object in-place. */
  def setEnableColumnFilters(enableColumnFilters: Option[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.enableColumnFilters = enableColumnFilters.orUndefined)

  lazy val getFilteredRowModel: Option[Table[T, TM, CM, TF] => () => RowModel[T, TM, CM, TF]] =
    toJsBase.getFilteredRowModel.toOption.map(fn =>
      t => (fn(t.toJs): (() => raw.buildLibTypesMod.RowModel[T])).map(RowModel(_))
    )

  /** WARNING: This mutates the object in-place. */
  def setGetFilteredRowModel(
    getFilteredRowModel: Option[Table[T, TM, CM, TF] => () => RowModel[T, TM, CM, TF]]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.getFilteredRowModel =
      getFilteredRowModel.orUndefined
        .map(fn =>
          ((t: raw.buildLibTypesMod.Table[T]) => fn(Table(t)).map(_.toJs)): js.Function1[
            raw.buildLibTypesMod.Table[T],
            js.Function0[raw.buildLibTypesMod.RowModel[T]]
          ]
        )
        .getOrElse(rawReact.mod.getFilteredRowModel())
    )

  // Global Filtering
  lazy val enableGlobalFilter: Option[Boolean] = toJsBase.enableGlobalFilter.toOption

  /** WARNING: This mutates the object in-place. */
  def setEnableGlobalFilter(enableGlobalFilter: Option[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.enableGlobalFilter = enableGlobalFilter.orUndefined)

  lazy val globalFilterFn: Option[BuiltInFilter[TF] | FilterFn[T, TM, CM, TF, TF, Any]] =
    toJsBase.globalFilterFn
      .map: v =>
        js.typeOf(v) match
          case "string" =>
            BuiltInFilter.fromJs(v.asInstanceOf[String]).asInstanceOf[BuiltInFilter[TF]]
          case fn       =>
            FilterFn.fromJs(fn.asInstanceOf[raw.buildLibFeaturesColumnFilteringMod.FilterFn[T]])
      .toOption

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

  lazy val onGlobalFilterChange: Option[Updater[TF] => Callback] =
    toJsBase.onGlobalFilterChange.toOption.map: fn =>
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
    onGlobalFilterChange: Option[Updater[TF] => Callback]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.onGlobalFilterChange = onGlobalFilterChange.orUndefined.map: fn =>
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

  lazy val getColumnCanGlobalFilter: Option[Column[T, Any, TM, CM, TF, Any, Any] => Boolean] =
    toJsBase.getColumnCanGlobalFilter.toOption.map: fn =>
      col => fn(col.toJs)

  /** WARNING: This mutates the object in-place. */
  def setGetColumnCanGlobalFilter(
    getColumnCanGlobalFilter: Option[Column[T, ?, TM, CM, TF, ?, ?] => Boolean]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.getColumnCanGlobalFilter = getColumnCanGlobalFilter.orUndefined.map: fn =>
      col => fn(Column(col)))

end TableOptions

object TableOptions:
  def apply[T, TM, CM, TF](
    columns_                : Reusable[List[ColumnDef[T, ?, ?, CM, ?, ?, ?]]],
    data_                   : Reusable[List[T]],
    getCoreRowModel:          js.UndefOr[Table[T, TM, CM, TF] => () => RowModel[T, TM, CM, TF]] =
      js.undefined,
    getRowId:                 js.UndefOr[(T, Int, Option[T]) => RowId] = js.undefined,
    onStateChange:            js.UndefOr[Updater[TableState] => Callback] = js.undefined,
    renderFallbackValue:      js.UndefOr[Any] = js.undefined,
    state:                    js.UndefOr[PartialTableState] = js.undefined,
    initialState:             js.UndefOr[TableState] = js.undefined,
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
    onColumnFiltersChange:    js.UndefOr[Option[Updater[ColumnFilters] => Callback]] = js.undefined,
    getFilteredRowModel:      js.UndefOr[Table[T, TM, CM, TF] => () => RowModel[T, TM, CM, TF]] =
      js.undefined,
    // Global Filtering
    enableGlobalFilter:       js.UndefOr[Boolean] = js.undefined,
    globalFilterFn:           js.UndefOr[
      BuiltInFilter[TF] | FilterFn[T, TM, CM, TF, TF, Any] | FilterFn.Type[T, TM, CM, TF, TF, Any]
    ] = js.undefined,
    onGlobalFilterChange:     js.UndefOr[Option[Updater[TF] => Callback]] = js.undefined,
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
      .setGetCoreRowModel(getCoreRowModel.toOption)
      .applyWhen(autoEnableSorting, _.setGetSortedRowModel(getSortedRowModel.toOption))
      .applyWhen(autoEnableExpanding, _.setGetExpandedRowModel(getExpandedRowModel.toOption))
      .applyWhen(autoEnableFiltering, _.setGetFilteredRowModel(getFilteredRowModel.toOption))
      .applyOrNot(getRowId, (p, v) => p.setGetRowId(v.some))
      .applyOrNot(onStateChange, (p, v) => p.setOnStateChange(v.some))
      .applyOrNot(renderFallbackValue, (p, v) => p.setRenderFallbackValue(v.some))
      .applyOrNot(state, (p, v) => p.setState(v.some))
      .applyOrNot(initialState, (p, v) => p.setInitialState(v.some))
      .applyOrNot(meta, (p, v) => p.setMeta(v.some))
      .applyOrNot(enableColumnResizing, (p, v) => p.setEnableColumnResizing(v.some))
      .applyOrNot(columnResizeMode, (p, v) => p.setColumnResizeMode(v.some))
      .applyOrNot(onColumnSizingChange, (p, v) => p.setOnColumnSizingChange(v.some))
      .applyOrNot(onColumnSizingInfoChange, (p, v) => p.setOnColumnSizingInfoChange(v.some))
      .applyOrNot(enableHiding, (p, v) => p.setEnableHiding(v.some))
      .applyOrNot(onColumnVisibilityChange, (p, v) => p.setOnColumnVisibilityChange(v.some))
      .applyOrNot(enableSorting, (p, v) => p.setEnableSorting(v.some))
      .applyOrNot(enableMultiSort, (p, v) => p.setEnableMultiSort(v.some))
      .applyOrNot(enableSortingRemoval, (p, v) => p.setEnableSortingRemoval(v.some))
      .applyOrNot(enableMultiRemove, (p, v) => p.setEnableMultiRemove(v.some))
      .applyOrNot(isMultiSortEvent, (p, v) => p.setIsMultiSortEvent(v.some))
      .applyOrNot(manualSorting, (p, v) => p.setManualSorting(v.some))
      .applyOrNot(maxMultiSortColCount, (p, v) => p.setMaxMultiSortColCount(v.some))
      .applyOrNot(onSortingChange, (p, v) => p.setOnSortingChange(v.some))
      .applyOrNot(sortDescFirst, (p, v) => p.setSortDescFirst(v.some))
      .applyOrNot(enableRowSelection, (p, v) => p.setEnableRowSelection(v.some))
      .applyOrNot(enableMultiRowSelection, (p, v) => p.setEnableMultiRowSelection(v.some))
      .applyOrNot(onRowSelectionChange, (p, v) => p.setOnRowSelectionChange(v.some))
      .applyOrNot(enableExpanding, (p, v) => p.setEnableExpanding(v.some))
      .applyOrNot(getSubRows, (p, v) => p.setGetSubRows(v.some))
      .applyOrNot(enablePinning, (p, v) => p.setEnablePinning(v.some))
      .applyOrNot(enableColumnPinning, (p, v) => p.setEnableColumnPinning(v.some))
      .applyOrNot(onColumnPinningChange, (p, v) => p.setOnColumnPinningChange(v.some))
      .applyOrNot(enableRowPinning, (p, v) => p.setEnableRowPinning(v.some))
      .applyOrNot(keepPinnedRows, (p, v) => p.setKeepPinnedRows(v.some))
      .applyOrNot(onRowPinningChange, (p, v) => p.setOnRowPinningChange(v.some))
      .applyOrNot(filterFromLeafRows, (p, v) => p.setFilterFromLeafRows(v.some))
      .applyOrNot(maxLeafRowFilterDepth, (p, v) => p.setMaxLeafRowFilterDepth(v.some))
      .applyOrNot(enableFilters, (p, v) => p.setEnableFilters(v.some))
      .applyOrNot(manualFiltering, (p, v) => p.setManualFiltering(v.some))
      .applyOrNot(onColumnFiltersChange, (p, v) => p.setOnColumnFiltersChange(v))
      .applyOrNot(enableColumnFilters, (p, v) => p.setEnableColumnFilters(v.some))
      .applyOrNot(enableGlobalFilter, (p, v) => p.setEnableGlobalFilter(v.some))
      .applyOrNot(globalFilterFn, _.setGlobalFilterFn(_))
      .applyOrNot(onGlobalFilterChange, (p, v) => p.setOnGlobalFilterChange(v))
      .applyOrNot(getColumnCanGlobalFilter, (p, v) => p.setGetColumnCanGlobalFilter(v.some))

  private[table] def fromJs[T, TM, CM, TF](
    raw: TableOptionsJs[T, TM, CM]
  ): TableOptions[T, TM, CM, TF] =
    new TableOptions[T, TM, CM, TF]:
      lazy val columns            = Reusable.always(raw.columns.toList.map(ColumnDef.fromJs))
      lazy val data               = Reusable.always(raw.data.toList)
      private[table] val toJsBase = raw
