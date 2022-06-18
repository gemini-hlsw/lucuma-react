// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.modules.dropdown

import scala.scalajs.js
import js.annotation._
import js.|
import japgolly.scalajs.react._
import react.semanticui._
import react.common._
import japgolly.scalajs.react.vdom.TagMod

final case class DropdownSearchInput(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  autoComplete:           MyUndefOr[String] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  tabIndex:               MyUndefOr[String | Double] = MyUndefOr.undefined,
  tpe:                    MyUndefOr[String] = MyUndefOr.undefined,
  value:                  MyUndefOr[Double | String] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPA[DropdownSearchInput.DropdownSearchInputProps, DropdownSearchInput] {
  override protected def cprops                     = DropdownSearchInput.props(this)
  override val component                            = DropdownSearchInput.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object DropdownSearchInput {
  @js.native
  @JSImport("semantic-ui-react", "DropdownSearchInput")
  object RawComponent extends js.Object

  @js.native
  trait DropdownSearchInputProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: MyUndefOr[AsT] = js.native

    /** An input can have the auto complete. */
    var autoComplete: MyUndefOr[String] = js.native

    /** Additional classes. */
    var className: MyUndefOr[String] = js.native

    /** An input can receive focus. */
    var tabIndex: MyUndefOr[String | Double] = js.native

    /** The HTML input type. */
    var `type`: MyUndefOr[String] = js.native

    /** Stored value. */
    var value: MyUndefOr[Double | String] = js.native
  }

  def props(q: DropdownSearchInput): DropdownSearchInputProps =
    rawprops(q.as, q.autoComplete, q.className, q.clazz, q.tabIndex, q.tpe, q.value)

  def rawprops(
    as:           MyUndefOr[AsC] = MyUndefOr.undefined,
    autoComplete: MyUndefOr[String] = MyUndefOr.undefined,
    className:    MyUndefOr[String] = MyUndefOr.undefined,
    clazz:        MyUndefOr[Css] = MyUndefOr.undefined,
    tabIndex:     MyUndefOr[String | Double] = MyUndefOr.undefined,
    `type`:       MyUndefOr[String] = MyUndefOr.undefined,
    value:        MyUndefOr[Double | String] = MyUndefOr.undefined
  ): DropdownSearchInputProps = {
    val p = as.toJsObject[DropdownSearchInputProps]
    as.toJs.foreach(v => p.as = v)
    autoComplete.foreach(v => p.autoComplete = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    tabIndex.foreach(v => p.tabIndex = v)
    p.`type` = `type`
    value.foreach(v => p.value = v)
    p
  }

  private val component =
    JsComponent[DropdownSearchInputProps, Children.None, Null](RawComponent)

  def apply(modifiers: TagMod*): DropdownSearchInput =
    new DropdownSearchInput(modifiers = modifiers)
}
