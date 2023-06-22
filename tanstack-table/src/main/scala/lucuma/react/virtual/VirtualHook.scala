// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.virtual

import japgolly.scalajs.react.*
import japgolly.scalajs.react.hooks.CustomHook
import lucuma.react.virtual.facade.VirtualOptionsJS
import lucuma.react.virtual.facade.Virtualizer
import org.scalajs.dom.Element

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

object VirtualHook:
  @JSImport("@tanstack/react-virtual", "useVirtualizer")
  @js.native
  private def useVirtualizerJS[TScrollElement <: Element, TItemElement <: Element](
    options: VirtualOptionsJS[TScrollElement, TItemElement]
  ): Virtualizer[TScrollElement, TItemElement] =
    js.native

  def useVirtualizerHook[TScrollElement <: Element, TItemElement <: Element] =
    CustomHook.unchecked[
      VirtualOptions[TScrollElement, TItemElement],
      Virtualizer[TScrollElement, TItemElement]
    ](i => useVirtualizerJS(i.toJs))

  // TODO Specify 'observeElementRect' | 'observeElementOffset' | 'scrollToFn'
  // TODO useWindowVirtualizer
