package react.semanticui.elements.segment

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax.vdom._

class SegmentSuite extends munit.FunSuite {
  test("segment") {
    val segment = Segment("Abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      segment.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="ui segment">Abc</div>""")
    }
  }
  test("segment with content") {
    val segment = Segment(secondary = true)(<.div("abc"))
    ReactTestUtils.withNewBodyElement { mountNode =>
      segment.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML,
                   """<div class="ui secondary segment"><div>abc</div></div>"""
      )
    }
  }
}
