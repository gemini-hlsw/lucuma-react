// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react

package gridlayout

import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.Children
import japgolly.scalajs.react.JsComponent
import japgolly.scalajs.react.vdom.TagMod
import lucuma.react.common.*

import scala.scalajs.js

import js.annotation.JSImport

final case class ReactGridLayout(
  width:                  Double,
  className:              js.UndefOr[String] = js.undefined,
  style:                  js.UndefOr[Style] = js.undefined,
  autoSize:               js.UndefOr[Boolean] = js.undefined,
  cols:                   js.UndefOr[Int] = js.undefined,
  draggableCancel:        js.UndefOr[String] = js.undefined,
  draggableHandle:        js.UndefOr[String] = js.undefined,
  verticalCompact:        js.UndefOr[Boolean] = js.undefined,
  compactType:            js.UndefOr[CompactType] = js.undefined,
  layout:                 Layout = Layout.Empty,
  margin:                 js.UndefOr[Margin] = js.undefined,
  containerPadding:       js.UndefOr[ContainerPadding] = js.undefined,
  rowHeight:              js.UndefOr[Int] = js.undefined,
  maxRows:                js.UndefOr[Int] = js.undefined,
  isDraggable:            js.UndefOr[Boolean] = js.undefined,
  isResizable:            js.UndefOr[Boolean] = js.undefined,
  isBounded:              js.UndefOr[Boolean] = js.undefined,
  isDroppable:            js.UndefOr[Boolean] = js.undefined,
  preventCollision:       js.UndefOr[Boolean] = js.undefined,
  useCSSTransforms:       js.UndefOr[Boolean] = js.undefined,
  transformScale:         js.UndefOr[Double] = js.undefined,
  droppingItem:           js.UndefOr[DroppingItem] = js.undefined,
  resizeHandles:          js.UndefOr[List[ResizeHandle]] = js.undefined,
  onLayoutChange:         OnLayoutChange = _ => Callback.empty,
  onDragStart:            ItemCallback = (_, _, _, _, _, _) => Callback.empty,
  onDrag:                 ItemCallback = (_, _, _, _, _, _) => Callback.empty,
  onDragStop:             ItemCallback = (_, _, _, _, _, _) => Callback.empty,
  onResizeStart:          ItemCallback = (_, _, _, _, _, _) => Callback.empty,
  onResize:               ItemCallback = (_, _, _, _, _, _) => Callback.empty,
  onResizeStop:           ItemCallback = (_, _, _, _, _, _) => Callback.empty,
  onDrop:                 DropCallback = (_, _, _) => Callback.empty,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[ReactGridLayout.ReactGridLayoutProps, ReactGridLayout] {
  override def cprops                               = ReactGridLayout.props(this)
  override protected val component                  = ReactGridLayout.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object ReactGridLayout {

  @js.native
  @JSImport("react-grid-layout", JSImport.Default)
  private object RawComponent extends js.Object

  @js.native
  trait ReactGridLayoutProps extends BaseProps {
    // # of cols.
    var cols: js.UndefOr[Int]
    // layout is an array of object with the format:
    // {x: Number, y: Number, w: Number, h: Number, i: String}
    var layout: js.UndefOr[raw.Layout]
    // Callback so you can save the layout. Calls after each drag & resize stops.
    var onLayoutChange: raw.RawLayoutChange
  }

  def props(q: ReactGridLayout): ReactGridLayoutProps =
    rawprops(
      q.width,
      q.className,
      q.style,
      q.autoSize,
      q.cols,
      q.draggableCancel,
      q.draggableHandle,
      q.verticalCompact,
      q.compactType,
      q.layout,
      q.margin,
      q.containerPadding,
      q.rowHeight,
      q.maxRows,
      q.isDraggable,
      q.isResizable,
      q.isBounded,
      q.isDroppable,
      q.preventCollision,
      q.useCSSTransforms,
      q.transformScale,
      q.droppingItem,
      q.resizeHandles,
      q.onLayoutChange,
      q.onDragStart,
      q.onDrag,
      q.onDragStop,
      q.onResizeStart,
      q.onResize,
      q.onResizeStop,
      q.onDrop
    )

  def rawprops(
    width:            Double,
    className:        js.UndefOr[String] = js.undefined,
    style:            js.UndefOr[Style] = js.undefined,
    autoSize:         js.UndefOr[Boolean] = js.undefined,
    cols:             js.UndefOr[Int] = js.undefined,
    draggableCancel:  js.UndefOr[String] = js.undefined,
    draggableHandle:  js.UndefOr[String] = js.undefined,
    verticalCompact:  js.UndefOr[Boolean] = js.undefined,
    compactType:      js.UndefOr[CompactType] = js.undefined,
    layout:           Layout = Layout.Empty,
    margin:           js.UndefOr[Margin] = js.undefined,
    containerPadding: js.UndefOr[ContainerPadding] = js.undefined,
    rowHeight:        js.UndefOr[Int] = js.undefined,
    maxRows:          js.UndefOr[Int] = js.undefined,
    isDraggable:      js.UndefOr[Boolean] = js.undefined,
    isResizable:      js.UndefOr[Boolean] = js.undefined,
    isBounded:        js.UndefOr[Boolean] = js.undefined,
    isDroppable:      js.UndefOr[Boolean] = js.undefined,
    preventCollision: js.UndefOr[Boolean] = js.undefined,
    useCSSTransforms: js.UndefOr[Boolean] = js.undefined,
    transformScale:   js.UndefOr[Double] = js.undefined,
    droppingItem:     js.UndefOr[DroppingItem] = js.undefined,
    resizeHandles:    js.UndefOr[List[ResizeHandle]] = js.undefined,
    onLayoutChange:   OnLayoutChange = _ => Callback.empty,
    onDragStart:      ItemCallback = (_, _, _, _, _, _) => Callback.empty,
    onDrag:           ItemCallback = (_, _, _, _, _, _) => Callback.empty,
    onDragStop:       ItemCallback = (_, _, _, _, _, _) => Callback.empty,
    onResizeStart:    ItemCallback = (_, _, _, _, _, _) => Callback.empty,
    onResize:         ItemCallback = (_, _, _, _, _, _) => Callback.empty,
    onResizeStop:     ItemCallback = (_, _, _, _, _, _) => Callback.empty,
    onDrop:           DropCallback = (_, _, _) => Callback.empty
  ): ReactGridLayoutProps = {
    val p = BaseProps.props(
      width,
      className,
      style,
      autoSize,
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
      isBounded,
      isDroppable,
      preventCollision,
      useCSSTransforms,
      transformScale,
      droppingItem,
      resizeHandles,
      onDragStart,
      onDrag,
      onDragStop,
      onResizeStart,
      onResize,
      onResizeStop,
      onDrop
    )
    val r = p.asInstanceOf[ReactGridLayoutProps]
    r.cols = cols
    r.layout = layout.toRaw
    r.onLayoutChange = (x: raw.Layout) => onLayoutChange(Layout.fromRaw(x)).runNow()
    r
  }

  val component = JsComponent.force[ReactGridLayoutProps, Children.Varargs, Null](RawComponent)

  def apply(width: Double, content: TagMod*): ReactGridLayout =
    new ReactGridLayout(width = width, modifiers = content)

}
