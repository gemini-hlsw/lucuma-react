// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.elements.rail

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.semanticui.floats._
import react.common.syntax.vdom._
import react.common.GenericFnComponentPACOps

class RailSuite extends munit.FunSuite {
  test("render") {
    val rail = Rail(position = Left).apply("abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      rail.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="ui left rail">abc</div>""")
    }
  }
}
