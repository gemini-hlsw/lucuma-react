package react.semanticui.modules.tab

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class TabPaneSuite extends munit.FunSuite {
  test("basic") {
    val basic = TabPane("Abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      basic.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="ui bottom attached segment active tab">Abc</div>"""
      )
    }
  }
  test("active") {
    val basic = TabPane(active = true)("Abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      basic.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="ui bottom attached segment active tab">Abc</div>"""
      )
    }
  }
}
