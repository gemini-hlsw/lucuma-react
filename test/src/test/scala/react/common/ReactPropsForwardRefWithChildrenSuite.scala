// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.common

import japgolly.scalajs.react._
import japgolly.scalajs.react.component.Scala
import japgolly.scalajs.react.internal.Box
import japgolly.scalajs.react.internal.FacadeExports
import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._

import scala.language.implicitConversions

class ReactPropsForwardRefWithChildrenSuite extends munit.FunSuite {

  val forwardee = ScalaComponent
    .builder[Unit]("Forwardee")
    .render_C(c => <.div(c))
    .build

  case class PropsWithChildren()
      extends ReactPropsForwardRefWithChildren(fwdRefWithChildrenPropsComponent)

  val fwdRefWithChildrenPropsComponent =
    React.forwardRef
      .toScalaComponent(forwardee)
      .withChildren[PropsWithChildren]((_, c, ref) => forwardee.withOptionalRef(ref)(c))

  test("propsWithChildren") {
    val p        = PropsWithChildren()
    val u        = p(<.div)
    val key      = u.key
    val ref      = u.ref
    val props    = u.props
    val children = u.propsChildren
    assertEquals(key, None)
    assertEquals(ref, None)
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
    val ref      = u.ref
    val props    = u.props
    val children = u.propsChildren
    assertEquals(key, Some("key": FacadeExports.Key))
    assertEquals(ref, None)
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

  test("propsWithChildren.withRef") {
    val p        = PropsWithChildren()
    val r        = Ref.toScalaComponent(forwardee)
    val pr       = p.withRef(r)
    val u        = pr(<.div)
    val key      = u.key
    val ref      = u.ref
    val props    = u.props
    val children = u.propsChildren
    assertEquals(key, None)
    assertEquals(ref.map(_.raw), Some(r.raw))
    assertEquals(props, PropsWithChildren())
    assertEquals(children.count, 1)
    ReactTestUtils.withNewBodyElement { mountNode =>
      pr.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(html, """<div></div>""")
    }
    ReactTestUtils.withNewBodyElement { mountNode =>
      u.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(html, """<div><div></div></div>""")
    }
  }

  test("propsWithChildren.withRef.withKey") {
    val p        = PropsWithChildren()
    val r        = Ref.toScalaComponent(forwardee)
    val pr       = p.withRef(r).withKey("key")
    val u        = pr(<.div)
    val key      = u.key
    val ref      = u.ref
    val props    = u.props
    val children = u.propsChildren
    assertEquals(key, Some("key": FacadeExports.Key))
    assertEquals(ref.map(_.raw), Some(r.raw))
    assertEquals(props, PropsWithChildren())
    assertEquals(children.count, 1)
    ReactTestUtils.withNewBodyElement { mountNode =>
      pr.renderIntoDOM(mountNode)
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
