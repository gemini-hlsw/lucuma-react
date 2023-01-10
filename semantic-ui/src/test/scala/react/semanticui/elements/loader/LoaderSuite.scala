// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.elements.loader

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.GenericComponentPACOps
import react.common.GenericFnComponentPACOps
import react.common.syntax.vdom._
import react.semanticui.elements.label.Label

class LoaderSuite extends munit.FunSuite {
  test("render") {
    val loader = Loader()
    ReactTestUtils.withNewBodyElement { mountNode =>
      loader.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="ui loader"></div>""")
    }
  }
  test("renderChild") {
    val loader = Loader(<.div("abc"))
    ReactTestUtils.withNewBodyElement { mountNode =>
      loader.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="ui text loader"><div>abc</div></div>""")
    }
  }
  test("renderChild2") {
    val loader = new Loader().apply(new Label().apply("abc"))
    ReactTestUtils.withNewBodyElement { mountNode =>
      loader.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="ui text loader"><div class="ui label">abc</div></div>"""
      )
    }
  }
  test("renderAs") {
    val loader = new Loader(as = <.a, inverted = true)
    ReactTestUtils.withNewBodyElement { mountNode =>
      loader.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<a class="ui inverted loader"></a>""")
    }
  }
}
