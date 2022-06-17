package react.semanticui.elements.list

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class ListContentSuite extends munit.FunSuite {
  test("render") {
    val header = ListContent()
    ReactTestUtils.withNewBodyElement { mountNode =>
      header.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="content"></div>""")
    }
  }
  test("renderAs") {
    val header = ListContent(as = <.a)
    ReactTestUtils.withNewBodyElement { mountNode =>
      header.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<a class="content"></a>""")
    }
  }
}
