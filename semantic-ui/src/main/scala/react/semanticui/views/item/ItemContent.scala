// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.views.item

import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.vdom.VdomNode
import react.common._
import react.semanticui._
import react.semanticui.{raw => suiraw}

import scala.scalajs.js

import js.annotation._

final case class ItemContent(
  as:                     js.UndefOr[AsC] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  content:                js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
  description:            js.UndefOr[ShorthandS[ItemDescription]] = js.undefined,
  extra:                  js.UndefOr[ShorthandS[ItemExtra]] = js.undefined,
  header:                 js.UndefOr[ShorthandS[ItemHeader]] = js.undefined,
  meta:                   js.UndefOr[ShorthandS[ItemMeta]] = js.undefined,
  verticalAlign:          js.UndefOr[SemanticVerticalAlignment] = js.undefined,
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
    var as: js.UndefOr[AsT] = js.native

    /** Primary content. */
    var children: js.UndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: js.UndefOr[String] = js.native

    /** Shorthand for primary content. */
    var content: js.UndefOr[suiraw.SemanticShorthandContent] = js.native

    /** Shorthand for ItemDescription component. */
    var description
      : js.UndefOr[suiraw.SemanticShorthandItemS[ItemDescription.ItemDescriptionProps]] = js.native

    /** Shorthand for ItemExtra component. */
    var extra: js.UndefOr[suiraw.SemanticShorthandItemS[ItemExtra.ItemExtraProps]] = js.native

    /** Shorthand for ItemHeader component. */
    var header: js.UndefOr[suiraw.SemanticShorthandItemS[ItemHeader.ItemHeaderProps]] = js.native

    /** Shorthand for ItemMeta component. */
    var meta: js.UndefOr[suiraw.SemanticShorthandItemS[ItemMeta.ItemMetaProps]] = js.native

    /** Content can specify its vertical alignment. */
    var verticalAlign: js.UndefOr[suiraw.SemanticVERTICALALIGNMENTS] = js.native
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
    as:            js.UndefOr[AsC] = js.undefined,
    className:     js.UndefOr[String] = js.undefined,
    clazz:         js.UndefOr[Css] = js.undefined,
    content:       js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
    description:   js.UndefOr[ShorthandS[ItemDescription]] = js.undefined,
    extra:         js.UndefOr[ShorthandS[ItemExtra]] = js.undefined,
    header:        js.UndefOr[ShorthandS[ItemHeader]] = js.undefined,
    meta:          js.UndefOr[ShorthandS[ItemMeta]] = js.undefined,
    verticalAlign: js.UndefOr[SemanticVerticalAlignment] = js.undefined
  ): ItemContentProps = {
    val p = as.toJsObject[ItemContentProps]
    as.toJs.foreachUnchecked(v => p.as = v)
    (className, clazz).cssToJs.foreachUnchecked(v => p.className = v)
    content.toJs.foreachUnchecked(v => p.content = v)
    CompFnToPropsS(description).toJs.foreachUnchecked(v => p.description = v)
    CompFnToPropsS(extra).toJs.foreachUnchecked(v => p.extra = v)
    CompFnToPropsS(header).toJs.foreachUnchecked(v => p.header = v)
    CompFnToPropsS(meta).toJs.foreachUnchecked(v => p.meta = v)
    verticalAlign.toJs.foreach(v => p.verticalAlign = v)
    p
  }

  private val component =
    JsFnComponent[ItemContentProps, Children.Varargs](RawComponent)

  def apply(modifiers: TagMod*): ItemContent =
    new ItemContent(modifiers = modifiers)
}
