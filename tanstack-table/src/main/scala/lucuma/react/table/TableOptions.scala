// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import cats.syntax.all.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.facade.SyntheticEvent
import lucuma.react.table.facade.ColumnDefJs
import lucuma.react.table.facade.TableOptionsJs
import lucuma.typed.std.Map as JsMap
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

  /** WARNING: This mutates the object in-place. */
  inline def withGetRowId(getRowId: (T, Int, Option[T]) => RowId): TableOptions[T, TM, CM, TF] =
    setGetRowId(getRowId.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutGetRowId: TableOptions[T, TM, CM, TF] =
    setGetRowId(none)

  lazy val onStateChange: Option[Updater[TableState[TF]] => Callback] =
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
    onStateChange: Option[Updater[TableState[TF]] => Callback]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.onStateChange =
      onStateChange.orUndefined
        .map(fn =>
          u =>
            fn(
              Updater.fromJs(u) match
                case Updater.Set(v)   => Updater.Set(TableState(v))
                case Updater.Mod(mod) => Updater.Mod(v => TableState(mod(v.toJs)))
            ).runNow()
        )
    )

  /** WARNING: This mutates the object in-place. */
  inline def withOnStateChange(
    onStateChange: Updater[TableState[TF]] => Callback
  ): TableOptions[T, TM, CM, TF] =
    setOnStateChange(onStateChange.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutOnStateChange: TableOptions[T, TM, CM, TF] =
    setOnStateChange(none)

  lazy val getCoreRowModel: Table[T, TM, CM, TF] => () => RowModel[T, TM, CM, TF] =
    t => () => RowModel(toJsBase.getCoreRowModel(t.toJs)())

  /** WARNING: This mutates the object in-place. */
  def withGetCoreRowModel(
    getCoreRowModel: Table[T, TM, CM, TF] => () => RowModel[T, TM, CM, TF]
  ): TableOptions[T, TM, CM, TF] =
    copy:
      _.getCoreRowModel = (t: raw.buildLibTypesMod.Table[T]) =>
        getCoreRowModel(Table(t)).map(_.toJs): js.Function0[raw.buildLibTypesMod.RowModel[T]]

  def withDefaultGetCoreRowModel: TableOptions[T, TM, CM, TF] =
    copy(_.getCoreRowModel = rawReact.mod.getCoreRowModel())

  lazy val renderFallbackValue: Option[Any] = toJsBase.renderFallbackValue.toOption

  /** WARNING: This mutates the object in-place. */
  def setRenderFallbackValue(renderFallbackValue: Option[Any]): TableOptions[T, TM, CM, TF] =
    copy(_.renderFallbackValue = renderFallbackValue.orUndefined)

  /** WARNING: This mutates the object in-place. */
  inline def withRenderFallbackValue(renderFallbackValue: Any): TableOptions[T, TM, CM, TF] =
    setRenderFallbackValue(renderFallbackValue.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutRenderFallbackValue: TableOptions[T, TM, CM, TF] =
    setRenderFallbackValue(none)

  lazy val state: Option[PartialTableState] =
    toJsBase.state.toOption.map(PartialTableState.apply)

  /** WARNING: This mutates the object in-place. */
  def setState(state: Option[PartialTableState]): TableOptions[T, TM, CM, TF] =
    copy(_.state = state.map(_.toJs).orUndefined)

  /** WARNING: This mutates the object in-place. */
  inline def withState(state: PartialTableState): TableOptions[T, TM, CM, TF] =
    setState(state.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutState: TableOptions[T, TM, CM, TF] =
    setState(none)

  lazy val initialState: Option[TableState[TF]] =
    toJsBase.initialState.toOption.map(v =>
      TableState(v.asInstanceOf[raw.buildLibTypesMod.TableState])
    )

  /** WARNING: This mutates the object in-place. */
  def setInitialState(initialState: Option[TableState[TF]]): TableOptions[T, TM, CM, TF] =
    copy(_.initialState =
      initialState.map(_.toJs.asInstanceOf[raw.buildLibTypesMod.InitialTableState]).orUndefined
    )

  /** WARNING: This mutates the object in-place. */
  inline def withInitialState(initialState: TableState[TF]): TableOptions[T, TM, CM, TF] =
    setInitialState(initialState.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutInitialState: TableOptions[T, TM, CM, TF] =
    setInitialState(none)

  lazy val meta: Option[TM] = toJsBase.meta.toOption

  /** WARNING: This mutates the object in-place. */
  def setMeta[TM1](meta: Option[TM1]): TableOptions[T, TM1, CM, TF] =
    this.asInstanceOf[TableOptions[T, TM1, CM, TF]].copy(_.meta = meta.orUndefined)

  /** WARNING: This mutates the object in-place. */
  inline def withMeta[TM1](meta: TM1): TableOptions[T, TM1, CM, TF] =
    setMeta(meta.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutMeta: TableOptions[T, Nothing, CM, TF] =
    setMeta(none)

  // Column Sizing
  lazy val enableColumnResizing: Option[Boolean] = toJsBase.enableColumnResizing.toOption

  /** WARNING: This mutates the object in-place. */
  def setEnableColumnResizing(
    enableColumnResizing: Option[Boolean]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.enableColumnResizing = enableColumnResizing.orUndefined)

  /** WARNING: This mutates the object in-place. */
  inline def withEnableColumnResizing(enableColumnResizing: Boolean): TableOptions[T, TM, CM, TF] =
    setEnableColumnResizing(enableColumnResizing.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutEnableColumnResizing: TableOptions[T, TM, CM, TF] =
    setEnableColumnResizing(none)

  lazy val columnResizeMode: Option[ColumnResizeMode] =
    toJsBase.columnResizeMode.toOption.map(ColumnResizeMode.fromJs)

  /** WARNING: This mutates the object in-place. */
  def setColumnResizeMode(
    columnResizeMode: Option[ColumnResizeMode]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.columnResizeMode = columnResizeMode.map(_.toJs).orUndefined)

  /** WARNING: This mutates the object in-place. */
  inline def withColumnResizeMode(columnResizeMode: ColumnResizeMode): TableOptions[T, TM, CM, TF] =
    setColumnResizeMode(columnResizeMode.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutColumnResizeMode: TableOptions[T, TM, CM, TF] =
    setColumnResizeMode(none)

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
      onColumnSizingChange.orUndefined
        .map(fn =>
          u =>
            fn(
              Updater.fromJs(u) match
                case Updater.Set(v)   => Updater.Set(ColumnSizing.fromJs(v))
                case Updater.Mod(mod) => Updater.Mod(v => ColumnSizing.fromJs(mod(v.toJs)))
            ).runNow()
        )
    )

  /** WARNING: This mutates the object in-place. */
  inline def withOnColumnSizingChange(
    onColumnSizingChange: Updater[ColumnSizing] => Callback
  ): TableOptions[T, TM, CM, TF] =
    setOnColumnSizingChange(onColumnSizingChange.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutOnColumnSizingChange: TableOptions[T, TM, CM, TF] =
    setOnColumnSizingChange(none)

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
      onColumnSizingInfoChange.orUndefined
        .map(fn =>
          u =>
            fn(
              Updater.fromJs(u) match
                case Updater.Set(v)   => Updater.Set(ColumnSizingInfo.fromJs(v))
                case Updater.Mod(mod) => Updater.Mod(v => ColumnSizingInfo.fromJs(mod(v.toJs)))
            ).runNow()
        )
    )

  /** WARNING: This mutates the object in-place. */
  inline def withOnColumnSizingInfoChange(
    onColumnSizingInfoChange: Updater[ColumnSizingInfo] => Callback
  ): TableOptions[T, TM, CM, TF] =
    setOnColumnSizingInfoChange(onColumnSizingInfoChange.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutOnColumnSizingInfoChange: TableOptions[T, TM, CM, TF] =
    setOnColumnSizingInfoChange(none)

  // Column Visibility
  lazy val enableHiding: Option[Boolean] = toJsBase.enableHiding.toOption

  /** WARNING: This mutates the object in-place. */
  def setEnableHiding(enableHiding: Option[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.enableHiding = enableHiding.orUndefined)

  /** WARNING: This mutates the object in-place. */
  inline def withEnableHiding(enableHiding: Boolean): TableOptions[T, TM, CM, TF] =
    setEnableHiding(enableHiding.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutEnableHiding: TableOptions[T, TM, CM, TF] =
    setEnableHiding(none)

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
      onColumnVisibilityChange.orUndefined
        .map(fn =>
          u =>
            fn(
              Updater.fromJs(u) match
                case Updater.Set(v)   => Updater.Set(ColumnVisibility.fromJs(v))
                case Updater.Mod(mod) => Updater.Mod(v => ColumnVisibility.fromJs(mod(v.toJs)))
            ).runNow()
        )
    )

  /** WARNING: This mutates the object in-place. */
  inline def withOnColumnVisibilityChange(
    onColumnVisibilityChange: Updater[ColumnVisibility] => Callback
  ): TableOptions[T, TM, CM, TF] =
    setOnColumnVisibilityChange(onColumnVisibilityChange.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutOnColumnVisibilityChange: TableOptions[T, TM, CM, TF] =
    setOnColumnVisibilityChange(none)

  // Sorting
  lazy val enableSorting: Boolean = toJsBase.enableSorting.getOrElse(true)

  /** WARNING: This mutates the object in-place. */
  def setEnableSorting(enableSorting: Option[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.enableSorting = enableSorting.orUndefined)

  /** WARNING: This mutates the object in-place. */
  inline def withEnableSorting(enableSorting: Boolean): TableOptions[T, TM, CM, TF] =
    setEnableSorting(enableSorting.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutEnableSorting: TableOptions[T, TM, CM, TF] =
    setEnableSorting(none)

  lazy val enableMultiSort: Option[Boolean] = toJsBase.enableMultiSort.toOption

  /** WARNING: This mutates the object in-place. */
  def setEnableMultiSort(enableMultiSort: Option[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.enableMultiSort = enableMultiSort.orUndefined)

  /** WARNING: This mutates the object in-place. */
  inline def withEnableMultiSort(enableMultiSort: Boolean): TableOptions[T, TM, CM, TF] =
    setEnableMultiSort(enableMultiSort.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutEnableMultiSort: TableOptions[T, TM, CM, TF] =
    setEnableMultiSort(none)

  lazy val enableSortingRemoval: Option[Boolean] = toJsBase.enableSortingRemoval.toOption

  /** WARNING: This mutates the object in-place. */
  def setEnableSortingRemoval(
    enableSortingRemoval: Option[Boolean]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.enableSortingRemoval = enableSortingRemoval.orUndefined)

  /** WARNING: This mutates the object in-place. */
  inline def withEnableSortingRemoval(enableSortingRemoval: Boolean): TableOptions[T, TM, CM, TF] =
    setEnableSortingRemoval(enableSortingRemoval.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutEnableSortingRemoval: TableOptions[T, TM, CM, TF] =
    setEnableSortingRemoval(none)

  lazy val enableMultiRemove: Option[Boolean] = toJsBase.enableMultiRemove.toOption

  /** WARNING: This mutates the object in-place. */
  def setEnableMultiRemove(enableMultiRemove: Option[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.enableMultiRemove = enableMultiRemove.orUndefined)

  /** WARNING: This mutates the object in-place. */
  inline def withEnableMultiRemove(enableMultiRemove: Boolean): TableOptions[T, TM, CM, TF] =
    setEnableMultiRemove(enableMultiRemove.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutEnableMultiRemove: TableOptions[T, TM, CM, TF] =
    setEnableMultiRemove(none)

  lazy val getSortedRowModel: Option[Table[T, TM, CM, TF] => () => RowModel[T, TM, CM, TF]] =
    toJsBase.getSortedRowModel.toOption.map(fn => t => () => RowModel(fn(t.toJs)()))

  /** WARNING: This mutates the object in-place. */
  def setGetSortedRowModel(
    getSortedRowModel: Option[Table[T, TM, CM, TF] => () => RowModel[T, TM, CM, TF]]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.getSortedRowModel =
      getSortedRowModel.orUndefined
        .map: fn =>
          (t: raw.buildLibTypesMod.Table[T]) =>
            fn(Table(t)).map(_.toJs): js.Function0[raw.buildLibTypesMod.RowModel[T]]
    )

  /** WARNING: This mutates the object in-place. */
  inline def withGetSortedRowModel(
    getSortedRowModel: Table[T, TM, CM, TF] => () => RowModel[T, TM, CM, TF]
  ): TableOptions[T, TM, CM, TF] =
    setGetSortedRowModel(getSortedRowModel.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutGetSortedRowModel: TableOptions[T, TM, CM, TF] =
    setGetSortedRowModel(none)

  /** WARNING: This mutates the object in-place. */
  def withDefaultGetSortedRowModel: TableOptions[T, TM, CM, TF] =
    copy:
      _.getSortedRowModel = rawReact.mod.getSortedRowModel()

  lazy val isMultiSortEvent: Option[SyntheticEvent[dom.Node] => Boolean] =
    toJsBase.isMultiSortEvent.toOption.map(identity)

  /** WARNING: This mutates the object in-place. */
  def setIsMultiSortEvent(
    isMultiSortEvent: Option[SyntheticEvent[dom.Node] => Boolean]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.isMultiSortEvent =
      isMultiSortEvent.orUndefined.map(fn => e => fn(e.asInstanceOf[SyntheticEvent[dom.Node]]))
    )

  /** WARNING: This mutates the object in-place. */
  inline def withIsMultiSortEvent(
    isMultiSortEvent: SyntheticEvent[dom.Node] => Boolean
  ): TableOptions[T, TM, CM, TF] =
    setIsMultiSortEvent(isMultiSortEvent.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutIsMultiSortEvent: TableOptions[T, TM, CM, TF] =
    setIsMultiSortEvent(none)

  lazy val manualSorting: Option[Boolean] = toJsBase.manualSorting.toOption

  /** WARNING: This mutates the object in-place. */
  def setManualSorting(manualSorting: Option[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.manualSorting = manualSorting.orUndefined)

  /** WARNING: This mutates the object in-place. */
  inline def withManualSorting(manualSorting: Boolean): TableOptions[T, TM, CM, TF] =
    setManualSorting(manualSorting.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutManualSorting: TableOptions[T, TM, CM, TF] =
    setManualSorting(none)

  lazy val maxMultiSortColCount: Option[Int] =
    toJsBase.maxMultiSortColCount.toOption.map(_.toInt)

  /** WARNING: This mutates the object in-place. */
  def setMaxMultiSortColCount(maxMultiSortColCount: Option[Int]): TableOptions[T, TM, CM, TF] =
    copy(_.maxMultiSortColCount = maxMultiSortColCount.orUndefined.map(_.toDouble))

  /** WARNING: This mutates the object in-place. */
  inline def withMaxMultiSortColCount(maxMultiSortColCount: Int): TableOptions[T, TM, CM, TF] =
    setMaxMultiSortColCount(maxMultiSortColCount.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutMaxMultiSortColCount: TableOptions[T, TM, CM, TF] =
    setMaxMultiSortColCount(none)

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
      onSortingChange.orUndefined
        .map(fn =>
          u =>
            fn(
              Updater.fromJs(u) match
                case Updater.Set(v)   => Updater.Set(Sorting.fromJs(v))
                case Updater.Mod(mod) => Updater.Mod(v => Sorting.fromJs(mod(v.toJs)))
            ).runNow()
        )
    )

  /** WARNING: This mutates the object in-place. */
  inline def withOnSortingChange(
    onSortingChange: Updater[Sorting] => Callback
  ): TableOptions[T, TM, CM, TF] =
    setOnSortingChange(onSortingChange.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutOnSortingChange: TableOptions[T, TM, CM, TF] =
    setOnSortingChange(none)

  lazy val sortDescFirst: Option[Boolean] = toJsBase.sortDescFirst.toOption

  /** WARNING: This mutates the object in-place. */
  def setSortDescFirst(sortDescFirst: Option[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.sortDescFirst = sortDescFirst.orUndefined)

  /** WARNING: This mutates the object in-place. */
  inline def withSortDescFirst(sortDescFirst: Boolean): TableOptions[T, TM, CM, TF] =
    setSortDescFirst(sortDescFirst.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutSortDescFirst: TableOptions[T, TM, CM, TF] =
    setSortDescFirst(none)

  // Selection
  lazy val enableRowSelection: Option[Boolean] = toJsBase.enableRowSelection.toOption

  /** WARNING: This mutates the object in-place. */
  def setEnableRowSelection(enableRowSelection: Option[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.enableRowSelection = enableRowSelection.orUndefined)

  /** WARNING: This mutates the object in-place. */
  inline def withEnableRowSelection(enableRowSelection: Boolean): TableOptions[T, TM, CM, TF] =
    setEnableRowSelection(enableRowSelection.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutEnableRowSelection: TableOptions[T, TM, CM, TF] =
    setEnableRowSelection(none)

  lazy val enableMultiRowSelection: Option[Boolean] = toJsBase.enableRowSelection.toOption

  /** WARNING: This mutates the object in-place. */
  def setEnableMultiRowSelection(
    enableMultiRowSelection: Option[Boolean]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.enableMultiRowSelection = enableMultiRowSelection.orUndefined)

  /** WARNING: This mutates the object in-place. */
  inline def withEnableMultiRowSelection(
    enableMultiRowSelection: Boolean
  ): TableOptions[T, TM, CM, TF] =
    setEnableMultiRowSelection(enableMultiRowSelection.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutEnableMultiRowSelection: TableOptions[T, TM, CM, TF] =
    setEnableMultiRowSelection(none)

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
      onRowSelectionChange.orUndefined
        .map(fn =>
          u =>
            fn(
              Updater.fromJs(u) match
                case Updater.Set(v)   => Updater.Set(RowSelection.fromJs(v))
                case Updater.Mod(mod) => Updater.Mod(v => RowSelection.fromJs(mod(v.toJs)))
            ).runNow()
        )
    )

  /** WARNING: This mutates the object in-place. */
  inline def withOnRowSelectionChange(
    onRowSelectionChange: Updater[RowSelection] => Callback
  ): TableOptions[T, TM, CM, TF] =
    setOnRowSelectionChange(onRowSelectionChange.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutOnRowSelectionChange: TableOptions[T, TM, CM, TF] =
    setOnRowSelectionChange(none)

  // Expanding
  lazy val enableExpanding: Option[Boolean] = toJsBase.enableExpanding.toOption

  /** WARNING: This mutates the object in-place. */
  def setEnableExpanding(enableExpanding: Option[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.enableExpanding = enableExpanding.orUndefined)

  /** WARNING: This mutates the object in-place. */
  inline def withEnableExpanding(enableExpanding: Boolean): TableOptions[T, TM, CM, TF] =
    setEnableExpanding(enableExpanding.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutEnableExpanding: TableOptions[T, TM, CM, TF] =
    setEnableExpanding(none)

  lazy val getExpandedRowModel: Option[Table[T, TM, CM, TF] => () => RowModel[T, TM, CM, TF]] =
    toJsBase.getExpandedRowModel.toOption.map(fn => t => () => RowModel(fn(t.toJs)()))

  /** WARNING: This mutates the object in-place. */
  def setGetExpandedRowModel(
    getExpandedRowModel: Option[Table[T, TM, CM, TF] => () => RowModel[T, TM, CM, TF]]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.getExpandedRowModel =
      getExpandedRowModel.orUndefined
        .map: fn =>
          (t: raw.buildLibTypesMod.Table[T]) =>
            fn(Table(t)).map(_.toJs): js.Function0[raw.buildLibTypesMod.RowModel[T]]
    )

  /** WARNING: This mutates the object in-place. */
  inline def withGetExpandedRowModel(
    getExpandedRowModel: Table[T, TM, CM, TF] => () => RowModel[T, TM, CM, TF]
  ): TableOptions[T, TM, CM, TF] =
    setGetExpandedRowModel(getExpandedRowModel.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutGetExpandedRowModel: TableOptions[T, TM, CM, TF] =
    setGetExpandedRowModel(none)

  /** WARNING: This mutates the object in-place. */
  def withDefaultGetExpandedRowModel: TableOptions[T, TM, CM, TF] =
    copy:
      _.getExpandedRowModel = rawReact.mod.getExpandedRowModel()

  lazy val getSubRows: Option[(T, Int) => Option[List[T]]] =
    toJsBase.getSubRows.toOption.map(fn => (row, idx) => fn(row, idx).toOption.map(_.toList))

  /** WARNING: This mutates the object in-place. */
  def setGetSubRows(
    getSubRows: Option[(T, Int) => Option[List[T]]]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.getSubRows =
      getSubRows.orUndefined.map(fn => (row, idx) => fn(row, idx).map(_.toJSArray).orUndefined)
    )

  /** WARNING: This mutates the object in-place. */
  inline def withGetSubRows(getSubRows: (T, Int) => Option[List[T]]): TableOptions[T, TM, CM, TF] =
    setGetSubRows(getSubRows.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutGetSubRows: TableOptions[T, TM, CM, TF] =
    setGetSubRows(none)

  // Pinning
  lazy val enablePinning: Option[Boolean] = toJsBase.enablePinning.toOption

  /** WARNING: This mutates the object in-place. */
  def setEnablePinning(enablePinning: Option[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.enablePinning = enablePinning.orUndefined)

  /** WARNING: This mutates the object in-place. */
  inline def withEnablePinning(enablePinning: Boolean): TableOptions[T, TM, CM, TF] =
    setEnablePinning(enablePinning.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutEnablePinning: TableOptions[T, TM, CM, TF] =
    setEnablePinning(none)

  // Column Pinning
  lazy val enableColumnPinning: Option[Boolean] = toJsBase.enableColumnPinning.toOption

  /** WARNING: This mutates the object in-place. */
  def setEnableColumnPinning(
    enableColumnPinning: Option[Boolean]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.enableColumnPinning = enableColumnPinning.orUndefined)

  /** WARNING: This mutates the object in-place. */
  inline def withEnableColumnPinning(enableColumnPinning: Boolean): TableOptions[T, TM, CM, TF] =
    setEnableColumnPinning(enableColumnPinning.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutEnableColumnPinning: TableOptions[T, TM, CM, TF] =
    setEnableColumnPinning(none)

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

  /** WARNING: This mutates the object in-place. */
  inline def withOnColumnPinningChange(
    onColumnPinningChange: Updater[ColumnPinning] => Callback
  ): TableOptions[T, TM, CM, TF] =
    setOnColumnPinningChange(onColumnPinningChange.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutOnColumnPinningChange: TableOptions[T, TM, CM, TF] =
    setOnColumnPinningChange(none)

  // Row Pinning
  lazy val enableRowPinning: Option[RowPinningEnabled[T, TM, CM, TF]] =
    toJsBase.enableRowPinning.toOption.map(RowPinningEnabled.fromJs)

  /** WARNING: This mutates the object in-place. */
  def setEnableRowPinning(
    enableRowPinning: Option[RowPinningEnabled[T, TM, CM, TF]]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.enableRowPinning = enableRowPinning.map(_.toJs).orUndefined)

  /** WARNING: This mutates the object in-place. */
  inline def withEnableRowPinning(
    enableRowPinning: RowPinningEnabled[T, TM, CM, TF]
  ): TableOptions[T, TM, CM, TF] =
    setEnableRowPinning(enableRowPinning.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutEnableRowPinning: TableOptions[T, TM, CM, TF] =
    setEnableRowPinning(none)

  lazy val keepPinnedRows: Option[Boolean] = toJsBase.keepPinnedRows.toOption

  /** WARNING: This mutates the object in-place. */
  def setKeepPinnedRows(keepPinnedRows: Option[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.keepPinnedRows = keepPinnedRows.orUndefined)

  /** WARNING: This mutates the object in-place. */
  inline def withKeepPinnedRows(keepPinnedRows: Boolean): TableOptions[T, TM, CM, TF] =
    setKeepPinnedRows(keepPinnedRows.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutKeepPinnedRows: TableOptions[T, TM, CM, TF] =
    setKeepPinnedRows(none)

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

  /** WARNING: This mutates the object in-place. */
  inline def withOnRowPinningChange(
    onRowPinningChange: Updater[RowPinning] => Callback
  ): TableOptions[T, TM, CM, TF] =
    setOnRowPinningChange(onRowPinningChange.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutOnRowPinningChange: TableOptions[T, TM, CM, TF] =
    setOnRowPinningChange(none)

  // Column Filtering
  lazy val filterFromLeafRows: Option[Boolean] = toJsBase.filterFromLeafRows.toOption

  /** WARNING: This mutates the object in-place. */
  def setFilterFromLeafRows(filterFromLeafRows: Option[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.filterFromLeafRows = filterFromLeafRows.orUndefined)

  /** WARNING: This mutates the object in-place. */
  inline def withFilterFromLeafRows(filterFromLeafRows: Boolean): TableOptions[T, TM, CM, TF] =
    setFilterFromLeafRows(filterFromLeafRows.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutFilterFromLeafRows: TableOptions[T, TM, CM, TF] =
    setFilterFromLeafRows(none)

  lazy val maxLeafRowFilterDepth: Option[Double] = toJsBase.maxLeafRowFilterDepth.toOption

  /** WARNING: This mutates the object in-place. */
  def setMaxLeafRowFilterDepth(
    maxLeafRowFilterDepth: Option[Double]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.maxLeafRowFilterDepth = maxLeafRowFilterDepth.orUndefined)

  /** WARNING: This mutates the object in-place. */
  inline def withMaxLeafRowFilterDepth(maxLeafRowFilterDepth: Double): TableOptions[T, TM, CM, TF] =
    setMaxLeafRowFilterDepth(maxLeafRowFilterDepth.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutMaxLeafRowFilterDepth: TableOptions[T, TM, CM, TF] =
    setMaxLeafRowFilterDepth(none)

  lazy val enableFilters: Option[Boolean] = toJsBase.enableFilters.toOption

  /** WARNING: This mutates the object in-place. */
  def setEnableFilters(enableFilters: Option[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.enableFilters = enableFilters.orUndefined)

  /** WARNING: This mutates the object in-place. */
  inline def withEnableFilters(enableFilters: Boolean): TableOptions[T, TM, CM, TF] =
    setEnableFilters(enableFilters.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutEnableFilters: TableOptions[T, TM, CM, TF] =
    setEnableFilters(none)

  lazy val manualFiltering: Option[Boolean] = toJsBase.manualFiltering.toOption

  /** WARNING: This mutates the object in-place. */
  def setManualFiltering(manualFiltering: Option[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.manualFiltering = manualFiltering.orUndefined)

  /** WARNING: This mutates the object in-place. */
  inline def withManualFiltering(manualFiltering: Boolean): TableOptions[T, TM, CM, TF] =
    setManualFiltering(manualFiltering.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutManualFiltering: TableOptions[T, TM, CM, TF] =
    setManualFiltering(none)

  lazy val onColumnFiltersChange: Option[Updater[ColumnFilters] => Callback] =
    toJsBase.onColumnFiltersChange.toOption
      .map: fn =>
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

  /** WARNING: This mutates the object in-place. */
  inline def withOnColumnFiltersChange(
    onColumnFiltersChange: Updater[ColumnFilters] => Callback
  ): TableOptions[T, TM, CM, TF] =
    setOnColumnFiltersChange(onColumnFiltersChange.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutOnColumnFiltersChange: TableOptions[T, TM, CM, TF] =
    setOnColumnFiltersChange(none)

  lazy val enableColumnFilters: Option[Boolean] = toJsBase.enableColumnFilters.toOption

  /** WARNING: This mutates the object in-place. */
  def setEnableColumnFilters(
    enableColumnFilters: Option[Boolean]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.enableColumnFilters = enableColumnFilters.orUndefined)

  /** WARNING: This mutates the object in-place. */
  inline def withEnableColumnFilters(enableColumnFilters: Boolean): TableOptions[T, TM, CM, TF] =
    setEnableColumnFilters(enableColumnFilters.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutEnableColumnFilters: TableOptions[T, TM, CM, TF] =
    setEnableColumnFilters(none)

  lazy val getFilteredRowModel: Option[Table[T, TM, CM, TF] => () => RowModel[T, TM, CM, TF]] =
    toJsBase.getFilteredRowModel.toOption.map(fn => t => () => RowModel(fn(t.toJs)()))

  /** WARNING: This mutates the object in-place. */
  def setGetFilteredRowModel(
    getFilteredRowModel: Option[Table[T, TM, CM, TF] => () => RowModel[T, TM, CM, TF]]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.getFilteredRowModel =
      getFilteredRowModel.orUndefined
        .map: fn =>
          (t: raw.buildLibTypesMod.Table[T]) =>
            fn(Table(t)).map(_.toJs): js.Function0[raw.buildLibTypesMod.RowModel[T]]
    )

  /** WARNING: This mutates the object in-place. */
  inline def withGetFilteredRowModel(
    getFilteredRowModel: Table[T, TM, CM, TF] => () => RowModel[T, TM, CM, TF]
  ): TableOptions[T, TM, CM, TF] =
    setGetFilteredRowModel(getFilteredRowModel.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutGetFilteredRowModel: TableOptions[T, TM, CM, TF] =
    setGetFilteredRowModel(none)

  /** WARNING: This mutates the object in-place. */
  def withDefaultGetFilteredRowModel: TableOptions[T, TM, CM, TF] =
    copy:
      _.getFilteredRowModel = rawReact.mod.getFilteredRowModel()

  // Global Filtering
  lazy val enableGlobalFilter: Option[Boolean] = toJsBase.enableGlobalFilter.toOption

  /** WARNING: This mutates the object in-place. */
  def setEnableGlobalFilter(enableGlobalFilter: Option[Boolean]): TableOptions[T, TM, CM, TF] =
    copy(_.enableGlobalFilter = enableGlobalFilter.orUndefined)

  /** WARNING: This mutates the object in-place. */
  inline def withEnableGlobalFilter(enableGlobalFilter: Boolean): TableOptions[T, TM, CM, TF] =
    setEnableGlobalFilter(enableGlobalFilter.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutEnableGlobalFilter: TableOptions[T, TM, CM, TF] =
    setEnableGlobalFilter(none)

  lazy val globalFilterFn: Option[BuiltInFilter[TF] | FilterFn[T, TM, CM, TF, TF, Any]] =
    toJsBase.globalFilterFn.toOption
      .map: v =>
        js.typeOf(v) match
          case "string" =>
            BuiltInFilter.fromJs(v.asInstanceOf[String]).asInstanceOf[BuiltInFilter[TF]]
          case fn       =>
            FilterFn.fromJs(fn.asInstanceOf[raw.buildLibFeaturesColumnFilteringMod.FilterFn[T]])

  /** WARNING: This mutates the object in-place. */
  def setGlobalFilterFn[TF1](
    globalFilterFn: Option[
      BuiltInFilter[TF1] | FilterFn[T, TM, CM, TF1, TF1, Any] |
        FilterFn.Type[T, TM, CM, TF1, TF1, Any]
    ]
  ): TableOptions[T, TM, CM, TF1] =
    copy(_.globalFilterFn =
      globalFilterFn.orUndefined
        .map: fn =>
          fn match
            case builtIn: BuiltInFilter[TF1] => builtIn.toJs
            case ff @ FilterFn(_, _, _)      => ff.asInstanceOf[FilterFn[T, TM, CM, TF1, TF1, Any]].toJs
            case fn                          =>
              FilterFn(fn.asInstanceOf[FilterFn.Type[T, TM, CM, TF1, TF1, Any]], none, none).toJs
    ).asInstanceOf[TableOptions[T, TM, CM, TF1]]

  /** WARNING: This mutates the object in-place. */
  inline def withGlobalFilterFn[TF1](
    globalFilterFn: BuiltInFilter[TF1] | FilterFn[T, TM, CM, TF1, TF1, Any] |
      FilterFn.Type[T, TM, CM, TF1, TF1, Any]
  ): TableOptions[T, TM, CM, TF1] =
    setGlobalFilterFn(globalFilterFn.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutGlobalFilterFn[TF1]: TableOptions[T, TM, CM, TF1] =
    setGlobalFilterFn[TF1](none)

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

  /** WARNING: This mutates the object in-place. */
  inline def withOnGlobalFilterChange(
    onGlobalFilterChange: Updater[TF] => Callback
  ): TableOptions[T, TM, CM, TF] =
    setOnGlobalFilterChange(onGlobalFilterChange.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutOnGlobalFilterChange: TableOptions[T, TM, CM, TF] =
    setOnGlobalFilterChange(none)

  lazy val getColumnCanGlobalFilter: Option[Column[T, Any, TM, CM, TF, Any, Any] => Boolean] =
    toJsBase.getColumnCanGlobalFilter.toOption.map: fn =>
      col => fn(col.toJs)

  /** WARNING: This mutates the object in-place. */
  def setGetColumnCanGlobalFilter(
    getColumnCanGlobalFilter: Option[Column[T, ?, TM, CM, TF, ?, ?] => Boolean]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.getColumnCanGlobalFilter = getColumnCanGlobalFilter.orUndefined.map: fn =>
      col => fn(Column(col)))

  /** WARNING: This mutates the object in-place. */
  inline def withGetColumnCanGlobalFilter(
    getColumnCanGlobalFilter: Column[T, ?, TM, CM, TF, ?, ?] => Boolean
  ): TableOptions[T, TM, CM, TF] =
    setGetColumnCanGlobalFilter(getColumnCanGlobalFilter.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutGetColumnCanGlobalFilter: TableOptions[T, TM, CM, TF] =
    setGetColumnCanGlobalFilter(none)

  // Column Faceting
  lazy val getFacetedRowModel
    : Option[(Table[T, TM, CM, TF], ColumnId) => () => RowModel[T, TM, CM, TF]] =
    toJsBase.getFacetedRowModel.toOption.map: fn =>
      (t, colId) =>
        (fn(t.toJs, colId.value): (() => raw.buildLibTypesMod.RowModel[T])).map(RowModel(_))

  /** WARNING: This mutates the object in-place. */
  def setGetFacetedRowModel(
    getFacetedRowModel: Option[(Table[T, TM, CM, TF], ColumnId) => () => RowModel[T, TM, CM, TF]]
  ): TableOptions[T, TM, CM, TF] =
    copy(_.getFacetedRowModel =
      getFacetedRowModel.orUndefined
        .map: fn =>
          (t: raw.buildLibTypesMod.Table[T], colId: String) =>
            fn(Table(t), ColumnId(colId))
              .map(_.toJs): js.Function0[raw.buildLibTypesMod.RowModel[T]]
    )

  /** WARNING: This mutates the object in-place. */
  inline def withGetFacetedRowModel(
    getFacetedRowModel: (Table[T, TM, CM, TF], ColumnId) => () => RowModel[T, TM, CM, TF]
  ): TableOptions[T, TM, CM, TF] =
    setGetFacetedRowModel(getFacetedRowModel.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutGetFacetedRowModel: TableOptions[T, TM, CM, TF] =
    setGetFacetedRowModel(none)

  /** WARNING: This mutates the object in-place. */
  def withDefaultGetFacetedRowModel: TableOptions[T, TM, CM, TF] =
    copy:
      _.getFacetedRowModel = rawReact.mod.getFacetedRowModel()

  lazy val getFacetedUniqueValues: Option[(Table[T, TM, CM, TF], ColumnId) => () => Map[Any, Int]] =
    toJsBase.getFacetedUniqueValues.toOption.map: fn =>
      (t, colId) => () => Map.fromJsMap(fn(t.toJs, colId.value)()).view.mapValues(_.toInt).toMap

  /** WARNING: This mutates the object in-place. */
  def setGetFacetedUniqueValues(
    getFacetedUniqueValues: Option[(Table[T, TM, CM, TF], ColumnId) => () => Map[Any, Int]]
  ): TableOptions[T, TM, CM, TF] =
    copy(
      _.getFacetedUniqueValues = getFacetedUniqueValues.orUndefined
        .map: fn =>
          (t: raw.buildLibTypesMod.Table[T], colId: String) =>
            fn(Table(t), ColumnId(colId)).map(
              _.view.mapValues(_.toDouble).toMap.toJsMap
            ): js.Function0[JsMap[Any, Double]]
    )

  /** WARNING: This mutates the object in-place. */
  inline def withGetFacetedUniqueValues(
    getFacetedUniqueValues: (Table[T, TM, CM, TF], ColumnId) => () => Map[Any, Int]
  ): TableOptions[T, TM, CM, TF] =
    setGetFacetedUniqueValues(getFacetedUniqueValues.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutGetFacetedUniqueValues: TableOptions[T, TM, CM, TF] =
    setGetFacetedUniqueValues(none)

  /** WARNING: This mutates the object in-place. */
  def withDefaultGetFacetedUniqueValues: TableOptions[T, TM, CM, TF] =
    copy:
      _.getFacetedUniqueValues = rawReact.mod.getFacetedUniqueValues()

  lazy val getFacetedMinMaxValues
    : Option[(Table[T, TM, CM, TF], ColumnId) => () => Option[(Double, Double)]] =
    toJsBase.getFacetedMinMaxValues.toOption.map: fn =>
      (t, colId) => () => fn(t.toJs, colId.value)().toOption.map(identity) // Force type cast

  /** WARNING: This mutates the object in-place. */
  def setGetFacetedMinMaxValues(
    getFacetedMinMaxValues: Option[
      (Table[T, TM, CM, TF], ColumnId) => () => Option[(Double, Double)]
    ]
  ): TableOptions[T, TM, CM, TF] =
    copy(
      _.getFacetedMinMaxValues = getFacetedMinMaxValues.orUndefined
        .map: fn =>
          (t: raw.buildLibTypesMod.Table[T], colId: String) =>
            fn(Table(t), ColumnId(colId)).map(
              _.orUndefined.map((min, max) => js.Tuple2(min, max))
            ): js.Function0[js.UndefOr[js.Tuple2[Double, Double]]]
    )

  /** WARNING: This mutates the object in-place. */
  inline def withGetFacetedMinMaxValues(
    getFacetedMinMaxValues: (Table[T, TM, CM, TF], ColumnId) => () => Option[(Double, Double)]
  ): TableOptions[T, TM, CM, TF] =
    setGetFacetedMinMaxValues(getFacetedMinMaxValues.some)

  /** WARNING: This mutates the object in-place. */
  inline def withoutGetFacetedMinMaxValues: TableOptions[T, TM, CM, TF] =
    setGetFacetedMinMaxValues(none)

  /** WARNING: This mutates the object in-place. */
  def withDefaultGetFacetedMinMaxValues: TableOptions[T, TM, CM, TF] =
    copy:
      _.getFacetedMinMaxValues = rawReact.mod.getFacetedMinMaxValues()

end TableOptions

object TableOptions:
  def apply[T, TM, CM, TF](
    columns_                 : Reusable[List[ColumnDef[T, ?, TM, CM, TF, ?, ?]]],
    data_                    : Reusable[List[T]],
    getCoreRowModel:           js.UndefOr[Table[T, TM, CM, TF] => () => RowModel[T, TM, CM, TF]] =
      js.undefined,
    getRowId:                  js.UndefOr[(T, Int, Option[T]) => RowId] = js.undefined,
    onStateChange:             js.UndefOr[Updater[TableState[TF]] => Callback] = js.undefined,
    renderFallbackValue:       js.UndefOr[Any] = js.undefined,
    state:                     js.UndefOr[PartialTableState] = js.undefined,
    initialState:              js.UndefOr[TableState[TF]] = js.undefined,
    meta:                      js.UndefOr[TM] = js.undefined,
    // Column Sizing
    enableColumnResizing:      js.UndefOr[Boolean] = js.undefined,
    columnResizeMode:          js.UndefOr[ColumnResizeMode] = js.undefined,
    onColumnSizingChange:      js.UndefOr[Updater[ColumnSizing] => Callback] = js.undefined,
    onColumnSizingInfoChange:  js.UndefOr[Updater[ColumnSizingInfo] => Callback] = js.undefined,
    // Column Visibility
    enableHiding:              js.UndefOr[Boolean] = js.undefined,
    onColumnVisibilityChange:  js.UndefOr[Updater[ColumnVisibility] => Callback] = js.undefined,
    // Sorting
    enableSorting:             js.UndefOr[Boolean] = js.undefined,
    enableMultiSort:           js.UndefOr[Boolean] = js.undefined,
    enableSortingRemoval:      js.UndefOr[Boolean] = js.undefined,
    enableMultiRemove:         js.UndefOr[Boolean] = js.undefined,
    getSortedRowModel:         js.UndefOr[Table[T, TM, CM, TF] => () => RowModel[T, TM, CM, TF]] =
      js.undefined,
    isMultiSortEvent:          js.UndefOr[SyntheticEvent[dom.Node] => Boolean] = js.undefined,
    manualSorting:             js.UndefOr[Boolean] = js.undefined,
    maxMultiSortColCount:      js.UndefOr[Int] = js.undefined,
    onSortingChange:           js.UndefOr[Updater[Sorting] => Callback] = js.undefined,
    sortDescFirst:             js.UndefOr[Boolean] = js.undefined,
    // Selection
    enableRowSelection:        js.UndefOr[Boolean] = js.undefined,
    enableMultiRowSelection:   js.UndefOr[Boolean] = js.undefined,
    onRowSelectionChange:      js.UndefOr[Updater[RowSelection] => Callback] = js.undefined,
    // Expanding
    enableExpanding:           js.UndefOr[Boolean] = js.undefined,
    getExpandedRowModel:       js.UndefOr[Table[T, TM, CM, TF] => () => RowModel[T, TM, CM, TF]] =
      js.undefined,
    getSubRows:                js.UndefOr[(T, Int) => Option[List[T]]] = js.undefined,
    // Pinning
    enablePinning:             js.UndefOr[Boolean] = js.undefined,
    // Column Pinning
    enableColumnPinning:       js.UndefOr[Boolean] = js.undefined,
    onColumnPinningChange:     js.UndefOr[Updater[ColumnPinning] => Callback] = js.undefined,
    // Row Pinning
    enableRowPinning:          js.UndefOr[RowPinningEnabled[T, TM, CM, TF]] = js.undefined,
    keepPinnedRows:            js.UndefOr[Boolean] = js.undefined,
    onRowPinningChange:        js.UndefOr[Updater[RowPinning] => Callback] = js.undefined,
    // Column Filtering
    enableFilters:             js.UndefOr[Boolean] = js.undefined,
    enableColumnFilters:       js.UndefOr[Boolean] = js.undefined,
    filterFromLeafRows:        js.UndefOr[Boolean] = js.undefined,
    maxLeafRowFilterDepth:     js.UndefOr[Double] = js.undefined,
    manualFiltering:           js.UndefOr[Boolean] = js.undefined,
    onColumnFiltersChange:     js.UndefOr[Updater[ColumnFilters] => Callback] = js.undefined,
    getFilteredRowModel:       js.UndefOr[Table[T, TM, CM, TF] => () => RowModel[T, TM, CM, TF]] =
      js.undefined,
    // Global Filtering
    enableGlobalFilter:        js.UndefOr[Boolean] = js.undefined,
    globalFilterFn:            js.UndefOr[
      BuiltInFilter[TF] | FilterFn[T, TM, CM, TF, TF, Any] | FilterFn.Type[T, TM, CM, TF, TF, Any]
    ] = js.undefined,
    onGlobalFilterChange:      js.UndefOr[Updater[TF] => Callback] = js.undefined,
    getColumnCanGlobalFilter:  js.UndefOr[Column[T, ?, TM, CM, TF, ?, ?] => Boolean] = js.undefined,
    // Column Faceting
    enableFaceting:            js.UndefOr[Boolean] = js.undefined, // Added for convenience
    getFacetedRowModel:        js.UndefOr[
      (Table[T, TM, CM, TF], ColumnId) => () => RowModel[T, TM, CM, TF]
    ] = js.undefined,
    enableFacetedUniqueValues: js.UndefOr[Boolean] = js.undefined, // Added for convenience
    getFacetedUniqueValues:    js.UndefOr[(Table[T, TM, CM, TF], ColumnId) => () => Map[Any, Int]] =
      js.undefined,
    enableFacetedMinMaxValues: js.UndefOr[Boolean] = js.undefined, // Added for convenience
    getFacetedMinMaxValues:    js.UndefOr[(Table[T, TM, CM, TF], ColumnId) => () => Option[
      (Double, Double)
    ]] = js.undefined
  ): TableOptions[T, TM, CM, TF] =
    val autoEnableSorting: Boolean             =
      !enableSorting.contains(false) && (
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
    val autoEnableExpanding: Boolean           =
      !enableExpanding.contains(false) && (
        enableExpanding.contains(true) ||
          getExpandedRowModel.isDefined ||
          getSubRows.isDefined
      )
    val autoEnableColumnFiltering: Boolean     =
      !enableColumnFilters.contains(false) && (
        enableColumnFilters.contains(true) ||
          filterFromLeafRows.contains(true) ||
          maxLeafRowFilterDepth.isDefined ||
          manualFiltering.contains(true) ||
          onColumnFiltersChange.isDefined ||
          getFilteredRowModel.isDefined
      )
    val autoEnableGlobalFiltering: Boolean     =
      !enableGlobalFilter.contains(false) && (
        enableGlobalFilter.contains(true) ||
          globalFilterFn.isDefined ||
          onGlobalFilterChange.isDefined
      )
    val autoEnableFiltering: Boolean           =
      !enableFilters.contains(false) && (
        enableFilters.contains(true) ||
          autoEnableColumnFiltering ||
          autoEnableGlobalFiltering
      )
    val autoEnableFacetedUniqueValues: Boolean =
      !enableFacetedUniqueValues.contains(false) && (
        enableFacetedUniqueValues.contains(true) ||
          getFacetedUniqueValues.isDefined
      )
    val autoEnableFacetedMinMaxValues: Boolean =
      !enableFacetedMinMaxValues.contains(false) && (
        enableFacetedMinMaxValues.contains(true) ||
          getFacetedMinMaxValues.isDefined
      )
    val autoEnableFaceting: Boolean            =
      !enableFaceting.contains(false) && (
        enableFaceting.contains(true) ||
          getFacetedRowModel.isDefined ||
          autoEnableFacetedUniqueValues ||
          autoEnableFacetedMinMaxValues
      )

    new TableOptions[T, TM, CM, TF] {
      val columns                 = columns_.asInstanceOf[Reusable[List[ColumnDef[T, ?, TM, CM, TF, ?, ?]]]]
      val data                    = data_
      private[table] val toJsBase = new TableOptionsJs[T, TM, CM] {
        var columns         = null // Undefined on purpose, should not be accessible
        var data            = null // Undefined on purpose, should not be accessible
        var getCoreRowModel = null
      }
    }
      .applyOrElse(getCoreRowModel, _.withGetCoreRowModel(_), _.withDefaultGetCoreRowModel)
      .applyOrElseWhen(
        autoEnableSorting,
        getSortedRowModel,
        _.withGetSortedRowModel(_),
        _.withDefaultGetSortedRowModel
      )
      .applyOrElseWhen(
        autoEnableExpanding,
        getExpandedRowModel,
        _.withGetExpandedRowModel(_),
        _.withDefaultGetExpandedRowModel
      )
      .applyOrElseWhen(
        autoEnableFiltering,
        getFilteredRowModel,
        _.withGetFilteredRowModel(_),
        _.withDefaultGetFilteredRowModel
      )
      .applyOrElseWhen(
        autoEnableFaceting,
        getFacetedRowModel,
        _.withGetFacetedRowModel(_),
        _.withDefaultGetFacetedRowModel
      )
      .applyOrElseWhen(
        autoEnableFacetedUniqueValues,
        getFacetedUniqueValues,
        _.withGetFacetedUniqueValues(_),
        _.withDefaultGetFacetedUniqueValues
      )
      .applyOrElseWhen(
        autoEnableFacetedMinMaxValues,
        getFacetedMinMaxValues,
        _.withGetFacetedMinMaxValues(_),
        _.withDefaultGetFacetedMinMaxValues
      )
      .applyOrNot(getRowId, _.withGetRowId(_))
      .applyOrNot(onStateChange, _.withOnStateChange(_))
      .applyOrNot(renderFallbackValue, _.withRenderFallbackValue(_))
      .applyOrNot(state, _.withState(_))
      .applyOrNot(initialState, _.withInitialState(_))
      .applyOrNot(meta, _.withMeta(_))
      .applyOrNot(enableColumnResizing, _.withEnableColumnResizing(_))
      .applyOrNot(columnResizeMode, _.withColumnResizeMode(_))
      .applyOrNot(onColumnSizingChange, _.withOnColumnSizingChange(_))
      .applyOrNot(onColumnSizingInfoChange, _.withOnColumnSizingInfoChange(_))
      .applyOrNot(enableHiding, _.withEnableHiding(_))
      .applyOrNot(onColumnVisibilityChange, _.withOnColumnVisibilityChange(_))
      .applyOrNot(enableSorting, _.withEnableSorting(_))
      .applyOrNot(enableMultiSort, _.withEnableMultiSort(_))
      .applyOrNot(enableSortingRemoval, _.withEnableSortingRemoval(_))
      .applyOrNot(enableMultiRemove, _.withEnableMultiRemove(_))
      .applyOrNot(isMultiSortEvent, _.withIsMultiSortEvent(_))
      .applyOrNot(manualSorting, _.withManualSorting(_))
      .applyOrNot(maxMultiSortColCount, _.withMaxMultiSortColCount(_))
      .applyOrNot(onSortingChange, _.withOnSortingChange(_))
      .applyOrNot(sortDescFirst, _.withSortDescFirst(_))
      .applyOrNot(enableRowSelection, _.withEnableRowSelection(_))
      .applyOrNot(enableMultiRowSelection, _.withEnableMultiRowSelection(_))
      .applyOrNot(onRowSelectionChange, _.withOnRowSelectionChange(_))
      .applyOrNot(enableExpanding, _.withEnableExpanding(_))
      .applyOrNot(getSubRows, _.withGetSubRows(_))
      .applyOrNot(enablePinning, _.withEnablePinning(_))
      .applyOrNot(enableColumnPinning, _.withEnableColumnPinning(_))
      .applyOrNot(onColumnPinningChange, _.withOnColumnPinningChange(_))
      .applyOrNot(enableRowPinning, _.withEnableRowPinning(_))
      .applyOrNot(keepPinnedRows, _.withKeepPinnedRows(_))
      .applyOrNot(onRowPinningChange, _.withOnRowPinningChange(_))
      .applyOrNot(filterFromLeafRows, _.withFilterFromLeafRows(_))
      .applyOrNot(maxLeafRowFilterDepth, _.withMaxLeafRowFilterDepth(_))
      .applyOrNot(enableFilters, _.withEnableFilters(_))
      .applyOrNot(manualFiltering, _.withManualFiltering(_))
      .applyOrNot(onColumnFiltersChange, _.withOnColumnFiltersChange(_))
      .applyOrNot(enableColumnFilters, _.withEnableColumnFilters(_))
      .applyOrNot(enableGlobalFilter, _.withEnableGlobalFilter(_))
      .applyOrNot(globalFilterFn, _.withGlobalFilterFn(_))
      .applyOrNot(onGlobalFilterChange, _.withOnGlobalFilterChange(_))
      .applyOrNot(getColumnCanGlobalFilter, _.withGetColumnCanGlobalFilter(_))

  private[table] def fromJs[T, TM, CM, TF](
    raw: TableOptionsJs[T, TM, CM]
  ): TableOptions[T, TM, CM, TF] =
    new TableOptions[T, TM, CM, TF]:
      lazy val columns            = Reusable.always(raw.columns.toList.map(ColumnDef.fromJs))
      lazy val data               = Reusable.always(raw.data.toList)
      private[table] val toJsBase = raw
