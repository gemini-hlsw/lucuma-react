package react.semanticui.elements.placeholder

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class PlaceholderParagraphSuite extends munit.FunSuite {
  test("render") {
    val paragraph = PlaceholderParagraph()
    ReactTestUtils.withNewBodyElement { mountNode =>
      paragraph.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="paragraph"></div>""")
    }
  }
  test("renderChild") {
    val paragraph = PlaceholderParagraph(<.div("abc"))
    ReactTestUtils.withNewBodyElement { mountNode =>
      paragraph.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="paragraph"><div>abc</div></div>""")
    }
  }
}
