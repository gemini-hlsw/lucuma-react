package react.semanticui.elements.button

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.semanticui.elements.icon._
import react.semanticui.elements.label.Label
import react.semanticui.collections.form.Form
import react.semanticui.colors._
import react.semanticui.sizes._
import react.semanticui.floats._
import react.semanticui._
import react.common.syntax.vdom._

class ButtonSuite extends munit.FunSuite {
  test("render") {
    val button = Button()
    ReactTestUtils.withNewBodyElement { mountNode =>
      button.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(html, """<button class="ui button"></button>""")
    }
  }
  test("renderAs") {
    val button = Button(as = "a")
    ReactTestUtils.withNewBodyElement { mountNode =>
      button.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(html, """<a class="ui button" role="button"></a>""")
    }
  }
  test("renderAsTag") {
    val button = Button(as = <.a(^.href := "")(^.target := ""))
    ReactTestUtils.withNewBodyElement { mountNode =>
      button.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(html, """<a href="" target="" class="ui button" role="button"></a>""")
    }
  }
  test("renderAsForm") {
    val button = Button(as = As.Form(Form(error = true)))
    ReactTestUtils.withNewBodyElement { mountNode =>
      button.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(
        html,
        """<form role="button" class="ui error form ui button"></form>"""
      )
    }
  }
  test("renderAsFormWithPassthrough") {
    val button = Button(as = As.Form(Form(error = true)(^.method := "post")))
    ReactTestUtils.withNewBodyElement { mountNode =>
      button.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(
        html,
        """<form method="post" role="button" class="ui error form ui button"></form>"""
      )
    }
  }
  test("attached") {
    val button = Button(attached = ButtonAttached.Top)
    ReactTestUtils.withNewBodyElement { mountNode =>
      button.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(
        html,
        """<div class="ui top attached button" role="button" tabindex="0"></div>"""
      )
    }
  }
  test("color") {
    val button = Button(color = Blue)
    ReactTestUtils.withNewBodyElement { mountNode =>
      button.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(html, """<button class="ui blue button"></button>""")
    }
  }
  test("active") {
    val button = Button(active = true)
    ReactTestUtils.withNewBodyElement { mountNode =>
      button.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(html, """<button class="ui active button"></button>""")
    }
  }
  test("icon") {
    val button = Button(icon = true)(Icon(name = "comment"))
    ReactTestUtils.withNewBodyElement { mountNode =>
      button.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(
        html,
        """<button class="ui icon button"><i aria-hidden="true" class="comment icon"></i></button>"""
      )
    }
  }
  test("iconShorthand") {
    val button = Button(icon = Icon(name = "comment"))
    ReactTestUtils.withNewBodyElement { mountNode =>
      button.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(
        html,
        """<button class="ui icon button"><i aria-hidden="true" class="comment icon"></i></button>"""
      )
    }
  }
  test("label") {
    val button = Button(label = Label("Label"))
    ReactTestUtils.withNewBodyElement { mountNode =>
      button.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(
        html,
        """<div class="ui labeled button"><button class="ui button" tabindex="0"> </button><div class="ui left pointing basic label">Label</div></div>"""
      )
    }
  }
  test("labelText") {
    val button = Button(label = "Label")
    ReactTestUtils.withNewBodyElement { mountNode =>
      button.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(
        html,
        """<div class="ui labeled button"><button class="ui button" tabindex="0"> </button><div class="ui left pointing basic label">Label</div></div>"""
      )
    }
  }
  test("iconAndText") {
    val button = Button()(Icon(name = "comment"), "23")
    ReactTestUtils.withNewBodyElement { mountNode =>
      button.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(
        html,
        """<button class="ui button"><i aria-hidden="true" class="comment icon"></i>23</button>"""
      )
    }
  }
  test("toggle") {
    val button = Button(toggle = true)
    ReactTestUtils.withNewBodyElement { mountNode =>
      button.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(
        html,
        """<button class="ui toggle button" aria-pressed="false"></button>"""
      )
    }
  }
  test("tabIndex") {
    val button = Button(tabIndex = 2)
    ReactTestUtils.withNewBodyElement { mountNode =>
      button.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(html, """<button class="ui button" tabindex="2"></button>""")
    }
  }
  test("floated") {
    val button = Button(floated = Right)
    ReactTestUtils.withNewBodyElement { mountNode =>
      button.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(html, """<button class="ui right floated button"></button>""")
    }
  }
  test("circular") {
    val button = Button(circular = true)
    ReactTestUtils.withNewBodyElement { mountNode =>
      button.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(html, """<button class="ui circular button"></button>""")
    }
  }
  test("secondary") {
    val button = Button(secondary = true, circular = true)
    ReactTestUtils.withNewBodyElement { mountNode =>
      button.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(
        html,
        """<button class="ui circular secondary button"></button>"""
      )
    }
  }
  test("basic") {
    val button = Button(basic = true)
    ReactTestUtils.withNewBodyElement { mountNode =>
      button.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(html, """<button class="ui basic button"></button>""")
    }
  }
  test("className") {
    val button = Button(className = "extra")
    ReactTestUtils.withNewBodyElement { mountNode =>
      button.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(html, """<button class="ui button extra"></button>""")
    }
  }
  test("size") {
    val button = Button(size = Big)
    ReactTestUtils.withNewBodyElement { mountNode =>
      button.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(html, """<button class="ui big button"></button>""")
    }
  }
  test("withContent") {
    val button = Button(^.tpe := "submit")
    ReactTestUtils.withNewBodyElement { mountNode =>
      button.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(html, """<button type="submit" class="ui button"></button>""")
    }
  }
  test("withTwoContents") {
    val button = Button(^.tpe := "button")("Submit")
    ReactTestUtils.withNewBodyElement { mountNode =>
      button.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(html, """<button type="button" class="ui button">Submit</button>""")
    }
  }
  test("withMixedContent") {
    val button = Button(^.tpe := "button", "Submit")
    ReactTestUtils.withNewBodyElement { mountNode =>
      button.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(html, """<button type="button" class="ui button">Submit</button>""")
    }
  }
  test("withPropsAndContent") {
    val button = Button(color = Blue)(^.tpe := "submit")
    ReactTestUtils.withNewBodyElement { mountNode =>
      button.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(html, """<button type="submit" class="ui blue button"></button>""")
    }
  }
}
