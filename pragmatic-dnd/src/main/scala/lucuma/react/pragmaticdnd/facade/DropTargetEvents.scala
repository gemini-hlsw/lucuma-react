// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.pragmaticdnd.facade

import scalajs.js

type DropTargetEventPayload[S, T] = BaseEventPayload[S, T] & DropTargetLocalizedData[T]

@js.native
trait DropTargetEvents[S, T] extends CommonEvents[DropTargetEventPayload[S, T]] {

  /**
   * Derived from the `onDropTargetChange` event (`onDragEnter` is not it's own event that bubbles)
   *
   * `onDragEnter` is fired when _this_ drop target is entered into and not when any child drop
   * targets change
   */
  var onDragEnter: js.Function1[DropTargetEventPayload[S, T], Unit]

  /**
   * Derived from the `onDropTargetChange` event (`onDragLeave` is not it's own event that bubbles)
   *
   * `onDragLeave` is fired when _this_ drop target is exited from and not when any child drop
   * targets change
   */
  var onDragLeave: js.Function1[DropTargetEventPayload[S, T], Unit]
}
