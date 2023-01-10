// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.views.card

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.GenericFnComponentPACOps
import react.common.syntax.vdom._

class CardGroupSuite extends munit.FunSuite {
  test("render") {
    val cardGroup = new CardGroup().apply("Abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cardGroup.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(html, """<div class="ui cards">Abc</div>""")
    }
  }
}
