package react.semanticui.modules.tab

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.semanticui.collections.menu.Menu
import react.common.syntax.vdom._

class TabSuite extends munit.FunSuite {
  test("basic") {
    val tab = Tab()
    ReactTestUtils.withNewBodyElement { mountNode =>
      tab.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div><div class="ui attached tabular menu"></div></div>"""
      )
    }
  }
  test("panes") {
    val tab = Tab(menu = Menu(fluid = true, vertical = true),
                  panes = List(
                    Pane(menuItem = "1", render = <.div("11")),
                    Pane(menuItem = "2", render = <.div("21"))
                  )
    )
    ReactTestUtils.withNewBodyElement { mountNode =>
      tab.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div><div class="ui grid"><div class="four wide column"><div class="ui fluid vertical menu"><a class="active item">1</a><a class="item">2</a></div></div><div class="stretched twelve wide column"><div>11</div></div></div></div>"""
      )
    }
  }
}
