package react.semanticui.elements.button

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class ButtonContentSuite extends munit.FunSuite {
  test("render") {
    val buttonContent = ButtonContent("abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      buttonContent.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="content">abc</div>""")
    }
  }
  test("contentString") {
    val buttonContent = ButtonContent(content = "abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      buttonContent.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="content">abc</div>""")
    }
  }
  test("contentNode") {
    val buttonContent = ButtonContent(content = <.span("abc"))
    ReactTestUtils.withNewBodyElement { mountNode =>
      buttonContent.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="content"><span>abc</span></div>""")
    }
  }
}
