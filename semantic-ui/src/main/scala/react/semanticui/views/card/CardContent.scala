// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.views.card

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.VdomNode
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.facade.React
import react.common._
import react.semanticui._
import react.semanticui.{raw => suiraw}

case class CardContent(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  content:                MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
  description:            MyUndefOr[ShorthandS[CardDescription]] = MyUndefOr.undefined,
  extra:                  MyUndefOr[Boolean] = MyUndefOr.undefined,
  header:                 MyUndefOr[ShorthandS[CardHeader]] = MyUndefOr.undefined,
  meta:                   MyUndefOr[ShorthandS[CardMeta]] = MyUndefOr.undefined,
  textAlign:              MyUndefOr[SemanticTextAlignment] = MyUndefOr.undefined,
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
    var as: MyUndefOr[AsT] = js.native

    /** Primary content. */
    var children: MyUndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: MyUndefOr[String] = js.native

    /** Shorthand for primary content. */
    var content: MyUndefOr[suiraw.SemanticShorthandContent] = js.native

    /** Shorthand for CardDescription component. */
    var description
      : MyUndefOr[suiraw.SemanticShorthandItemS[CardDescription.CardDescriptionProps]] = js.native

    /** Shorthand for CardExtra component. */
    var extra: MyUndefOr[Boolean] = js.native

    /** Shorthand for CardHeader component. */
    var header: MyUndefOr[suiraw.SemanticShorthandItemS[CardHeader.CardHeaderProps]] = js.native

    /** Shorthand for CardMeta component. */
    var meta: MyUndefOr[suiraw.SemanticShorthandItemS[CardMeta.CardMetaProps]] = js.native

    /** Content can specify its vertical alignment. */
    var textAlign: MyUndefOr[suiraw.SemanticTEXTALIGNMENTS] = js.native
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
    as:          MyUndefOr[AsC] = MyUndefOr.undefined,
    className:   MyUndefOr[String] = MyUndefOr.undefined,
    clazz:       MyUndefOr[Css] = MyUndefOr.undefined,
    content:     MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
    description: MyUndefOr[ShorthandS[CardDescription]] = MyUndefOr.undefined,
    extra:       MyUndefOr[Boolean] = MyUndefOr.undefined,
    header:      MyUndefOr[ShorthandS[CardHeader]] = MyUndefOr.undefined,
    meta:        MyUndefOr[ShorthandS[CardMeta]] = MyUndefOr.undefined,
    textAlign:   MyUndefOr[SemanticTextAlignment] = MyUndefOr.undefined
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
