// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.elements.divider

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.GenericComponentPACOps
import react.common.syntax.vdom._
import react.semanticui.elements.image.Image

class DividerSuite extends munit.FunSuite {
  test("render") {
    val divider = Divider()
    ReactTestUtils.withNewBodyElement { mountNode =>
      divider.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="ui divider"></div>""")
    }
  }
  test("renderAs") {
    val divider = new Divider(as = <.a)
    ReactTestUtils.withNewBodyElement { mountNode =>
      divider.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<a class="ui divider"></a>""")
    }
  }
  test("children") {
    val divider = Divider(vertical = true).apply(<.div("abc"), Image(href = "cde"))
    ReactTestUtils.withNewBodyElement { mountNode =>
      divider.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="ui vertical divider"><div>abc</div><a class="ui image" href="cde"><img></a></div>"""
      )
    }
  }
}
