package react.semanticui.modules.dropdown

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.semanticui.elements.icon._
import react.semanticui.elements.flag.Flag
import react.semanticui.elements.image.Image
import react.semanticui.elements.label.Label
import react.common.syntax.vdom._

class DropdownItemSuite extends munit.FunSuite {
  test("item") {
    val item = DropdownItem("Option 1")
    ReactTestUtils.withNewBodyElement { mountNode =>
      item.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div role="option" class="item">Option 1</div>"""
      )
    }
  }
  test("itemDescriptionString") {
    val item = DropdownItem(description = "some description")
    ReactTestUtils.withNewBodyElement { mountNode =>
      item.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div role="option" class="item"><span class="description">some description</span></div>"""
      )
    }
  }
  test("itemValue") {
    val item = DropdownItem(1)
    ReactTestUtils.withNewBodyElement { mountNode =>
      item.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div role="option" class="item">1</div>"""
      )
    }
  }
  test("itemValueChild") {
    val item = DropdownItem(value = 1)(<.div("abc"))
    ReactTestUtils.withNewBodyElement { mountNode =>
      item.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(
        html,
        """<div role="option" class="item"><div>abc</div></div>"""
      )
    }
  }
  test("icon") {
    val item = DropdownItem(value = 1, icon = Icon("edit"))
    ReactTestUtils.withNewBodyElement { mountNode =>
      item.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div role="option" class="item"><i aria-hidden="true" class="edit icon"></i></div>"""
      )
    }
  }
  test("flag") {
    val item = DropdownItem(value = 1, flag = Flag("cl"))
    ReactTestUtils.withNewBodyElement { mountNode =>
      item.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div role="option" class="item"><i class="cl flag"></i></div>"""
      )
    }
  }
  test("image") {
    val item = DropdownItem(value = 1, image = Image(href = "image"))
    ReactTestUtils.withNewBodyElement { mountNode =>
      item.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div role="option" class="item"><a class="ui image" href="image"><img></a></div>"""
      )
    }
  }
  test("label") {
    val item = DropdownItem(value = 1, label = Label("image"))
    ReactTestUtils.withNewBodyElement { mountNode =>
      item.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(
        html,
        """<div role="option" class="item"><div class="ui label">image</div></div>"""
      )
    }
  }
}
