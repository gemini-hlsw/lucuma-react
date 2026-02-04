// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.pragmaticdnd

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.TagOf
import lucuma.react.common.*
import lucuma.react.pragmaticdnd.facade.*
import org.scalajs.dom.HTMLElement

import scalajs.js

final case class Draggable[S, T](
  tag:                   TagOf[HTMLElement],
  canDrag:               js.UndefOr[DraggableGetFeedbackArgs => CallbackTo[Boolean]] = js.undefined,
  getInitialData:        js.UndefOr[DraggableGetFeedbackArgs => CallbackTo[S]] = js.undefined,
  onGenerateDragPreview: js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDragStart:           js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDrag:                js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDropTargetChange:    js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
  onDrop:                js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined
) extends ReactFnProps(Draggable.component)

private abstract class DraggableBuilder[S, T]:
  val component = ScalaFnComponent[Draggable[S, T]]: props =>
    for ref <- useDraggableRef(
                 props.canDrag,
                 props.getInitialData,
                 props.onGenerateDragPreview,
                 props.onDragStart,
                 props.onDrag,
                 props.onDropTargetChange,
                 props.onDrop
               )
    yield props.tag.withRef(ref)

object Draggable extends DraggableBuilder[Any, Any]
