// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.common

import scala.scalajs.js
import japgolly.scalajs.react.facade.JsNumber

@js.native
trait Size extends js.Object {
  var height: JsNumber
  var width: JsNumber
}

object Size {
  def apply(height: JsNumber, width: JsNumber): Size = {
    val p = (new js.Object).asInstanceOf[Size]
    p.height = height
    p.width = width
    p
  }
}
