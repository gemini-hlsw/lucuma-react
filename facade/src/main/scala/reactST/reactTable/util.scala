package reactST.reactTable

import japgolly.scalajs.react.internal.JsUtil
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.vdom.Builder
import scalajs.js

object util {

  /**
   * Helper method for taking a properties object returned by JS
   * and turning it into a TagMod of attributes.
   */
  def props2Attrs(obj: js.Object): TagMod =
    TagMod(
      TagMod.fn(_.addAttrsObject(obj, except = Set("style"))),
      obj
        .asInstanceOf[js.Dictionary[js.Object]]
        .get("style")
        .fold(TagMod.empty)(styles => TagMod.fn(_.addStylesObject(styles)))
    )

  implicit class BuilderOps(b: Builder) {
    def addAttrsObject(o: js.Object, except: Set[String] = Set.empty): Unit =
      for ((k, v) <- JsUtil.objectIterator(o) if !except.contains(k)) b.addAttr(k, v)
  }
}
