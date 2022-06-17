package react.semanticui.views.item

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class ItemMetaSuite extends munit.FunSuite {
  test("render") {
    val itemMeta = ItemMeta("Abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      itemMeta.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="meta">Abc</div>""")
    }
  }
  test("renderMany") {
    val itemMeta = ItemMeta("Abc", <.div("abc"))
    ReactTestUtils.withNewBodyElement { mountNode =>
      itemMeta.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="meta">Abc<div>abc</div></div>""")
    }
  }
}
