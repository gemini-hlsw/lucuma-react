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

final case class PlaceholderImage(
  as:                     js.UndefOr[AsC] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  content:                js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
  square:                 js.UndefOr[Boolean] = js.undefined,
  rectangular:            js.UndefOr[Boolean] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPA[
      PlaceholderImage.PlaceholderImageProps,
      PlaceholderImage
    ] {
  override protected def cprops                     = PlaceholderImage.props(this)
  override protected val component                  = PlaceholderImage.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object PlaceholderImage {
  @js.native
  @JSImport("semantic-ui-react", "PlaceholderImage")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait PlaceholderImageProps extends js.Object {
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

    /** An image can modify size correctly with responsive styles. */
    var square: js.UndefOr[Boolean] = js.native

    /** An image can modify size correctly with responsive styles. */
    var rectangular: js.UndefOr[Boolean] = js.native
  }

  def props(
    q: PlaceholderImage
  ): PlaceholderImageProps = {
    val p = q.as.toJsObject[PlaceholderImageProps]
    q.as.toJs.foreach(v => p.as = v)
    (q.className, q.clazz).toJs.foreach(v => p.className = v)
    q.content.toJs.foreach(v => p.content = v)
    q.square.foreach(v => p.square = v)
    q.rectangular.foreach(v => p.rectangular = v)
    p
  }

  private val component =
    JsFnComponent[PlaceholderImageProps, Children.None](RawComponent)

  val Default: PlaceholderImage = PlaceholderImage()

  val defaultProps: PlaceholderImageProps = props(Default)

  def apply(modifiers: TagMod*): PlaceholderImage =
    new PlaceholderImage(modifiers = modifiers)
}
