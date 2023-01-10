// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.elements.segment

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.GenericFnComponentPACOps
import react.common.syntax.vdom._

class SegmentGroupSuite extends munit.FunSuite {
  test("segment-group") {
    val segments = SegmentGroup(new Segment().apply("Abc"))
    ReactTestUtils.withNewBodyElement { mountNode =>
      segments.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(html, """<div class="ui segments"><div class="ui segment">Abc</div></div>""")
    }
  }
  test("segment-group with content") {
    val segments =
      SegmentGroup(horizontal = true).apply(new Segment().apply("Abc"),
                                            Segment(secondary = true)(<.div("abc"))
      )
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
