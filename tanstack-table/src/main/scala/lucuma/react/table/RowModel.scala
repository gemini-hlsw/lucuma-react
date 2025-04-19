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
 */
case class RowModel[T, TM, CM, TF] private[table] (
  private[table] val toJs: raw.buildLibTypesMod.RowModel[T]
):
  lazy val flatRows: List[Row[T, TM, CM, TF]]       = toJs.flatRows.map(Row(_)).toList
  lazy val rows: List[Row[T, TM, CM, TF]]           = toJs.rows.map(Row(_)).toList
  lazy val rowsById: Map[RowId, Row[T, TM, CM, TF]] =
    toJs.rowsById.toMap.map: (k, v) =>
      RowId(k) -> Row[T, TM, CM, TF](v)
