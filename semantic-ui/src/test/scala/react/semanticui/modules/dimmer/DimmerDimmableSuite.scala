// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.modules.dimmer

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.GenericFnComponentPACOps
import react.common.syntax.vdom._

class DimmerDimmableSuite extends munit.FunSuite {
  test("dimmer") {
    val dimmer = new DimmerDimmable().apply("Abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      dimmer.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="dimmable">Abc</div>""")
    }
  }
}
