package react.semanticui.collections.grid

import japgolly.scalajs.react.test._
import react.common.syntax.vdom._

class GridSuite extends munit.FunSuite {
  test("render") {
    val grid = Grid()
    ReactTestUtils.withNewBodyElement { mountNode =>
      grid.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="ui grid"></div>""")
    }
  }
}
