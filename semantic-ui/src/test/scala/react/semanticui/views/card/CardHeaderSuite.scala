package react.semanticui.views.card

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class CardHeaderSuite extends munit.FunSuite {
  test("render") {
    val header = CardHeader("Abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      header.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="header">Abc</div>""")
    }
  }
}
