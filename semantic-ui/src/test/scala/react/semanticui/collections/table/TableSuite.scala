package react.semanticui.collections.table

import japgolly.scalajs.react.test._
import react.common.syntax.vdom._

class TableSuite extends munit.FunSuite {
  test("Table") {
    val header = TableHeader(TableRow(TableHeaderCell("header")))
    val body   = TableBody(TableRow(TableCell("A cell")))
    val footer = TableFooter(TableRow(TableCell("footer")))
    val table  = Table(header, body, footer)
    ReactTestUtils.withNewBodyElement { mountNode =>
      table.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<table class="ui table"><thead class=""><tr class=""><th class="">header</th></tr></thead><tbody class=""><tr class=""><td class="">A cell</td></tr></tbody><tfoot class=""><tr class=""><td class="">footer</td></tr></tfoot></table>"""
      )
    }
  }
  test("TableAttached.Attached") {
    val header = TableHeader(TableRow(TableHeaderCell("header")))
    val body   = TableBody(TableRow(TableCell("A cell")))
    val footer = TableFooter(TableRow(TableCell("footer")))
    val table  = Table(attached = TableAttached.Attached)(header, body, footer)
    ReactTestUtils.withNewBodyElement { mountNode =>
      table.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<table class="ui attached table"><thead class=""><tr class=""><th class="">header</th></tr></thead><tbody class=""><tr class=""><td class="">A cell</td></tr></tbody><tfoot class=""><tr class=""><td class="">footer</td></tr></tfoot></table>"""
      )
    }
  }
  test("TableAttached.NotAttached") {
    val table = Table(attached = TableAttached.NotAttached)
    ReactTestUtils.withNewBodyElement { mountNode =>
      table.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<table class="ui table"><tbody class=""></tbody></table>"""
      )
    }
  }
  test("TableAttached.Top") {
    val table = Table(attached = TableAttached.Top)
    ReactTestUtils.withNewBodyElement { mountNode =>
      table.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<table class="ui top attached table"><tbody class=""></tbody></table>"""
      )
    }
  }
  test("TableAttached.Bottom") {
    val table = Table(attached = TableAttached.Bottom)
    ReactTestUtils.withNewBodyElement { mountNode =>
      table.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<table class="ui bottom attached table"><tbody class=""></tbody></table>"""
      )
    }
  }
  test("TableBasic.Basic") {
    val table = Table(basic = TableBasic.Basic)
    ReactTestUtils.withNewBodyElement { mountNode =>
      table.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<table class="ui basic table"><tbody class=""></tbody></table>"""
      )
    }
  }
  test("TableBasic.VeryBasic") {
    val table = Table(basic = TableBasic.Very)
    ReactTestUtils.withNewBodyElement { mountNode =>
      table.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<table class="ui very basic table"><tbody class=""></tbody></table>"""
      )
    }
  }
  test("TableBasic.NotBasic") {
    val table = Table(basic = TableBasic.NotBasic)
    ReactTestUtils.withNewBodyElement { mountNode =>
      table.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<table class="ui table"><tbody class=""></tbody></table>"""
      )
    }
  }
  test("Table celled") {
    val table = Table(celled = true)
    ReactTestUtils.withNewBodyElement { mountNode =>
      table.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<table class="ui celled table"><tbody class=""></tbody></table>"""
      )
    }
  }
  test("Table not celled") {
    val table = Table(celled = false)
    ReactTestUtils.withNewBodyElement { mountNode =>
      table.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<table class="ui table"><tbody class=""></tbody></table>"""
      )
    }
  }
  test("Table collapsing") {
    val table = Table(collapsing = true)
    ReactTestUtils.withNewBodyElement { mountNode =>
      table.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<table class="ui collapsing table"><tbody class=""></tbody></table>"""
      )
    }
  }
  test("Table not collapsing") {
    val table = Table(collapsing = false)
    ReactTestUtils.withNewBodyElement { mountNode =>
      table.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<table class="ui table"><tbody class=""></tbody></table>"""
      )
    }
  }
}
