package react.semanticui.collections.menu

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class MenuMenuSuite extends munit.FunSuite {
  test("render") {
    val menumenu = MenuMenu()
    ReactTestUtils.withNewBodyElement { mountNode =>
      menumenu.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="menu"></div>""")
    }
  }
  test("renderAs") {
    val menumenu = MenuMenu(as = <.a)
    ReactTestUtils.withNewBodyElement { mountNode =>
      menumenu.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<a class="menu"></a>""")
    }
  }
}
