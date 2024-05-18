// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import lucuma.typed.tanstackTableCore as raw

case class CellContext[T, A, TM, CM] private[table] (
  private[table] val toJs: raw.buildLibCoreCellMod.CellContext[T, A]
):
  lazy val cell: Cell[T, A, TM, CM]     = Cell(toJs.cell)
  lazy val column: Column[T, A, TM, CM] = Column(toJs.column)
  def getValue(): A                     = toJs.getValue().asInstanceOf[A]
  lazy val value: A                     = getValue() // Added for convenience
  def renderValue(): Option[A]          = Option(toJs.renderValue().asInstanceOf[A])
  lazy val row: Row[T, TM]              = Row(toJs.row)
  lazy val table: Table[T, TM]          = Table(toJs.table)
