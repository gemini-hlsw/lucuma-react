package react.common

import japgolly.scalajs.react._
import japgolly.scalajs.react.component.Scala
import japgolly.scalajs.react.internal.Box
import japgolly.scalajs.react.internal.FacadeExports
import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._

class ReactPropsForwardRefSuite extends munit.FunSuite {

  val forwardee = ScalaComponent
    .builder[Unit]("Forwardee")
    .render(_ => <.div)
    .build

  type RefType = japgolly.scalajs.react.facade.React.Component[Box[Unit], Box[Unit]]
    with Scala.Vars[Unit, Unit, Unit]

  case class Props() extends ReactPropsForwardRef[Props, RefType](fwdRefPropsComponent)

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
