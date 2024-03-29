// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import lucuma.typed.std.Record
import lucuma.typed.tanstackTableCore as raw
import org.scalablytyped.runtime.StringDictionary

enum Expanded:
  case AllRows                         extends Expanded
  case Rows(rows: Map[RowId, Boolean]) extends Expanded

  def toJs: raw.buildLibFeaturesExpandingMod.ExpandedState =
    this match
      case AllRows    => raw.tanstackTableCoreBooleans.`true`
      case Rows(rows) =>
        StringDictionary(rows.map((rowId, expanded) => rowId.value -> expanded).toSeq*)

object Expanded:
  def fromRows(rows:          (RowId, Boolean)*): Expanded.Rows = Expanded.Rows(rows.toMap)
  def fromExpandedRows(rows:  RowId*): Expanded.Rows            = fromRows(rows.map(_ -> true)*)
  def fromCollapsedRows(rows: RowId*): Expanded.Rows            = fromRows(rows.map(_ -> false)*)

  private[table] def fromJs(rawValue: raw.buildLibFeaturesExpandingMod.ExpandedState): Expanded =
    rawValue match
      case v if v == raw.tanstackTableCoreBooleans.`true` => Expanded.AllRows
      case rows                                           =>
        Expanded.Rows:
          rows
            .asInstanceOf[Record[String, Boolean]]
            .toMap
            .map: (rowId, expanded) =>
              RowId(rowId) -> expanded
