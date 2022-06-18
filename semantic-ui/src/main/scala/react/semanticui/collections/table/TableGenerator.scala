// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.collections.table

import scala.scalajs.js
import js.annotation._
import js.|
import js.JSConverters._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import react.common._
import react.semanticui.{raw => suiraw}
import react.semanticui._
import react.semanticui.collections.table.TableRow.TableRowProps

final case class TableGenerator[A](
  tableData:              Seq[A],
  renderBodyRow:          TableGenerator.RenderBodyRow[A],
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  attached:               MyUndefOr[TableAttached] = MyUndefOr.undefined,
  basic:                  MyUndefOr[TableBasic] = MyUndefOr.undefined,
  celled:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  collapsing:             MyUndefOr[Boolean] = MyUndefOr.undefined,
  color:                  MyUndefOr[SemanticColor] = MyUndefOr.undefined,
  columns:                MyUndefOr[SemanticWidth] = MyUndefOr.undefined,
  compact:                MyUndefOr[TableCompact] = MyUndefOr.undefined,
  definition:             MyUndefOr[Boolean] = MyUndefOr.undefined,
  fixed:                  MyUndefOr[Boolean] = MyUndefOr.undefined,
  footerRow:              MyUndefOr[TableRow] = MyUndefOr.undefined,
  headerRow:              MyUndefOr[TableRow] = MyUndefOr.undefined,
  headerRows:             MyUndefOr[Seq[TableRow]] = MyUndefOr.undefined,
  inverted:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  padded:                 MyUndefOr[TablePadded] = MyUndefOr.undefined,
  selectable:             MyUndefOr[Boolean] = MyUndefOr.undefined,
  singleLine:             MyUndefOr[Boolean] = MyUndefOr.undefined,
  size:                   MyUndefOr[TableSize] = MyUndefOr.undefined,
  sortable:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  stackable:              MyUndefOr[Boolean] = MyUndefOr.undefined,
  striped:                MyUndefOr[Boolean] = MyUndefOr.undefined,
  structured:             MyUndefOr[Boolean] = MyUndefOr.undefined,
  textAlign:              MyUndefOr[TableTextAlign] = MyUndefOr.undefined,
  unstackable:            MyUndefOr[Boolean] = MyUndefOr.undefined,
  verticalAlign:          MyUndefOr[SemanticVerticalAlignment] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPA[TableGenerator.TableGeneratorProps, TableGenerator[A]] {
  override protected def cprops                     = TableGenerator.props(this)
  override protected val component                  = TableGenerator.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object TableGenerator {
  type RenderBodyRow[A] = (A, Int) => TableRow
  type RawRenderBodyRow = js.Function2[js.Any, Int, TableRowProps]

  @js.native
  @JSImport("semantic-ui-react", "Table")
  object RawComponent extends js.Object

  @js.native
  trait TableGeneratorProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: MyUndefOr[AsT] = js.native

    /** Attach table to other content */
    var attached: MyUndefOr[Boolean | String] = js.native

    /** A table can reduce its complexity to increase readability. */
    var basic: MyUndefOr[Boolean | String] = js.native

    /** A table may be divided each row into separate cells. */
    var celled: MyUndefOr[Boolean] = js.native

    /** Additional classes. */
    var className: MyUndefOr[String] = js.native

    /** A table can be collapsing, taking up only as much space as its rows. */
    var collapsing: MyUndefOr[Boolean] = js.native

    /** A table can be given a color to distinguish it from other tables. */
    var color: MyUndefOr[suiraw.SemanticCOLORS] = js.native

    /** A table can specify its column count to divide its content evenly. */
    var columns: MyUndefOr[String] = js.native

    /** A table may sometimes need to be more compact to make more rows visible at a time. */
    var compact: MyUndefOr[Boolean | String] = js.native

    /** A table may be formatted to emphasize a first column that defines a rows content. */
    var definition: MyUndefOr[Boolean] = js.native

    /**
     * A table can use fixed a special faster form of table rendering that does not resize table
     * cells based on content.
     */
    var fixed: MyUndefOr[Boolean] = js.native

    /** Shorthand for a TableRow to be placed within Table.Footer. */
    var footerRow: MyUndefOr[TableRow.TableRowProps] = js.native

    /** Shorthand for a TableRow to be placed within Table.Header. */
    var headerRow: MyUndefOr[TableRow.TableRowProps] = js.native

    /** Shorthand for multiple TableRows to be placed within Table.Header. */
    var headerRows: MyUndefOr[js.Array[TableRow.TableRowProps]] = js.native

    /** A table's colors can be inverted. */
    var inverted: MyUndefOr[Boolean] = js.native

    /** A table may sometimes need to be more padded for legibility. */
    var padded: MyUndefOr[Boolean | String] = js.native

    /**
     * Mapped over `tableData` and should return shorthand for each Table.Row to be placed within
     * Table.Body.
     *
     * @param {*}
     *   data - An element in the `tableData` array.
     * @param {number}
     *   index - The index of the current element in `tableData`.
     * @return
     *   {*} Shorthand for a Table.Row.
     */
    var renderBodyRow: RawRenderBodyRow = js.native

    /** A table can have its rows appear selectable. */
    var selectable: MyUndefOr[Boolean] = js.native

    /** A table can specify that its cell contents should remain on a single line and not wrap. */
    var singleLine: MyUndefOr[Boolean] = js.native

    /** A table can also be small or large. */
    var size: MyUndefOr[Boolean | String] = js.native

    /** A table may allow a user to sort contents by clicking on a table header. */
    var sortable: MyUndefOr[Boolean] = js.native

    /** A table can specify how it stacks table content responsively. */
    var stackable: MyUndefOr[Boolean] = js.native

    /** A table can stripe alternate rows of content with a darker color to increase contrast. */
    var striped: MyUndefOr[Boolean] = js.native

    /** A table can be formatted to display complex structured data. */
    var structured: MyUndefOr[Boolean] = js.native

    /** Data to be passed to the renderBodyRow function. */
    var tableData: MyUndefOr[js.Array[js.Any]] = js.native

    /** A table can adjust its text alignment. */
    var textAlign: MyUndefOr[Boolean | String] = js.native

    /** A table can specify how it stacks table content responsively. */
    var unstackable: MyUndefOr[Boolean] = js.native

    /** A table can adjust its text alignment. */
    var verticalAlign: MyUndefOr[suiraw.SemanticVERTICALALIGNMENTS]
  }

  def props[A](q: TableGenerator[A]): TableGeneratorProps = {
    val p = q.as.toJsObject[TableGeneratorProps]
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
    q.footerRow.foreach(v => p.footerRow = v.props)
    q.headerRow.foreach(v => p.headerRow = v.props)
    q.headerRows.map(_.map(_.props).toJSArray).foreach(v => p.headerRows = v)
    q.inverted.foreach(v => p.inverted = v)
    q.padded.toJs.foreach(v => p.padded = v)
    p.renderBodyRow = (item: js.Any, index: Int) =>
      q.renderBodyRow(item.asInstanceOf[A], index).props
    q.selectable.foreach(v => p.selectable = v)
    q.singleLine.foreach(v => p.singleLine = v)
    q.size.toJs.foreach(v => p.size = v)
    q.sortable.foreach(v => p.sortable = v)
    q.stackable.foreach(v => p.stackable = v)
    q.striped.foreach(v => p.striped = v)
    q.structured.foreach(v => p.structured = v)
    p.tableData = q.tableData.map(_.asInstanceOf[js.Any]).toJSArray
    q.unstackable.foreach(v => p.unstackable = v)
    q.verticalAlign.toJs.foreach(v => p.verticalAlign = v)
    p
  }

  private val component = JsComponent[TableGeneratorProps, Children.None, Null](RawComponent)
}
