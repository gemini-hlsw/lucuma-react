package react.semanticui.collections.form

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.semanticui.widths._
import react.common.syntax.vdom._

class FormCheckboxSuite extends munit.FunSuite {
  test("render") {
    val form = FormCheckbox()
    ReactTestUtils.withNewBodyElement { mountNode =>
      form.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="field"><div class="ui fitted checkbox"><input class="hidden" readonly="" tabindex="0" type="checkbox" value=""><label></label></div></div>"""
      )
    }
  }
  test("width") {
    val form = FormCheckbox(width = Two)
    ReactTestUtils.withNewBodyElement { mountNode =>
      form.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="two wide field"><div class="ui fitted checkbox"><input class="hidden" readonly="" tabindex="0" type="checkbox" value=""><label></label></div></div>"""
      )
    }
  }
  test("value") {
    val form = FormCheckbox(width = Two, value = 1, slider = true)
    ReactTestUtils.withNewBodyElement { mountNode =>
      form.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="two wide field"><div class="ui fitted slider checkbox"><input class="hidden" readonly="" tabindex="0" type="checkbox" value="1"><label></label></div></div>"""
      )
    }
  }
}
