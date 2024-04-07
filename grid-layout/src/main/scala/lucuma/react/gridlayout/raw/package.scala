// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.gridlayout

import org.scalajs.dom.Event
import org.scalajs.dom.MouseEvent
import org.scalajs.dom.html.Element as HTMLElement

import scala.scalajs.js

package object raw {

  type RawLayoutChange =
    js.Function1[Layout, Unit]

  type RawItemCallback =
    js.Function6[Layout, LayoutItem, LayoutItem, LayoutItem, MouseEvent, HTMLElement, Unit]

  type DropCallback =
    js.Function3[Layout, LayoutItem, Event, Unit]

  type BreakpointChangeCallback =
    js.Function2[Breakpoint, Int, Unit]

  type LayoutChangeCallback =
    js.Function2[Layout, js.Object, Unit]

  type WidthChangeCallback =
    js.Function4[Int, js.Array[Int], Int, js.Array[Int], Unit]

  type Layout = js.Array[LayoutItem]

  type Breakpoint = String
}

package raw {
  class DroppingItem(
    var i: String,
    var w: Int,
    var h: Int
  ) extends js.Object

  class LayoutItem(
    val w:             Int,
    val h:             Int,
    val x:             Int,
    val y:             Int,
    val i:             String,
    val minW:          js.UndefOr[Int] = js.undefined,
    val minH:          js.UndefOr[Int] = js.undefined,
    val maxW:          js.UndefOr[Int] = js.undefined,
    val maxH:          js.UndefOr[Int] = js.undefined,
    val static:        js.UndefOr[Boolean] = js.undefined,
    val isDraggable:   js.UndefOr[Boolean] = js.undefined,
    val isResizable:   js.UndefOr[Boolean] = js.undefined,
    val resizeHandles: js.UndefOr[js.Array[String]] = js.undefined,
    val isBounded:     js.UndefOr[Boolean] = js.undefined
  ) extends js.Object

}
