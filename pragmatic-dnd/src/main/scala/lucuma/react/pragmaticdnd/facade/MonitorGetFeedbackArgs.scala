// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.pragmaticdnd.facade

import scalajs.js

@js.native
trait MonitorGetFeedbackArgs[S, T] extends js.Object {

  /**
   * The users `initial` drag location
   */
  var initial: DragLocation[T]

  /**
   * The data associated with the entity being dragged
   */
  var source: ElementDragPayload[S]
}

object MonitorGetFeedbackArgs:
  def apply[S, T](
    initial: DragLocation[T],
    source:  ElementDragPayload[S]
  ): MonitorGetFeedbackArgs[S, T] =
    val p = (new js.Object).asInstanceOf[MonitorGetFeedbackArgs[S, T]]
    p.initial = initial
    p.source = source
    p
