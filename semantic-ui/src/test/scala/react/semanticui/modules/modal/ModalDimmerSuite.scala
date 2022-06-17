package react.semanticui.modules.modal

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class ModalDimmerSuite extends munit.FunSuite {
  test("render") {
    val modal = ModalDimmer("Abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      modal.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="ui top aligned page modals dimmer transition visible active">Abc</div>"""
      )
    }
  }
}
