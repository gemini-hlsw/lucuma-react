package react.semanticui.collections.form

import japgolly.scalajs.react.test._
import react.common.syntax.vdom._

class FormSuite extends munit.FunSuite {
  test("render") {
    val form = Form()
    ReactTestUtils.withNewBodyElement { mountNode =>
      form.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<form class="ui form"></form>""")
    }
  }
}
