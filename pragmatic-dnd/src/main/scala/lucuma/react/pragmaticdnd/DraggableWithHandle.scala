// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.pragmaticdnd

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.TagOf
import lucuma.react.common.*
import lucuma.react.pragmaticdnd.facade.*
import org.scalajs.dom.HTMLElement

import scalajs.js

final case class DraggableWithHandle[S, T](
  tag:                   Ref.ToVdom[HTMLElement] => TagOf[HTMLElement],
  canDrag:               js.UndefOr[DraggableGetFeedbackArgs => CallbackTo[Boolean]] = js.undefined,
  getInitialData:        js.UndefOr[DraggableGetFeedbackArgs => CallbackTo[S]] = js.undefined,
  onGenerateDragPreview: js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDragStart:           js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDrag:                js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDropTargetChange:    js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDrop:                js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined
) extends ReactFnProps(DraggableWithHandle.component)

private abstract class DraggableWithHandleBuilder[S, T]:
  val component = ScalaFnComponent[DraggableWithHandle[S, T]]: props =>
    for (ref, handleRef) <-
        useDraggableWithHandleRefs(
          props.canDrag,
          props.getInitialData,
          props.onGenerateDragPreview,
          props.onDragStart,
          props.onDrag,
          props.onDropTargetChange,
          props.onDrop
        )
    yield props.tag(handleRef).withRef(ref)

object DraggableWithHandle extends DraggableWithHandleBuilder[Any, Any]
