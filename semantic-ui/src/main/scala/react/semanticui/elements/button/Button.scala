package react.semanticui.elements.button

import scala.scalajs.js
import js.annotation._
import js.|
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import react.common._
import react.semanticui.{ raw => suiraw }
import react.semanticui._
import react.semanticui.elements.label.Label.LabelProps
import react.semanticui.elements.label.Label
import react.semanticui.elements.icon.Icon
import react.semanticui.elements.icon.Icon.IconProps

import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.vdom.VdomNode

final case class Button(
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
  disabled:               js.UndefOr[Boolean] = js.undefined,
  floated:                js.UndefOr[SemanticFloat] = js.undefined,
  fluid:                  js.UndefOr[Boolean] = js.undefined,
  icon:                   js.UndefOr[ShorthandSB[Icon]] = js.undefined,
  inverted:               js.UndefOr[Boolean] = js.undefined,
  label:                  js.UndefOr[ShorthandS[Label]] = js.undefined,
  labelPosition:          js.UndefOr[LabelPosition] = js.undefined,
  loading:                js.UndefOr[Boolean] = js.undefined,
  negative:               js.UndefOr[Boolean] = js.undefined,
  onClickE:               js.UndefOr[Button.OnClick] = js.undefined,
  onClick:                js.UndefOr[Callback] = js.undefined,
  positive:               js.UndefOr[Boolean] = js.undefined,
  primary:                js.UndefOr[Boolean] = js.undefined,
  role:                   js.UndefOr[String] = js.undefined,
  secondary:              js.UndefOr[Boolean] = js.undefined,
  size:                   js.UndefOr[SemanticSize] = js.undefined,
  tabIndex:               js.UndefOr[TabIndex] = js.undefined,
  toggle:                 js.UndefOr[Boolean] = js.undefined,
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

    var as: js.UndefOr[AsT]                                                   = js.native
    var active: js.UndefOr[Boolean]                                           = js.native
    var animated: js.UndefOr[Boolean | String]                                = js.native
    var attached: js.UndefOr[Boolean | String]                                = js.native
    var basic: js.UndefOr[Boolean]                                            = js.native
    var children: js.UndefOr[React.Node]                                      = js.native
    var circular: js.UndefOr[Boolean]                                         = js.native
    var className: js.UndefOr[String]                                         = js.native
    var color: js.UndefOr[suiraw.SemanticCOLORS]                              = js.native
    var compact: js.UndefOr[Boolean]                                          = js.native
    var content: js.UndefOr[suiraw.SemanticShorthandContent]                  = js.native
    var disabled: js.UndefOr[Boolean]                                         = js.native
    var floated: js.UndefOr[suiraw.SemanticFLOATS]                            = js.native
    var fluid: js.UndefOr[Boolean]                                            = js.native
    var icon: js.UndefOr[suiraw.SemanticShorthandItemSB[IconProps]]           = js.native
    var inverted: js.UndefOr[Boolean]                                         = js.native
    var label: js.UndefOr[suiraw.SemanticShorthandItemS[LabelProps]]          = js.native
    var labelPosition: js.UndefOr[String]                                     = js.native
    var loading: js.UndefOr[Boolean]                                          = js.native
    var negative: js.UndefOr[Boolean]                                         = js.native
    var onClick: js.UndefOr[js.Function2[ReactMouseEvent, ButtonProps, Unit]] = js.native
    var positive: js.UndefOr[Boolean]                                         = js.native
    var primary: js.UndefOr[Boolean]                                          = js.native
    var role: js.UndefOr[String]                                              = js.native
    var secondary: js.UndefOr[Boolean]                                        = js.native
    var size: js.UndefOr[suiraw.SemanticSIZES]                                = js.native
    var tabIndex: js.UndefOr[Double | String]                                 = js.native
    var toggle: js.UndefOr[Boolean]                                           = js.native
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
    disabled:      js.UndefOr[Boolean] = js.undefined,
    floated:       js.UndefOr[SemanticFloat] = js.undefined,
    fluid:         js.UndefOr[Boolean] = js.undefined,
    icon:          js.UndefOr[ShorthandSB[Icon]] = js.undefined,
    inverted:      js.UndefOr[Boolean] = js.undefined,
    label:         js.UndefOr[ShorthandS[Label]] = js.undefined,
    labelPosition: js.UndefOr[LabelPosition] = js.undefined,
    loading:       js.UndefOr[Boolean] = js.undefined,
    negative:      js.UndefOr[Boolean] = js.undefined,
    onClickE:      js.UndefOr[OnClick] = js.undefined,
    onClick:       js.UndefOr[Callback] = js.undefined,
    positive:      js.UndefOr[Boolean] = js.undefined,
    primary:       js.UndefOr[Boolean] = js.undefined,
    role:          js.UndefOr[String] = js.undefined,
    secondary:     js.UndefOr[Boolean] = js.undefined,
    size:          js.UndefOr[SemanticSize] = js.undefined,
    tabIndex:      js.UndefOr[TabIndex] = js.undefined,
    toggle:        js.UndefOr[Boolean] = js.undefined
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
