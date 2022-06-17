package react.semanticui.modules.popup

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class PopupContentSuite extends munit.FunSuite {
  test("render") {
    val popup = PopupContent("Abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      popup.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="content">Abc</div>""")
    }
  }
}
