package react.semanticui.elements.list

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class ListItemSuite extends munit.FunSuite {
  test("render") {
    val item = ListItem()
    ReactTestUtils.withNewBodyElement { mountNode =>
      item.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div role="listitem" class="item"></div>""")
    }
  }
  test("renderAs") {
    val item = ListItem(as = <.a)
    ReactTestUtils.withNewBodyElement { mountNode =>
      item.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<a role="listitem" class="item"></a>""")
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
    val item = ListItem(header = ListHeader(^.color.aqua, "value"))
    ReactTestUtils.withNewBodyElement { mountNode =>
      item.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(
        html,
        """<div role="listitem" class="item"><div style="color: aqua;" class="header">value</div></div>"""
      )
    }
  }
}
