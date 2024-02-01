// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.floatingui.syntax

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.floatingui.*

import scala.language.implicitConversions // This shouldn't be necessary, but it is.

extension (tag: VdomTag)
  def withTooltip(
    tooltip:   VdomNode,
    placement: Placement = Placement.Top
  ): VdomNode =
    Tooltip(trigger = tag, tooltip = tooltip, placement = placement)

  def withTooltipWhen(
    condition: Boolean,
    tooltip:   VdomNode,
    placement: Placement = Placement.Top
  ): VdomNode =
    if (condition) withTooltip(tooltip, placement)
    else tag

  def withTooltipUnless(
    condition: Boolean,
    tooltip:   VdomNode,
    placement: Placement = Placement.Top
  ): VdomNode =
    withTooltipWhen(!condition, tooltip, placement)
