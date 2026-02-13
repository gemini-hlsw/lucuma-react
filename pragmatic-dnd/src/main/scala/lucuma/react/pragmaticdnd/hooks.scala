// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.pragmaticdnd

import cats.syntax.all.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.feature.Context
import japgolly.scalajs.react.hooks.HookResult
import lucuma.react.pragmaticdnd.facade.*
import org.scalajs.dom.HTMLElement

import scalajs.js

def useDraggableWithHandleRefs[S, T](
  canDrag:               js.UndefOr[DraggableGetFeedbackArgs => Boolean] = js.undefined,
  getInitialData:        js.UndefOr[DraggableGetFeedbackArgs => S] = js.undefined,
  onGenerateDragPreview: js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDragStart:           js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDrag:                js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDropTargetChange:    js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDrop:                js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined
): HookResult[(Ref.ToVdom[HTMLElement], Ref.ToVdom[HTMLElement])] =
  for
    context   <- useContext(DragAndDropContext.ctx)
    ref       <- useRefToVdom[HTMLElement]
    handleRef <- useRefToVdom[HTMLElement]
    _         <- useEffectOnMount:
                   (ref.get, handleRef.get).tupled.flatMap: (refValue, handleRefValue) =>
                     (refValue, handleRefValue).tupled.foldMap: (elem, handleElem) =>
                       ElementAdapter.draggable[ContextData[S], ContextData[T]](
                         elem,
                         handleElem,
                         canDrag,
                         contextualizeGetInitialData(context)(getInitialData),
                         decontextualizeBaseEventHandler(onGenerateDragPreview),
                         decontextualizeBaseEventHandler(onDragStart),
                         decontextualizeBaseEventHandler(onDrag),
                         decontextualizeBaseEventHandler(onDropTargetChange),
                         decontextualizeBaseEventHandler(onDrop)
                       )
  yield (ref, handleRef)

def useDraggableRef[S, T](
  canDrag:               js.UndefOr[DraggableGetFeedbackArgs => Boolean] = js.undefined,
  getInitialData:        js.UndefOr[DraggableGetFeedbackArgs => S] = js.undefined,
  onGenerateDragPreview: js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDragStart:           js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDrag:                js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDropTargetChange:    js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDrop:                js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined
): HookResult[Ref.ToVdom[HTMLElement]] =
  for
    context <- useContext(DragAndDropContext.ctx)
    ref     <- useRefToVdom[HTMLElement]
    _       <- useEffectOnMount:
                 ref.get.flatMap: refValue =>
                   refValue.foldMap: elem =>
                     ElementAdapter.draggable[ContextData[S], ContextData[T]](
                       elem,
                       js.undefined,
                       canDrag,
                       contextualizeGetInitialData(context)(getInitialData),
                       decontextualizeBaseEventHandler(onGenerateDragPreview),
                       decontextualizeBaseEventHandler(onDragStart),
                       decontextualizeBaseEventHandler(onDrag),
                       decontextualizeBaseEventHandler(onDropTargetChange),
                       decontextualizeBaseEventHandler(onDrop)
                     )
  yield ref

def useDropTargetRef[S, T](
  getData:               js.UndefOr[DropTargetGetFeedbackArgs[S] => T] = js.undefined,
  canDrop:               js.UndefOr[DropTargetGetFeedbackArgs[S] => Boolean] = js.undefined,
  getIsSticky:           js.UndefOr[DropTargetGetFeedbackArgs[S] => Boolean] = js.undefined,
  onGenerateDragPreview: js.UndefOr[DropTargetEventPayload[S, T] => Callback] = js.undefined,
  onDragStart:           js.UndefOr[DropTargetEventPayload[S, T] => Callback] = js.undefined,
  onDrag:                js.UndefOr[DropTargetEventPayload[S, T] => Callback] = js.undefined,
  onDropTargetChange:    js.UndefOr[DropTargetEventPayload[S, T] => Callback] = js.undefined,
  onDrop:                js.UndefOr[DropTargetEventPayload[S, T] => Callback] = js.undefined,
  onDragEnter:           js.UndefOr[DropTargetEventPayload[S, T] => Callback] = js.undefined,
  onDragLeave:           js.UndefOr[DropTargetEventPayload[S, T] => Callback] = js.undefined
): HookResult[Ref.ToVdom[HTMLElement]] =
  for
    context <- useContext(DragAndDropContext.ctx)
    ref     <- useRefToVdom[HTMLElement]
    _       <- useEffectOnMount:
                 ref.get.flatMap: refValue =>
                   refValue.foldMap: elem =>
                     ElementAdapter.dropTarget[ContextData[S], ContextData[T]](
                       elem,
                       contextualizeGetData(context)(getData),
                       decontextualizeCanDrop(context)(canDrop),
                       decontextualizeDropTargetBooleanFunction(getIsSticky),
                       decontextualizeDropTargetEventHandler(onGenerateDragPreview),
                       decontextualizeDropTargetEventHandler(onDragStart),
                       decontextualizeDropTargetEventHandler(onDrag),
                       decontextualizeDropTargetEventHandler(onDropTargetChange),
                       decontextualizeDropTargetEventHandler(onDrop),
                       decontextualizeDropTargetEventHandler(onDragEnter),
                       decontextualizeDropTargetEventHandler(onDragLeave)
                     )
  yield ref

// Combined Draggable + Drop Target
def useDraggableDropTargetWithHandleRefs[S, T](
  canDrag:                         js.UndefOr[DraggableGetFeedbackArgs => Boolean] = js.undefined,
  canDrop:                         js.UndefOr[DropTargetGetFeedbackArgs[S] => Boolean] = js.undefined,
  getInitialData:                  js.UndefOr[DraggableGetFeedbackArgs => S] = js.undefined,
  getData:                         js.UndefOr[DropTargetGetFeedbackArgs[S] => T] = js.undefined,
  getIsSticky:                     js.UndefOr[DropTargetGetFeedbackArgs[S] => Boolean] = js.undefined,
  onDraggableGenerateDragPreview:  js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDraggableDragStart:            js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDraggableDrag:                 js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDraggableDropTargetChange:     js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDraggableDrop:                 js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDropTargetGenerateDragPreview: js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDropTargetDragStart:           js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDropTargetDrag:                js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDropTargetDropTargetChange:    js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDropTargetDrop:                js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDropTargetDragEnter:           js.UndefOr[DropTargetEventPayload[S, T] => Callback] = js.undefined,
  onDropTargetDragLeave:           js.UndefOr[DropTargetEventPayload[S, T] => Callback] = js.undefined
): HookResult[(Ref.ToVdom[HTMLElement], Ref.ToVdom[HTMLElement])] =
  for
    context   <- useContext(DragAndDropContext.ctx)
    ref       <- useRefToVdom[HTMLElement]
    handleRef <- useRefToVdom[HTMLElement]
    _         <- useEffectOnMount:
                   (ref.get, handleRef.get).tupled.flatMap: (refValue, handleRefValue) =>
                     (refValue, handleRefValue).tupled.foldMap: (elem, handleElem) =>
                       for
                         draggableCleanup  <-
                           ElementAdapter.draggable[ContextData[S], ContextData[T]](
                             elem,
                             handleElem,
                             canDrag,
                             contextualizeGetInitialData(context)(getInitialData),
                             decontextualizeBaseEventHandler(onDraggableGenerateDragPreview),
                             decontextualizeBaseEventHandler(onDraggableDragStart),
                             decontextualizeBaseEventHandler(onDraggableDrag),
                             decontextualizeBaseEventHandler(onDraggableDropTargetChange),
                             decontextualizeBaseEventHandler(onDraggableDrop)
                           )
                         dropTargetCleanup <-
                           ElementAdapter.dropTarget[ContextData[S], ContextData[T]](
                             elem,
                             contextualizeGetData(context)(getData),
                             decontextualizeCanDrop(context)(canDrop),
                             decontextualizeDropTargetBooleanFunction(getIsSticky),
                             decontextualizeDropTargetEventHandler(onDropTargetGenerateDragPreview),
                             decontextualizeDropTargetEventHandler(onDropTargetDragStart),
                             decontextualizeDropTargetEventHandler(onDropTargetDrag),
                             decontextualizeDropTargetEventHandler(onDropTargetDropTargetChange),
                             decontextualizeDropTargetEventHandler(onDropTargetDrop),
                             decontextualizeDropTargetEventHandler(onDropTargetDragEnter),
                             decontextualizeDropTargetEventHandler(onDropTargetDragLeave)
                           )
                       yield draggableCleanup >> dropTargetCleanup
  yield (ref, handleRef)

def useDraggableDropTargetRef[S, T](
  canDrag:                         js.UndefOr[DraggableGetFeedbackArgs => Boolean] = js.undefined,
  canDrop:                         js.UndefOr[DropTargetGetFeedbackArgs[S] => Boolean] = js.undefined,
  getInitialData:                  js.UndefOr[DraggableGetFeedbackArgs => S] = js.undefined,
  getData:                         js.UndefOr[DropTargetGetFeedbackArgs[S] => T] = js.undefined,
  getIsSticky:                     js.UndefOr[DropTargetGetFeedbackArgs[S] => Boolean] = js.undefined,
  onDraggableGenerateDragPreview:  js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDraggableDragStart:            js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDraggableDrag:                 js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDraggableDropTargetChange:     js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDraggableDrop:                 js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDropTargetGenerateDragPreview: js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDropTargetDragStart:           js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDropTargetDrag:                js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDropTargetDropTargetChange:    js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDropTargetDrop:                js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDropTargetDragEnter:           js.UndefOr[DropTargetEventPayload[S, T] => Callback] = js.undefined,
  onDropTargetDragLeave:           js.UndefOr[DropTargetEventPayload[S, T] => Callback] = js.undefined
): HookResult[Ref.ToVdom[HTMLElement]] =
  for
    context <- useContext(DragAndDropContext.ctx)
    ref     <- useRefToVdom[HTMLElement]
    _       <- useEffectOnMount:
                 ref.get.flatMap: refValue =>
                   refValue.foldMap: elem =>
                     for
                       draggableCleanup  <-
                         ElementAdapter.draggable[ContextData[S], ContextData[T]](
                           elem,
                           js.undefined,
                           canDrag,
                           contextualizeGetInitialData(context)(getInitialData),
                           decontextualizeBaseEventHandler(onDraggableGenerateDragPreview),
                           decontextualizeBaseEventHandler(onDraggableDragStart),
                           decontextualizeBaseEventHandler(onDraggableDrag),
                           decontextualizeBaseEventHandler(onDraggableDropTargetChange),
                           decontextualizeBaseEventHandler(onDraggableDrop)
                         )
                       dropTargetCleanup <-
                         ElementAdapter.dropTarget[ContextData[S], ContextData[T]](
                           elem,
                           contextualizeGetData(context)(getData),
                           decontextualizeCanDrop(context)(canDrop),
                           decontextualizeDropTargetBooleanFunction(getIsSticky),
                           decontextualizeDropTargetEventHandler(onDropTargetGenerateDragPreview),
                           decontextualizeDropTargetEventHandler(onDropTargetDragStart),
                           decontextualizeDropTargetEventHandler(onDropTargetDrag),
                           decontextualizeDropTargetEventHandler(onDropTargetDropTargetChange),
                           decontextualizeDropTargetEventHandler(onDropTargetDrop),
                           decontextualizeDropTargetEventHandler(onDropTargetDragEnter),
                           decontextualizeDropTargetEventHandler(onDropTargetDragLeave)
                         )
                     yield draggableCleanup >> dropTargetCleanup
  yield ref

/**
 * Low-level. Will monitor globally! Unless you are doing something funky, use
 * `useDragAndDropContext` instead.
 */
def useDragAndDropMonitor[S, T](
  canMonitor:            js.UndefOr[MonitorGetFeedbackArgs[S, T] => Boolean] = js.undefined,
  onGenerateDragPreview: js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDragStart:           js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDrag:                js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDropTargetChange:    js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDrop:                js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined
): HookResult[Unit] =
  useEffectOnMount:
    ElementAdapter.monitor[S, T](
      canMonitor,
      onGenerateDragPreview,
      onDragStart,
      onDrag,
      onDropTargetChange,
      onDrop
    )

def useDragAndDropContext[S, T](
  canMonitor:            js.UndefOr[MonitorGetFeedbackArgs[S, T] => Boolean] = js.undefined,
  onGenerateDragPreview: js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDragStart:           js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDrag:                js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDropTargetChange:    js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDrop:                js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined
): HookResult[Context.Provided[DragAndDropContext]] =
  for
    contextId <- useId
    _         <- useDragAndDropMonitor[ContextData[S], ContextData[T]](
                   decontextualizeCanMonitor(contextId.some)(canMonitor),
                   decontextualizeBaseEventHandler(onGenerateDragPreview),
                   decontextualizeBaseEventHandler(onDragStart),
                   decontextualizeBaseEventHandler(onDrag),
                   decontextualizeBaseEventHandler(onDropTargetChange),
                   decontextualizeBaseEventHandler(onDrop)
                 )
  yield DragAndDropContext.ctx.provide(contextId.some)
