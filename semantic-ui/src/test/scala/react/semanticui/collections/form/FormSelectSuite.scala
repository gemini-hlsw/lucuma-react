package react.semanticui.collections.form

import japgolly.scalajs.react.test._
import react.semanticui.modules.dropdown.DropdownItem
import react.semanticui.widths._
import react.common.syntax.vdom._

class FormSelectSuite extends munit.FunSuite {
  test("options") {
    val options =
      List(DropdownItem("abc"), DropdownItem(text = "def", value = 2))
    val select  = FormSelect(width = Two, options = options)
    ReactTestUtils.withNewBodyElement { mountNode =>
      select.renderIntoDOM(mountNode)
      val html = mountNode.outerHTML
      assertEquals(
        html,
        """<div><div class="two wide field"><div role="listbox" aria-expanded="false" class="ui selection dropdown" tabindex="0"><i aria-hidden="true" class="dropdown icon"></i><div class="menu transition"><div style="pointer-events: all;" role="option" aria-checked="false" aria-selected="true" class="selected item">abc</div><div style="pointer-events: all;" role="option" aria-checked="false" aria-selected="false" class="item"><span class="text">def</span></div></div></div></div></div>"""
      )
    }
  }
}
