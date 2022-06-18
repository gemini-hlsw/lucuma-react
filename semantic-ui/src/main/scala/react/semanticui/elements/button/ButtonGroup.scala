// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.elements.button

import scala.scalajs.js
import js.annotation._
import js.|
import js.JSConverters._
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.VdomNode
import react.common._
import react.semanticui.{raw => suiraw}
import react.semanticui._
import japgolly.scalajs.react.vdom.TagMod

final case class ButtonGroup(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  attached:               MyUndefOr[Boolean | String] = MyUndefOr.undefined,
  basic:                  MyUndefOr[Boolean] = MyUndefOr.undefined,
  buttons:                MyUndefOr[Seq[Button]] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  color:                  MyUndefOr[SemanticColor] = MyUndefOr.undefined,
  compact:                MyUndefOr[Boolean] = MyUndefOr.undefined,
  content:                MyUndefOr[Seq[VdomNode]] = MyUndefOr.undefined,
  floated:                MyUndefOr[SemanticFloat] = MyUndefOr.undefined,
  fluid:                  MyUndefOr[Boolean] = MyUndefOr.undefined,
  icon:                   MyUndefOr[Boolean] = MyUndefOr.undefined,
  inverted:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  labeled:                MyUndefOr[Boolean] = MyUndefOr.undefined,
  negative:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  positive:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  primary:                MyUndefOr[Boolean] = MyUndefOr.undefined,
  secondary:              MyUndefOr[Boolean] = MyUndefOr.undefined,
  size:                   MyUndefOr[SemanticSize] = MyUndefOr.undefined,
  toggle:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  vertical:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  widths:                 MyUndefOr[SemanticWidth] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[ButtonGroup.ButtonGroupProps, ButtonGroup] {
  override protected def cprops                     = ButtonGroup.props(this)
  override protected val component                  = ButtonGroup.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object ButtonGroup {
  @js.native
  @JSImport("semantic-ui-react", "ButtonGroup")
  object RawComponent extends js.Object

  @js.native
  trait ButtonGroupProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: MyUndefOr[AsT] = js.native

    /** Groups can be attached to other content. */
    var attached: MyUndefOr[Boolean | String] = js.native

    /** Groups can be less pronounced. */
    var basic: MyUndefOr[Boolean] = js.native

    /** Array of shorthand ButtonGroup values. */
    var buttons: MyUndefOr[js.Array[Button.ButtonProps]] = js.native

    /** Primary content. */
    var children: MyUndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: MyUndefOr[String] = js.native

    /** Groups can have a shared color. */
    var color: MyUndefOr[suiraw.SemanticCOLORS] = js.native

    /** Groups can reduce their padding to fit into tighter spaces. */
    var compact: MyUndefOr[Boolean] = js.native

    /** Shorthand for primary content. */
    var content: MyUndefOr[js.Array[suiraw.SemanticShorthandContent]] = js.native

    /** Groups can be aligned to the left or right of its container. */
    var floated: MyUndefOr[suiraw.SemanticFLOATS] = js.native

    /** Groups can take the width of their container. */
    var fluid: MyUndefOr[Boolean] = js.native

    /** Groups can be formatted as icons. */
    var icon: MyUndefOr[Boolean] = js.native

    /** Groups can be formatted to appear on dark backgrounds. */
    var inverted: MyUndefOr[Boolean] = js.native

    /** Groups can be formatted as labeled icon buttons. */
    var labeled: MyUndefOr[Boolean] = js.native

    /** Groups can hint towards a negative consequence. */
    var negative: MyUndefOr[Boolean] = js.native

    /** Groups can hint towards a positive consequence. */
    var positive: MyUndefOr[Boolean] = js.native

    /** Groups can be formatted to show different levels of emphasis. */
    var primary: MyUndefOr[Boolean] = js.native

    /** Groups can be formatted to show different levels of emphasis. */
    var secondary: MyUndefOr[Boolean] = js.native

    /** Groups can have different sizes. */
    var size: MyUndefOr[suiraw.SemanticSIZES] = js.native

    /** Groups can be formatted to toggle on and off. */
    var toggle: MyUndefOr[Boolean] = js.native

    /** Groups can be formatted to appear vertically. */
    var vertical: MyUndefOr[Boolean] = js.native

    /** Groups can have their widths divided evenly. */
    var widths: MyUndefOr[suiraw.SemanticWIDTHS] = js.native
  }

  def props(q: ButtonGroup): ButtonGroupProps =
    rawprops(
      q.as,
      q.attached,
      q.basic,
      q.buttons,
      q.className,
      q.clazz,
      q.color,
      q.compact,
      q.content,
      q.floated,
      q.fluid,
      q.icon,
      q.inverted,
      q.labeled,
      q.negative,
      q.positive,
      q.primary,
      q.secondary,
      q.size,
      q.toggle,
      q.vertical,
      q.widths
    )

  def rawprops(
    as:        MyUndefOr[AsC] = MyUndefOr.undefined,
    attached:  MyUndefOr[Boolean | String] = MyUndefOr.undefined,
    basic:     MyUndefOr[Boolean] = MyUndefOr.undefined,
    buttons:   MyUndefOr[Seq[Button]] = MyUndefOr.undefined,
    className: MyUndefOr[String] = MyUndefOr.undefined,
    clazz:     MyUndefOr[Css] = MyUndefOr.undefined,
    color:     MyUndefOr[SemanticColor] = MyUndefOr.undefined,
    compact:   MyUndefOr[Boolean] = MyUndefOr.undefined,
    content:   MyUndefOr[Seq[VdomNode]] = MyUndefOr.undefined,
    floated:   MyUndefOr[SemanticFloat] = MyUndefOr.undefined,
    fluid:     MyUndefOr[Boolean] = MyUndefOr.undefined,
    icon:      MyUndefOr[Boolean] = MyUndefOr.undefined,
    inverted:  MyUndefOr[Boolean] = MyUndefOr.undefined,
    labeled:   MyUndefOr[Boolean] = MyUndefOr.undefined,
    negative:  MyUndefOr[Boolean] = MyUndefOr.undefined,
    positive:  MyUndefOr[Boolean] = MyUndefOr.undefined,
    primary:   MyUndefOr[Boolean] = MyUndefOr.undefined,
    secondary: MyUndefOr[Boolean] = MyUndefOr.undefined,
    size:      MyUndefOr[SemanticSize] = MyUndefOr.undefined,
    toggle:    MyUndefOr[Boolean] = MyUndefOr.undefined,
    vertical:  MyUndefOr[Boolean] = MyUndefOr.undefined,
    widths:    MyUndefOr[SemanticWidth] = MyUndefOr.undefined
  ): ButtonGroupProps = {
    val p = as.toJsObject[ButtonGroupProps]
    as.toJs.foreach(v => p.as = v)
    attached.foreach(v => p.attached = v)
    basic.foreach(v => p.basic = v)
    buttons.map(x => x.map(btn => btn.props).toJSArray).foreach(v => p.buttons = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    color.toJs.foreach(v => p.color = v)
    compact.foreach(v => p.compact = v)
    content.map(_.map(_.rawNode).toJSArray).foreach(v => p.content = v)
    floated.toJs.foreach(v => p.floated = v)
    icon.foreach(v => p.icon = v)
    inverted.foreach(v => p.inverted = v)
    labeled.foreach(v => p.labeled = v)
    negative.foreach(v => p.negative = v)
    positive.foreach(v => p.positive = v)
    primary.foreach(v => p.primary = v)
    secondary.foreach(v => p.secondary = v)
    size.toJs.foreach(v => p.size = v)
    toggle.foreach(v => p.toggle = v)
    vertical.foreach(v => p.vertical = v)
    widths.toJs.foreach(v => p.widths = v)
    p
  }

  private val component =
    JsComponent[ButtonGroupProps, Children.Varargs, Null](RawComponent)

  def apply(button: Button, buttons: Button*): ButtonGroup =
    new ButtonGroup(buttons = button +: buttons)

  def apply(modifiers: TagMod*): ButtonGroup =
    new ButtonGroup(modifiers = modifiers)
}
