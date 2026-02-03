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
