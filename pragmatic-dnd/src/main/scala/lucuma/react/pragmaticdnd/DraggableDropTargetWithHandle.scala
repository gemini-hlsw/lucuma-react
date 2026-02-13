// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.pragmaticdnd

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.TagOf
import lucuma.react.common.*
import lucuma.react.pragmaticdnd.facade.*
import org.scalajs.dom.HTMLElement

import scalajs.js

final case class DraggableDropTargetWithHandle[S, T](
  tag:                             Ref.ToVdom[HTMLElement] => TagOf[HTMLElement],
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
) extends ReactFnProps(DraggableDropTargetWithHandle.component)

private abstract class DraggableDropTargetWithHandleBuilder[S, T]:
  val component = ScalaFnComponent[DraggableDropTargetWithHandle[S, T]]: props =>
    for (ref, handleRef) <-
        useDraggableDropTargetWithHandleRefs(
          props.canDrag,
          props.canDrop,
          props.getInitialData,
          props.getData,
          props.getIsSticky,
          props.onDraggableGenerateDragPreview,
          props.onDraggableDragStart,
          props.onDraggableDrag,
          props.onDraggableDropTargetChange,
          props.onDraggableDrop,
          props.onDropTargetGenerateDragPreview,
          props.onDropTargetDragStart,
          props.onDropTargetDrag,
          props.onDropTargetDropTargetChange,
          props.onDropTargetDrop,
          props.onDropTargetDragEnter,
          props.onDropTargetDragLeave
        )
    yield props.tag(handleRef).withRef(ref)

object DraggableDropTargetWithHandle extends DraggableDropTargetWithHandleBuilder[Any, Any]
