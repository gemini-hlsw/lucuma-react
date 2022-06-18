// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.modules.dropdown

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.VdomNode
import react.common._
import react.semanticui.{raw => suiraw}
import react.semanticui.raw._
import react.semanticui.elements.icon.Icon
import react.semanticui.elements.icon.Icon.IconProps

import react.semanticui._
import japgolly.scalajs.react.vdom.TagMod

final case class DropdownHeader(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  content:                MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
  icon:                   MyUndefOr[ShorthandS[Icon]] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[DropdownHeader.DropdownHeaderProps, DropdownHeader] {
  override protected def cprops                     = DropdownHeader.props(this)
  override protected val component                  = DropdownHeader.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object DropdownHeader {
  @js.native
  @JSImport("semantic-ui-react", "DropdownHeader")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait DropdownHeaderProps extends js.Object {
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
    var content: MyUndefOr[SemanticShorthandContent] = js.native

    /** Shorthand for Icon. */
    var icon: MyUndefOr[suiraw.SemanticShorthandItemS[IconProps]] = js.native
  }

  def props(q: DropdownHeader): DropdownHeaderProps =
    rawprops(q.as, q.className, q.clazz, q.content, q.icon)

  def rawprops(
    as:        MyUndefOr[AsC] = MyUndefOr.undefined,
    className: MyUndefOr[String] = MyUndefOr.undefined,
    clazz:     MyUndefOr[Css] = MyUndefOr.undefined,
    content:   MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
    icon:      MyUndefOr[ShorthandS[Icon]] = MyUndefOr.undefined
  ): DropdownHeaderProps = {
    val p = as.toJsObject[DropdownHeaderProps]
    as.toJs.foreach(v => p.as = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    content.toJs.foreach(v => p.content = v)
    icon.toJs.foreach(v => p.icon = v)
    p
  }

  private val component =
    JsComponent[DropdownHeaderProps, Children.Varargs, Null](RawComponent)

  def apply(modifiers: TagMod*): DropdownHeader =
    new DropdownHeader(modifiers = modifiers)
}
