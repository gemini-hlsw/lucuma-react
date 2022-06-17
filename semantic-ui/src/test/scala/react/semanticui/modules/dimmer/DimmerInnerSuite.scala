package react.semanticui.modules.dimmer

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class DimmerInnerSuite extends munit.FunSuite {
  test("render") {
    val dimmerInner = DimmerInner("Abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      dimmerInner.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="ui dimmer"><div class="content">Abc</div></div>"""
      )
    }
  }
}
