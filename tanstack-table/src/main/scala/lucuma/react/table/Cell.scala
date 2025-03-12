// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import lucuma.typed.tanstackTableCore as raw

case class Cell[T, A, TM, CM, F, FM](private val toJs: raw.buildLibTypesMod.Cell[T, A]):
  lazy val id: CellId                                = CellId(toJs.id)
  lazy val row: Row[T, TM]                           = Row(toJs.row)
  lazy val column: Column[T, A, TM, CM, F, FM]       = Column(toJs.column)
  def getContext(): CellContext[T, A, TM, CM, F, FM] = CellContext(toJs.getContext())
  def getValue[V](): V                               = toJs.getValue().asInstanceOf[V]

  // Grouping
  def getIsAggregated(): Boolean  = toJs.getIsAggregated()
  def getIsGrouped(): Boolean     = toJs.getIsGrouped()
  def getIsPlaceholder(): Boolean = toJs.getIsPlaceholder()
