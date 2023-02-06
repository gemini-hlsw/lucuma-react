// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.ReactEventFrom
import org.scalajs.dom.Element
import org.scalajs.dom.HTMLElement
import lucuma.typed.primereact.primereactStrings.both
import lucuma.typed.primereact.primereactStrings.bottom
import lucuma.typed.primereact.primereactStrings.focus
import lucuma.typed.primereact.primereactStrings.hover
import lucuma.typed.primereact.primereactStrings.left
import lucuma.typed.primereact.primereactStrings.right
import lucuma.typed.primereact.primereactStrings.self
import lucuma.typed.primereact.primereactStrings.top

import scalajs.js

@js.native
trait TooltipEventParams extends js.Object:
  val originalEvent: ReactEventFrom[Element]
  val target: HTMLElement

@js.native
trait TooltipOptions extends js.Object:
  var appendTo: js.UndefOr[self | HTMLElement]                         = js.native
  var at: js.UndefOr[String]                                           = js.native
  var autoHide: js.UndefOr[Boolean]                                    = js.native
  var autoZIndex: js.UndefOr[Boolean]                                  = js.native
  var baseZIndex: js.UndefOr[Int]                                      = js.native
  var className: js.UndefOr[String]                                    = js.native
  var disabled: js.UndefOr[Boolean]                                    = js.native
  var event: js.UndefOr[hover | focus | both]                          = js.native
  var hideDelay: js.UndefOr[Int]                                       = js.native
  var hideEvent: js.UndefOr[String]                                    = js.native
  var mouseTrack: js.UndefOr[Boolean]                                  = js.native
  var mouseTrackLeft: js.UndefOr[Int]                                  = js.native
  var mouseTrackTop: js.UndefOr[Int]                                   = js.native
  var my: js.UndefOr[String]                                           = js.native
  var onBeforeHide: js.UndefOr[js.Function1[TooltipEventParams, Unit]] = js.native
  var onBeforeShow: js.UndefOr[js.Function1[TooltipEventParams, Unit]] = js.native
  var onHide: js.UndefOr[js.Function1[TooltipEventParams, Unit]]       = js.native
  var onShow: js.UndefOr[js.Function1[TooltipEventParams, Unit]]       = js.native
  var position: js.UndefOr[top | bottom | left | right]                = js.native
  var showDelay: js.UndefOr[Int]                                       = js.native
  var showEvent: js.UndefOr[String]                                    = js.native
  var showOnDisabled: js.UndefOr[Boolean]                              = js.native
  var style: js.UndefOr[js.Object]                                     = js.native
  var updateDelay: js.UndefOr[Int]                                     = js.native

object TooltipOptions:
  enum AppendTo(val toJs: self | HTMLElement):
    case Self                          extends AppendTo(self)
    case Element(element: HTMLElement) extends AppendTo(element)

  enum Event(val toJs: hover | focus | both):
    case Hover extends Event(hover)
    case Focus extends Event(focus)
    case Both  extends Event(both)

  enum Position(val toJs: top | bottom | left | right):
    case Top    extends Position(top)
    case Bottom extends Position(bottom)
    case Left   extends Position(left)
    case Right  extends Position(right)

  def apply(
    appendTo:       js.UndefOr[TooltipOptions.AppendTo] = js.undefined,
    at:             js.UndefOr[String] = js.undefined,
    autoHide:       js.UndefOr[Boolean] = js.undefined,                 // default: true
    autoZIndex:     js.UndefOr[Boolean] = js.undefined,                 // default: true
    baseZIndex:     js.UndefOr[Int] = js.undefined,
    className:      js.UndefOr[String] = js.undefined,
    disabled:       js.UndefOr[Boolean] = js.undefined,
    event:          js.UndefOr[TooltipOptions.Event] = js.undefined,
    hideDelay:      js.UndefOr[Int] = js.undefined,                     // default: 0
    hideEvent:      js.UndefOr[String] = js.undefined,                  // default: mouseleave
    mouseTrack:     js.UndefOr[Boolean] = js.undefined,                 // default: mouseTrack
    mouseTrackLeft: js.UndefOr[Int] = js.undefined,                     // default: 5
    mouseTrackTop:  js.UndefOr[Int] = js.undefined,                     // default: 5
    my:             js.UndefOr[String] = js.undefined,
    onBeforeHide:   js.UndefOr[TooltipEventParams => Callback] = js.undefined,
    onBeforeShow:   js.UndefOr[TooltipEventParams => Callback] = js.undefined,
    onHide:         js.UndefOr[TooltipEventParams => Callback] = js.undefined,
    onShow:         js.UndefOr[TooltipEventParams => Callback] = js.undefined,
    position:       js.UndefOr[TooltipOptions.Position] = js.undefined, // default: right
    showDelay:      js.UndefOr[Int] = js.undefined,                     // default: 0
    showEvent:      js.UndefOr[String] = js.undefined,                  // default: mouseenter
    showOnDisabled: js.UndefOr[Boolean] = js.undefined,                 // default: false
    style:          js.UndefOr[js.Object] = js.undefined,
    updateDelay:    js.UndefOr[Int] = js.undefined                      // default: 0
  ): TooltipOptions = {
    val p = (new js.Object).asInstanceOf[TooltipOptions]
    appendTo.foreach(v => p.appendTo = v.toJs)
    at.foreach(v => p.at = v)
    autoHide.foreach(v => p.autoHide = v)
    autoZIndex.foreach(v => p.autoZIndex = v)
    baseZIndex.foreach(v => p.baseZIndex = v)
    className.foreach(v => p.className = v)
    disabled.foreach(v => p.disabled = v)
    event.foreach(v => p.event = v.toJs)
    hideDelay.foreach(v => p.hideDelay = v)
    hideEvent.foreach(v => p.hideEvent = v)
    mouseTrack.foreach(v => p.mouseTrack = v)
    mouseTrackLeft.foreach(v => p.mouseTrackLeft = v)
    mouseTrackTop.foreach(v => p.mouseTrackTop = v)
    my.foreach(v => p.my = v)
    onBeforeHide.foreach(fn => p.onBeforeHide = e => fn(e).runNow())
    onBeforeShow.foreach(fn => p.onBeforeShow = e => fn(e).runNow())
    onHide.foreach(fn => p.onHide = e => fn(e).runNow())
    onShow.foreach(fn => p.onShow = e => fn(e).runNow())
    position.foreach(v => p.position = v.toJs)
    showDelay.foreach(v => p.showDelay = v)
    showEvent.foreach(v => p.showEvent = v)
    showOnDisabled.foreach(v => p.showOnDisabled = v)
    style.foreach(v => p.style = v)
    updateDelay.foreach(v => p.updateDelay = v)
    p
  }
