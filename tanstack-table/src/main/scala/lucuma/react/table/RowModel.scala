// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import lucuma.typed.tanstackTableCore as raw

case class RowModel[T, TM, CM] private[table] (
  private[table] val toJs: raw.buildLibTypesMod.RowModel[T]
):
  lazy val flatRows: List[Row[T, TM, CM]]       = toJs.flatRows.map(Row(_)).toList
  lazy val rows: List[Row[T, TM, CM]]           = toJs.rows.map(Row(_)).toList
  lazy val rowsById: Map[RowId, Row[T, TM, CM]] =
    toJs.rowsById.toMap.map: (k, v) =>
      RowId(k) -> Row[T, TM, CM](v)
