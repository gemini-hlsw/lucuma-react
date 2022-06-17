package react.semanticui.views.card

import scalajs.js
import react.common._
import react.semanticui._
import japgolly.scalajs.react.vdom.VdomNode
import japgolly.scalajs.react.vdom.TagMod

case class CardExtra(
  as:                     js.UndefOr[AsC] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  content:                js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
  description:            js.UndefOr[ShorthandS[CardDescription]] = js.undefined,
  header:                 js.UndefOr[ShorthandS[CardHeader]] = js.undefined,
  meta:                   js.UndefOr[ShorthandS[CardMeta]] = js.undefined,
  textAlign:              js.UndefOr[SemanticTextAlignment] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPAC[CardContent.CardContentProps, CardExtra] {
  override protected def cprops                     =
    CardContent.props(
      CardContent(as, className, clazz, content, description, extra = true, header, meta, textAlign)
    )
  override protected val component                  = CardContent.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object CardExtra {
  def apply(modifiers: TagMod*): CardExtra =
    new CardExtra(modifiers = modifiers)

}
