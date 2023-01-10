// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.views.item

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.GenericFnComponentPACOps
import react.common.syntax.vdom._

class ItemGroupSuite extends munit.FunSuite {
  test("render") {
    val itemGroup = new ItemGroup().apply("Abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      itemGroup.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="ui items">Abc</div>""")
    }
  }
}
