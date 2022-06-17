package react.semanticui.views.card

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class CardGroupSuite extends munit.FunSuite {
  test("render") {
    val cardGroup = CardGroup("Abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cardGroup.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(html, """<div class="ui cards">Abc</div>""")
    }
  }
}
