package react.semanticui.modules.dropdown

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class DropdownDividerSuite extends munit.FunSuite {
  test("pusher") {
    val pusher = DropdownDivider("Abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      pusher.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="divider">Abc</div>"""
      )
    }
  }
}
