// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react

import lucuma.react.virtual.facade.Virtualizer
import org.scalajs.dom

import scalajs.js

package object virtual extends HooksApiExt:
  export VirtualHook.useVirtualizer

  /**
   * Given a virtualizer, returns:
   *   - Index of first element in original model.
   *   - Estimated size in pixels of omitted items before shown range.
   *   - Estimated size in pixels of omitted items after shown range.
   */
  def virtualOffsets[TScrollElement <: dom.Element, TItemElement <: dom.Element](
    virtualizer: Virtualizer[TScrollElement, TItemElement],
    debug:       js.UndefOr[Boolean]
  ): (Int, Int, Int) = // indexOffset, paddingBefore, paddingAfter
    val virtualRows = virtualizer.getVirtualItems()
    val totalSize   = virtualizer.getTotalSize().toInt

    val indexOffset   = virtualRows.headOption.map(_.index.toInt).getOrElse(0)
    val paddingBefore =
      if (virtualRows.length > 0) virtualRows.headOption.map(_.start.toInt).getOrElse(0) else 0
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
