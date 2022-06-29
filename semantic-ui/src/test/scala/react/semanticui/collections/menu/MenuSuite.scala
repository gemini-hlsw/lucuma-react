// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.collections.menu

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.GenericComponentPACOps
import react.common.style.Css
import react.common.syntax.vdom._
import react.semanticui.tagOf2AsC

class MenuSuite extends munit.FunSuite {
  test("render") {
    val menu = Menu()
    ReactTestUtils.withNewBodyElement { m =>
      menu.renderIntoDOM(m)
      assertEquals(m.innerHTML, """<div class="ui menu"></div>""")
    }
  }
  test("renderAs") {
    val menu = new Menu(as = <.a)
    ReactTestUtils.withNewBodyElement { m =>
      menu.renderIntoDOM(m)
      assertEquals(m.innerHTML, """<a class="ui menu"></a>""")
    }
  }
  test("applyItems") {
    val menu = Menu(
      MenuHeader(),
      MenuItem(clazz = Css("my-class")).apply(<.div, <.span)
    )
    ReactTestUtils.withNewBodyElement { m =>
      menu.renderIntoDOM(m)
      val html = m.innerHTML
      assertEquals(
        html,
        """<div class="ui menu"><div class="header"></div><div class="item my-class"><div></div><span></span></div></div>"""
      )
    }
  }
}
