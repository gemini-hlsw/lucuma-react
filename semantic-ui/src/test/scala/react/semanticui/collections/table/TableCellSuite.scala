// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.collections.table

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.GenericComponentPACOps
import react.common.syntax.vdom._
import react.semanticui.elements.icon._
import react.semanticui.verticalalignment
import react.semanticui.widths

class TableCellSuite extends munit.FunSuite {
  test("default") {
    val cell = TableCell(content = "Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<td class="">Some Content</td>""")
    }
  }
  test("object apply") {
    val cell = TableCell("Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<td class="">Some Content</td>""")
    }
  }
  test("active") {
    val cell = TableCell(active = true, content = <.div("Some Content"))
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<td class="active"><div>Some Content</div></td>""")
    }
  }
  test("collapsing") {
    val cell = TableCell(collapsing = true, content = "Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<td class="collapsing">Some Content</td>""")
    }
  }
  test("disabled") {
    val cell = TableCell(disabled = true).apply("Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<td class="disabled">Some Content</td>""")
    }
  }
  test("error") {
    val cell = TableCell(error = true).apply("Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<td class="error">Some Content</td>""")
    }
  }
  test("icon") {
    val cell = TableCell(icon = Icon("edit"), content = "Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<td class=""><i aria-hidden="true" class="edit icon"></i>Some Content</td>"""
      )
    }
  }
  test("negative") {
    val cell = TableCell(negative = true).apply("Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<td class="negative">Some Content</td>""")
    }
  }
  test("positive") {
    val cell = TableCell(positive = true).apply("Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<td class="positive">Some Content</td>""")
    }
  }
  test("selectable") {
    val cell = TableCell(selectable = true).apply("Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<td class="selectable">Some Content</td>""")
    }
  }
  test("singleLine") {
    val cell = TableCell(singleLine = true).apply("Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<td class="single line">Some Content</td>""")
    }
  }
  test("textAlign Center") {
    val cell = TableCell(textAlign = TableTextAlign.Center).apply("Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<td class="center aligned">Some Content</td>""")
    }
  }
  test("textAlign Right") {
    val cell = TableCell(textAlign = TableTextAlign.Right).apply("Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<td class="right aligned">Some Content</td>""")
    }
  }
  test("textAlign Left") {
    val cell = TableCell(textAlign = TableTextAlign.Left).apply("Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<td class="left aligned">Some Content</td>""")
    }
  }
  test("verticalAlign") {
    val cell = TableCell(verticalAlign = verticalalignment.Top).apply("Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<td class="top aligned">Some Content</td>""")
    }
  }
  test("aligned both") {
    val cell =
      TableCell(textAlign = TableTextAlign.Center, verticalAlign = verticalalignment.Middle)(
        "Some Content"
      )
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<td class="center aligned middle aligned">Some Content</td>"""
      )
    }
  }
  test("warning") {
    val cell = TableCell(warning = true)("Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<td class="warning">Some Content</td>""")
    }
  }
  test("width") {
    val cell = TableCell(width = widths.One)("Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<td class="one wide">Some Content</td>""")
    }
  }
}
