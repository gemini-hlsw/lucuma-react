package react.semanticui.collections.form

import japgolly.scalajs.react.test._
import react.semanticui.widths._
import react.common.syntax.vdom._

class FormGroupSuite extends munit.FunSuite {
  test("render") {
    val form = FormGroup()
    ReactTestUtils.withNewBodyElement { mountNode =>
      form.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="fields"></div>""")
    }
  }
  test("width") {
    val form = FormGroup(widths = Two)
    ReactTestUtils.withNewBodyElement { mountNode =>
      form.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="two fields"></div>""")
    }
  }
}
