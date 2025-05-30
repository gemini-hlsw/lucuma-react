// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import japgolly.scalajs.react.callback.Callback
import japgolly.scalajs.react.facade.SyntheticEvent
import lucuma.typed.tanstackTableCore as raw
import org.scalajs.dom

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
case class Row[T, TM, CM, TF] private[table] (private[table] val toJs: raw.buildLibTypesMod.Row[T]):
  lazy val depth: Int                                         = toJs.depth.toInt
  lazy val id: RowId                                          = RowId(toJs.id)
  lazy val index: Int                                         = toJs.index.toInt
  lazy val original: T                                        = toJs.original
  lazy val originalSubRows: Option[List[T]]                   = toJs.originalSubRows.toOption.map(_.toList)
  lazy val subRows: List[Row[T, TM, CM, TF]]                  = toJs.subRows.toList.map(Row(_))
  def getAllCells(): List[Cell[T, Any, TM, CM, TF, Any, Any]] =
    toJs.getAllCells().toList.map(Cell(_))
  def getLeafRows(): List[Row[T, TM, CM, TF]]                 = toJs.getLeafRows().toList.map(Row(_))
  def getValue[V](columnId: ColumnId): V                      = toJs.getValue(columnId.value)

  // Visibility
  def getVisibleCells(): List[Cell[T, Any, TM, CM, TF, Any, Any]] =
    toJs.getVisibleCells().toList.map(Cell(_))

  // Column Pinning
  def getCenterVisibleCells(): List[Cell[T, Any, TM, CM, TF, Any, Any]] =
    toJs.getCenterVisibleCells().toList.map(Cell(_))
  def getLeftVisibleCells(): List[Cell[T, Any, TM, CM, TF, Any, Any]]   =
    toJs.getLeftVisibleCells().toList.map(Cell(_))
  def getRightVisibleCells(): List[Cell[T, Any, TM, CM, TF, Any, Any]]  =
    toJs.getRightVisibleCells().toList.map(Cell(_))

  // Row Grouping
  def getIsGrouped(): Boolean               = toJs.getIsGrouped()
  lazy val groupingColumnId: Option[String] = toJs.groupingColumnId.toOption
  lazy val groupingValue: Option[Any]       = toJs.groupingValue.toOption

  // Row Selection
  def getCanMultiSelect(): Boolean                                     = toJs.getCanMultiSelect()
  def getCanSelect(): Boolean                                          = toJs.getCanSelect()
  def getCanSelectSubRows(): Boolean                                   = toJs.getCanSelectSubRows()
  def getIsAllSubRowsSelected(): Boolean                               = toJs.getIsAllSubRowsSelected()
  def getIsSelected(): Boolean                                         = toJs.getIsSelected()
  def getIsSomeSelected(): Boolean                                     = toJs.getIsSomeSelected()
  def getToggleSelectedHandler(): SyntheticEvent[dom.Node] => Callback =
    e => Callback(toJs.getToggleSelectedHandler()(e))
  def toggleSelected(): Callback                                       = Callback(toJs.toggleSelected())
  def toggleSelected(value: Boolean): Callback                         = Callback(toJs.toggleSelected(value))

  // Expanded Rows
  def getCanExpand(): Boolean                     = toJs.getCanExpand()
  def getIsExpanded(): Boolean                    = toJs.getIsExpanded()
  def getToggleExpandedHandler(): Callback        = Callback(toJs.getToggleExpandedHandler()())
  def toggleExpanded(): Callback                  = Callback(toJs.toggleExpanded())
  def toggleExpanded(expanded: Boolean): Callback = Callback(toJs.toggleExpanded(expanded))

  // Row Pinning
  def pin(position: RowPinningPosition): Callback = Callback(toJs.pin(position.toJs))
  def unpin(): Callback                           = Callback(
    toJs.pin(raw.buildLibFeaturesRowPinningMod.RowPinningPosition.`false`)
  )
  def getCanPin(): Boolean                        = toJs.getCanPin()
  def getIsPinned(): Option[RowPinningPosition]   = RowPinningPosition.fromJs(toJs.getIsPinned())
  def getPinnedIndex(): Int                       = toJs.getPinnedIndex().toInt

  // Column Filtering
  def columnFilters: Map[ColumnId, FilterResult] =
    toJs.columnFilters.map((colId, pass) => (ColumnId(colId), FilterResult.fromBoolean(pass))).toMap
  def columnFiltersMeta: Map[ColumnId, Any]      =
    toJs.columnFiltersMeta.map((colId, value) => (ColumnId(colId), value)).toMap
