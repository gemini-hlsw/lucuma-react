package react

package gridlayout

import scala.scalajs.js
import js.annotation.JSImport
import japgolly.scalajs.react._
import japgolly.scalajs.react.component.Js.RawMounted
import japgolly.scalajs.react.component.Js.UnmountedMapped
import japgolly.scalajs.react.internal.Effect.Id
import japgolly.scalajs.react.raw.JsNumber
import japgolly.scalajs.react.vdom.VdomNode
import react.common._
// import react.common.syntax._
// import org.scalajs.dom.html.{Element => HTMLElement}
// import org.scalajs.dom.MouseEvent

trait ResponsiveReactGridLayout extends js.Object

object ResponsiveReactGridLayout {

  @js.native
  @JSImport("react-grid-layout", JSImport.Default)
  object RawComponent extends js.Object

  @js.native
  trait Props extends ReactGridLayout.Props {
    var breakpoints: js.Object = js.native
    var columns: js.Object     = js.native
  }

  def props(
    width:            JsNumber,
    breakpoints:      Breakpoints,
    columns:          Columns,
    className:        js.UndefOr[String] = js.undefined,
    style:            js.UndefOr[Style] = js.undefined,
    autoSize:         js.UndefOr[Boolean] = js.undefined,
    cols:             js.UndefOr[Int] = js.undefined,
    draggableCancel:  js.UndefOr[String] = js.undefined,
    draggableHandle:  js.UndefOr[String] = js.undefined,
    compactType:      js.UndefOr[CompactType] = js.undefined,
    layout:           Layout = Layout.Empty,
    margin:           js.UndefOr[Margin] = js.undefined,
    containerPadding: js.UndefOr[ContainerPadding] = js.undefined,
    rowHeight:        js.UndefOr[Int] = js.undefined,
    maxRows:          js.UndefOr[Int] = js.undefined,
    onLayoutChange:   OnLayoutChange = _ => Callback.empty,
    onDragStart:      ItemCallback = (_, _, _, _, _, _) => Callback.empty,
    onDrag:           ItemCallback = (_, _, _, _, _, _) => Callback.empty,
    onDragStop:       ItemCallback = (_, _, _, _, _, _) => Callback.empty,
    onResizeStart:    ItemCallback = (_, _, _, _, _, _) => Callback.empty,
    onResize:         ItemCallback = (_, _, _, _, _, _) => Callback.empty,
    onResizeStop:     ItemCallback = (_, _, _, _, _, _) => Callback.empty
  ): Props = {
    val p = ReactGridLayout.props(
      width,
      className,
      style,
      autoSize,
      cols,
      draggableCancel,
      draggableHandle,
      compactType,
      layout,
      margin,
      containerPadding,
      rowHeight,
      maxRows,
      onLayoutChange,
      onDragStart,
      onDrag,
      onDragStop,
      onResizeStart,
      onResize,
      onResizeStop
    )
    val r = p.asInstanceOf[Props]
    r.breakpoints = breakpoints.toRaw
    r.columns     = columns.toRaw
    r
  }

  def build(values: Map[BreakpointName, (JsNumber, JsNumber)]): (Breakpoints, Columns) =
    (Breakpoints(values.collect {
      case (v, (w, _)) => Breakpoint(v, w)
    }.toList), Columns(values.collect {
      case (v, (_, c)) => Column(v, c)
    }.toList))

  val component = JsComponent[Props, Children.Varargs, Null](RawComponent)

  def apply(
    p:        Props,
    children: VdomNode*): UnmountedMapped[Id, Props, Null, RawMounted[Props, Null], Props, Null] =
    component.apply(p)(children: _*)
}
