package react.semanticui.elements.container

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class ContainerSuite extends munit.FunSuite {
  test("render") {
    val container = Container()
    ReactTestUtils.withNewBodyElement { mountNode =>
      container.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="ui container"></div>""")
    }
  }
  test("renderWithChildren") {
    val container = Container("Abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      container.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="ui container">Abc</div>""")
    }
  }
  test("renderWithPropsAndChildren") {
    val container = Container(fluid = true)("Abc", <.div("test"))
    ReactTestUtils.withNewBodyElement { mountNode =>
      container.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML,
                   """<div class="ui fluid container">Abc<div>test</div></div>"""
      )
    }
  }
  test("renderAs") {
    val container = Container(as = <.a, fluid = true)("Abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      container.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="ui fluid container">Abc</div>""")
    }
  }
}
