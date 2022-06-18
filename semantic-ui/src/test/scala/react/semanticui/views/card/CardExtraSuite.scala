// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.views.card

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class CardExtraSuite extends munit.FunSuite {
  test("render") {
    val ce = CardExtra("Abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      ce.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(html, """<div class="extra content">Abc</div>""")
    }
  }
}
