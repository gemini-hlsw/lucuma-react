package react.semanticui.views.item

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.VdomNode
import japgolly.scalajs.react.facade.React
import react.common._
import react.semanticui._
import react.semanticui.{ raw => suiraw }
import japgolly.scalajs.react.vdom.TagMod

final case class Item(
  as:                     js.UndefOr[AsC] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  content:                js.UndefOr[VdomNode] = js.undefined,
  description:            js.UndefOr[ShorthandS[ItemDescription]] = js.undefined,
  extra:                  js.UndefOr[ShorthandS[ItemExtra]] = js.undefined,
  header:                 js.UndefOr[ShorthandS[ItemHeader]] = js.undefined,
  image:                  js.UndefOr[ShorthandS[ItemImage]] = js.undefined,
  meta:                   js.UndefOr[ShorthandS[ItemMeta]] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPAC[Item.ItemProps, Item] {
  override protected def cprops                     = Item.props(this)
  override protected val component                  = Item.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object Item {
  @js.native
  @JSImport("semantic-ui-react", "Item")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait ItemProps extends js.Object {
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

    /** Shorthand for Item component. */
    var content: js.UndefOr[suiraw.SemanticShorthandContent] = js.native

    /** Shorthand for ItemDescription component. */
    var description
      : js.UndefOr[suiraw.SemanticShorthandItemS[ItemDescription.ItemDescriptionProps]] = js.native

    /** Shorthand for ItemExtra component. */
    var extra: js.UndefOr[suiraw.SemanticShorthandItemS[ItemExtra.ItemExtraProps]] = js.native

    /** Shorthand for ItemHeader component. */
    var header: js.UndefOr[suiraw.SemanticShorthandItemS[ItemHeader.ItemHeaderProps]] = js.native

    /** Shorthand for ItemImage component. */
    var image: js.UndefOr[suiraw.SemanticShorthandItemS[ItemImage.ItemImageProps]] = js.native

    /** Shorthand for ItemMeta component. */
    var meta: js.UndefOr[suiraw.SemanticShorthandItemS[ItemMeta.ItemMetaProps]] = js.native
  }

  def props(q: Item): ItemProps =
    rawprops(q.as,
             q.className,
             q.clazz,
             q.content,
             q.description,
             q.extra,
             q.header,
             q.image,
             q.meta
    )

  def rawprops(
    as:          js.UndefOr[AsC] = js.undefined,
    className:   js.UndefOr[String] = js.undefined,
    clazz:       js.UndefOr[Css] = js.undefined,
    content:     js.UndefOr[VdomNode] = js.undefined,
    description: js.UndefOr[ShorthandS[ItemDescription]] = js.undefined,
    extra:       js.UndefOr[ShorthandS[ItemExtra]] = js.undefined,
    header:      js.UndefOr[ShorthandS[ItemHeader]] = js.undefined,
    image:       js.UndefOr[ShorthandS[ItemImage]] = js.undefined,
    meta:        js.UndefOr[ShorthandS[ItemMeta]] = js.undefined
  ): ItemProps = {
    val p = as.toJsObject[ItemProps]
    as.toJs.foreach(v => p.as = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    content.toJs.foreach(v => p.content = v)
    description.toJs.foreach(v => p.description = v)
    extra.toJs.foreach(v => p.extra = v)
    header.toJs.foreach(v => p.header = v)
    image.toJs.foreach(v => p.image = v)
    meta.toJs.foreach(v => p.meta = v)
    p
  }

  private val component =
    JsFnComponent[ItemProps, Children.Varargs](RawComponent)

  def apply(modifiers: TagMod*): Item =
    Item(modifiers = modifiers)
}
