// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.elements.segment

import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.GenericFnComponentPACOps
import react.common.syntax.vdom._

class SegmentSuite extends munit.FunSuite {
  test("segment") {
    val segment = new Segment().apply("Abc")
    ReactTestUtils.withNewBodyElement { mountNode =>
      segment.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML, """<div class="ui segment">Abc</div>""")
    }
  }
  test("segment with content") {
    val segment = Segment(secondary = true).apply(<.div("abc"))
    ReactTestUtils.withNewBodyElement { mountNode =>
      segment.renderIntoDOM(mountNode)
      assertEquals(mountNode.innerHTML,
                   """<div class="ui secondary segment"><div>abc</div></div>"""
      )
    }
  }
}
