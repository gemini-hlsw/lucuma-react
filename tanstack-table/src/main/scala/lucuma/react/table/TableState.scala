// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import lucuma.typed.tanstackTableCore as raw

import scalajs.js
import scalajs.js.JSConverters.*

// Still missing (everywhere):  grouping, pagination
case class TableState[TF](private[table] val toJs: raw.buildLibTypesMod.TableState):
  lazy val columnVisibility: ColumnVisibility = ColumnVisibility.fromJs(toJs.columnVisibility)

  /** WARNING: This mutates the object in-place. */
  def setColumnVisibility(columnVisibility: ColumnVisibility): TableState[TF] =
    TableState(toJs.setColumnVisibility(columnVisibility.toJs))

  lazy val columnOrder: ColumnOrder = ColumnOrder.fromJs(toJs.columnOrder)

  /** WARNING: This mutates the object in-place. */
  def setColumnOrder(columnOrder: ColumnOrder): TableState[TF] =
    TableState(toJs.setColumnOrder(columnOrder.toJs))

  lazy val columnPinning: ColumnPinning = ColumnPinning.fromJs(toJs.columnPinning)

  /** WARNING: This mutates the object in-place. */
  def setColumnPinning(columnPinning: ColumnPinning): TableState[TF] =
    TableState(toJs.setColumnPinning(columnPinning.toJs))

  lazy val rowPinning: RowPinning = RowPinning.fromJs(toJs.rowPinning)

  /** WARNING: This mutates the object in-place. */
  def setRowPinning(rowPinning: RowPinning): TableState[TF] =
    TableState(toJs.setRowPinning(rowPinning.toJs))

  lazy val sorting: Sorting = Sorting.fromJs(toJs.sorting)

  /** WARNING: This mutates the object in-place. */
  def setSorting(sorting: Sorting): TableState[TF] =
    TableState(toJs.setSorting(sorting.toJs))

  lazy val expanded: Expanded = Expanded.fromJs(toJs.expanded)

  /** WARNING: This mutates the object in-place. */
  def setExpanded(expanded: Expanded): TableState[TF] =
    TableState(toJs.setExpanded(expanded.toJs))

  lazy val columnSizing: ColumnSizing = ColumnSizing.fromJs(toJs.columnSizing)

  /** WARNING: This mutates the object in-place. */
  def setColumnSizing(columnSizing: ColumnSizing): TableState[TF] =
    TableState(toJs.setColumnSizing(columnSizing.toJs))

  lazy val columnSizingInfo: ColumnSizingInfo = ColumnSizingInfo.fromJs(toJs.columnSizingInfo)

  /** WARNING: This mutates the object in-place. */
  def setColumnSizingInfo(columnSizingInfo: ColumnSizingInfo): TableState[TF] =
    TableState(toJs.setColumnSizingInfo(columnSizingInfo.toJs))

  lazy val rowSelection: RowSelection = RowSelection.fromJs(toJs.rowSelection)

  /** WARNING: This mutates the object in-place. */
  def setRowSelection(rowSelection: RowSelection): TableState[TF] =
    TableState(toJs.setRowSelection(rowSelection.toJs))

  lazy val columnFilters: ColumnFilters = ColumnFilters.fromJs(toJs.columnFilters)

  /** WARNING: This mutates the object in-place. */
  def setColumnFilters(columnFilters: ColumnFilters): TableState[TF] =
    TableState(toJs.setColumnFilters(columnFilters.toJs))

  lazy val globalFilter: Option[TF] = toJs.globalFilter.asInstanceOf[js.UndefOr[TF]].toOption

  /** WARNING: This mutates the object in-place. */
  def setGlobalFilter(globalFilter: Option[TF]): TableState[TF] =
    TableState(toJs.setGlobalFilter(globalFilter.orUndefined))

object TableState:
  def apply[TF](
    columnVisibility: js.UndefOr[ColumnVisibility] = js.undefined,
    columnOrder:      js.UndefOr[ColumnOrder] = js.undefined,
    columnPinning:    js.UndefOr[ColumnPinning] = js.undefined,
    rowPinning:       js.UndefOr[RowPinning] = js.undefined,
    sorting:          js.UndefOr[Sorting] = js.undefined,
    expanded:         js.UndefOr[Expanded] = js.undefined,
    columnSizing:     js.UndefOr[ColumnSizing] = js.undefined,
    columnSizingInfo: js.UndefOr[ColumnSizingInfo] = js.undefined,
    rowSelection:     js.UndefOr[RowSelection] = js.undefined,
    columnFilters:    js.UndefOr[ColumnFilters] = js.undefined,
    globalFilter:     js.UndefOr[TF] = js.undefined
  ): TableState[TF] = TableState(
    raw.buildLibTypesMod
      .InitialTableState()
      .applyOrNot(columnVisibility, (s, p) => s.setColumnVisibility(p.toJs))
      .applyOrNot(columnOrder, (s, p) => s.setColumnOrder(p.toJs))
      .applyOrNot(columnPinning, (s, p) => s.setColumnPinning(p.toJs))
      .applyOrNot(rowPinning, (s, p) => s.setRowPinning(p.toJs))
      .applyOrNot(sorting, (s, p) => s.setSorting(p.toJs))
      .applyOrNot(expanded, (s, p) => s.setExpanded(p.toJs))
      .applyOrNot(columnSizing, (s, p) => s.setColumnSizing(p.toJs))
      .applyOrNot(columnSizingInfo, (s, p) => s.setColumnSizingInfo(p.toJs))
      .applyOrNot(rowSelection, (s, p) => s.setRowSelection(p.toJs))
      .applyOrNot(columnFilters, (s, p) => s.setColumnFilters(p.toJs))
      .applyOrNot(globalFilter, (s, p) => s.setGlobalFilter(p))
      .asInstanceOf[raw.buildLibTypesMod.TableState]
  )
