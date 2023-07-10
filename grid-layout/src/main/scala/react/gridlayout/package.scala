// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react

import cats.*
import japgolly.scalajs.react.Callback
import org.scalajs.dom.Event
import org.scalajs.dom.MouseEvent
import org.scalajs.dom.html.{Element => HTMLElement}
import react.common.*

import scala.annotation.nowarn
import scala.scalajs.js

import js.annotation.JSImport
import js.JSConverters.*

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
    // Method from js lanf to get the breakpoint from the width
    @nowarn
    def getBreakpointFromWidth(breakpoints: js.Dictionary[Int], width: Double): String = js.native
  }

  opaque type BreakpointName = String

  object BreakpointName {
    inline def apply(x: String): BreakpointName = x

    extension (x: BreakpointName) inline def name: String = x

    given (using ord: Order[String]): Order[BreakpointName]       = ord
    given (using ord: Ordering[String]): Ordering[BreakpointName] = ord

    val xxl: BreakpointName = "xxl"
    val xl: BreakpointName  = "xl"
    val lg: BreakpointName  = "lg"
    val md: BreakpointName  = "md"
    val sm: BreakpointName  = "sm"
    val xs: BreakpointName  = "xs"
    val xxs: BreakpointName = "xxs"

    val predefined: List[BreakpointName] = List(xxl, xl, lg, md, sm, xs, xxs)
  }

  case class Breakpoint(name: BreakpointName, pos: Int)

  object Breakpoint {
    given Eq[Breakpoint] = Eq.by(x => (x.name, x.pos))
  }

  case class Breakpoints(bps: List[Breakpoint]) {
    def toRaw: js.Object = {
      val p = js.Dynamic.literal()
      bps.foreach { case Breakpoint(name, v) => p.updateDynamic(name.name)(v.asInstanceOf[js.Any]) }
      p
    }
  }

  object Breakpoints {
    given Eq[Breakpoints] = Eq.by(_.bps)
  }

  case class Column(col: BreakpointName, pos: Int)
  object Column {
    given Eq[Column] = Eq.by(x => (x.col, x.pos))
  }

  opaque type Columns = List[Column]

  object Columns {
    given (using eq: Eq[List[Column]]): Eq[Columns] = eq

    inline def apply(x: List[Column]): Columns = x

    extension (x: Columns)
      inline def cols: List[Column] = x

      def toRaw: js.Object = {
        val p = js.Dynamic.literal()
        x.foreach { case Column(name, v) => p.updateDynamic(name.name)(v.asInstanceOf[js.Any]) }
        p
      }
  }

  case class DroppingItem(
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

  case class BreakpointLayout(name: BreakpointName, layout: Layout)

  object BreakpointLayout {
    given Eq[BreakpointLayout] = Eq.by(x => (x.name, x.layout))

    private[gridlayout] def layoutsFromRaw(l: js.Object): Layout = {
      val c                   = l.asInstanceOf[js.Array[raw.LayoutItem]]
      val i: List[LayoutItem] = c.map(LayoutItem.fromRaw).toList
      Layout(i)
    }
  }

  case class Layouts(layouts: List[BreakpointLayout]) {
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

  case class LayoutItem(
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
    private given eqUndef[A: Eq]: Eq[js.UndefOr[A]] = Eq.by(_.toOption)
    given eqLayoutItem: Eq[LayoutItem]              = Eq.by(x =>
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

  opaque type Layout = List[LayoutItem]

  object Layout {
    inline def apply(l: List[LayoutItem]): Layout = l

    extension (l: Layout)
      private[gridlayout] inline def toRaw: raw.Layout = l.toArray.map(_.toRaw).toJSArray

    given (using eq: Eq[List[LayoutItem]]): Eq[Layout] = eq

    val Empty: Layout = Layout(Nil)

    private[gridlayout] def fromRaw(l: raw.Layout): Layout =
      Layout(List(l.map(LayoutItem.fromRaw).toSeq: _*))
  }

  enum CompactType {
    case Vertical, Horizontal
  }

  object CompactType {
    given EnumValue[CompactType] = EnumValue.toLowerCaseString
  }

  enum ResizeHandle {
    case S, W, E, N, SW, NW, SE, NE
  }

  object ResizeHandle {

    given enumValue: EnumValue[ResizeHandle] = EnumValue.toLowerCaseString
    given Eq[ResizeHandle]                   = Eq.by(enumValue.value)

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

  }

}
