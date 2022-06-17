package react.semanticui.modules.dropdown

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class DropdownMenuSuite extends munit.FunSuite {
  test("menu") {
    val menu = DropdownMenu()
    ReactTestUtils.withNewBodyElement { mountNode =>
      menu.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="menu transition"></div>"""
      )
    }
  }
  test("menuContent") {
    val menu = DropdownMenu("content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      menu.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="menu transition">content</div>"""
      )
    }
  }
}
