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
import react.semanticui.{ raw => suiraw }

final case class CardGroup(
  as:                     js.UndefOr[AsC] = js.undefined,
  centered:               js.UndefOr[Boolean] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  content:                js.UndefOr[VdomNode] = js.undefined,
  doubling:               js.UndefOr[Boolean] = js.undefined,
  items:                  js.UndefOr[Seq[Card]] = js.undefined,
  itemsPerRow:            js.UndefOr[SemanticWidth] = js.undefined,
  stackable:              js.UndefOr[Boolean] = js.undefined,
  textAlign:              js.UndefOr[SemanticTextAlignment] = js.undefined,
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
    var as: js.UndefOr[AsT] = js.native

    /** A group of cards can center itself inside its container. */
    var centered: js.UndefOr[Boolean] = js.native

    /** Primary content. */
    var children: js.UndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: js.UndefOr[String] = js.native

    /** Shorthand for primary content. */
    var content: js.UndefOr[suiraw.SemanticShorthandContent] = js.native

    /** A group of cards can double its column width for mobile. */
    var doubling: js.UndefOr[Boolean] = js.native

    /** Shorthand array of props for Card. */
    var items: js.UndefOr[js.Array[Card.CardProps]] = js.native

    /** Shorthand for primary content. */
    var itemsPerRow: js.UndefOr[suiraw.SemanticWIDTHS] = js.native

    /** A group of cards can automatically stack rows to a single columns on mobile devices. */
    var stackable: js.UndefOr[Boolean] = js.native

    /** A card group can adjust its text alignment. */
    var textAlign: js.UndefOr[suiraw.SemanticTEXTALIGNMENTS] = js.native

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
    as:          js.UndefOr[AsC] = js.undefined,
    centered:    js.UndefOr[Boolean] = js.undefined,
    className:   js.UndefOr[String] = js.undefined,
    clazz:       js.UndefOr[Css] = js.undefined,
    content:     js.UndefOr[VdomNode] = js.undefined,
    doubling:    js.UndefOr[Boolean] = js.undefined,
    items:       js.UndefOr[Seq[Card]] = js.undefined,
    itemsPerRow: js.UndefOr[SemanticWidth] = js.undefined,
    stackable:   js.UndefOr[Boolean] = js.undefined,
    textAlign:   js.UndefOr[SemanticTextAlignment] = js.undefined
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
