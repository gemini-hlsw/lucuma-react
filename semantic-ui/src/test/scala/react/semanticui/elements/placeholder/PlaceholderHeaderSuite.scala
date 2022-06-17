package react.semanticui.elements.placeholder

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class PlaceholderHeaderSuite extends munit.FunSuite {
  test("render") {
    val paragraph = PlaceholderHeader()
    ReactTestUtils.withNewBodyElement { mountNode =>
      paragraph.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="header"></div>""")
    }
  }
  test("renderChild") {
    val paragraph = PlaceholderHeader(<.div("abc"))
    ReactTestUtils.withNewBodyElement { mountNode =>
      paragraph.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="header"><div>abc</div></div>""")
    }
  }
}
