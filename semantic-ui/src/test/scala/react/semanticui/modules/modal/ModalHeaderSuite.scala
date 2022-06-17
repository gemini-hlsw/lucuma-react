package react.semanticui.modules.modal

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class ModalHeaderSuite extends munit.FunSuite {
  test("render") {
    val header = ModalHeader("Abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      header.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="header">Abc</div>""")
    }
  }
}
