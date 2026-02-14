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

@js.native
private[pragmaticdnd] trait ContextData[D] extends Data[D]:
  var context: DragAndDropContext

private[pragmaticdnd] object ContextData:
  def fromData[D](context: DragAndDropContext, data: Data[D]): ContextData[D] =
    val p = data.asInstanceOf[ContextData[D]]
    p.context = context
    p

private[pragmaticdnd] def contextualizeDropTargetRecord[T](context: DragAndDropContext)(
  dtr: DropTargetRecord[T]
): DropTargetRecord[T] =
  DropTargetRecord[T](
    dtr.element,
    ContextData.fromData(context, dtr.data),
    dtr.isActiveDueToStickiness
  )

private def contextualizeDragLocation[T](context: DragAndDropContext)(
  dl: DragLocation[T]
): DragLocation[T] =
  DragLocation[T](dl.input, dl.dropTargets.map(contextualizeDropTargetRecord(context)))

private def contextualizeDragLocationHistory[T](context: DragAndDropContext)(
  dlh: DragLocationHistory[T]
): DragLocationHistory[T] =
  DragLocationHistory[T](
    contextualizeDragLocation(context)(dlh.initial),
    contextualizeDragLocation(context)(dlh.current),
    DropTargets(dlh.previous.dropTargets.map(contextualizeDropTargetRecord(context)))
  )

private def contextualizeElementDragPayload[S](context: DragAndDropContext)(
  edp: ElementDragPayload[S]
): ElementDragPayload[S] =
  ElementDragPayload[S](edp.element, edp.dragHandle, ContextData.fromData(context, edp.data))

private def contextualizeBaseEventPayload[S, T](context: DragAndDropContext)(
  payload: BaseEventPayload[S, T]
): BaseEventPayload[S, T] =
  BaseEventPayload[S, T](
    contextualizeDragLocationHistory(context)(payload.location),
    contextualizeElementDragPayload(context)(payload.source)
  )

private def contextualizeGetInitialData[S](context: DragAndDropContext)(
  f: js.UndefOr[DraggableGetFeedbackArgs => Data[S]]
): js.UndefOr[DraggableGetFeedbackArgs => Data[S]] =
  f.map(getter => args => ContextData.fromData(context, getter(args)))

private def contextualizeGetData[S, T](context: DragAndDropContext)(
  f: js.UndefOr[DropTargetGetFeedbackArgs[S] => Data[T]]
): js.UndefOr[DropTargetGetFeedbackArgs[S] => Data[T]] =
  f.map: getter =>
    args => ContextData.fromData(context, getter(args))

private def decontextualizeCanDrop[S](context: DragAndDropContext)(
  f: js.UndefOr[DropTargetGetFeedbackArgs[S] => Boolean]
): DropTargetGetFeedbackArgs[S] => Boolean =
  args =>
    val isInContext: Boolean = args.source.data.asInstanceOf[ContextData[S]].context === context
    isInContext && f.fold(true)(_(args))

private def decontextualizeCanMonitor[S, T](context: DragAndDropContext)(
  f: js.UndefOr[MonitorGetFeedbackArgs[S, T] => Boolean]
): MonitorGetFeedbackArgs[S, T] => Boolean =
  args =>
    val isInContext: Boolean =
      args.source.data.asInstanceOf[ContextData[S]].context === context &&
        args.initial.dropTargets.headOption
          .forall(_.data.asInstanceOf[ContextData[T]].context === context)
    isInContext && f.fold(true)(_(args))
