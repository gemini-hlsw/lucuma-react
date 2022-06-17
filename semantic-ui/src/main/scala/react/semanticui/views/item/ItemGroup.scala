package react.semanticui.views.item

import scala.scalajs.js
import js.annotation._
import js.|
import js.JSConverters._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.VdomNode
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.facade.React
import react.common._
import react.semanticui._
import react.semanticui.{ raw => suiraw }

final case class ItemGroup(
  as:                     js.UndefOr[AsC] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  content:                js.UndefOr[VdomNode] = js.undefined,
  divided:                js.UndefOr[Boolean] = js.undefined,
  items:                  js.UndefOr[Seq[Item]] = js.undefined,
  link:                   js.UndefOr[Boolean] = js.undefined,
  relaxed:                js.UndefOr[ItemRelaxed] = js.undefined,
  unstackable:            js.UndefOr[Boolean] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPAC[ItemGroup.ItemGroupProps, ItemGroup] {
  override protected def cprops                     = ItemGroup.props(this)
  override protected val component                  = ItemGroup.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object ItemGroup {
  @js.native
  @JSImport("semantic-ui-react", "ItemGroup")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait ItemGroupProps extends js.Object {
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

    /** Items can be divided to better distinguish between grouped content. */
    var divided: js.UndefOr[Boolean] = js.native

    /** Shorthand array of props for Item. */
    var items: js.UndefOr[js.Array[Item.ItemProps]] = js.native

    /** An item can be formatted so that the entire contents link to another page. */
    var link: js.UndefOr[Boolean] = js.native

    /** A group of items can relax its padding to provide more negative space. */
    var relaxed: js.UndefOr[Boolean | String] = js.native

    /** Prevent items from stacking on mobile. */
    var unstackable: js.UndefOr[Boolean] = js.native
  }

  def props(q: ItemGroup): ItemGroupProps =
    rawprops(q.as,
             q.className,
             q.clazz,
             q.content,
             q.divided,
             q.items,
             q.link,
             q.relaxed,
             q.unstackable
    )

  def rawprops(
    as:          js.UndefOr[AsC] = js.undefined,
    className:   js.UndefOr[String] = js.undefined,
    clazz:       js.UndefOr[Css] = js.undefined,
    content:     js.UndefOr[VdomNode] = js.undefined,
    divided:     js.UndefOr[Boolean] = js.undefined,
    items:       js.UndefOr[Seq[Item]] = js.undefined,
    link:        js.UndefOr[Boolean] = js.undefined,
    relaxed:     js.UndefOr[ItemRelaxed] = js.undefined,
    unstackable: js.UndefOr[Boolean] = js.undefined
  ): ItemGroupProps = {
    val p = as.toJsObject[ItemGroupProps]
    as.toJs.foreach(v => p.as = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    content.toJs.foreach(v => p.content = v)
    divided.foreach(v => p.divided = v)
    items.map(_.map(_.props).toJSArray).foreach(v => p.items = v)
    link.foreach(v => p.link = v)
    relaxed.toJs.foreach(v => p.relaxed = v)
    unstackable.foreach(v => p.unstackable = v)
    p
  }

  private val component =
    JsFnComponent[ItemGroupProps, Children.Varargs](RawComponent)

  def apply(modifiers: TagMod*): ItemGroup =
    new ItemGroup(modifiers = modifiers)
}
