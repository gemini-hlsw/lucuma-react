// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.views.card

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.VdomNode
import japgolly.scalajs.react.facade.React
import react.common._
import react.semanticui._
import react.semanticui.{raw => suiraw}
import japgolly.scalajs.react.vdom.TagMod
import react.semanticui.elements.image.Image

final case class Card(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  centered:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  color:                  MyUndefOr[SemanticColor] = MyUndefOr.undefined,
  content:                MyUndefOr[VdomNode] = MyUndefOr.undefined,
  description:            MyUndefOr[ShorthandS[CardDescription]] = MyUndefOr.undefined,
  extra:                  MyUndefOr[VdomNode] = MyUndefOr.undefined,
  fluid:                  MyUndefOr[Boolean] = MyUndefOr.undefined,
  header:                 MyUndefOr[ShorthandS[CardHeader]] = MyUndefOr.undefined,
  href:                   MyUndefOr[String] = MyUndefOr.undefined,
  image:                  MyUndefOr[ShorthandS[Image]] = MyUndefOr.undefined,
  link:                   MyUndefOr[Boolean] = MyUndefOr.undefined,
  meta:                   MyUndefOr[ShorthandS[CardMeta]] = MyUndefOr.undefined,
  onClickE:               MyUndefOr[Card.OnClick] = MyUndefOr.undefined,
  onClick:                MyUndefOr[Callback] = MyUndefOr.undefined,
  raised:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[Card.CardProps, Card] {
  override protected def cprops                     = Card.props(this)
  override protected val component                  = Card.component
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
    var as: MyUndefOr[AsT] = js.native

    /** A Card can center itself inside its container. */
    var centered: MyUndefOr[Boolean] = js.native

    /** Primary content. */
    var children: MyUndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: MyUndefOr[String] = js.native

    /** A Card can be formatted to display different colors. */
    var color: MyUndefOr[suiraw.SemanticCOLORS] = js.native

    /** Shorthand for primary component. */
    var content: MyUndefOr[suiraw.SemanticShorthandContent] = js.native

    /** Shorthand for CardDescription. */
    var description
      : MyUndefOr[suiraw.SemanticShorthandItemS[CardDescription.CardDescriptionProps]] = js.native

    /** Shorthand for primary content of CardContent. */
    var extra: MyUndefOr[suiraw.SemanticShorthandContent] = js.native

    /** A Card can be formatted to take up the width of its container. */
    var fluid: MyUndefOr[Boolean] = js.native

    /** Shorthand for CardHeader. */
    var header: MyUndefOr[suiraw.SemanticShorthandItemS[CardHeader.CardHeaderProps]] = js.native

    /** Render as an `a` tag instead of a `div` and adds the href attribute. */
    var href: MyUndefOr[String] = js.native

    /** A card can contain an Image component. */
    var image: MyUndefOr[suiraw.SemanticShorthandItemS[Image.ImageProps]] = js.native

    /** A card can be formatted to link to other content. */
    var link: MyUndefOr[Boolean] = js.native

    /** Shorthand for CardMeta. */
    var meta: MyUndefOr[suiraw.SemanticShorthandItemS[CardMeta.CardMetaProps]] = js.native

    /**
     * Called on click. When passed, the component renders as an `a` tag by default instead of a
     * `div`.
     */
    var onClick: MyUndefOr[js.Function2[ReactMouseEvent, CardProps, Unit]] = js.native

    /** A Card can be formatted to raise above the page. */
    var raised: MyUndefOr[Boolean] = js.native
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
    as:          MyUndefOr[AsC] = MyUndefOr.undefined,
    centered:    MyUndefOr[Boolean] = MyUndefOr.undefined,
    className:   MyUndefOr[String] = MyUndefOr.undefined,
    clazz:       MyUndefOr[Css] = MyUndefOr.undefined,
    color:       MyUndefOr[SemanticColor] = MyUndefOr.undefined,
    content:     MyUndefOr[VdomNode] = MyUndefOr.undefined,
    description: MyUndefOr[ShorthandS[CardDescription]] = MyUndefOr.undefined,
    extra:       MyUndefOr[VdomNode] = MyUndefOr.undefined,
    fluid:       MyUndefOr[Boolean] = MyUndefOr.undefined,
    header:      MyUndefOr[ShorthandS[CardHeader]] = MyUndefOr.undefined,
    href:        MyUndefOr[String] = MyUndefOr.undefined,
    image:       MyUndefOr[ShorthandS[Image]] = MyUndefOr.undefined,
    link:        MyUndefOr[Boolean] = MyUndefOr.undefined,
    meta:        MyUndefOr[ShorthandS[CardMeta]] = MyUndefOr.undefined,
    onClickE:    MyUndefOr[Card.OnClick] = MyUndefOr.undefined,
    onClick:     MyUndefOr[Callback] = MyUndefOr.undefined,
    raised:      MyUndefOr[Boolean] = MyUndefOr.undefined
  ): CardProps = {
    val p = as.toJsObject[CardProps]
    as.toJs.foreach(v => p.as = v)
    centered.foreach(v => p.centered = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    color.toJs.foreach(v => p.color = v)
    content.toJs.foreach(v => p.content = v)
    description.toJs.foreach(v => p.description = v)
    extra.toJs.foreach(v => p.extra = v)
    fluid.foreach(v => p.fluid = v)
    header.toJs.foreach(v => p.header = v)
    href.foreach(v => p.href = v)
    image.toJs.foreach(v => p.image = v)
    link.foreach(v => p.link = v)
    meta.toJs.foreach(v => p.meta = v)
    (onClickE, onClick).toJs.foreach(v => p.onClick = v)
    raised.foreach(v => p.raised = v)
    p
  }

  private val component =
    JsComponent[CardProps, Children.Varargs, Null](RawComponent)

  def apply(modifiers: TagMod*): Card =
    Card(modifiers = modifiers)
}
