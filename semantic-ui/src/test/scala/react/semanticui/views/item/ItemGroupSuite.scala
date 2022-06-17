package react.semanticui.views.item

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class ItemGroupSuite extends munit.FunSuite {
  test("render") {
    val itemGroup = ItemGroup("Abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      itemGroup.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="ui items">Abc</div>""")
    }
  }
}
