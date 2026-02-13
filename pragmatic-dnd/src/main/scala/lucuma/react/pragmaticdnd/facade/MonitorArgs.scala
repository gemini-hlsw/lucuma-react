// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.pragmaticdnd.facade

import japgolly.scalajs.react.callback.*

import scalajs.js

@js.native
trait MonitorArgs[S, T] extends CommonEvents[BaseEventPayload[S, T]] {
  var canMonitor: js.UndefOr[js.Function1[MonitorGetFeedbackArgs[S, T], Boolean]]
}

object MonitorArgs {
  def apply[S, T](
    canMonitor:            js.UndefOr[MonitorGetFeedbackArgs[S, T] => Boolean] = js.undefined,
    onGenerateDragPreview: js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
    onDragStart:           js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
    onDrag:                js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
    onDropTargetChange:    js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined,
    onDrop:                js.UndefOr[BaseEventPayload[S, T] => Callback] = js.undefined
  ): MonitorArgs[S, T] = {
    val p = (new js.Object).asInstanceOf[MonitorArgs[S, T]]
    p.applyOrNot(canMonitor, (p, f) => p.canMonitor = args => f(args))
      .applyOrNot(
        onGenerateDragPreview,
        (p, f) => p.onGenerateDragPreview = args => f(args).runNow()
      )
      .applyOrNot(
        onDragStart,
        (p, f) => p.onDragStart = args => { println("HELLO"); f(args).runNow() }
      )
      .applyOrNot(onDrag, (p, f) => p.onDrag = args => f(args).runNow())
      .applyOrNot(onDropTargetChange, (p, f) => p.onDropTargetChange = args => f(args).runNow())
      .applyOrNot(onDrop, (p, f) => p.onDrop = args => f(args).runNow())
  }
}
