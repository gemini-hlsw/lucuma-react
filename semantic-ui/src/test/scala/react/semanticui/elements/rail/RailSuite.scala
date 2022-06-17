package react.semanticui.elements.rail

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.semanticui.floats._
import react.common.syntax.vdom._

class RailSuite extends munit.FunSuite {
  test("render") {
    val rail = Rail(position = Left)("abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      rail.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="ui left rail">abc</div>""")
    }
  }
}
