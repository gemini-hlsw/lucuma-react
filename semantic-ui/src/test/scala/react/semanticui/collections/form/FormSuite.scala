// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.collections.form

import japgolly.scalajs.react.test._
import react.common.syntax.vdom._

class FormSuite extends munit.FunSuite {
  test("render") {
    val form = Form()
    ReactTestUtils.withNewBodyElement { mountNode =>
      form.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<form class="ui form"></form>""")
    }
  }
}
