// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.pragmaticdnd.facade

import scalajs.js

/**
 * The common data that is provided to all events.
 *
 * @typeparam
 *   {S} Source (Draggable)
 * @typeparam
 *   {T} Target (Drop Target)
 */
@js.native
trait BaseEventPayload[S, T] extends js.Object {

  /**
   * Location history for the drag operation
   */
  var location: DragLocationHistory[T]

  /**
   * Data associated with the entity that is being dragged
   */
  var source: ElementDragPayload[S]
}
