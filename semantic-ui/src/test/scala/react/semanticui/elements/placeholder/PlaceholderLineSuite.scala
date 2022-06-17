package react.semanticui.elements.placeholder

import japgolly.scalajs.react.test._
import react.common.syntax.vdom._

class PlaceholderLineSuite extends munit.FunSuite {
  test("render") {
    val paragraph = PlaceholderLine()
    ReactTestUtils.withNewBodyElement { mountNode =>
      paragraph.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="line"></div>""")
    }
  }
}
