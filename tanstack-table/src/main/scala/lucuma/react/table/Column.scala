// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import japgolly.scalajs.react.callback.Callback
import japgolly.scalajs.react.facade.SyntheticEvent
import lucuma.react.SizePx
import lucuma.react.table.facade.ColumnDefJs
import lucuma.typed.tanstackTableCore as raw
import org.scalajs.dom

import scalajs.js.JSConverters.*
import scalajs.js

// Missing: Grouping
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
 * @tparam CF
 *   The type of the column filter.
 * @tparam FM
 *   The type of the filter metadata (column specific).
 */
case class Column[T, A, TM, CM, TF, CF, FM] private[table] (
  val toJs: raw.buildLibTypesMod.Column[T, A]
):
  lazy val id: ColumnId                                            = ColumnId(toJs.id)
  lazy val depth: Int                                              = toJs.depth.toInt
  lazy val accessorFn: Option[(T, Int) => A]                       =
    toJs.accessorFn.toOption.map(f => (row, index) => f(row, index))
  lazy val columnDef: ColumnDef[T, A, TM, CM, TF, CF, FM]          =
    ColumnDef.fromJs(toJs.columnDef.asInstanceOf[ColumnDefJs[T, A, CM]])
  lazy val columns: List[Column[T, Any, TM, CM, TF, Any, Any]]     =
    toJs.columns.toList.map(col => Column(col.asInstanceOf[raw.buildLibTypesMod.Column[T, Any]]))
  lazy val parent: Option[Column[T, Any, TM, CM, TF, Any, Any]]    =
    toJs.parent.toOption.map(col => Column(col.asInstanceOf[raw.buildLibTypesMod.Column[T, Any]]))
  def getFlatColumns(): List[Column[T, Any, TM, CM, TF, Any, Any]] =
    toJs
      .getFlatColumns()
      .toList
      .map(col => Column(col.asInstanceOf[raw.buildLibTypesMod.Column[T, Any]]))
  def getLeafColumns(): List[Column[T, Any, TM, CM, TF, Any, Any]] =
    toJs
      .getLeafColumns()
      .toList
      .map(col => Column(col.asInstanceOf[raw.buildLibTypesMod.Column[T, Any]]))

  // Column Visibility
  def getCanHide(): Boolean                                              = toJs.getCanHide()
  def getIsVisible(): Boolean                                            = toJs.getIsVisible()
  def getToggleVisibilityHandler(): SyntheticEvent[dom.Node] => Callback =
    e => Callback(toJs.getToggleVisibilityHandler()(e))
  def toggleVisibility(): Callback                                       = Callback(toJs.toggleVisibility())
  def toggleVisibility(value: Boolean): Callback                         = Callback(toJs.toggleVisibility(value))

  // Sorting
  def clearSorting(): Callback                                                    = Callback(toJs.clearSorting())
  def getAutoSortDir(): SortDirection                                             =
    SortDirection.fromDescending(toJs.getAutoSortDir() == raw.tanstackTableCoreStrings.desc)
  def getAutoSortingFn(): SortingFn[T, TM, CM, TF]                                =
    (rowA, rowB, colId) => toJs.getAutoSortingFn()(rowA.toJs, rowB.toJs, colId.value).toInt
  def getCanMultiSort(): Boolean                                                  = toJs.getCanMultiSort()
  def getCanSort(): Boolean                                                       = toJs.getCanSort()
  def getFirstSortDir(): SortDirection                                            = SortDirection.fromDescending(
    toJs.getFirstSortDir() == raw.tanstackTableCoreStrings.desc
  )
  def getIsSorted(): Option[SortDirection]                                        =
    Some(toJs.getIsSorted())
      .filterNot(_ == raw.tanstackTableCoreBooleans.`false`)
      .map(dir => SortDirection.fromDescending(dir == raw.tanstackTableCoreStrings.desc))
  def getNextSortingOrder(): Option[SortDirection]                                =
    Some(toJs.getNextSortingOrder())
      .filterNot(_ == raw.tanstackTableCoreBooleans.`false`)
      .map(dir => SortDirection.fromDescending(dir == raw.tanstackTableCoreStrings.desc))
  def getSortIndex(): Int                                                         = toJs.getSortIndex().toInt
  def getSortingFn(): SortingFn[T, TM, CM, TF]                                    =
    (rowA, rowB, colId) => toJs.getSortingFn()(rowA.toJs, rowB.toJs, colId.value).toInt
  def getToggleSortingHandler(): Option[SyntheticEvent[dom.Node] => Callback]     =
    toJs.getToggleSortingHandler().toOption.map(fn => e => Callback(fn(e)))
  def toggleSorting(): Callback                                                   = Callback(toJs.toggleSorting())
  def toggleSorting(dir:     SortDirection): Callback                             = Callback(toJs.toggleSorting(dir.toDescending))
  def toggleSorting(dir: SortDirection, isMulti: Boolean): Callback               =
    Callback(toJs.toggleSorting(dir.toDescending, isMulti))
  def toggleSorting(isMulti: Boolean): Callback                                   = Callback(toJs.toggleSorting((), isMulti))

  // Column Sizing
  def getCanResize(): Boolean  = toJs.getCanResize()
  def getIsResizing(): Boolean = toJs.getIsResizing()
  def getSize(): SizePx        = SizePx(toJs.getSize().toInt)
  def getStart(): SizePx       = SizePx(toJs.getStart().toInt)
  // def getStart(position: ColumnPinningPosition): Double = js.native
  def resetSize(): Callback    = Callback(toJs.resetSize())

  // Column Pinning
  def getCanPin(): Boolean                           = toJs.getCanPin()
  def getPinnedIndex(): Int                          = toJs.getPinnedIndex().toInt
  def getIsPinned(): Option[ColumnPinningPosition]   =
    ColumnPinningPosition.fromJs(toJs.getIsPinned())
  def pin(position: ColumnPinningPosition): Callback = Callback(toJs.pin(position.toJs))
  def unpin(): Callback                              = Callback(
    toJs.pin(raw.buildLibFeaturesColumnPinningMod.ColumnPinningPosition.`false`)
  )

  // Column Filtering
  def getCanFilter(): Boolean                                        = toJs.getCanFilter()
  def getFilterIndex(): Int                                          = toJs.getFilterIndex().toInt
  def getIsFiltered(): Boolean                                       = toJs.getIsFiltered()
  def getFilterValue(): Option[CF]                                   =
    toJs.getFilterValue().asInstanceOf[js.UndefOr[CF]].toOption
  def setFilterValue(value: Option[CF]): Callback                    = Callback(toJs.setFilterValue(value.orUndefined))
  def modFilterValue(f: Option[CF] => Option[CF]): Callback          = Callback(
    toJs.setFilterValue((v: js.UndefOr[CF]) => f(v.toOption).orUndefined)
  )
  def getAutoFilterFn(): Option[FilterFn[T, TM, CM, TF, CF, FM]]     =
    toJs
      .getAutoFilterFn()
      .toOption
      .map(FilterFn.fromJs(_))
  def getFilterFn[CF, FM](): Option[FilterFn[T, TM, CM, TF, CF, FM]] =
    toJs
      .getFilterFn()
      .toOption
      .map(FilterFn.fromJs(_))

  // Global Filtering
  def getCanGlobalFilter(): Boolean = toJs.getCanGlobalFilter()

  // Column Faceting
  def getFacetedRowModel(): RowModel[T, TM, CM, TF]      = RowModel(toJs.getFacetedRowModel())
  def getFacetedUniqueValues(): Map[A, Int]              =
    Map.fromJsMap(toJs.getFacetedUniqueValues()).map((k, v) => (k.asInstanceOf[A], v.toInt))
  def getFacetedMinMaxValues(): Option[(Double, Double)] =
    toJs.getFacetedMinMaxValues().toOption.map(identity) // Force type cast
