package react
package draggable

import japgolly.scalajs.react._
import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom.MouseEvent
import utest._

object DraggableTests extends TestSuite {
  val tests = Tests {
    'render - {
      val draggable = Draggable(Draggable.props(), <.div("Abc"))
      ReactTestUtils.withRenderedIntoDocument(draggable) { m =>
        val html =
          """<div class="react-draggable">Abc</div>""".stripMargin.replaceAll("[\n\r]", "")
        assert(m.outerHtmlScrubbed() == html)
      }
    }
    'supportAllowAnyClick - {
      Draggable(Draggable.props(), <.div("Abc")).props.allowAnyClick.toOption ==> Some(false)
      val draggable = Draggable(Draggable.props(allowAnyClick = true), <.div("Abc"))
      ReactTestUtils.withRenderedIntoDocument(draggable) { m =>
        val html =
          """<div class="react-draggable">Abc</div>""".stripMargin.replaceAll("[\n\r]", "")
        assert(m.outerHtmlScrubbed() == html)
      }
      draggable.props.allowAnyClick.toOption ==> Some(true)
    }
    'supportAxis - {
      Draggable(Draggable.props(), <.div("Abc")).props.axis.toOption ==> Some("both")
      val draggable = Draggable(Draggable.props(axis = Axis.X), <.div("Abc"))
      ReactTestUtils.withRenderedIntoDocument(draggable) { m =>
        val html =
          """<div class="react-draggable">Abc</div>""".stripMargin.replaceAll("[\n\r]", "")
        assert(m.outerHtmlScrubbed() == html)
      }
      draggable.props.axis.toOption ==> Some("x")
    }
    'boundsDefault - {
      Draggable(Draggable.props(), <.div("Abc")).props.bounds.toOption ==> Some(false)
    }
    'supportBounds1 - {
      val draggable = Draggable(Draggable.props(bounds = FalseBounds), <.div("Abc"))
      ReactTestUtils.withRenderedIntoDocument(draggable) { m =>
        val html =
          """<div class="react-draggable">Abc</div>""".stripMargin.replaceAll("[\n\r]", "")
        assert(m.outerHtmlScrubbed() == html)
      }
      draggable.props.bounds.toOption ==> Some(false)
    }
    'supportBounds2 - {
      val draggable = Draggable(Draggable.props(bounds = "bounds"), <.div("Abc"))
      ReactTestUtils.withRenderedIntoDocument(draggable) { m =>
        val html =
          """<div class="react-draggable">Abc</div>""".stripMargin.replaceAll("[\n\r]", "")
        assert(m.outerHtmlScrubbed() == html)
      }
      draggable.props.bounds.toOption ==> Some("bounds")
    }
    'supportBounds3 - {
      val draggable = Draggable(Draggable.props(bounds = DraggableBounds(1, 2, 3, 4)), <.div("Abc"))
      ReactTestUtils.withRenderedIntoDocument(draggable) { m =>
        val html =
          """<div class="react-draggable">Abc</div>""".stripMargin.replaceAll("[\n\r]", "")
        assert(m.outerHtmlScrubbed() == html)
      }
      draggable.props.bounds.toOption.map(_.asInstanceOf[DraggableBounds].left) ==> Some(1)
    }
    'supportCancel - {
      Draggable(Draggable.props(), <.div("Abc")).props.cancel.toOption ==> Some(null)
      val draggable = Draggable(Draggable.props(cancel = "cancel"), <.div("Abc"))
      ReactTestUtils.withRenderedIntoDocument(draggable) { m =>
        val html =
          """<div class="react-draggable">Abc</div>""".stripMargin.replaceAll("[\n\r]", "")
        assert(m.outerHtmlScrubbed() == html)
      }
      draggable.props.cancel.toOption ==> Some("cancel")
    }
    'supportDefaultClassName - {
      Draggable(Draggable.props(), <.div("Abc")).props.defaultClassName.toOption ==> Some("react-draggable")
      val draggable = Draggable(Draggable.props(defaultClassName = "draggable"), <.div("Abc"))
      ReactTestUtils.withRenderedIntoDocument(draggable) { m =>
        val html =
          """<div class="draggable">Abc</div>""".stripMargin.replaceAll("[\n\r]", "")
        assert(m.outerHtmlScrubbed() == html)
      }
      draggable.props.defaultClassName.toOption ==> Some("draggable")
    }
    'supportDefaultClassNameDragging - {
      Draggable(Draggable.props(), <.div("Abc")).props.defaultClassNameDragging.toOption ==> Some("react-draggable-dragging")
      val draggable = Draggable(Draggable.props(defaultClassNameDragging = "draggable"), <.div("Abc"))
      ReactTestUtils.withRenderedIntoDocument(draggable) { m =>
        val html =
          """<div class="react-draggable">Abc</div>""".stripMargin.replaceAll("[\n\r]", "")
        assert(m.outerHtmlScrubbed() == html)
      }
      draggable.props.defaultClassNameDragging.toOption ==> Some("draggable")
    }
    'supportDefaultClassNameDragged - {
      Draggable(Draggable.props(), <.div("Abc")).props.defaultClassNameDragged.toOption ==> Some("react-draggable-dragged")
      val draggable = Draggable(Draggable.props(defaultClassNameDragged = "draggable"), <.div("Abc"))
      ReactTestUtils.withRenderedIntoDocument(draggable) { m =>
        val html =
          """<div class="react-draggable">Abc</div>""".stripMargin.replaceAll("[\n\r]", "")
        assert(m.outerHtmlScrubbed() == html)
      }
      draggable.props.defaultClassNameDragged.toOption ==> Some("draggable")
    }
    'supportDefaultPosition - {
      Draggable(Draggable.props(), <.div("Abc")).props.defaultPosition.toOption.map(_.x) ==> Some(0)
      Draggable(Draggable.props(), <.div("Abc")).props.defaultPosition.toOption.map(_.y) ==> Some(0)
      val draggable = Draggable(Draggable.props(defaultPosition = ControlPosition(1, 2)), <.div("Abc"))
      ReactTestUtils.withRenderedIntoDocument(draggable) { m =>
        val html =
          """<div class="react-draggable">Abc</div>""".stripMargin.replaceAll("[\n\r]", "")
        assert(m.outerHtmlScrubbed() == html)
      }
      draggable.props.defaultPosition.toOption.map(_.x) ==> Some(1)
      draggable.props.defaultPosition.toOption.map(_.y) ==> Some(2)
    }
    'supportDisabled - {
      Draggable(Draggable.props(), <.div("Abc")).props.disabled.toOption ==> Some(false)
      val draggable = Draggable(Draggable.props(disabled = true), <.div("Abc"))
      ReactTestUtils.withRenderedIntoDocument(draggable) { m =>
        val html =
          """<div class="react-draggable">Abc</div>""".stripMargin.replaceAll("[\n\r]", "")
        assert(m.outerHtmlScrubbed() == html)
      }
      draggable.props.disabled.toOption ==> Some(true)
    }
    'supportGrid - {
      Draggable(Draggable.props(), <.div("Abc")).props.grid.toOption ==> Some(null)
      val draggable = Draggable(Draggable.props(grid = Grid(1, 2)), <.div("Abc"))
      ReactTestUtils.withRenderedIntoDocument(draggable) { m =>
        val html =
          """<div class="react-draggable">Abc</div>""".stripMargin.replaceAll("[\n\r]", "")
        assert(m.outerHtmlScrubbed() == html)
      }
      draggable.props.grid.toOption.map(_.apply(0)) ==> Some(1)
      draggable.props.grid.toOption.map(_.apply(1)) ==> Some(2)
    }
    'supportHandle - {
      Draggable(Draggable.props(), <.div("Abc")).props.handle.toOption ==> Some(null)
      val draggable = Draggable(Draggable.props(handle = "handle"), <.div("Abc"))
      ReactTestUtils.withRenderedIntoDocument(draggable) { m =>
        val html =
          """<div class="react-draggable">Abc</div>""".stripMargin.replaceAll("[\n\r]", "")
        assert(m.outerHtmlScrubbed() == html)
      }
      draggable.props.handle.toOption ==> Some("handle")
    }
    'supportOffsetParent - {
      Draggable(Draggable.props(), <.div("Abc")).props.offsetParent.toOption ==> Some(null)
    }
    'supportOnMouseDown - {
      Draggable(Draggable.props(onMouseDown = (m: MouseEvent) => Callback.empty), <.div("Abc"))
    }
    'supportOnStart - {
      Draggable(Draggable.props(onStart = (m: MouseEvent, d: DraggableData) => Callback.empty), <.div("Abc"))
    }
    'supportOnDrag - {
      Draggable(Draggable.props(onDrag = (m: MouseEvent, d: DraggableData) => Callback.empty), <.div("Abc"))
    }
    'supportOnStop - {
      Draggable(Draggable.props(onStop = (m: MouseEvent, d: DraggableData) => Callback.empty), <.div("Abc"))
    }
    'supportPosition - {
      Draggable(Draggable.props(), <.div("Abc")).props.position.toOption ==> Some(null)
      val draggable = Draggable(Draggable.props(position = ControlPosition(1, 2)), <.div("Abc"))
      ReactTestUtils.withRenderedIntoDocument(draggable) { m =>
        val html =
          """<div class="react-draggable">Abc</div>""".stripMargin.replaceAll("[\n\r]", "")
        assert(m.outerHtmlScrubbed() == html)
      }
      draggable.props.position.toOption.map(_.x) ==> Some(1)
      draggable.props.position.toOption.map(_.y) ==> Some(2)
    }
  }
}
