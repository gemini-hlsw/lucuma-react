// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react

package gridlayout

import japgolly.scalajs.react.Callback
import lucuma.react.common.{*, given}
import org.scalajs.dom.Event
import org.scalajs.dom.MouseEvent
import org.scalajs.dom.html.Element as HTMLElement

import scala.language.implicitConversions
import scala.scalajs.js
import scala.scalajs.js.JSConverters.*

// Props shared by both react-grid-layout v2 components (GridLayout and ResponsiveGridLayout).
// The historical "flat" v1 props are still exposed by the Scala API; they are translated here
// into v2's composable configuration objects (dragConfig / resizeConfig / dropConfig), the
// compaction strategy (compactor) and the CSS positioning strategy (positionStrategy).
@js.native
trait BaseProps extends js.Object {
  var className: js.UndefOr[String]
  var style: js.UndefOr[js.Object]
  var width: Double
  // If true, the container height swells and contracts to fit contents
  var autoSize: js.UndefOr[Boolean]
  // { enabled, bounded, handle, cancel, threshold }
  var dragConfig: js.UndefOr[raw.DragConfig]
  // { enabled, handles }
  var resizeConfig: js.UndefOr[raw.ResizeConfig]
  // { enabled, defaultItem }
  var dropConfig: js.UndefOr[raw.DropConfig]
  // Pluggable compaction strategy (replaces compactType / verticalCompact / preventCollision)
  var compactor: js.UndefOr[raw.Compactor]
  // CSS positioning strategy (replaces useCSSTransforms / transformScale)
  var positionStrategy: js.UndefOr[raw.PositionStrategy]

  // Calls when drag starts. Callback is of the signature (layout, oldItem, newItem, placeholder, e, ?node).
  // All callbacks below have the same signature. 'start' and 'stop' callbacks omit the 'placeholder'.
  var onDragStart: raw.RawItemCallback
  // Calls on each drag movement.
  var onDrag: raw.RawItemCallback
  // Calls when drag is complete.
  var onDragStop: raw.RawItemCallback
  // Calls when resize starts.
  var onResizeStart: raw.RawItemCallback
  // Calls when resize movement happens.
  var onResize: raw.RawItemCallback
  // Calls when resize is complete.
  var onResizeStop: raw.RawItemCallback
  var onDrop: raw.DropCallback
}

object BaseProps {

  private def dragConfig(
    isDraggable:     js.UndefOr[Boolean],
    isBounded:       js.UndefOr[Boolean],
    draggableHandle: js.UndefOr[String],
    draggableCancel: js.UndefOr[String],
    dragThreshold:   js.UndefOr[Int]
  ): js.UndefOr[raw.DragConfig] =
    if (
      isDraggable.isDefined || isBounded.isDefined || draggableHandle.isDefined ||
      draggableCancel.isDefined || dragThreshold.isDefined
    )
      new raw.DragConfig(isDraggable, isBounded, draggableHandle, draggableCancel, dragThreshold)
    else js.undefined

  private def resizeConfig(
    isResizable:   js.UndefOr[Boolean],
    resizeHandles: js.UndefOr[List[ResizeHandle]]
  ): js.UndefOr[raw.ResizeConfig] =
    if (isResizable.isDefined || resizeHandles.isDefined)
      new raw.ResizeConfig(isResizable, resizeHandles.map(_.toJSArray.map(_.toJs)))
    else js.undefined

  private def dropConfig(
    isDroppable:  js.UndefOr[Boolean],
    droppingItem: js.UndefOr[DroppingItem]
  ): js.UndefOr[raw.DropConfig] =
    if (isDroppable.isDefined || droppingItem.isDefined)
      new raw.DropConfig(isDroppable, droppingItem.map(_.toRaw))
    else js.undefined

  // Translate the v1-style compactType / verticalCompact / preventCollision into a v2 Compactor.
  // Leaving it undefined preserves v2's default (vertical compaction).
  private def compactor(
    compactType:      js.UndefOr[CompactType],
    verticalCompact:  js.UndefOr[Boolean],
    preventCollision: js.UndefOr[Boolean]
  ): js.UndefOr[raw.Compactor] =
    if (compactType.isDefined)
      raw.Core.getCompactor(compactType.toJs, js.undefined, preventCollision)
    else
      verticalCompact.toOption match {
        // verticalCompact = false means "no compaction" -> null compactType
        case Some(false) => raw.Core.getCompactor(null, js.undefined, preventCollision)
        case Some(true)  => raw.Core.getCompactor("vertical", js.undefined, preventCollision)
        case None        =>
          // Only preventCollision was supplied: keep the default (vertical) compaction.
          if (preventCollision.isDefined)
            raw.Core.getCompactor("vertical", js.undefined, preventCollision)
          else js.undefined
      }

  private def positionStrategy(
    useCSSTransforms: js.UndefOr[Boolean],
    transformScale:   js.UndefOr[Double]
  ): js.UndefOr[raw.PositionStrategy] =
    transformScale.toOption match {
      case Some(scale) => raw.Core.createScaledStrategy(scale)
      case None        =>
        useCSSTransforms.toOption match {
          case Some(false) => raw.Core.absoluteStrategy
          case _           => js.undefined
        }
    }

  private def itemCallback(cb: ItemCallback): raw.RawItemCallback =
    (
      layout:      raw.Layout,
      oldItem:     raw.LayoutItem,
      newItem:     raw.LayoutItem,
      placeholder: raw.LayoutItem,
      e:           Event,
      element:     js.UndefOr[HTMLElement]
    ) =>
      cb(
        Layout.fromRaw(layout),
        LayoutItem.fromRaw(oldItem),
        LayoutItem.fromRaw(newItem),
        LayoutItem.fromRawO(placeholder),
        e.asInstanceOf[MouseEvent],
        element.getOrElse(null.asInstanceOf[HTMLElement])
      ).runNow()

  def props(
    width:            Double,
    className:        js.UndefOr[String] = js.undefined,
    style:            js.UndefOr[Style] = js.undefined,
    autoSize:         js.UndefOr[Boolean] = js.undefined,
    draggableCancel:  js.UndefOr[String] = js.undefined,
    draggableHandle:  js.UndefOr[String] = js.undefined,
    verticalCompact:  js.UndefOr[Boolean] = js.undefined,
    compactType:      js.UndefOr[CompactType] = js.undefined,
    isDraggable:      js.UndefOr[Boolean] = js.undefined,
    isResizable:      js.UndefOr[Boolean] = js.undefined,
    isBounded:        js.UndefOr[Boolean] = js.undefined,
    isDroppable:      js.UndefOr[Boolean] = js.undefined,
    preventCollision: js.UndefOr[Boolean] = js.undefined,
    useCSSTransforms: js.UndefOr[Boolean] = js.undefined,
    transformScale:   js.UndefOr[Double] = js.undefined,
    droppingItem:     js.UndefOr[DroppingItem] = js.undefined,
    resizeHandles:    js.UndefOr[List[ResizeHandle]] = js.undefined,
    dragThreshold:    js.UndefOr[Int] = js.undefined,
    onDragStart:      ItemCallback = (_, _, _, _, _, _) => Callback.empty,
    onDrag:           ItemCallback = (_, _, _, _, _, _) => Callback.empty,
    onDragStop:       ItemCallback = (_, _, _, _, _, _) => Callback.empty,
    onResizeStart:    ItemCallback = (_, _, _, _, _, _) => Callback.empty,
    onResize:         ItemCallback = (_, _, _, _, _, _) => Callback.empty,
    onResizeStop:     ItemCallback = (_, _, _, _, _, _) => Callback.empty,
    onDrop:           DropCallback = (_, _, _) => Callback.empty
  ): BaseProps = {
    val p = (new js.Object).asInstanceOf[BaseProps]
    p.className = className
    p.width = width
    p.style = style.map(_.toJsObject)
    p.autoSize = autoSize
    p.dragConfig =
      dragConfig(isDraggable, isBounded, draggableHandle, draggableCancel, dragThreshold)
    p.resizeConfig = resizeConfig(isResizable, resizeHandles)
    p.dropConfig = dropConfig(isDroppable, droppingItem)
    p.compactor = compactor(compactType, verticalCompact, preventCollision)
    p.positionStrategy = positionStrategy(useCSSTransforms, transformScale)
    p.onDragStart = itemCallback(onDragStart)
    p.onDrag = itemCallback(onDrag)
    p.onDragStop = itemCallback(onDragStop)
    p.onResizeStart = itemCallback(onResizeStart)
    p.onResize = itemCallback(onResize)
    p.onResizeStop = itemCallback(onResizeStop)
    p.onDrop = (
      layout: raw.Layout,
      item:   js.UndefOr[raw.LayoutItem],
      event:  Event
    ) =>
      item.toOption.foreach(it =>
        onDrop(Layout.fromRaw(layout), LayoutItem.fromRaw(it), event).runNow()
      )
    p
  }
}
