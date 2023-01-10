// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.collections.menu

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.GenericComponentPACOps
import react.common.style.Css
import react.common.syntax.vdom._
import react.semanticui.elements.icon._

class MenuItemSuite extends munit.FunSuite {
  test("render") {
    val menuItem = MenuItem()
    ReactTestUtils.withNewBodyElement { m =>
      menuItem.renderIntoDOM(m)
      assertEquals(m.innerHTML, """<div class="item"></div>""")
    }
  }
  test("renderAs") {
    val menuItem = MenuItem(as = "a")
    ReactTestUtils.withNewBodyElement { m =>
      menuItem.renderIntoDOM(m)
      assertEquals(m.innerHTML, """<a class="item"></a>""")
    }
  }
  test("renderAsTag") {
    val menuItem = new MenuItem(as = <.a, icon = Icon(name = "comment"))
    ReactTestUtils.withNewBodyElement { m =>
      menuItem.renderIntoDOM(m)
      assertEquals(
        m.innerHTML,
        """<a class="icon item"><i aria-hidden="true" class="comment icon"></i></a>"""
      )
    }
  }
  test("apply") {
    val menuItem = new MenuItem(clazz = Css("my-class")).apply(<.div, <.span)
    ReactTestUtils.withNewBodyElement { m =>
      menuItem.renderIntoDOM(m)
      val html = m.innerHTML
      assertEquals(html, """<div class="item my-class"><div></div><span></span></div>""")
    }
  }
}
