package reactST.reactTable

import TableHooks._

// It doesn't seem to be documented, but the order of plugins seems to matter.
sealed abstract class Plugin(val hook: TableHook) extends Ordered[Plugin] {
  type Tag

  def compare(that: Plugin): Int = Plugin.index(this) - Plugin.index(that)
}

object Plugin {
  type Base

  final case object GroupBy       extends Plugin(useGroupBy)
  final case object SortBy        extends Plugin(useSortBy)
  final case object Expanded      extends Plugin(useExpanded)
  final case object ResizeColumns extends Plugin(useResizeColumns)
  final case object BlockLayout   extends Plugin(useBlockLayout)
  final case object GridLayout    extends Plugin(useGridLayout)

  // Regardless of what order plugins are declared in usage site,
  // we always enforce the order required by react-table.
  val all: List[Plugin] = List(GroupBy, SortBy, Expanded, ResizeColumns, BlockLayout, GridLayout)

  val index: Map[Plugin, Int] = all.zipWithIndex.toMap
}
