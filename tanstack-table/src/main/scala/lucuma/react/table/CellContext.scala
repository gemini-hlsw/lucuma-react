// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
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
case class CellContext[T, A, TM, CM, TF, CF, FM] private[table] (
  private[table] val toJs: raw.buildLibCoreCellMod.CellContext[T, A]
):
  lazy val cell: Cell[T, A, TM, CM, TF, CF, FM]     = Cell(toJs.cell)
  lazy val column: Column[T, A, TM, CM, TF, CF, FM] = Column(toJs.column)
  def getValue(): A                                 = toJs.getValue().asInstanceOf[A]
  lazy val value: A                                 = getValue() // Added for convenience
  def renderValue(): Option[A]                      = Option(toJs.renderValue().asInstanceOf[A])
  lazy val row: Row[T, TM, CM, TF]                  = Row(toJs.row)
  lazy val table: Table[T, TM, CM, TF]              = Table(toJs.table)
