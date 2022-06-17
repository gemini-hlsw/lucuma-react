package react.semanticui.elements.segment

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class SegmentGroupSuite extends munit.FunSuite {
  test("segment-group") {
    val segments = SegmentGroup(Segment("Abc"))
    ReactTestUtils.withNewBodyElement { mountNode =>
      segments.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(html, """<div class="ui segments"><div class="ui segment">Abc</div></div>""")
    }
  }
  test("segment-group with content") {
    val segments =
      SegmentGroup(horizontal = true)(Segment("Abc"), Segment(secondary = true)(<.div("abc")))
    ReactTestUtils.withNewBodyElement { mountNode =>
      segments.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(
        html,
        """<div class="ui horizontal segments"><div class="ui segment">Abc</div><div class="ui secondary segment"><div>abc</div></div></div>"""
      )
    }
  }
}
