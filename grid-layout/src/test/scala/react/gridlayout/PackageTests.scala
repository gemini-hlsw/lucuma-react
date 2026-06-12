// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.gridlayout

import japgolly.scalajs.react.test.*
import japgolly.scalajs.react.vdom.html_<^.*
import munit.FunSuite

import scala.language.implicitConversions

class PackageTests extends FunSuite {

  test("layouItem") {
    val item = LayoutItem(0, 1, 2, 3, "")
    assertNotEquals(item, null)
    assertEquals(item.w.toDouble, 0.0)
    assertEquals(item.h.toDouble, 1.0)
    assertEquals(item.x.toDouble, 2.0)
    assertEquals(item.y.toDouble, 3.0)
  }
  test("render") {
    val layout = ReactGridLayout(200, <.div("Abc"))
    ReactTestUtils.withRenderedSync(layout.raw) { m =>
      val html =
        """<div class="react-grid-layout" style="height: 10px;"></div>""".stripMargin
          .replaceAll("[\n\r]", "")
      m.outerHTML.assert(html)
    }
  }
  test("responsive") {
    val layouts: Map[BreakpointName, (Int, Int, Layout)] =
      Map(
        (BreakpointName.lg, (1200, 12, Layout.Empty)),
        (BreakpointName.md, (996, 10, Layout.Empty)),
        (BreakpointName.sm, (768, 8, Layout.Empty)),
        (BreakpointName.xs, (480, 6, Layout.Empty))
      )
    val layout                                           =
      ResponsiveReactGridLayout(200, layouts = layouts, <.div("Abc"))
    ReactTestUtils.withRenderedSync(layout.raw) { m =>
      val html =
        """<div class="react-grid-layout" style="height: 10px;"></div>""".stripMargin
          .replaceAll("[\n\r]", "")
      m.outerHTML.assert(html)
    }
    test("getBreakpointFromWidth") {
      // Exercises the react-grid-layout/core import (moved from Responsive.utils in v1).
      val breakpoints =
        Map(BreakpointName.lg -> 1200, BreakpointName.md -> 996, BreakpointName.sm -> 768)
      assert(getBreakpointFromWidth(breakpoints, 1300) == BreakpointName.lg)
      assert(getBreakpointFromWidth(breakpoints, 800) == BreakpointName.sm)
    }
    test("compactorAndStrategy") {
      // Exercises getCompactor + createScaledStrategy (react-grid-layout/core) at runtime.
      val layout =
        ReactGridLayout(200, compactType = CompactType.Horizontal, transformScale = 0.5)(
          <.div("Abc")
        )
      ReactTestUtils.withRenderedSync(layout.raw) { m =>
        val html =
          """<div class="react-grid-layout" style="height: 10px;"></div>""".stripMargin
            .replaceAll("[\n\r]", "")
        m.outerHTML.assert(html)
      }
    }
  }
}
