// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.modules.dropdown

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.JsFnComponent
import react.semanticui._
import react.common._
import japgolly.scalajs.react.vdom.TagMod

final case class DropdownDivider(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPAC[DropdownDivider.DropdownDividerProps, DropdownDivider] {
  override protected def cprops                     = DropdownDivider.props(this)
  override protected val component                  = DropdownDivider.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object DropdownDivider {
  @js.native
  @JSImport("semantic-ui-react", "DropdownDivider")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait DropdownDividerProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: MyUndefOr[AsT] = js.native

    /** Additional classes. */
    var className: MyUndefOr[String] = js.native
  }

  def props(q: DropdownDivider): DropdownDividerProps =
    rawprops(q.as, q.className, q.clazz)

  def rawprops(
    as:        MyUndefOr[AsC] = MyUndefOr.undefined,
    className: MyUndefOr[String] = MyUndefOr.undefined,
    clazz:     MyUndefOr[Css] = MyUndefOr.undefined
  ): DropdownDividerProps = {
    val p = as.toJsObject[DropdownDividerProps]
    as.toJs.foreach(v => p.as = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    p
  }

  private val component =
    JsFnComponent[DropdownDividerProps, Children.Varargs](RawComponent)

  def apply(children: TagMod*): DropdownDivider =
    new DropdownDivider(modifiers = children)
}
