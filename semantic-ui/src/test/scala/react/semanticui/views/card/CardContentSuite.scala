// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.views.card

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class CardContentSuite extends munit.FunSuite {
  test("render") {
    val cc = CardContent("Abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cc.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(html, """<div class="content">Abc</div>""")
    }
  }
}
