package react.semanticui.collections.table

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.semanticui.elements.icon._
import react.semanticui.verticalalignment
import react.semanticui.widths
import react.common.syntax.vdom._

class TableHeaderCellSuite extends munit.FunSuite {
  test("default") {
    val cell = TableHeaderCell(content = "Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<th class="">Some Content</th>""")
    }
  }
  test("object apply") {
    val cell = TableHeaderCell("Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<th class="">Some Content</th>""")
    }
  }
  test("active") {
    val cell = TableHeaderCell(active = true, content = <.div("Some Content"))
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<th class="active"><div>Some Content</div></th>""")
    }
  }
  test("collapsing") {
    val cell = TableHeaderCell(collapsing = true, content = "Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<th class="collapsing">Some Content</th>""")
    }
  }
  test("disabled") {
    val cell = TableHeaderCell(disabled = true)("Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<th class="disabled">Some Content</th>""")
    }
  }
  test("error") {
    val cell = TableHeaderCell(error = true)("Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<th class="error">Some Content</th>""")
    }
  }
  test("icon") {
    val cell = TableHeaderCell(icon = Icon("edit"), content = "Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<th class=""><i aria-hidden="true" class="edit icon"></i>Some Content</th>"""
      )
    }
  }
  test("negative") {
    val cell = TableHeaderCell(negative = true)("Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<th class="negative">Some Content</th>""")
    }
  }
  test("positive") {
    val cell = TableHeaderCell(positive = true)("Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<th class="positive">Some Content</th>""")
    }
  }
  test("selectable") {
    val cell = TableHeaderCell(selectable = true)("Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<th class="selectable">Some Content</th>""")
    }
  }
  test("singleLine") {
    val cell = TableHeaderCell(singleLine = true)("Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<th class="single line">Some Content</th>""")
    }
  }
  test("sorted ascending") {
    val cell = TableHeaderCell(sorted = TableSorted.Ascending)("Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<th class="ascending sorted">Some Content</th>""")
    }
  }
  test("sorted descending") {
    val cell = TableHeaderCell(sorted = TableSorted.Descending)("Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<th class="descending sorted">Some Content</th>""")
    }
  }
  test("textAlign Center") {
    val cell = TableHeaderCell(textAlign = TableTextAlign.Center)("Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<th class="center aligned">Some Content</th>""")
    }
  }
  test("textAlign Right") {
    val cell = TableHeaderCell(textAlign = TableTextAlign.Right)("Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<th class="right aligned">Some Content</th>""")
    }
  }
  test("textAlign Left") {
    val cell = TableHeaderCell(textAlign = TableTextAlign.Left)("Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<th class="left aligned">Some Content</th>""")
    }
  }
  test("verticalAlign") {
    val cell = TableHeaderCell(verticalAlign = verticalalignment.Top)("Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<th class="top aligned">Some Content</th>""")
    }
  }
  test("aligned both") {
    val cell =
      TableHeaderCell(textAlign = TableTextAlign.Center, verticalAlign = verticalalignment.Middle)(
        "Some Content"
      )
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<th class="center aligned middle aligned">Some Content</th>"""
      )
    }
  }
  test("warning") {
    val cell = TableHeaderCell(warning = true)("Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<th class="warning">Some Content</th>""")
    }
  }
  test("width") {
    val cell = TableHeaderCell(width = widths.One)("Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<th class="one wide">Some Content</th>""")
    }
  }
}
