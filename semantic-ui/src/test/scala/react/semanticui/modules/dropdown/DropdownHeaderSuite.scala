// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.modules.dropdown

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.GenericComponentPACOps
import react.common.syntax.vdom._

class DropdownHeaderSuite extends munit.FunSuite {
  test("header") {
    val header = DropdownHeader()
    ReactTestUtils.withNewBodyElement { mountNode =>
      header.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="header"></div>"""
      )
    }
  }
  test("headerContent") {
    val header = new DropdownHeader().apply("content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      header.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="header">content</div>""")
    }
  }
}
