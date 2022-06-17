package react.semanticui.elements.placeholder

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.VdomNode
import react.common._
import react.semanticui.{ raw => suiraw }
import react.semanticui._
import japgolly.scalajs.react.vdom.TagMod

final case class Placeholder(
  as:                     js.UndefOr[AsC] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  content:                js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
  fluid:                  js.UndefOr[Boolean] = js.undefined,
  inverted:               js.UndefOr[Boolean] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPAC[Placeholder.PlaceholderProps, Placeholder] {
  override protected def cprops                     = Placeholder.props(this)
  override protected val component                  = Placeholder.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object Placeholder {
  @js.native
  @JSImport("semantic-ui-react", "Placeholder")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait PlaceholderProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: js.UndefOr[AsT] = js.native

    /** Primary content. */
    var children: js.UndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: js.UndefOr[String] = js.native

    /** Shorthand for primary content. */
    var content: js.UndefOr[suiraw.SemanticShorthandContent] = js.native

    /** A fluid placeholder takes up the width of its container. */
    var fluid: js.UndefOr[Boolean] = js.native

    /** A placeholder can have their color inverted. */
    var inverted: js.UndefOr[Boolean] = js.native
  }

  def props(
    q: Placeholder
  ): PlaceholderProps = {
    val p = q.as.toJsObject[PlaceholderProps]
    q.as.toJs.foreach(v => p.as = v)
    (q.className, q.clazz).toJs.foreach(v => p.className = v)
    q.content.toJs.foreach(v => p.content = v)
    q.fluid.foreach(v => p.fluid = v)
    q.inverted.foreach(v => p.inverted = v)
    p
  }

  private val component =
    JsFnComponent[PlaceholderProps, Children.Varargs](RawComponent)

  val Default: Placeholder = Placeholder()

  val defaultProps: PlaceholderProps = props(Default)

  def apply(modifiers: TagMod*): Placeholder =
    new Placeholder(modifiers = modifiers)
}
