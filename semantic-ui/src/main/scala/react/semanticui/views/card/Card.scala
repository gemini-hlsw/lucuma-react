// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.views.card

import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.vdom.VdomNode
import react.common._
import react.semanticui._
import react.semanticui.elements.image.Image
import react.semanticui.{raw => suiraw}

import scala.scalajs.js

import js.annotation._

final case class Card(
  as:                     js.UndefOr[AsC] = js.undefined,
  centered:               js.UndefOr[Boolean] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  color:                  js.UndefOr[SemanticColor] = js.undefined,
  content:                js.UndefOr[VdomNode] = js.undefined,
  description:            js.UndefOr[ShorthandS[CardDescription]] = js.undefined,
  extra:                  js.UndefOr[VdomNode] = js.undefined,
  fluid:                  js.UndefOr[Boolean] = js.undefined,
  header:                 js.UndefOr[ShorthandS[CardHeader]] = js.undefined,
  href:                   js.UndefOr[String] = js.undefined,
  image:                  js.UndefOr[ShorthandS[Image]] = js.undefined,
  link:                   js.UndefOr[Boolean] = js.undefined,
  meta:                   js.UndefOr[ShorthandS[CardMeta]] = js.undefined,
  onClickE:               js.UndefOr[Card.OnClick] = js.undefined,
  onClick:                js.UndefOr[Callback] = js.undefined,
  raised:                 js.UndefOr[Boolean] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[Card.CardProps, Card] {
  override protected def cprops    = Card.props(this)
  override protected val component = Card.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object Card {
  type OnClick = (ReactMouseEvent, CardProps) => Callback

  @js.native
  @JSImport("semantic-ui-react", "Card")
  object RawComponent extends js.Object

  @js.native
  trait CardProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: js.UndefOr[AsT] = js.native

    /** A Card can center itself inside its container. */
    var centered: js.UndefOr[Boolean] = js.native

    /** Primary content. */
    var children: js.UndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: js.UndefOr[String] = js.native

    /** A Card can be formatted to display different colors. */
    var color: js.UndefOr[suiraw.SemanticCOLORS] = js.native

    /** Shorthand for primary component. */
    var content: js.UndefOr[suiraw.SemanticShorthandContent] = js.native

    /** Shorthand for CardDescription. */
    var description
      : js.UndefOr[suiraw.SemanticShorthandItemS[CardDescription.CardDescriptionProps]] = js.native

    /** Shorthand for primary content of CardContent. */
    var extra: js.UndefOr[suiraw.SemanticShorthandContent] = js.native

    /** A Card can be formatted to take up the width of its container. */
    var fluid: js.UndefOr[Boolean] = js.native

    /** Shorthand for CardHeader. */
    var header: js.UndefOr[suiraw.SemanticShorthandItemS[CardHeader.CardHeaderProps]] = js.native

    /** Render as an `a` tag instead of a `div` and adds the href attribute. */
    var href: js.UndefOr[String] = js.native

    /** A card can contain an Image component. */
    var image: js.UndefOr[suiraw.SemanticShorthandItemS[Image.ImageProps]] = js.native

    /** A card can be formatted to link to other content. */
    var link: js.UndefOr[Boolean] = js.native

    /** Shorthand for CardMeta. */
    var meta: js.UndefOr[suiraw.SemanticShorthandItemS[CardMeta.CardMetaProps]] = js.native

    /**
     * Called on click. When passed, the component renders as an `a` tag by default instead of a
     * `div`.
     */
    var onClick: js.UndefOr[js.Function2[ReactMouseEvent, CardProps, Unit]] = js.native

    /** A Card can be formatted to raise above the page. */
    var raised: js.UndefOr[Boolean] = js.native
  }

  def props(q: Card): CardProps =
    rawprops(
      q.as,
      q.centered,
      q.className,
      q.clazz,
      q.color,
      q.content,
      q.description,
      q.extra,
      q.fluid,
      q.header,
      q.href,
      q.image,
      q.link,
      q.meta,
      q.onClickE,
      q.onClick,
      q.raised
    )

  def rawprops(
    as:          js.UndefOr[AsC] = js.undefined,
    centered:    js.UndefOr[Boolean] = js.undefined,
    className:   js.UndefOr[String] = js.undefined,
    clazz:       js.UndefOr[Css] = js.undefined,
    color:       js.UndefOr[SemanticColor] = js.undefined,
    content:     js.UndefOr[VdomNode] = js.undefined,
    description: js.UndefOr[ShorthandS[CardDescription]] = js.undefined,
    extra:       js.UndefOr[VdomNode] = js.undefined,
    fluid:       js.UndefOr[Boolean] = js.undefined,
    header:      js.UndefOr[ShorthandS[CardHeader]] = js.undefined,
    href:        js.UndefOr[String] = js.undefined,
    image:       js.UndefOr[ShorthandS[Image]] = js.undefined,
    link:        js.UndefOr[Boolean] = js.undefined,
    meta:        js.UndefOr[ShorthandS[CardMeta]] = js.undefined,
    onClickE:    js.UndefOr[Card.OnClick] = js.undefined,
    onClick:     js.UndefOr[Callback] = js.undefined,
    raised:      js.UndefOr[Boolean] = js.undefined
  ): CardProps = {
    val p = as.toJsObject[CardProps]
    as.toJs.foreachUnchecked(v => p.as = v)
    centered.foreach(v => p.centered = v)
    (className, clazz).cssToJs.foreach(v => p.className = v)
    color.toJs.foreach(v => p.color = v)
    content.toJs.foreachUnchecked(v => p.content = v)
    CompFnToPropsS(description).toJs.foreachUnchecked(v => p.description = v)
    extra.toJs.foreachUnchecked(v => p.extra = v)
    fluid.foreach(v => p.fluid = v)
    CompFnToPropsS(header).toJs.foreachUnchecked(v => p.header = v)
    href.foreach(v => p.href = v)
    CompFnToPropsS(image).toJs.foreachUnchecked(v => p.image = v)
    link.foreach(v => p.link = v)
    CompFnToPropsS(meta).toJs.foreachUnchecked(v => p.meta = v)
    (onClickE, onClick).toJs.foreach(v => p.onClick = v)
    raised.foreach(v => p.raised = v)
    p
  }

  private val component =
    JsComponent[CardProps, Children.Varargs, Null](RawComponent)

  def apply(modifiers: TagMod*): Card =
    Card(modifiers = modifiers)
}
