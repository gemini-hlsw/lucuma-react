package react.semanticui.views.card

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.VdomNode
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.facade.React
import react.common._
import react.semanticui._
import react.semanticui.{ raw => suiraw }

case class CardContent(
  as:                     js.UndefOr[AsC] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  content:                js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
  description:            js.UndefOr[ShorthandS[CardDescription]] = js.undefined,
  extra:                  js.UndefOr[Boolean] = js.undefined,
  header:                 js.UndefOr[ShorthandS[CardHeader]] = js.undefined,
  meta:                   js.UndefOr[ShorthandS[CardMeta]] = js.undefined,
  textAlign:              js.UndefOr[SemanticTextAlignment] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPAC[CardContent.CardContentProps, CardContent] {
  override protected def cprops                     = CardContent.props(this)
  override protected val component                  = CardContent.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object CardContent {
  @js.native
  @JSImport("semantic-ui-react", "CardContent")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait CardContentProps extends js.Object {
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

    /** Shorthand for CardDescription component. */
    var description
      : js.UndefOr[suiraw.SemanticShorthandItemS[CardDescription.CardDescriptionProps]] = js.native

    /** Shorthand for CardExtra component. */
    var extra: js.UndefOr[Boolean] = js.native

    /** Shorthand for CardHeader component. */
    var header: js.UndefOr[suiraw.SemanticShorthandItemS[CardHeader.CardHeaderProps]] = js.native

    /** Shorthand for CardMeta component. */
    var meta: js.UndefOr[suiraw.SemanticShorthandItemS[CardMeta.CardMetaProps]] = js.native

    /** Content can specify its vertical alignment. */
    var textAlign: js.UndefOr[suiraw.SemanticTEXTALIGNMENTS] = js.native
  }

  def props(q: CardContent): CardContentProps =
    rawprops(q.as,
             q.className,
             q.clazz,
             q.content,
             q.description,
             q.extra,
             q.header,
             q.meta,
             q.textAlign
    )

  def rawprops(
    as:          js.UndefOr[AsC] = js.undefined,
    className:   js.UndefOr[String] = js.undefined,
    clazz:       js.UndefOr[Css] = js.undefined,
    content:     js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
    description: js.UndefOr[ShorthandS[CardDescription]] = js.undefined,
    extra:       js.UndefOr[Boolean] = js.undefined,
    header:      js.UndefOr[ShorthandS[CardHeader]] = js.undefined,
    meta:        js.UndefOr[ShorthandS[CardMeta]] = js.undefined,
    textAlign:   js.UndefOr[SemanticTextAlignment] = js.undefined
  ): CardContentProps = {
    val p = as.toJsObject[CardContentProps]
    as.toJs.foreach(v => p.as = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    content.toJs.foreach(v => p.content = v)
    description.toJs.foreach(v => p.description = v)
    extra.foreach(v => p.extra = v)
    header.toJs.foreach(v => p.header = v)
    meta.toJs.foreach(v => p.meta = v)
    textAlign.toJs.foreach(v => p.textAlign = v)
    p
  }

  protected[views] val component =
    JsFnComponent[CardContentProps, Children.Varargs](RawComponent)

  def apply(modifiers: TagMod*): CardContent =
    new CardContent(modifiers = modifiers)
}
