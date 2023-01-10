// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.views.card

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.GenericComponentPACOps
import react.common.syntax.vdom._

class CardSuite extends munit.FunSuite {
  test("render") {
    val card = new Card().apply("Abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      card.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assert(html == """<div class="ui card">Abc</div>""")
    }
  }
}
