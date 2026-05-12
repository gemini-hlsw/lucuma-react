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
    canDrag:               js.UndefOr[DraggableGetFeedbackArgs => OptionalCallbackTo[Boolean]] = js.undefined,
    getInitialData:        js.UndefOr[DraggableGetFeedbackArgs => OptionalCallbackTo[Data[S]]] =
      js.undefined,
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
            canDrag.resolve,
            getInitialData.resolve,
            onGenerateDragPreview,
            onDragStart,
            onDrag,
            onDropTargetChange,
            onDrop
          )
        )

  def dropTarget[S, T](
    element:               HTMLElement,
    getData:               js.UndefOr[DropTargetGetFeedbackArgs[S] => OptionalCallbackTo[Data[T]]] = js.undefined,
    canDrop:               js.UndefOr[DropTargetGetFeedbackArgs[S] => OptionalCallbackTo[Boolean]] = js.undefined,
    getIsSticky:           js.UndefOr[DropTargetGetFeedbackArgs[S] => OptionalCallbackTo[Boolean]] =
      js.undefined,
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
            getData.resolve,
            canDrop.resolve,
            getIsSticky.resolve,
            onGenerateDragPreview,
            onDragStart,
            onDrag,
            onDropTargetChange,
            onDrop,
            onDragEnter,
            onDragLeave
          )

  def monitor[S, T](
    canMonitor:            js.UndefOr[MonitorGetFeedbackArgs[S, T] => OptionalCallbackTo[Boolean]] =
      js.undefined,
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
            canMonitor.resolve,
            onGenerateDragPreview,
            onDragStart,
            onDrag,
            onDropTargetChange,
            onDrop
          )
