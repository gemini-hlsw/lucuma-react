package react.semanticui.collections.table

import japgolly.scalajs.react.test._
import react.common.syntax.vdom._

class TableBodySuite extends munit.FunSuite {
  test("TableBody") {
    val row1 = TableRow(TableCell("one"))
    val row2 = TableRow(TableCell("two"))
    val body = TableBody(row1, row2)
    ReactTestUtils.withNewBodyElement { mountNode =>
      body.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<tbody class=""><tr class=""><td class="">one</td></tr><tr class=""><td class="">two</td></tr></tbody>"""
      )
    }
  }
}
