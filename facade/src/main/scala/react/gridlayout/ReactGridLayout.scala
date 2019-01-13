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
import org.scalajs.dom.html.{ Element => HTMLElement }
import org.scalajs.dom.MouseEvent
import react.common._

trait ReactGridLayout extends js.Object

object ReactGridLayout {

  @js.native
  @JSImport("react-grid-layout", JSImport.Default)
  object RawComponent extends js.Object

  @js.native
  trait Props extends js.Object {
    var className: js.UndefOr[String]
    var style: js.UndefOr[js.Object]
    var width: JsNumber
    // If true, the container height swells and contracts to fit contents
    var autoSize: js.UndefOr[Boolean]
    // # of cols.
    var cols: js.UndefOr[Int]
    // A selector that will not be draggable.
    var draggableCancel: js.UndefOr[String]
    // A selector for the draggable handler
    var draggableHandle: js.UndefOr[String]
    // Choose vertical or hotizontal compaction
    var compactType: js.UndefOr[String]
    // layout is an array of object with the format:
    // {x: Number, y: Number, w: Number, h: Number, i: String}
    var layout: js.UndefOr[raw.Layout]
    // Margin between items [x, y] in px
    var margin: js.UndefOr[Margin]
    // Padding inside the container [x, y] in px
    var containerPadding: js.UndefOr[ContainerPadding]
    // Rows have a static height, but you can change this based on breakpoints if you like
    var rowHeight: js.UndefOr[Int]
    var maxRows: js.UndefOr[Int]
    var isDraggable: js.UndefOr[Boolean]
    var isResizable: js.UndefOr[Boolean]
    // If true, grid items won't change position when being dragged over.
    var preventCollision: js.UndefOr[Boolean]
    // Use CSS transforms instead of top/left
    var useCSSTransforms: js.UndefOr[Boolean]
    // Callback so you can save the layout. Calls after each drag & resize stops.
    var onLayoutChange: raw.RawLayoutChange

    // Calls when drag starts. Callback is of the signature (layout, oldItem, newItem, placeholder, e, ?node).
    // All callbacks below have the same signature. 'start' and 'stop' callbacks omit the 'placeholder'.
    var onDragStart: raw.RawItemCallback
    // Calls on each drag movement.
    var onDrag: raw.RawItemCallback
    // Calls when drag is complete.
    var onDragStop: raw.RawItemCallback
    //Calls when resize starts.
    var onResizeStart: raw.RawItemCallback
    // Calls when resize movement happens.
    var onResize: raw.RawItemCallback
    // Calls when resize is complete.
    var onResizeStop: raw.RawItemCallback
  }

  def props(
    width:            JsNumber,
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
    val p = (new js.Object).asInstanceOf[Props]
    p.className        = className
    p.width            = width
    p.style            = style.map(Style.toJsObject)
    p.autoSize         = autoSize
    p.cols             = cols
    p.draggableCancel  = draggableCancel
    p.draggableHandle  = draggableHandle
    p.compactType      = compactType.map(CompactType.toRaw)
    p.layout           = if (layout.l.nonEmpty) layout.toRaw else js.undefined
    p.margin           = margin
    p.containerPadding = containerPadding
    p.rowHeight        = rowHeight
    p.maxRows          = maxRows
    p.onLayoutChange   = (x: raw.Layout) => onLayoutChange(Layout.fromRaw(x)).runNow()
    p.onDragStart = (layout: raw.Layout,
                     oldItem:     raw.LayoutItem,
                     newItem:     raw.LayoutItem,
                     placeholder: raw.LayoutItem,
                     e:           MouseEvent,
                     element:     HTMLElement) =>
      onDragStart(Layout.fromRaw(layout),
                  LayoutItem.fromRaw(oldItem),
                  LayoutItem.fromRaw(newItem),
                  LayoutItem.fromRawO(placeholder),
                  e,
                  element).runNow()
    p.onDrag = (layout: raw.Layout,
                oldItem:     raw.LayoutItem,
                newItem:     raw.LayoutItem,
                placeholder: raw.LayoutItem,
                e:           MouseEvent,
                element:     HTMLElement) =>
      onDrag(Layout.fromRaw(layout),
             LayoutItem.fromRaw(oldItem),
             LayoutItem.fromRaw(newItem),
             LayoutItem.fromRawO(placeholder),
             e,
             element).runNow()
    p.onDragStop = (layout: raw.Layout,
                    oldItem:     raw.LayoutItem,
                    newItem:     raw.LayoutItem,
                    placeholder: raw.LayoutItem,
                    e:           MouseEvent,
                    element:     HTMLElement) =>
      onDragStop(Layout.fromRaw(layout),
                 LayoutItem.fromRaw(oldItem),
                 LayoutItem.fromRaw(newItem),
                 LayoutItem.fromRawO(placeholder),
                 e,
                 element).runNow()
    p.onResizeStart = (layout: raw.Layout,
                       oldItem:     raw.LayoutItem,
                       newItem:     raw.LayoutItem,
                       placeholder: raw.LayoutItem,
                       e:           MouseEvent,
                       element:     HTMLElement) =>
      onResizeStart(Layout.fromRaw(layout),
                    LayoutItem.fromRaw(oldItem),
                    LayoutItem.fromRaw(newItem),
                    LayoutItem.fromRawO(placeholder),
                    e,
                    element).runNow()
    p.onResize = (layout: raw.Layout,
                  oldItem:     raw.LayoutItem,
                  newItem:     raw.LayoutItem,
                  placeholder: raw.LayoutItem,
                  e:           MouseEvent,
                  element:     HTMLElement) =>
      onResize(Layout.fromRaw(layout),
               LayoutItem.fromRaw(oldItem),
               LayoutItem.fromRaw(newItem),
               LayoutItem.fromRawO(placeholder),
               e,
               element).runNow()
    p.onResizeStop = (layout: raw.Layout,
                      oldItem:     raw.LayoutItem,
                      newItem:     raw.LayoutItem,
                      placeholder: raw.LayoutItem,
                      e:           MouseEvent,
                      element:     HTMLElement) =>
      onResizeStop(Layout.fromRaw(layout),
                   LayoutItem.fromRaw(oldItem),
                   LayoutItem.fromRaw(newItem),
                   LayoutItem.fromRawO(placeholder),
                   e,
                   element).runNow()
    p
  }

  val component = JsComponent[Props, Children.Varargs, Null](RawComponent)

  def apply(
    p:        Props,
    children: VdomNode*): UnmountedMapped[Id, Props, Null, RawMounted[Props, Null], Props, Null] =
    component.apply(p)(children: _*)
}
