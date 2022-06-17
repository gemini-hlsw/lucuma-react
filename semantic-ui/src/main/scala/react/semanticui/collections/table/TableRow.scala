package react.semanticui.collections.table

import scala.scalajs.js
import js.annotation._
import js.|
import js.JSConverters._
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.html_<^._
import react.common._
import react.semanticui.{ raw => suiraw }
import react.semanticui._

final case class TableRow(
  as:                     js.UndefOr[AsC] = js.undefined,
  active:                 js.UndefOr[Boolean] = js.undefined,
  cellAs:                 js.UndefOr[AsC] = js.undefined,
  cells:                  js.UndefOr[Seq[TableCell | TableHeaderCell]] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  disabled:               js.UndefOr[Boolean] = js.undefined,
  error:                  js.UndefOr[Boolean] = js.undefined,
  negative:               js.UndefOr[Boolean] = js.undefined,
  positive:               js.UndefOr[Boolean] = js.undefined,
  textAlign:              js.UndefOr[TableTextAlign] = js.undefined,
  verticalAlign:          js.UndefOr[SemanticVerticalAlignment] = js.undefined,
  warning:                js.UndefOr[Boolean] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[TableRow.TableRowProps, TableRow] {
  override protected def cprops                     = TableRow.props(this)
  override protected val component                  = TableRow.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object TableRow {
  @js.native
  @JSImport("semantic-ui-react", "TableRow")
  object RawComponent extends js.Object

  @js.native
  trait TableRowProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: js.UndefOr[AsT] = js.native

    /** Style as the currently chosen item. */
    var active: js.UndefOr[Boolean] = js.native

    /** An element type to render as (string or function). */
    var cellAs: js.UndefOr[AsT] = js.native

    /** Shorthand array of props for TableCell. */
    var cells
      : js.UndefOr[js.Array[TableCell.TableCellProps | TableHeaderCell.TableHeaderCellProps]] =
      js.native

    /** Primary content. */
    var children: js.UndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: js.UndefOr[String] = js.native

    /** A row can be disabled. */
    var disabled: js.UndefOr[Boolean] = js.native

    /** A row may call attention to an error or a negative value. */
    var error: js.UndefOr[Boolean] = js.native

    /** A row may let a user know whether a value is bad. */
    var negative: js.UndefOr[Boolean] = js.native

    /** A row may let a user know whether a value is good. */
    var positive: js.UndefOr[Boolean] = js.native

    /** A table row can adjust its text alignment. */
    var textAlign: js.UndefOr[String] = js.native

    /** A table row can adjust its vertical alignment. */
    var verticalAlign: js.UndefOr[suiraw.SemanticVERTICALALIGNMENTS] = js.native

    /** A cell may warn a user. */
    var warning: js.UndefOr[Boolean] = js.native
  }

  def props(q: TableRow): TableRowProps = {
    val p = q.as.toJsObject[TableRowProps]
    q.as.toJs.foreach(v => p.as = v)
    q.active.foreach(v => p.active = v)
    q.cellAs.toJs.foreach(v => p.cellAs = v)
    q.cells
      .map(_.map[TableCell.TableCellProps | TableHeaderCell.TableHeaderCellProps] { x =>
        (x: Any) match {
          case tc: TableCell        => tc.props
          case thc: TableHeaderCell => thc.props
          case _                    => sys.error("Shouldn't happen")
        }
      }.toJSArray)
      .foreach(v => p.cells = v)
    (q.className, q.clazz).toJs.foreach(v => p.className = v)
    q.disabled.foreach(v => p.disabled = v)
    q.error.foreach(v => p.error = v)
    q.negative.foreach(v => p.negative = v)
    q.positive.foreach(v => p.positive = v)
    q.textAlign.toJs.foreach(v => p.textAlign = v)
    q.verticalAlign.toJs.foreach(v => p.verticalAlign = v)
    q.warning.foreach(v => p.warning = v)
    p
  }

  private val component = JsComponent[TableRowProps, Children.Varargs, Null](RawComponent)

  def apply(mods: TagMod*): TableRow = TableRow(modifiers = mods)
}
