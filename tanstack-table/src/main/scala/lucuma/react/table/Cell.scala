// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import lucuma.typed.tanstackTableCore as raw

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
case class Cell[T, A, TM, CM, TF, CF, FM](private val toJs: raw.buildLibTypesMod.Cell[T, A]):
  lazy val id: CellId                                     = CellId(toJs.id)
  lazy val row: Row[T, TM, CM, TF]                        = Row(toJs.row)
  lazy val column: Column[T, A, TM, CM, TF, CF, FM]       = Column(toJs.column)
  def getContext(): CellContext[T, A, TM, CM, TF, CF, FM] = CellContext(toJs.getContext())
  def getValue[V](): V                                    = toJs.getValue().asInstanceOf[V]

  // Grouping
  def getIsAggregated(): Boolean  = toJs.getIsAggregated()
  def getIsGrouped(): Boolean     = toJs.getIsGrouped()
  def getIsPlaceholder(): Boolean = toJs.getIsPlaceholder()
