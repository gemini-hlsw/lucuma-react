// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import cats.Endo
import lucuma.typed.tanstackTableCore as raw

import scalajs.js.JSConverters.*

opaque type ColumnFilters = Map[ColumnId, Any]

object ColumnFilters:
  val Empty: ColumnFilters = ColumnFilters(Map.empty)

  inline def apply(value:  Map[ColumnId, Any]): ColumnFilters = value
  inline def apply(values: (ColumnId, Any)*): ColumnFilters   = values.toMap
  private[table] def fromJs(
    rawValue: raw.buildLibFeaturesColumnFilteringMod.ColumnFiltersState
  ): ColumnFilters =
    rawValue.map(f => (ColumnId(f.id), f.value)).toMap

  extension (opaqueValue: ColumnFilters)
    inline def value: Map[ColumnId, Any]                                =
      opaqueValue
    inline def modify(f: Endo[Map[ColumnId, Any]]): ColumnFilters       =
      f(opaqueValue)
    def toJs: raw.buildLibFeaturesColumnFilteringMod.ColumnFiltersState =
      opaqueValue
        .map: (colId, value) =>
          raw.buildLibFeaturesColumnFilteringMod.ColumnFilter(colId.value, value)
        .toJSArray
    def get[F](colId: ColumnId): Option[F]                              =
      opaqueValue.get(colId).map(_.asInstanceOf[F])
