// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.modules.dropdown

import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.JsNumber
import japgolly.scalajs.react.vdom.TagMod
import react.common._
import react.semanticui._

import scala.scalajs.js

import js.annotation._
import js.|

final case class DropdownSearchInput(
  as:                     js.UndefOr[AsC] = js.undefined,
  autoComplete:           js.UndefOr[String] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  tabIndex:               js.UndefOr[String | JsNumber] = js.undefined,
  tpe:                    js.UndefOr[String] = js.undefined,
  value:                  js.UndefOr[JsNumber | String] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPA[DropdownSearchInput.DropdownSearchInputProps, DropdownSearchInput] {
  override protected def cprops = DropdownSearchInput.props(this)
  override val component        = DropdownSearchInput.component
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
    var as: js.UndefOr[AsT] = js.native

    /** An input can have the auto complete. */
    var autoComplete: js.UndefOr[String] = js.native

    /** Additional classes. */
    var className: js.UndefOr[String] = js.native

    /** An input can receive focus. */
    var tabIndex: js.UndefOr[String | JsNumber] = js.native

    /** The HTML input type. */
    var `type`: js.UndefOr[String] = js.native

    /** Stored value. */
    var value: js.UndefOr[JsNumber | String] = js.native
  }

  def props(q: DropdownSearchInput): DropdownSearchInputProps =
    rawprops(q.as, q.autoComplete, q.className, q.clazz, q.tabIndex, q.tpe, q.value)

  def rawprops(
    as:           js.UndefOr[AsC] = js.undefined,
    autoComplete: js.UndefOr[String] = js.undefined,
    className:    js.UndefOr[String] = js.undefined,
    clazz:        js.UndefOr[Css] = js.undefined,
    tabIndex:     js.UndefOr[String | JsNumber] = js.undefined,
    `type`:       js.UndefOr[String] = js.undefined,
    value:        js.UndefOr[JsNumber | String] = js.undefined
  ): DropdownSearchInputProps = {
    val p = as.toJsObject[DropdownSearchInputProps]
    as.toJs.foreachUnchecked(v => p.as = v)
    autoComplete.foreach(v => p.autoComplete = v)
    (className, clazz).cssToJs.foreach(v => p.className = v)
    tabIndex.foreachUnchecked(v => p.tabIndex = v)
    p.`type` = `type`
    value.foreachUnchecked(v => p.value = v)
    p
  }

  private val component =
    JsComponent[DropdownSearchInputProps, Children.None, Null](RawComponent)

  def apply(modifiers: TagMod*): DropdownSearchInput =
    new DropdownSearchInput(modifiers = modifiers)
}
