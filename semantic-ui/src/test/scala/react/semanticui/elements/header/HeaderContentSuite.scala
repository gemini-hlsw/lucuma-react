package react.semanticui.elements.header

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class HeaderContentSuite extends munit.FunSuite {
  test("render") {
    val header = HeaderContent()
    ReactTestUtils.withNewBodyElement { mountNode =>
      header.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="content"></div>""")
    }
  }
  test("renderAs") {
    val header = HeaderContent(as = <.a)
    ReactTestUtils.withNewBodyElement { mountNode =>
      header.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<a class="content"></a>""")
    }
  }
}
