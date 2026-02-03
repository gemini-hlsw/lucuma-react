// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.pragmaticdnd.facade

import scalajs.js

@js.native
trait DropTargetLocalizedData[T] extends js.Object {

  /**
   * A convenance pointer to this drop targets values
   */
  var self: DropTargetRecord[T]
}
