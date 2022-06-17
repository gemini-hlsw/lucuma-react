package react.semanticui.modules.sidebar

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.ScalaComponent
import react.semanticui.collections.menu._
import react.semanticui.elements.icon._
import react.semanticui.As
import react.common.syntax.vdom._

class SidebarSuite extends munit.FunSuite {
  val DoubleComp = ScalaComponent
    .builder[Int]("Doubler")
    .render_P(i => <.p(s"$i + $i = ${i << 1}"))
    .build

  test("pusher") {
    val pusher = SidebarPusher("Abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      pusher.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="pusher">Abc</div>""")
    }
  }
  test("pushable") {
    val pushable = SidebarPushable("Abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      pushable.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="pushable">Abc</div>""")
    }
  }
  test("pushableAs") {
    val pushable = SidebarPushable(as = As.Segment())("Abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      pushable.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="ui segment pushable">Abc</div>""")
    }
  }
  test("sidebar") {
    val sidebar = Sidebar(DoubleComp(1))
    ReactTestUtils.withNewBodyElement { mountNode =>
      sidebar.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="ui left sidebar"><p>1 + 1 = 2</p></div>"""
      )
    }
  }
  test("sidebarAs") {
    val sidebar = Sidebar(as = As.Menu(Menu(inverted = true)))("Abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      sidebar.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="ui inverted ui left sidebar menu">Abc</div>"""
      )
    }
  }
  test("example") {
    val sidebar =
      <.div(
        Menu(
          attached = MenuAttached.Top,
          compact = true,
          borderless = true,
          tabular = MenuTabular.Right
        )(
          MenuItem(as = "a")(
            Icon("bars"),
            "Formerly known as OT"
          )
        ),
        SidebarPushable(className = "maingrid")(
          Sidebar(
            as = As.Menu(
              Menu(
                inverted = true,
                vertical = true,
                icon = MenuIcon.Labeled
              )
            ),
            width = SidebarWidth.Thin,
            animation = SidebarAnimation.Push,
            direction = SidebarDirection.Left,
            visible = true
          )(
            MenuItem(as = "a")(Icon("home"), "P I"),
            MenuItem(as = "a")(Icon("home"), "P II")
          ),
          SidebarPusher(dimmed = true)(
            <.div("Main")
          )
        )
      )
    ReactTestUtils.withNewBodyElement { mountNode =>
      sidebar.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div><div class="ui borderless compact top attached right tabular menu"><a class="item"><i aria-hidden="true" class="bars icon"></i>Formerly known as OT</a></div><div class="pushable maingrid"><div class="ui inverted vertical labeled icon ui push left thin visible sidebar menu"><a class="item"><i aria-hidden="true" class="home icon"></i>P I</a><a class="item"><i aria-hidden="true" class="home icon"></i>P II</a></div><div class="pusher dimmed"><div>Main</div></div></div></div>"""
      )
    }
  }
}
