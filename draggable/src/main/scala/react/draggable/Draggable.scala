// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react

package draggable

import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.Children
import japgolly.scalajs.react.JsComponent
import japgolly.scalajs.react.vdom.VdomNode
import org.scalajs.dom.MouseEvent
import org.scalajs.dom.html.{Element => HTMLElement}
import react.common._

import scala.scalajs.js

import js.annotation.JSImport

trait Draggable extends js.Object

object Draggable {
  @js.native
  @JSImport("react-draggable", JSImport.Default)
  object RawComponent extends js.Object

  type DraggableEventHandler = (MouseEvent, DraggableData) => Callback
  type OnMouseDown           = MouseEvent => Callback

  @js.native
  trait Props extends js.Object {
    var allowAnyClick: js.UndefOr[Boolean]
    var axis: js.UndefOr[String]
    var bounds: js.UndefOr[DraggableBounds | String | Boolean]
    var cancel: js.UndefOr[String]
    var children: js.Array[Children]
    var defaultClassName: js.UndefOr[String]
    var defaultClassNameDragging: js.UndefOr[String]
    var defaultClassNameDragged: js.UndefOr[String]
    var defaultPosition: js.UndefOr[ControlPosition]
    var disabled: js.UndefOr[Boolean]
    // var enableUserSelectHack: js.UndefOr[Boolean]
    var grid: js.UndefOr[js.Array[Double]]
    var handle: js.UndefOr[String]
    var offsetParent: js.UndefOr[HTMLElement]
    var onMouseDown: js.UndefOr[raw.RawOnMouseDown]
    var onStart: js.UndefOr[raw.RawDraggableEventHandler]
    var onDrag: js.UndefOr[raw.RawDraggableEventHandler]
    var onStop: js.UndefOr[raw.RawDraggableEventHandler]
    var positionOffset: js.UndefOr[PositionOffsetControlPosition]
    var position: js.UndefOr[ControlPosition]
  }

  def props(
    allowAnyClick:            js.UndefOr[Boolean] = js.undefined,
    axis:                     js.UndefOr[Axis] = js.undefined,
    bounds:                   js.UndefOr[Bounds] = js.undefined,
    cancel:                   js.UndefOr[String] = js.undefined,
    defaultClassName:         js.UndefOr[String] = js.undefined,
    defaultClassNameDragging: js.UndefOr[String] = js.undefined,
    defaultClassNameDragged:  js.UndefOr[String] = js.undefined,
    defaultPosition:          js.UndefOr[ControlPosition] = js.undefined,
    disabled:                 js.UndefOr[Boolean] = js.undefined,
    grid:                     js.UndefOr[Grid] = js.undefined,
    handle:                   js.UndefOr[String] = js.undefined,
    onMouseDown:              js.UndefOr[OnMouseDown] = js.undefined,
    onStart:                  js.UndefOr[DraggableEventHandler] = js.undefined,
    onDrag:                   js.UndefOr[DraggableEventHandler] = js.undefined,
    onStop:                   js.UndefOr[DraggableEventHandler] = js.undefined,
    position:                 js.UndefOr[ControlPosition] = js.undefined,
    positionOffset:           js.UndefOr[PositionOffsetControlPosition] = js.undefined
  ): Props = {
    val p = (new js.Object).asInstanceOf[Props]
    p.allowAnyClick = allowAnyClick
    p.axis = axis.toJs
    p.bounds = bounds.map { x =>
      (x: Any) match {
        case FalseBounds => false
        case s: String   => s
        case b           => b.asInstanceOf[DraggableBounds]
      }
    }
    p.cancel = cancel
    p.defaultClassName = defaultClassName
    p.defaultClassNameDragging = defaultClassNameDragging
    p.defaultClassNameDragged = defaultClassNameDragged
    p.defaultPosition = defaultPosition
    p.disabled = disabled
    p.grid = grid.map(_.value)
    p.handle = handle
    p.onMouseDown = onMouseDown.map(cb => (m: MouseEvent) => cb(m).runNow())
    p.onStart = onStart.map(cb => (m: MouseEvent, d: DraggableData) => cb(m, d).runNow())
    p.onDrag = onDrag.map(cb => (m: MouseEvent, d: DraggableData) => cb(m, d).runNow())
    p.onStop = onStop.map(cb => (m: MouseEvent, d: DraggableData) => cb(m, d).runNow())
    p.position = position
    p.positionOffset = positionOffset
    p
  }

  val component = JsComponent.force[Props, Children.Varargs, Null](RawComponent)

  def apply(
    p:        Props,
    children: VdomNode
  ) =
    component.apply(p)(children)
}
