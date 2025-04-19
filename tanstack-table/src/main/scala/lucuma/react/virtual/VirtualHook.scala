// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.virtual

import japgolly.scalajs.react.*
import lucuma.react.virtual.facade.VirtualOptionsJS
import lucuma.react.virtual.facade.Virtualizer
import org.scalajs.dom.Element

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

object VirtualHook:
  @JSImport("@tanstack/react-virtual", "useVirtualizer")
  @js.native
  private def useVirtualizerJs[TScrollElement <: Element, TItemElement <: Element]: js.Function1[
    VirtualOptionsJS[TScrollElement, TItemElement],
    Virtualizer[TScrollElement, TItemElement]
  ] = js.native

  def useVirtualizer[TScrollElement <: Element, TItemElement <: Element] =
    HookResult
      .fromFunction(useVirtualizerJs[TScrollElement, TItemElement])
      .contramap[VirtualOptions[TScrollElement, TItemElement]](_.toJs)

  def useVirtualizerHook[TScrollElement <: Element, TItemElement <: Element] =
    CustomHook.fromHookResult(useVirtualizer[TScrollElement, TItemElement](_))

  // TODO Specify 'observeElementRect' | 'observeElementOffset' | 'scrollToFn'
  // TODO useWindowVirtualizer
