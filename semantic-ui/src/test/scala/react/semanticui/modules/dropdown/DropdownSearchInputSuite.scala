// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.modules.dropdown

import japgolly.scalajs.react.test._
import react.common.syntax.vdom._

class DropdownSearchInputSuite extends munit.FunSuite {
  test("pusher") {
    val pusher = DropdownSearchInput()
    ReactTestUtils.withNewBodyElement { mountNode =>
      pusher.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<input aria-autocomplete="list" autocomplete="off" class="search" type="text" value="">"""
      )
    }
  }
}
