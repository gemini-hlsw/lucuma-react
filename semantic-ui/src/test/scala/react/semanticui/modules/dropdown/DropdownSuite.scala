package react.semanticui.modules.dropdown

import japgolly.scalajs.react.test._
import react.common.syntax.vdom._

class DropdownSuite extends munit.FunSuite {
  test("dropdown") {
    val dropdown = Dropdown()
    ReactTestUtils.withNewBodyElement { mountNode =>
      dropdown.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.outerHTML,
        """<div><div role="listbox" aria-expanded="false" class="ui dropdown" tabindex="0"><i aria-hidden="true" class="dropdown icon"></i><div class="menu transition"></div></div></div>"""
      )
    }
  }
  test("options") {
    val options  =
      List(DropdownItem("abc"), DropdownItem(text = "def", value = 2))
    val dropdown = Dropdown(options = options)
    ReactTestUtils.withNewBodyElement { mountNode =>
      dropdown.renderIntoDOM(mountNode)
      val html = mountNode.outerHTML
      assertEquals(
        html,
        """<div><div role="listbox" aria-expanded="false" class="ui dropdown" tabindex="0"><i aria-hidden="true" class="dropdown icon"></i><div class="menu transition"><div style="pointer-events: all;" role="option" aria-checked="false" aria-selected="true" class="selected item">abc</div><div style="pointer-events: all;" role="option" aria-checked="false" aria-selected="false" class="item"><span class="text">def</span></div></div></div></div>"""
      )
    }
  }
  test("additionLabel") {
    val options  =
      List(DropdownItem("abc"), DropdownItem(text = "def", value = 2))
    val dropdown = Dropdown(options = options,
                            selection = false,
                            search = true,
                            defaultOpen = true,
                            additionPosition = AdditionPosition.Top,
                            allowAdditions = true,
                            additionLabel = "add"
    )
    ReactTestUtils.withNewBodyElement { mountNode =>
      dropdown.renderIntoDOM(mountNode)
      val html = mountNode.outerHTML
      assertEquals(
        html,
        """<div><div role="combobox" aria-expanded="true" class="ui active visible search dropdown"><input aria-autocomplete="list" autocomplete="off" class="search" tabindex="0" type="text" value=""><div aria-atomic="true" aria-live="polite" role="alert" class="divider text"></div><i aria-hidden="true" class="dropdown icon"></i><div role="listbox" class="visible menu transition"><div style="pointer-events: all;" role="option" aria-checked="false" aria-selected="true" class="selected item">abc</div><div style="pointer-events: all;" role="option" aria-checked="false" aria-selected="false" class="item"><span class="text">def</span></div></div></div></div>"""
      )
    }
  }
  test("basic") {
    val dropdown = Dropdown(basic = true)
    ReactTestUtils.withNewBodyElement { mountNode =>
      dropdown.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.outerHTML,
        """<div><div role="listbox" aria-expanded="false" class="ui basic dropdown" tabindex="0"><i aria-hidden="true" class="dropdown icon"></i><div class="menu transition"></div></div></div>"""
      )
    }
  }
}
