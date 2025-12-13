// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react

package draggable

import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.test.*
import japgolly.scalajs.react.vdom.html_<^.*
import org.scalajs.dom.MouseEvent
import utest.*

class DraggableTests extends TestSuite {
  val tests = Tests {
    test("render") {
      val draggable = Draggable(Draggable.props(), <.div("Abc"))
      ReactTestUtils.withRenderedSync(draggable) { m =>
        val html =
          """<div class="react-draggable" style="transform: translate(0px,0px);">Abc</div>""".stripMargin
            .replaceAll("[\n\r]", "")
        m.outerHTML.assert(html)
      }
    }
    test("supportAllowAnyClick") {
      Draggable(Draggable.props(), <.div("Abc")).props.allowAnyClick.toOption ==> Some(false)
      val draggable =
        Draggable(Draggable.props(allowAnyClick = true), <.div("Abc"))
      ReactTestUtils.withRenderedSync(draggable) { m =>
        val html =
          """<div class="react-draggable" style="transform: translate(0px,0px);">Abc</div>""".stripMargin
            .replaceAll("[\n\r]", "")
        m.outerHTML.assert(html)
      }
      draggable.props.allowAnyClick.toOption ==> Some(true)
    }
    test("supportAxis") {
      Draggable(Draggable.props(), <.div("Abc")).props.axis.toOption ==> Some("both")
      val draggable = Draggable(Draggable.props(axis = Axis.X), <.div("Abc"))
      ReactTestUtils.withRenderedSync(draggable) { m =>
        val html =
          """<div class="react-draggable" style="transform: translate(0px,0px);">Abc</div>""".stripMargin
            .replaceAll("[\n\r]", "")
        m.outerHTML.assert(html)
      }
      draggable.props.axis.toOption ==> Some("x")
    }
    test("boundsDefault") {
      Draggable(Draggable.props(), <.div("Abc")).props.bounds.toOption ==> Some(false)
    }
    test("supportBounds1") {
      val draggable =
        Draggable(Draggable.props(bounds = FalseBounds), <.div("Abc"))
      ReactTestUtils.withRenderedSync(draggable) { m =>
        val html =
          """<div class="react-draggable" style="transform: translate(0px,0px);">Abc</div>""".stripMargin
            .replaceAll("[\n\r]", "")
        m.outerHTML.assert(html)
      }
      draggable.props.bounds.toOption ==> Some(false)
    }
    test("supportBounds2") {
      val draggable =
        Draggable(Draggable.props(bounds = "bounds"), <.div("Abc"))
      ReactTestUtils.withRenderedSync(draggable) { m =>
        val html =
          """<div class="react-draggable" style="transform: translate(0px,0px);">Abc</div>""".stripMargin
            .replaceAll("[\n\r]", "")
        m.outerHTML.assert(html)
      }
      draggable.props.bounds.toOption ==> Some("bounds")
    }
    test("supportBounds3") {
      val draggable =
        Draggable(Draggable.props(bounds = DraggableBounds(1, 2, 3, 4)), <.div("Abc"))
      ReactTestUtils.withRenderedSync(draggable) { m =>
        val html =
          """<div class="react-draggable" style="transform: translate(0px,0px);">Abc</div>""".stripMargin
            .replaceAll("[\n\r]", "")
        m.outerHTML.assert(html)
      }
      draggable.props.bounds.toOption
        .map(_.asInstanceOf[DraggableBounds].left) ==> Some(1)
    }
    test("supportCancel") {
      Draggable(Draggable.props(), <.div("Abc")).props.cancel.toOption ==> None
      val draggable =
        Draggable(Draggable.props(cancel = "cancel"), <.div("Abc"))
      ReactTestUtils.withRenderedSync(draggable) { m =>
        val html =
          """<div class="react-draggable" style="transform: translate(0px,0px);">Abc</div>""".stripMargin
            .replaceAll("[\n\r]", "")
        m.outerHTML.assert(html)
      }
      draggable.props.cancel.toOption ==> Some("cancel")
    }
    test("supportDefaultClassName") {
      Draggable(Draggable.props(), <.div("Abc")).props.defaultClassName.toOption ==> Some(
        "react-draggable"
      )
      val draggable =
        Draggable(Draggable.props(defaultClassName = "draggable"), <.div("Abc"))
      ReactTestUtils.withRenderedSync(draggable) { m =>
        val html =
          """<div class="draggable" style="transform: translate(0px,0px);">Abc</div>""".stripMargin
            .replaceAll("[\n\r]", "")
        m.outerHTML.assert(html)
      }
      draggable.props.defaultClassName.toOption ==> Some("draggable")
    }
    test("supportDefaultClassNameDragging") {
      Draggable(Draggable.props(), <.div("Abc")).props.defaultClassNameDragging.toOption ==> Some(
        "react-draggable-dragging"
      )
      val draggable =
        Draggable(Draggable.props(defaultClassNameDragging = "draggable"), <.div("Abc"))
      ReactTestUtils.withRenderedSync(draggable) { m =>
        val html =
          """<div class="react-draggable" style="transform: translate(0px,0px);">Abc</div>""".stripMargin
            .replaceAll("[\n\r]", "")
        m.outerHTML.assert(html)
      }
      draggable.props.defaultClassNameDragging.toOption ==> Some("draggable")
    }
    test("supportDefaultClassNameDragged") {
      Draggable(Draggable.props(), <.div("Abc")).props.defaultClassNameDragged.toOption ==> Some(
        "react-draggable-dragged"
      )
      val draggable =
        Draggable(Draggable.props(defaultClassNameDragged = "draggable"), <.div("Abc"))
      ReactTestUtils.withRenderedSync(draggable) { m =>
        val html =
          """<div class="react-draggable" style="transform: translate(0px,0px);">Abc</div>""".stripMargin
            .replaceAll("[\n\r]", "")
        m.outerHTML.assert(html)
      }
      draggable.props.defaultClassNameDragged.toOption ==> Some("draggable")
    }
    test("supportDefaultPosition") {
      Draggable(Draggable.props(), <.div("Abc")).props.defaultPosition.toOption
        .map(_.x) ==> Some(0)
      Draggable(Draggable.props(), <.div("Abc")).props.defaultPosition.toOption
        .map(_.y) ==> Some(0)
      val draggable =
        Draggable(Draggable.props(defaultPosition = ControlPosition(1, 2)), <.div("Abc"))
      ReactTestUtils.withRenderedSync(draggable) { m =>
        val html =
          """<div class="react-draggable" style="transform: translate(1px,2px);">Abc</div>""".stripMargin
            .replaceAll("[\n\r]", "")
        m.outerHTML.assert(html)
      }
      draggable.props.defaultPosition.toOption.map(_.x) ==> Some(1)
      draggable.props.defaultPosition.toOption.map(_.y) ==> Some(2)
    }
    test("supportDisabled") {
      Draggable(Draggable.props(), <.div("Abc")).props.disabled.toOption ==> Some(false)
      val draggable = Draggable(Draggable.props(disabled = true), <.div("Abc"))
      ReactTestUtils.withRenderedSync(draggable) { m =>
        val html =
          """<div class="react-draggable" style="transform: translate(0px,0px);">Abc</div>""".stripMargin
            .replaceAll("[\n\r]", "")
        m.outerHTML.assert(html)
      }
      draggable.props.disabled.toOption ==> Some(true)
    }
    test("supportGrid") {
      Draggable(Draggable.props(), <.div("Abc")).props.grid.toOption ==> None
      val draggable =
        Draggable(Draggable.props(grid = Grid(1, 2)), <.div("Abc"))
      ReactTestUtils.withRenderedSync(draggable) { m =>
        val html =
          """<div class="react-draggable" style="transform: translate(0px,0px);">Abc</div>""".stripMargin
            .replaceAll("[\n\r]", "")
        m.outerHTML.assert(html)
      }
      draggable.props.grid.toOption.map(_.apply(0)) ==> Some(1)
      draggable.props.grid.toOption.map(_.apply(1)) ==> Some(2)
    }
    test("supportHandle") {
      Draggable(Draggable.props(), <.div("Abc")).props.handle.toOption ==> None
      val draggable =
        Draggable(Draggable.props(handle = "handle"), <.div("Abc"))
      ReactTestUtils.withRenderedSync(draggable) { m =>
        val html =
          """<div class="react-draggable" style="transform: translate(0px,0px);">Abc</div>""".stripMargin
            .replaceAll("[\n\r]", "")
        m.outerHTML.assert(html)
      }
      draggable.props.handle.toOption ==> Some("handle")
    }
    test("supportOffsetParent") {
      Draggable(Draggable.props(), <.div("Abc")).props.offsetParent.toOption ==> None
    }
    test("supportOnMouseDown"):
      Draggable(Draggable.props(onMouseDown = (_: MouseEvent) => Callback.empty), <.div("Abc"))
    test("supportOnStart"):
      Draggable(Draggable.props(onStart = (_: MouseEvent, _: DraggableData) => Callback.empty),
                <.div("Abc")
      )
    test("supportOnDrag"):
      Draggable(Draggable.props(onDrag = (_: MouseEvent, _: DraggableData) => Callback.empty),
                <.div("Abc")
      )
    test("supportOnStop"):
      Draggable(Draggable.props(onStop = (_: MouseEvent, _: DraggableData) => Callback.empty),
                <.div("Abc")
      )
    test("supportPosition") {
      Draggable(Draggable.props(), <.div("Abc")).props.position.toOption ==> None
      val draggable =
        Draggable(Draggable.props(position = ControlPosition(1, 2)), <.div("Abc"))
      ReactTestUtils.withRenderedSync(draggable) { m =>
        val html =
          """<div class="react-draggable" style="transform: translate(1px,2px);">Abc</div>""".stripMargin
            .replaceAll("[\n\r]", "")
        m.outerHTML.assert(html)
      }
      draggable.props.position.toOption.map(_.x) ==> Some(1)
      draggable.props.position.toOption.map(_.y) ==> Some(2)
    }
    test("supportPositionOffset") {
      Draggable(Draggable.props(), <.div("Abc")).props.positionOffset.toOption ==> None
      val draggable =
        Draggable(Draggable.props(positionOffset = PositionOffsetControlPosition("10%", "20%")),
                  <.div("Abc")
        )
      ReactTestUtils.withRenderedSync(draggable) { m =>
        val html =
          """<div class="react-draggable" style="transform: translate(10%, 20%)translate(0px,0px);">Abc</div>""".stripMargin
            .replaceAll("[\n\r]", "")
        m.outerHTML.assert(html)
      }
      draggable.props.positionOffset.toOption.map(_.x) ==> Some("10%")
      draggable.props.positionOffset.toOption.map(_.y) ==> Some("20%")
    }
  }
}
