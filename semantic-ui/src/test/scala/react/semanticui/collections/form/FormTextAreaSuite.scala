// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.collections.form

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._
import react.semanticui.widths._

class FormTextAreaSuite extends munit.FunSuite {
  test("render") {
    val form = FormTextArea()
    ReactTestUtils.withNewBodyElement { mountNode =>
      form.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="field"><textarea rows="3"></textarea></div>"""
      )
    }
  }
  test("width") {
    val form = FormTextArea(width = Two)
    ReactTestUtils.withNewBodyElement { mountNode =>
      form.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="two wide field"><textarea rows="3"></textarea></div>"""
      )
    }
  }
  test("value") {
    val form = FormTextArea(width = Two, value = "test")
    ReactTestUtils.withNewBodyElement { mountNode =>
      form.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="two wide field"><textarea rows="3">test</textarea></div>"""
      )
    }
  }
}
