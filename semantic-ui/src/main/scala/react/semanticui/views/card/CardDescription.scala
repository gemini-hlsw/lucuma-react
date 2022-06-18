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

final case class CardDescription(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  content:                MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
  textAlign:              MyUndefOr[SemanticTextAlignment] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPAC[CardDescription.CardDescriptionProps, CardDescription] {
  override protected def cprops                     = CardDescription.props(this)
  override protected val component                  = CardDescription.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object CardDescription {
  @js.native
  @JSImport("semantic-ui-react", "CardDescription")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait CardDescriptionProps extends js.Object {
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

    /** A card content can adjust its text alignment. */
    var textAlign: MyUndefOr[suiraw.SemanticTEXTALIGNMENTS] = js.native
  }

  def props(q: CardDescription): CardDescriptionProps =
    rawprops(q.as, q.className, q.clazz, q.content, q.textAlign)

  def rawprops(
    as:        MyUndefOr[AsC] = MyUndefOr.undefined,
    className: MyUndefOr[String] = MyUndefOr.undefined,
    clazz:     MyUndefOr[Css] = MyUndefOr.undefined,
    content:   MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
    textAlign: MyUndefOr[SemanticTextAlignment] = MyUndefOr.undefined
  ): CardDescriptionProps = {
    val p = as.toJsObject[CardDescriptionProps]
    as.toJs.foreach(v => p.as = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    content.toJs.foreach(v => p.content = v)
    textAlign.toJs.foreach(v => p.textAlign = v)
    p
  }

  private val component =
    JsFnComponent[CardDescriptionProps, Children.Varargs](RawComponent)

  def apply(modifiers: TagMod*): CardDescription =
    new CardDescription(modifiers = modifiers)
}
