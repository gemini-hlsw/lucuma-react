// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.gridlayout

import cats.*
import cats.derived.*
import japgolly.scalajs.react.Callback
import lucuma.react.common.*
import org.scalajs.dom.Event
import org.scalajs.dom.html.Element as HTMLElement

import scala.scalajs.js

import js.JSConverters.*

type Margin           = (Int, Int)
type ContainerPadding = (Int, Int)

type ResponsiveLayouts = Map[BreakpointName, Layout]

type OnLayoutChange     = Layout => Callback
type OnLayoutsChange    = (Layout, ResponsiveLayouts) => Callback
type OnBreakpointChange = (BreakpointName, Int) => Callback
type OnWidthChange      = (Int, Margin, Int, ContainerPadding) => Callback

// v2 EventCallback: (layout, oldItem|null, newItem|null, placeholder|null, event, element?)
type ItemCallback =
  (
    Layout,
    Option[LayoutItem],
    Option[LayoutItem],
    Option[LayoutItem],
    Event,
    Option[HTMLElement]
  ) => Callback
type DropCallback =
  (Layout, Option[LayoutItem], Event) => Callback

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

def getBreakpointFromWidth(breakpoints: Map[BreakpointName, Int], width: Int): BreakpointName = {
  import BreakpointName.*

  val currentBreakpoints: scala.collection.mutable.Map[String, Int] =
    scala.collection.mutable.Map.from(
      breakpoints
        .map { case (x, w) => x.name -> w }
    )
  BreakpointName(
    raw.Core.getBreakpointFromWidth(currentBreakpoints.toJSDictionary, width.toDouble)
  )
}

// { cols, rowHeight, margin, containerPadding, maxRows } — react-grid-layout v2 GridConfig.
case class GridConfig(
  cols:             js.UndefOr[Int] = js.undefined,
  rowHeight:        js.UndefOr[Int] = js.undefined,
  margin:           js.UndefOr[Margin] = js.undefined,
  containerPadding: js.UndefOr[ContainerPadding] = js.undefined,
  maxRows:          js.UndefOr[Int] = js.undefined
) {
  def toRaw: raw.GridConfig =
    BaseProps.pruneUndef(
      new raw.GridConfig(
        cols,
        rowHeight,
        margin.map(m => js.Array(m._1.toDouble, m._2.toDouble)),
        containerPadding.map(p => js.Array(p._1.toDouble, p._2.toDouble)),
        maxRows
      )
    )
}

// { enabled, bounded, handle, cancel, threshold } — v2 DragConfig.
case class DragConfig(
  enabled:   js.UndefOr[Boolean] = js.undefined,
  bounded:   js.UndefOr[Boolean] = js.undefined,
  handle:    js.UndefOr[String] = js.undefined,
  cancel:    js.UndefOr[String] = js.undefined,
  threshold: js.UndefOr[Int] = js.undefined
) {
  def toRaw: raw.DragConfig =
    BaseProps.pruneUndef(new raw.DragConfig(enabled, bounded, handle, cancel, threshold))
}

// { enabled, handles } — v2 ResizeConfig.
case class ResizeConfig(
  enabled: js.UndefOr[Boolean] = js.undefined,
  handles: js.UndefOr[List[ResizeHandle]] = js.undefined
) {
  def toRaw: raw.ResizeConfig =
    BaseProps.pruneUndef(new raw.ResizeConfig(enabled, handles.map(_.toJSArray.map(_.toJs))))
}

// { enabled, defaultItem } — v2 DropConfig (defaultItem carries w/h).
case class DropConfig(
  enabled:     js.UndefOr[Boolean] = js.undefined,
  defaultItem: js.UndefOr[DroppingItem] = js.undefined
) {
  def toRaw: raw.DropConfig =
    BaseProps.pruneUndef(new raw.DropConfig(enabled, defaultItem.map(_.toRaw)))
}

// Pluggable compaction strategy. Built-ins and a factory mirror react-grid-layout/core.
type Compactor = raw.Compactor

object Compactor {
  private def mk(compactType: js.UndefOr[String]): Compactor =
    raw.Core.getCompactor(compactType, js.undefined, js.undefined)

  lazy val vertical: Compactor   = mk("vertical")
  lazy val horizontal: Compactor = mk("horizontal")
  lazy val wrap: Compactor       = mk("wrap")
  lazy val none: Compactor       = mk(null)

  def apply(
    compactType:      CompactType,
    allowOverlap:     js.UndefOr[Boolean] = js.undefined,
    preventCollision: js.UndefOr[Boolean] = js.undefined
  ): Compactor =
    raw.Core.getCompactor(CompactType.toJs(compactType), allowOverlap, preventCollision)
}

// CSS positioning strategy. Mirrors react-grid-layout/core strategies.
type PositionStrategy = raw.PositionStrategy

object PositionStrategy {
  lazy val transform: PositionStrategy        = raw.Core.transformStrategy
  lazy val absolute: PositionStrategy         = raw.Core.absoluteStrategy
  def scaled(scale: Double): PositionStrategy = raw.Core.createScaledStrategy(scale)
}

case class DroppingItem(
  i: String,
  w: Int,
  h: Int
) {
  def toRaw: raw.DropDefaultItem = new raw.DropDefaultItem(w, h)
}

case class LayoutItem(
  w:             Int,
  h:             Int,
  x:             Int,
  y:             Int,
  i:             String,
  minW:          js.UndefOr[Int] = js.undefined,
  minH:          js.UndefOr[Int] = js.undefined,
  maxW:          js.UndefOr[Int] = js.undefined,
  maxH:          js.UndefOr[Int] = js.undefined,
  static:        js.UndefOr[Boolean] = js.undefined,
  isDraggable:   js.UndefOr[Boolean] = js.undefined,
  isResizable:   js.UndefOr[Boolean] = js.undefined,
  resizeHandles: js.UndefOr[List[ResizeHandle]] = js.undefined,
  isBounded:     js.UndefOr[Boolean] = js.undefined
) derives Eq {
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
    if (l != null) Some(fromRaw(l)) else None
}

opaque type Layout = List[LayoutItem]

object Layout {
  inline def apply(l: List[LayoutItem]): Layout = l

  extension (l: Layout)
    private[gridlayout] inline def toRaw: raw.Layout = l.toArray.map(_.toRaw).toJSArray
    inline def asList: List[LayoutItem]              = l

  given (using eq: Eq[List[LayoutItem]]): Eq[Layout] = eq

  val Empty: Layout = Layout(Nil)

  private[gridlayout] def fromRaw(l: raw.Layout): Layout =
    Layout(List(l.map(LayoutItem.fromRaw).toSeq*))
}

enum CompactType {
  case Vertical, Horizontal, Wrap
}

object CompactType {
  given EnumValue[CompactType] = EnumValue.toLowerCaseString

  private[gridlayout] def toJs(c: CompactType): String = c match {
    case Vertical   => "vertical"
    case Horizontal => "horizontal"
    case Wrap       => "wrap"
  }
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
