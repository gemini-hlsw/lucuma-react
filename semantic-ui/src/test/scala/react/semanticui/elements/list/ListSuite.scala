// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.elements.list

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.GenericComponentPACOps
import react.common.syntax.vdom._
import react.semanticui.tagOf2AsC

class ListSuite extends munit.FunSuite {
  test("render") {
    val item1 = ListItem(value = "a").apply(^.key := "a")("a")
    val item2 = ListItem(value = "b").apply(^.key := "b")("b")
    val list  = List(item1, item2)
    ReactTestUtils.withNewBodyElement { mountNode =>
      list.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div role="list" class="ui list"><div data-value="a" role="listitem" class="item">a</div><div data-value="b" role="listitem" class="item">b</div></div>"""
      )
    }
  }
  test("renderAs") {
    val item = new ListItem(as = <.a)
    ReactTestUtils.withNewBodyElement { mountNode =>
      item.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(html, """<a role="listitem" class="item"></a>""")
    }
  }
  test("icon") {
    val item = ListItem(icon = ListIcon("home"))
    ReactTestUtils.withNewBodyElement { mountNode =>
      item.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div role="listitem" class="item"><i aria-hidden="true" class="home icon"></i></div>"""
      )
    }
  }
  test("icon2") {
    val item = ListItem(icon = "reply")
    ReactTestUtils.withNewBodyElement { mountNode =>
      item.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(
        html,
        """<div role="listitem" class="item"><i aria-hidden="true" class="reply icon"></i></div>"""
      )
    }
  }
  test("header") {
    val item = ListItem(header = ListHeader("value"))
    ReactTestUtils.withNewBodyElement { mountNode =>
      item.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div role="listitem" class="item"><div class="header">value</div></div>"""
      )
    }
  }
}
