package react.semanticui.modules.popup

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class PopupHeaderSuite extends munit.FunSuite {
  test("render") {
    val popup = PopupHeader("Abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      popup.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="header">Abc</div>""")
    }
  }
}
