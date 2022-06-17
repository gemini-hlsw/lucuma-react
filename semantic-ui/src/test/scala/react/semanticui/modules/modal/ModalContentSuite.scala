package react.semanticui.modules.modal

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class ModalContentSuite extends munit.FunSuite {
  test("render") {
    val modal = ModalContent("Abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      modal.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="content">Abc</div>""")
    }
  }
}
