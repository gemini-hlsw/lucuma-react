package react.semanticui.modules.accordion

import japgolly.scalajs.react.test._
import react.common.syntax.vdom._

class AccordionSuite extends munit.FunSuite {
  test("single") {
    val panel     = AccordionPanel(title = "The Title", content = "Some Content")
    val accordion = Accordion(panels = Seq(panel))
    ReactTestUtils.withNewBodyElement { mountNode =>
      accordion.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="accordion ui"><div class="title"><i aria-hidden="true" class="dropdown icon"></i>The Title</div><div class="content">Some Content</div></div>"""
      )
    }
  }
  test("single") {
    val panel1    = AccordionPanel(title = "Title 1", content = "Content 1")
    val panel2    = AccordionPanel(title = "Title 2", content = "Content 2")
    val accordion = Accordion(panels = Seq(panel1, panel2))
    ReactTestUtils.withNewBodyElement { mountNode =>
      accordion.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="accordion ui"><div class="title"><i aria-hidden="true" class="dropdown icon"></i>Title 1</div><div class="content">Content 1</div><div class="title"><i aria-hidden="true" class="dropdown icon"></i>Title 2</div><div class="content">Content 2</div></div>"""
      )
    }
  }
  test("styled") {
    val panel1    = AccordionPanel(title = "Title 1", content = "Content 1")
    val panel2    = AccordionPanel(title = "Title 2", content = "Content 2")
    val accordion = Accordion(panels = Seq(panel1, panel2), styled = true)
    ReactTestUtils.withNewBodyElement { mountNode =>
      accordion.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="accordion ui styled"><div class="title"><i aria-hidden="true" class="dropdown icon"></i>Title 1</div><div class="content">Content 1</div><div class="title"><i aria-hidden="true" class="dropdown icon"></i>Title 2</div><div class="content">Content 2</div></div>"""
      )
    }
  }
  test("fluid") {
    val panel1    = AccordionPanel(title = "Title 1", content = "Content 1")
    val panel2    = AccordionPanel(title = "Title 2", content = "Content 2")
    val accordion = Accordion(panels = Seq(panel1, panel2), fluid = true)
    ReactTestUtils.withNewBodyElement { mountNode =>
      accordion.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="accordion ui fluid"><div class="title"><i aria-hidden="true" class="dropdown icon"></i>Title 1</div><div class="content">Content 1</div><div class="title"><i aria-hidden="true" class="dropdown icon"></i>Title 2</div><div class="content">Content 2</div></div>"""
      )
    }
  }
  test("inverted") {
    val panel1    = AccordionPanel(title = "Title 1", content = "Content 1")
    val panel2    = AccordionPanel(title = "Title 2", content = "Content 2")
    val accordion = Accordion(panels = Seq(panel1, panel2), inverted = true)
    ReactTestUtils.withNewBodyElement { mountNode =>
      accordion.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="accordion ui inverted"><div class="title"><i aria-hidden="true" class="dropdown icon"></i>Title 1</div><div class="content">Content 1</div><div class="title"><i aria-hidden="true" class="dropdown icon"></i>Title 2</div><div class="content">Content 2</div></div>"""
      )
    }
  }
  test("exclusive") {
    val panel1    = AccordionPanel(title = "Title 1", content = "Content 1")
    val panel2    = AccordionPanel(title = "Title 2", content = "Content 2")
    val accordion = Accordion(panels = Seq(panel1, panel2), exclusive = true)
    ReactTestUtils.withNewBodyElement { mountNode =>
      accordion.renderIntoDOM(mountNode)
      // does not affect the html
      assertEquals(
        mountNode.innerHTML,
        """<div class="accordion ui"><div class="title"><i aria-hidden="true" class="dropdown icon"></i>Title 1</div><div class="content">Content 1</div><div class="title"><i aria-hidden="true" class="dropdown icon"></i>Title 2</div><div class="content">Content 2</div></div>"""
      )
    }
  }
  test("all options") {
    val panel1    = AccordionPanel(title = "Title 1", content = "Content 1")
    val panel2    = AccordionPanel(title = "Title 2", content = "Content 2")
    val accordion =
      Accordion(panels = Seq(panel1, panel2), styled = true, fluid = true, inverted = true)
    ReactTestUtils.withNewBodyElement { mountNode =>
      accordion.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="accordion ui fluid inverted styled"><div class="title"><i aria-hidden="true" class="dropdown icon"></i>Title 1</div><div class="content">Content 1</div><div class="title"><i aria-hidden="true" class="dropdown icon"></i>Title 2</div><div class="content">Content 2</div></div>"""
      )
    }
  }
  test("one active") {
    val panel1    = AccordionPanel(title = "Title 1", content = "Content 1")
    val panel2    = AccordionPanel(title = "Title 2", content = "Content 2")
    val accordion = Accordion(panels = Seq(panel1, panel2), defaultActiveIndex = 1)
    ReactTestUtils.withNewBodyElement { mountNode =>
      accordion.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="accordion ui"><div class="title"><i aria-hidden="true" class="dropdown icon"></i>Title 1</div><div class="content">Content 1</div><div class="active title"><i aria-hidden="true" class="dropdown icon"></i>Title 2</div><div class="content active">Content 2</div></div>"""
      )
    }
  }
  test("all active") {
    val panel1    = AccordionPanel(title = "Title 1", content = "Content 1")
    val panel2    = AccordionPanel(title = "Title 2", content = "Content 2")
    val accordion =
      Accordion(panels = Seq(panel1, panel2), exclusive = false, defaultActiveIndex = List(0, 1))
    ReactTestUtils.withNewBodyElement { mountNode =>
      accordion.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="accordion ui"><div class="active title"><i aria-hidden="true" class="dropdown icon"></i>Title 1</div><div class="content active">Content 1</div><div class="active title"><i aria-hidden="true" class="dropdown icon"></i>Title 2</div><div class="content active">Content 2</div></div>"""
      )
    }
  }
  test("some active") {
    val panel1    = AccordionPanel(title = "Title 1", content = "Content 1")
    val panel2    = AccordionPanel(title = "Title 2", content = "Content 2")
    val panel3    = AccordionPanel(title = "Title 3", content = "Content 3")
    val accordion =
      Accordion(panels = Seq(panel1, panel2, panel3),
                exclusive = false,
                defaultActiveIndex = List(0, 2)
      )
    ReactTestUtils.withNewBodyElement { mountNode =>
      accordion.renderIntoDOM(mountNode)
      assertEquals(
        mountNode.innerHTML,
        """<div class="accordion ui"><div class="active title"><i aria-hidden="true" class="dropdown icon"></i>Title 1</div><div class="content active">Content 1</div><div class="title"><i aria-hidden="true" class="dropdown icon"></i>Title 2</div><div class="content">Content 2</div><div class="active title"><i aria-hidden="true" class="dropdown icon"></i>Title 3</div><div class="content active">Content 3</div></div>"""
      )
    }
  }
}
