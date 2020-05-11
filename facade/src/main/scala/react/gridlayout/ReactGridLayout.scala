package react

package gridlayout

import scala.scalajs.js
import js.annotation.JSImport
import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.JsComponent
import japgolly.scalajs.react.Children
import japgolly.scalajs.react.raw.JsNumber
import japgolly.scalajs.react.vdom.TagMod
import react.common._

final case class ReactGridLayout(
  width:                  JsNumber,
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
  isDroppable:            js.UndefOr[Boolean] = js.undefined,
  preventCollision:       js.UndefOr[Boolean] = js.undefined,
  useCSSTransforms:       js.UndefOr[Boolean] = js.undefined,
  transformScale:         js.UndefOr[JsNumber] = js.undefined,
  droppingItem:           js.UndefOr[DroppingItem] = js.undefined,
  onLayoutChange:         OnLayoutChange = _ => Callback.empty,
  onDragStart:            ItemCallback = (_, _, _, _, _, _) => Callback.empty,
  onDrag:                 ItemCallback = (_, _, _, _, _, _) => Callback.empty,
  onDragStop:             ItemCallback = (_, _, _, _, _, _) => Callback.empty,
  onResizeStart:          ItemCallback = (_, _, _, _, _, _) => Callback.empty,
  onResize:               ItemCallback = (_, _, _, _, _, _) => Callback.empty,
  onResizeStop:           ItemCallback = (_, _, _, _, _, _) => Callback.empty,
  onDrop:                 DropCallback = (_, _, _, _) => Callback.empty,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[ReactGridLayout.ReactGridLayoutProps, ReactGridLayout] {
  override def cprops              = ReactGridLayout.props(this)
  override protected val component = ReactGridLayout.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object ReactGridLayout                                                                 {

  @js.native
  @JSImport("react-grid-layout", JSImport.Default)
  private object RawComponent extends js.Object

  @js.native
  trait ReactGridLayoutProps extends BaseProps {
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
      q.onDrop
    )

  def rawprops(
    width:            JsNumber,
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
    isDroppable:      js.UndefOr[Boolean] = js.undefined,
    preventCollision: js.UndefOr[Boolean] = js.undefined,
    useCSSTransforms: js.UndefOr[Boolean] = js.undefined,
    transformScale:   js.UndefOr[JsNumber] = js.undefined,
    droppingItem:     js.UndefOr[DroppingItem] = js.undefined,
    onLayoutChange:   OnLayoutChange = _ => Callback.empty,
    onDragStart:      ItemCallback = (_, _, _, _, _, _) => Callback.empty,
    onDrag:           ItemCallback = (_, _, _, _, _, _) => Callback.empty,
    onDragStop:       ItemCallback = (_, _, _, _, _, _) => Callback.empty,
    onResizeStart:    ItemCallback = (_, _, _, _, _, _) => Callback.empty,
    onResize:         ItemCallback = (_, _, _, _, _, _) => Callback.empty,
    onResizeStop:     ItemCallback = (_, _, _, _, _, _) => Callback.empty,
    onDrop:           DropCallback = (_, _, _, _) => Callback.empty
  ): ReactGridLayoutProps = {
    val p = BaseProps.props(
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
    val r = p.asInstanceOf[ReactGridLayoutProps]
    r.layout = layout.toRaw
    r.onLayoutChange = (x: raw.Layout) => onLayoutChange(Layout.fromRaw(x)).runNow()
    r
  }

  val component           = JsComponent[ReactGridLayoutProps, Children.Varargs, Null](RawComponent)

  def apply(width: JsNumber, content: TagMod*): ReactGridLayout =
    new ReactGridLayout(width = width, modifiers = content)
}
