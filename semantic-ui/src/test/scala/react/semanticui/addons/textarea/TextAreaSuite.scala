// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.addons.textarea

import japgolly.scalajs.react.test._
import react.common.syntax.render._

class TextAreaSuite extends munit.FunSuite {
  test("render") {
    val check = TextArea()
    ReactTestUtils.withNewBodyElement { mountNode =>
      check.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<textarea rows="3"></textarea>"""
      )
    }
  }
  test("value") {
    val check = TextArea(rows = 10, value = "Some text")
    ReactTestUtils.withNewBodyElement { mountNode =>
      check.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<textarea rows="10">Some text</textarea>"""
      )
    }
  }
}
