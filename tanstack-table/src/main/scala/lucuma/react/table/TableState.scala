// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import org.scalablytyped.runtime.StringDictionary
import reactST.{tanstackTableCore => raw}

import scalajs.js

// Still missing (everywhere): filters, grouping, pagination
case class TableState(val toJs: raw.mod.TableState):
  lazy val columnVisibility: ColumnVisibility = ColumnVisibility.fromJs(toJs.columnVisibility)

  def withColumnVisibility(columnVisibility: ColumnVisibility): TableState =
    TableState(toJs.setColumnVisibility(columnVisibility.toJs))

  lazy val columnOrder: ColumnOrder = ColumnOrder.fromJs(toJs.columnOrder)

  def withColumnOrder(columnOrder: ColumnOrder): TableState =
    TableState(toJs.setColumnOrder(columnOrder.toJs))

  lazy val columnPinning: ColumnPinning = ColumnPinning.fromJs(toJs.columnPinning)

  lazy val sorting: Sorting = Sorting.fromJs(toJs.sorting)

  def withSorting(sorting: Sorting): TableState =
    TableState(toJs.setSorting(sorting.toJs))

  lazy val expanded: Expanded = Expanded.fromJs(toJs.expanded)

  def withExpanded(expanded: Expanded): TableState =
    TableState(toJs.setExpanded(expanded.toJs))

  lazy val columnSizing: ColumnSizing = ColumnSizing.fromJs(toJs.columnSizing)

  def withColumnSizing(columnSizing: ColumnSizing): TableState =
    TableState(toJs.setColumnSizing(columnSizing.toJs))

  lazy val columnSizingInfo: ColumnSizingInfo = ColumnSizingInfo.fromJs(toJs.columnSizingInfo)

  def withColumnSizingInfo(columnSizingInfo: ColumnSizingInfo): TableState =
    TableState(toJs.setColumnSizingInfo(columnSizingInfo.toJs))

  lazy val rowSelection: RowSelection = RowSelection.fromJs(toJs.rowSelection)

  def withRowSelection(rowSelection: RowSelection): TableState =
    TableState(toJs.setRowSelection(rowSelection.toJs))

object TableState:
  def apply(
    columnVisibility: js.UndefOr[ColumnVisibility] = js.undefined,
    columnOrder:      js.UndefOr[ColumnOrder] = js.undefined,
    columnPinning:    js.UndefOr[ColumnPinning] = js.undefined,
    sorting:          js.UndefOr[Sorting] = js.undefined,
    expanded:         js.UndefOr[Expanded] = js.undefined,
    columnSizing:     js.UndefOr[ColumnSizing] = js.undefined,
    columnSizingInfo: js.UndefOr[ColumnSizingInfo] = js.undefined,
    rowSelection:     js.UndefOr[RowSelection] = js.undefined
  ): TableState = TableState(
    raw.mod
      .InitialTableState()
      .applyOrNot(columnVisibility, (s, p) => s.setColumnVisibility(p.toJs))
      .applyOrNot(columnOrder, (s, p) => s.setColumnOrder(p.toJs))
      .applyOrNot(columnPinning, (s, p) => s.setColumnPinning(p.toJs))
      .applyOrNot(sorting, (s, p) => s.setSorting(p.toJs))
      .applyOrNot(expanded, (s, p) => s.setExpanded(p.toJs))
      .applyOrNot(columnSizing, (s, p) => s.setColumnSizing(p.toJs))
      .applyOrNot(columnSizingInfo, (s, p) => s.setColumnSizingInfo(p.toJs))
      .applyOrNot(rowSelection, (s, p) => s.setRowSelection(p.toJs))
      .asInstanceOf[raw.mod.TableState]
  )
