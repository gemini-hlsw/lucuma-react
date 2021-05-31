package reactST.reactTable

import Hooks._

sealed abstract class Plugin(val hook: Hook)

object Plugin {
  final case object SortBy        extends Plugin(useSortBy)
  final case object ResizeColumns extends Plugin(useResizeColumns)
  final case object BlockLayout   extends Plugin(useSortBy)
  final case object GridLayout    extends Plugin(useGridLayout)
}
