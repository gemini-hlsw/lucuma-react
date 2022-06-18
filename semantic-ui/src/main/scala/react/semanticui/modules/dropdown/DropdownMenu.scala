// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.modules.dropdown

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.VdomNode
import react.common._
import react.semanticui.raw._
import react.semanticui._
import japgolly.scalajs.react.vdom.TagMod

final case class DropdownMenu(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  content:                MyUndefOr[VdomNode] = MyUndefOr.undefined,
  direction:              MyUndefOr[MenuDirection] = MyUndefOr.undefined,
  open:                   MyUndefOr[Boolean] = MyUndefOr.undefined,
  scrolling:              MyUndefOr[Boolean] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[DropdownMenu.DropdownMenuProps, DropdownMenu] {
  override protected def cprops                     = DropdownMenu.props(this)
  override protected val component                  = DropdownMenu.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object DropdownMenu {
  @js.native
  @JSImport("semantic-ui-react", "DropdownMenu")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait DropdownMenuProps extends js.Object {
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

    /** A dropdown menu can open to the left or to the right. */
    var direction: MyUndefOr[String] = js.native

    /** Whether or not the dropdown menu is displayed. */
    var open: MyUndefOr[Boolean] = js.native

    /** A dropdown menu can scroll. */
    var scrolling: MyUndefOr[Boolean] = js.native
  }

  def props(q: DropdownMenu): DropdownMenuProps =
    rawprops(q.as, q.className, q.clazz, q.content, q.direction, q.open, q.scrolling)

  def rawprops(
    as:        MyUndefOr[AsC] = MyUndefOr.undefined,
    className: MyUndefOr[String] = MyUndefOr.undefined,
    clazz:     MyUndefOr[Css] = MyUndefOr.undefined,
    content:   MyUndefOr[VdomNode] = MyUndefOr.undefined,
    direction: MyUndefOr[MenuDirection] = MyUndefOr.undefined,
    open:      MyUndefOr[Boolean] = MyUndefOr.undefined,
    scrolling: MyUndefOr[Boolean] = MyUndefOr.undefined
  ): DropdownMenuProps = {
    val p = as.toJsObject[DropdownMenuProps]
    as.toJs.foreach(v => p.as = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    content.toJs.foreach(v => p.content = v)
    direction.toJs.foreach(v => p.direction = v)
    open.foreach(v => p.open = v)
    scrolling.foreach(v => p.scrolling = v)
    p
  }

  private val component =
    JsComponent[DropdownMenuProps, Children.Varargs, Null](RawComponent)

  def apply(children: TagMod*): DropdownMenu =
    DropdownMenu(modifiers = children)
}
