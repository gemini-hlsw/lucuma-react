// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.collections.table

import japgolly.scalajs.react.test._
import react.common.syntax.vdom._
import react.common.GenericComponentPACOps

class TableHeaderSuite extends munit.FunSuite {
  test("TableHeader") {
    val row1 = TableRow(TableHeaderCell("one"))
    val row2 = TableRow(TableHeaderCell("two"))
    val body = TableHeader(row1, row2)
    ReactTestUtils.withNewBodyElement { mountNode =>
      body.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<thead class=""><tr class=""><th class="">one</th></tr><tr class=""><th class="">two</th></tr></thead>"""
      )
    }
  }
  test("fullWidth") {
    val row1 = TableRow(TableHeaderCell("one"))
    val row2 = TableRow(TableHeaderCell("two"))
    val body = TableHeader(fullWidth = true).apply(row1, row2)
    ReactTestUtils.withNewBodyElement { mountNode =>
      body.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<thead class="full-width"><tr class=""><th class="">one</th></tr><tr class=""><th class="">two</th></tr></thead>"""
      )
    }
  }
}
