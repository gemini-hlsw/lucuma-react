// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.pragmaticdnd

import cats.syntax.all.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.hooks.HookResult
import lucuma.react.pragmaticdnd.facade.*
import org.scalajs.dom.HTMLElement

import scalajs.js

def useDraggableWithHandleRefs[S, T](
  canDrag:               js.UndefOr[DraggableGetFeedbackArgs => CallbackTo[Boolean]] = js.undefined,
  getInitialData:        js.UndefOr[DraggableGetFeedbackArgs => CallbackTo[S]] = js.undefined,
  onGenerateDragPreview: js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDragStart:           js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDrag:                js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDropTargetChange:    js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDrop:                js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined
): HookResult[(Ref.ToVdom[HTMLElement], Ref.ToVdom[HTMLElement])] =
  for
    ref       <- useRefToVdom[HTMLElement]
    handleRef <- useRefToVdom[HTMLElement]
    _         <- useEffectOnMount:
                   (ref.get, handleRef.get).tupled.flatMap: (refValue, handleRefValue) =>
                     (refValue, handleRefValue).tupled.foldMap: (elem, handleElem) =>
                       ElementAdapter.draggable(
                         elem,
                         handleElem,
                         canDrag,
                         getInitialData,
                         onGenerateDragPreview,
                         onDragStart,
                         onDrag,
                         onDropTargetChange,
                         onDrop
                       )
  yield (ref, handleRef)

def useDraggableRef[S, T](
  canDrag:               js.UndefOr[DraggableGetFeedbackArgs => CallbackTo[Boolean]] = js.undefined,
  getInitialData:        js.UndefOr[DraggableGetFeedbackArgs => CallbackTo[S]] = js.undefined,
  onGenerateDragPreview: js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDragStart:           js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDrag:                js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDropTargetChange:    js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDrop:                js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined
): HookResult[Ref.ToVdom[HTMLElement]] =
  for
    ref <- useRefToVdom[HTMLElement]
    _   <- useEffectOnMount:
             ref.get.flatMap: refValue =>
               refValue.foldMap: elem =>
                 ElementAdapter.draggable(
                   elem,
                   js.undefined,
                   canDrag,
                   getInitialData,
                   onGenerateDragPreview,
                   onDragStart,
                   onDrag,
                   onDropTargetChange,
                   onDrop
                 )
  yield ref

def useDropTargetRef[S, T](
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
): HookResult[Ref.ToVdom[HTMLElement]] =
  for
    ref <- useRefToVdom[HTMLElement]
    _   <- useEffectOnMount:
             ref.get.flatMap: refValue =>
               refValue.foldMap: elem =>
                 ElementAdapter.dropTarget(
                   elem,
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
  yield ref

def useDragAndDropMonitor[S, T](
  canMonitor:            js.UndefOr[MonitorGetFeedbackArgs[S, T] => CallbackTo[Boolean]] = js.undefined,
  onGenerateDragPreview: js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDragStart:           js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDrag:                js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDropTargetChange:    js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDrop:                js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined
): HookResult[Unit] =
  useEffectOnMount:
    ElementAdapter.monitor(
      canMonitor,
      onGenerateDragPreview,
      onDragStart,
      onDrag,
      onDropTargetChange,
      onDrop
    )
