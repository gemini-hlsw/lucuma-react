// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.common

import japgolly.scalajs.react.*
import japgolly.scalajs.react.internal.FacadeExports
import japgolly.scalajs.react.test.*
import japgolly.scalajs.react.vdom.html_<^.*

class ReactFnPropsWithChildrenSuite extends munit.FunSuite {

  case class PropsWithChildren() extends ReactFnPropsWithChildren(fnPropsWithChildrenComponent)

  val fnPropsWithChildrenComponent =
    ScalaFnComponent.withChildren[PropsWithChildren]((_, c) => <.div(c))

  test("propsWithChildren") {
    val p        = PropsWithChildren()
    val u        = p(<.div)
    val key      = u.key
    val props    = u.props
    val children = u.propsChildren
    assertEquals(key, None)
    assertEquals(props, PropsWithChildren())
    assertEquals(children.count, 1)
    ReactTestUtils.withNewBodyElement { mountNode =>
      p.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(html, """<div></div>""")
    }
    ReactTestUtils.withNewBodyElement { mountNode =>
      u.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(html, """<div><div></div></div>""")
    }
  }

  test("propsWithChildren.withKey") {
    val p        = PropsWithChildren().withKey("key")
    val u        = p(<.div)
    val key      = u.key
    val props    = u.props
    val children = u.propsChildren
    assertEquals(key, Some("key": FacadeExports.Key))
    assertEquals(props, PropsWithChildren())
    assertEquals(children.count, 1)
    ReactTestUtils.withNewBodyElement { mountNode =>
      p.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(html, """<div></div>""")
    }
    ReactTestUtils.withNewBodyElement { mountNode =>
      u.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(html, """<div><div></div></div>""")
    }
  }
}
