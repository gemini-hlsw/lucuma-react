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

final case class SidebarPushable(
  as:                     js.UndefOr[AsC] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  content:                js.UndefOr[VdomNode] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPAC[SidebarPushable.SidebarPushableProps, SidebarPushable] {
  override protected def cprops                     = SidebarPushable.props(this)
  override protected val component                  = SidebarPushable.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object SidebarPushable {
  @js.native
  @JSImport("semantic-ui-react", "SidebarPushable")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait SidebarPushableProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit          = js.native
    var as: js.UndefOr[AsT]                           = js.native
    var children: js.UndefOr[React.Node]              = js.native
    var className: js.UndefOr[String]                 = js.native
    var content: js.UndefOr[SemanticShorthandContent] = js.native
  }

  def props(q: SidebarPushable): SidebarPushableProps =
    rawprops(q.as, q.className, q.content)

  def rawprops(
    as:        js.UndefOr[AsC] = js.undefined,
    className: js.UndefOr[String] = js.undefined,
    content:   js.UndefOr[VdomNode] = js.undefined
  ): SidebarPushableProps = {
    val p = as.toJsObject[SidebarPushableProps]
    as.toJs.foreach(v => p.as = v)
    className.foreach(v => p.className = v)
    content.toJs.foreach(v => p.content = v)
    p
  }

  private val component =
    JsFnComponent[SidebarPushableProps, Children.Varargs](RawComponent)

  val Default: SidebarPushable = SidebarPushable()

  val defaultProps: SidebarPushableProps = props(Default)

  def apply(modifiers: TagMod*): SidebarPushable =
    new SidebarPushable(modifiers = modifiers)
}
