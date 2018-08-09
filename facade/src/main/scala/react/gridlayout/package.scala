package react

import scala.scalajs.js
import scala.scalajs.js.|
// import js.annotation.JSImport
import japgolly.scalajs.react.raw.JsNumber
import japgolly.scalajs.react.raw.React
import japgolly.scalajs.react.vdom.VdomNode
import org.scalajs.dom.html.{Element => HTMLElement}
import org.scalajs.dom.MouseEvent

package object gridlayout {
  implicit class VdomToRaw(val node: VdomNode) extends AnyVal {
    def toRaw: React.Node = node.rawNode
  }

  type Bounds = DraggableBounds | String | FalseBounds.type
}

package gridlayout {
  // @js.native
  class LayoutItem(
    val w: JsNumber,
    val h: JsNumber,
    val x: JsNumber,
    val y: JsNumber,
    val i: String,
    val minW: js.UndefOr[JsNumber],
    val minH: js.UndefOr[JsNumber],
    val maxW: js.UndefOr[JsNumber],
    val maxH: js.UndefOr[JsNumber],
    val moved: js.UndefOr[Boolean],
    val static: js.UndefOr[Boolean],
    val isDraggable: js.UndefOr[Boolean],
    val isResizable: js.UndefOr[Boolean]
  ) extends js.Object

  object LayoutItem {
    def apply(
      w: JsNumber,
      h: JsNumber,
      x: JsNumber,
      y: JsNumber,
      i: String
    ): LayoutItem = new LayoutItem(w, h, x, y, i, js.undefined, js.undefined, js.undefined, js.undefined, js.undefined, js.undefined, js.undefined, js.undefined)
  }

  sealed trait Axis {
    def value: String
  }

  object Axis {
    case object Both extends Axis {
      val value = "both"
    }
    case object X extends Axis {
      val value = "x"
    }
    case object Y extends Axis {
      val value = "y"
    }
    case object None extends Axis {
      val value = "none"
    }
  }

  case class Grid(x: JsNumber, y: JsNumber) {
    val value: js.Array[JsNumber] = js.Array(x, y)
  }

  @js.native
  trait ControlPosition extends js.Object {
    var x: js.UndefOr[JsNumber]
    var y: js.UndefOr[JsNumber]
  }

  object ControlPosition {

    def apply(x: js.UndefOr[JsNumber] = js.undefined, y: js.UndefOr[JsNumber] = js.undefined): ControlPosition = {
      val p = (new js.Object).asInstanceOf[ControlPosition]
      p.x = x
      p.y = y
      p
    }
  }

  @js.native
  trait DraggableBounds extends js.Object {
    var left: JsNumber
    var right: JsNumber
    var top: JsNumber
    var bottom: JsNumber
  }

  object DraggableBounds {

    def apply(left: JsNumber,
              right: JsNumber,
              top: JsNumber,
              bottom: JsNumber): DraggableBounds = {
      val p = (new js.Object).asInstanceOf[DraggableBounds]
      p.left = left
      p.right = right
      p.top = top
      p.bottom = bottom
      p
    }
  }

  @js.native
  trait DraggableData extends js.Object {
    var node: HTMLElement = js.native
    var x: JsNumber       = js.native
    var y: JsNumber       = js.native
    var deltaX: JsNumber  = js.native
    var deltaY: JsNumber  = js.native
    var lastX: JsNumber   = js.native
    var lastY: JsNumber   = js.native
  }

  case object FalseBounds

  /**
    * Raw facades, shouldn't be exposed to final users
    */
  private[gridlayout] object raw {
    type RawOnMouseDown = js.Function1[MouseEvent, Unit]

    type RawDraggableEventHandler =
      js.Function2[MouseEvent, DraggableData, Unit]
  }
}
