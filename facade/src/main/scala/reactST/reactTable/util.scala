package reactST.reactTable

import japgolly.scalajs.react.util.JsUtil
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.vdom.VdomBuilder
import scalajs.js

object util {

  /**
   * Helper method for taking a properties object returned by JS
   * and turning it into a TagMod of attributes.
   */
  def props2Attrs(obj: js.Object): TagMod =
    TagMod(
      TagMod.fn(_.addAttrsObject(obj, _ != "style")),
      obj
        .asInstanceOf[js.Dictionary[js.Object]]
        .get("style")
        .fold(TagMod.empty)(styles => TagMod.fn(_.addStylesObject(styles)))
    )
}
