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
import react.semanticui.{ raw => suiraw }
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.vdom.VdomNode

final case class FormButton(
  as:                     js.UndefOr[AsC] = js.undefined,
  active:                 js.UndefOr[Boolean] = js.undefined,
  animated:               js.UndefOr[ButtonAnimated] = js.undefined,
  attached:               js.UndefOr[ButtonAttached] = js.undefined,
  basic:                  js.UndefOr[Boolean] = js.undefined,
  circular:               js.UndefOr[Boolean] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  color:                  js.UndefOr[SemanticColor] = js.undefined,
  compact:                js.UndefOr[Boolean] = js.undefined,
  content:                js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
  control:                js.UndefOr[String] = js.undefined,
  disabled:               js.UndefOr[Boolean] = js.undefined,
  error:                  js.UndefOr[ShorthandB[Label]] = js.undefined,
  floated:                js.UndefOr[SemanticFloat] = js.undefined,
  fluid:                  js.UndefOr[Boolean] = js.undefined,
  icon:                   js.UndefOr[ShorthandSB[Icon]] = js.undefined,
  inline:                 js.UndefOr[Boolean] = js.undefined,
  inverted:               js.UndefOr[Boolean] = js.undefined,
  label:                  js.UndefOr[ShorthandS[Label]] = js.undefined,
  labelPosition:          js.UndefOr[LabelPosition] = js.undefined,
  loading:                js.UndefOr[Boolean] = js.undefined,
  negative:               js.UndefOr[Boolean] = js.undefined,
  onClick:                js.UndefOr[Callback] = js.undefined,
  onClickE:               js.UndefOr[Button.OnClick] = js.undefined,
  positive:               js.UndefOr[Boolean] = js.undefined,
  primary:                js.UndefOr[Boolean] = js.undefined,
  required:               js.UndefOr[Boolean] = js.undefined,
  role:                   js.UndefOr[Boolean] = js.undefined,
  secondary:              js.UndefOr[Boolean] = js.undefined,
  size:                   js.UndefOr[SemanticSize] = js.undefined,
  tabIndex:               js.UndefOr[TabIndex] = js.undefined,
  toggle:                 js.UndefOr[Boolean] = js.undefined,
  tpe:                    js.UndefOr[String] = js.undefined,
  width:                  js.UndefOr[SemanticWidth] = js.undefined,
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
    var as: js.UndefOr[AsT]                                                          = js.native
    var active: js.UndefOr[Boolean]                                                  = js.native
    var animated: js.UndefOr[Boolean | String]                                       = js.native
    var attached: js.UndefOr[Boolean | String]                                       = js.native
    var basic: js.UndefOr[Boolean]                                                   = js.native
    var circular: js.UndefOr[Boolean]                                                = js.native
    var color: js.UndefOr[String]                                                    = js.native
    var compact: js.UndefOr[Boolean]                                                 = js.native
    var floated: js.UndefOr[suiraw.SemanticFLOATS]                                   = js.native
    var fluid: js.UndefOr[Boolean]                                                   = js.native
    var icon: js.UndefOr[suiraw.SemanticShorthandItemSB[IconProps]]                  = js.native
    var inverted: js.UndefOr[Boolean]                                                = js.native
    var labelPosition: js.UndefOr[String]                                            = js.native
    var loading: js.UndefOr[Boolean]                                                 = js.native
    var negative: js.UndefOr[Boolean]                                                = js.native
    var onClick: js.UndefOr[js.Function2[ReactMouseEvent, Button.ButtonProps, Unit]] = js.native
    var positive: js.UndefOr[Boolean]                                                = js.native
    var primary: js.UndefOr[Boolean]                                                 = js.native
    var role: js.UndefOr[Boolean]                                                    = js.native
    var secondary: js.UndefOr[Boolean]                                               = js.native
    var size: js.UndefOr[suiraw.SemanticSIZES]                                       = js.native
    var tabIndex: js.UndefOr[Double | String]                                        = js.native
    var toggle: js.UndefOr[Boolean]                                                  = js.native

    /** Primary content. */
    var children: js.UndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: js.UndefOr[String] = js.native

    /** Shorthand for primary content. */
    var content: js.UndefOr[suiraw.SemanticShorthandContent] =
      js.undefined

    /**
     * A form control component (i.e. Dropdown) or HTML tagName (i.e. 'input'). Extra FormField
     * props are passed to the control component. Mutually exclusive with children.
     */
    // control?: any
    var control: js.UndefOr[String]

    /** Individual fields may be disabled. */
    var disabled: js.UndefOr[Boolean] = js.native

    /** Individual fields may display an error state along with a message. */
    var error: js.UndefOr[suiraw.SemanticShorthandItemB[Label.LabelProps]] = js.native

    /** A field can have its label next to instead of above it. */
    var inline: js.UndefOr[Boolean] = js.native

    /** Mutually exclusive with children. */
    var label: js.UndefOr[suiraw.SemanticShorthandItemS[Label.LabelProps]] = js.native

    /** A field can show that input is mandatory.  Requires a label. */
    var required: js.UndefOr[Boolean] = js.native

    /** Passed to the control component (i.e. <input type='password' />) */
    var `type`: js.UndefOr[String] = js.native

    /** A field can specify its width in grid columns */
    var width: js.UndefOr[suiraw.SemanticWIDTHS] = js.native // | 'equal'
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
    as:            js.UndefOr[AsC] = js.undefined,
    active:        js.UndefOr[Boolean] = js.undefined,
    animated:      js.UndefOr[ButtonAnimated] = js.undefined,
    attached:      js.UndefOr[ButtonAttached] = js.undefined,
    basic:         js.UndefOr[Boolean] = js.undefined,
    circular:      js.UndefOr[Boolean] = js.undefined,
    className:     js.UndefOr[String] = js.undefined,
    clazz:         js.UndefOr[Css] = js.undefined,
    color:         js.UndefOr[SemanticColor] = js.undefined,
    compact:       js.UndefOr[Boolean] = js.undefined,
    content:       js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
    control:       js.UndefOr[String] = js.undefined,
    disabled:      js.UndefOr[Boolean] = js.undefined,
    error:         js.UndefOr[ShorthandB[Label]] = js.undefined,
    floated:       js.UndefOr[SemanticFloat] = js.undefined,
    fluid:         js.UndefOr[Boolean] = js.undefined,
    icon:          js.UndefOr[ShorthandSB[Icon]] = js.undefined,
    inline:        js.UndefOr[Boolean] = js.undefined,
    inverted:      js.UndefOr[Boolean] = js.undefined,
    label:         js.UndefOr[ShorthandS[Label]] = js.undefined,
    labelPosition: js.UndefOr[LabelPosition] = js.undefined,
    loading:       js.UndefOr[Boolean] = js.undefined,
    negative:      js.UndefOr[Boolean] = js.undefined,
    onClick:       js.UndefOr[Callback] = js.undefined,
    onClickE:      js.UndefOr[Button.OnClick] = js.undefined,
    positive:      js.UndefOr[Boolean] = js.undefined,
    primary:       js.UndefOr[Boolean] = js.undefined,
    required:      js.UndefOr[Boolean] = js.undefined,
    role:          js.UndefOr[Boolean] = js.undefined,
    secondary:     js.UndefOr[Boolean] = js.undefined,
    size:          js.UndefOr[SemanticSize] = js.undefined,
    tabIndex:      js.UndefOr[TabIndex] = js.undefined,
    toggle:        js.UndefOr[Boolean] = js.undefined,
    tpe:           js.UndefOr[String] = js.undefined,
    width:         js.UndefOr[SemanticWidth] = js.undefined
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
