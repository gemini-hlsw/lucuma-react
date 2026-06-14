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
    val breakpoints: Map[BreakpointName, Int] =
      Map(BreakpointName.lg -> 1200, BreakpointName.md -> 996, BreakpointName.sm -> 768)
    val cols: Map[BreakpointName, Int]        =
      Map(BreakpointName.lg -> 12, BreakpointName.md -> 10, BreakpointName.sm -> 8)
    val layouts: ResponsiveLayouts            =
      Map(BreakpointName.lg -> Layout.Empty,
          BreakpointName.md -> Layout.Empty,
          BreakpointName.sm -> Layout.Empty
      )
    val layout                                =
      ResponsiveReactGridLayout(200, breakpoints, cols, layouts, <.div("Abc"))
    ReactTestUtils.withRenderedSync(layout.raw) { m =>
      val html =
        """<div class="react-grid-layout" style="height: 10px;"></div>""".stripMargin
          .replaceAll("[\n\r]", "")
      m.outerHTML.assert(html)
    }
  }
  test("getBreakpointFromWidth") {
    val breakpoints =
      Map(BreakpointName.lg -> 1200, BreakpointName.md -> 996, BreakpointName.sm -> 768)
    assert(getBreakpointFromWidth(breakpoints, 1300) == BreakpointName.lg)
    assert(getBreakpointFromWidth(breakpoints, 800) == BreakpointName.sm)
  }
  test("compactorAndStrategy") {
    // Exercises a custom compactor + scaled position strategy (react-grid-layout/core) at runtime.
    val layout =
      ReactGridLayout(
        width = 200,
        compactor = Compactor.horizontal,
        positionStrategy = PositionStrategy.scaled(0.5)
      )(<.div("Abc"))
    ReactTestUtils.withRenderedSync(layout.raw) { m =>
      val html =
        """<div class="react-grid-layout" style="height: 10px;"></div>""".stripMargin
          .replaceAll("[\n\r]", "")
      m.outerHTML.assert(html)
    }
  }
  test("resizeHandleRenders") {
    val item   = LayoutItem(w = 2, h = 2, x = 0, y = 0, i = "a")
    val layout =
      ReactGridLayout(width = 200, layout = Layout(List(item)))(
        <.div(^.key := "a", "Abc")
      )
    ReactTestUtils.withRenderedSync(layout.raw) { m =>
      val html = m.asElement().outerHTML
      assert(html.contains("react-resizable-handle"), html)
    }
  }
  test("resizeDragFiresCallback") {
    // Simulate grabbing the 'se' handle and dragging: onResizeStart/onResize must fire,
    // mirroring explore's config (absolute position strategy).
    var started = false
    var resized = false
    val item    = LayoutItem(w = 2, h = 2, x = 0, y = 0, i = "a")
    val layout  =
      ReactGridLayout(
        width = 200,
        layout = Layout(List(item)),
        positionStrategy = PositionStrategy.absolute,
        onResizeStart = (_, _, _, _, _, _) => japgolly.scalajs.react.Callback { started = true },
        onResize = (_, _, _, _, _, _) => japgolly.scalajs.react.Callback { resized = true }
      )(
        <.div(^.key := "a", "Abc")
      )
    ReactTestUtils.withRenderedSync(layout.raw) { m =>
      val handle                                                               = m.querySelector(".react-resizable-handle")
      def mouse(tpe: String, x: Double, y: Double): org.scalajs.dom.MouseEvent = {
        val init = new org.scalajs.dom.MouseEventInit {}
        init.bubbles = true
        init.cancelable = true
        init.clientX = x
        init.clientY = y
        new org.scalajs.dom.MouseEvent(tpe, init)
      }
      handle.dispatchEvent(mouse("mousedown", 22, 310))
      org.scalajs.dom.document.dispatchEvent(mouse("mousemove", 160, 500))
      org.scalajs.dom.document.dispatchEvent(mouse("mouseup", 160, 500))
      assert(started, "onResizeStart did not fire")
      assert(resized, "onResize did not fire")
    }
  }
  test("responsiveWithDraggableHandleResizes") {
    // Mirror explore: Responsive + dragConfig.handle + absolute strategy + controlled layouts.
    // Setting a drag handle must not disable dragging nor hide the resize handle.
    val item                                  = LayoutItem(w = 2, h = 2, x = 0, y = 0, i = "a")
    val breakpoints: Map[BreakpointName, Int] = Map(BreakpointName.lg -> 1200)
    val cols: Map[BreakpointName, Int]        = Map(BreakpointName.lg -> 12)
    val layouts: ResponsiveLayouts            = Map(BreakpointName.lg -> Layout(List(item)))
    val layout                                =
      ResponsiveReactGridLayout(
        width = 1200,
        breakpoints = breakpoints,
        cols = cols,
        layouts = layouts,
        dragConfig = DragConfig(handle = ".tileTitle"),
        positionStrategy = PositionStrategy.absolute
      )(
        <.div(^.key := "a", <.div(^.cls := "tileTitle", "title"), "Abc")
      )
    ReactTestUtils.withRenderedSync(layout.raw) { m =>
      val gridItem = m.querySelector(".react-grid-item").asInstanceOf[org.scalajs.dom.html.Element]
      assert(gridItem.classList.contains("react-draggable"),
             "draggableHandle clobbered dragging: " + gridItem.className
      )
      assert(!gridItem.classList.contains("react-resizable-hide"),
             "resize handle hidden: " + gridItem.className
      )
      assert(m.querySelectorOption(".react-resizable-handle").isDefined, "resize handle missing")
    }
  }
  test("configObjectsDropUndefinedKeys") {
    // toRaw must prune undefined fields so spreading over v2's defaults doesn't clobber them.
    val drag = DragConfig(handle = ".item").toRaw
    val keys = scala.scalajs.js.Object.keys(drag.asInstanceOf[scala.scalajs.js.Object]).toList
    assertEquals(keys,
                 List("handle"),
                 "DragConfig should only carry the keys actually set: " + keys
    )
  }
}
