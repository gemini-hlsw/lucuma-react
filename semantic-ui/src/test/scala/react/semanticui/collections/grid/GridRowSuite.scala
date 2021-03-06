// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.collections.grid

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._
import react.semanticui.widths._

class GridRowSuite extends munit.FunSuite {
  test("render") {
    val gridRow = GridRow()
    ReactTestUtils.withNewBodyElement { mountNode =>
      gridRow.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="row"></div>""")
    }
  }
  test("renderChild") {
    val gridRow = GridRow(<.div("abc"))
    ReactTestUtils.withNewBodyElement { mountNode =>
      gridRow.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="row"><div>abc</div></div>""")
    }
  }
  test("columns") {
    val gridRow = GridRow(columns = Three)
    ReactTestUtils.withNewBodyElement { mountNode =>
      gridRow.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="three column row"></div>""")
    }
  }
  test("columns equal") {
    val gridRow = GridRow(columns = GridColumns.Equal)
    ReactTestUtils.withNewBodyElement { mountNode =>
      gridRow.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="equal width row"></div>""")
    }
  }
}
