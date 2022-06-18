// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.elements.list

import japgolly.scalajs.react._
import react.common._
import react.semanticui._
import react.semanticui.{raw => suiraw}
import react.semanticui.elements.icon._
import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react.vdom.TagMod

final case class ListIcon(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  bordered:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  circular:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  color:                  MyUndefOr[SemanticColor] = MyUndefOr.undefined,
  corner:                 MyUndefOr[IconCorner] = MyUndefOr.undefined,
  disabled:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  fitted:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  flipped:                MyUndefOr[IconFlip] = MyUndefOr.undefined,
  inverted:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  link:                   MyUndefOr[Boolean] = MyUndefOr.undefined,
  loading:                MyUndefOr[Boolean] = MyUndefOr.undefined,
  name:                   MyUndefOr[suiraw.SemanticICONS] = MyUndefOr.undefined,
  rotated:                MyUndefOr[IconRotated] = MyUndefOr.undefined,
  size:                   MyUndefOr[SemanticSize] = MyUndefOr.undefined,
  ariaLabel:              MyUndefOr[String] = MyUndefOr.undefined,
  verticalAlign:          MyUndefOr[SemanticVerticalAlignment] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPA[ListIcon.ListIconProps, ListIcon] {
  override protected def cprops                     = ListIcon.props(this)
  override protected val component                  = ListIcon.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object ListIcon {
  @js.native
  @JSImport("semantic-ui-react", "ListIcon")
  object RawComponent extends js.Object

  @js.native
  trait ListIconProps extends Icon.IconProps {

    /** An element inside a list can be vertically aligned. */
    var verticalAlign: MyUndefOr[suiraw.SemanticVERTICALALIGNMENTS] = js.native
  }

  def props(
    q: ListIcon
  ): ListIconProps = {
    val p = q.as.toJsObject[ListIconProps]
    q.as.toJs.foreach(v => p.as = v)
    q.bordered.foreach(v => p.bordered = v)
    q.circular.foreach(v => p.circular = v)
    (q.className, q.clazz).toJs.foreach(v => p.className = v)
    q.color.toJs.foreach(v => p.color = v)
    q.corner.toJs.foreach(v => p.corner = v)
    q.disabled.foreach(v => p.disabled = v)
    q.fitted.foreach(v => p.fitted = v)
    q.flipped.toJs.foreach(v => p.flipped = v)
    q.inverted.foreach(v => p.inverted = v)
    q.link.foreach(v => p.link = v)
    q.loading.foreach(v => p.loading = v)
    q.name.foreach(v => p.name = v)
    q.rotated.toJs.foreach(v => p.rotated = v)
    q.size.toJs.foreach(v => p.size = v)
    p.`aria-label` = q.ariaLabel
    q.verticalAlign.toJs.foreach(v => p.verticalAlign = v)
    p
  }

  private val component =
    JsComponent[ListIconProps, Children.None, Null](RawComponent)

  def apply(name: String): ListIcon =
    new ListIcon(as = MyUndefOr.undefined,
                 bordered = MyUndefOr.undefined,
                 circular = MyUndefOr.undefined,
                 className = MyUndefOr.undefined,
                 clazz = MyUndefOr.undefined,
                 color = MyUndefOr.undefined,
                 corner = MyUndefOr.undefined,
                 disabled = MyUndefOr.undefined,
                 fitted = MyUndefOr.undefined,
                 flipped = MyUndefOr.undefined,
                 inverted = MyUndefOr.undefined,
                 link = MyUndefOr.undefined,
                 loading = MyUndefOr.undefined,
                 name = name,
                 rotated = MyUndefOr.undefined,
                 size = MyUndefOr.undefined,
                 ariaLabel = MyUndefOr.undefined,
                 verticalAlign = MyUndefOr.undefined
    )
}
