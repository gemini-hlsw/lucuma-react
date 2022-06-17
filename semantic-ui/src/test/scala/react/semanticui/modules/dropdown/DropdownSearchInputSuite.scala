package react.semanticui.modules.dropdown

import japgolly.scalajs.react.test._
import react.common.syntax.vdom._

class DropdownSearchInputSuite extends munit.FunSuite {
  test("pusher") {
    val pusher = DropdownSearchInput()
    ReactTestUtils.withNewBodyElement { mountNode =>
      pusher.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<input aria-autocomplete="list" autocomplete="off" class="search" type="text" value="">"""
      )
    }
  }
}
