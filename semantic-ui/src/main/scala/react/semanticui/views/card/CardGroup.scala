// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.views.card

import scala.scalajs.js
import js.annotation._
import js.JSConverters._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.VdomNode
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.facade.React
import react.common._
import react.semanticui._
import react.semanticui.{raw => suiraw}

final case class CardGroup(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  centered:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  content:                MyUndefOr[VdomNode] = MyUndefOr.undefined,
  doubling:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  items:                  MyUndefOr[Seq[Card]] = MyUndefOr.undefined,
  itemsPerRow:            MyUndefOr[SemanticWidth] = MyUndefOr.undefined,
  stackable:              MyUndefOr[Boolean] = MyUndefOr.undefined,
  textAlign:              MyUndefOr[SemanticTextAlignment] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPAC[CardGroup.CardGroupProps, CardGroup] {
  override protected def cprops                     = CardGroup.props(this)
  override protected val component                  = CardGroup.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object CardGroup {
  @js.native
  @JSImport("semantic-ui-react", "CardGroup")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait CardGroupProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: MyUndefOr[AsT] = js.native

    /** A group of cards can center itself inside its container. */
    var centered: MyUndefOr[Boolean] = js.native

    /** Primary content. */
    var children: MyUndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: MyUndefOr[String] = js.native

    /** Shorthand for primary content. */
    var content: MyUndefOr[suiraw.SemanticShorthandContent] = js.native

    /** A group of cards can double its column width for mobile. */
    var doubling: MyUndefOr[Boolean] = js.native

    /** Shorthand array of props for Card. */
    var items: MyUndefOr[js.Array[Card.CardProps]] = js.native

    /** Shorthand for primary content. */
    var itemsPerRow: MyUndefOr[suiraw.SemanticWIDTHS] = js.native

    /** A group of cards can automatically stack rows to a single columns on mobile devices. */
    var stackable: MyUndefOr[Boolean] = js.native

    /** A card group can adjust its text alignment. */
    var textAlign: MyUndefOr[suiraw.SemanticTEXTALIGNMENTS] = js.native

  }

  def props(q: CardGroup): CardGroupProps =
    rawprops(q.as,
             q.centered,
             q.className,
             q.clazz,
             q.content,
             q.doubling,
             q.items,
             q.itemsPerRow,
             q.stackable,
             q.textAlign
    )

  def rawprops(
    as:          MyUndefOr[AsC] = MyUndefOr.undefined,
    centered:    MyUndefOr[Boolean] = MyUndefOr.undefined,
    className:   MyUndefOr[String] = MyUndefOr.undefined,
    clazz:       MyUndefOr[Css] = MyUndefOr.undefined,
    content:     MyUndefOr[VdomNode] = MyUndefOr.undefined,
    doubling:    MyUndefOr[Boolean] = MyUndefOr.undefined,
    items:       MyUndefOr[Seq[Card]] = MyUndefOr.undefined,
    itemsPerRow: MyUndefOr[SemanticWidth] = MyUndefOr.undefined,
    stackable:   MyUndefOr[Boolean] = MyUndefOr.undefined,
    textAlign:   MyUndefOr[SemanticTextAlignment] = MyUndefOr.undefined
  ): CardGroupProps = {
    val p = as.toJsObject[CardGroupProps]
    as.toJs.foreach(v => p.as = v)
    centered.foreach(v => p.centered = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    content.toJs.foreach(v => p.content = v)
    doubling.foreach(v => p.doubling = v)
    items.map(_.map(_.props).toJSArray).foreach(v => p.items = v)
    itemsPerRow.toJs.foreach(v => p.itemsPerRow = v)
    stackable.foreach(v => p.stackable = v)
    textAlign.toJs.foreach(v => p.textAlign = v)
    p
  }

  private val component =
    JsFnComponent[CardGroupProps, Children.Varargs](RawComponent)

  def apply(modifiers: TagMod*): CardGroup =
    new CardGroup(modifiers = modifiers)
}
