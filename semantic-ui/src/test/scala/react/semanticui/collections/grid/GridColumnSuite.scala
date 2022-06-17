package react.semanticui.collections.grid

import japgolly.scalajs.react.test._
import react.common.syntax.vdom._

class GridColumnSuite extends munit.FunSuite {
  test("render") {
    val gridColumn = GridColumn()
    ReactTestUtils.withNewBodyElement { mountNode =>
      gridColumn.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="column"></div>""")
    }
  }
}
