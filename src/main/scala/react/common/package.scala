package react

import scala.scalajs.js
import scala.scalajs.js.|
import js.JSConverters._

package object common

package common {

  /**
    * Simple class to represent styles
    */
  final case class Style(styles: Map[String, String | Int])

  object Style {

    def toJsObject(style: Style): js.Object =
      style.styles.toJSDictionary.asInstanceOf[js.Object]

    def fromJsObject(o: js.Object): Style = {
      val xDict = o.asInstanceOf[js.Dictionary[String | Int]]
      val map   = (for ((prop, value) <- xDict) yield prop -> value).toMap
      Style(map)
    }
  }

}
