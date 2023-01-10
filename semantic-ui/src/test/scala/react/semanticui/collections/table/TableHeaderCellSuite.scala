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
    val cell = TableHeaderCell(disabled = true).apply("Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<th class="disabled">Some Content</th>""")
    }
  }
  test("error") {
    val cell = TableHeaderCell(error = true).apply("Some Content")
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
    val cell = TableHeaderCell(negative = true).apply("Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<th class="negative">Some Content</th>""")
    }
  }
  test("positive") {
    val cell = TableHeaderCell(positive = true).apply("Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<th class="positive">Some Content</th>""")
    }
  }
  test("selectable") {
    val cell = TableHeaderCell(selectable = true).apply("Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<th class="selectable">Some Content</th>""")
    }
  }
  test("singleLine") {
    val cell = TableHeaderCell(singleLine = true).apply("Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<th class="single line">Some Content</th>""")
    }
  }
  test("sorted ascending") {
    val cell = TableHeaderCell(sorted = TableSorted.Ascending).apply("Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<th class="ascending sorted">Some Content</th>""")
    }
  }
  test("sorted descending") {
    val cell = TableHeaderCell(sorted = TableSorted.Descending).apply("Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<th class="descending sorted">Some Content</th>""")
    }
  }
  test("textAlign Center") {
    val cell = TableHeaderCell(textAlign = TableTextAlign.Center).apply("Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<th class="center aligned">Some Content</th>""")
    }
  }
  test("textAlign Right") {
    val cell = TableHeaderCell(textAlign = TableTextAlign.Right).apply("Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<th class="right aligned">Some Content</th>""")
    }
  }
  test("textAlign Left") {
    val cell = TableHeaderCell(textAlign = TableTextAlign.Left).apply("Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<th class="left aligned">Some Content</th>""")
    }
  }
  test("verticalAlign") {
    val cell = TableHeaderCell(verticalAlign = verticalalignment.Top).apply("Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<th class="top aligned">Some Content</th>""")
    }
  }
  test("aligned both") {
    val cell =
      TableHeaderCell(textAlign = TableTextAlign.Center, verticalAlign = verticalalignment.Middle)
        .apply(
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
    val cell = TableHeaderCell(warning = true).apply("Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<th class="warning">Some Content</th>""")
    }
  }
  test("width") {
    val cell = TableHeaderCell(width = widths.One).apply("Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cell.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<th class="one wide">Some Content</th>""")
    }
  }
}
