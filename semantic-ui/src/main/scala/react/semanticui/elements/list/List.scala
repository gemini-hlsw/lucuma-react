package react.semanticui.elements.list

import scala.scalajs.js
import js.annotation._
import js.|
import js.JSConverters._
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.VdomNode
import japgolly.scalajs.react.vdom.html_<^._
import react.common._
import react.semanticui.{ raw => suiraw }
import react.semanticui.raw._
import react.semanticui._

final case class List(
  as:                     js.UndefOr[AsC] = js.undefined,
  animated:               js.UndefOr[Boolean] = js.undefined,
  bulleted:               js.UndefOr[Boolean] = js.undefined,
  celled:                 js.UndefOr[Boolean] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  content:                js.UndefOr[Seq[VdomNode]] = js.undefined,
  divided:                js.UndefOr[Boolean] = js.undefined,
  floated:                js.UndefOr[SemanticFloat] = js.undefined,
  horizontal:             js.UndefOr[Boolean] = js.undefined,
  inverted:               js.UndefOr[Boolean] = js.undefined,
  items:                  js.UndefOr[Seq[ShorthandS[ListItem]]] = js.undefined,
  onItemClickE:           js.UndefOr[List.OnItemClick] = js.undefined,
  onItemClick:            js.UndefOr[Callback] = js.undefined,
  link:                   js.UndefOr[Boolean] = js.undefined,
  ordered:                js.UndefOr[Boolean] = js.undefined,
  relaxed:                js.UndefOr[Boolean] = js.undefined,
  selection:              js.UndefOr[Boolean] = js.undefined,
  size:                   js.UndefOr[SemanticSize] = js.undefined,
  verticalAlign:          js.UndefOr[SemanticVerticalAlignment] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[List.ListProps, List] {
  override protected def cprops                     = List.props(this)
  override protected val component                  = List.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object List {
  type OnItemClick = (ReactMouseEvent, ListItem.ListItemProps) => Callback

  @js.native
  @JSImport("semantic-ui-react", "List")
  object RawComponent extends js.Object

  @js.native
  trait ListProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: js.UndefOr[AsT] = js.native

    /** A list can animate to set the current item apart from the list. */
    var animated: js.UndefOr[Boolean] = js.native

    /** A list can mark items with a bullet. */
    var bulleted: js.UndefOr[Boolean] = js.native

    /** A list can divide its items into cells. */
    var celled: js.UndefOr[Boolean] = js.native

    /** Primary content. */
    var children: js.UndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: js.UndefOr[String] = js.native

    /** Shorthand for primary content. */
    var content: js.UndefOr[js.Array[SemanticShorthandContent]] = js.native

    /** A list can show divisions between content. */
    var divided: js.UndefOr[Boolean] = js.native

    /** An list can be floated left or right. */
    var floated: js.UndefOr[suiraw.SemanticFLOATS] = js.native

    /** A list can be formatted to have items appear horizontally. */
    var horizontal: js.UndefOr[Boolean] = js.native

    /** A list can be inverted to appear on a dark background. */
    var inverted: js.UndefOr[Boolean] = js.native

    /** Shorthand array of props for ListItem. */
    var items: js.UndefOr[js.Array[suiraw.SemanticShorthandItemS[ListItem.ListItemProps]]] =
      js.native

    /** A list can be specially formatted for navigation links. */
    var link: js.UndefOr[Boolean] = js.native

    /**
     * onClick handler for ListItem. Mutually exclusive with children.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All item props.
     */
    var onItemClick: js.UndefOr[js.Function2[ReactMouseEvent, ListItem.ListItemProps, Unit]] =
      js.native

    /** A list can be ordered numerically. */
    var ordered: js.UndefOr[Boolean] = js.native

    /** A list can relax its padding to provide more negative space. */
    var relaxed: js.UndefOr[Boolean | String] = js.native

    /** A selection list formats list items as possible choices. */
    var selection: js.UndefOr[Boolean] = js.native

    /** A list can vary in size. */
    var size: js.UndefOr[suiraw.SemanticSIZES] = js.native

    /** An element inside a list can be vertically aligned. */
    var verticalAlign: js.UndefOr[suiraw.SemanticVERTICALALIGNMENTS] = js.native

  }

  def props(
    q: List
  ): ListProps = {
    val p = q.as.toJsObject[ListProps]
    q.as.toJs.foreach(v => p.as = v)
    q.animated.foreach(v => p.animated = v)
    q.bulleted.foreach(v => p.bulleted = v)
    q.celled.foreach(v => p.celled = v)
    (q.className, q.clazz).toJs.foreach(v => p.className = v)
    q.content.map(_.map(_.rawNode).toJSArray).foreach(v => p.content = v)
    q.divided.foreach(v => p.divided = v)
    q.floated.toJs.foreach(v => p.floated = v)
    q.horizontal.foreach(v => p.horizontal = v)
    q.inverted.foreach(v => p.inverted = v)
    q.items.toJs.foreach(v => p.items = v)
    (q.onItemClickE, q.onItemClick).toJs.foreach(v => p.onItemClick = v)
    q.link.foreach(v => p.link = v)
    q.ordered.foreach(v => p.ordered = v)
    q.relaxed.foreach(v => p.relaxed = v)
    q.selection.foreach(v => p.selection = v)
    q.size.toJs.foreach(v => p.size = v)
    q.verticalAlign.toJs.foreach(v => p.verticalAlign = v)
    p
  }

  private val component =
    JsComponent[ListProps, Children.Varargs, Null](RawComponent)

  def apply(items: ShorthandS[ListItem]*): List =
    new List(items = items)

}
