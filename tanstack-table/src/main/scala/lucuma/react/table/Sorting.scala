// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import cats.Endo
import lucuma.typed.tanstackTableCore as raw

import scalajs.js
import scalajs.js.JSConverters.*

opaque type Sorting = List[ColumnSort]

object Sorting:
  val Empty: Sorting = Sorting(List.empty)

  inline def apply(value: List[ColumnSort]): Sorting            = value
  inline def apply(values: (ColumnId, SortDirection)*): Sorting =
    values.toList.map(ColumnSort.apply.tupled)
  protected[table] def fromJs(
    rawValue: js.Array[raw.buildLibFeaturesRowSortingMod.ColumnSort]
  ): Sorting =
    rawValue.toList.map(ColumnSort.fromJs)

  extension (opaqueValue: Sorting)
    inline def value: List[ColumnSort]                               =
      opaqueValue
    inline def modify(f: Endo[List[ColumnSort]]): Sorting            =
      f(opaqueValue)
    def toJs: js.Array[raw.buildLibFeaturesRowSortingMod.ColumnSort] =
      opaqueValue.map(_.toJs).toJSArray

    def one(columnId: ColumnId, direction: SortDirection = SortDirection.Ascending): Sorting =
      modify(_ => List(ColumnSort(columnId, direction)))
    def add(columnId: ColumnId, direction: SortDirection = SortDirection.Ascending): Sorting =
      modify(_ :+ ColumnSort(columnId, direction))
    def remove(columnId: ColumnId): Sorting                                                  =
      modify(_.filterNot(_.columnId == columnId))
