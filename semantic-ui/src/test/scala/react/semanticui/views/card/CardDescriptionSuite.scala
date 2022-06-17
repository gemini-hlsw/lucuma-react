package react.semanticui.views.card

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class CardDescriptionSuite extends munit.FunSuite {
  test("render") {
    val cd = CardDescription("Abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cd.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="description">Abc</div>""")
    }
  }
}
