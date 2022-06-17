package react.semanticui.views.item

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class ItemDescriptionSuite extends munit.FunSuite {
  test("render") {
    val id = ItemDescription("Abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      id.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="description">Abc</div>""")
    }
  }
}
