package react.semanticui.elements.button

import japgolly.scalajs.react.test._
import react.common.syntax.vdom._

class ButtonOrSuite extends munit.FunSuite {
  test("render") {
    val buttonOr = ButtonOr()
    ReactTestUtils.withNewBodyElement { mountNode =>
      buttonOr.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="or"></div>""")
    }
  }
}
