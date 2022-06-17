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
  as:                     js.UndefOr[AsC] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  content:                js.UndefOr[VdomNode] = js.undefined,
  direction:              js.UndefOr[MenuDirection] = js.undefined,
  open:                   js.UndefOr[Boolean] = js.undefined,
  scrolling:              js.UndefOr[Boolean] = js.undefined,
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
    var as: js.UndefOr[AsT] = js.native

    /** Primary content. */
    var children: js.UndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: js.UndefOr[String] = js.native

    /** Shorthand for primary content. */
    var content: js.UndefOr[SemanticShorthandContent] = js.native

    /** A dropdown menu can open to the left or to the right. */
    var direction: js.UndefOr[String] = js.native

    /** Whether or not the dropdown menu is displayed. */
    var open: js.UndefOr[Boolean] = js.native

    /** A dropdown menu can scroll. */
    var scrolling: js.UndefOr[Boolean] = js.native
  }

  def props(q: DropdownMenu): DropdownMenuProps =
    rawprops(q.as, q.className, q.clazz, q.content, q.direction, q.open, q.scrolling)

  def rawprops(
    as:        js.UndefOr[AsC] = js.undefined,
    className: js.UndefOr[String] = js.undefined,
    clazz:     js.UndefOr[Css] = js.undefined,
    content:   js.UndefOr[VdomNode] = js.undefined,
    direction: js.UndefOr[MenuDirection] = js.undefined,
    open:      js.UndefOr[Boolean] = js.undefined,
    scrolling: js.UndefOr[Boolean] = js.undefined
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
