// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.pragmaticdnd.facade

import scalajs.js

@js.native
trait Input extends js.Object {
  var altKey: Boolean
  var button: Int
  var buttons: Int
  var ctrlKey: Boolean
  var metaKey: Boolean
  var shiftKey: Boolean

  // coordinates
  var clientX: Double
  var clientY: Double
  var pageX: Double
  var pageY: Double
}
