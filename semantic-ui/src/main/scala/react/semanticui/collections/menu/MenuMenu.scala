package react.semanticui.collections.menu

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import react.common._
import react.semanticui._
import react.semanticui.{ raw => suiraw }
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.vdom.VdomNode

final case class MenuMenu(
  as:                     js.UndefOr[AsC] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  content:                js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
  position:               js.UndefOr[MenuMenuPosition] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPAC[MenuMenu.MenuMenuProps, MenuMenu] {
  override protected def cprops                     = MenuMenu.props(this)
  override protected val component                  = MenuMenu.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object MenuMenu {
  @js.native
  @JSImport("semantic-ui-react", "MenuMenu")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait MenuMenuProps extends js.Object {
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
    var content: js.UndefOr[suiraw.SemanticShorthandContent] = js.native

    /** A sub menu can take left or right position. */
    var position: js.UndefOr[String] = js.native
  }

  def props(q: MenuMenu): MenuMenuProps =
    rawprops(q.as, q.className, q.clazz, q.content, q.position)

  def rawprops(
    as:        js.UndefOr[AsC] = js.undefined,
    className: js.UndefOr[String] = js.undefined,
    clazz:     js.UndefOr[Css] = js.undefined,
    content:   js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
    position:  js.UndefOr[MenuMenuPosition] = js.undefined
  ): MenuMenuProps = {
    val p = as.toJsObject[MenuMenuProps]
    as.toJs.foreach(v => p.as = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    content.toJs.foreach(v => p.content = v)
    position.toJs.foreach(v => p.position = v)
    p
  }

  private val component =
    JsFnComponent[MenuMenuProps, Children.Varargs](RawComponent)

  def apply(modifiers: TagMod*): MenuMenu =
    new MenuMenu(modifiers = modifiers)
}
