// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.collections.menu

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class MenuMenuSuite extends munit.FunSuite {
  test("render") {
    val menumenu = MenuMenu()
    ReactTestUtils.withNewBodyElement { mountNode =>
      menumenu.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="menu"></div>""")
    }
  }
  test("renderAs") {
    val menumenu = new MenuMenu(as = <.a)
    ReactTestUtils.withNewBodyElement { mountNode =>
      menumenu.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<a class="menu"></a>""")
    }
  }
}
