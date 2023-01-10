// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.views.item

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.GenericFnComponentPACOps
import react.common.syntax.vdom._

class ItemSuite extends munit.FunSuite {
  test("render") {
    val item = new Item().apply("Abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      item.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="item">Abc</div>""")
    }
  }
}
