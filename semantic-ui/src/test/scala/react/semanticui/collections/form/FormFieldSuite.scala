// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.collections.form

import japgolly.scalajs.react.test._
import react.common.syntax.vdom._
import react.semanticui.widths._

class FormFieldSuite extends munit.FunSuite {
  test("render") {
    val form = FormField()
    ReactTestUtils.withNewBodyElement { mountNode =>
      form.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="field"></div>""")
    }
  }
  test("width") {
    val form = FormField(width = Two)
    ReactTestUtils.withNewBodyElement { mountNode =>
      form.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="two wide field"></div>""")
    }
  }
}
