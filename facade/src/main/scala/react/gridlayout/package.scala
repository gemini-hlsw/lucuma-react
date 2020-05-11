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
  type OnLayoutChange     = Layout => Callback
  type OnLayoutsChange    = (Layout, Layouts) => Callback
  type OnBreakpointChange = (BreakpointName, JsNumber) => Callback
  type OnWidthChange      = (JsNumber, Margin, JsNumber, ContainerPadding) => Callback
  type ItemCallback       =
    (Layout, LayoutItem, LayoutItem, Option[LayoutItem], MouseEvent, HTMLElement) => Callback
  type DropCallback       =
    (JsNumber, JsNumber, JsNumber, JsNumber) => Callback
}

package gridlayout {
  trait BreakpointName {
    val name: String
  }

  object BreakpointName   {
    private final case class BreakpointNameI(name: String) extends BreakpointName
    def apply(name: String): BreakpointName = new BreakpointNameI(name)

    val lg: BreakpointName = apply("lg")
    val md: BreakpointName = apply("md")
    val sm: BreakpointName = apply("sm")
    val xs: BreakpointName = apply("xs")
    val xx: BreakpointName = apply("xx")
  }

  final case class Breakpoint(name: BreakpointName, pos: JsNumber)
  final case class Breakpoints(bps: List[Breakpoint]) {
    def toRaw: js.Object = {
      val p = js.Dynamic.literal()
      bps.foreach { case Breakpoint(name, v) => p.updateDynamic(name.name)(v.asInstanceOf[js.Any]) }
      p
    }
  }

  final case class Column(col: BreakpointName, pos: JsNumber)
  final case class Columns(cols: List[Column]) {
    def toRaw: js.Object = {
      val p = js.Dynamic.literal()
      cols.foreach { case Column(name, v) => p.updateDynamic(name.name)(v.asInstanceOf[js.Any]) }
      p
    }
  }

  final case class DroppingItem(
    i: String,
    w: JsNumber,
    h: JsNumber
  )                       {
    def toRaw: raw.DroppingItem = {
      val p = (new js.Object).asInstanceOf[raw.DroppingItem]
      p.i = i
      p.w = w
      p.h = h
      p
    }
  }

  final case class BreakpointLayout(name: BreakpointName, layout: Layout)

  object BreakpointLayout {
    private[gridlayout] def layoutsFromRaw(l: js.Object): Layout = {
      val c                   = l.asInstanceOf[js.Array[raw.LayoutItem]]
      val i: List[LayoutItem] = c.map(LayoutItem.fromRaw).toList
      Layout(i)
    }
  }

  final case class Layouts(layouts: List[BreakpointLayout]) {
    def toRaw: js.Object = {
      val p = js.Dynamic.literal()
      layouts.foreach { case BreakpointLayout(name, v) => p.updateDynamic(name.name)(v.toRaw) }
      p
    }
  }

  object Layouts          {
    private[gridlayout] def fromRaw(l: js.Object): Layouts = {
      val c  = l.asInstanceOf[js.Dictionary[js.Any]]
      val bp = for {
        p <- js.Object.getOwnPropertyNames(l)
        v <- c.get(p)
      } yield BreakpointLayout(BreakpointName(p),
                               BreakpointLayout.layoutsFromRaw(v.asInstanceOf[js.Object])
      )
      Layouts(bp.toList)
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
    isResizable: js.UndefOr[Boolean] = js.undefined,
    handle:      js.UndefOr[String] = js.undefined
  )                       {
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
                         isResizable,
                         handle
      )
  }

  object LayoutItem       {
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
                     l.isResizable,
                     l.handle
      )

    private[gridlayout] def fromRawO(l: raw.LayoutItem): Option[LayoutItem] =
      if (l != null)
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
                         l.isResizable,
                         l.handle
          )
        )
      else
        None
  }

  final case class Layout(l: List[LayoutItem]) {
    private[gridlayout] def toRaw: raw.Layout = l.toArray.map(_.toRaw).toJSArray
  }

  object Layout           {
    val Empty: Layout = Layout(Nil)

    private[gridlayout] def fromRaw(l: raw.Layout): Layout =
      Layout(List(l.map(LayoutItem.fromRaw).toSeq: _*))
  }

  sealed trait CompactType extends Product with Serializable
  object CompactType      {
    implicit val enum: EnumValue[CompactType] = EnumValue.toLowerCaseString
    case object Vertical   extends CompactType
    case object Horizontal extends CompactType
  }

}
