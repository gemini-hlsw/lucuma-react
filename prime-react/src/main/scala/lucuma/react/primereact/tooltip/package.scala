// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.primereact.tooltip

import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.vdom.TagOf
import japgolly.scalajs.react.vdom.TopNode
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.common.Css
import lucuma.react.primereact.Tooltip
import org.scalajs.dom.HTMLElement

import scalajs.js

/** Content to be displayed in tooltip. Default: null */
val tooltipContent = VdomAttr("data-pr-tooltip")

/** When present, it specifies that the tooltip should be hidden. Default: false */
val tooltipDisabled = VdomAttr("data-pr-disabled")

/** Style class of the tooltip. Default: null */
val tooltipClassName = VdomAttr("data-pr-classname")

/** Position of the tooltip. Default: right */
val tooltipPosition = VdomAttr("data-pr-position")

/**
 * Defines which position on the tooltip being positioned to align with the target element. Default:
 * null
 */
val tooltipMy = VdomAttr("data-pr-my")

/** Defines which position on the target element to align the positioned tooltip. Default: null */
val tooltipAt = VdomAttr("data-pr-at")

/** Event to trigger the tooltip. Default: hover */
val tooltipEvent = VdomAttr("data-pr-event")

/** Event to show the tooltip if the event property is empty. Default: mouseenter */
val tooltipShowEvent = VdomAttr("data-pr-showevent")

/** Event to hide the tooltip if the event property is empty. Default: mouseleave */
val tooltipHideEvent = VdomAttr("data-pr-hideevent")

/** Whether the tooltip will follow the mouse. Default: false */
val tooltipMouseTrack = VdomAttr("data-pr-mousetrack")

/**
 * Defines top position of the tooltip in relation to the mouse when the mouseTrack is enabled.
 * Default: 5
 */
val tooltipMouseTrackTop = VdomAttr("data-pr-mousetracktop")

/**
 * Defines left position of the tooltip in relation to the mouse when the mouseTrack is enabled.
 * Default: 5
 */
val tooltipMouseTrackLeft = VdomAttr("data-pr-mousetrackleft")

/** Delay to show the tooltip in milliseconds. Default: 0 */
val tooltipShowDelay = VdomAttr("data-pr-showdelay")

/** Delay to update the tooltip in milliseconds. Default: 0 */
val tooltipUpdateDelay = VdomAttr("data-pr-updatedelay")

/** Delay to hide the tooltip in milliseconds. Default: 0 */
val tooltipHideDelay = VdomAttr("data-pr-hidedelay")

/** Whether to hide tooltip when hovering over tooltip content. Default: true */
val tooltipAutoHide = VdomAttr("data-pr-autohide")

/** Whether to show tooltip for disabled elements. Default: false */
val tooltipShowOnDisabled = VdomAttr("data-pr-showondisabled")

extension [E <: TopNode](self: TagOf[E])
  def withTooltipOptions(
    content:        js.UndefOr[String] = js.undefined,
    disabled:       js.UndefOr[Boolean] = js.undefined,
    clazz:          js.UndefOr[Css] = js.undefined,
    position:       js.UndefOr[Tooltip.Position] = js.undefined,
    my:             js.UndefOr[String] = js.undefined,
    at:             js.UndefOr[String] = js.undefined,
    event:          js.UndefOr[Tooltip.Event] = js.undefined,
    showEvent:      js.UndefOr[String] = js.undefined,
    hideEvent:      js.UndefOr[String] = js.undefined,
    mouseTrack:     js.UndefOr[Boolean] = js.undefined,
    mouseTrackTop:  js.UndefOr[Int] = js.undefined,
    mouseTrackLeft: js.UndefOr[Int] = js.undefined,
    showDelay:      js.UndefOr[Int] = js.undefined,
    updateDelay:    js.UndefOr[Int] = js.undefined,
    hideDelay:      js.UndefOr[Int] = js.undefined,
    autoHide:       js.UndefOr[Boolean] = js.undefined,
    showOnDisabled: js.UndefOr[Boolean] = js.undefined
  ): TagOf[E] =
    self(
      content.map(tooltipContent := _).whenDefined,
      disabled.map(tooltipDisabled := _).whenDefined,
      clazz.map(tooltipClassName := _.htmlClass).whenDefined,
      position.map(tooltipPosition := _.toJs).whenDefined,
      my.map(tooltipMy := _).whenDefined,
      at.map(tooltipAt := _).whenDefined,
      event.map(tooltipEvent := _.toJs).whenDefined,
      showEvent.map(tooltipShowEvent := _).whenDefined,
      hideEvent.map(tooltipHideEvent := _).whenDefined,
      mouseTrack.map(tooltipMouseTrack := _).whenDefined,
      mouseTrackTop.map(tooltipMouseTrackTop := _).whenDefined,
      mouseTrackLeft.map(tooltipMouseTrackLeft := _).whenDefined,
      showDelay.map(tooltipShowDelay := _).whenDefined,
      updateDelay.map(tooltipUpdateDelay := _).whenDefined,
      hideDelay.map(tooltipHideDelay := _).whenDefined,
      autoHide.map(tooltipAutoHide := _).whenDefined,
      showOnDisabled.map(tooltipShowOnDisabled := _).whenDefined
    )

extension (self: TagOf[HTMLElement])
  def withTooltip(
    appendTo:       js.UndefOr[Tooltip.AppendTo] = js.undefined,
    at:             js.UndefOr[String] = js.undefined,
    autoHide:       js.UndefOr[Boolean] = js.undefined,          // default: true
    autoZIndex:     js.UndefOr[Boolean] = js.undefined,          // default: true
    baseZIndex:     js.UndefOr[Int] = js.undefined,
    clazz:          js.UndefOr[Css] = js.undefined,
    content:        js.UndefOr[VdomNode] = js.undefined,
    disabled:       js.UndefOr[Boolean] = js.undefined,
    event:          js.UndefOr[Tooltip.Event] = js.undefined,
    hideDelay:      js.UndefOr[Int] = js.undefined,              // default: 0
    hideEvent:      js.UndefOr[String] = js.undefined,           // default: mouseleave
    id:             js.UndefOr[String] = js.undefined,
    mouseTrack:     js.UndefOr[Boolean] = js.undefined,          // default: mouseTrack
    mouseTrackLeft: js.UndefOr[Int] = js.undefined,              // default: 5
    mouseTrackTop:  js.UndefOr[Int] = js.undefined,              // default: 5
    my:             js.UndefOr[String] = js.undefined,
    onBeforeHide:   js.UndefOr[Tooltip.EventRaw => Callback] = js.undefined,
    onBeforeShow:   js.UndefOr[Tooltip.EventRaw => Callback] = js.undefined,
    onHide:         js.UndefOr[Tooltip.EventRaw => Callback] = js.undefined,
    onShow:         js.UndefOr[Tooltip.EventRaw => Callback] = js.undefined,
    position:       js.UndefOr[Tooltip.Position] = js.undefined, // default: right
    showDelay:      js.UndefOr[Int] = js.undefined,              // default: 0
    showEvent:      js.UndefOr[String] = js.undefined,           // default: mouseenter
    showOnDisabled: js.UndefOr[Boolean] = js.undefined,          // default: false
    updateDelay:    js.UndefOr[Int] = js.undefined               // default: 0
  ): VdomElement = Tooltip.Fragment(
    appendTo = appendTo,
    at = at,
    autoHide = autoHide,
    autoZIndex = autoZIndex,
    baseZIndex = baseZIndex,
    clazz = clazz,
    content = content,
    disabled = disabled,
    event = event,
    hideDelay = hideDelay,
    hideEvent = hideEvent,
    id = id,
    mouseTrack = mouseTrack,
    mouseTrackLeft = mouseTrackLeft,
    mouseTrackTop = mouseTrackTop,
    my = my,
    onBeforeHide = onBeforeHide,
    onBeforeShow = onBeforeShow,
    onHide = onHide,
    onShow = onShow,
    position = position,
    showDelay = showDelay,
    showEvent = showEvent,
    showOnDisabled = showOnDisabled,
    updateDelay = updateDelay
  )(self)
