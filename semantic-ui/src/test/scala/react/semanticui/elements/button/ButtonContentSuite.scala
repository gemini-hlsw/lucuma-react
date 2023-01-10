// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.elements.button

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.GenericComponentPACOps
import react.common.syntax.vdom._

class ButtonContentSuite extends munit.FunSuite {
  test("render") {
    val buttonContent = new ButtonContent().apply("abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      buttonContent.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="content">abc</div>""")
    }
  }
  test("contentString") {
    val buttonContent = ButtonContent(content = "abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      buttonContent.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="content">abc</div>""")
    }
  }
  test("contentNode") {
    val buttonContent = ButtonContent(content = <.span("abc"))
    ReactTestUtils.withNewBodyElement { mountNode =>
      buttonContent.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="content"><span>abc</span></div>""")
    }
  }
}
