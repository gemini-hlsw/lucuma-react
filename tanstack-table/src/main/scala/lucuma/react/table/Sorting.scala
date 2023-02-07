// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import cats.Endo
import lucuma.typed.{tanstackTableCore => raw}

import scalajs.js
import scalajs.js.JSConverters.*

opaque type Sorting = List[ColumnSort]

object Sorting:
  inline def apply(value: List[ColumnSort]): Sorting = value
  inline def apply(values: (ColumnId, SortDirection)*): Sorting =
    values.toList.map(ColumnSort.apply.tupled)
  protected[table] def fromJs(
    rawValue: js.Array[raw.buildLibFeaturesSortingMod.ColumnSort]
  ): Sorting =
    rawValue.toList.map(ColumnSort.fromJs)

  extension (opaqueValue: Sorting)
    inline def value: List[ColumnSort]                            =
      opaqueValue
    inline def modify(f: Endo[List[ColumnSort]]): Sorting         =
      f(opaqueValue)
    def toJs: js.Array[raw.buildLibFeaturesSortingMod.ColumnSort] =
      opaqueValue.map(_.toJs).toJSArray
