// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.pragmaticdnd.facade

import scalajs.js

@js.native
trait DragLocationHistory[T] extends js.Object {

  /**
   * Where the drag operation started
   */
  var initial: DragLocation[T]

  /**
   * Where the user currently is
   */
  var current: DragLocation[T]

  /**
   * Where the user was previously. `previous` points to what `current` was in the last dispatched
   * event
   *
   * `previous` is particularly useful for `onDropTargetChange` (and the derived `onDragEnter` and
   * `onDragLeave`) as you can know what the delta of the change
   *
   * Exception: `onGenerateDragPreview` and `onDragStart` will have the same `current` and
   * `previous` values. This is done so that the data received in `onDragStart` feels logical
   * (`location.previous` should be `[]` in `onDragStart`)
   */
  var previous: DropTargets[T]
}
