package react.semanticui.views.card

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class CardSuite extends munit.FunSuite {
  test("render") {
    val card = Card("Abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      card.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assert(html == """<div class="ui card">Abc</div>""")
    }
  }
}
