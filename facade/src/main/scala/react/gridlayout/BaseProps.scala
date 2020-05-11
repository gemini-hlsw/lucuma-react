package react

package gridlayout

import scala.scalajs.js
import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.raw.JsNumber
import org.scalajs.dom.html.{ Element => HTMLElement }
import org.scalajs.dom.MouseEvent
import react.common._

@js.native
trait BaseProps extends js.Object {
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
  var verticalCompact: js.UndefOr[Boolean]
  // Choose vertical or hotizontal compaction
  var compactType: js.UndefOr[String]
  // Margin between items [x, y] in px
  var margin: js.UndefOr[js.Array[JsNumber]]
  // Padding inside the container [x, y] in px
  var containerPadding: js.UndefOr[js.Array[JsNumber]]
  // Rows have a static height, but you can change this based on breakpoints if you like
  var rowHeight: js.UndefOr[Int]
  var maxRows: js.UndefOr[Int]
  var isDraggable: js.UndefOr[Boolean]
  var isResizable: js.UndefOr[Boolean]
  // If true, droppable elements (with `draggable={true}` attribute)
  // can be dropped on the grid. It triggers "onDrop" callback
  // with position and event object as parameters.
  // It can be useful for dropping an element in a specific position
  //
  // NOTE: In case of using Firefox you should add
  // `onDragStart={e => e.dataTransfer.setData('text/plain', '')}` attribute
  // along with `draggable={true}` otherwise this feature will work incorrect.
  // onDragStart attribute is required for Firefox for a dragging initialization
  // @see https://bugzilla.mozilla.org/show_bug.cgi?id=568313
  var isDroppable: js.UndefOr[Boolean]
  // If true, grid items won't change position when being dragged over.
  var preventCollision: js.UndefOr[Boolean]
  // Use CSS transforms instead of top/left
  var useCSSTransforms: js.UndefOr[Boolean]
// If parent DOM node of ResponsiveReactGridLayout or ReactGridLayout has "transform: scale(n)" css property,
// we should set scale coefficient to avoid render artefacts while dragging.
  var transformScale: js.UndefOr[JsNumber]
  var droppingItem: js.UndefOr[raw.DroppingItem]

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
  var onDrop: raw.DropCallback
}

object BaseProps {
  def props(
    width:            JsNumber,
    className:        js.UndefOr[String] = js.undefined,
    style:            js.UndefOr[Style] = js.undefined,
    autoSize:         js.UndefOr[Boolean] = js.undefined,
    cols:             js.UndefOr[Int] = js.undefined,
    draggableCancel:  js.UndefOr[String] = js.undefined,
    draggableHandle:  js.UndefOr[String] = js.undefined,
    verticalCompact:  js.UndefOr[Boolean] = js.undefined,
    compactType:      js.UndefOr[CompactType] = js.undefined,
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
    onDragStart:      ItemCallback = (_, _, _, _, _, _) => Callback.empty,
    onDrag:           ItemCallback = (_, _, _, _, _, _) => Callback.empty,
    onDragStop:       ItemCallback = (_, _, _, _, _, _) => Callback.empty,
    onResizeStart:    ItemCallback = (_, _, _, _, _, _) => Callback.empty,
    onResize:         ItemCallback = (_, _, _, _, _, _) => Callback.empty,
    onResizeStop:     ItemCallback = (_, _, _, _, _, _) => Callback.empty,
    onDrop:           DropCallback = (_, _, _, _) => Callback.empty
  ): BaseProps = {
    val p = (new js.Object).asInstanceOf[BaseProps]
    p.className = className
    p.width = width
    p.style = style.map(Style.toJsObject)
    p.autoSize = autoSize
    p.cols = cols
    p.draggableCancel = draggableCancel
    p.draggableHandle = draggableHandle
    p.verticalCompact = verticalCompact
    p.compactType = compactType.toJs
    p.margin = margin.map(x => js.Array(x._1, x._2))
    p.containerPadding = containerPadding.map(x => js.Array(x._1, x._2))
    p.rowHeight = rowHeight
    p.maxRows = maxRows
    p.isDraggable = isDraggable
    p.isResizable = isResizable
    p.isDroppable = isDroppable
    p.preventCollision = preventCollision
    p.useCSSTransforms = useCSSTransforms
    p.transformScale = transformScale
    p.droppingItem = droppingItem.map(_.toRaw)
    p.onDragStart = (
      layout:      raw.Layout,
      oldItem:     raw.LayoutItem,
      newItem:     raw.LayoutItem,
      placeholder: raw.LayoutItem,
      e:           MouseEvent,
      element:     HTMLElement
    ) =>
      onDragStart(Layout.fromRaw(layout),
                  LayoutItem.fromRaw(oldItem),
                  LayoutItem.fromRaw(newItem),
                  LayoutItem.fromRawO(placeholder),
                  e,
                  element
      ).runNow()
    p.onDrag = (
      layout:      raw.Layout,
      oldItem:     raw.LayoutItem,
      newItem:     raw.LayoutItem,
      placeholder: raw.LayoutItem,
      e:           MouseEvent,
      element:     HTMLElement
    ) =>
      onDrag(Layout.fromRaw(layout),
             LayoutItem.fromRaw(oldItem),
             LayoutItem.fromRaw(newItem),
             LayoutItem.fromRawO(placeholder),
             e,
             element
      ).runNow()
    p.onDragStop = (
      layout:      raw.Layout,
      oldItem:     raw.LayoutItem,
      newItem:     raw.LayoutItem,
      placeholder: raw.LayoutItem,
      e:           MouseEvent,
      element:     HTMLElement
    ) =>
      onDragStop(Layout.fromRaw(layout),
                 LayoutItem.fromRaw(oldItem),
                 LayoutItem.fromRaw(newItem),
                 LayoutItem.fromRawO(placeholder),
                 e,
                 element
      ).runNow()
    p.onResizeStart = (
      layout:      raw.Layout,
      oldItem:     raw.LayoutItem,
      newItem:     raw.LayoutItem,
      placeholder: raw.LayoutItem,
      e:           MouseEvent,
      element:     HTMLElement
    ) =>
      onResizeStart(Layout.fromRaw(layout),
                    LayoutItem.fromRaw(oldItem),
                    LayoutItem.fromRaw(newItem),
                    LayoutItem.fromRawO(placeholder),
                    e,
                    element
      ).runNow()
    p.onResize = (
      layout:      raw.Layout,
      oldItem:     raw.LayoutItem,
      newItem:     raw.LayoutItem,
      placeholder: raw.LayoutItem,
      e:           MouseEvent,
      element:     HTMLElement
    ) =>
      onResize(Layout.fromRaw(layout),
               LayoutItem.fromRaw(oldItem),
               LayoutItem.fromRaw(newItem),
               LayoutItem.fromRawO(placeholder),
               e,
               element
      ).runNow()
    p.onResizeStop = (
      layout:      raw.Layout,
      oldItem:     raw.LayoutItem,
      newItem:     raw.LayoutItem,
      placeholder: raw.LayoutItem,
      e:           MouseEvent,
      element:     HTMLElement
    ) =>
      onResizeStop(Layout.fromRaw(layout),
                   LayoutItem.fromRaw(oldItem),
                   LayoutItem.fromRaw(newItem),
                   LayoutItem.fromRawO(placeholder),
                   e,
                   element
      ).runNow()
    p.onDrop = (
      x: JsNumber,
      y: JsNumber,
      w: JsNumber,
      h: JsNumber
    ) => onDrop(x, y, w, h).runNow()
    p
  }
}
