// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.views.card

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.GenericFnComponentPACOps
import react.common.syntax.vdom._

class CardMetaSuite extends munit.FunSuite {
  test("render") {
    val cardMeta = new CardMeta().apply("Abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      cardMeta.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="meta">Abc</div>""")
    }
  }
  test("renderMany") {
    val cardMeta = CardMeta("Abc", <.div("abc"))
    ReactTestUtils.withNewBodyElement { mountNode =>
      cardMeta.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="meta">Abc<div>abc</div></div>""")
    }
  }
}
