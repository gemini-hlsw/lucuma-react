package react.semanticui.modules.accordion

import japgolly.scalajs.react.test._
import react.common.syntax.vdom._

class AccordionContentSuite extends munit.FunSuite {
  test("content") {
    val content = AccordionContent(content = "polka")
    ReactTestUtils.withNewBodyElement { mountNode =>
      content.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="content">polka</div>""")
    }
  }
  test("apply") {
    val content = AccordionContent("zydeco")
    ReactTestUtils.withNewBodyElement { mountNode =>
      content.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="content">zydeco</div>""")
    }
  }
  test("active") {
    val content = AccordionContent(content = "disco", active = true)
    ReactTestUtils.withNewBodyElement { mountNode =>
      content.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="content active">disco</div>""")
    }
  }
}
