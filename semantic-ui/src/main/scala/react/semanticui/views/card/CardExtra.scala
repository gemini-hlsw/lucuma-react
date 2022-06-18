// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.views.card

import scalajs.js
import react.common._
import react.semanticui._
import japgolly.scalajs.react.vdom.VdomNode
import japgolly.scalajs.react.vdom.TagMod

case class CardExtra(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  content:                MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
  description:            MyUndefOr[ShorthandS[CardDescription]] = MyUndefOr.undefined,
  header:                 MyUndefOr[ShorthandS[CardHeader]] = MyUndefOr.undefined,
  meta:                   MyUndefOr[ShorthandS[CardMeta]] = MyUndefOr.undefined,
  textAlign:              MyUndefOr[SemanticTextAlignment] = MyUndefOr.undefined,
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
