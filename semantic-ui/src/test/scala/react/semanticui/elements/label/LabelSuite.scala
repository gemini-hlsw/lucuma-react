package react.semanticui.elements.label

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.semanticui.elements.icon._
import react.semanticui.colors._
import react.semanticui.sizes._
import react.common.syntax.vdom._

class LabelSuite extends munit.FunSuite {
  test("render") {
    val label = Label()
    ReactTestUtils.withNewBodyElement { mountNode =>
      label.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="ui label"></div>""")
    }
  }
  test("attached") {
    val label = Label(attached = LabelAttached.Top)
    ReactTestUtils.withNewBodyElement { mountNode =>
      label.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="ui top attached label"></div>""")
    }
  }
  test("color") {
    val label = Label(color = Blue)
    ReactTestUtils.withNewBodyElement { m =>
      label.renderIntoDOM(m)
      assertEquals(m.innerHTML, """<div class="ui blue label"></div>""")
    }
  }
  test("corner_boolean") {
    val label = Label(corner = LabelCorner.Corner)
    ReactTestUtils.withNewBodyElement { m =>
      label.renderIntoDOM(m)
      assertEquals(m.innerHTML, """<div class="ui corner label"></div>""")
    }
  }
  test("corner_string") {
    val label = Label(corner = LabelCorner.Left)
    ReactTestUtils.withNewBodyElement { m =>
      label.renderIntoDOM(m)
      assertEquals(m.innerHTML, """<div class="ui left corner label"></div>""")
    }
  }
  test("prompt") {
    val label = Label(prompt = true)
    ReactTestUtils.withNewBodyElement { m =>
      label.renderIntoDOM(m)
      assertEquals(m.innerHTML, """<div class="ui prompt label"></div>""")
    }
  }
  test("detailVdom") {
    val labelDetail = LabelDetail("Detail")
    val label       = Label(detail = labelDetail)
    ReactTestUtils.withNewBodyElement { m =>
      label.renderIntoDOM(m)
      assertEquals(m.innerHTML, """<div class="ui label"><div class="detail">Detail</div></div>""")
    }
  }
  test("active") {
    val label = Label(active = true)
    ReactTestUtils.withNewBodyElement { m =>
      label.renderIntoDOM(m)
      assertEquals(m.innerHTML, """<div class="ui active label"></div>""")
    }
  }
  test("icon") {
    val label = Label(Icon(name = "comment"))
    ReactTestUtils.withNewBodyElement { m =>
      label.renderIntoDOM(m)
      assertEquals(
        m.innerHTML,
        """<div class="ui label"><i aria-hidden="true" class="comment icon"></i></div>"""
      )
    }
  }
  test("iconShorthand") {
    val label = Label(icon = Icon(name = "comment"))
    ReactTestUtils.withNewBodyElement { m =>
      label.renderIntoDOM(m)
      assertEquals(
        m.innerHTML,
        """<div class="ui label"><i aria-hidden="true" class="comment icon"></i></div>"""
      )
    }
  }
  test("iconAndText") {
    val label = Label(Icon("comment"), "23")
    ReactTestUtils.withNewBodyElement { m =>
      label.renderIntoDOM(m)
      val html = m.innerHTML
      assertEquals(
        html,
        """<div class="ui label"><i aria-hidden="true" class="comment icon"></i>23</div>"""
      )
    }
  }
  test("iconAndTextShorthand") {
    val label = Label(icon = Icon(name = "comment"), content = "23")
    ReactTestUtils.withNewBodyElement { m =>
      label.renderIntoDOM(m)
      val html = m.innerHTML
      assertEquals(
        html,
        """<div class="ui label"><i aria-hidden="true" class="comment icon"></i>23</div>"""
      )
    }
  }
  test("imageAndDetail") {
    val label = Label(as = "a", image = true)(<.img(^.src := "image.jpg"), "img")
    ReactTestUtils.withNewBodyElement { m =>
      label.renderIntoDOM(m)
      assertEquals(m.innerHTML, """<a class="ui image label"><img src="image.jpg">img</a>""")
    }
  }
  test("tag") {
    val label = Label(tag = true)
    ReactTestUtils.withNewBodyElement { m =>
      label.renderIntoDOM(m)
      assertEquals(m.innerHTML, """<div class="ui tag label"></div>""")
    }
  }
  test("horizontal") {
    val label = Label(horizontal = true)
    ReactTestUtils.withNewBodyElement { m =>
      label.renderIntoDOM(m)
      assertEquals(m.innerHTML, """<div class="ui horizontal label"></div>""")
    }
  }
  test("floating") {
    val label = Label(floating = true)
    ReactTestUtils.withNewBodyElement { m =>
      label.renderIntoDOM(m)
      assertEquals(m.innerHTML, """<div class="ui floating label"></div>""")
    }
  }
  test("circular") {
    val label = Label(circular = true)
    ReactTestUtils.withNewBodyElement { m =>
      label.renderIntoDOM(m)
      assertEquals(m.innerHTML, """<div class="ui circular label"></div>""")
    }
  }
  test("empty") {
    val label = Label(empty = true, circular = true)
    ReactTestUtils.withNewBodyElement { m =>
      label.renderIntoDOM(m)
      assertEquals(m.innerHTML, """<div class="ui circular empty label"></div>""")
    }
  }
  test("pointing") {
    val label = Label(pointing = LabelPointing.Default)
    ReactTestUtils.withNewBodyElement { m =>
      label.renderIntoDOM(m)
      assertEquals(m.innerHTML, """<div class="ui pointing label"></div>""")
    }
  }
  test("pointingBelow") {
    val label = Label(pointing = LabelPointing.Below)
    ReactTestUtils.withNewBodyElement { m =>
      label.renderIntoDOM(m)
      assertEquals(m.innerHTML, """<div class="ui pointing below label"></div>""")
    }
  }
  test("basic") {
    val label = Label(basic = true)
    ReactTestUtils.withNewBodyElement { m =>
      label.renderIntoDOM(m)
      assertEquals(m.innerHTML, """<div class="ui basic label"></div>""")
    }
  }
  test("className") {
    val label = Label(className = "extra")
    ReactTestUtils.withNewBodyElement { m =>
      label.renderIntoDOM(m)
      assertEquals(m.innerHTML, """<div class="ui label extra"></div>""")
    }
  }
  test("removeIcon") {
    val label = Label(removeIcon = Icon(name = "remove"))
    ReactTestUtils.withNewBodyElement { m =>
      label.renderIntoDOM(m)
      assertEquals(m.innerHTML, """<div class="ui label"></div>""")
    }
  }
  test("ribbonDefault") {
    val label = Label(ribbon = LabelRibbon.Default)
    ReactTestUtils.withNewBodyElement { m =>
      label.renderIntoDOM(m)
      assertEquals(m.innerHTML, """<div class="ui ribbon label"></div>""")
    }
  }
  test("ribbonRight") {
    val label = Label(ribbon = LabelRibbon.Right)
    ReactTestUtils.withNewBodyElement { m =>
      label.renderIntoDOM(m)
      assertEquals(m.innerHTML, """<div class="ui right ribbon label"></div>""")
    }
  }
  test("size") {
    val label = Label(size = Big)
    ReactTestUtils.withNewBodyElement { m =>
      label.renderIntoDOM(m)
      assertEquals(m.innerHTML, """<div class="ui big label"></div>""")
    }
  }
}
