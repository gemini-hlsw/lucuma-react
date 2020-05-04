package react.common

import scala.scalajs.js
import japgolly.scalajs.react.raw.JsNumber

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
