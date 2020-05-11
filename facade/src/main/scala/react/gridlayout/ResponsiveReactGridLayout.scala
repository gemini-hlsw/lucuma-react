package react

package gridlayout

import scala.scalajs.js
import js.annotation.JSImport
import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.Children
import japgolly.scalajs.react.JsComponent
import japgolly.scalajs.react.raw.JsNumber
import react.common.Style
import react.common._
import japgolly.scalajs.react.vdom.TagMod

final case class ResponsiveReactGridLayout(
  width:                  JsNumber,
  layouts:                Map[BreakpointName, (JsNumber, JsNumber, Layout)],
  className:              js.UndefOr[String] = js.undefined,
  style:                  js.UndefOr[Style] = js.undefined,
  autoSize:               js.UndefOr[Boolean] = js.undefined,
  cols:                   js.UndefOr[Int] = js.undefined,
  draggableCancel:        js.UndefOr[String] = js.undefined,
  draggableHandle:        js.UndefOr[String] = js.undefined,
  verticalCompact:        js.UndefOr[Boolean] = js.undefined,
  compactType:            js.UndefOr[CompactType] = js.undefined,
  margin:                 js.UndefOr[Margin] = js.undefined,
  containerPadding:       js.UndefOr[ContainerPadding] = js.undefined,
  rowHeight:              js.UndefOr[Int] = js.undefined,
  maxRows:                js.UndefOr[Int] = js.undefined,
  isDraggable:            js.UndefOr[Boolean] = js.undefined,
  isResizable:            js.UndefOr[Boolean] = js.undefined,
  isDroppable:            js.UndefOr[Boolean] = js.undefined,
  preventCollision:       js.UndefOr[Boolean] = js.undefined,
  useCSSTransforms:       js.UndefOr[Boolean] = js.undefined,
  transformScale:         js.UndefOr[JsNumber] = js.undefined,
  droppingItem:           js.UndefOr[DroppingItem] = js.undefined,
  onLayoutChange:         OnLayoutsChange = (_, _) => Callback.empty,
  onDragStart:            ItemCallback = (_, _, _, _, _, _) => Callback.empty,
  onDrag:                 ItemCallback = (_, _, _, _, _, _) => Callback.empty,
  onDragStop:             ItemCallback = (_, _, _, _, _, _) => Callback.empty,
  onResizeStart:          ItemCallback = (_, _, _, _, _, _) => Callback.empty,
  onResize:               ItemCallback = (_, _, _, _, _, _) => Callback.empty,
  onResizeStop:           ItemCallback = (_, _, _, _, _, _) => Callback.empty,
  onDrop:                 DropCallback = (_, _, _, _) => Callback.empty,
  onBreakpointChange:     OnBreakpointChange = (_, _) => Callback.empty,
  onWidthChange:          OnWidthChange = (_, _, _, _) => Callback.empty,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[
      ResponsiveReactGridLayout.ResponsiveReactGridLayoutProps,
      ResponsiveReactGridLayout
    ]                            {
  override def cprops              = ResponsiveReactGridLayout.props(this)
  override protected val component = ResponsiveReactGridLayout.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object ResponsiveReactGridLayout {

  @js.native
  @JSImport("react-grid-layout", "Responsive")
  object RawComponent extends js.Object

  @js.native
  trait ResponsiveReactGridLayoutProps extends BaseProps {
    // {name: pxVal}, e.g. {lg: 1200, md: 996, sm: 768, xs: 480}
    // Breakpoint names are arbitrary but must match in the cols and layouts objects.
    var breakpoints: js.Object = js.native
    // # of cols. This is a breakpoint -> cols map, e.g. {lg: 12, md: 10, ...}
    var columns: js.Object     = js.native
    // layouts is an object mapping breakpoints to layouts.
    // e.g. {lg: Layout, md: Layout, ...}
    var layouts: js.Object     = js.native

    // Calls back with breakpoint and new # cols
    var onBreakpointChange: raw.BreakpointChangeCallback = js.native

    // Callback so you can save the layout.
    // AllLayouts are keyed by breakpoint.
    var onLayoutChange: raw.LayoutChangeCallback = js.native
    // (currentLayout: Layout, allLayouts: {[key: $Keys<breakpoints>]: Layout}) => void,
    // Callback when the width changes, so you can modify the layout as needed.
    var onWidthChange: raw.WidthChangeCallback   = js.native
    //(containerWidth: number, margin: [number, number], cols: number, containerPadding: [number, number]) => void;

  }

  def props(q: ResponsiveReactGridLayout): ResponsiveReactGridLayoutProps =
    rawprops(
      q.width,
      q.layouts,
      q.className,
      q.style,
      q.autoSize,
      q.cols,
      q.draggableCancel,
      q.draggableHandle,
      q.verticalCompact,
      q.compactType,
      q.margin,
      q.containerPadding,
      q.rowHeight,
      q.maxRows,
      q.isDraggable,
      q.isResizable,
      q.isDroppable,
      q.preventCollision,
      q.useCSSTransforms,
      q.transformScale,
      q.droppingItem,
      q.onLayoutChange,
      q.onDragStart,
      q.onDrag,
      q.onDragStop,
      q.onResizeStart,
      q.onResize,
      q.onResizeStop,
      q.onDrop,
      q.onBreakpointChange,
      q.onWidthChange
    )

  def rawprops(
    width:              JsNumber,
    layouts:            Map[BreakpointName, (JsNumber, JsNumber, Layout)],
    className:          js.UndefOr[String] = js.undefined,
    style:              js.UndefOr[Style] = js.undefined,
    autoSize:           js.UndefOr[Boolean] = js.undefined,
    cols:               js.UndefOr[Int] = js.undefined,
    draggableCancel:    js.UndefOr[String] = js.undefined,
    draggableHandle:    js.UndefOr[String] = js.undefined,
    verticalCompact:    js.UndefOr[Boolean] = js.undefined,
    compactType:        js.UndefOr[CompactType] = js.undefined,
    margin:             js.UndefOr[Margin] = js.undefined,
    containerPadding:   js.UndefOr[ContainerPadding] = js.undefined,
    rowHeight:          js.UndefOr[Int] = js.undefined,
    maxRows:            js.UndefOr[Int] = js.undefined,
    isDraggable:        js.UndefOr[Boolean] = js.undefined,
    isResizable:        js.UndefOr[Boolean] = js.undefined,
    isDroppable:        js.UndefOr[Boolean] = js.undefined,
    preventCollision:   js.UndefOr[Boolean] = js.undefined,
    useCSSTransforms:   js.UndefOr[Boolean] = js.undefined,
    transformScale:     js.UndefOr[JsNumber] = js.undefined,
    droppingItem:       js.UndefOr[DroppingItem] = js.undefined,
    onLayoutChange:     OnLayoutsChange = (_, _) => Callback.empty,
    onDragStart:        ItemCallback = (_, _, _, _, _, _) => Callback.empty,
    onDrag:             ItemCallback = (_, _, _, _, _, _) => Callback.empty,
    onDragStop:         ItemCallback = (_, _, _, _, _, _) => Callback.empty,
    onResizeStart:      ItemCallback = (_, _, _, _, _, _) => Callback.empty,
    onResize:           ItemCallback = (_, _, _, _, _, _) => Callback.empty,
    onResizeStop:       ItemCallback = (_, _, _, _, _, _) => Callback.empty,
    onDrop:             DropCallback = (_, _, _, _) => Callback.empty,
    onBreakpointChange: OnBreakpointChange = (_, _) => Callback.empty,
    onWidthChange:      OnWidthChange = (_, _, _, _) => Callback.empty
  ): ResponsiveReactGridLayoutProps  = {
    val p                                           = BaseProps.props(
      width,
      className,
      style,
      autoSize,
      cols,
      draggableCancel,
      draggableHandle,
      verticalCompact,
      compactType,
      margin,
      containerPadding,
      rowHeight,
      maxRows,
      isDraggable,
      isResizable,
      isDroppable,
      preventCollision,
      useCSSTransforms,
      transformScale,
      droppingItem,
      onDragStart,
      onDrag,
      onDragStop,
      onResizeStart,
      onResize,
      onResizeStop,
      onDrop
    )
    val r                                           = p.asInstanceOf[ResponsiveReactGridLayoutProps]
    val (br: Breakpoints, cl: Columns, ly: Layouts) = build(layouts)
    r.breakpoints = br.toRaw
    r.columns = cl.toRaw
    r.layouts = ly.toRaw
    r.onBreakpointChange = (newBreakpoint: raw.Breakpoint, newCol: JsNumber) =>
      onBreakpointChange(BreakpointName(newBreakpoint), newCol).runNow()
    r.onLayoutChange = (currentLayout: raw.Layout, allLayouts: js.Object) => {
      onLayoutChange(Layout.fromRaw(currentLayout), Layouts.fromRaw(allLayouts)).runNow()
    }
    r.onWidthChange = (
      containerWidth:   JsNumber,
      margin:           js.Array[JsNumber],
      cols:             JsNumber,
      containerPadding: js.Array[JsNumber]
    ) =>
      onWidthChange(containerWidth,
                    (margin(0), margin(1)),
                    cols,
                    (containerPadding(0), containerPadding(1))
      ).runNow()
    r
  }

  def build(
    values: Map[BreakpointName, (JsNumber, JsNumber, Layout)]
  ): (Breakpoints, Columns, Layouts) =
    (Breakpoints(values.collect {
       case (v, (w, _, _)) => Breakpoint(v, w)
     }.toList),
     Columns(values.collect {
       case (v, (_, c, _)) => Column(v, c)
     }.toList),
     Layouts(values.collect {
       case (v, (_, _, l)) => BreakpointLayout(v, l)
     }.toList)
    )

  val component                      = JsComponent[ResponsiveReactGridLayoutProps, Children.Varargs, Null](RawComponent)

  def apply(
    width:   JsNumber,
    layouts: Map[BreakpointName, (JsNumber, JsNumber, Layout)],
    content: TagMod*
  ): ResponsiveReactGridLayout =
    new ResponsiveReactGridLayout(width = width, layouts = layouts, modifiers = content)
}
