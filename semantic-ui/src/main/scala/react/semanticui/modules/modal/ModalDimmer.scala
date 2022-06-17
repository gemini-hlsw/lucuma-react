package react.semanticui.modules.modal

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.VdomNode
import japgolly.scalajs.react.facade.React
import react.common._
import react.semanticui._
import react.semanticui.{ raw => suiraw }
import japgolly.scalajs.react.vdom.TagMod

final case class ModalDimmer(
  as:                     js.UndefOr[AsC] = js.undefined,
  blurring:               js.UndefOr[Boolean] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  centered:               js.UndefOr[Boolean] = js.undefined,
  content:                js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
  inverted:               js.UndefOr[Boolean] = js.undefined,
  scrolling:              js.UndefOr[Boolean] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPAC[ModalDimmer.ModalDimmerProps, ModalDimmer] {
  override protected def cprops                     = ModalDimmer.props(this)
  override protected val component                  = ModalDimmer.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object ModalDimmer {
  @js.native
  @JSImport("semantic-ui-react", "ModalDimmer")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait ModalDimmerProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: js.UndefOr[AsT] = js.native

    /** A dimmer can be blurred. */
    var blurring: js.UndefOr[Boolean] = js.native

    /** Primary content. */
    var children: js.UndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: js.UndefOr[String] = js.native

    /** A dimmer can center its contents in the viewport. */
    var centered: js.UndefOr[Boolean] = js.native

    /** Shorthand for primary content. */
    var content: js.UndefOr[suiraw.SemanticShorthandContent] = js.native

    /** A dimmer can be inverted. */
    var inverted: js.UndefOr[Boolean] = js.native

    /** The node where the modal should mount. Defaults to document.body. */
    // mountNode?: any

    /** A dimmer can make body scrollable. */
    var scrolling: js.UndefOr[Boolean] = js.native
  }

  def props(q: ModalDimmer): ModalDimmerProps =
    rawprops(q.as, q.blurring, q.className, q.clazz, q.centered, q.content, q.inverted, q.scrolling)

  def rawprops(
    as:        js.UndefOr[AsC] = js.undefined,
    blurring:  js.UndefOr[Boolean] = js.undefined,
    className: js.UndefOr[String] = js.undefined,
    clazz:     js.UndefOr[Css] = js.undefined,
    centered:  js.UndefOr[Boolean] = js.undefined,
    content:   js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
    inverted:  js.UndefOr[Boolean] = js.undefined,
    scrolling: js.UndefOr[Boolean] = js.undefined
  ): ModalDimmerProps = {
    val p = as.toJsObject[ModalDimmerProps]
    as.toJs.foreach(v => p.as = v)
    blurring.foreach(v => p.blurring = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    centered.foreach(v => p.centered = v)
    content.toJs.foreach(v => p.content = v)
    inverted.foreach(v => p.inverted = v)
    scrolling.foreach(v => p.scrolling = v)
    p
  }

  private val component =
    JsFnComponent[ModalDimmerProps, Children.Varargs](RawComponent)

  def apply(modifiers: TagMod*): ModalDimmer =
    new ModalDimmer(modifiers = modifiers)
}
