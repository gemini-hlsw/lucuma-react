package react.semanticui.elements.input

import japgolly.scalajs.react.test.ReactTestUtils
import japgolly.scalajs.react.Callback
import react.semanticui.elements.icon._
import react.common.syntax.vdom._
import japgolly.scalajs.react.vdom.VdomNode

class InputSuite extends munit.FunSuite {
  test("render") {
    val input = Input()
    ReactTestUtils.withNewBodyElement { mountNode =>
      input.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="ui input"><input type="text"></div>""")
    }
  }
  test("actionBool") {
    val input = Input(action = true)
    ReactTestUtils.withNewBodyElement { mountNode =>
      input.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="ui action input"><input type="text"></div>"""
      )
    }
  }
  test("actionItem") {
    val input = Input(action = Icon(name = "comment"): VdomNode)
    ReactTestUtils.withNewBodyElement { mountNode =>
      input.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="ui action input"><input type="text"><i aria-hidden="true" class="comment icon"></i></div>"""
      )
    }
  }
  test("icon") {
    val input = Input(icon = Icon(name = "comment"))
    ReactTestUtils.withNewBodyElement { mountNode =>
      input.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="ui icon input"><input type="text"><i aria-hidden="true" class="comment icon"></i></div>"""
      )
    }
  }
  test("value") {
    val input = Input(value = "test")
    ReactTestUtils.withNewBodyElement { mountNode =>
      input.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="ui input"><input type="text" value="test"></div>"""
      )
    }
  }
  test("input") {
    val input = Input(input = Icon(name = "comment"): VdomNode)
    ReactTestUtils.withNewBodyElement { mountNode =>
      input.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="ui input"><i type="text" aria-hidden="true" class="comment icon"></i></div>"""
      )
    }
  }
  test("typePassword") {
    val input = Input(tpe = "password")
    ReactTestUtils.withNewBodyElement { mountNode =>
      input.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="ui input"><input type="password"></div>""")
    }
  }
  test("onChange") {
    val input = Input(onChange = Callback.log("abc"))
    ReactTestUtils.withNewBodyElement { mountNode =>
      input.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="ui input"><input type="text"></div>""")
    }
  }
}
