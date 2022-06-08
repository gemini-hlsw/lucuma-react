package react.clipboard

import japgolly.scalajs.react.vdom.html_<^.{ < => <<, _ }
import react.common.TestUtils

class CopyToClipboardSuite extends munit.FunSuite with TestUtils {
  test("render") {
    val table =
      CopyToClipboard(text = "text")(<<.div("Copy"))
    val html  =
      """<div><div>Copy</div></div>""".stripMargin.replaceAll("[\n\r]", "")
    assertRender(table, html)
  }
}
