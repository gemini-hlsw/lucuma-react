// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.virtual.facade

import lucuma.typed.tanstackVirtualCore as rawVirtual
import org.scalajs.dom.Element

import scala.scalajs.js

@js.native
trait Virtualizer[TScrollElement <: Element, TItemElement <: Element] protected ()
    extends js.Object:
  // These are just the documented elements

  val options: VirtualOptionsJS[TScrollElement, TItemElement] = js.native

  var scrollElement: TScrollElement | Null = js.native

  def getVirtualItems(): js.Array[rawVirtual.mod.VirtualItem] = js.native

  def scrollToOffset(
    toOffset: Int,
    options:  js.UndefOr[rawVirtual.mod.ScrollToOffsetOptions] = js.undefined
  ): Unit =
    js.native

  def scrollToIndex(
    index:   Int,
    options: js.UndefOr[rawVirtual.mod.ScrollToOffsetOptions] = js.undefined
  ): Unit = js.native

  def getTotalSize(): Int = js.native

  def measure(): Unit = js.native
