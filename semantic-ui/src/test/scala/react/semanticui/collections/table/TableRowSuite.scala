// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.collections.table

import japgolly.scalajs.react.test._
import react.common.GenericComponentPACOps
import react.common.syntax.vdom._
import react.semanticui.verticalalignment

class TableRowSuite extends munit.FunSuite {
  test("No cells") {
    val row = TableRow()
    ReactTestUtils.withNewBodyElement { mountNode =>
      row.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<tr class=""></tr>""")
    }
  }
  test("One cell") {
    val cell = TableCell(content = "Hi")
    val row  = TableRow(cell)
    ReactTestUtils.withNewBodyElement { mountNode =>
      row.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<tr class=""><td class="">Hi</td></tr>""")
    }
  }
  test("Two cells") {
    val cell1 = TableCell(content = "High")
    val cell2 = TableCell(content = "Low")
    val row   = TableRow(cell1, cell2)
    ReactTestUtils.withNewBodyElement { mountNode =>
      row.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<tr class=""><td class="">High</td><td class="">Low</td></tr>"""
      )
    }
  }
  test("Two header cells") {
    val cell1 = TableHeaderCell(content = "High")
    val cell2 = TableHeaderCell("Low")
    val row   = TableRow(cell1, cell2)
    ReactTestUtils.withNewBodyElement { mountNode =>
      row.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<tr class=""><th class="">High</th><th class="">Low</th></tr>"""
      )
    }
  }
  test("cells property TableCell") {
    val cell1 = TableCell(content = "High")
    val cell2 = TableCell(content = "Low")
    val row   = TableRow(cells = Seq(cell1, cell2))
    ReactTestUtils.withNewBodyElement { mountNode =>
      row.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<tr class=""><td class="">High</td><td class="">Low</td></tr>"""
      )
    }
  }
  test("cells property TableHeaderCell") {
    val cell1 = TableHeaderCell(content = "High")
    val cell2 = TableHeaderCell(content = "Low")
    val row   = TableRow(cells = Seq(cell1, cell2))
    ReactTestUtils.withNewBodyElement { mountNode =>
      row.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<tr class=""><th class="">High</th><th class="">Low</th></tr>"""
      )
    }
  }
  test("object apply") {
    val cell1 = TableCell(content = "High")
    val cell2 = TableCell(content = "Low")
    val row   = TableRow(cell1, cell2)
    ReactTestUtils.withNewBodyElement { mountNode =>
      row.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<tr class=""><td class="">High</td><td class="">Low</td></tr>"""
      )
    }
  }
  test("active") {
    val cell1 = TableCell(content = "High")
    val cell2 = TableCell(content = "Low")
    val row   = TableRow(active = true).apply(cell1, cell2)
    ReactTestUtils.withNewBodyElement { mountNode =>
      row.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<tr class="active"><td class="">High</td><td class="">Low</td></tr>"""
      )
    }
  }
  test("disabled") {
    val cell1 = TableCell(content = "High")
    val cell2 = TableCell(content = "Low")
    val row   = TableRow(disabled = true).apply(cell1, cell2)
    ReactTestUtils.withNewBodyElement { mountNode =>
      row.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<tr class="disabled"><td class="">High</td><td class="">Low</td></tr>"""
      )
    }
  }
  test("error") {
    val cell1 = TableCell(content = "High")
    val cell2 = TableCell(content = "Low")
    val row   = TableRow(error = true).apply(cell1, cell2)
    ReactTestUtils.withNewBodyElement { mountNode =>
      row.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<tr class="error"><td class="">High</td><td class="">Low</td></tr>"""
      )
    }
  }
  test("negative") {
    val cell1 = TableCell(content = "High")
    val cell2 = TableCell(content = "Low")
    val row   = TableRow(negative = true).apply(cell1, cell2)
    ReactTestUtils.withNewBodyElement { mountNode =>
      row.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<tr class="negative"><td class="">High</td><td class="">Low</td></tr>"""
      )
    }
  }
  test("positive") {
    val cell1 = TableCell(content = "High")
    val cell2 = TableCell(content = "Low")
    val row   = TableRow(positive = true).apply(cell1, cell2)
    ReactTestUtils.withNewBodyElement { mountNode =>
      row.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<tr class="positive"><td class="">High</td><td class="">Low</td></tr>"""
      )
    }
  }
  test("textAlign") {
    val cell1 = TableCell(content = "High")
    val cell2 = TableCell(content = "Low")
    val row   = TableRow(textAlign = TableTextAlign.Right).apply(cell1, cell2)
    ReactTestUtils.withNewBodyElement { mountNode =>
      row.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<tr class="right aligned"><td class="">High</td><td class="">Low</td></tr>"""
      )
    }
  }
  test("verticalAlign") {
    val cell1 = TableCell(content = "High")
    val cell2 = TableCell(content = "Low")
    val row   = TableRow(verticalAlign = verticalalignment.Bottom).apply(cell1, cell2)
    ReactTestUtils.withNewBodyElement { mountNode =>
      row.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<tr class="bottom aligned"><td class="">High</td><td class="">Low</td></tr>"""
      )
    }
  }
  test("warning") {
    val cell1 = TableCell(content = "High")
    val cell2 = TableCell(content = "Low")
    val row   = TableRow(warning = true).apply(cell1, cell2)
    ReactTestUtils.withNewBodyElement { mountNode =>
      row.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<tr class="warning"><td class="">High</td><td class="">Low</td></tr>"""
      )
    }
  }
}
