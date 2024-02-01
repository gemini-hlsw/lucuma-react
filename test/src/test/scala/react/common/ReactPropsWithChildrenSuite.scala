// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.common

import japgolly.scalajs.react.*
import japgolly.scalajs.react.facade.React.RefHandle
import japgolly.scalajs.react.internal.FacadeExports
import japgolly.scalajs.react.test.*
import japgolly.scalajs.react.vdom.html_<^.*

import scala.language.implicitConversions

class ReactPropsWithChildrenSuite extends munit.FunSuite:
  case class PropsWithChildren() extends ReactPropsWithChildren(propsWithChildrenComponent)

  val propsWithChildrenComponent =
    ScalaComponent.builder[PropsWithChildren].render_C(c => <.div(c)).build

  test("propsWithChildren"):
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
    ReactTestUtils2.withRendered(p.toUnmounted) { mountNode =>
      val html = mountNode.outerHTML()
      assertEquals(html, """<div></div>""")
    }
    ReactTestUtils2.withRendered(u) { mountNode =>
      val html = mountNode.outerHTML()
      assertEquals(html, """<div><div></div></div>""")
    }

  test("propsWithChildren.withKey"):
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
    ReactTestUtils2.withRendered(p.toUnmounted) { mountNode =>
      val html = mountNode.outerHTML()
      assertEquals(html, """<div></div>""")
    }
    ReactTestUtils2.withRendered(u) { mountNode =>
      val html = mountNode.outerHTML()
      assertEquals(html, """<div><div></div></div>""")
    }

  test("propsWithChildren.withRef"):
    val p        = PropsWithChildren()
    val r        = Ref.toScalaComponent(p.component)
    val pr       = p.withRef(r)
    val u        = pr(<.div)
    val key      = u.key
    val ref      = u.ref
    val props    = u.props
    val children = u.propsChildren
    assertEquals(key, None)
    assertEquals(ref.map(_.asInstanceOf[RefHandle[Any]]), Some(r.raw.asInstanceOf[RefHandle[Any]]))
    assertEquals(props, PropsWithChildren())
    assertEquals(children.count, 1)
    ReactTestUtils2.withRendered(pr.toUnmounted) { mountNode =>
      val html = mountNode.outerHTML()
      assertEquals(html, """<div></div>""")
    }
    ReactTestUtils2.withRendered(u) { mountNode =>
      val html = mountNode.outerHTML()
      assertEquals(html, """<div><div></div></div>""")
    }

  test("propsWithChildren.withRef.withKey"):
    val p        = PropsWithChildren()
    val r        = Ref.toScalaComponent(p.component)
    val pr       = p.withRef(r).withKey("key")
    val u        = pr(<.div)
    val key      = u.key
    val ref      = u.ref
    val props    = u.props
    val children = u.propsChildren
    assertEquals(key, Some("key": FacadeExports.Key))
    assertEquals(ref.map(_.asInstanceOf[RefHandle[Any]]), Some(r.raw.asInstanceOf[RefHandle[Any]]))
    assertEquals(props, PropsWithChildren())
    assertEquals(children.count, 1)
    ReactTestUtils2.withRendered(pr.toUnmounted) { root =>
      val html = root.outerHTML()
      assertEquals(html, """<div></div>""")
    }
    ReactTestUtils2.withRendered(u) { mountNode =>
      val html = mountNode.outerHTML()
      assertEquals(html, """<div><div></div></div>""")
    }
