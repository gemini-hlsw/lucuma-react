package react.semanticui.modules.dimmer

import japgolly.scalajs.react.test._
import react.common.syntax.vdom._

class DimmerSuite extends munit.FunSuite {
  test("dimmer") {
    val dimmer = Dimmer()
    ReactTestUtils.withNewBodyElement { mountNode =>
      dimmer.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="ui dimmer"></div>"""
      )
    }
  }
}
