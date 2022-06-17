package react.semanticui.collections.menu

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class MenuHeaderSuite extends munit.FunSuite {
  test("render") {
    val menuHeader = MenuHeader()
    ReactTestUtils.withNewBodyElement { mountNode =>
      menuHeader.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="header"></div>""")
    }
  }
  test("renderAsTag") {
    val menuHeader = MenuHeader(as = <.a)
    ReactTestUtils.withNewBodyElement { mountNode =>
      menuHeader.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<a class="header"></a>""")
    }
  }
  test("renderAsHTMLTag") {
    val menuHeader = MenuHeader(as = <.div)
    ReactTestUtils.withNewBodyElement { mountNode =>
      menuHeader.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="header"></div>""")
    }
  }
}
