// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import cats.Endo
import lucuma.typed.tanstackTableCore as raw
import org.scalablytyped.runtime.StringDictionary

opaque type ColumnVisibility = Map[ColumnId, Visibility]

object ColumnVisibility:
  val Empty: ColumnVisibility = ColumnVisibility(Map.empty)

  inline def apply(value:  Map[ColumnId, Visibility]): ColumnVisibility = value
  inline def apply(values: (ColumnId, Visibility)*): ColumnVisibility   = values.toMap
  private[table] def fromJs(
    rawValue: raw.buildLibFeaturesColumnVisibilityMod.VisibilityState
  ): ColumnVisibility =
    rawValue.toList.map((col, visible) => ColumnId(col) -> Visibility.fromVisible(visible)).toMap

  extension (opaqueValue: ColumnVisibility)
    inline def value: Map[ColumnId, Visibility]                             =
      opaqueValue
    inline def modify(f: Endo[Map[ColumnId, Visibility]]): ColumnVisibility =
      f(opaqueValue)
    def toJs: raw.buildLibFeaturesColumnVisibilityMod.VisibilityState       =
      StringDictionary(opaqueValue.map((colId, visible) => colId.value -> visible.value).toSeq*)

    def toggled(columnId: ColumnId): ColumnVisibility =
      // From the docs: A column will be hidden if its ID is present in the map and the value is false.
      // If the column ID is not present in the map, or the value is true, the column will be shown.
      modify: map =>
        map.get(columnId) match
          case Some(Visibility.Hidden) => map.updated(columnId, Visibility.Visible)
          case _                       => map.updated(columnId, Visibility.Hidden)
