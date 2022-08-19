// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react

import cats._
import cats.syntax.all._
import japgolly.scalajs.react.Callback
import org.scalajs.dom.Event
import org.scalajs.dom.MouseEvent
import org.scalajs.dom.html.{Element => HTMLElement}
import react.common._

import scala.annotation.nowarn
import scala.scalajs.js

import js.annotation.JSImport
import js.JSConverters._

package object gridlayout {
  type Margin           = (Int, Int)
  type ContainerPadding = (Int, Int)

  // Callbacks
  type OnLayoutChange     = Layout => Callback
  type OnLayoutsChange    = (Layout, Layouts) => Callback
  type OnBreakpointChange = (BreakpointName, Int) => Callback
  type OnWidthChange      = (Int, Margin, Int, ContainerPadding) => Callback
  type ItemCallback       =
    (Layout, LayoutItem, LayoutItem, Option[LayoutItem], MouseEvent, HTMLElement) => Callback
  type DropCallback       =
    (Layout, LayoutItem, Event) => Callback

  def getBreakpointFromWidth(breakpoints: Map[BreakpointName, Int], width: Int): BreakpointName = {
    val currentBreakpoints: scala.collection.mutable.Map[String, Int] =
      scala.collection.mutable.Map.from(
        breakpoints
          .map { case (x, w) => x.name -> w }
      )
    BreakpointName(
      ResponsiveUtils.getBreakpointFromWidth(currentBreakpoints.toJSDictionary, width.toDouble)
    )
  }

}

package gridlayout {

  /**
   * Facade for ResponsiveUtils
   */
  @js.native
  @JSImport("react-grid-layout", "Responsive.utils")
  object ResponsiveUtils extends js.Object {
    @nowarn
    def getBreakpointFromWidth(breakpoints: js.Dictionary[Int], width: Double): String = js.native
  }

  trait BreakpointName {
    val name: String
  }

  object BreakpointName {
    implicit val eqBreakpointName: Eq[BreakpointName] = Eq.by(_.name)

    private final case class BreakpointNameI(name: String) extends BreakpointName
    def apply(name: String): BreakpointName = new BreakpointNameI(name)

    val xxl: BreakpointName = apply("xxl")
    val xl: BreakpointName  = apply("xl")
    val lg: BreakpointName  = apply("lg")
    val md: BreakpointName  = apply("md")
    val sm: BreakpointName  = apply("sm")
    val xs: BreakpointName  = apply("xs")
    val xxs: BreakpointName = apply("xxs")

    val predefined: List[BreakpointName] = List(xxl, xl, lg, md, sm, xs, xxs)
  }

  final case class Breakpoint(name: BreakpointName, pos: Int)

  object Breakpoint {
    implicit val eqBreakpoint: Eq[Breakpoint] = Eq.by(x => (x.name, x.pos))
  }

  final case class Breakpoints(bps: List[Breakpoint]) {
    def toRaw: js.Object = {
      val p = js.Dynamic.literal()
      bps.foreach { case Breakpoint(name, v) => p.updateDynamic(name.name)(v.asInstanceOf[js.Any]) }
      p
    }
  }

  object Breakpoints {
    implicit val eqBreakpoints: Eq[Breakpoints] = Eq.by(_.bps)
  }

  final case class Column(col: BreakpointName, pos: Int)
  object Column {
    implicit val eqColumn: Eq[Column] = Eq.by(x => (x.col, x.pos))
  }

  final case class Columns(cols: List[Column]) {
    def toRaw: js.Object = {
      val p = js.Dynamic.literal()
      cols.foreach { case Column(name, v) => p.updateDynamic(name.name)(v.asInstanceOf[js.Any]) }
      p
    }
  }

  object Columns {
    implicit val eqColumns: Eq[Columns] = Eq.by(_.cols)
  }

  final case class DroppingItem(
    i: String,
    w: Int,
    h: Int
  ) {
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
    implicit val eqBreakpointLayout: Eq[BreakpointLayout]        = Eq.by(x => (x.name, x.layout))
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

  object Layouts {
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
    w:             Int,
    h:             Int,
    x:             Int,
    y:             Int,
    i:             js.UndefOr[String] = js.undefined,
    minW:          js.UndefOr[Int] = js.undefined,
    minH:          js.UndefOr[Int] = js.undefined,
    maxW:          js.UndefOr[Int] = js.undefined,
    maxH:          js.UndefOr[Int] = js.undefined,
    static:        js.UndefOr[Boolean] = js.undefined,
    isDraggable:   js.UndefOr[Boolean] = js.undefined,
    isResizable:   js.UndefOr[Boolean] = js.undefined,
    resizeHandles: js.UndefOr[List[ResizeHandle]] = js.undefined,
    isBounded:     js.UndefOr[Boolean] = js.undefined
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
                         static,
                         isDraggable,
                         isResizable,
                         resizeHandles.map(_.toJSArray.map(_.toJs)),
                         isBounded
      )
  }

  object LayoutItem {
    implicit def eqUndef[A: Eq]: Eq[js.UndefOr[A]] = Eq.by(_.toOption)
    implicit val eqLayoutItem: Eq[LayoutItem]      = Eq.by(x =>
      (x.w,
       x.h,
       x.x,
       x.y,
       x.y,
       x.i,
       x.minW,
       x.minH,
       x.maxW,
       x.maxH,
       x.static,
       x.isDraggable,
       x.isResizable,
       x.resizeHandles,
       x.isBounded
      )
    )

    private[gridlayout] def fromRaw(l: raw.LayoutItem): LayoutItem =
      new LayoutItem(
        l.w,
        l.h,
        l.x,
        l.y,
        l.i,
        l.minW,
        l.minH,
        l.maxW,
        l.maxH,
        l.static,
        l.isDraggable,
        l.isResizable,
        l.resizeHandles.map(_.toList.map(ResizeHandle.fromRaw).collect { case Some(x) =>
          x
        }),
        l.isBounded
      )

    private[gridlayout] def fromRawO(l: raw.LayoutItem): Option[LayoutItem] =
      if (l != null)
        Some(
          new LayoutItem(
            l.w,
            l.h,
            l.x,
            l.y,
            l.i,
            l.minW,
            l.minH,
            l.maxW,
            l.maxH,
            l.static,
            l.isDraggable,
            l.isResizable,
            l.resizeHandles.map(_.toList.map(ResizeHandle.fromRaw).collect { case Some(x) =>
              x
            }),
            l.isBounded
          )
        )
      else
        None
  }

  final case class Layout(l: List[LayoutItem]) {
    private[gridlayout] def toRaw: raw.Layout = l.toArray.map(_.toRaw).toJSArray
  }

  object Layout {
    implicit val eqLayout: Eq[Layout] = Eq.by(_.l)
    val Empty: Layout                 = Layout(Nil)

    private[gridlayout] def fromRaw(l: raw.Layout): Layout =
      Layout(List(l.map(LayoutItem.fromRaw).toSeq: _*))
  }

  sealed trait CompactType extends Product with Serializable
  object CompactType {
    implicit val enumValue: EnumValue[CompactType] = EnumValue.toLowerCaseString
    case object Vertical   extends CompactType
    case object Horizontal extends CompactType
  }

  sealed trait ResizeHandle extends Product with Serializable
  object ResizeHandle {
    implicit val eqResizeHandle: Eq[ResizeHandle]   = Eq.by(enumValue.value)
    implicit val enumValue: EnumValue[ResizeHandle] = EnumValue.toLowerCaseString

    def fromRaw(s: String): Option[ResizeHandle] = s match {
      case "s"  => Some(S)
      case "w"  => Some(W)
      case "e"  => Some(E)
      case "n"  => Some(N)
      case "sw" => Some(SW)
      case "nw" => Some(NW)
      case "se" => Some(SE)
      case "ne" => Some(NE)
      case _    => None
    }

    case object S  extends ResizeHandle
    case object W  extends ResizeHandle
    case object E  extends ResizeHandle
    case object N  extends ResizeHandle
    case object SW extends ResizeHandle
    case object NW extends ResizeHandle
    case object SE extends ResizeHandle
    case object NE extends ResizeHandle
  }

}
