// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import japgolly.scalajs.react.*
import japgolly.scalajs.react.facade.SyntheticEvent
import lucuma.react.table.facade.ColumnDefJs
import lucuma.react.table.facade.TableOptionsJs
import org.scalajs.dom
import reactST.{tanstackReactTable => rawReact}
import reactST.{tanstackTableCore => raw}

import scalajs.js
import scalajs.js.JSConverters.*

sealed trait TableOptions[T]:
  def columns: Reusable[List[ColumnDef[T, ?]]]
  def data: Reusable[List[T]]
  private[table] def toJsBase: TableOptionsJs[T]

  private[table] def columnsJs: js.Array[ColumnDefJs[T, ?]] = columns.toJSArray.map(_.toJs)

  private[table] def dataJs: js.Array[T] = data.toJSArray

  private[table] def toJs(cols: js.Array[ColumnDefJs[T, ?]], rows: js.Array[T]): TableOptionsJs[T] =
    toJsBase.columns = cols
    toJsBase.data = rows
    toJsBase

  private def copy(toJsMod: TableOptionsJs[T] => Unit): TableOptions[T] =
    val self = this
    new TableOptions[T]:
      def columns                 = self.columns
      def data                    = self.data
      private[table] def toJsBase = { toJsMod(self.toJsBase); self.toJsBase }

  lazy val getRowId: js.UndefOr[(T, Int, Option[T]) => RowId] =
    toJsBase.getRowId.map(fn =>
      (originalRow, index, parent) => RowId(fn(originalRow, index, parent.orUndefined))
    )

  /** WARNING: This mutates the object in-place. */
  def setGetRowId(getRowId: js.UndefOr[(T, Int, Option[T]) => RowId]): TableOptions[T] =
    copy(_.getRowId =
      getRowId.map(fn =>
        (originalRow, index, parent) => fn(originalRow, index, parent.toOption).value
      )
    )

  lazy val getCoreRowModel: Table[T] => () => raw.mod.RowModel[T] =
    t => toJsBase.getCoreRowModel(t.toJs)

  /** WARNING: This mutates the object in-place. */
  def setGetCoreRowModel(
    getCoreRowModel: js.UndefOr[Table[T] => () => raw.mod.RowModel[T]]
  ): TableOptions[T] =
    copy(_.getCoreRowModel =
      getCoreRowModel
        .map(fn =>
          ((t: raw.mod.Table[T]) => fn(Table(t))): js.Function1[raw.mod.Table[T], js.Function0[
            raw.mod.RowModel[T]
          ]]
        )
        .getOrElse(rawReact.mod.getCoreRowModel())
    )

  lazy val renderFallbackValue: js.UndefOr[Any] = toJsBase.renderFallbackValue

  /** WARNING: This mutates the object in-place. */
  def setRenderFallbackValue(renderFallbackValue: js.UndefOr[Any]): TableOptions[T] =
    copy(_.renderFallbackValue = renderFallbackValue)

  lazy val state: js.UndefOr[raw.anon.PartialTableState] = toJsBase.state

  /** WARNING: This mutates the object in-place. */
  def setState(state: js.UndefOr[raw.anon.PartialTableState]): TableOptions[T] =
    copy(_.state = state)

  lazy val initialState: js.UndefOr[TableState] =
    TableState(toJsBase.initialState.asInstanceOf[raw.mod.TableState])

  /** WARNING: This mutates the object in-place. */
  def setInitialState(initialState: js.UndefOr[TableState]): TableOptions[T] =
    copy(_.initialState = initialState.map(_.toJs.asInstanceOf[raw.mod.InitialTableState]))

  // Column Sizing
  lazy val enableColumnResizing: js.UndefOr[Boolean] = toJsBase.enableColumnResizing

  /** WARNING: This mutates the object in-place. */
  def setEnableColumnResizing(enableColumnResizing: js.UndefOr[Boolean]): TableOptions[T] =
    copy(_.enableColumnResizing = enableColumnResizing)

  lazy val columnResizeMode: js.UndefOr[ColumnResizeMode] =
    toJsBase.columnResizeMode.map(ColumnResizeMode.fromJs)

  /** WARNING: This mutates the object in-place. */
  def setColumnResizeMode(columnResizeMode: js.UndefOr[ColumnResizeMode]): TableOptions[T] =
    copy(_.columnResizeMode = columnResizeMode.map(_.toJs))

  // Column Visibility
  lazy val enableHiding: js.UndefOr[Boolean] = toJsBase.enableHiding

  /** WARNING: This mutates the object in-place. */
  def setEnableHiding(enableHiding: js.UndefOr[Boolean]): TableOptions[T] =
    copy(_.enableHiding = enableHiding)

  // Column Visibility
  lazy val onColumnVisibilityChange: js.UndefOr[Updater[ColumnVisibility] => Callback] =
    toJsBase.onColumnVisibilityChange.map(fn =>
      u =>
        Callback(fn((u match
          case Updater.Set(v)   => Updater.Set(v.toJs)
          case Updater.Mod(mod) =>
            Updater.Mod((v: raw.mod.VisibilityState) => mod(ColumnVisibility.fromJs(v)).toJs)
        ).toJs))
    )

  /** WARNING: This mutates the object in-place. */
  def setOnColumnVisibilityChange(
    onColumnVisibilityChange: js.UndefOr[Updater[ColumnVisibility] => Callback]
  ): TableOptions[T] =
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
  lazy val enableSorting: js.UndefOr[Boolean] = toJsBase.enableSorting

  /** WARNING: This mutates the object in-place. */
  def setEnableSorting(enableSorting: js.UndefOr[Boolean]): TableOptions[T] =
    copy(_.enableSorting = enableSorting)

  lazy val enableMultiSort: js.UndefOr[Boolean] = toJsBase.enableMultiSort

  /** WARNING: This mutates the object in-place. */
  def setEnableMultiSort(enableMultiSort: js.UndefOr[Boolean]): TableOptions[T] =
    copy(_.enableMultiSort = enableMultiSort)

  lazy val enableSortingRemoval: js.UndefOr[Boolean] = toJsBase.enableSortingRemoval

  /** WARNING: This mutates the object in-place. */
  def setEnableSortingRemoval(enableSortingRemoval: js.UndefOr[Boolean]): TableOptions[T] =
    copy(_.enableSortingRemoval = enableSortingRemoval)

  lazy val enableMultiRemove: js.UndefOr[Boolean] = toJsBase.enableMultiRemove

  /** WARNING: This mutates the object in-place. */
  def setEnableMultiRemove(enableMultiRemove: js.UndefOr[Boolean]): TableOptions[T] =
    copy(_.enableMultiRemove = enableMultiRemove)

  lazy val getSortedRowModel: js.UndefOr[Table[T] => () => raw.mod.RowModel[T]] =
    toJsBase.getSortedRowModel.map(fn => t => fn(t.toJs))

  /** WARNING: This mutates the object in-place. */
  def setGetSortedRowModel(
    getSortedRowModel: js.UndefOr[Table[T] => () => raw.mod.RowModel[T]]
  ): TableOptions[T] =
    copy(_.getSortedRowModel =
      getSortedRowModel
        .map(fn =>
          ((t: raw.mod.Table[T]) => fn(Table(t))): js.Function1[raw.mod.Table[T], js.Function0[
            raw.mod.RowModel[T]
          ]]
        )
        .getOrElse(rawReact.mod.getSortedRowModel())
    )

  lazy val isMultiSortEvent: js.UndefOr[SyntheticEvent[dom.Node] => Boolean] =
    toJsBase.isMultiSortEvent.map(identity)

  /** WARNING: This mutates the object in-place. */
  def setIsMultiSortEvent(
    isMultiSortEvent: js.UndefOr[SyntheticEvent[dom.Node] => Boolean]
  ): TableOptions[T] =
    copy(_.isMultiSortEvent =
      isMultiSortEvent.map(fn => e => fn(e.asInstanceOf[SyntheticEvent[dom.Node]]))
    )

  lazy val manualSorting: js.UndefOr[Boolean] = toJsBase.manualSorting

  /** WARNING: This mutates the object in-place. */
  def setManualSorting(manualSorting: js.UndefOr[Boolean]): TableOptions[T] =
    copy(_.manualSorting = manualSorting)

  lazy val maxMultiSortColCount: js.UndefOr[Int] = toJsBase.maxMultiSortColCount.map(_.toInt)

  /** WARNING: This mutates the object in-place. */
  def setMaxMultiSortColCount(maxMultiSortColCount: js.UndefOr[Int]): TableOptions[T] =
    copy(_.maxMultiSortColCount = maxMultiSortColCount.map(_.toDouble))

  lazy val onSortingChange: js.UndefOr[Updater[Sorting] => Callback] =
    toJsBase.onSortingChange.map(fn =>
      u =>
        Callback(fn((u match
          case Updater.Set(v)   => Updater.Set(v.toJs)
          case Updater.Mod(mod) =>
            Updater.Mod((v: raw.mod.SortingState) => mod(Sorting.fromJs(v)).toJs)
        ).toJs))
    )

  /** WARNING: This mutates the object in-place. */
  def setOnSortingChange(
    onSortingChange: js.UndefOr[Updater[Sorting] => Callback]
  ): TableOptions[T] =
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
  def setSortDescFirst(sortDescFirst: js.UndefOr[Boolean]): TableOptions[T] =
    copy(_.sortDescFirst = sortDescFirst)

  // Selection
  lazy val enableRowSelection: js.UndefOr[Boolean] = toJsBase.enableRowSelection

  /** WARNING: This mutates the object in-place. */
  def setEnableRowSelection(enableRowSelection: js.UndefOr[Boolean]): TableOptions[T] =
    copy(_.enableRowSelection = enableRowSelection)

  lazy val enableMultiRowSelection: js.UndefOr[Boolean] = toJsBase.enableRowSelection

  /** WARNING: This mutates the object in-place. */
  def setEnableMultiRowSelection(enableMultiRowSelection: js.UndefOr[Boolean]): TableOptions[T] =
    copy(_.enableMultiRowSelection = enableMultiRowSelection)

  lazy val onSelectChange: js.UndefOr[Updater[RowSelection] => Callback] =
    toJsBase.onSelectChange.map(fn =>
      u =>
        Callback(fn((u match
          case Updater.Set(v)   => Updater.Set(v.toJs)
          case Updater.Mod(mod) =>
            Updater.Mod((v: raw.mod.RowSelectionState) => mod(RowSelection.fromJs(v)).toJs)
        ).toJs))
    )

  /** WARNING: This mutates the object in-place. */
  def setOnSelectChange(
    onSelectChange: js.UndefOr[Updater[RowSelection] => Callback]
  ): TableOptions[T] =
    copy(_.onSelectChange =
      onSelectChange.map(fn =>
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
  def setEnableExpanding(enableExpanding: js.UndefOr[Boolean]): TableOptions[T] =
    copy(_.enableExpanding = enableExpanding)

  lazy val getExpandedRowModel: js.UndefOr[Table[T] => () => raw.mod.RowModel[T]] =
    toJsBase.getExpandedRowModel.map(fn => t => fn(t.toJs))

  /** WARNING: This mutates the object in-place. */
  def setGetExpandedRowModel(
    getExpandedRowModel: js.UndefOr[Table[T] => () => raw.mod.RowModel[T]]
  ): TableOptions[T] =
    copy(_.getExpandedRowModel =
      getExpandedRowModel
        .map(fn =>
          ((t: raw.mod.Table[T]) => fn(Table(t))): js.Function1[raw.mod.Table[T], js.Function0[
            raw.mod.RowModel[T]
          ]]
        )
        .getOrElse(rawReact.mod.getExpandedRowModel())
    )

  lazy val getSubRows: js.UndefOr[(T, Int) => Option[List[T]]] =
    toJsBase.getSubRows.map(fn => (row, idx) => fn(row, idx).toOption.map(_.toList))

  /** WARNING: This mutates the object in-place. */
  def setGetSubRows(getSubRows: js.UndefOr[(T, Int) => Option[List[T]]]): TableOptions[T] =
    copy(_.getSubRows =
      getSubRows.map(fn => (row, idx) => fn(row, idx).map(_.toJSArray).orUndefined)
    )
end TableOptions

object TableOptions:
  def apply[T](
    columns_                : Reusable[List[ColumnDef[T, ?]]],
    data_                   : Reusable[List[T]],
    getCoreRowModel:          js.UndefOr[Table[T] => () => raw.mod.RowModel[T]] = js.undefined,
    getRowId:                 js.UndefOr[(T, Int, Option[T]) => RowId] = js.undefined,
    // onStateChange:            js.UndefOr[raw.mod.Updater[raw.mod.TableState] => Callback] = js.undefined,
    renderFallbackValue:      js.UndefOr[Any] = js.undefined,
    // state:                    js.UndefOr[raw.anon.PartialTableState] = js.undefined,
    initialState:             js.UndefOr[TableState] = js.undefined,
    // Column Sizing
    enableColumnResizing:     js.UndefOr[Boolean] = js.undefined,
    columnResizeMode:         js.UndefOr[ColumnResizeMode] = js.undefined,
    // Column Visibility
    enableHiding:             js.UndefOr[Boolean] = js.undefined,
    onColumnVisibilityChange: js.UndefOr[Updater[ColumnVisibility] => Callback] = js.undefined,
    // Sorting
    enableSorting:            js.UndefOr[Boolean] = js.undefined,
    enableMultiSort:          js.UndefOr[Boolean] = js.undefined,
    enableSortingRemoval:     js.UndefOr[Boolean] = js.undefined,
    enableMultiRemove:        js.UndefOr[Boolean] = js.undefined,
    getSortedRowModel:        js.UndefOr[Table[T] => () => raw.mod.RowModel[T]] = js.undefined,
    isMultiSortEvent:         js.UndefOr[SyntheticEvent[dom.Node] => Boolean] = js.undefined,
    manualSorting:            js.UndefOr[Boolean] = js.undefined,
    maxMultiSortColCount:     js.UndefOr[Int] = js.undefined,
    onSortingChange:          js.UndefOr[Updater[Sorting] => Callback] = js.undefined,
    sortDescFirst:            js.UndefOr[Boolean] = js.undefined,
    // Selection
    enableRowSelection:       js.UndefOr[Boolean] = js.undefined,
    enableMultiRowSelection:  js.UndefOr[Boolean] = js.undefined,
    onSelectChange:           js.UndefOr[Updater[RowSelection] => Callback] = js.undefined,
    // Expanding
    enableExpanding:          js.UndefOr[Boolean] = js.undefined,
    getExpandedRowModel:      js.UndefOr[Table[T] => () => raw.mod.RowModel[T]] = js.undefined,
    getSubRows:               js.UndefOr[(T, Int) => Option[List[T]]] = js.undefined
  ): TableOptions[T] =
    new TableOptions[T] {
      val columns                 = columns_
      val data                    = data_
      private[table] val toJsBase = new TableOptionsJs[T] {
        var columns         = null // Undefined on purpose, should not be accessible
        var data            = null // Undefined on purpose, should not be accessible
        var getCoreRowModel = null
      }
    }
      .setGetCoreRowModel(getCoreRowModel)
      .setGetSortedRowModel(getSortedRowModel)
      .setGetExpandedRowModel(getExpandedRowModel)
      .applyOrNot(getRowId, _.setGetRowId(_))
      .applyOrNot(renderFallbackValue, _.setRenderFallbackValue(_))
      .applyOrNot(initialState, _.setInitialState(_))
      .applyOrNot(enableColumnResizing, _.setEnableColumnResizing(_))
      .applyOrNot(columnResizeMode, _.setColumnResizeMode(_))
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
      .applyOrNot(onSelectChange, _.setOnSelectChange(_))
      .applyOrNot(enableExpanding, _.setEnableExpanding(_))
      .applyOrNot(getSubRows, _.setGetSubRows(_))

  private[table] def fromJs[T](raw: TableOptionsJs[T]): TableOptions[T] =
    new TableOptions[T]:
      lazy val columns            = Reusable.always(raw.columns.toList.map(ColumnDef.fromJs))
      lazy val data               = Reusable.always(raw.data.toList)
      private[table] val toJsBase = raw
