// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.elements.segment

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.GenericFnComponentPACOps
import react.common.syntax.vdom._

class SegmentInlineSuite extends munit.FunSuite {
  test("segment-inline") {
    val segment = Segment(placeholder = true).apply("Abc", new SegmentInline().apply("Def"))
    ReactTestUtils.withNewBodyElement { mountNode =>
      segment.renderIntoDOM(mountNode)
      val html = mountNode.innerHTML
      assertEquals(html,
                   """<div class="ui placeholder segment">Abc<div class="inline">Def</div></div>"""
      )
    }
  }
  test("segment-inline with content") {
    val segment = Segment(placeholder = true).apply("Abc", SegmentInline(<.div("def")))
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
