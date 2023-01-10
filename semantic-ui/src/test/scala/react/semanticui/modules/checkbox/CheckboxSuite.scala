// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.modules.checkbox

import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.test._
import react.common.syntax.vdom._

class CheckboxSuite extends munit.FunSuite {
  test("render") {
    val check = Checkbox()
    ReactTestUtils.withNewBodyElement { mountNode =>
      check.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="ui fitted checkbox"><input class="hidden" readonly="" tabindex="0" type="checkbox" value=""><label></label></div>"""
      )
    }
  }
  test("boolean callback") {
    val cb    = (b: Boolean) => Callback.log(b)
    val check = Checkbox(onChange = cb)
    ReactTestUtils.withNewBodyElement { mountNode =>
      check.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="ui fitted checkbox"><input class="hidden" readonly="" tabindex="0" type="checkbox" value=""><label></label></div>"""
      )
    }
  }
}
