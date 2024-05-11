// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.primereact

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.common.*
import lucuma.typed.primereact.components.Tooltip as CTooltip
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

case class Tooltip(
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
  onBeforeHide:   js.UndefOr[TooltipEvent => Callback] = js.undefined,
  onBeforeShow:   js.UndefOr[TooltipEvent => Callback] = js.undefined,
  onHide:         js.UndefOr[TooltipEvent => Callback] = js.undefined,
  onShow:         js.UndefOr[TooltipEvent => Callback] = js.undefined,
  position:       js.UndefOr[Tooltip.Position] = js.undefined, // default: right
  showDelay:      js.UndefOr[Int] = js.undefined,              // default: 0
  showEvent:      js.UndefOr[String] = js.undefined,           // default: mouseenter
  showOnDisabled: js.UndefOr[Boolean] = js.undefined,          // default: false
  // string | string[] | HTMLElement | RefObject<HTMLElement>
  target:         js.UndefOr[String] = js.undefined,           // query selector
  targetCss:      js.UndefOr[Css] = js.undefined,              // query selector
  updateDelay:    js.UndefOr[Int] = js.undefined,              // default: 0
  modifiers:      Seq[TagMod] = Seq.empty
) extends ReactFnPropsWithChildren(Tooltip.component) {
  def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)

  // You can add content to the tag as children without this method, but if you want
  // to add event handlers or other attributes, you'll need to use the modifiers.
  def withMods(mods: TagMod*) = addModifiers(mods)
}

object Tooltip:
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

  private val component =
    ScalaFnComponent
      .withHooks[Tooltip]
      .withPropsChildren
      .render: (props, children) =>
        CTooltip
          .applyOrNot(props.appendTo, (c, p) => c.appendTo(p.toJs))
          .applyOrNot(props.at, _.at(_))
          .applyOrNot(props.autoHide, _.autoHide(_))
          .applyOrNot(props.autoZIndex, _.autoZIndex(_))
          .applyOrNot(props.baseZIndex, _.baseZIndex(_))
          .applyOrNot(props.clazz, (c, p) => c.className(p.htmlClass))
          // ST facade erroneously defines content as String when it should be a ReactNode
          .applyOrNot(props.content, (c, p) => c.content(p.rawNode.asInstanceOf[String]))
          .applyOrNot(props.disabled, _.disabled(_))
          .applyOrNot(props.event, (c, p) => c.event(p.toJs))
          .applyOrNot(props.hideDelay, _.hideDelay(_))
          .applyOrNot(props.hideEvent, _.hideEvent(_))
          .applyOrNot(props.id, _.id(_))
          .applyOrNot(props.mouseTrack, _.mouseTrack(_))
          .applyOrNot(props.mouseTrackLeft, _.mouseTrackLeft(_))
          .applyOrNot(props.mouseTrackTop, _.mouseTrackTop(_))
          .applyOrNot(props.my, _.my(_))
          .applyOrNot(props.onBeforeHide, _.onBeforeHide(_))
          .applyOrNot(props.onBeforeShow, _.onBeforeShow(_))
          .applyOrNot(props.onHide, _.onHide(_))
          .applyOrNot(props.onShow, _.onShow(_))
          .applyOrNot(props.position, (c, p) => c.position(p.toJs))
          .applyOrNot(props.showDelay, _.showDelay(_))
          .applyOrNot(props.showEvent, _.showEvent(_))
          .applyOrNot(props.showOnDisabled, _.showOnDisabled(_))
          .applyOrNot(props.target, _.target(_))
          .applyOrNot(props.targetCss, (c, p) => c.target(p.querySelector))
          .applyOrNot(props.updateDelay, _.updateDelay(_))(
            props.modifiers.toTagMod,
            children
          )
