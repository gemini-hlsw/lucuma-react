// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.collections.form

import scala.scalajs.js
import scala.scalajs.js.|
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import react.common._
import react.semanticui._
import react.semanticui.elements.label.Label
import react.semanticui.elements.button._
import react.semanticui.elements.icon.Icon
import react.semanticui.elements.icon.Icon.IconProps
import react.semanticui.{raw => suiraw}
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.vdom.VdomNode

final case class FormButton(
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
  control:                MyUndefOr[String] = MyUndefOr.undefined,
  disabled:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  error:                  MyUndefOr[ShorthandB[Label]] = MyUndefOr.undefined,
  floated:                MyUndefOr[SemanticFloat] = MyUndefOr.undefined,
  fluid:                  MyUndefOr[Boolean] = MyUndefOr.undefined,
  icon:                   MyUndefOr[ShorthandSB[Icon]] = MyUndefOr.undefined,
  inline:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  inverted:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  label:                  MyUndefOr[ShorthandS[Label]] = MyUndefOr.undefined,
  labelPosition:          MyUndefOr[LabelPosition] = MyUndefOr.undefined,
  loading:                MyUndefOr[Boolean] = MyUndefOr.undefined,
  negative:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  onClick:                MyUndefOr[Callback] = MyUndefOr.undefined,
  onClickE:               MyUndefOr[Button.OnClick] = MyUndefOr.undefined,
  positive:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  primary:                MyUndefOr[Boolean] = MyUndefOr.undefined,
  required:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  role:                   MyUndefOr[Boolean] = MyUndefOr.undefined,
  secondary:              MyUndefOr[Boolean] = MyUndefOr.undefined,
  size:                   MyUndefOr[SemanticSize] = MyUndefOr.undefined,
  tabIndex:               MyUndefOr[TabIndex] = MyUndefOr.undefined,
  toggle:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  tpe:                    MyUndefOr[String] = MyUndefOr.undefined,
  width:                  MyUndefOr[SemanticWidth] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[FormButton.FormButtonProps, FormButton] {
  override protected def cprops                     = FormButton.props(this)
  override protected val component                  = FormButton.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object FormButton {

  @js.native
  @JSImport("semantic-ui-react", "FormButton")
  object RawComponent extends js.Object

  @js.native
  // This should be FormButtonProps extends FormField.FormFieldProps with Button.ButtonProps
  // But it collides with scala multiple inheritane rules, thus we just copy the props from both
  trait FormButtonProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: MyUndefOr[AsT]                                                          = js.native
    var active: MyUndefOr[Boolean]                                                  = js.native
    var animated: MyUndefOr[Boolean | String]                                       = js.native
    var attached: MyUndefOr[Boolean | String]                                       = js.native
    var basic: MyUndefOr[Boolean]                                                   = js.native
    var circular: MyUndefOr[Boolean]                                                = js.native
    var color: MyUndefOr[String]                                                    = js.native
    var compact: MyUndefOr[Boolean]                                                 = js.native
    var floated: MyUndefOr[suiraw.SemanticFLOATS]                                   = js.native
    var fluid: MyUndefOr[Boolean]                                                   = js.native
    var icon: MyUndefOr[suiraw.SemanticShorthandItemSB[IconProps]]                  = js.native
    var inverted: MyUndefOr[Boolean]                                                = js.native
    var labelPosition: MyUndefOr[String]                                            = js.native
    var loading: MyUndefOr[Boolean]                                                 = js.native
    var negative: MyUndefOr[Boolean]                                                = js.native
    var onClick: MyUndefOr[js.Function2[ReactMouseEvent, Button.ButtonProps, Unit]] = js.native
    var positive: MyUndefOr[Boolean]                                                = js.native
    var primary: MyUndefOr[Boolean]                                                 = js.native
    var role: MyUndefOr[Boolean]                                                    = js.native
    var secondary: MyUndefOr[Boolean]                                               = js.native
    var size: MyUndefOr[suiraw.SemanticSIZES]                                       = js.native
    var tabIndex: MyUndefOr[Double | String]                                        = js.native
    var toggle: MyUndefOr[Boolean]                                                  = js.native

    /** Primary content. */
    var children: MyUndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: MyUndefOr[String] = js.native

    /** Shorthand for primary content. */
    var content: MyUndefOr[suiraw.SemanticShorthandContent] =
      MyUndefOr.undefined

    /**
     * A form control component (i.e. Dropdown) or HTML tagName (i.e. 'input'). Extra FormField
     * props are passed to the control component. Mutually exclusive with children.
     */
    // control?: any
    var control: MyUndefOr[String]

    /** Individual fields may be disabled. */
    var disabled: MyUndefOr[Boolean] = js.native

    /** Individual fields may display an error state along with a message. */
    var error: MyUndefOr[suiraw.SemanticShorthandItemB[Label.LabelProps]] = js.native

    /** A field can have its label next to instead of above it. */
    var inline: MyUndefOr[Boolean] = js.native

    /** Mutually exclusive with children. */
    var label: MyUndefOr[suiraw.SemanticShorthandItemS[Label.LabelProps]] = js.native

    /** A field can show that input is mandatory.  Requires a label. */
    var required: MyUndefOr[Boolean] = js.native

    /** Passed to the control component (i.e. <input type='password' />) */
    var `type`: MyUndefOr[String] = js.native

    /** A field can specify its width in grid columns */
    var width: MyUndefOr[suiraw.SemanticWIDTHS] = js.native // | 'equal'
  }

  def props(q: FormButton): FormButtonProps =
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
      q.control,
      q.disabled,
      q.error,
      q.floated,
      q.fluid,
      q.icon,
      q.inline,
      q.inverted,
      q.label,
      q.labelPosition,
      q.loading,
      q.negative,
      q.onClick,
      q.onClickE,
      q.positive,
      q.primary,
      q.required,
      q.role,
      q.secondary,
      q.size,
      q.tabIndex,
      q.toggle,
      q.tpe,
      q.width
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
    control:       MyUndefOr[String] = MyUndefOr.undefined,
    disabled:      MyUndefOr[Boolean] = MyUndefOr.undefined,
    error:         MyUndefOr[ShorthandB[Label]] = MyUndefOr.undefined,
    floated:       MyUndefOr[SemanticFloat] = MyUndefOr.undefined,
    fluid:         MyUndefOr[Boolean] = MyUndefOr.undefined,
    icon:          MyUndefOr[ShorthandSB[Icon]] = MyUndefOr.undefined,
    inline:        MyUndefOr[Boolean] = MyUndefOr.undefined,
    inverted:      MyUndefOr[Boolean] = MyUndefOr.undefined,
    label:         MyUndefOr[ShorthandS[Label]] = MyUndefOr.undefined,
    labelPosition: MyUndefOr[LabelPosition] = MyUndefOr.undefined,
    loading:       MyUndefOr[Boolean] = MyUndefOr.undefined,
    negative:      MyUndefOr[Boolean] = MyUndefOr.undefined,
    onClick:       MyUndefOr[Callback] = MyUndefOr.undefined,
    onClickE:      MyUndefOr[Button.OnClick] = MyUndefOr.undefined,
    positive:      MyUndefOr[Boolean] = MyUndefOr.undefined,
    primary:       MyUndefOr[Boolean] = MyUndefOr.undefined,
    required:      MyUndefOr[Boolean] = MyUndefOr.undefined,
    role:          MyUndefOr[Boolean] = MyUndefOr.undefined,
    secondary:     MyUndefOr[Boolean] = MyUndefOr.undefined,
    size:          MyUndefOr[SemanticSize] = MyUndefOr.undefined,
    tabIndex:      MyUndefOr[TabIndex] = MyUndefOr.undefined,
    toggle:        MyUndefOr[Boolean] = MyUndefOr.undefined,
    tpe:           MyUndefOr[String] = MyUndefOr.undefined,
    width:         MyUndefOr[SemanticWidth] = MyUndefOr.undefined
  ): FormButtonProps = {
    val p = as.toJsObject[FormButtonProps]
    (className, clazz).toJs.foreach(v => p.className = v)
    (onClickE, onClick).toJs.foreach(v => p.onClick = v)
    active.foreach(v => p.active = v)
    animated.toJs.foreach(v => p.animated = v)
    as.toJs.foreach(v => p.as = v)
    attached.toJs.foreach(v => p.attached = v)
    basic.foreach(v => p.basic = v)
    circular.foreach(v => p.circular = v)
    color.toJs.foreach(v => p.color = v)
    compact.foreach(v => p.compact = v)
    content.toJs.foreach(v => p.content = v)
    control.foreach(v => p.control = v)
    disabled.foreach(v => p.disabled = v)
    error.toJs.foreach(v => p.error = v)
    floated.toJs.foreach(v => p.floated = v)
    fluid.foreach(v => p.fluid = v)
    icon.toJs.foreach(v => p.icon = v)
    inline.foreach(v => p.inline = v)
    inverted.foreach(v => p.inverted = v)
    label.toJs.foreach(v => p.label = v)
    labelPosition.toJs.foreach(v => p.labelPosition = v)
    loading.foreach(v => p.loading = v)
    negative.foreach(v => p.negative = v)
    p.`type` = tpe
    positive.foreach(v => p.positive = v)
    primary.foreach(v => p.primary = v)
    required.foreach(v => p.required = v)
    role.foreach(v => p.role = v)
    secondary.foreach(v => p.secondary = v)
    size.toJs.foreach(v => p.size = v)
    tabIndex.foreach(v => p.tabIndex = v)
    toggle.foreach(v => p.toggle = v)
    width.toJs.foreach(v => p.width = v)
    p
  }

  private val component =
    JsComponent[FormButtonProps, Children.Varargs, Null](RawComponent)

  def apply(modifiers: TagMod*): FormButton =
    FormButton(modifiers = modifiers)
}
