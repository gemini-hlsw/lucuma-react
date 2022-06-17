package react.semanticui.elements.header

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class HeaderSubheaderSuite extends munit.FunSuite {
  test("render") {
    val header = HeaderSubheader()
    ReactTestUtils.withNewBodyElement { mountNode =>
      header.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="sub header"></div>""")
    }
  }
  test("renderAs") {
    val header = HeaderSubheader(as = <.a)
    ReactTestUtils.withNewBodyElement { mountNode =>
      header.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<a class="sub header"></a>""")
    }
  }
}
