package react.semanticui.collections.table

import japgolly.scalajs.react.test._
import react.common.syntax.vdom._

class TableGeneratorSuite extends munit.FunSuite {
  test("TableGenerator headerRow") {
    def makeRow(a: String, i: Int) = TableRow(TableCell(i.toString), TableCell(a))
    val headerRow                  = TableRow(TableHeaderCell("header 1"), TableHeaderCell("header 2"))
    val footerRow                  = TableRow(TableCell("footer 1"), TableCell("footer 2"))
    val table                      =
      TableGenerator[String](tableData = Seq("a", "b"),
                             renderBodyRow = makeRow _,
                             headerRow = headerRow,
                             footerRow = footerRow
      )
    ReactTestUtils.withNewBodyElement { mountNode =>
      table.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<table class="ui table"><thead class=""><tr class=""><th class="">header 1</th><th class="">header 2</th></tr></thead><tbody class=""><tr class=""><td class="">0</td><td class="">a</td></tr><tr class=""><td class="">1</td><td class="">b</td></tr></tbody><tfoot class=""><tr class=""><td class="">footer 1</td><td class="">footer 2</td></tr></tfoot></table>"""
      )
    }
  }
  test("TableGenerator headerRows") {
    def makeRow(a: String, i: Int) = TableRow(TableCell(i.toString), TableCell(a))
    val headerRows                 = Seq(TableRow(TableHeaderCell("Row 1")), TableRow(TableHeaderCell("Row 2")))
    val table                      =
      TableGenerator[String](tableData = Seq("a", "b"),
                             renderBodyRow = makeRow _,
                             headerRows = headerRows
      )
    ReactTestUtils.withNewBodyElement { mountNode =>
      table.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<table class="ui table"><thead class=""><tr class=""><th class="">Row 1</th></tr><tr class=""><th class="">Row 2</th></tr></thead><tbody class=""><tr class=""><td class="">0</td><td class="">a</td></tr><tr class=""><td class="">1</td><td class="">b</td></tr></tbody></table>"""
      )
    }
  }
}
