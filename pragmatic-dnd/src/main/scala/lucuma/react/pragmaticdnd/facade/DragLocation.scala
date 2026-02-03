// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.pragmaticdnd.facade

import scalajs.js

@js.native
trait DragLocation[T] extends DropTargets[T] {

  /**
   * A users input at a point in time
   */
  var input: Input
}
