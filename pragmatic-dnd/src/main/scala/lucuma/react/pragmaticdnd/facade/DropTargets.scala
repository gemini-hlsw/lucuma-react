// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.pragmaticdnd.facade

import scalajs.js

@js.native
trait DropTargets[T] extends js.Object {

  /**
   * A _bubble_ ordered (innermost upwards) list of active drop targets
   *
   * @example
   *   [grandChildRecord, childRecord, parentRecord]
   */
  var dropTargets: js.Array[DropTargetRecord[T]]
}

object DropTargets {
  def apply[T](dropTargets: js.Array[DropTargetRecord[T]]): DropTargets[T] =
    val p = (new js.Object).asInstanceOf[DropTargets[T]]
    p.dropTargets = dropTargets
    p
}
