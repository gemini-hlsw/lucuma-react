package react.semanticui.elements.header

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class HeaderSuite extends munit.FunSuite {
  test("render") {
    val header = Header()
    ReactTestUtils.withNewBodyElement { mountNode =>
      header.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="ui header"></div>""")
    }
  }
  test("renderAs") {
    val header = Header(as = <.a)
    ReactTestUtils.withNewBodyElement { mountNode =>
      header.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<a class="ui header"></a>""")
    }
  }
  test("subheader") {
    val header =
      Header(subheader = HeaderSubheader("abc"))
    ReactTestUtils.withNewBodyElement { mountNode =>
      header.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="ui header"><div class="sub header">abc</div></div>"""
      )
    }
  }
}
