package react.semanticui.elements.button

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.semanticui.widths.widthOf
import react.common.syntax.vdom._

class ButtonGroupSuite extends munit.FunSuite {
  test("render") {
    val buttonGroup = ButtonGroup("abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      buttonGroup.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="ui buttons">abc</div>""")
    }
  }
  test("widths") {
    val buttonGroup =
      ButtonGroup(widths = widthOf(2))(Button("1"), Button("2"))
    ReactTestUtils.withNewBodyElement { mountNode =>
      buttonGroup.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="ui two buttons"><button class="ui button">1</button><button class="ui button">2</button></div>"""
      )
    }
  }
  test("buttons") {
    val buttonGroup =
      ButtonGroup()(Button("1"), Button("2"))
    ReactTestUtils.withNewBodyElement { mountNode =>
      buttonGroup.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="ui buttons"><button class="ui button">1</button><button class="ui button">2</button></div>"""
      )
    }
  }
}
