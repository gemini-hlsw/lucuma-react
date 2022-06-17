package react.semanticui.elements.button

import scala.scalajs.js
import js.annotation._
import js.|
import js.JSConverters._
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.VdomNode
import react.common._
import react.semanticui.{ raw => suiraw }
import react.semanticui._
import japgolly.scalajs.react.vdom.TagMod

final case class ButtonGroup(
  as:                     js.UndefOr[AsC] = js.undefined,
  attached:               js.UndefOr[Boolean | String] = js.undefined,
  basic:                  js.UndefOr[Boolean] = js.undefined,
  buttons:                js.UndefOr[Seq[Button]] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  color:                  js.UndefOr[SemanticColor] = js.undefined,
  compact:                js.UndefOr[Boolean] = js.undefined,
  content:                js.UndefOr[Seq[VdomNode]] = js.undefined,
  floated:                js.UndefOr[SemanticFloat] = js.undefined,
  fluid:                  js.UndefOr[Boolean] = js.undefined,
  icon:                   js.UndefOr[Boolean] = js.undefined,
  inverted:               js.UndefOr[Boolean] = js.undefined,
  labeled:                js.UndefOr[Boolean] = js.undefined,
  negative:               js.UndefOr[Boolean] = js.undefined,
  positive:               js.UndefOr[Boolean] = js.undefined,
  primary:                js.UndefOr[Boolean] = js.undefined,
  secondary:              js.UndefOr[Boolean] = js.undefined,
  size:                   js.UndefOr[SemanticSize] = js.undefined,
  toggle:                 js.UndefOr[Boolean] = js.undefined,
  vertical:               js.UndefOr[Boolean] = js.undefined,
  widths:                 js.UndefOr[SemanticWidth] = js.undefined,
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
    var as: js.UndefOr[AsT] = js.native

    /** Groups can be attached to other content. */
    var attached: js.UndefOr[Boolean | String] = js.native

    /** Groups can be less pronounced. */
    var basic: js.UndefOr[Boolean] = js.native

    /** Array of shorthand ButtonGroup values. */
    var buttons: js.UndefOr[js.Array[Button.ButtonProps]] = js.native

    /** Primary content. */
    var children: js.UndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: js.UndefOr[String] = js.native

    /** Groups can have a shared color. */
    var color: js.UndefOr[suiraw.SemanticCOLORS] = js.native

    /** Groups can reduce their padding to fit into tighter spaces. */
    var compact: js.UndefOr[Boolean] = js.native

    /** Shorthand for primary content. */
    var content: js.UndefOr[js.Array[suiraw.SemanticShorthandContent]] = js.native

    /** Groups can be aligned to the left or right of its container. */
    var floated: js.UndefOr[suiraw.SemanticFLOATS] = js.native

    /** Groups can take the width of their container. */
    var fluid: js.UndefOr[Boolean] = js.native

    /** Groups can be formatted as icons. */
    var icon: js.UndefOr[Boolean] = js.native

    /** Groups can be formatted to appear on dark backgrounds. */
    var inverted: js.UndefOr[Boolean] = js.native

    /** Groups can be formatted as labeled icon buttons. */
    var labeled: js.UndefOr[Boolean] = js.native

    /** Groups can hint towards a negative consequence. */
    var negative: js.UndefOr[Boolean] = js.native

    /** Groups can hint towards a positive consequence. */
    var positive: js.UndefOr[Boolean] = js.native

    /** Groups can be formatted to show different levels of emphasis. */
    var primary: js.UndefOr[Boolean] = js.native

    /** Groups can be formatted to show different levels of emphasis. */
    var secondary: js.UndefOr[Boolean] = js.native

    /** Groups can have different sizes. */
    var size: js.UndefOr[suiraw.SemanticSIZES] = js.native

    /** Groups can be formatted to toggle on and off. */
    var toggle: js.UndefOr[Boolean] = js.native

    /** Groups can be formatted to appear vertically. */
    var vertical: js.UndefOr[Boolean] = js.native

    /** Groups can have their widths divided evenly. */
    var widths: js.UndefOr[suiraw.SemanticWIDTHS] = js.native
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
    as:        js.UndefOr[AsC] = js.undefined,
    attached:  js.UndefOr[Boolean | String] = js.undefined,
    basic:     js.UndefOr[Boolean] = js.undefined,
    buttons:   js.UndefOr[Seq[Button]] = js.undefined,
    className: js.UndefOr[String] = js.undefined,
    clazz:     js.UndefOr[Css] = js.undefined,
    color:     js.UndefOr[SemanticColor] = js.undefined,
    compact:   js.UndefOr[Boolean] = js.undefined,
    content:   js.UndefOr[Seq[VdomNode]] = js.undefined,
    floated:   js.UndefOr[SemanticFloat] = js.undefined,
    fluid:     js.UndefOr[Boolean] = js.undefined,
    icon:      js.UndefOr[Boolean] = js.undefined,
    inverted:  js.UndefOr[Boolean] = js.undefined,
    labeled:   js.UndefOr[Boolean] = js.undefined,
    negative:  js.UndefOr[Boolean] = js.undefined,
    positive:  js.UndefOr[Boolean] = js.undefined,
    primary:   js.UndefOr[Boolean] = js.undefined,
    secondary: js.UndefOr[Boolean] = js.undefined,
    size:      js.UndefOr[SemanticSize] = js.undefined,
    toggle:    js.UndefOr[Boolean] = js.undefined,
    vertical:  js.UndefOr[Boolean] = js.undefined,
    widths:    js.UndefOr[SemanticWidth] = js.undefined
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
