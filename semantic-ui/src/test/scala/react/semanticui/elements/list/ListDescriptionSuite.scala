// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.elements.list

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._
import react.semanticui.tagOf2AsC

class ListDescriptionSuite extends munit.FunSuite {
  test("render") {
    val header = ListDescription()
    ReactTestUtils.withNewBodyElement { mountNode =>
      header.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="description"></div>""")
    }
  }
  test("renderAs") {
    val header = new ListDescription(as = <.a)
    ReactTestUtils.withNewBodyElement { mountNode =>
      header.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<a class="description"></a>""")
    }
  }
}
