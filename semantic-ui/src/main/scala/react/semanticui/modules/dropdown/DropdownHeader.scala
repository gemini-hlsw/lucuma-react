// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.modules.dropdown

import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.vdom.VdomNode
import react.common._
import react.semanticui._
import react.semanticui.elements.icon.Icon
import react.semanticui.elements.icon.Icon.IconProps
import react.semanticui.raw._
import react.semanticui.{raw => suiraw}

import scala.scalajs.js

import js.annotation._

final case class DropdownHeader(
  as:                     js.UndefOr[AsC] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  content:                js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
  icon:                   js.UndefOr[ShorthandS[Icon]] = js.undefined,
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
    var as: js.UndefOr[AsT] = js.native

    /** Primary content. */
    var children: js.UndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: js.UndefOr[String] = js.native

    /** Shorthand for primary content. */
    var content: js.UndefOr[SemanticShorthandContent] = js.native

    /** Shorthand for Icon. */
    var icon: js.UndefOr[suiraw.SemanticShorthandItemS[IconProps]] = js.native
  }

  def props(q: DropdownHeader): DropdownHeaderProps =
    rawprops(q.as, q.className, q.clazz, q.content, q.icon)

  def rawprops(
    as:        js.UndefOr[AsC] = js.undefined,
    className: js.UndefOr[String] = js.undefined,
    clazz:     js.UndefOr[Css] = js.undefined,
    content:   js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
    icon:      js.UndefOr[ShorthandS[Icon]] = js.undefined
  ): DropdownHeaderProps = {
    val p = as.toJsObject[DropdownHeaderProps]
    as.toJs.foreachUnchecked(v => p.as = v)
    (className, clazz).cssToJs.foreach(v => p.className = v)
    content.toJs.foreachUnchecked(v => p.content = v)
    CompToPropsS(icon).toJs.foreachUnchecked(v => p.icon = v)
    p
  }

  private val component =
    JsComponent[DropdownHeaderProps, Children.Varargs, Null](RawComponent)

  def apply(modifiers: TagMod*): DropdownHeader =
    new DropdownHeader(modifiers = modifiers)
}
