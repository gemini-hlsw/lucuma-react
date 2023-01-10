// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

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
