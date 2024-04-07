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

// Missing: ColumnOrder, ColumnPinning, Filters, Grouping, Pagination
case class Table[T](private[table] val toJs: raw.buildLibTypesMod.Table[T]):
  def getAllColumns(): List[Column[T, Any]]               = toJs.getAllColumns().toList.map(Column(_))
  def getAllFlatColumns(): List[Column[T, Any]]           = toJs.getAllFlatColumns().toList.map(Column(_))
  def getAllLeafColumns(): List[Column[T, Any]]           = toJs.getAllLeafColumns().toList.map(Column(_))
  def getColumn(columnId: String): Option[Column[T, Any]] =
    toJs.getColumn(columnId).toOption.map(Column(_))
  def getCoreRowModel(): raw.buildLibTypesMod.RowModel[T] = toJs.getCoreRowModel()
  def getRow(id: String): Row[T] = Row(toJs.getRow(id))
  def getRowModel(): raw.buildLibTypesMod.RowModel[T] = toJs.getRowModel()
  def getState(): TableState                          = TableState(toJs.getState())
  lazy val initialState: TableState                   = TableState(toJs.initialState)
  lazy val options: TableOptions[T]                   =
    TableOptions.fromJs(toJs.options.asInstanceOf[TableOptionsJs[T]])
  def reset(): Callback                               = Callback(toJs.reset())
  def setState(value: TableState): Callback = Callback(toJs.setState(value.toJs))
  def modState(f: Endo[TableState]): Callback =
    Callback(toJs.setState(rawState => f(TableState(rawState)).toJs))

  // Headers
  def getCenterFlatHeaders(): List[raw.buildLibTypesMod.Header[T, Any]]  =
    toJs.getCenterFlatHeaders().toList
  def getCenterFooterGroups(): List[raw.buildLibTypesMod.HeaderGroup[T]] =
    toJs.getCenterFooterGroups().toList
  def getCenterHeaderGroups(): List[raw.buildLibTypesMod.HeaderGroup[T]] =
    toJs.getCenterHeaderGroups().toList
  def getCenterLeafHeaders(): List[raw.buildLibTypesMod.Header[T, Any]]  =
    toJs.getCenterLeafHeaders().toList
  def getFlatHeaders(): List[raw.buildLibTypesMod.Header[T, Any]]        = toJs.getFlatHeaders().toList
  def getFooterGroups(): List[raw.buildLibTypesMod.HeaderGroup[T]]       = toJs.getFooterGroups().toList
  def getHeaderGroups(): List[raw.buildLibTypesMod.HeaderGroup[T]]       = toJs.getHeaderGroups().toList
  def getLeafHeaders(): List[raw.buildLibTypesMod.Header[T, Any]]        = toJs.getLeafHeaders().toList
  def getLeftFlatHeaders(): List[raw.buildLibTypesMod.Header[T, Any]]    =
    toJs.getLeftFlatHeaders().toList
  def getLeftFooterGroups(): List[raw.buildLibTypesMod.HeaderGroup[T]]   =
    toJs.getLeftFooterGroups().toList
  def getLeftHeaderGroups(): List[raw.buildLibTypesMod.HeaderGroup[T]]   =
    toJs.getLeftHeaderGroups().toList
  def getLeftLeafHeaders(): List[raw.buildLibTypesMod.Header[T, Any]]    =
    toJs.getLeftLeafHeaders().toList
  def getRightFlatHeaders(): List[raw.buildLibTypesMod.Header[T, Any]]   =
    toJs.getRightFlatHeaders().toList
  def getRightFooterGroups(): List[raw.buildLibTypesMod.HeaderGroup[T]]  =
    toJs.getRightFooterGroups().toList
  def getRightHeaderGroups(): List[raw.buildLibTypesMod.HeaderGroup[T]]  =
    toJs.getRightHeaderGroups().toList
  def getRightLeafHeaders(): List[raw.buildLibTypesMod.Header[T, Any]]   =
    toJs.getRightLeafHeaders().toList

  // Visibility
  def getCenterVisibleLeafColumns(): List[Column[T, Any]]                          =
    toJs.getCenterVisibleLeafColumns().toList.map(Column(_))
  def getIsAllColumnsVisible(): Boolean                                            = toJs.getIsAllColumnsVisible()
  def getIsSomeColumnsVisible(): Boolean                                           = toJs.getIsSomeColumnsVisible()
  def getLeftVisibleLeafColumns(): List[Column[T, Any]]                            =
    toJs.getLeftVisibleLeafColumns().toList.map(Column(_))
  def getRightVisibleLeafColumns(): List[Column[T, Any]]                           =
    toJs.getRightVisibleLeafColumns().toList.map(Column(_))
  def getToggleAllColumnsVisibilityHandler(): SyntheticEvent[dom.Node] => Callback =
    e => Callback(toJs.getToggleAllColumnsVisibilityHandler()(e))
  def getVisibleFlatColumns(): List[Column[T, Any]]                                =
    toJs.getVisibleFlatColumns().toList.map(Column(_))
  def getVisibleLeafColumns(): List[Column[T, Any]]                                =
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
  def getPreSortedRowModel(): raw.buildLibTypesMod.RowModel[T] = toJs.getPreSortedRowModel()
  def getSortedRowModel(): raw.buildLibTypesMod.RowModel[T]    = toJs.getSortedRowModel()
  def resetSorting(): Callback                                 = Callback(toJs.resetSorting())
  def resetSorting(defaultState: Boolean): Callback = Callback(toJs.resetSorting(defaultState))
  def setSorting(value:          Sorting): Callback = Callback(toJs.setSorting(value.toJs))
  def modSorting(f: Endo[Sorting]): Callback =
    Callback(toJs.setSorting(v => f(Sorting.fromJs(v)).toJs))

  // ColumnSizing
  def getCenterTotalSize(): SizePx                         = SizePx(toJs.getCenterTotalSize().toInt)
  def getLeftTotalSize(): SizePx                           = SizePx(toJs.getLeftTotalSize().toInt)
  def getRightTotalSize(): SizePx                          = SizePx(toJs.getRightTotalSize().toInt)
  def getTotalSize(): SizePx                               = SizePx(toJs.getTotalSize().toInt)
  def resetColumnSizing(): Callback                        = Callback(toJs.resetColumnSizing())
  def resetColumnSizing(defaultState: Boolean): Callback   =
    Callback(toJs.resetColumnSizing(defaultState))
  def resetHeaderSizeInfo(): Callback                      = Callback(toJs.resetHeaderSizeInfo())
  def resetHeaderSizeInfo(defaultState: Boolean): Callback =
    Callback(toJs.resetHeaderSizeInfo(defaultState))
  def setColumnSizing(value: ColumnSizing): Callback = Callback(toJs.setColumnSizing(value.toJs))
  def modColumnSizing(f: Endo[ColumnSizing]): Callback         =
    Callback(toJs.setColumnSizing(v => f(ColumnSizing.fromJs(v)).toJs))
  def setColumnSizingInfo(value: ColumnSizingInfo): Callback   =
    Callback(toJs.setColumnSizingInfo(value.toJs))
  def modColumnSizingInfo(f: Endo[ColumnSizingInfo]): Callback =
    Callback(toJs.setColumnSizingInfo(v => f(ColumnSizingInfo.fromJs(v)).toJs))

  // Expanded
  def getCanSomeRowsExpand(): Boolean                                         = toJs.getCanSomeRowsExpand()
  def getExpandedDepth(): Int                                                 = toJs.getExpandedDepth().toInt
  def getExpandedRowModel(): raw.buildLibTypesMod.RowModel[T]                 = toJs.getExpandedRowModel()
  def getIsAllRowsExpanded(): Boolean                                         = toJs.getIsAllRowsExpanded()
  def getIsSomeRowsExpanded(): Boolean                                        = toJs.getIsSomeRowsExpanded()
  def getPreExpandedRowModel(): raw.buildLibTypesMod.RowModel[T]              = toJs.getPreExpandedRowModel()
  def getToggleAllRowsExpandedHandler(): SyntheticEvent[dom.Node] => Callback =
    e => Callback(toJs.getToggleAllRowsExpandedHandler()(e))
  def resetExpanded(): Callback                                               = Callback(toJs.resetExpanded())
  def resetExpanded(defaultState: Boolean): Callback  = Callback(toJs.resetExpanded(defaultState))
  def setExpanded(value:          Expanded): Callback = Callback(toJs.setExpanded(value.toJs))
  def modExpanded(f: Endo[Expanded]): Callback           =
    Callback(toJs.setExpanded(v => f(Expanded.fromJs(v)).toJs))
  def toggleAllRowsExpanded(): Callback                  = Callback(toJs.toggleAllRowsExpanded())
  def toggleAllRowsExpanded(expanded: Boolean): Callback =
    Callback(toJs.toggleAllRowsExpanded(expanded))

  // RowSelection
  def getFilteredSelectedRowModel(): raw.buildLibTypesMod.RowModel[T]             =
    toJs.getFilteredSelectedRowModel()
  def getGroupedSelectedRowModel(): raw.buildLibTypesMod.RowModel[T]              =
    toJs.getGroupedSelectedRowModel()
  def getIsAllPageRowsSelected(): Boolean                                         = toJs.getIsAllPageRowsSelected()
  def getIsAllRowsSelected(): Boolean                                             = toJs.getIsAllRowsSelected()
  def getIsSomePageRowsSelected(): Boolean                                        = toJs.getIsSomePageRowsSelected()
  def getIsSomeRowsSelected(): Boolean                                            = toJs.getIsSomeRowsSelected()
  def getPreSelectedRowModel(): raw.buildLibTypesMod.RowModel[T]                  = toJs.getPreSelectedRowModel()
  def getSelectedRowModel(): raw.buildLibTypesMod.RowModel[T]                     = toJs.getSelectedRowModel()
  def getToggleAllPageRowsSelectedHandler(): SyntheticEvent[dom.Node] => Callback =
    e => Callback(toJs.getToggleAllPageRowsSelectedHandler()(e))
  def getToggleAllRowsSelectedHandler(): SyntheticEvent[dom.Node] => Callback     =
    e => Callback(toJs.getToggleAllRowsSelectedHandler()(e))
  def resetRowSelection(): Callback                                               = Callback(toJs.resetRowSelection())
  def resetRowSelection(defaultState: Boolean): Callback                          =
    Callback(toJs.resetRowSelection(defaultState))
  def setRowSelection(value: RowSelection): Callback = Callback(toJs.setRowSelection(value.toJs))
  def modRowSelection(f: Endo[RowSelection]): Callback    =
    Callback(toJs.setRowSelection(v => f(RowSelection.fromJs(v)).toJs))
  def toggleAllPageRowsSelected(): Callback               = Callback(toJs.toggleAllPageRowsSelected())
  def toggleAllPageRowsSelected(value: Boolean): Callback =
    Callback(toJs.toggleAllPageRowsSelected(value))
  def toggleAllRowsSelected(): Callback                   = Callback(toJs.toggleAllRowsSelected())
  def toggleAllRowsSelected(value: Boolean): Callback     =
    Callback(toJs.toggleAllRowsSelected(value))
