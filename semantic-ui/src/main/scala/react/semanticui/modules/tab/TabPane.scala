// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.modules.tab

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.JsFnComponent
import japgolly.scalajs.react.vdom.VdomNode
import react.common._
import react.semanticui._
import react.semanticui.raw._
import japgolly.scalajs.react.vdom.TagMod

final case class TabPane(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  active:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  content:                MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
  loading:                MyUndefOr[Boolean] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPAC[TabPane.TabPaneProps, TabPane] {
  override protected def cprops                     = TabPane.props(this)
  override protected val component                  = TabPane.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object TabPane {
  @js.native
  @JSImport("semantic-ui-react", "TabPane")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait TabPaneProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: MyUndefOr[AsT] = js.native

    /** A tab pane can be active. */
    var active: MyUndefOr[Boolean] = js.native

    /** Primary content. */
    var children: MyUndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: MyUndefOr[String] = js.native

    /** Shorthand for primary content. */
    var content: MyUndefOr[SemanticShorthandContent] = js.native

    /** Additional classes. */
    var loading: MyUndefOr[Boolean] = js.native
  }

  def props(
    q: TabPane
  ): TabPaneProps = {
    val p = q.as.toJsObject[TabPaneProps]
    q.as.toJs.foreach(v => p.as = v)
    q.active.foreach(v => p.active = v)
    (q.className, q.clazz).toJs.foreach(v => p.className = v)
    q.content.toJs.foreach(v => p.content = v)
    q.loading.foreach(v => p.loading = v)
    p
  }

  private val component =
    JsFnComponent[TabPaneProps, Children.Varargs](RawComponent)

  def apply(modifiers: TagMod*): TabPane =
    new TabPane(modifiers = modifiers)
}
