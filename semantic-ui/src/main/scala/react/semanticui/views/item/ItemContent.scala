// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.views.item

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.VdomNode
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.facade.React
import react.common._
import react.semanticui._
import react.semanticui.{raw => suiraw}

final case class ItemContent(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  content:                MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
  description:            MyUndefOr[ShorthandS[ItemDescription]] = MyUndefOr.undefined,
  extra:                  MyUndefOr[ShorthandS[ItemExtra]] = MyUndefOr.undefined,
  header:                 MyUndefOr[ShorthandS[ItemHeader]] = MyUndefOr.undefined,
  meta:                   MyUndefOr[ShorthandS[ItemMeta]] = MyUndefOr.undefined,
  verticalAlign:          MyUndefOr[SemanticVerticalAlignment] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPAC[ItemContent.ItemContentProps, ItemContent] {
  override protected def cprops                     = ItemContent.props(this)
  override protected val component                  = ItemContent.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object ItemContent {
  @js.native
  @JSImport("semantic-ui-react", "ItemContent")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait ItemContentProps extends js.Object {
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

    /** Shorthand for ItemDescription component. */
    var description
      : MyUndefOr[suiraw.SemanticShorthandItemS[ItemDescription.ItemDescriptionProps]] = js.native

    /** Shorthand for ItemExtra component. */
    var extra: MyUndefOr[suiraw.SemanticShorthandItemS[ItemExtra.ItemExtraProps]] = js.native

    /** Shorthand for ItemHeader component. */
    var header: MyUndefOr[suiraw.SemanticShorthandItemS[ItemHeader.ItemHeaderProps]] = js.native

    /** Shorthand for ItemMeta component. */
    var meta: MyUndefOr[suiraw.SemanticShorthandItemS[ItemMeta.ItemMetaProps]] = js.native

    /** Content can specify its vertical alignment. */
    var verticalAlign: MyUndefOr[suiraw.SemanticVERTICALALIGNMENTS] = js.native
  }

  def props(q: ItemContent): ItemContentProps =
    rawprops(q.as,
             q.className,
             q.clazz,
             q.content,
             q.description,
             q.extra,
             q.header,
             q.meta,
             q.verticalAlign
    )

  def rawprops(
    as:            MyUndefOr[AsC] = MyUndefOr.undefined,
    className:     MyUndefOr[String] = MyUndefOr.undefined,
    clazz:         MyUndefOr[Css] = MyUndefOr.undefined,
    content:       MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
    description:   MyUndefOr[ShorthandS[ItemDescription]] = MyUndefOr.undefined,
    extra:         MyUndefOr[ShorthandS[ItemExtra]] = MyUndefOr.undefined,
    header:        MyUndefOr[ShorthandS[ItemHeader]] = MyUndefOr.undefined,
    meta:          MyUndefOr[ShorthandS[ItemMeta]] = MyUndefOr.undefined,
    verticalAlign: MyUndefOr[SemanticVerticalAlignment] = MyUndefOr.undefined
  ): ItemContentProps = {
    val p = as.toJsObject[ItemContentProps]
    as.toJs.foreach(v => p.as = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    content.toJs.foreach(v => p.content = v)
    description.toJs.foreach(v => p.description = v)
    extra.toJs.foreach(v => p.extra = v)
    header.toJs.foreach(v => p.header = v)
    meta.toJs.foreach(v => p.meta = v)
    verticalAlign.toJs.foreach(v => p.verticalAlign = v)
    p
  }

  private val component =
    JsFnComponent[ItemContentProps, Children.Varargs](RawComponent)

  def apply(modifiers: TagMod*): ItemContent =
    new ItemContent(modifiers = modifiers)
}
