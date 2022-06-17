package react.semanticui.elements.divider

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.semanticui.elements.image.Image
import react.common.syntax.vdom._

class DividerSuite extends munit.FunSuite {
  test("render") {
    val divider = Divider()
    ReactTestUtils.withNewBodyElement { mountNode =>
      divider.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="ui divider"></div>""")
    }
  }
  test("renderAs") {
    val divider = Divider(as = <.a)
    ReactTestUtils.withNewBodyElement { mountNode =>
      divider.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<a class="ui divider"></a>""")
    }
  }
  test("children") {
    val divider = Divider(vertical = true)(<.div("abc"), Image(href = "cde"))
    ReactTestUtils.withNewBodyElement { mountNode =>
      divider.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="ui vertical divider"><div>abc</div><a class="ui image" href="cde"><img></a></div>"""
      )
    }
  }
}
