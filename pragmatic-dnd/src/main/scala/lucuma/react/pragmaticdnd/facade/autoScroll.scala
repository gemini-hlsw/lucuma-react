// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.pragmaticdnd.facade

import japgolly.scalajs.react.callback.Callback
import japgolly.scalajs.react.callback.CallbackTo
import lucuma.react.pragmaticdnd.facade.Edge
import lucuma.react.pragmaticdnd.facade.ElementDragPayload
import lucuma.react.pragmaticdnd.facade.Input
import org.scalajs.dom.HTMLElement

import scalajs.js
import scalajs.js.annotation.JSImport

@js.native
trait ElementGetFeedbackArgs[S] extends js.Object:
  var input: Input
  var source: ElementDragPayload[S]
  var element: HTMLElement

@js.native
trait WindowGetFeedbackArgs[S] extends js.Object:
  var input: Input
  var source: ElementDragPayload[S]

opaque type Axis = String
object Axis:
  val Vertical: Axis   = "vertical"
  val Horizontal: Axis = "horizontal"
  val All: Axis        = "all"

  extension (axis: Axis)
    def edges: List[Edge] =
      axis match
        case Vertical   => List(Edge.Top, Edge.Bottom)
        case Horizontal => List(Edge.Left, Edge.Right)
        case All        => List(Edge.Top, Edge.Bottom, Edge.Left, Edge.Right)

opaque type ScrollSpeed = String
object ScrollSpeed:
  val Standard: ScrollSpeed = "standard"
  val Fast: ScrollSpeed     = "fast"

@js.native
trait PublicConfig extends js.Object:
  var maxScrollSpeed: js.UndefOr[ScrollSpeed]

object PublicConfig:
  def apply(maxScrollSpeed: js.UndefOr[ScrollSpeed] = js.undefined): PublicConfig =
    val p = (new js.Object).asInstanceOf[PublicConfig]
    p.applyOrNot(maxScrollSpeed, (p, s) => p.maxScrollSpeed = s)
    p

@js.native
trait ElementAutoScrollArgs[S] extends js.Object:
  var element: HTMLElement
  var canScroll: js.UndefOr[js.Function1[ElementGetFeedbackArgs[S], Boolean]]
  var getAllowedAxis: js.UndefOr[js.Function1[ElementGetFeedbackArgs[S], Axis]]
  var getConfiguration: js.UndefOr[js.Function1[ElementGetFeedbackArgs[S], PublicConfig]]

object ElementAutoScrollArgs:
  def apply[S](
    element:          HTMLElement,
    canScroll:        js.UndefOr[ElementGetFeedbackArgs[S] => Boolean] = js.undefined,
    getAllowedAxis:   js.UndefOr[ElementGetFeedbackArgs[S] => Axis] = js.undefined,
    getConfiguration: js.UndefOr[ElementGetFeedbackArgs[S] => PublicConfig] = js.undefined
  ): ElementAutoScrollArgs[S] =
    val p = (new js.Object).asInstanceOf[ElementAutoScrollArgs[S]]
    p.element = element
    p.applyOrNot(canScroll, (p, f) => p.canScroll = f)
    p.applyOrNot(getAllowedAxis, (p, f) => p.getAllowedAxis = f)
    p.applyOrNot(getConfiguration, (p, f) => p.getConfiguration = f)
    p

@js.native
trait WindowAutoScrollArgs[S] extends js.Object:
  var canScroll: js.UndefOr[js.Function1[WindowGetFeedbackArgs[S], Boolean]]
  var getAllowedAxis: js.UndefOr[js.Function1[WindowGetFeedbackArgs[S], Axis]]
  var getConfiguration: js.UndefOr[js.Function1[WindowGetFeedbackArgs[S], PublicConfig]]

object WindowAutoScrollArgs:
  def apply[S](
    canScroll:        js.UndefOr[WindowGetFeedbackArgs[S] => Boolean] = js.undefined,
    getAllowedAxis:   js.UndefOr[WindowGetFeedbackArgs[S] => Axis] = js.undefined,
    getConfiguration: js.UndefOr[WindowGetFeedbackArgs[S] => PublicConfig] = js.undefined
  ): WindowAutoScrollArgs[S] =
    val p = (new js.Object).asInstanceOf[WindowAutoScrollArgs[S]]
    p.applyOrNot(canScroll, (p, f) => p.canScroll = f)
    p.applyOrNot(getAllowedAxis, (p, f) => p.getAllowedAxis = f)
    p.applyOrNot(getConfiguration, (p, f) => p.getConfiguration = f)
    p

@js.native
@JSImport("@atlaskit/pragmatic-drag-and-drop-auto-scroll/element", JSImport.Namespace)
object AutoScrollRaw extends js.Object:
  def autoScrollForElements[S](args:       ElementAutoScrollArgs[S]): js.Function0[Unit] = js.native
  def autoScrollWindowForElements[S](args: WindowAutoScrollArgs[S]): js.Function0[Unit]  = js.native

object AutoScroll:
  def forElement[S](
    element:          HTMLElement,
    canScroll:        js.UndefOr[ElementGetFeedbackArgs[S] => Boolean] = js.undefined,
    getAllowedAxis:   js.UndefOr[ElementGetFeedbackArgs[S] => Axis] = js.undefined,
    getConfiguration: js.UndefOr[ElementGetFeedbackArgs[S] => PublicConfig] = js.undefined
  ): CallbackTo[Callback] =
    CallbackTo:
      Callback.fromJsFn:
        AutoScrollRaw.autoScrollForElements:
          ElementAutoScrollArgs(element, canScroll, getAllowedAxis, getConfiguration)

  def forWindow[S](
    canScroll:        js.UndefOr[WindowGetFeedbackArgs[S] => Boolean] = js.undefined,
    getAllowedAxis:   js.UndefOr[WindowGetFeedbackArgs[S] => Axis] = js.undefined,
    getConfiguration: js.UndefOr[WindowGetFeedbackArgs[S] => PublicConfig] = js.undefined
  ): CallbackTo[Callback] =
    CallbackTo:
      Callback.fromJsFn:
        AutoScrollRaw.autoScrollWindowForElements:
          WindowAutoScrollArgs(canScroll, getAllowedAxis, getConfiguration)
