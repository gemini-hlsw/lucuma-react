// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.collections.table

import scala.scalajs.js
import js.annotation._
import js.|
import js.JSConverters._
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.html_<^._
import react.common._
import react.semanticui.{raw => suiraw}
import react.semanticui._

final case class TableRow(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  active:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  cellAs:                 MyUndefOr[AsC] = MyUndefOr.undefined,
  cells:                  MyUndefOr[Seq[TableCell | TableHeaderCell]] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  disabled:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  error:                  MyUndefOr[Boolean] = MyUndefOr.undefined,
  negative:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  positive:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  textAlign:              MyUndefOr[TableTextAlign] = MyUndefOr.undefined,
  verticalAlign:          MyUndefOr[SemanticVerticalAlignment] = MyUndefOr.undefined,
  warning:                MyUndefOr[Boolean] = MyUndefOr.undefined,
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
    var as: MyUndefOr[AsT] = js.native

    /** Style as the currently chosen item. */
    var active: MyUndefOr[Boolean] = js.native

    /** An element type to render as (string or function). */
    var cellAs: MyUndefOr[AsT] = js.native

    /** Shorthand array of props for TableCell. */
    var cells
      : MyUndefOr[js.Array[TableCell.TableCellProps | TableHeaderCell.TableHeaderCellProps]] =
      js.native

    /** Primary content. */
    var children: MyUndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: MyUndefOr[String] = js.native

    /** A row can be disabled. */
    var disabled: MyUndefOr[Boolean] = js.native

    /** A row may call attention to an error or a negative value. */
    var error: MyUndefOr[Boolean] = js.native

    /** A row may let a user know whether a value is bad. */
    var negative: MyUndefOr[Boolean] = js.native

    /** A row may let a user know whether a value is good. */
    var positive: MyUndefOr[Boolean] = js.native

    /** A table row can adjust its text alignment. */
    var textAlign: MyUndefOr[String] = js.native

    /** A table row can adjust its vertical alignment. */
    var verticalAlign: MyUndefOr[suiraw.SemanticVERTICALALIGNMENTS] = js.native

    /** A cell may warn a user. */
    var warning: MyUndefOr[Boolean] = js.native
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
