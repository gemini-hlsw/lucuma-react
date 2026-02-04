// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.pragmaticdnd.facade

import japgolly.scalajs.react.callback.*
import org.scalajs.dom.HTMLElement

import scalajs.js

@js.native
trait DraggableArgs[S, T] extends CommonEvents[BaseEventPayload[S, T]] {

  /**
   * The `HTMLElement` that you want to attach draggable behaviour to. `element` is our unique _key_
   * for a draggable. `element` is a `HTMLElement` as only a `HTMLElement` can have a "draggable"
   * attribute
   */
  var element: HTMLElement

  /**
   * The part of a draggable `element` that you want to use to control the dragging of the whole
   * `element`
   */
  var dragHandle: js.UndefOr[HTMLElement]

  /**
   * Conditionally allow a drag to occur
   */
  var canDrag: js.UndefOr[js.Function1[DraggableGetFeedbackArgs, Boolean]]

  /**
   * Used to attach data to a drag operation. Called once just before the drag starts
   */
  var getInitialData: js.UndefOr[js.Function1[DraggableGetFeedbackArgs, S]]
}

object DraggableArgs {
  def apply[S, T](
    element:               HTMLElement,
    dragHandle:            js.UndefOr[HTMLElement] = js.undefined,
    canDrag:               js.UndefOr[DraggableGetFeedbackArgs => CallbackTo[Boolean]] = js.undefined,
    getInitialData:        js.UndefOr[DraggableGetFeedbackArgs => CallbackTo[S]] = js.undefined,
    onGenerateDragPreview: js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
    onDragStart:           js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
    onDrag:                js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
    onDropTargetChange:    js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
    onDrop:                js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined
  ): DraggableArgs[S, T] = {
    val p = (new js.Object).asInstanceOf[DraggableArgs[S, T]]
    p.element = element
    p.applyOrNot(dragHandle, _.dragHandle = _)
      .applyOrNot(canDrag, (p, f) => p.canDrag = args => f(args).runNow())
      .applyOrNot(getInitialData, (p, f) => p.getInitialData = args => f(args).runNow())
      .applyOrNot(
        onGenerateDragPreview,
        (p, f) => p.onGenerateDragPreview = args => f(args).runNow()
      )
      .applyOrNot(onDragStart, (p, f) => p.onDragStart = args => f(args).runNow())
      .applyOrNot(onDrag, (p, f) => p.onDrag = args => f(args).runNow())
      .applyOrNot(onDropTargetChange, (p, f) => p.onDropTargetChange = args => f(args).runNow())
      .applyOrNot(onDrop, (p, f) => p.onDrop = args => f(args).runNow())
  }
}
