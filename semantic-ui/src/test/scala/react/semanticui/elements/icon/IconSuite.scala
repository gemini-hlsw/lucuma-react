package react.semanticui.elements.icon

import japgolly.scalajs.react.test._
import react.common._
import react.semanticui.elements.icon.IconRotated._
import react.semanticui.sizes._
import react.semanticui.colors._
import react.common.syntax.vdom._

class IconSuite extends munit.FunSuite {
  test("render") {
    val icon = Icon(name = "edit")
    ReactTestUtils.withNewBodyElement { mountNode =>
      icon.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<i aria-hidden="true" class="edit icon"></i>""")
    }
  }
  test("renderFitted") {
    val icon = Icon(fitted = true, name = "edit")
    ReactTestUtils.withNewBodyElement { mountNode =>
      icon.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<i aria-hidden="true" class="edit fitted icon"></i>"""
      )
    }
  }
  test("renderClassName") {
    val icon = Icon(className = "another", name = "edit")
    ReactTestUtils.withNewBodyElement { mountNode =>
      icon.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<i aria-hidden="true" class="edit icon another"></i>"""
      )
    }
  }
  test("size") {
    val icon = Icon(size = Big, name = "edit")
    ReactTestUtils.withNewBodyElement { mountNode =>
      icon.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<i aria-hidden="true" class="edit big icon"></i>"""
      )
    }
  }
  test("flipped") {
    val icon = Icon(flipped = IconFlip.Horizontally, name = "edit")
    ReactTestUtils.withNewBodyElement { mountNode =>
      icon.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<i aria-hidden="true" class="edit horizontally flipped icon"></i>"""
      )
    }
  }
  test("color") {
    val icon = Icon(color = Blue, name = "edit")
    ReactTestUtils.withNewBodyElement { mountNode =>
      icon.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<i aria-hidden="true" class="blue edit icon"></i>"""
      )
    }
  }
  test("colorCopy") {
    val icon = Icon(color = Blue, name = "edit").color(Red)
    ReactTestUtils.withNewBodyElement { mountNode =>
      icon.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<i aria-hidden="true" class="red edit icon"></i>"""
      )
    }
  }
  test("rotated") {
    val icon = Icon(rotated = IconRotated.Clockwise, name = "edit")
    ReactTestUtils.withNewBodyElement { mountNode =>
      icon.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<i aria-hidden="true" class="edit clockwise rotated icon"></i>"""
      )
    }
  }
  test("group") {
    val icon = IconGroup(Icon(rotated = Clockwise, name = "edit"), Icon(name = "copy"))
    ReactTestUtils.withNewBodyElement { mountNode =>
      icon.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<i class="icons"><i aria-hidden="true" class="edit clockwise rotated icon"></i><i aria-hidden="true" class="copy icon"></i></i>"""
      )
    }
  }
  test("conversions") {
    assert(Icon(loading = true, name = "edit") == Icon("edit").loading())
    assert(Icon(loading = false, name = "edit") == Icon("edit").loading(false))
    assert(Icon(color = Red, name = "edit") == Icon("edit").color(Red))
    assert(
      Icon(rotated = IconRotated.Clockwise, name = "edit") == Icon("edit")
        .rotated(IconRotated.Clockwise)
    )
    val clazz = Css("prop")
    assertEquals(Icon(clazz = clazz, name = "edit"), Icon("edit").clazz(clazz))
  }
}
