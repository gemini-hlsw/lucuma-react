package react.common

import scala.scalajs.js

@js.native
trait Size extends js.Object {
  var height: Double
  var width: Double
}

object Size {
  def apply(height: Double, width: Double): Size = {
    val p = (new js.Object).asInstanceOf[Size]
    p.height = height
    p.width = width
    p
  }
}
