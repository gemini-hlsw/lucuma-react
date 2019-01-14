package react.gridlayout

// import japgolly.scalajs.react._
import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
// import org.scalajs.dom.MouseEvent
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
        println(m.outerHtmlScrubbed)
        assert(m.outerHtmlScrubbed() == html)
      }
    }
  }
}
