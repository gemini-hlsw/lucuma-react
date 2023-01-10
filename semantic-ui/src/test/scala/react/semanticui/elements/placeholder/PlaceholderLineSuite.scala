// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.elements.placeholder

import japgolly.scalajs.react.test._
import react.common.syntax.vdom._

class PlaceholderLineSuite extends munit.FunSuite {
  test("render") {
    val paragraph = PlaceholderLine()
    ReactTestUtils.withNewBodyElement { mountNode =>
      paragraph.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="line"></div>""")
    }
  }
}
