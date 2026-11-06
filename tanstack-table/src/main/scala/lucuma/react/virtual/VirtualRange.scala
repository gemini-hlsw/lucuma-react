// Copyright (c) 2016-2026 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.virtual

import lucuma.typed.tanstackVirtualCore as rawVirtual

import scala.collection.immutable.SortedSet

/**
 * The visible range of items, as given to a range extractor.
 *
 * @param startIndex
 *   Index of the first visible item.
 * @param endIndex
 *   Index of the last visible item.
 * @param overscan
 *   Number of items rendered beyond the visible range on each side.
 * @param count
 *   Total number of items.
 */
case class VirtualRange(startIndex: Int, endIndex: Int, overscan: Int, count: Int):
  /** The indices the default range extractor renders: the visible range plus the overscan. */
  lazy val withOverscan: SortedSet[Int] =
    SortedSet.from((startIndex - overscan).max(0) to (endIndex + overscan).min(count - 1))

object VirtualRange:
  def fromJs(range: rawVirtual.mod.Range): VirtualRange =
    VirtualRange(
      range.startIndex.toInt,
      range.endIndex.toInt,
      range.overscan.toInt,
      range.count.toInt
    )
