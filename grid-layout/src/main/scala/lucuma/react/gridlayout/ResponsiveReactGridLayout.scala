// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react

package gridlayout

import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.Children
import japgolly.scalajs.react.JsComponent
import japgolly.scalajs.react.vdom.TagMod
import lucuma.react.common.*
import lucuma.react.common.Style

import scala.scalajs.js

import js.annotation.JSImport

final case class ResponsiveReactGridLayout(
  width:                  Double,
  breakpoints:            Map[BreakpointName, Int],
  cols:                   Map[BreakpointName, Int],
  layouts:                ResponsiveLayouts,
  rowHeight:              js.UndefOr[Int] = js.undefined,
  maxRows:                js.UndefOr[Int] = js.undefined,
  margin:                 js.UndefOr[Margin] = js.undefined,
  containerPadding:       js.UndefOr[ContainerPadding] = js.undefined,
  dragConfig:             js.UndefOr[DragConfig] = js.undefined,
  resizeConfig:           js.UndefOr[ResizeConfig] = js.undefined,
  dropConfig:             js.UndefOr[DropConfig] = js.undefined,
  compactor:              js.UndefOr[Compactor] = js.undefined,
  positionStrategy:       js.UndefOr[PositionStrategy] = js.undefined,
  autoSize:               js.UndefOr[Boolean] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  style:                  js.UndefOr[Style] = js.undefined,
  onLayoutChange:         OnLayoutsChange = (_, _) => Callback.empty,
  onBreakpointChange:     OnBreakpointChange = (_, _) => Callback.empty,
  onWidthChange:          OnWidthChange = (_, _, _, _) => Callback.empty,
  onDragStart:            ItemCallback = (_, _, _, _, _, _) => Callback.empty,
  onDrag:                 ItemCallback = (_, _, _, _, _, _) => Callback.empty,
  onDragStop:             ItemCallback = (_, _, _, _, _, _) => Callback.empty,
  onResizeStart:          ItemCallback = (_, _, _, _, _, _) => Callback.empty,
  onResize:               ItemCallback = (_, _, _, _, _, _) => Callback.empty,
  onResizeStop:           ItemCallback = (_, _, _, _, _, _) => Callback.empty,
  onDrop:                 DropCallback = (_, _, _) => Callback.empty,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[
      ResponsiveReactGridLayout.ResponsiveReactGridLayoutProps,
      ResponsiveReactGridLayout
    ] {
  override def cprops                               = ResponsiveReactGridLayout.props(this)
  override protected val component                  = ResponsiveReactGridLayout.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object ResponsiveReactGridLayout {

  @js.native
  @JSImport("react-grid-layout", "Responsive")
  object RawComponent extends js.Object

  @js.native
  trait ResponsiveReactGridLayoutProps extends BaseProps {
    // {name: pxVal}, e.g. {lg: 1200, md: 996, sm: 768, xs: 480}
    var breakpoints: js.Object = js.native
    // # of cols. This is a breakpoint -> cols map, e.g. {lg: 12, md: 10, ...}
    var cols: js.Object        = js.native
    // layouts is an object mapping breakpoints to layouts, e.g. {lg: Layout, md: Layout, ...}
    var layouts: js.Object     = js.native

    // On the responsive component these grid metrics remain flat props (not in gridConfig).
    var rowHeight: js.UndefOr[Int]                     = js.native
    var maxRows: js.UndefOr[Int]                       = js.native
    var margin: js.UndefOr[js.Array[Double]]           = js.native
    var containerPadding: js.UndefOr[js.Array[Double]] = js.native

    // Calls back with breakpoint and new # cols
    var onBreakpointChange: raw.BreakpointChangeCallback = js.native
    // Callback so you can save the layout. allLayouts are keyed by breakpoint.
    var onLayoutChange: raw.LayoutChangeCallback         = js.native
    // (containerWidth, margin: [n, n], cols, containerPadding: [n, n] | null)
    var onWidthChange: raw.WidthChangeCallback           = js.native
  }

  private def intMapToRaw(m: Map[BreakpointName, Int]): js.Object = {
    val p = js.Dynamic.literal()
    m.foreach { case (k, v) => p.updateDynamic(k.name)(v.asInstanceOf[js.Any]) }
    p
  }

  private def layoutsToRaw(m: ResponsiveLayouts): js.Object = {
    val p = js.Dynamic.literal()
    m.foreach { case (k, v) => p.updateDynamic(k.name)(v.toRaw) }
    p
  }

  private def layoutsFromRaw(o: js.Object): ResponsiveLayouts = {
    val dict = o.asInstanceOf[js.Dictionary[js.Any]]
    js.Object
      .getOwnPropertyNames(o)
      .flatMap(p =>
        dict.get(p).map(v => BreakpointName(p) -> Layout.fromRaw(v.asInstanceOf[raw.Layout]))
      )
      .toMap
  }

  def props(q: ResponsiveReactGridLayout): ResponsiveReactGridLayoutProps =
    rawprops(
      q.width,
      q.breakpoints,
      q.cols,
      q.layouts,
      q.rowHeight,
      q.maxRows,
      q.margin,
      q.containerPadding,
      q.dragConfig,
      q.resizeConfig,
      q.dropConfig,
      q.compactor,
      q.positionStrategy,
      q.autoSize,
      q.className,
      q.style,
      q.onLayoutChange,
      q.onBreakpointChange,
      q.onWidthChange,
      q.onDragStart,
      q.onDrag,
      q.onDragStop,
      q.onResizeStart,
      q.onResize,
      q.onResizeStop,
      q.onDrop
    )

  def rawprops(
    width:              Double,
    breakpoints:        Map[BreakpointName, Int],
    cols:               Map[BreakpointName, Int],
    layouts:            ResponsiveLayouts,
    rowHeight:          js.UndefOr[Int] = js.undefined,
    maxRows:            js.UndefOr[Int] = js.undefined,
    margin:             js.UndefOr[Margin] = js.undefined,
    containerPadding:   js.UndefOr[ContainerPadding] = js.undefined,
    dragConfig:         js.UndefOr[DragConfig] = js.undefined,
    resizeConfig:       js.UndefOr[ResizeConfig] = js.undefined,
    dropConfig:         js.UndefOr[DropConfig] = js.undefined,
    compactor:          js.UndefOr[Compactor] = js.undefined,
    positionStrategy:   js.UndefOr[PositionStrategy] = js.undefined,
    autoSize:           js.UndefOr[Boolean] = js.undefined,
    className:          js.UndefOr[String] = js.undefined,
    style:              js.UndefOr[Style] = js.undefined,
    onLayoutChange:     OnLayoutsChange = (_, _) => Callback.empty,
    onBreakpointChange: OnBreakpointChange = (_, _) => Callback.empty,
    onWidthChange:      OnWidthChange = (_, _, _, _) => Callback.empty,
    onDragStart:        ItemCallback = (_, _, _, _, _, _) => Callback.empty,
    onDrag:             ItemCallback = (_, _, _, _, _, _) => Callback.empty,
    onDragStop:         ItemCallback = (_, _, _, _, _, _) => Callback.empty,
    onResizeStart:      ItemCallback = (_, _, _, _, _, _) => Callback.empty,
    onResize:           ItemCallback = (_, _, _, _, _, _) => Callback.empty,
    onResizeStop:       ItemCallback = (_, _, _, _, _, _) => Callback.empty,
    onDrop:             DropCallback = (_, _, _) => Callback.empty
  ): ResponsiveReactGridLayoutProps = {
    val p = BaseProps.props(
      width,
      className,
      style,
      autoSize,
      dragConfig,
      resizeConfig,
      dropConfig,
      compactor,
      positionStrategy,
      onDragStart,
      onDrag,
      onDragStop,
      onResizeStart,
      onResize,
      onResizeStop,
      onDrop
    )
    val r = p.asInstanceOf[ResponsiveReactGridLayoutProps]
    r.breakpoints = intMapToRaw(breakpoints)
    r.cols = intMapToRaw(cols)
    r.layouts = layoutsToRaw(layouts)
    r.rowHeight = rowHeight
    r.maxRows = maxRows
    r.margin = margin.map(m => js.Array(m._1.toDouble, m._2.toDouble))
    r.containerPadding = containerPadding.map(p => js.Array(p._1.toDouble, p._2.toDouble))
    r.onBreakpointChange = (newBreakpoint: raw.Breakpoint, newCol: Int) =>
      onBreakpointChange(BreakpointName(newBreakpoint), newCol).runNow()
    r.onLayoutChange = (currentLayout: raw.Layout, allLayouts: js.Object) =>
      onLayoutChange(Layout.fromRaw(currentLayout), layoutsFromRaw(allLayouts)).runNow()
    r.onWidthChange = (
      containerWidth:   Int,
      margin:           js.Array[Int],
      cols:             Int,
      containerPadding: js.Array[Int]
    ) =>
      onWidthChange(containerWidth,
                    (margin(0).toInt, margin(1).toInt),
                    cols,
                    (containerPadding(0).toInt, containerPadding(1).toInt)
      ).runNow()
    r
  }

  val component =
    JsComponent.force[ResponsiveReactGridLayoutProps, Children.Varargs, Null](RawComponent)

  def apply(
    width:       Double,
    breakpoints: Map[BreakpointName, Int],
    cols:        Map[BreakpointName, Int],
    layouts:     ResponsiveLayouts,
    content:     TagMod*
  ): ResponsiveReactGridLayout =
    new ResponsiveReactGridLayout(
      width = width,
      breakpoints = breakpoints,
      cols = cols,
      layouts = layouts,
      modifiers = content
    )

}
