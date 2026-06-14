// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react

package gridlayout

import japgolly.scalajs.react.Callback
import lucuma.react.common.*
import org.scalajs.dom.Event
import org.scalajs.dom.html.Element as HTMLElement

import scala.scalajs.js

// Props shared by react-grid-layout v2 components GridLayout and ResponsiveGridLayout
@js.native
trait BaseProps extends js.Object:
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
  // Pluggable compaction strategy
  var compactor: js.UndefOr[raw.Compactor]
  // CSS positioning strategy
  var positionStrategy: js.UndefOr[raw.PositionStrategy]

  // Calls when drag starts. Callback is of the signature (layout, oldItem, newItem, placeholder, e, ?node).
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

object BaseProps {

  // Strip undefined keys so only the values we actually set survive merges with defaults.
  // Makes js operations like { ...defaultGridConfig, ...gridConfigProp } work properly on scala.js
  private[gridlayout] def pruneUndef[A <: js.Object](o: A): A = {
    val dict = o.asInstanceOf[js.Dictionary[js.Any]]
    js.Object.keys(o).foreach(k => if (js.isUndefined(dict(k))) js.special.delete(o, k))
    o
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
        LayoutItem.fromRawO(oldItem),
        LayoutItem.fromRawO(newItem),
        LayoutItem.fromRawO(placeholder),
        e,
        element.toOption
      ).runNow()

  def props(
    width:            Double,
    className:        js.UndefOr[String] = js.undefined,
    style:            js.UndefOr[Style] = js.undefined,
    autoSize:         js.UndefOr[Boolean] = js.undefined,
    dragConfig:       js.UndefOr[DragConfig] = js.undefined,
    resizeConfig:     js.UndefOr[ResizeConfig] = js.undefined,
    dropConfig:       js.UndefOr[DropConfig] = js.undefined,
    compactor:        js.UndefOr[Compactor] = js.undefined,
    positionStrategy: js.UndefOr[PositionStrategy] = js.undefined,
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
    p.dragConfig = dragConfig.map(_.toRaw)
    p.resizeConfig = resizeConfig.map(_.toRaw)
    p.dropConfig = dropConfig.map(_.toRaw)
    p.compactor = compactor
    p.positionStrategy = positionStrategy
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
    ) => onDrop(Layout.fromRaw(layout), item.toOption.map(LayoutItem.fromRaw), event).runNow()
    p
  }
}
