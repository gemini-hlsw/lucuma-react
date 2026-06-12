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
  }
  test("getBreakpointFromWidth") {
    val breakpoints =
      Map(BreakpointName.lg -> 1200, BreakpointName.md -> 996, BreakpointName.sm -> 768)
    assert(getBreakpointFromWidth(breakpoints, 1300) == BreakpointName.lg)
    assert(getBreakpointFromWidth(breakpoints, 800) == BreakpointName.sm)
  }
  test("compactorAndStrategy") {
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
    // mirroring explore's config (useCSSTransforms = false).
    var started = false
    var resized = false
    val item    = LayoutItem(w = 2, h = 2, x = 0, y = 0, i = "a")
    val layout  =
      ReactGridLayout(
        width = 200,
        layout = Layout(List(item)),
        useCSSTransforms = false,
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
    // Mirror explore/ui-demo: Responsive + draggableHandle + useCSSTransforms=false + controlled layouts.
    // The facade builds a dragConfig here; verify the resize handle is NOT hidden/disabled.
    var started                                          = false
    val item                                             = LayoutItem(w = 2, h = 2, x = 0, y = 0, i = "a")
    val layouts: Map[BreakpointName, (Int, Int, Layout)] =
      Map(BreakpointName.lg -> (1200, 12, Layout(List(item))))
    val layout                                           =
      ResponsiveReactGridLayout(
        width = 1200,
        layouts = layouts,
        draggableHandle = ".tileTitle",
        useCSSTransforms = false,
        onResizeStart = (_, _, _, _, _, _) => japgolly.scalajs.react.Callback { started = true }
      )(
        <.div(^.key := "a", <.div(^.cls := "tileTitle", "title"), "Abc")
      )
    ReactTestUtils.withRenderedSync(layout.raw) { m =>
      val gridItem = m.querySelector(".react-grid-item").asInstanceOf[org.scalajs.dom.html.Element]
      // Setting draggableHandle must not disable dragging (the react-draggable class must be
      // present) nor hide the resize handle.
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
    // The facade builds config objects with some fields left undefined. Those must be pruned so that
    // spreading them over v2's defaults (e.g. { enabled: true }) does not clobber library defaults.
    val drag = BaseProps.pruneUndef(new raw.DragConfig(handle = ".item"))
    val keys = scala.scalajs.js.Object.keys(drag.asInstanceOf[scala.scalajs.js.Object]).toList
    assertEquals(keys,
                 List("handle"),
                 "DragConfig should only carry the keys actually set: " + keys
    )
  }
}
