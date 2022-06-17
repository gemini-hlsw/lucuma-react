package react.semanticui.collections.form

import japgolly.scalajs.react.test._
import react.semanticui.widths._
import react.common.syntax.vdom._

class FormFieldSuite extends munit.FunSuite {
  test("render") {
    val form = FormField()
    ReactTestUtils.withNewBodyElement { mountNode =>
      form.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="field"></div>""")
    }
  }
  test("width") {
    val form = FormField(width = Two)
    ReactTestUtils.withNewBodyElement { mountNode =>
      form.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="two wide field"></div>""")
    }
  }
}
