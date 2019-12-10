package react

package clipboard

import japgolly.scalajs.react.vdom.html_<^.{ < => <<, _ }
import react.common.TestUtils
import utest._

object CopyToClipboardTest extends TestSuite with TestUtils {
  val tests = Tests {
    'render - {
      val table =
        CopyToClipboard(text = "text")(<<.div("Copy"))
      val html =
        """<div><div>Copy</div></div>""".stripMargin.replaceAll("[\n\r]", "")
      assertRender(table, html)
    }
  }
}
