package react.common

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.Ref
import japgolly.scalajs.react.ScalaComponent
import utest._

object ScalaComponentTests extends TestSuite {

  case class Props() extends ReactProps[Props](propsComponent)

  case class PropsWithChildren()
      extends ReactPropsWithChildren[PropsWithChildren](propsWithChildrenComponent)

  val propsComponent =
    ScalaComponent.builder[Props].render(_ => <.div).build

  val propsWithChildrenComponent =
    ScalaComponent.builder[PropsWithChildren].render_C(c => <.div(c)).build

  val tests = Tests {
    test("props") {
      val p        = Props()
      val u        = p.toUnmounted
      val key      = u.key
      val ref      = u.ref
      val props    = u.props
      val children = u.propsChildren
      assert(key == None)
      assert(ref == None)
      assert(props == Props())
      assert(children.toList == List.empty)
      ReactTestUtils.withNewBodyElement { mountNode =>
        p.renderIntoDOM(mountNode)
        val html = mountNode.innerHTML
        assert(html == """<div></div>""")
      }
    }

    test("props.withKey") {
      val p        = Props().withKey("key")
      val u        = p.toUnmounted
      val key      = u.key
      val ref      = u.ref
      val props    = u.props
      val children = u.propsChildren
      assert(key == Some("key"))
      assert(ref == None)
      assert(props == Props())
      assert(children.toList == List.empty)
      ReactTestUtils.withNewBodyElement { mountNode =>
        p.renderIntoDOM(mountNode)
        val html = mountNode.innerHTML
        assert(html == """<div></div>""")
      }
    }

    test("props.withRef") {
      val p        = Props()
      val r        = Ref.toScalaComponent(p.component)
      val pr       = p.withRef(r)
      val u        = pr.toUnmounted
      val key      = u.key
      val ref      = u.ref
      val props    = u.props
      val children = u.propsChildren
      assert(key == None)
      assert(ref == Some(r.raw.asInstanceOf[japgolly.scalajs.react.raw.React.RefHandle[Any]]))
      assert(props == Props())
      assert(children.toList == List.empty)
      ReactTestUtils.withNewBodyElement { mountNode =>
        pr.renderIntoDOM(mountNode)
        val html = mountNode.innerHTML
        assert(html == """<div></div>""")
      }
    }

    test("props.withRef.withKey") {
      val p        = Props()
      val r        = Ref.toScalaComponent(p.component)
      val pr       = p.withRef(r).withKey("key")
      val u        = pr.toUnmounted
      val key      = u.key
      val ref      = u.ref
      val props    = u.props
      val children = u.propsChildren
      assert(key == Some("key"))
      assert(ref == Some(r.raw.asInstanceOf[japgolly.scalajs.react.raw.React.RefHandle[Any]]))
      assert(props == Props())
      assert(children.toList == List.empty)
      ReactTestUtils.withNewBodyElement { mountNode =>
        pr.renderIntoDOM(mountNode)
        val html = mountNode.innerHTML
        assert(html == """<div></div>""")
      }
    }

    test("propsWithChildren") {
      val p        = PropsWithChildren()
      val u        = p(<.div)
      val key      = u.key
      val ref      = u.ref
      val props    = u.props
      val children = u.propsChildren
      assert(key == None)
      assert(ref == None)
      assert(props == PropsWithChildren())
      assert(children.count == 1)
      ReactTestUtils.withNewBodyElement { mountNode =>
        p.renderIntoDOM(mountNode)
        val html = mountNode.innerHTML
        assert(html == """<div></div>""")
      }
      ReactTestUtils.withNewBodyElement { mountNode =>
        u.renderIntoDOM(mountNode)
        val html = mountNode.innerHTML
        assert(html == """<div><div></div></div>""")
      }
    }

    test("propsWithChildren.withKey") {
      val p        = PropsWithChildren().withKey("key")
      val u        = p(<.div)
      val key      = u.key
      val ref      = u.ref
      val props    = u.props
      val children = u.propsChildren
      assert(key == Some("key"))
      assert(ref == None)
      assert(props == PropsWithChildren())
      assert(children.count == 1)
      ReactTestUtils.withNewBodyElement { mountNode =>
        p.renderIntoDOM(mountNode)
        val html = mountNode.innerHTML
        assert(html == """<div></div>""")
      }
      ReactTestUtils.withNewBodyElement { mountNode =>
        u.renderIntoDOM(mountNode)
        val html = mountNode.innerHTML
        assert(html == """<div><div></div></div>""")
      }
    }

    test("propsWithChildren.withRef") {
      val p        = PropsWithChildren()
      val r        = Ref.toScalaComponent(p.component)
      val pr       = p.withRef(r)
      val u        = pr(<.div)
      val key      = u.key
      val ref      = u.ref
      val props    = u.props
      val children = u.propsChildren
      assert(key == None)
      assert(ref == Some(r.raw.asInstanceOf[japgolly.scalajs.react.raw.React.RefHandle[Any]]))
      assert(props == PropsWithChildren())
      assert(children.count == 1)
      ReactTestUtils.withNewBodyElement { mountNode =>
        pr.renderIntoDOM(mountNode)
        val html = mountNode.innerHTML
        assert(html == """<div></div>""")
      }
      ReactTestUtils.withNewBodyElement { mountNode =>
        u.renderIntoDOM(mountNode)
        val html = mountNode.innerHTML
        assert(html == """<div><div></div></div>""")
      }
    }

    test("propsWithChildren.withRef.withKey") {
      val p        = PropsWithChildren()
      val r        = Ref.toScalaComponent(p.component)
      val pr       = p.withRef(r).withKey("key")
      val u        = pr(<.div)
      val key      = u.key
      val ref      = u.ref
      val props    = u.props
      val children = u.propsChildren
      assert(key == Some("key"))
      assert(ref == Some(r.raw.asInstanceOf[japgolly.scalajs.react.raw.React.RefHandle[Any]]))
      assert(props == PropsWithChildren())
      assert(children.count == 1)
      ReactTestUtils.withNewBodyElement { mountNode =>
        pr.renderIntoDOM(mountNode)
        val html = mountNode.innerHTML
        assert(html == """<div></div>""")
      }
      ReactTestUtils.withNewBodyElement { mountNode =>
        u.renderIntoDOM(mountNode)
        val html = mountNode.innerHTML
        assert(html == """<div><div></div></div>""")
      }
    }
  }
}
