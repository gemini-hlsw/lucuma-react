package react

import scala.scalajs.js
import scala.scalajs.js.|
import js.JSConverters._
import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.raw.JsNumber
import japgolly.scalajs.react.raw.React
import japgolly.scalajs.react.vdom.VdomNode
import org.scalajs.dom.html.{ Element => HTMLElement }
import org.scalajs.dom.MouseEvent

package object gridlayout {
  implicit class VdomToRaw(val node: VdomNode) extends AnyVal {
    def toRaw: React.Node = node.rawNode
  }

  type Margin           = (JsNumber, JsNumber)
  type ContainerPadding = (JsNumber, JsNumber)

  // Callbacks
  type OnLayoutChange = Layout => Callback
  type ItemCallback =
    (Layout, LayoutItem, LayoutItem, Option[LayoutItem], MouseEvent, HTMLElement) => Callback
}

package gridlayout {
  final case class Style(styles: Map[String, String | Int])

  object Style {
    def toJsObject(style: Style): js.Object =
      style.styles.toJSDictionary.asInstanceOf[js.Object]

    def fromJsObject(o: js.Object): Style = {
      val xDict = o.asInstanceOf[js.Dictionary[String | Int]]
      val map   = (for ((prop, value) <- xDict) yield prop -> value).toMap
      Style(map)
    }
  }

  final case class LayoutItem(
    w:           JsNumber,
    h:           JsNumber,
    x:           JsNumber,
    y:           JsNumber,
    i:           js.UndefOr[String] = js.undefined,
    minW:        js.UndefOr[JsNumber] = js.undefined,
    minH:        js.UndefOr[JsNumber] = js.undefined,
    maxW:        js.UndefOr[JsNumber] = js.undefined,
    maxH:        js.UndefOr[JsNumber] = js.undefined,
    moved:       js.UndefOr[Boolean] = js.undefined,
    static:      js.UndefOr[Boolean] = js.undefined,
    isDraggable: js.UndefOr[Boolean] = js.undefined,
    isResizable: js.UndefOr[Boolean] = js.undefined
  ) {
    def toRaw: raw.LayoutItem =
      new raw.LayoutItem(w,
                         h,
                         x,
                         y,
                         i,
                         minW,
                         minH,
                         maxW,
                         maxH,
                         moved,
                         static,
                         isDraggable,
                         isResizable)
  }

  object LayoutItem {
    private[gridlayout] def fromRaw(l: raw.LayoutItem): LayoutItem =
      new LayoutItem(l.w,
                     l.h,
                     l.x,
                     l.y,
                     l.i,
                     l.minW,
                     l.minH,
                     l.maxW,
                     l.maxH,
                     l.moved,
                     l.static,
                     l.isDraggable,
                     l.isResizable)

    private[gridlayout] def fromRawO(l: raw.LayoutItem): Option[LayoutItem] =
      if (l != null) {
        Some(
          new LayoutItem(l.w,
                         l.h,
                         l.x,
                         l.y,
                         l.i,
                         l.minW,
                         l.minH,
                         l.maxW,
                         l.maxH,
                         l.moved,
                         l.static,
                         l.isDraggable,
                         l.isResizable))
      } else {
        None
      }
  }

  final case class Layout(l: List[LayoutItem]) {
    private[gridlayout] def toRaw: raw.Layout = l.toArray.map(_.toRaw).toJSArray

  }

  object Layout {
    val Empty: Layout = Layout(Nil)

    private[gridlayout] def fromRaw(l: raw.Layout): Layout =
      Layout(List(l.map(LayoutItem.fromRaw): _*))
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

}
