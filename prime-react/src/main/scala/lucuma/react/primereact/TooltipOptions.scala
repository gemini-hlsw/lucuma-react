// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.primereact

import japgolly.scalajs.react.Callback
import lucuma.typed.primereact.primereactStrings.both
import lucuma.typed.primereact.primereactStrings.bottom
import lucuma.typed.primereact.primereactStrings.focus
import lucuma.typed.primereact.primereactStrings.hover
import lucuma.typed.primereact.primereactStrings.left
import lucuma.typed.primereact.primereactStrings.right
import lucuma.typed.primereact.primereactStrings.self
import lucuma.typed.primereact.primereactStrings.top
import lucuma.typed.primereact.tooltipTooltipoptionsMod.TooltipEvent
import org.scalajs.dom.HTMLElement

import scalajs.js

@js.native
trait TooltipOptions extends js.Object:
  var appendTo: js.UndefOr[self | HTMLElement]                   = js.native
  var at: js.UndefOr[String]                                     = js.native
  var autoHide: js.UndefOr[Boolean]                              = js.native
  var autoZIndex: js.UndefOr[Boolean]                            = js.native
  var baseZIndex: js.UndefOr[Int]                                = js.native
  var className: js.UndefOr[String]                              = js.native
  var disabled: js.UndefOr[Boolean]                              = js.native
  var event: js.UndefOr[hover | focus | both]                    = js.native
  var hideDelay: js.UndefOr[Int]                                 = js.native
  var hideEvent: js.UndefOr[String]                              = js.native
  var mouseTrack: js.UndefOr[Boolean]                            = js.native
  var mouseTrackLeft: js.UndefOr[Int]                            = js.native
  var mouseTrackTop: js.UndefOr[Int]                             = js.native
  var my: js.UndefOr[String]                                     = js.native
  var onBeforeHide: js.UndefOr[js.Function1[TooltipEvent, Unit]] = js.native
  var onBeforeShow: js.UndefOr[js.Function1[TooltipEvent, Unit]] = js.native
  var onHide: js.UndefOr[js.Function1[TooltipEvent, Unit]]       = js.native
  var onShow: js.UndefOr[js.Function1[TooltipEvent, Unit]]       = js.native
  var position: js.UndefOr[top | bottom | left | right]          = js.native
  var showDelay: js.UndefOr[Int]                                 = js.native
  var showEvent: js.UndefOr[String]                              = js.native
  var showOnDisabled: js.UndefOr[Boolean]                        = js.native
  var style: js.UndefOr[js.Object]                               = js.native
  var updateDelay: js.UndefOr[Int]                               = js.native

object TooltipOptions:
  val Top: TooltipOptions    = TooltipOptions(position = Tooltip.Position.Top)
  val Bottom: TooltipOptions = TooltipOptions(position = Tooltip.Position.Bottom)
  val Left: TooltipOptions   = TooltipOptions(position = Tooltip.Position.Left)
  val Right: TooltipOptions  = TooltipOptions(position = Tooltip.Position.Right)

  def apply(
    appendTo:       js.UndefOr[Tooltip.AppendTo] = js.undefined,
    at:             js.UndefOr[String] = js.undefined,
    autoHide:       js.UndefOr[Boolean] = js.undefined,          // default: true
    autoZIndex:     js.UndefOr[Boolean] = js.undefined,          // default: true
    baseZIndex:     js.UndefOr[Int] = js.undefined,
    className:      js.UndefOr[String] = js.undefined,
    disabled:       js.UndefOr[Boolean] = js.undefined,
    event:          js.UndefOr[Tooltip.Event] = js.undefined,
    hideDelay:      js.UndefOr[Int] = js.undefined,              // default: 0
    hideEvent:      js.UndefOr[String] = js.undefined,           // default: mouseleave
    mouseTrack:     js.UndefOr[Boolean] = js.undefined,          // default: mouseTrack
    mouseTrackLeft: js.UndefOr[Int] = js.undefined,              // default: 5
    mouseTrackTop:  js.UndefOr[Int] = js.undefined,              // default: 5
    my:             js.UndefOr[String] = js.undefined,
    onBeforeHide:   js.UndefOr[TooltipEvent => Callback] = js.undefined,
    onBeforeShow:   js.UndefOr[TooltipEvent => Callback] = js.undefined,
    onHide:         js.UndefOr[TooltipEvent => Callback] = js.undefined,
    onShow:         js.UndefOr[TooltipEvent => Callback] = js.undefined,
    position:       js.UndefOr[Tooltip.Position] = js.undefined, // default: right
    showDelay:      js.UndefOr[Int] = js.undefined,              // default: 0
    showEvent:      js.UndefOr[String] = js.undefined,           // default: mouseenter
    showOnDisabled: js.UndefOr[Boolean] = js.undefined,          // default: false
    style:          js.UndefOr[js.Object] = js.undefined,
    updateDelay:    js.UndefOr[Int] = js.undefined               // default: 0
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
