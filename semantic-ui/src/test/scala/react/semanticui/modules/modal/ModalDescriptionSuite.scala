package react.semanticui.modules.modal

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class ModalDescriptionSuite extends munit.FunSuite {
  test("render") {
    val modal = ModalDescription("Abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      modal.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="description">Abc</div>""")
    }
  }
}
