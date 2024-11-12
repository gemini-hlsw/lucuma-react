// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import lucuma.typed.tanstackTableCore as raw

import scalajs.js
import scalajs.js.JSConverters.*

// Still missing (everywhere): filters, grouping, pagination
case class PartialTableState(private[table] val toJs: raw.anon.PartialTableState):
  lazy val columnVisibility: Option[ColumnVisibility] =
    toJs.columnVisibility.toOption.map(ColumnVisibility.fromJs)

  /** WARNING: This mutates the object in-place. */
  def setColumnVisibility(columnVisibility: Option[ColumnVisibility]): PartialTableState =
    PartialTableState(
      columnVisibility.fold(toJs.setColumnVisibilityUndefined)(v =>
        toJs.setColumnVisibility(v.toJs)
      )
    )

  lazy val columnOrder: Option[ColumnOrder] =
    toJs.columnOrder.toOption.map(ColumnOrder.fromJs)

  /** WARNING: This mutates the object in-place. */
  def setColumnOrder(columnOrder: Option[ColumnOrder]): PartialTableState =
    PartialTableState(
      columnOrder.fold(toJs.setColumnOrderUndefined)(v => toJs.setColumnOrder(v.toJs))
    )

  lazy val columnPinning: Option[ColumnPinning] =
    toJs.columnPinning.toOption.map(ColumnPinning.fromJs)

  /** WARNING: This mutates the object in-place. */
  def setColumnPinning(columnPinning: Option[ColumnPinning]): PartialTableState =
    PartialTableState(
      columnPinning.fold(toJs.setColumnPinningUndefined)(v => toJs.setColumnPinning(v.toJs))
    )

  lazy val rowPinning: Option[RowPinning] =
    toJs.rowPinning.toOption.map(RowPinning.fromJs)

  /** WARNING: This mutates the object in-place. */
  def setRowPinning(columnPinning: Option[RowPinning]): PartialTableState =
    PartialTableState(
      rowPinning.fold(toJs.setRowPinningUndefined)(v => toJs.setRowPinning(v.toJs))
    )

  lazy val sorting: Option[Sorting] =
    toJs.sorting.toOption.map(Sorting.fromJs)

  /** WARNING: This mutates the object in-place. */
  def setSorting(sorting: Option[Sorting]): PartialTableState =
    PartialTableState(
      sorting.fold(toJs.setSortingUndefined)(v => toJs.setSorting(v.toJs))
    )

  lazy val expanded: Option[Expanded] =
    toJs.expanded.toOption.map(Expanded.fromJs)

  /** WARNING: This mutates the object in-place. */
  def setExpanded(expanded: Option[Expanded]): PartialTableState =
    PartialTableState(
      expanded.fold(toJs.setExpandedUndefined)(v => toJs.setExpanded(v.toJs))
    )

  lazy val columnSizing: Option[ColumnSizing] =
    toJs.columnSizing.toOption.map(ColumnSizing.fromJs)

  /** WARNING: This mutates the object in-place. */
  def setColumnSizing(columnSizing: Option[ColumnSizing]): PartialTableState =
    PartialTableState(
      columnSizing.fold(toJs.setColumnSizingUndefined)(v => toJs.setColumnSizing(v.toJs))
    )

  lazy val columnSizingInfo: Option[ColumnSizingInfo] =
    toJs.columnSizingInfo.toOption.map(ColumnSizingInfo.fromJs)

  /** WARNING: This mutates the object in-place. */
  def setColumnSizingInfo(columnSizingInfo: Option[ColumnSizingInfo]): PartialTableState =
    PartialTableState(
      columnSizingInfo.fold(toJs.setColumnSizingInfoUndefined)(v =>
        toJs.setColumnSizingInfo(v.toJs)
      )
    )

  lazy val rowSelection: Option[RowSelection] =
    toJs.rowSelection.toOption.map(RowSelection.fromJs)

  /** WARNING: This mutates the object in-place. */
  def setRowSelection(rowSelection: Option[RowSelection]): PartialTableState =
    PartialTableState(
      rowSelection.fold(toJs.setRowSelectionUndefined)(v => toJs.setRowSelection(v.toJs))
    )

object PartialTableState:
  def apply(
    columnVisibility: js.UndefOr[ColumnVisibility] = js.undefined,
    columnOrder:      js.UndefOr[ColumnOrder] = js.undefined,
    columnPinning:    js.UndefOr[ColumnPinning] = js.undefined,
    rowPinning:       js.UndefOr[RowPinning] = js.undefined,
    sorting:          js.UndefOr[Sorting] = js.undefined,
    expanded:         js.UndefOr[Expanded] = js.undefined,
    columnSizing:     js.UndefOr[ColumnSizing] = js.undefined,
    columnSizingInfo: js.UndefOr[ColumnSizingInfo] = js.undefined,
    rowSelection:     js.UndefOr[RowSelection] = js.undefined
  ): PartialTableState = PartialTableState(
    raw.anon
      .PartialTableState()
      .applyOrNot(columnVisibility, (s, p) => s.setColumnVisibility(p.toJs))
      .applyOrNot(columnOrder, (s, p) => s.setColumnOrder(p.toJs))
      .applyOrNot(columnPinning, (s, p) => s.setColumnPinning(p.toJs))
      .applyOrNot(rowPinning, (s, p) => s.setRowPinning(p.toJs))
      .applyOrNot(sorting, (s, p) => s.setSorting(p.toJs))
      .applyOrNot(expanded, (s, p) => s.setExpanded(p.toJs))
      .applyOrNot(columnSizing, (s, p) => s.setColumnSizing(p.toJs))
      .applyOrNot(columnSizingInfo, (s, p) => s.setColumnSizingInfo(p.toJs))
      .applyOrNot(rowSelection, (s, p) => s.setRowSelection(p.toJs))
  )
