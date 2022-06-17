package react.semanticui.elements.segment

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class SegmentInlineSuite extends munit.FunSuite {
  test("segment-inline") {
    val segment = Segment(placeholder = true)("Abc", SegmentInline("Def"))
    ReactTestUtils.withNewBodyElement { mountNode =>
      segment.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(html,
                   """<div class="ui placeholder segment">Abc<div class="inline">Def</div></div>"""
      )
    }
  }
  test("segment-inline with content") {
    val segment = Segment(placeholder = true)("Abc", SegmentInline(<.div("def")))
    ReactTestUtils.withNewBodyElement { mountNode =>
      segment.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(
        html,
        """<div class="ui placeholder segment">Abc<div class="inline"><div>def</div></div></div>"""
      )
    }
  }
}
