package react.semanticui.elements.label

import munit._
import japgolly.scalajs.react.test._
import react.common.syntax.vdom._

class LabelGroupSuite extends FunSuite {
  test("render") {
    val label = LabelGroup()
    ReactTestUtils.withNewBodyElement { mountNode =>
      label.renderIntoDOM(mountNode)
      assert(mountNode.innerHTML == """<div class="ui labels"></div>""")
    }
  }
  test("labels") {
    val label = LabelGroup(Label(content = "a"), Label(content = "b"))
    ReactTestUtils.withNewBodyElement { mountNode =>
      label.renderIntoDOM(mountNode)
      assert(
        mountNode.innerHTML == """<div class="ui labels"><div class="ui label">a</div><div class="ui label">b</div></div>"""
      )
    }
  }
}
