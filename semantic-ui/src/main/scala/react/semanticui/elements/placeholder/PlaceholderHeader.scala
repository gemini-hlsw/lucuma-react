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

final case class PlaceholderHeader(
  as:                     js.UndefOr[AsC] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  content:                js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
  image:                  js.UndefOr[Boolean] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPAC[
      PlaceholderHeader.PlaceholderHeaderProps,
      PlaceholderHeader
    ] {
  override protected def cprops                     = PlaceholderHeader.props(this)
  override protected val component                  = PlaceholderHeader.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object PlaceholderHeader {
  @js.native
  @JSImport("semantic-ui-react", "PlaceholderHeader")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait PlaceholderHeaderProps extends js.Object {
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

    /** A placeholder can contain an image. */
    var image: js.UndefOr[Boolean] = js.native
  }

  def props(
    q: PlaceholderHeader
  ): PlaceholderHeaderProps = {
    val p = q.as.toJsObject[PlaceholderHeaderProps]
    q.as.toJs.foreach(v => p.as = v)
    (q.className, q.clazz).toJs.foreach(v => p.className = v)
    q.content.toJs.foreach(v => p.content = v)
    q.image.foreach(v => p.image = v)
    p
  }

  private val component =
    JsFnComponent[PlaceholderHeaderProps, Children.Varargs](RawComponent)

  val Default: PlaceholderHeader = PlaceholderHeader()

  val defaultProps: PlaceholderHeaderProps = props(Default)

  def apply(modifiers: TagMod*): PlaceholderHeader =
    new PlaceholderHeader(modifiers = modifiers)
}
