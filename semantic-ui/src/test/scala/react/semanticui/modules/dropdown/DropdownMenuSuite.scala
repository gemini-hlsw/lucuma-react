// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.modules.dropdown

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.GenericComponentPACOps
import react.common.syntax.vdom._

class DropdownMenuSuite extends munit.FunSuite {
  test("menu") {
    val menu = DropdownMenu()
    ReactTestUtils.withNewBodyElement { mountNode =>
      menu.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="menu transition"></div>"""
      )
    }
  }
  test("menuContent") {
    val menu = new DropdownMenu().apply("content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      menu.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="menu transition">content</div>"""
      )
    }
  }
}
