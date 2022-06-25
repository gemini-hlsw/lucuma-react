// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.modules.dropdown

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class DropdownTextSuite extends munit.FunSuite {
  test("text") {
    val header = DropdownText()
    ReactTestUtils.withNewBodyElement { mountNode =>
      header.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div aria-atomic="true" aria-live="polite" role="alert" class="divider"></div>"""
      )
    }
  }
  test("textContent") {
    val header = DropdownText("content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      header.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div aria-atomic="true" aria-live="polite" role="alert" class="divider">content</div>"""
      )
    }
  }
}
