package react.gridlayout

import japgolly.scalajs.react.raw.JsNumber
import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import react.common.syntax._
import utest._

object PackageTests extends TestSuite {

  val tests = Tests {
    'layouItem - {
      val item = LayoutItem(0, 1, 2, 3, "")
      assert(item != null)
      assert(item.w.toDouble == 0.0)
      assert(item.h.toDouble == 1.0)
      assert(item.x.toDouble == 2.0)
      assert(item.y.toDouble == 3.0)
    }
    'render - {
      val layout = ReactGridLayout(ReactGridLayout.props(200), <.div("Abc"))
      ReactTestUtils.withRenderedIntoDocument(layout) { m =>
        val html =
          """<div class="react-grid-layout" style="height: 170px;"></div>""".stripMargin
            .replaceAll("[\n\r]", "")
        assert(m.outerHtmlScrubbed() == html)
      }
    }
    'responsive - {
      val layouts: Map[BreakpointName, (JsNumber, JsNumber, Layout)] =
        Map(
          (BreakpointName.lg, (1200, 12, Layout.Empty)),
          (BreakpointName.md, (996, 10, Layout.Empty)),
          (BreakpointName.sm, (768, 8, Layout.Empty)),
          (BreakpointName.xs, (480, 6, Layout.Empty))
        )
      val layout =
        ResponsiveReactGridLayout(ResponsiveReactGridLayout.props(200, layouts = layouts),
                                  <.div("Abc"))
      ReactTestUtils.withRenderedIntoDocument(layout) { m =>
        val html =
          """<div class="react-grid-layout" style="height: 170px;"></div>""".stripMargin
            .replaceAll("[\n\r]", "")
        println(m.outerHtmlScrubbed)
        assert(m.outerHtmlScrubbed() == html)
      }
    }
  }
}
