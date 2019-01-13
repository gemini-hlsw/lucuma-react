package react

import scala.scalajs.js
import js.JSConverters._
import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.raw.JsNumber
import org.scalajs.dom.html.{ Element => HTMLElement }
import org.scalajs.dom.MouseEvent
import react.common.EnumValue

package object gridlayout {
  type Margin           = (JsNumber, JsNumber)
  type ContainerPadding = (JsNumber, JsNumber)

  // Callbacks
  type OnLayoutChange = Layout => Callback
  type ItemCallback =
    (Layout, LayoutItem, LayoutItem, Option[LayoutItem], MouseEvent, HTMLElement) => Callback
}

package gridlayout {
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

  sealed trait CompactType extends Product with Serializable
  object CompactType {
    implicit val enum: EnumValue[CompactType] = EnumValue.toLowerCaseString
    case object Vertical extends CompactType
    case object Horizontal extends CompactType
  }

}
