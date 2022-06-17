package react.semanticui.modules.sidebar

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.JsFnComponent
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.VdomNode
import react.common._
import react.semanticui.raw._
import react.semanticui._
import japgolly.scalajs.react.vdom.TagMod

final case class SidebarPusher(
  as:                     js.UndefOr[AsC] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  content:                js.UndefOr[VdomNode] = js.undefined,
  dimmed:                 js.UndefOr[Boolean] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPAC[SidebarPusher.SidebarPusherProps, SidebarPusher] {
  override protected def cprops                     = SidebarPusher.props(this)
  override protected val component                  = SidebarPusher.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object SidebarPusher {
  @js.native
  @JSImport("semantic-ui-react", "SidebarPusher")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait SidebarPusherProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit          = js.native
    var as: js.UndefOr[AsT]                           = js.native
    var children: js.UndefOr[React.Node]              = js.native
    var className: js.UndefOr[String]                 = js.native
    var content: js.UndefOr[SemanticShorthandContent] = js.native
    var dimmed: js.UndefOr[Boolean]                   = js.native
  }

  def props(q: SidebarPusher): SidebarPusherProps =
    rawprops(q.as, q.className, q.content, q.dimmed)

  def rawprops(
    as:        js.UndefOr[AsC] = js.undefined,
    className: js.UndefOr[String] = js.undefined,
    content:   js.UndefOr[VdomNode] = js.undefined,
    dimmed:    js.UndefOr[Boolean] = js.undefined
  ): SidebarPusherProps = {
    val p = as.toJsObject[SidebarPusherProps]
    as.toJs.foreach(v => p.as = v)
    className.foreach(v => p.className = v)
    content.map(_.rawNode).foreach(v => p.content = v)
    dimmed.foreach(v => p.dimmed = v)
    p
  }

  private val component =
    JsFnComponent[SidebarPusherProps, Children.Varargs](RawComponent)

  def apply(modifiers: TagMod*): SidebarPusher =
    new SidebarPusher(modifiers = modifiers)
}
