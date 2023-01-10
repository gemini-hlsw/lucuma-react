// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.elements.button

import japgolly.scalajs.react.test._
import react.common.syntax.vdom._

class ButtonOrSuite extends munit.FunSuite {
  test("render") {
    val buttonOr = ButtonOr()
    ReactTestUtils.withNewBodyElement { mountNode =>
      buttonOr.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="or"></div>""")
    }
  }
}
