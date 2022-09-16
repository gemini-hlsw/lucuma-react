// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.virtual

import japgolly.scalajs.react.*
import japgolly.scalajs.react.hooks.CustomHook
import lucuma.react.table.facade.*
import lucuma.react.virtual.facade.VirtualOptionsJS
import org.scalajs.dom.Element
import reactST.tanstackReactTable.mod.useReactTable
import reactST.tanstackTableCore.tanstackTableCoreStrings.renderFallbackValue
import reactST.{tanstackTableCore => raw}
import reactST.{tanstackVirtualCore => rawVirtual}

import scala.scalajs.js
import scala.scalajs.js.JSConverters.*
import scala.scalajs.js.annotation.JSImport

object VirtualHook:
  @JSImport("@tanstack/react-virtual", "useVirtualizer")
  @js.native
  private def useVirtualizerJS[TScrollElement <: Element, TItemElement <: Element](
    options: VirtualOptionsJS[TScrollElement, TItemElement]
  ): rawVirtual.mod.Virtualizer[TScrollElement, TItemElement] =
    js.native

  def useVirtualizerHook[TScrollElement <: Element, TItemElement <: Element] =
    CustomHook.unchecked[
      VirtualOptions[TScrollElement, TItemElement],
      rawVirtual.mod.Virtualizer[TScrollElement, TItemElement]
    ](i => useVirtualizerJS(i.toJS))

  // TODO Specify 'observeElementRect' | 'observeElementOffset' | 'scrollToFn'
  // TODO useWindowVirtualizer
