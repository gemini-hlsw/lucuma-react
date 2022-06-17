package react.semanticui.elements.image

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.semanticui.elements.icon._
import react.common.syntax.vdom._

class ImageSuite extends munit.FunSuite {
  test("render") {
    val image = Image()
    ReactTestUtils.withNewBodyElement { mountNode =>
      image.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<img class="ui image">""")
    }
  }
  test("renderInside") {
    val image = <.div(
      Image(src = "http://fig1.jpg", className = "draggable")
    )
    ReactTestUtils.withNewBodyElement { mountNode =>
      image.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div><img src="http://fig1.jpg" class="ui image draggable"></div>"""
      )
    }
  }
  test("renderAs") {
    val image = Image(as = "a", href = "abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      image.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<a class="ui image" href="abc"><img></a>""")
    }
  }
  test("renderChild") {
    val image = Image(as = <.a, circular = true)(Icon(name = "help"))
    ReactTestUtils.withNewBodyElement { mountNode =>
      image.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<a class="ui circular image"><i aria-hidden="true" class="help icon"></i></a>"""
      )
    }
  }
}
