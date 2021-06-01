package reactST.reactTable

import Hooks._

sealed abstract class Plugin(val hook: Hook) extends Ordered[Plugin] {
  def compare(that: Plugin): Int = Plugin.index(this) - Plugin.index(that)
}

object Plugin {
  final case object SortBy        extends Plugin(useSortBy)
  final case object ResizeColumns extends Plugin(useResizeColumns)
  final case object BlockLayout   extends Plugin(useBlockLayout)
  final case object GridLayout    extends Plugin(useGridLayout)

  val all: List[Plugin] = List(SortBy, ResizeColumns, BlockLayout, GridLayout)

  val index: Map[Plugin, Int] = all.zipWithIndex.toMap
}
