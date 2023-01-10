// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.modules.dropdown

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.GenericFnComponentPACOps
import react.common.syntax.vdom._

class DropdownDividerSuite extends munit.FunSuite {
  test("pusher") {
    val pusher = new DropdownDivider().apply("Abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      pusher.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="divider">Abc</div>"""
      )
    }
  }
}
