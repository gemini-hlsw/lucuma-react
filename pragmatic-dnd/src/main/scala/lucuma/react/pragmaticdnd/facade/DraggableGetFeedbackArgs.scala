// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.pragmaticdnd.facade

import org.scalajs.dom.HTMLElement

import scalajs.js

@js.native
trait DraggableGetFeedbackArgs extends js.Object {

  /**
   * The user input as a drag is trying to start (the `initial` input)
   */
  var input: Input

  /**
   * The `draggable` element
   */
  var element: HTMLElement

  /**
   * The `dragHandle` element for the `draggable`
   */
  var dragHandle: js.UndefOr[HTMLElement]
}
