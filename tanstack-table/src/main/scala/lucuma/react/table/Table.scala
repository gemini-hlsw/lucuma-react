// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import cats.Endo
import japgolly.scalajs.react.callback.Callback
import japgolly.scalajs.react.facade.SyntheticEvent
import lucuma.react.SizePx
import lucuma.react.table.facade.TableOptionsJs
import lucuma.typed.tanstackTableCore as raw
import org.scalajs.dom

import scalajs.js.JSConverters.*

// Missing: ColumnOrder, Filters, Grouping, Pagination
case class Table[T, TM, CM] private[table] (private[table] val toJs: raw.buildLibTypesMod.Table[T]):
  def getAllColumns(): List[Column[T, Any, TM, CM, Any, Any]]                    =
    toJs.getAllColumns().toList.map(Column(_))
  def getAllFlatColumns(): List[Column[T, Any, TM, CM, Any, Any]]                =
    toJs.getAllFlatColumns().toList.map(Column(_))
  def getAllLeafColumns(): List[Column[T, Any, TM, CM, Any, Any]]                =
    toJs.getAllLeafColumns().toList.map(Column(_))
  def getColumn(columnId: String): Option[Column[T, Any, TM, CM, Any, Any]]      =
    toJs.getColumn(columnId).toOption.map(Column(_))
  def getCoreRowModel(): RowModel[T, TM, CM]                                     = RowModel(toJs.getCoreRowModel())
  def getRow(id:      String): Row[T, TM, CM]                                    = Row(toJs.getRow(id))
  def getRowModel(): RowModel[T, TM, CM]                                         = RowModel(toJs.getRowModel())
  def getState(): TableState                                                     = TableState(toJs.getState())
  lazy val initialState: TableState                                              = TableState(toJs.initialState)
  lazy val options: TableOptions[T, TM, CM]                                      =
    TableOptions.fromJs(toJs.options.asInstanceOf[TableOptionsJs[T, TM, CM]])
  def reset(): Callback                                                          = Callback(toJs.reset())
  def setState(value: TableState): Callback                                      = Callback(toJs.setState(value.toJs))
  def modState(f: Endo[TableState]): Callback                                    =
    Callback(toJs.setState(rawState => f(TableState(rawState)).toJs))

  // Headers
  def getFlatHeaders(): List[Header[T, Any, TM, CM, Any, Any]] =
    toJs.getFlatHeaders().toList.map(Header(_))
  def getFooterGroups(): List[HeaderGroup[T, TM, CM]]          =
    toJs.getFooterGroups().toList.map(HeaderGroup(_))
  def getHeaderGroups(): List[HeaderGroup[T, TM, CM]]          =
    toJs.getHeaderGroups().toList.map(HeaderGroup(_))
  def getLeafHeaders(): List[Header[T, Any, TM, CM, Any, Any]] =
    toJs.getLeafHeaders().toList.map(Header(_))

  // Visibility
  def getCenterVisibleLeafColumns(): List[Column[T, Any, TM, CM, Any, Any]]        =
    toJs.getCenterVisibleLeafColumns().toList.map(Column(_))
  def getIsAllColumnsVisible(): Boolean                                            = toJs.getIsAllColumnsVisible()
  def getIsSomeColumnsVisible(): Boolean                                           = toJs.getIsSomeColumnsVisible()
  def getLeftVisibleLeafColumns(): List[Column[T, Any, TM, CM, Any, Any]]          =
    toJs.getLeftVisibleLeafColumns().toList.map(Column(_))
  def getRightVisibleLeafColumns(): List[Column[T, Any, TM, CM, Any, Any]]         =
    toJs.getRightVisibleLeafColumns().toList.map(Column(_))
  def getToggleAllColumnsVisibilityHandler(): SyntheticEvent[dom.Node] => Callback =
    e => Callback(toJs.getToggleAllColumnsVisibilityHandler()(e))
  def getVisibleFlatColumns(): List[Column[T, Any, TM, CM, Any, Any]]              =
    toJs.getVisibleFlatColumns().toList.map(Column(_))
  def getVisibleLeafColumns(): List[Column[T, Any, TM, CM, Any, Any]]              =
    toJs.getVisibleLeafColumns().toList.map(Column(_))
  def resetColumnVisibility(): Callback                                            = Callback(toJs.resetColumnVisibility())
  def resetColumnVisibility(defaultState: Boolean): Callback                       =
    Callback(toJs.resetColumnVisibility(defaultState))
  def setColumnVisibility(value: ColumnVisibility): Callback                       =
    Callback(toJs.setColumnVisibility(value.toJs))
  def modColumnVisibility(f: Endo[ColumnVisibility]): Callback                     =
    Callback(toJs.setColumnVisibility(v => f(ColumnVisibility.fromJs(v)).toJs))
  def toggleAllColumnsVisible(): Callback                                          = Callback(toJs.toggleAllColumnsVisible())
  def toggleAllColumnsVisible(value: Boolean): Callback                            =
    Callback(toJs.toggleAllColumnsVisible(value))

  // Sorting
  def getPreSortedRowModel(): RowModel[T, TM, CM]   = RowModel(toJs.getPreSortedRowModel())
  def getSortedRowModel(): RowModel[T, TM, CM]      = RowModel(toJs.getSortedRowModel())
  def resetSorting(): Callback                      = Callback(toJs.resetSorting())
  def resetSorting(defaultState: Boolean): Callback = Callback(toJs.resetSorting(defaultState))
  def setSorting(value:          Sorting): Callback = Callback(toJs.setSorting(value.toJs))
  def modSorting(f: Endo[Sorting]): Callback        =
    Callback(toJs.setSorting(v => f(Sorting.fromJs(v)).toJs))

  // ColumnSizing
  def getCenterTotalSize(): SizePx                             = SizePx(toJs.getCenterTotalSize().toInt)
  def getLeftTotalSize(): SizePx                               = SizePx(toJs.getLeftTotalSize().toInt)
  def getRightTotalSize(): SizePx                              = SizePx(toJs.getRightTotalSize().toInt)
  def getTotalSize(): SizePx                                   = SizePx(toJs.getTotalSize().toInt)
  def resetColumnSizing(): Callback                            = Callback(toJs.resetColumnSizing())
  def resetColumnSizing(defaultState: Boolean): Callback       =
    Callback(toJs.resetColumnSizing(defaultState))
  def resetHeaderSizeInfo(): Callback                          = Callback(toJs.resetHeaderSizeInfo())
  def resetHeaderSizeInfo(defaultState: Boolean): Callback     =
    Callback(toJs.resetHeaderSizeInfo(defaultState))
  def setColumnSizing(value: ColumnSizing): Callback           = Callback(toJs.setColumnSizing(value.toJs))
  def modColumnSizing(f: Endo[ColumnSizing]): Callback         =
    Callback(toJs.setColumnSizing(v => f(ColumnSizing.fromJs(v)).toJs))
  def setColumnSizingInfo(value: ColumnSizingInfo): Callback   =
    Callback(toJs.setColumnSizingInfo(value.toJs))
  def modColumnSizingInfo(f: Endo[ColumnSizingInfo]): Callback =
    Callback(toJs.setColumnSizingInfo(v => f(ColumnSizingInfo.fromJs(v)).toJs))

  // Expanded
  def getCanSomeRowsExpand(): Boolean                                         = toJs.getCanSomeRowsExpand()
  def getExpandedDepth(): Int                                                 = toJs.getExpandedDepth().toInt
  def getExpandedRowModel(): RowModel[T, TM, CM]                              = RowModel(toJs.getExpandedRowModel())
  def getIsAllRowsExpanded(): Boolean                                         = toJs.getIsAllRowsExpanded()
  def getIsSomeRowsExpanded(): Boolean                                        = toJs.getIsSomeRowsExpanded()
  def getPreExpandedRowModel(): RowModel[T, TM, CM]                           = RowModel(toJs.getPreExpandedRowModel())
  def getToggleAllRowsExpandedHandler(): SyntheticEvent[dom.Node] => Callback =
    e => Callback(toJs.getToggleAllRowsExpandedHandler()(e))
  def resetExpanded(): Callback                                               = Callback(toJs.resetExpanded())
  def resetExpanded(defaultState: Boolean): Callback                          = Callback(toJs.resetExpanded(defaultState))
  def setExpanded(value:          Expanded): Callback                         = Callback(toJs.setExpanded(value.toJs))
  def modExpanded(f: Endo[Expanded]): Callback                                =
    Callback(toJs.setExpanded(v => f(Expanded.fromJs(v)).toJs))
  def toggleAllRowsExpanded(): Callback                                       = Callback(toJs.toggleAllRowsExpanded())
  def toggleAllRowsExpanded(expanded: Boolean): Callback                      =
    Callback(toJs.toggleAllRowsExpanded(expanded))

  // RowSelection
  def getFilteredSelectedRowModel(): RowModel[T, TM, CM]                          = RowModel(
    toJs.getFilteredSelectedRowModel()
  )
  def getGroupedSelectedRowModel(): RowModel[T, TM, CM]                           = RowModel(
    toJs.getGroupedSelectedRowModel()
  )
  def getIsAllPageRowsSelected(): Boolean                                         = toJs.getIsAllPageRowsSelected()
  def getIsAllRowsSelected(): Boolean                                             = toJs.getIsAllRowsSelected()
  def getIsSomePageRowsSelected(): Boolean                                        = toJs.getIsSomePageRowsSelected()
  def getIsSomeRowsSelected(): Boolean                                            = toJs.getIsSomeRowsSelected()
  def getPreSelectedRowModel(): RowModel[T, TM, CM]                               = RowModel(toJs.getPreSelectedRowModel())
  def getSelectedRowModel(): RowModel[T, TM, CM]                                  = RowModel(toJs.getSelectedRowModel())
  def getToggleAllPageRowsSelectedHandler(): SyntheticEvent[dom.Node] => Callback =
    e => Callback(toJs.getToggleAllPageRowsSelectedHandler()(e))
  def getToggleAllRowsSelectedHandler(): SyntheticEvent[dom.Node] => Callback     =
    e => Callback(toJs.getToggleAllRowsSelectedHandler()(e))
  def resetRowSelection(): Callback                                               = Callback(toJs.resetRowSelection())
  def resetRowSelection(defaultState: Boolean): Callback                          =
    Callback(toJs.resetRowSelection(defaultState))
  def setRowSelection(value: RowSelection): Callback                              = Callback(toJs.setRowSelection(value.toJs))
  def modRowSelection(f: Endo[RowSelection]): Callback                            =
    Callback(toJs.setRowSelection(v => f(RowSelection.fromJs(v)).toJs))
  def toggleAllPageRowsSelected(): Callback                                       = Callback(toJs.toggleAllPageRowsSelected())
  def toggleAllPageRowsSelected(value: Boolean): Callback                         =
    Callback(toJs.toggleAllPageRowsSelected(value))
  def toggleAllRowsSelected(): Callback                                           = Callback(toJs.toggleAllRowsSelected())
  def toggleAllRowsSelected(value: Boolean): Callback                             =
    Callback(toJs.toggleAllRowsSelected(value))

  // Column Pinning
  def setColumnPinning(value: ColumnPinning): Callback                 = Callback(toJs.setColumnPinning(value.toJs))
  def modColumnPinning(f: Endo[ColumnPinning]): Callback               =
    Callback(toJs.setColumnPinning(v => f(ColumnPinning.fromJs(v)).toJs))
  def resetColumnPinning(): Callback                                   = Callback(toJs.resetColumnPinning())
  def resetColumnPinning(defaultState: Boolean): Callback              =
    Callback(toJs.resetColumnPinning(defaultState))
  def getIsSomeColumnsPinned(): Boolean                                = toJs.getIsSomeColumnsPinned()
  def getIsSomeColumnsPinned(position: ColumnPinningPosition): Boolean =
    toJs.getIsSomeColumnsPinned(position.toJs)
  def getCenterFlatHeaders(): List[Header[T, Any, TM, CM, Any, Any]]   =
    toJs.getCenterFlatHeaders().toList.map(Header(_))
  def getCenterFooterGroups(): List[HeaderGroup[T, TM, CM]]            =
    toJs.getCenterFooterGroups().toList.map(HeaderGroup(_))
  def getCenterHeaderGroups(): List[HeaderGroup[T, TM, CM]]            =
    toJs.getCenterHeaderGroups().toList.map(HeaderGroup(_))
  def getCenterLeafHeaders(): List[Header[T, Any, TM, CM, Any, Any]]   =
    toJs.getCenterLeafHeaders().toList.map(Header(_))
  def getLeftFlatHeaders(): List[Header[T, Any, TM, CM, Any, Any]]     =
    toJs.getLeftFlatHeaders().toList.map(Header(_))
  def getLeftFooterGroups(): List[HeaderGroup[T, TM, CM]]              =
    toJs.getLeftFooterGroups().toList.map(HeaderGroup(_))
  def getLeftHeaderGroups(): List[HeaderGroup[T, TM, CM]]              =
    toJs.getLeftHeaderGroups().toList.map(HeaderGroup(_))
  def getLeftLeafHeaders(): List[Header[T, Any, TM, CM, Any, Any]]     =
    toJs.getLeftLeafHeaders().toList.map(Header(_))
  def getRightFlatHeaders(): List[Header[T, Any, TM, CM, Any, Any]]    =
    toJs.getRightFlatHeaders().toList.map(Header(_))
  def getRightFooterGroups(): List[HeaderGroup[T, TM, CM]]             =
    toJs.getRightFooterGroups().toList.map(HeaderGroup(_))
  def getRightHeaderGroups(): List[HeaderGroup[T, TM, CM]]             =
    toJs.getRightHeaderGroups().toList.map(HeaderGroup(_))
  def getRightLeafHeaders(): List[Header[T, Any, TM, CM, Any, Any]]    =
    toJs.getRightLeafHeaders().toList.map(Header(_))
  // Row Pinning
  def setRowPinning(value:    RowPinning): Callback                    = Callback(toJs.setRowPinning(value.toJs))
  def modRowPinning(f: Endo[RowPinning]): Callback                     = Callback:
    toJs.setRowPinning(v => f(RowPinning.fromJs(v)).toJs)
  def resetRowPinning(): Callback                                      = Callback(toJs.resetRowPinning())
  def resetRowPinning(defaultState: Boolean): Callback                 = Callback(
    toJs.resetRowPinning(defaultState)
  )
  def getIsSomeRowsPinned(): Boolean                                   = toJs.getIsSomeRowsPinned()
  def getIsSomeRowsPinned(position: RowPinningPosition): Boolean       =
    toJs.getIsSomeRowsPinned(position.toJs)
  def getTopRows(): List[Row[T, TM, CM]]                               =
    toJs.getTopRows().toList.map(Row(_))
  def getBottomRows(): List[Row[T, TM, CM]]                            =
    toJs.getBottomRows().toList.map(Row(_))
  def getCenterRows(): List[Row[T, TM, CM]]                            =
    toJs.getCenterRows().toList.map(Row(_))

  // Column Filtering
  def setColumnFilters(value:          ColumnFilters): Callback   = Callback(toJs.setColumnFilters(value.toJs))
  def modColumnFilters(f: Endo[ColumnFilters]): Callback          = Callback:
    toJs.setColumnFilters(v => f(ColumnFilters.fromJs(v)).toJs)
  def resetColumnFilters()                                        = toJs.resetColumnFilters()
  def resetColumnFilters(defaultState: Boolean)                   = toJs.resetColumnFilters(defaultState)
  def getPreFilteredRowModel(): RowModel[T, TM, CM]               = RowModel(toJs.getPreFilteredRowModel())
  def getFilteredRowModel(): RowModel[T, TM, CM]                  = RowModel(toJs.getFilteredRowModel())
