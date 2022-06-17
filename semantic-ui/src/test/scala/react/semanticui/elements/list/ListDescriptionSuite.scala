package react.semanticui.elements.list

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class ListDescriptionSuite extends munit.FunSuite {
  test("render") {
    val header = ListDescription()
    ReactTestUtils.withNewBodyElement { mountNode =>
      header.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="description"></div>""")
    }
  }
  test("renderAs") {
    val header = ListDescription(as = <.a)
    ReactTestUtils.withNewBodyElement { mountNode =>
      header.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<a class="description"></a>""")
    }
  }
}
