package react.semanticui.views.item

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class ItemSuite extends munit.FunSuite {
  test("render") {
    val item = Item("Abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      item.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="item">Abc</div>""")
    }
  }
}
