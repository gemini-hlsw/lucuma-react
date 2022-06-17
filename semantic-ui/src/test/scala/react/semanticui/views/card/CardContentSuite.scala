package react.semanticui.views.card

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class CardContentSuite extends munit.FunSuite {
  test("render") {
    val cc = CardContent("Abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cc.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(html, """<div class="content">Abc</div>""")
    }
  }
}
