// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.views.item

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.vdom.VdomNode
import react.common._
import react.semanticui._
import react.semanticui.elements.image.Image._
import react.semanticui.elements.image._
import react.semanticui.elements.label.Label
import react.semanticui.modules.dimmer.Dimmer

import scala.scalajs.js

import js.annotation._
import js.|

final case class ItemImage(
  as:                     js.UndefOr[AsC] = js.undefined,
  avatar:                 js.UndefOr[Boolean] = js.undefined,
  bordered:               js.UndefOr[Boolean] = js.undefined,
  centered:               js.UndefOr[Boolean] = js.undefined,
  circular:               js.UndefOr[Boolean] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  content:                js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
  disabled:               js.UndefOr[Boolean] = js.undefined,
  dimmer:                 js.UndefOr[Dimmer] = js.undefined,
  floated:                js.UndefOr[SemanticFloat] = js.undefined,
  fluid:                  js.UndefOr[Boolean | String] = js.undefined,
  hidden:                 js.UndefOr[Boolean] = js.undefined,
  href:                   js.UndefOr[String] = js.undefined,
  inline:                 js.UndefOr[Boolean] = js.undefined,
  label:                  js.UndefOr[ShorthandS[Label]] = js.undefined,
  rounded:                js.UndefOr[Boolean] = js.undefined,
  size:                   js.UndefOr[SemanticSize] = js.undefined,
  spaced:                 js.UndefOr[ImageSpaced] = js.undefined,
  src:                    js.UndefOr[String] = js.undefined,
  ui:                     js.UndefOr[Boolean] = js.undefined,
  verticalAlign:          js.UndefOr[SemanticVerticalAlignment] = js.undefined,
  wrapped:                js.UndefOr[Boolean] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPAC[ItemImage.ItemImageProps, ItemImage] {
  override protected def cprops    = ItemImage.props(this)
  override protected val component = ItemImage.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object ItemImage {
  @js.native
  @JSImport("semantic-ui-react", "ItemImage")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait ItemImageProps extends ImageProps {}

  def props(
    q: ItemImage
  ): ItemImageProps = {
    val p = q.as.toJsObject[ItemImageProps]
    q.as.toJs.foreachUnchecked(v => p.as = v)
    q.avatar.foreach(v => p.avatar = v)
    q.bordered.foreach(v => p.bordered = v)
    q.centered.foreach(v => p.centered = v)
    q.circular.foreach(v => p.circular = v)
    (q.className, q.clazz).cssToJs.foreach(v => p.className = v)
    q.content.toJs.foreachUnchecked(v => p.content = v)
    q.disabled.foreach(v => p.disabled = v)
    q.dimmer.map(_.props).foreach(v => p.dimmer = v)
    q.floated.toJs.foreach(v => p.floated = v)
    q.fluid.foreachUnchecked(v => p.fluid = v)
    q.hidden.foreach(v => p.hidden = v)
    q.href.foreach(v => p.href = v)
    q.inline.foreach(v => p.inline = v)
    CompToPropsS(q.label).toJs.foreachUnchecked(v => p.label = v)
    q.rounded.foreach(v => p.rounded = v)
    q.size.toJs.foreach(v => p.size = v)
    q.spaced.toJs.foreachUnchecked(v => p.spaced = v)
    q.src.foreach(v => p.src = v)
    q.ui.foreach(v => p.ui = v)
    q.verticalAlign.toJs.foreach(v => p.verticalAlign = v)
    q.wrapped.foreach(v => p.wrapped = v)
    p
  }

  private val component =
    JsFnComponent[ItemImageProps, Children.Varargs](RawComponent)

  def apply(modifiers: TagMod*): ItemImage =
    new ItemImage(modifiers = modifiers)
}
