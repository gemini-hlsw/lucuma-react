package react.gridlayout

import scala.scalajs.js
import japgolly.scalajs.react.raw.JsNumber
import org.scalajs.dom.html.{ Element => HTMLElement }
import org.scalajs.dom.MouseEvent
import org.scalajs.dom.raw.Event

package object raw {

  type RawLayoutChange =
    js.Function1[Layout, Unit]

  type RawItemCallback =
    js.Function6[Layout, LayoutItem, LayoutItem, LayoutItem, MouseEvent, HTMLElement, Unit]

  type DropCallback =
    js.Function3[Layout, LayoutItem, Event, Unit]

  type BreakpointChangeCallback =
    js.Function2[Breakpoint, JsNumber, Unit]

  type LayoutChangeCallback =
    js.Function2[Layout, js.Object, Unit]

  type WidthChangeCallback =
    js.Function4[JsNumber, js.Array[JsNumber], JsNumber, js.Array[JsNumber], Unit]

  type Layout = js.Array[LayoutItem]

  type Breakpoint = String
}

package raw {
  class DroppingItem(
    var i: String,
    var w: JsNumber,
    var h: JsNumber
  ) extends js.Object

  class LayoutItem(
    val w:             JsNumber,
    val h:             JsNumber,
    val x:             JsNumber,
    val y:             JsNumber,
    val i:             js.UndefOr[String] = js.undefined,
    val minW:          js.UndefOr[JsNumber] = js.undefined,
    val minH:          js.UndefOr[JsNumber] = js.undefined,
    val maxW:          js.UndefOr[JsNumber] = js.undefined,
    val maxH:          js.UndefOr[JsNumber] = js.undefined,
    val static:        js.UndefOr[Boolean] = js.undefined,
    val isDraggable:   js.UndefOr[Boolean] = js.undefined,
    val isResizable:   js.UndefOr[Boolean] = js.undefined,
    val resizeHandles: js.UndefOr[js.Array[String]] = js.undefined,
    val isBounded:     js.UndefOr[Boolean] = js.undefined
  ) extends js.Object

}
