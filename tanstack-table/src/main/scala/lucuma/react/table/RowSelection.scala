// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import cats.Endo
import org.scalablytyped.runtime.StringDictionary
import lucuma.typed.{tanstackTableCore => raw}

opaque type RowSelection = Map[RowId, Boolean]

object RowSelection:
  inline def apply(value:  Map[RowId, Boolean]): RowSelection = value
  inline def apply(values: (RowId, Boolean)*): RowSelection   = values.toMap
  private[table] def fromJs(
    rawValue: raw.buildLibFeaturesRowSelectionMod.RowSelectionState
  ): RowSelection =
    rawValue.toList.map((row, selected) => RowId(row) -> selected).toMap

  extension (opaqueValue: RowSelection)
    inline def value: Map[RowId, Boolean]                           =
      opaqueValue
    inline def modify(f: Endo[Map[RowId, Boolean]]): RowSelection   =
      f(opaqueValue)
    def toJs: raw.buildLibFeaturesRowSelectionMod.RowSelectionState =
      StringDictionary(
        opaqueValue.map((rowId, selected) => rowId.value -> selected).toSeq: _*
      )
