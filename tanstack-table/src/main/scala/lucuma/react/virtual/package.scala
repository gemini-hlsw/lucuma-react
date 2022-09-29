// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react

import japgolly.scalajs.react.vdom.TagMod
import lucuma.react.virtual.facade.Virtualizer
import org.scalajs.dom.Element

import scalajs.js

package object virtual extends HooksApiExt:
  def virtualVerticalPadding[TScrollElement <: Element, TItemElement <: Element](
    virtualizer: Virtualizer[TScrollElement, TItemElement]
  ): (Int, Int) =
    val virtualRows   = virtualizer.getVirtualItems()
    val totalSize     = virtualizer.getTotalSize().toInt
    val paddingTop    =
      if (virtualRows.length > 0) virtualRows.headOption.map(_.start.toInt).getOrElse(0) else 0
    val paddingBottom =
      if (virtualRows.length > 0)
        totalSize - virtualRows.lastOption.map(_.end.toInt).getOrElse(0)
      else 0

    (paddingTop, paddingBottom)
