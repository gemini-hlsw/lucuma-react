package react

import scala.scalajs.js
// import js.annotation.JSImport
import scala.scalajs.js.|
import js.JSConverters._
import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.raw.JsNumber
import japgolly.scalajs.react.raw.React
import japgolly.scalajs.react.vdom.VdomNode
import org.scalajs.dom.html.{Element => HTMLElement}
import org.scalajs.dom.MouseEvent

package object gridlayout {
  implicit class VdomToRaw(val node: VdomNode) extends AnyVal {
    def toRaw: React.Node = node.rawNode
  }

  type Layout = js.Array[LayoutItem]
  type Margin = (JsNumber, JsNumber)
  type ContainerPadding = (JsNumber, JsNumber)

  // Callbacks
  type OnLayoutChange           = Layout => Callback
  type ItemCallback = (Layout, LayoutItem, LayoutItem, LayoutItem, MouseEvent, HTMLElement) => Callback
}

package gridlayout {
  final case class Style(styles: Map[String, String | Int])

  object Style {
    def toJsObject(style: Style): js.Object =
      style.styles.toJSDictionary.asInstanceOf[js.Object]

    def fromJsObject(o: js.Object): Style = {
      val xDict = o.asInstanceOf[js.Dictionary[String | Int]]
      val map = (for ((prop, value) <- xDict) yield prop -> value).toMap
      Style(map)
    }
  }

  // @js.native
  class LayoutItem(
    val w: JsNumber,
    val h: JsNumber,
    val x: JsNumber,
    val y: JsNumber,
    val i: js.UndefOr[String],
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
      i: js.UndefOr[String] = js.undefined,
    ): LayoutItem = new LayoutItem(w, h, x, y, i, js.undefined, js.undefined, js.undefined, js.undefined, js.undefined, js.undefined, js.undefined, js.undefined)
  }

  sealed trait CompactType
  object CompactType {
    case object Vertical extends CompactType
    case object Horizontal extends CompactType

    def toRaw(s: CompactType): String = s match {
      case Vertical   => "vertical"
      case Horizontal => "horizontal"
    }
  }

  package object raw {

    type RawLayoutChange =
      js.Function1[Layout, Unit]

    type RawItemCallback =
      js.Function6[Layout, LayoutItem, LayoutItem, LayoutItem, MouseEvent, HTMLElement, Unit]
  }
}
