// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import reactST.{tanstackTableCore => raw}

case class Cell[T, A](private val toJs: raw.mod.Cell[T, A]):
  lazy val id: CellId                         = CellId(toJs.id)
  lazy val row: Row[T]                        = Row(toJs.row)
  lazy val column: Column[T, A]               = Column(toJs.column)
  def getContext(): raw.mod.CellContext[T, A] = toJs.getContext()
  def getValue[V](): V                        = toJs.getValue().asInstanceOf[V]

  // Grouping
  def getIsAggregated(): Boolean  = toJs.getIsAggregated()
  def getIsGrouped(): Boolean     = toJs.getIsGrouped()
  def getIsPlaceholder(): Boolean = toJs.getIsPlaceholder()
