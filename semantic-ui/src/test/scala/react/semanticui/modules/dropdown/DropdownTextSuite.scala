package react.semanticui.modules.dropdown

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class DropdownTextSuite extends munit.FunSuite {
  test("text") {
    val header = DropdownText()
    ReactTestUtils.withNewBodyElement { mountNode =>
      header.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div aria-atomic="true" aria-live="polite" role="alert" class="divider"></div>"""
      )
    }
  }
  test("textContent") {
    val header = DropdownText("content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      header.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div aria-atomic="true" aria-live="polite" role="alert" class="divider">content</div>"""
      )
    }
  }
}
