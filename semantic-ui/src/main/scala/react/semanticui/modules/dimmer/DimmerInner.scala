package react.semanticui.modules.dimmer

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.VdomNode
import japgolly.scalajs.react.facade.React
import react.common._
import react.semanticui._
import react.semanticui.{ raw => suiraw }
import japgolly.scalajs.react.vdom.TagMod

final case class DimmerInner(
  as:                     js.UndefOr[AsC] = js.undefined,
  active:                 js.UndefOr[Boolean] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  content:                js.UndefOr[VdomNode] = js.undefined,
  disabled:               js.UndefOr[Boolean] = js.undefined,
  onClickE:               js.UndefOr[DimmerInner.OnClick] = js.undefined,
  onClick:                js.UndefOr[Callback] = js.undefined,
  onClickOutsideE:        js.UndefOr[DimmerInner.OnClickOutside] = js.undefined,
  onClickOutside:         js.UndefOr[Callback] = js.undefined,
  inverted:               js.UndefOr[Boolean] = js.undefined,
  page:                   js.UndefOr[Boolean] = js.undefined,
  simple:                 js.UndefOr[Boolean] = js.undefined,
  verticalAlign:          js.UndefOr[DimmerVerticalAlign] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[DimmerInner.DimmerInnerProps, DimmerInner] {
  override protected def cprops                     = DimmerInner.props(this)
  override protected val component                  = DimmerInner.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object DimmerInner {
  type RawOnClick        = js.Function2[ReactMouseEvent, DimmerInner.DimmerInnerProps, Unit]
  type OnClick           = (ReactMouseEvent, DimmerInner.DimmerInnerProps) => Callback
  type RawOnClickOutside = RawOnClick
  type OnClickOutside    = OnClick

  @js.native
  @JSImport("semantic-ui-react", "DimmerInner")
  object RawComponent extends js.Object

  @js.native
  trait DimmerInnerProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: js.UndefOr[AsT] = js.native

    /** An active dimmer will dim its parent container. */
    var active: js.UndefOr[Boolean] = js.native

    /** Primary content. */
    var children: js.UndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: js.UndefOr[String] = js.native

    /** Shorthand for primary content. */
    var content: js.UndefOr[suiraw.SemanticShorthandContent] =
      js.native

    /** A disabled dimmer cannot be activated */
    var disabled: js.UndefOr[Boolean] = js.native

    /**
     * Called when the dimmer is clicked.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props.
     */
    var onClick: js.UndefOr[RawOnClick] = js.native

    /**
     * Handles click outside Dimmer's content, but inside Dimmer area.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props.
     */
    var onClickOutside: js.UndefOr[RawOnClickOutside] = js.native

    /** A dimmer can be formatted to have its colors inverted. */
    var inverted: js.UndefOr[Boolean] = js.native

    /** A dimmer can be formatted to be fixed to the page. */
    var page: js.UndefOr[Boolean] = js.native

    /** A dimmer can be controlled with simple prop. */
    var simple: js.UndefOr[Boolean] = js.native

    /** A dimmer can have its content top or bottom aligned. */
    var verticalAlign: js.UndefOr[String] = js.native
  }

  def props(q: DimmerInner): DimmerInnerProps =
    rawprops(
      q.as,
      q.active,
      q.className,
      q.clazz,
      q.content,
      q.disabled,
      q.onClickE,
      q.onClick,
      q.onClickOutsideE,
      q.onClickOutside,
      q.inverted,
      q.page,
      q.simple,
      q.verticalAlign
    )

  def rawprops(
    as:              js.UndefOr[AsC] = js.undefined,
    active:          js.UndefOr[Boolean] = js.undefined,
    className:       js.UndefOr[String] = js.undefined,
    clazz:           js.UndefOr[Css] = js.undefined,
    content:         js.UndefOr[VdomNode] = js.undefined,
    disabled:        js.UndefOr[Boolean] = js.undefined,
    onClickE:        js.UndefOr[OnClick] = js.undefined,
    onClick:         js.UndefOr[Callback] = js.undefined,
    onClickOutsideE: js.UndefOr[OnClickOutside] = js.undefined,
    onClickOutside:  js.UndefOr[Callback] = js.undefined,
    inverted:        js.UndefOr[Boolean] = js.undefined,
    page:            js.UndefOr[Boolean] = js.undefined,
    simple:          js.UndefOr[Boolean] = js.undefined,
    verticalAlign:   js.UndefOr[DimmerVerticalAlign] = js.undefined
  ): DimmerInnerProps = {
    val p = as.toJsObject[DimmerInnerProps]
    as.toJs.foreach(v => p.as = v)
    active.foreach(v => p.active = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    disabled.foreach(v => p.disabled = v)
    (onClickE, onClick).toJs.foreach(v => p.onClick = v)
    (onClickOutsideE, onClickOutside).toJs.foreach(v => p.onClickOutside = v)
    inverted.foreach(v => p.inverted = v)
    page.foreach(v => p.page = v)
    simple.foreach(v => p.simple = v)
    verticalAlign.toJs.foreach(v => p.verticalAlign = v)
    p
  }

  private val component =
    JsComponent[DimmerInnerProps, Children.Varargs, Null](RawComponent)

  def apply(modifiers: TagMod*): DimmerInner =
    new DimmerInner(modifiers = modifiers)
}
