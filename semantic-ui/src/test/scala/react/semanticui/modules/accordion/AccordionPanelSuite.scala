package react.semanticui.modules.accordion

import japgolly.scalajs.react.test._
import react.common.syntax.vdom._

class AccordionPanelSuite extends munit.FunSuite {
  test("strings") {
    val panel = AccordionPanel(title = "The Title", content = "Some Content")
    ReactTestUtils.withNewBodyElement { mountNode =>
      panel.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="title"><i aria-hidden="true" class="dropdown icon"></i>The Title</div><div class="content">Some Content</div>"""
      )
    }
  }
  test("classes") {
    val title   = AccordionTitle("The Title")
    val content = AccordionContent("Some Content")
    val panel   = AccordionPanel(title = title, content = content)
    ReactTestUtils.withNewBodyElement { mountNode =>
      panel.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="title"><i aria-hidden="true" class="dropdown icon"></i>The Title</div><div class="content">Some Content</div>"""
      )
    }
  }
  test("active") {
    val title   = AccordionTitle("The Title")
    val content = AccordionContent("Some Content")
    val panel   = AccordionPanel(title = title, content = content, active = true)
    ReactTestUtils.withNewBodyElement { mountNode =>
      panel.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="active title"><i aria-hidden="true" class="dropdown icon"></i>The Title</div><div class="content active">Some Content</div>"""
      )
    }
  }
}
