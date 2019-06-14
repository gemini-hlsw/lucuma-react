package react
package common

import scala.scalajs.js
import scala.scalajs.js.|
import js.JSConverters._
import japgolly.scalajs.react.vdom.html_<^._

package object style {

  implicit final def cssToTagMod(s: Css): TagMod =
    ^.className := s.htmlClass

  implicit final def cssToTagString(s: Css): String =
    s.htmlClass.mkString(" ")
}

package style {

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

  /**
    * Simple class to represent css class
    */
  final case class Css(htmlClasses: List[String]) {
    val htmlClass: String = htmlClasses.mkString(" ")
  }

  object Css {
    def apply(htmlClass: String): Css = Css(List(htmlClass))

    val Zero: Css = Css(Nil)
  }
}
