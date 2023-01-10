// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.views.item

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.GenericFnComponentPACOps
import react.common.syntax.vdom._

class ItemDescriptionSuite extends munit.FunSuite {
  test("render") {
    val id = new ItemDescription().apply("Abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      id.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="description">Abc</div>""")
    }
  }
}
