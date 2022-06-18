// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.elements.icon

import japgolly.scalajs.react._
import react.common._
import react.semanticui._
import react.semanticui.{raw => suiraw}
import scala.scalajs.js
import scala.scalajs.js.|
import js.annotation._
import japgolly.scalajs.react.vdom.TagMod

final case class Icon(
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
  name:                   MyUndefOr[String] = MyUndefOr.undefined,
  rotated:                MyUndefOr[IconRotated] = MyUndefOr.undefined,
  size:                   MyUndefOr[SemanticSize] = MyUndefOr.undefined,
  ariaLabel:              MyUndefOr[String] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPA[Icon.IconProps, Icon] {
  override protected def cprops                     = Icon.props(this)
  override val component                            = Icon.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object Icon {
  @js.native
  @JSImport("semantic-ui-react", "Icon")
  object RawComponent extends js.Object

  @js.native
  trait IconProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit     = js.native
    var as: MyUndefOr[AsT]                      = js.native
    var bordered: MyUndefOr[Boolean]            = js.native
    var circular: MyUndefOr[Boolean]            = js.native
    var className: MyUndefOr[String]            = js.native
    var color: MyUndefOr[suiraw.SemanticCOLORS] = js.native
    var corner: MyUndefOr[Boolean | String]     = js.native
    var disabled: MyUndefOr[Boolean]            = js.native
    var fitted: MyUndefOr[Boolean]              = js.native
    var flipped: MyUndefOr[String]              = js.native
    var inverted: MyUndefOr[Boolean]            = js.native
    var link: MyUndefOr[Boolean]                = js.native
    var loading: MyUndefOr[Boolean]             = js.native
    var name: MyUndefOr[suiraw.SemanticICONS]   = js.native
    var rotated: MyUndefOr[String]              = js.native
    var size: MyUndefOr[suiraw.SemanticSIZES]   = js.native
    var `aria-label`: MyUndefOr[String]         = js.native
  }

  def props(
    q: Icon
  ): IconProps = {
    val p = q.as.toJsObject[IconProps]
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
    p
  }

  private val component =
    JsComponent[IconProps, Children.None, Null](RawComponent)

  def apply(name: String): Icon =
    new Icon(name = name)
}
