// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.common

import japgolly.scalajs.react.*
import japgolly.scalajs.react.facade.React.RefHandle
import japgolly.scalajs.react.internal.FacadeExports
import japgolly.scalajs.react.test.*
import japgolly.scalajs.react.vdom.html_<^.*

import scala.language.implicitConversions

class ReactPropsSuite extends munit.FunSuite:
  case class Props() extends ReactProps(propsComponent)

  val propsComponent = ScalaComponent.builder[Props].render(_ => <.div).build

  test("props"):
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
    ReactTestUtils.withRenderedSync(p.toUnmounted) { mountNode =>
      mountNode.outerHTML.assert("""<div></div>""")
    }

  test("props.withKey"):
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
    ReactTestUtils.withRenderedSync(p.toUnmounted) { mountNode =>
      mountNode.outerHTML.assert("""<div></div>""")
    }

  test("props.withRef"):
    val p        = Props()
    val r        = Ref.toScalaComponent(p.component)
    val pr       = p.withRef(r)
    val u        = pr.toUnmounted
    val key      = u.key
    val ref      = u.ref
    val props    = u.props
    val children = u.propsChildren
    assertEquals(key, None)
    assertEquals(ref.map(_.asInstanceOf[RefHandle[Any]]), Some(r.raw.asInstanceOf[RefHandle[Any]]))
    assertEquals(props, Props())
    assertEquals(children.toList, List.empty)
    ReactTestUtils.withRenderedSync(pr.toUnmounted) { mountNode =>
      mountNode.outerHTML.assert("""<div></div>""")
    }

  test("props.withRef.withKey"):
    val p        = Props()
    val r        = Ref.toScalaComponent(p.component)
    val pr       = p.withRef(r).withKey("key")
    val u        = pr.toUnmounted
    val key      = u.key
    val ref      = u.ref
    val props    = u.props
    val children = u.propsChildren
    assertEquals(key, Some("key": FacadeExports.Key))
    assertEquals(ref.map(_.asInstanceOf[RefHandle[Any]]), Some(r.raw.asInstanceOf[RefHandle[Any]]))
    assertEquals(props, Props())
    assertEquals(children.toList, List.empty)
    ReactTestUtils.withRenderedSync(pr.toUnmounted) { mountNode =>
      mountNode.outerHTML.assert("""<div></div>""")
    }
