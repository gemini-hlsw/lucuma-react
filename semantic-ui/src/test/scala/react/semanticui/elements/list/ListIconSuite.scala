package react.semanticui.elements.list

import japgolly.scalajs.react.test._
import react.common.syntax.vdom._

class ListIconSuite extends munit.FunSuite {
  test("render") {
    val header = ListIcon()
    ReactTestUtils.withNewBodyElement { mountNode =>
      header.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(html, """<i aria-hidden="true" class="icon"></i>""")
    }
  }
  test("renderIcon") {
    val header = ListIcon("home")
    ReactTestUtils.withNewBodyElement { mountNode =>
      header.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(html, """<i aria-hidden="true" class="home icon"></i>""")
    }
  }
}
