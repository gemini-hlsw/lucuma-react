// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.common

import japgolly.scalajs.react._
import japgolly.scalajs.react.internal.FacadeExports
import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._

class ReactFnPropsSuite extends munit.FunSuite {

  case class Props() extends ReactFnProps(fnPropsComponent)

  val fnPropsComponent = ScalaFnComponent[Props](_ => <.div)

  test("props") {
    val p        = Props()
    val u        = p.toUnmounted
    val key      = u.key
    val props    = u.props
    val children = u.propsChildren
    assertEquals(key, None)
    assertEquals(props, Props())
    assertEquals(children.toList, List.empty)
    ReactTestUtils.withNewBodyElement { mountNode =>
      p.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(html, """<div></div>""")
    }
  }

  test("props.withKey") {
    val p        = Props().withKey("key")
    val u        = p.toUnmounted
    val key      = u.key
    val props    = u.props
    val children = u.propsChildren
    assertEquals(key, Some("key": FacadeExports.Key))
    assertEquals(props, Props())
    assertEquals(children.toList, List.empty)
    ReactTestUtils.withNewBodyElement { mountNode =>
      p.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(html, """<div></div>""")
    }
  }
}
