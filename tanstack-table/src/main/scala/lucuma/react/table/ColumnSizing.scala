// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import cats.Endo
import lucuma.react.SizePx
import org.scalablytyped.runtime.StringDictionary
import lucuma.typed.{tanstackTableCore => raw}

opaque type ColumnSizing = Map[ColumnId, SizePx]

object ColumnSizing:
  inline def apply(value:  Map[ColumnId, SizePx]): ColumnSizing = value
  inline def apply(values: (ColumnId, SizePx)*): ColumnSizing   = values.toMap
  private[table] def fromJs(
    rawValue: raw.buildLibFeaturesColumnSizingMod.ColumnSizingState
  ): ColumnSizing =
    rawValue.toList.map((col, size) => ColumnId(col) -> SizePx(size.toInt)).toMap

  extension (opaqueValue: ColumnSizing)
    inline def value: Map[ColumnId, SizePx]                         =
      opaqueValue
    inline def modify(f: Endo[Map[ColumnId, SizePx]]): ColumnSizing =
      f(opaqueValue)
    def toJs: raw.buildLibFeaturesColumnSizingMod.ColumnSizingState =
      StringDictionary(
        opaqueValue.map((colId, size) => colId.value -> size.value.toDouble).toSeq: _*
      )
