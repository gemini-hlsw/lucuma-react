package react.semanticui.collections.table

import scala.scalajs.js
import js.annotation._
import js.|
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.html_<^._
import react.common._
import react.semanticui.{ raw => suiraw }
import react.semanticui._

final case class Table(
  as:                     js.UndefOr[AsC] = js.undefined,
  attached:               js.UndefOr[TableAttached] = js.undefined,
  basic:                  js.UndefOr[TableBasic] = js.undefined,
  celled:                 js.UndefOr[Boolean] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  collapsing:             js.UndefOr[Boolean] = js.undefined,
  color:                  js.UndefOr[SemanticColor] = js.undefined,
  columns:                js.UndefOr[SemanticWidth] = js.undefined,
  compact:                js.UndefOr[TableCompact] = js.undefined,
  definition:             js.UndefOr[Boolean] = js.undefined,
  fixed:                  js.UndefOr[Boolean] = js.undefined,
  inverted:               js.UndefOr[Boolean] = js.undefined,
  padded:                 js.UndefOr[TablePadded] = js.undefined,
  selectable:             js.UndefOr[Boolean] = js.undefined,
  singleLine:             js.UndefOr[Boolean] = js.undefined,
  size:                   js.UndefOr[TableSize] = js.undefined,
  sortable:               js.UndefOr[Boolean] = js.undefined,
  stackable:              js.UndefOr[Boolean] = js.undefined,
  striped:                js.UndefOr[Boolean] = js.undefined,
  structured:             js.UndefOr[Boolean] = js.undefined,
  textAlign:              js.UndefOr[TableTextAlign] = js.undefined,
  unstackable:            js.UndefOr[Boolean] = js.undefined,
  verticalAlign:          js.UndefOr[SemanticVerticalAlignment] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[Table.TableProps, Table] {
  override protected def cprops                     = Table.props(this)
  override protected val component                  = Table.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object Table {
  @js.native
  @JSImport("semantic-ui-react", "Table")
  object RawComponent extends js.Object

  @js.native
  trait TableProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: js.UndefOr[AsT] = js.native

    /** Attach table to other content */
    var attached: js.UndefOr[Boolean | String] = js.native

    /** A table can reduce its complexity to increase readability. */
    var basic: js.UndefOr[Boolean | String] = js.native

    /** A table may be divided each row into separate cells. */
    var celled: js.UndefOr[Boolean] = js.native

    /** Primary content. */
    var children: js.UndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: js.UndefOr[String] = js.native

    /** A table can be collapsing, taking up only as much space as its rows. */
    var collapsing: js.UndefOr[Boolean] = js.native

    /** A table can be given a color to distinguish it from other tables. */
    var color: js.UndefOr[suiraw.SemanticCOLORS] = js.native

    /** A table can specify its column count to divide its content evenly. */
    var columns: js.UndefOr[String] = js.native

    /** A table may sometimes need to be more compact to make more rows visible at a time. */
    var compact: js.UndefOr[Boolean | String] = js.native

    /** A table may be formatted to emphasize a first column that defines a rows content. */
    var definition: js.UndefOr[Boolean] = js.native

    /**
     * A table can use fixed a special faster form of table rendering that does not resize table
     * cells based on content.
     */
    var fixed: js.UndefOr[Boolean] = js.native

    /** A table's colors can be inverted. */
    var inverted: js.UndefOr[Boolean] = js.native

    /** A table may sometimes need to be more padded for legibility. */
    var padded: js.UndefOr[Boolean | String] = js.native

    /** A table can have its rows appear selectable. */
    var selectable: js.UndefOr[Boolean] = js.native

    /** A table can specify that its cell contents should remain on a single line and not wrap. */
    var singleLine: js.UndefOr[Boolean] = js.native

    /** A table can also be small or large. */
    var size: js.UndefOr[Boolean | String] = js.native

    /** A table may allow a user to sort contents by clicking on a table header. */
    var sortable: js.UndefOr[Boolean] = js.native

    /** A table can specify how it stacks table content responsively. */
    var stackable: js.UndefOr[Boolean] = js.native

    /** A table can stripe alternate rows of content with a darker color to increase contrast. */
    var striped: js.UndefOr[Boolean] = js.native

    /** A table can be formatted to display complex structured data. */
    var structured: js.UndefOr[Boolean] = js.native

    /** A table can adjust its text alignment. */
    var textAlign: js.UndefOr[Boolean | String] = js.native

    /** A table can specify how it stacks table content responsively. */
    var unstackable: js.UndefOr[Boolean] = js.native

    /** A table can adjust its text alignment. */
    var verticalAlign: js.UndefOr[suiraw.SemanticVERTICALALIGNMENTS]
  }

  def props[A](q: Table): TableProps = {
    val p = q.as.toJsObject[TableProps]
    q.as.toJs.foreach(v => p.as = v)
    q.attached.toJs.foreach(v => p.attached = v)
    q.basic.toJs.foreach(v => p.basic = v)
    q.celled.foreach(v => p.celled = v)
    (q.className, q.clazz).toJs.foreach(v => p.className = v)
    q.collapsing.foreach(v => p.collapsing = v)
    q.color.toJs.foreach(v => p.color = v)
    q.columns.toJs.foreach(v => p.columns = v)
    q.compact.toJs.foreach(v => p.compact = v)
    q.definition.foreach(v => p.definition = v)
    q.fixed.foreach(v => p.fixed = v)
    q.inverted.foreach(v => p.inverted = v)
    q.padded.toJs.foreach(v => p.padded = v)
    q.selectable.foreach(v => p.selectable = v)
    q.singleLine.foreach(v => p.singleLine = v)
    q.size.toJs.foreach(v => p.size = v)
    q.sortable.foreach(v => p.sortable = v)
    q.stackable.foreach(v => p.stackable = v)
    q.striped.foreach(v => p.striped = v)
    q.structured.foreach(v => p.structured = v)
    q.unstackable.foreach(v => p.unstackable = v)
    q.verticalAlign.toJs.foreach(v => p.verticalAlign = v)
    p
  }

  private val component = JsComponent[TableProps, Children.Varargs, Null](RawComponent)

  def apply[A](mods: TagMod*): Table = Table(modifiers = mods)

}
