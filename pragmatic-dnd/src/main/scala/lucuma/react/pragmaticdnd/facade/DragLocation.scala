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

object DragLocation:
  def apply[T](input: Input, dropTargets: js.Array[DropTargetRecord[T]]): DragLocation[T] =
    val p = (new js.Object).asInstanceOf[DragLocation[T]]
    p.input = input
    p.dropTargets = dropTargets
    p
