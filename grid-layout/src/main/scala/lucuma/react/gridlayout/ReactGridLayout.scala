// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
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
  layout:                 Layout = Layout.Empty,
  gridConfig:             js.UndefOr[GridConfig] = js.undefined,
  dragConfig:             js.UndefOr[DragConfig] = js.undefined,
  resizeConfig:           js.UndefOr[ResizeConfig] = js.undefined,
  dropConfig:             js.UndefOr[DropConfig] = js.undefined,
  compactor:              js.UndefOr[Compactor] = js.undefined,
  positionStrategy:       js.UndefOr[PositionStrategy] = js.undefined,
  autoSize:               js.UndefOr[Boolean] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  style:                  js.UndefOr[Style] = js.undefined,
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
    // { cols, rowHeight, margin, containerPadding, maxRows }
    var gridConfig: js.UndefOr[raw.GridConfig]
    // layout is an array of object with the format:
    // {x: Number, y: Number, w: Number, h: Number, i: String}
    var layout: js.UndefOr[raw.Layout]
    // Callback so you can save the layout. Calls after each drag & resize stops.
    var onLayoutChange: raw.RawLayoutChange
  }

  def props(q: ReactGridLayout): ReactGridLayoutProps =
    rawprops(
      q.width,
      q.layout,
      q.gridConfig,
      q.dragConfig,
      q.resizeConfig,
      q.dropConfig,
      q.compactor,
      q.positionStrategy,
      q.autoSize,
      q.className,
      q.style,
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
    layout:           Layout = Layout.Empty,
    gridConfig:       js.UndefOr[GridConfig] = js.undefined,
    dragConfig:       js.UndefOr[DragConfig] = js.undefined,
    resizeConfig:     js.UndefOr[ResizeConfig] = js.undefined,
    dropConfig:       js.UndefOr[DropConfig] = js.undefined,
    compactor:        js.UndefOr[Compactor] = js.undefined,
    positionStrategy: js.UndefOr[PositionStrategy] = js.undefined,
    autoSize:         js.UndefOr[Boolean] = js.undefined,
    className:        js.UndefOr[String] = js.undefined,
    style:            js.UndefOr[Style] = js.undefined,
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
    val r = p.asInstanceOf[ReactGridLayoutProps]
    r.gridConfig = gridConfig.map(_.toRaw)
    r.layout = layout.toRaw
    r.onLayoutChange = (x: raw.Layout) => onLayoutChange(Layout.fromRaw(x)).runNow()
    r
  }

  val component = JsComponent.force[ReactGridLayoutProps, Children.Varargs, Null](RawComponent)

  def apply(width: Double, content: TagMod*): ReactGridLayout =
    new ReactGridLayout(width = width, modifiers = content)

}
