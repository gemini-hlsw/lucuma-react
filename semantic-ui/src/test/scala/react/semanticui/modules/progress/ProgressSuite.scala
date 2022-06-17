package react.semanticui.modules.progress

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class ProgressSuite extends munit.FunSuite {
  test("progress") {
    val progress = Progress("Abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      progress.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="ui progress" data-percent="0"><div class="bar" style="width: 0%;"></div><div class="label">Abc</div></div>"""
      )
    }
  }
}
