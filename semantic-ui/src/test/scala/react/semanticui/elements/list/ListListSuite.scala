// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.elements.list

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class ListListSuite extends munit.FunSuite {
  test("render") {
    val header = ListList()
    ReactTestUtils.withNewBodyElement { mountNode =>
      header.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="list"></div>""")
    }
  }
  test("renderAs") {
    val header = new ListList(as = <.a)
    ReactTestUtils.withNewBodyElement { mountNode =>
      header.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<a class="list"></a>""")
    }
  }
}
