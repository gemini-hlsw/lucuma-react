// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.pragmaticdnd

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.TagOf
import lucuma.react.common.*
import lucuma.react.pragmaticdnd
import lucuma.react.pragmaticdnd.facade.*
import org.scalajs.dom.HTMLElement

import scalajs.js

final case class DropTarget[S, T](
  tag:                   TagOf[HTMLElement],
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
) extends ReactFnProps(DropTarget.component)

private abstract class DropTargetBuilder[S, T]:
  val component = ScalaFnComponent[DropTarget[S, T]]: props =>
    for ref <- useDropTargetRef(
                 props.getData,
                 props.canDrop,
                 props.getIsSticky,
                 props.onGenerateDragPreview,
                 props.onDragStart,
                 props.onDrag,
                 props.onDropTargetChange,
                 props.onDrop,
                 props.onDragEnter,
                 props.onDragLeave
               )
    yield props.tag.withRef(ref)

object DropTarget extends DropTargetBuilder
