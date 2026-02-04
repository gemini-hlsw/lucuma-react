// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.pragmaticdnd.facade

import org.scalajs.dom.HTMLElement

import scalajs.js

@js.native
trait DropTargetGetFeedbackArgs[S] extends js.Object {

  /**
   * The users _current_ input
   */
  var input: Input

  /**
   * The data associated with the entity being dragged
   */
  var source: ElementDragPayload[S]

  /**
   * This drop target's element
   */
  var element: HTMLElement
}

object DropTargetGetFeedbackArgs:
  def apply[S](
    input:   Input,
    source:  ElementDragPayload[S],
    element: HTMLElement
  ): DropTargetGetFeedbackArgs[S] =
    val p = (new js.Object).asInstanceOf[DropTargetGetFeedbackArgs[S]]
    p.input = input
    p.source = source
    p.element = element
    p
