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

final case class PlaceholderParagraph(
  as:                     js.UndefOr[AsC] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  content:                js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPAC[
      PlaceholderParagraph.PlaceholderParagraphProps,
      PlaceholderParagraph
    ] {
  override protected def cprops                     = PlaceholderParagraph.props(this)
  override protected val component                  = PlaceholderParagraph.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object PlaceholderParagraph {
  @js.native
  @JSImport("semantic-ui-react", "PlaceholderParagraph")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait PlaceholderParagraphProps extends js.Object {
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
  }

  def props(
    q: PlaceholderParagraph
  ): PlaceholderParagraphProps = {
    val p = q.as.toJsObject[PlaceholderParagraphProps]
    q.as.toJs.foreach(v => p.as = v)
    (q.className, q.clazz).toJs.foreach(v => p.className = v)
    q.content.toJs.foreach(v => p.content = v)
    p
  }

  private val component =
    JsFnComponent[PlaceholderParagraphProps, Children.Varargs](RawComponent)

  val Default: PlaceholderParagraph = PlaceholderParagraph()

  val defaultProps: PlaceholderParagraphProps = props(Default)

  def apply(modifiers: TagMod*): PlaceholderParagraph =
    new PlaceholderParagraph(modifiers = modifiers)
}
