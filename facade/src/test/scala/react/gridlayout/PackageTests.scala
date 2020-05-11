package react.gridlayout

import japgolly.scalajs.react.raw.JsNumber
import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax._
import utest._

object PackageTests extends TestSuite {

  val tests = Tests {
    test("layouItem") {
      val item = LayoutItem(0, 1, 2, 3, "")
      assert(item != null)
      assert(item.w.toDouble == 0.0)
      assert(item.h.toDouble == 1.0)
      assert(item.x.toDouble == 2.0)
      assert(item.y.toDouble == 3.0)
    }
    test("render") {
      val layout = ReactGridLayout(200, <.div("Abc"))
      ReactTestUtils.withRenderedIntoDocument(layout) { m =>
        val html =
          """<div class="react-grid-layout" style="height: 170px;"></div>""".stripMargin
            .replaceAll("[\n\r]", "")
        assert(m.outerHtmlScrubbed() == html)
      }
    }
    test("responsive") {
      val layouts: Map[BreakpointName, (JsNumber, JsNumber, Layout)] =
        Map(
          (BreakpointName.lg, (1200, 12, Layout.Empty)),
          (BreakpointName.md, (996, 10, Layout.Empty)),
          (BreakpointName.sm, (768, 8, Layout.Empty)),
          (BreakpointName.xs, (480, 6, Layout.Empty))
        )
      val layout                                                     =
        ResponsiveReactGridLayout(200, layouts = layouts, <.div("Abc"))
      ReactTestUtils.withRenderedIntoDocument(layout) { m =>
        val html =
          """<div class="react-grid-layout" style="height: 150px;"></div>""".stripMargin
            .replaceAll("[\n\r]", "")
        assert(m.outerHtmlScrubbed() == html)
      }
    }
  }
}
