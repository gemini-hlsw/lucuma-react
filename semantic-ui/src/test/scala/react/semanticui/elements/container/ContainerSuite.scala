// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.elements.container

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.GenericFnComponentPACOps
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
    val container = new Container().apply("Abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      container.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="ui container">Abc</div>""")
    }
  }
  test("renderWithPropsAndChildren") {
    val container = Container(fluid = true).apply("Abc", <.div("test"))
    ReactTestUtils.withNewBodyElement { mountNode =>
      container.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML,
                   """<div class="ui fluid container">Abc<div>test</div></div>"""
      )
    }
  }
  test("renderAs") {
    val container = new Container(as = <.a, fluid = true).apply("Abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      container.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="ui fluid container">Abc</div>""")
    }
  }
}
