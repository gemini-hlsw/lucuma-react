// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.elements.button

import scala.scalajs.js
import js.annotation._
import js.|
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import react.common._
import react.semanticui.{raw => suiraw}
import react.semanticui._
import react.semanticui.elements.label.Label.LabelProps
import react.semanticui.elements.label.Label
import react.semanticui.elements.icon.Icon
import react.semanticui.elements.icon.Icon.IconProps

import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.vdom.VdomNode

final case class Button(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  active:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  animated:               MyUndefOr[ButtonAnimated] = MyUndefOr.undefined,
  attached:               MyUndefOr[ButtonAttached] = MyUndefOr.undefined,
  basic:                  MyUndefOr[Boolean] = MyUndefOr.undefined,
  circular:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  color:                  MyUndefOr[SemanticColor] = MyUndefOr.undefined,
  compact:                MyUndefOr[Boolean] = MyUndefOr.undefined,
  content:                MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
  disabled:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  floated:                MyUndefOr[SemanticFloat] = MyUndefOr.undefined,
  fluid:                  MyUndefOr[Boolean] = MyUndefOr.undefined,
  icon:                   MyUndefOr[ShorthandSB[Icon]] = MyUndefOr.undefined,
  inverted:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  label:                  MyUndefOr[ShorthandS[Label]] = MyUndefOr.undefined,
  labelPosition:          MyUndefOr[LabelPosition] = MyUndefOr.undefined,
  loading:                MyUndefOr[Boolean] = MyUndefOr.undefined,
  negative:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  onClickE:               MyUndefOr[Button.OnClick] = MyUndefOr.undefined,
  onClick:                MyUndefOr[Callback] = MyUndefOr.undefined,
  positive:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  primary:                MyUndefOr[Boolean] = MyUndefOr.undefined,
  role:                   MyUndefOr[String] = MyUndefOr.undefined,
  secondary:              MyUndefOr[Boolean] = MyUndefOr.undefined,
  size:                   MyUndefOr[SemanticSize] = MyUndefOr.undefined,
  tabIndex:               MyUndefOr[TabIndex] = MyUndefOr.undefined,
  toggle:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[Button.ButtonProps, Button] {
  override protected def cprops                     = Button.props(this)
  override protected val component                  = Button.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object Button {
  type OnClick = (ReactMouseEvent, ButtonProps) => Callback

  @js.native
  @JSImport("semantic-ui-react", "Button")
  object RawComponent extends js.Object

  @js.native
  trait ButtonProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    var as: MyUndefOr[AsT]                                                   = js.native
    var active: MyUndefOr[Boolean]                                           = js.native
    var animated: MyUndefOr[Boolean | String]                                = js.native
    var attached: MyUndefOr[Boolean | String]                                = js.native
    var basic: MyUndefOr[Boolean]                                            = js.native
    var children: MyUndefOr[React.Node]                                      = js.native
    var circular: MyUndefOr[Boolean]                                         = js.native
    var className: MyUndefOr[String]                                         = js.native
    var color: MyUndefOr[suiraw.SemanticCOLORS]                              = js.native
    var compact: MyUndefOr[Boolean]                                          = js.native
    var content: MyUndefOr[suiraw.SemanticShorthandContent]                  = js.native
    var disabled: MyUndefOr[Boolean]                                         = js.native
    var floated: MyUndefOr[suiraw.SemanticFLOATS]                            = js.native
    var fluid: MyUndefOr[Boolean]                                            = js.native
    var icon: MyUndefOr[suiraw.SemanticShorthandItemSB[IconProps]]           = js.native
    var inverted: MyUndefOr[Boolean]                                         = js.native
    var label: MyUndefOr[suiraw.SemanticShorthandItemS[LabelProps]]          = js.native
    var labelPosition: MyUndefOr[String]                                     = js.native
    var loading: MyUndefOr[Boolean]                                          = js.native
    var negative: MyUndefOr[Boolean]                                         = js.native
    var onClick: MyUndefOr[js.Function2[ReactMouseEvent, ButtonProps, Unit]] = js.native
    var positive: MyUndefOr[Boolean]                                         = js.native
    var primary: MyUndefOr[Boolean]                                          = js.native
    var role: MyUndefOr[String]                                              = js.native
    var secondary: MyUndefOr[Boolean]                                        = js.native
    var size: MyUndefOr[suiraw.SemanticSIZES]                                = js.native
    var tabIndex: MyUndefOr[Double | String]                                 = js.native
    var toggle: MyUndefOr[Boolean]                                           = js.native
  }

  def props(q: Button): ButtonProps =
    rawprops(
      q.as,
      q.active,
      q.animated,
      q.attached,
      q.basic,
      q.circular,
      q.className,
      q.clazz,
      q.color,
      q.compact,
      q.content,
      q.disabled,
      q.floated,
      q.fluid,
      q.icon,
      q.inverted,
      q.label,
      q.labelPosition,
      q.loading,
      q.negative,
      q.onClickE,
      q.onClick,
      q.positive,
      q.primary,
      q.role,
      q.secondary,
      q.size,
      q.tabIndex,
      q.toggle
    )

  def rawprops(
    as:            MyUndefOr[AsC] = MyUndefOr.undefined,
    active:        MyUndefOr[Boolean] = MyUndefOr.undefined,
    animated:      MyUndefOr[ButtonAnimated] = MyUndefOr.undefined,
    attached:      MyUndefOr[ButtonAttached] = MyUndefOr.undefined,
    basic:         MyUndefOr[Boolean] = MyUndefOr.undefined,
    circular:      MyUndefOr[Boolean] = MyUndefOr.undefined,
    className:     MyUndefOr[String] = MyUndefOr.undefined,
    clazz:         MyUndefOr[Css] = MyUndefOr.undefined,
    color:         MyUndefOr[SemanticColor] = MyUndefOr.undefined,
    compact:       MyUndefOr[Boolean] = MyUndefOr.undefined,
    content:       MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
    disabled:      MyUndefOr[Boolean] = MyUndefOr.undefined,
    floated:       MyUndefOr[SemanticFloat] = MyUndefOr.undefined,
    fluid:         MyUndefOr[Boolean] = MyUndefOr.undefined,
    icon:          MyUndefOr[ShorthandSB[Icon]] = MyUndefOr.undefined,
    inverted:      MyUndefOr[Boolean] = MyUndefOr.undefined,
    label:         MyUndefOr[ShorthandS[Label]] = MyUndefOr.undefined,
    labelPosition: MyUndefOr[LabelPosition] = MyUndefOr.undefined,
    loading:       MyUndefOr[Boolean] = MyUndefOr.undefined,
    negative:      MyUndefOr[Boolean] = MyUndefOr.undefined,
    onClickE:      MyUndefOr[OnClick] = MyUndefOr.undefined,
    onClick:       MyUndefOr[Callback] = MyUndefOr.undefined,
    positive:      MyUndefOr[Boolean] = MyUndefOr.undefined,
    primary:       MyUndefOr[Boolean] = MyUndefOr.undefined,
    role:          MyUndefOr[String] = MyUndefOr.undefined,
    secondary:     MyUndefOr[Boolean] = MyUndefOr.undefined,
    size:          MyUndefOr[SemanticSize] = MyUndefOr.undefined,
    tabIndex:      MyUndefOr[TabIndex] = MyUndefOr.undefined,
    toggle:        MyUndefOr[Boolean] = MyUndefOr.undefined
  ): ButtonProps = {
    val p = as.toJsObject[ButtonProps]
    as.toJs.foreach(v => p.as = v)
    active.foreach(v => p.active = v)
    animated.toJs.foreach(v => p.animated = v)
    attached.toJs.foreach(v => p.attached = v)
    basic.foreach(v => p.basic = v)
    circular.foreach(v => p.circular = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    color.toJs.foreach(v => p.color = v)
    compact.foreach(v => p.compact = v)
    content.toJs.foreach(v => p.content = v)
    disabled.foreach(v => p.disabled = v)
    floated.toJs.foreach(v => p.floated = v)
    fluid.foreach(v => p.fluid = v)
    icon.toJs.foreach(v => p.icon = v)
    inverted.foreach(v => p.inverted = v)
    label.toJs.foreach(v => p.label = v)
    labelPosition.toJs.foreach(v => p.labelPosition = v)
    loading.foreach(v => p.loading = v)
    negative.foreach(v => p.negative = v)
    (onClickE, onClick).toJs.foreach(v => p.onClick = v)
    positive.foreach(v => p.positive = v)
    primary.foreach(v => p.primary = v)
    role.foreach(v => p.role = v)
    secondary.foreach(v => p.secondary = v)
    size.toJs.foreach(v => p.size = v)
    tabIndex.foreach(v => p.tabIndex = v)
    toggle.foreach(v => p.toggle = v)
    p
  }

  private val component =
    JsComponent[ButtonProps, Children.Varargs, Null](RawComponent)

  def apply(modifiers: TagMod*): Button =
    Button(modifiers = modifiers)
}
