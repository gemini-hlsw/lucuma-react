// Copyright (c) 2016-2026 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react

import lucuma.react.virtual.facade.Virtualizer
import lucuma.typed.tanstackVirtualCore as rawVirtual
import org.scalajs.dom

import scalajs.js

package object virtual extends HooksApiExt:
  export VirtualHook.useVirtualizer

  /**
   * Splits the virtualizer's items into the leading ones that a range extractor added before the
   * default range (e.g. sticky headers) and the default range itself: the visible range plus the
   * overscan.
   */
  def splitLeadingItems[TScrollElement <: dom.Element, TItemElement <: dom.Element](
    virtualizer: Virtualizer[TScrollElement, TItemElement]
  ): (List[rawVirtual.mod.VirtualItem], List[rawVirtual.mod.VirtualItem]) =
    // The range is null before the first measurement, when there are no items anyway.
    val firstDefaultIndex: Int =
      Option(virtualizer.range)
        .map(range => (range.startIndex - virtualizer.options.overscan.getOrElse(1)).max(0))
        .getOrElse(0)
    virtualizer.getVirtualItems().toList.partition(_.index < firstDefaultIndex)

  /**
   * Given a virtualizer, returns:
   *   - Index of first element in original model.
   *   - Estimated size in pixels of omitted items before shown range.
   *   - Estimated size in pixels of omitted items after shown range.
   *
   * The offsets describe the contiguous visible range. Items a range extractor added before it are
   * rendered in flow, so their size is taken off the padding before the range.
   */
  def virtualOffsets[TScrollElement <: dom.Element, TItemElement <: dom.Element](
    virtualizer: Virtualizer[TScrollElement, TItemElement],
    debug:       js.UndefOr[Boolean]
  ): (Int, Int, Int) = // indexOffset, paddingBefore, paddingAfter
    val (leadingRows, virtualRows) = splitLeadingItems(virtualizer)
    val totalSize                  = virtualizer.getTotalSize().toInt

    val indexOffset   = virtualRows.headOption.map(_.index.toInt).getOrElse(0)
    val paddingBefore =
      if (virtualRows.length > 0)
        virtualRows.headOption.map(_.start.toInt).getOrElse(0) - leadingRows.map(_.size.toInt).sum
      else 0
    val paddingAfter  =
      if (virtualRows.length > 0)
        totalSize - virtualRows.lastOption.map(_.end.toInt).getOrElse(0)
      else 0

    if (debug.contains(true))
      dom.console.log(
        "Measuring Virtualizer Padding:",
        s"Virtual Rows: ${virtualRows.size},",
        s"Total Size: ${totalSize}px,",
        s"HeadOption Start: ${virtualRows.headOption.map(_.start.toInt)}px,",
        s"LastOption End: ${virtualRows.lastOption.map(_.end.toInt)}px"
      )

    (indexOffset, paddingBefore, paddingAfter)
