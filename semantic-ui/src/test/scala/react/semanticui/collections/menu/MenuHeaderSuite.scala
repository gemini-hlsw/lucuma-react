// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.collections.menu

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class MenuHeaderSuite extends munit.FunSuite {
  test("render") {
    val menuHeader = MenuHeader()
    ReactTestUtils.withNewBodyElement { mountNode =>
      menuHeader.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="header"></div>""")
    }
  }
  test("renderAsTag") {
    val menuHeader = new MenuHeader(as = <.a)
    ReactTestUtils.withNewBodyElement { mountNode =>
      menuHeader.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<a class="header"></a>""")
    }
  }
  test("renderAsHTMLTag") {
    val menuHeader = new MenuHeader(as = <.div)
    ReactTestUtils.withNewBodyElement { mountNode =>
      menuHeader.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="header"></div>""")
    }
  }
}
