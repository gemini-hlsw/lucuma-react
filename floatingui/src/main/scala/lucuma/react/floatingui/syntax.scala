// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.floatingui.syntax

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.common.*
import lucuma.react.floatingui.*

extension (tag: VdomTag)
  def withTooltip(
    tooltip:   VdomNode,
    placement: Placement = Placement.Top
  ): VdomNode =
    Tooltip(trigger = tag, tooltip = tooltip, placement = placement)