// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.collections.form

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._
import react.semanticui.widths._

class FormInputSuite extends munit.FunSuite {
  test("render") {
    val form = FormInput()
    ReactTestUtils.withNewBodyElement { mountNode =>
      form.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="field"><div class="ui input"><input type="text"></div></div>"""
      )
    }
  }
  test("width") {
    val form = FormInput(width = Two)
    ReactTestUtils.withNewBodyElement { mountNode =>
      form.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="two wide field"><div class="ui input"><input type="text"></div></div>"""
      )
    }
  }
  test("value") {
    val form = FormInput(width = Two, inverted = true, focus = true, value = "test")
    ReactTestUtils.withNewBodyElement { mountNode =>
      form.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="two wide field"><div class="ui focus inverted input"><input type="text" value="test"></div></div>"""
      )
    }
  }
}
