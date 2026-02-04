// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.pragmaticdnd

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.TagOf
import lucuma.react.pragmaticdnd
import lucuma.react.pragmaticdnd.facade.*
import lucuma.react.pragmaticdnd.facade.BaseEventPayload
import org.scalajs.dom.HTMLElement

import scalajs.js

extension (tag: TagOf[HTMLElement])
  def draggable[S, T](
    canDrag:               js.UndefOr[DraggableGetFeedbackArgs => CallbackTo[Boolean]] = js.undefined,
    getInitialData:        js.UndefOr[DraggableGetFeedbackArgs => CallbackTo[S]] = js.undefined,
    onGenerateDragPreview: js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
    onDragStart:           js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
    onDrag:                js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
    onDropTargetChange:    js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
    onDrop:                js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined
  ): Draggable[S, T] =
    Draggable[S, T](
      tag,
      canDrag,
      getInitialData,
      onGenerateDragPreview,
      onDragStart,
      onDrag,
      onDropTargetChange,
      onDrop
    )

  def dropTarget[S, T](
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
  ): DropTarget[S, T] =
    DropTarget[S, T](
      tag,
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
