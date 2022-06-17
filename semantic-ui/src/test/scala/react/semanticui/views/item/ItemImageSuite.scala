package react.semanticui.views.item

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class ItemImageSuite extends munit.FunSuite {
  test("render") {
    val header = ItemImage(src = "Abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      header.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="image"><img src="Abc"></div>""")
    }
  }
}
