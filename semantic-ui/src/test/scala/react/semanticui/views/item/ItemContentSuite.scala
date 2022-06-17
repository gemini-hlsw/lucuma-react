package react.semanticui.views.item

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class ItemContentSuite extends munit.FunSuite {
  test("render") {
    val ic = ItemContent("Abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      ic.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="content">Abc</div>""")
    }
  }
}
