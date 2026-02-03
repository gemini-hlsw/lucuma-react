// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.pragmaticdnd

import japgolly.scalajs.react.callback.*
import lucuma.react.pragmaticdnd.facade.*
import org.scalajs.dom.HTMLElement

import scalajs.js

object ElementAdapter:
  def draggable[S, T](
    element:               HTMLElement,
    dragHandle:            js.UndefOr[HTMLElement] = js.undefined,
    canDrag:               js.UndefOr[DraggableGetFeedbackArgs => CallbackTo[Boolean]] = js.undefined,
    getInitialData:        js.UndefOr[DraggableGetFeedbackArgs => CallbackTo[S]] = js.undefined,
    onGenerateDragPreview: js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
    onDragStart:           js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
    onDrag:                js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
    onDropTargetChange:    js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
    onDrop:                js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined
  ): CallbackTo[Callback] = // Returns cleanup callback
    CallbackTo:
      Callback.fromJsFn:
        ElementAdapterRaw.draggable(
          DraggableArgs[S, T](
            element,
            dragHandle,
            canDrag,
            getInitialData,
            onGenerateDragPreview,
            onDragStart,
            onDrag,
            onDropTargetChange,
            onDrop
          )
        )

  def dropTarget[S, T](
    element:               HTMLElement,
    getData:               js.UndefOr[DropTargetGetFeedbackArgs[S] => CallbackTo[T]] = js.undefined,
    canDrop:               js.UndefOr[DropTargetGetFeedbackArgs[S] => CallbackTo[Boolean]] = js.undefined,
    getIsSticky:           js.UndefOr[DropTargetGetFeedbackArgs[S] => CallbackTo[Boolean]] = js.undefined,
    onGenerateDragPreview: js.UndefOr[DropTargetEventPayload[S, T] => Callback] = js.undefined,
    onDragStart:           js.UndefOr[DropTargetEventPayload[S, T] => Callback] = js.undefined,
    onDrag:                js.UndefOr[DropTargetEventPayload[S, T] => Callback] = js.undefined,
    onDropTargetChange:    js.UndefOr[DropTargetEventPayload[S, T] => Callback] = js.undefined,
    onDrop:                js.UndefOr[DropTargetEventPayload[S, T] => Callback] = js.undefined,
    onDragEnter:           js.UndefOr[DropTargetEventPayload[S, T] => Callback] = js.undefined,
    onDragLeave:           js.UndefOr[DropTargetEventPayload[S, T] => Callback] = js.undefined
  ): CallbackTo[Callback] = // Returns cleanup callback
    CallbackTo:
      Callback.fromJsFn:
        ElementAdapterRaw.dropTargetForElements:
          DropTargetArgs[S, T](
            element,
            getData,
            canDrop,
            getIsSticky,
            onGenerateDragPreview,
            onDragStart,
            onDrag,
            onDropTargetChange,
            onDrop,
            onDragEnter,
            onDragLeave
          )

  def monitor[S, T](
    canMonitor:            js.UndefOr[MonitorGetFeedbackArgs[S, T] => CallbackTo[Boolean]] = js.undefined,
    onGenerateDragPreview: js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
    onDragStart:           js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
    onDrag:                js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
    onDropTargetChange:    js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
    onDrop:                js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined
  ): CallbackTo[Callback] = // Returns cleanup callback
    CallbackTo:
      Callback.fromJsFn:
        ElementAdapterRaw.monitorForElements:
          MonitorArgs[S, T](
            canMonitor,
            onGenerateDragPreview,
            onDragStart,
            onDrag,
            onDropTargetChange,
            onDrop
          )
