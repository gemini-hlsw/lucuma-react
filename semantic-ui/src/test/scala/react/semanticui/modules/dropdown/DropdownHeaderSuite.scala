package react.semanticui.modules.dropdown

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class DropdownHeaderSuite extends munit.FunSuite {
  test("header") {
    val header = DropdownHeader()
    ReactTestUtils.withNewBodyElement { mountNode =>
      header.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="header"></div>"""
      )
    }
  }
  test("headerContent") {
    val header = DropdownHeader("content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      header.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="header">content</div>""")
    }
  }
}
