// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import lucuma.typed.tanstackTableCore as raw

case class ColumnSort(columnId: ColumnId, direction: SortDirection):
  def toJs: raw.buildLibFeaturesRowSortingMod.ColumnSort =
    raw.buildLibFeaturesRowSortingMod.ColumnSort(direction.toDescending, columnId.value)

object ColumnSort:
  private[table] def fromJs(rawValue: raw.buildLibFeaturesRowSortingMod.ColumnSort): ColumnSort =
    ColumnSort(ColumnId(rawValue.id), SortDirection.fromDescending(rawValue.desc))
