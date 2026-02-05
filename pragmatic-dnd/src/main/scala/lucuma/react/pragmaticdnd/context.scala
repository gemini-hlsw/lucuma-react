// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.pragmaticdnd

import cats.syntax.all.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.feature.Context
import lucuma.react.pragmaticdnd.facade.*

import scalajs.js

type DragAndDropContext = Option[String]

object DragAndDropContext:
  val ctx: Context[DragAndDropContext] = React.createContext("DragAndDropContext", none)

private[pragmaticdnd] final case class ContextData[D](context: DragAndDropContext, data: D)

private[pragmaticdnd] def contextualizeDropTargetRecord[T](context: DragAndDropContext)(
  dtr: DropTargetRecord[T]
): DropTargetRecord[ContextData[T]] =
  DropTargetRecord[ContextData[T]](
    dtr.element,
    ContextData(context, dtr.data),
    dtr.isActiveDueToStickiness
  )

private[pragmaticdnd] def decontextualizeDropTargetRecord[T](
  dtr: DropTargetRecord[ContextData[T]]
): DropTargetRecord[T] =
  DropTargetRecord[T](dtr.element, dtr.data.data, dtr.isActiveDueToStickiness)

private def contextualizeDragLocation[T](context: DragAndDropContext)(
  dl: DragLocation[T]
): DragLocation[ContextData[T]] =
  DragLocation[ContextData[T]](dl.input, dl.dropTargets.map(contextualizeDropTargetRecord(context)))

private def decontextualizeDragLocation[T](dl: DragLocation[ContextData[T]]): DragLocation[T] =
  DragLocation[T](dl.input, dl.dropTargets.map(decontextualizeDropTargetRecord))

private def contextualizeDragLocationHistory[T](context: DragAndDropContext)(
  dlh: DragLocationHistory[T]
): DragLocationHistory[ContextData[T]] =
  DragLocationHistory[ContextData[T]](
    contextualizeDragLocation(context)(dlh.initial),
    contextualizeDragLocation(context)(dlh.current),
    DropTargets(dlh.previous.dropTargets.map(contextualizeDropTargetRecord(context)))
  )

private def decontextualizeDragLocationHistory[T](
  dlh: DragLocationHistory[ContextData[T]]
): DragLocationHistory[T] =
  DragLocationHistory[T](
    decontextualizeDragLocation(dlh.initial),
    decontextualizeDragLocation(dlh.current),
    DropTargets(dlh.previous.dropTargets.map(decontextualizeDropTargetRecord))
  )

private def contextualizeElementDragPayload[S](context: DragAndDropContext)(
  edp: ElementDragPayload[S]
): ElementDragPayload[ContextData[S]] =
  ElementDragPayload[ContextData[S]](edp.element, edp.dragHandle, ContextData(context, edp.data))

private def decontextualizeElementDragPayload[S](
  edp: ElementDragPayload[ContextData[S]]
): ElementDragPayload[S] =
  ElementDragPayload[S](edp.element, edp.dragHandle, edp.data.data)

private def decontextualizeDropTargetGetFeedbackArgs[S](
  args: DropTargetGetFeedbackArgs[ContextData[S]]
): DropTargetGetFeedbackArgs[S] =
  DropTargetGetFeedbackArgs[S](
    args.input,
    decontextualizeElementDragPayload(args.source),
    args.element
  )

private def contextualizeBaseEventPayload[S, T](context: DragAndDropContext)(
  payload: BaseEventPayload[S, T]
): BaseEventPayload[ContextData[S], ContextData[T]] =
  BaseEventPayload[ContextData[S], ContextData[T]](
    contextualizeDragLocationHistory(context)(payload.location),
    contextualizeElementDragPayload(context)(payload.source)
  )

private def decontextualizeBaseEventPayload[S, T](
  payload: BaseEventPayload[ContextData[S], ContextData[T]]
): BaseEventPayload[S, T] =
  BaseEventPayload[S, T](
    decontextualizeDragLocationHistory(payload.location),
    decontextualizeElementDragPayload(payload.source)
  )

private def decontextualizeBaseEventHandler[S, T](
  f: js.UndefOr[BaseEventPayload[S, T] => Callback]
): js.UndefOr[BaseEventPayload[ContextData[S], ContextData[T]] => Callback] =
  f.map: handler =>
    (payload: BaseEventPayload[ContextData[S], ContextData[T]]) =>
      handler(decontextualizeBaseEventPayload(payload))

private def decontextualizeDropTargetEventPayload[S, T](
  payload: DropTargetEventPayload[ContextData[S], ContextData[T]]
): DropTargetEventPayload[S, T] =
  DropTargetEventPayload[S, T](
    decontextualizeDragLocationHistory(payload.location),
    decontextualizeElementDragPayload(payload.source),
    decontextualizeDropTargetRecord(payload.self)
  )

private def decontextualizeDropTargetEventHandler[S, T](
  f: js.UndefOr[DropTargetEventPayload[S, T] => Callback]
): js.UndefOr[DropTargetEventPayload[ContextData[S], ContextData[T]] => Callback] =
  f.map: handler =>
    (payload: DropTargetEventPayload[ContextData[S], ContextData[T]]) =>
      handler(decontextualizeDropTargetEventPayload(payload))

private def contextualizeGetInitialData[S](context: DragAndDropContext)(
  f: js.UndefOr[DraggableGetFeedbackArgs => CallbackTo[S]]
): js.UndefOr[DraggableGetFeedbackArgs => CallbackTo[ContextData[S]]] =
  f.map(getter => args => getter(args).map(s => ContextData(context, s)))

private def decontextualizeDropTargetBooleanFunction[S, T](
  f: js.UndefOr[DropTargetGetFeedbackArgs[S] => CallbackTo[Boolean]]
): js.UndefOr[DropTargetGetFeedbackArgs[ContextData[S]] => CallbackTo[Boolean]] =
  f.map: func =>
    args => func(decontextualizeDropTargetGetFeedbackArgs(args))

private def contextualizeGetData[S, T](context: DragAndDropContext)(
  f: js.UndefOr[DropTargetGetFeedbackArgs[S] => CallbackTo[T]]
): js.UndefOr[DropTargetGetFeedbackArgs[ContextData[S]] => CallbackTo[ContextData[T]]] =
  f.map: getter =>
    args => getter(decontextualizeDropTargetGetFeedbackArgs(args)).map(t => ContextData(context, t))

private def decontextualizeCanDrop[S](context: DragAndDropContext)(
  f: js.UndefOr[DropTargetGetFeedbackArgs[S] => CallbackTo[Boolean]]
): DropTargetGetFeedbackArgs[ContextData[S]] => CallbackTo[Boolean] =
  args =>
    CallbackTo:
      val isInContext: Boolean = args.source.data.context === context
      isInContext && f.fold(true): canDrop =>
        canDrop(
          DropTargetGetFeedbackArgs(
            args.input,
            decontextualizeElementDragPayload(args.source),
            args.element
          )
        ).runNow()

private def decontextualizeCanMonitor[S, T](context: DragAndDropContext)(
  f: js.UndefOr[MonitorGetFeedbackArgs[S, T] => CallbackTo[Boolean]]
): MonitorGetFeedbackArgs[ContextData[S], ContextData[T]] => CallbackTo[Boolean] =
  args =>
    CallbackTo:
      val isInContext: Boolean =
        args.source.data.context === context &&
          (args.initial.dropTargets.length === 0 ||
            args.initial.dropTargets(0).data.context === context)
      isInContext && f.fold(true): canMonitor =>
        canMonitor(
          MonitorGetFeedbackArgs(
            decontextualizeDragLocation(args.initial),
            decontextualizeElementDragPayload(args.source)
          )
        ).runNow()
