// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package reactST.reactTable

import TableHooks._

// It doesn't seem to be documented, but the order of plugins seems to matter.
sealed abstract class Plugin(val hook: TableHook) extends Ordered[Plugin] {
  type Tag

  def compare(that: Plugin): Int = Plugin.index(this) - Plugin.index(that)
}

object Plugin {
  type Base

  case object GroupBy       extends Plugin(useGroupBy)
  case object SortBy        extends Plugin(useSortBy)
  case object Expanded      extends Plugin(useExpanded)
  case object ResizeColumns extends Plugin(useResizeColumns)
  case object BlockLayout   extends Plugin(useBlockLayout)
  case object FlexLayout    extends Plugin(useFlexLayout)
  case object GridLayout    extends Plugin(useGridLayout)

  // Regardless of what order plugins are declared in usage site,
  // we always enforce the order required by react-table.
  val all: List[Plugin] =
    List(GroupBy, SortBy, Expanded, ResizeColumns, FlexLayout, BlockLayout, GridLayout)

  val index: Map[Plugin, Int] = all.zipWithIndex.toMap
}
