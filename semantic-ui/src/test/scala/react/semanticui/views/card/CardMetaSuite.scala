package react.semanticui.views.card

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class CardMetaSuite extends munit.FunSuite {
  test("render") {
    val cardMeta = CardMeta("Abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cardMeta.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="meta">Abc</div>""")
    }
  }
  test("renderMany") {
    val cardMeta = CardMeta("Abc", <.div("abc"))
    ReactTestUtils.withNewBodyElement { mountNode =>
      cardMeta.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="meta">Abc<div>abc</div></div>""")
    }
  }
}
