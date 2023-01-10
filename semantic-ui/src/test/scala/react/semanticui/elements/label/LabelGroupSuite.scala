// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.elements.label

import japgolly.scalajs.react.test._
import munit._
import react.common.syntax.vdom._

class LabelGroupSuite extends FunSuite {
  test("render") {
    val label = LabelGroup()
    ReactTestUtils.withNewBodyElement { mountNode =>
      label.renderIntoDOM(mountNode)
      assert(mountNode.innerHTML == """<div class="ui labels"></div>""")
    }
  }
  test("labels") {
    val label = LabelGroup(Label(content = "a"), Label(content = "b"))
    ReactTestUtils.withNewBodyElement { mountNode =>
      label.renderIntoDOM(mountNode)
      assert(
        mountNode.innerHTML == """<div class="ui labels"><div class="ui label">a</div><div class="ui label">b</div></div>"""
      )
    }
  }
}
