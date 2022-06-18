// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.modules.sidebar

import scala.scalajs.js
import scala.scalajs.js.|
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.facade.React.Ref
import japgolly.scalajs.react.vdom.VdomNode
import react.common._
import react.semanticui.raw._
import react.semanticui._
import japgolly.scalajs.react.vdom.TagMod

final case class Sidebar(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  animation:              MyUndefOr[SidebarAnimation] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  content:                MyUndefOr[VdomNode] = MyUndefOr.undefined,
  direction:              MyUndefOr[SidebarDirection] = MyUndefOr.undefined,
  onHideE:                MyUndefOr[Sidebar.OnHide] = MyUndefOr.undefined,
  onHide:                 MyUndefOr[Callback] = MyUndefOr.undefined,
  onHidden:               MyUndefOr[Callback] = MyUndefOr.undefined,
  onShow:                 MyUndefOr[Callback] = MyUndefOr.undefined,
  onVisible:              MyUndefOr[Callback] = MyUndefOr.undefined,
  target:                 MyUndefOr[Ref] = MyUndefOr.undefined,
  visible:                MyUndefOr[Boolean] = MyUndefOr.undefined,
  width:                  MyUndefOr[SidebarWidth] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[Sidebar.SidebarProps, Sidebar] {
  override protected def cprops                     = Sidebar.props(this)
  override protected val component                  = Sidebar.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object Sidebar {
  type OnHide = ReactEvent => Callback

  @js.native
  @JSImport("semantic-ui-react", "Sidebar")
  object RawComponent extends js.Object

  @js.native
  trait SidebarProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit               = js.native
    var as: MyUndefOr[AsT]                                = js.native
    var animation: MyUndefOr[String]                      = js.native
    var children: MyUndefOr[React.Node]                   = js.native
    var className: MyUndefOr[String]                      = js.native
    var content: MyUndefOr[SemanticShorthandContent]      = js.native
    var direction: MyUndefOr[String]                      = js.native
    var onHide: MyUndefOr[js.Function1[ReactEvent, Unit]] = js.native
    var onHidden: MyUndefOr[js.Function0[Unit]]           = js.native
    var onShow: MyUndefOr[js.Function0[Unit]]             = js.native
    var onVisible: MyUndefOr[js.Function0[Unit]]          = js.native
    var target: MyUndefOr[js.Object | Ref]                = js.native
    var visible: MyUndefOr[Boolean]                       = js.native
    var width: MyUndefOr[String]                          = js.native
  }

  def props(q: Sidebar): SidebarProps =
    rawprops(q.as,
             q.animation,
             q.className,
             q.clazz,
             q.content,
             q.direction,
             q.onHideE,
             q.onHide,
             q.onHidden,
             q.onShow,
             q.onVisible,
             q.target,
             q.visible,
             q.width
    )

  def rawprops(
    as:        MyUndefOr[AsC] = MyUndefOr.undefined,
    animation: MyUndefOr[SidebarAnimation] = MyUndefOr.undefined,
    className: MyUndefOr[String] = MyUndefOr.undefined,
    clazz:     MyUndefOr[Css] = MyUndefOr.undefined,
    content:   MyUndefOr[VdomNode] = MyUndefOr.undefined,
    direction: MyUndefOr[SidebarDirection] = MyUndefOr.undefined,
    onHideE:   MyUndefOr[OnHide] = MyUndefOr.undefined,
    onHide:    MyUndefOr[Callback] = MyUndefOr.undefined,
    onHidden:  MyUndefOr[Callback] = MyUndefOr.undefined,
    onShow:    MyUndefOr[Callback] = MyUndefOr.undefined,
    onVisible: MyUndefOr[Callback] = MyUndefOr.undefined,
    target:    MyUndefOr[Ref] = MyUndefOr.undefined,
    visible:   MyUndefOr[Boolean] = MyUndefOr.undefined,
    width:     MyUndefOr[SidebarWidth] = MyUndefOr.undefined
  ): SidebarProps = {
    val p = as.toJsObject[SidebarProps]
    as.toJs.foreach(v => p.as = v)
    animation.toJs.foreach(v => p.animation = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    content.toJs.foreach(v => p.content = v)
    direction.toJs.foreach(v => p.direction = v)
    (onHideE, onHide).toJs.foreach(v => p.onHide = v)
    onHidden.toJs.foreach(v => p.onHidden = v)
    onShow.toJs.foreach(v => p.onShow = v)
    onVisible.toJs.foreach(v => p.onVisible = v)
    target.foreach(v => p.target = v)
    visible.foreach(v => p.visible = v)
    width.toJs.foreach(v => p.width = v)
    p
  }

  private val component =
    JsComponent[SidebarProps, Children.Varargs, Null](RawComponent)

  def apply(modifiers: TagMod*): Sidebar =
    new Sidebar(modifiers = modifiers)

}
