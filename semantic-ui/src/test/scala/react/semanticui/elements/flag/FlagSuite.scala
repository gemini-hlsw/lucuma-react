// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.elements.flag

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._
import react.semanticui.tagOf2AsC

class FlagSuite extends munit.FunSuite {
  test("render") {
    val menuItem = Flag(name = "cl")
    ReactTestUtils.withNewBodyElement { mountNode =>
      menuItem.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<i class="cl flag"></i>""")
    }
  }
  test("renderAs") {
    val menuItem = Flag(as = <.a, name = "cl")
    ReactTestUtils.withNewBodyElement { mountNode =>
      menuItem.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<a class="cl flag"></a>""")
    }
  }
}
