// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.common

import japgolly.scalajs.react.*
import japgolly.scalajs.react.internal.Box
import japgolly.scalajs.react.internal.FacadeExports
import japgolly.scalajs.react.test.*
import japgolly.scalajs.react.vdom.html_<^.*

import scala.language.implicitConversions

class ReactPropsForwardRefSuite extends munit.FunSuite {

  val forwardee = ScalaComponent
    .builder[Unit]("Forwardee")
    .render(_ => <.div)
    .build

  case class Props() extends ReactPropsForwardRef(fwdRefPropsComponent)

  val fwdRefPropsComponent =
    React.forwardRef.toScalaComponent(forwardee)[Props]((_, ref) =>
      forwardee.withOptionalRef(ref)()
    )

  test("props") {
    val p        = Props()
    val u        = p.toUnmounted
    val key      = u.key
    val ref      = u.ref
    val props    = u.props
    val children = u.propsChildren
    assertEquals(key, None)
    assertEquals(ref, None)
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
    val ref      = u.ref
    val props    = u.props
    val children = u.propsChildren
    assertEquals(key, Some("key": FacadeExports.Key))
    assertEquals(ref, None)
    assertEquals(props, Props())
    assertEquals(children.toList, List.empty)
    ReactTestUtils.withNewBodyElement { mountNode =>
      p.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(html, """<div></div>""")
    }
  }

  test("props.withRef") {
    val p        = Props()
    val r        = Ref.toScalaComponent(forwardee)
    val pr       = p.withRef(r)
    val u        = pr.toUnmounted
    val key      = u.key
    val ref      = u.ref
    val props    = u.props
    val children = u.propsChildren
    assertEquals(key, None)
    assertEquals(ref.map(_.raw), Some(r.raw))
    assertEquals(props, Props())
    assertEquals(children.toList, List.empty)
    ReactTestUtils.withNewBodyElement { mountNode =>
      pr.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(html, """<div></div>""")
    }
  }

  test("props.withRef.withKey") {
    val p        = Props()
    val r        = Ref.toScalaComponent(forwardee)
    val pr       = p.withRef(r).withKey("key")
    val u        = pr.toUnmounted
    val key      = u.key
    val ref      = u.ref
    val props    = u.props
    val children = u.propsChildren
    assertEquals(key, Some("key": FacadeExports.Key))
    assertEquals(ref.map(_.raw), Some(r.raw))
    assertEquals(props, Props())
    assertEquals(children.toList, List.empty)
    ReactTestUtils.withNewBodyElement { mountNode =>
      pr.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(html, """<div></div>""")
    }
  }
}
