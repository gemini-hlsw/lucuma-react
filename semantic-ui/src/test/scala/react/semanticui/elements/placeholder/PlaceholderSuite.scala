package react.semanticui.elements.placeholder

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class PlaceholderSuite extends munit.FunSuite {
  test("render") {
    val loader = Placeholder()
    ReactTestUtils.withNewBodyElement { mountNode =>
      loader.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="ui placeholder"></div>""")
    }
  }
  test("renderChild") {
    val loader = Placeholder(<.div("abc"))
    ReactTestUtils.withNewBodyElement { mountNode =>
      loader.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="ui placeholder"><div>abc</div></div>""")
    }
  }
  test("renderAs") {
    val loader = Placeholder(as = <.a, inverted = true)
    ReactTestUtils.withNewBodyElement { mountNode =>
      loader.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<a class="ui inverted placeholder"></a>""")
    }
  }
}
