// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.floatingui.syntax

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.floatingui.*

import scala.language.implicitConversions // This shouldn't be necessary, but it is.

extension (tag: VdomTag)
  def withTooltip(
    tooltip:   VdomNode,
    placement: Placement = Placement.Top,
    open:      Boolean = false
  ): VdomNode =
    Tooltip(trigger = tag, tooltip = tooltip, placement = placement, open = open)

  def withTooltipWhen(
    condition: Boolean,
    tooltip:   VdomNode,
    placement: Placement = Placement.Top,
    open:      Boolean = false
  ): VdomNode =
    if (condition) withTooltip(tooltip, placement, open = open)
    else tag

  def withTooltipUnless(
    condition: Boolean,
    tooltip:   VdomNode,
    placement: Placement = Placement.Top,
    open:      Boolean = false
  ): VdomNode =
    withTooltipWhen(!condition, tooltip, placement, open = open)
