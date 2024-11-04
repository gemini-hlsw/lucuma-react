// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import cats.Endo
import lucuma.typed.tanstackTableCore as raw

import scalajs.js.JSConverters.*

opaque type ColumnOrder = List[ColumnId]

object ColumnOrder:
  val Empty: ColumnOrder = ColumnOrder(List.empty)

  inline def apply(value:  List[ColumnId]): ColumnOrder = value
  inline def apply(values: ColumnId*): ColumnOrder      = values.toList
  private[table] def fromJs(
    rawValue: raw.buildLibFeaturesColumnOrderingMod.ColumnOrderState
  ): ColumnOrder =
    rawValue.toList.map(ColumnId(_))

  extension (opaqueValue: ColumnOrder)
    inline def value: List[ColumnId]                                 =
      opaqueValue
    inline def modify(f: Endo[List[ColumnId]]): ColumnOrder          =
      f(opaqueValue)
    def toJs: raw.buildLibFeaturesColumnOrderingMod.ColumnOrderState =
      opaqueValue.map(_.value).toJSArray
