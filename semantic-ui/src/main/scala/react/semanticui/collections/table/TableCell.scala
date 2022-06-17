package react.semanticui.collections.table

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.html_<^._
import react.common._
import react.semanticui.elements.icon.Icon
import react.semanticui.elements.icon.Icon.IconProps
import react.semanticui.{ raw => suiraw }
import react.semanticui._

final case class TableCell(
  as:                     js.UndefOr[AsC] = js.undefined,
  active:                 js.UndefOr[Boolean] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  collapsing:             js.UndefOr[Boolean] = js.undefined,
  content:                js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
  disabled:               js.UndefOr[Boolean] = js.undefined,
  error:                  js.UndefOr[Boolean] = js.undefined,
  icon:                   js.UndefOr[ShorthandS[Icon]] = js.undefined,
  negative:               js.UndefOr[Boolean] = js.undefined,
  positive:               js.UndefOr[Boolean] = js.undefined,
  selectable:             js.UndefOr[Boolean] = js.undefined,
  singleLine:             js.UndefOr[Boolean] = js.undefined,
  textAlign:              js.UndefOr[TableTextAlign] = js.undefined,
  verticalAlign:          js.UndefOr[SemanticVerticalAlignment] = js.undefined,
  warning:                js.UndefOr[Boolean] = js.undefined,
  width:                  js.UndefOr[SemanticWidth] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[TableCell.TableCellProps, TableCell] {
  override protected def cprops                     = TableCell.props(this)
  override protected val component                  = TableCell.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object TableCell {
  @js.native
  @JSImport("semantic-ui-react", "TableCell")
  object RawComponent extends js.Object

  @js.native
  trait TableCellProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: js.UndefOr[AsT] = js.native

    /** Style as the currently chosen item. */
    var active: js.UndefOr[Boolean] = js.native

    /** Primary content. */
    var children: js.UndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: js.UndefOr[String] = js.native

    /** A table can be collapsing, taking up only as much space as its rows. */
    var collapsing: js.UndefOr[Boolean] = js.native

    /** Shorthand for primary content. */
    var content: js.UndefOr[suiraw.SemanticShorthandContent] = js.native

    /** A cell can be disabled. */
    var disabled: js.UndefOr[Boolean] = js.native

    /** A cell may call attention to an error or a negative value. */
    var error: js.UndefOr[Boolean] = js.native

    /** Add an Icon by name, props object, or pass an <Icon /> */
    var icon: js.UndefOr[suiraw.SemanticShorthandItemS[IconProps]] = js.native

    /** A cell may let a user know whether a value is bad. */
    var negative: js.UndefOr[Boolean] = js.native

    /** A cell may let a user know whether a value is good. */
    var positive: js.UndefOr[Boolean] = js.native

    /** A cell can be selectable. */
    var selectable: js.UndefOr[Boolean] = js.native

    /** A cell can specify that its contents should remain on a single line and not wrap. */
    var singleLine: js.UndefOr[Boolean] = js.native

    /** A table cell can adjust its text alignment. */
    var textAlign: js.UndefOr[String] = js.native

    /** A table cell can adjust its vertical alignment. */
    var verticalAlign: js.UndefOr[suiraw.SemanticVERTICALALIGNMENTS] = js.native

    /** A cell may warn a user. */
    var warning: js.UndefOr[Boolean] = js.native

    /** A table can specify the width of individual columns independently. */
    var width: js.UndefOr[suiraw.SemanticWIDTHS] = js.native
  }

  def props(q: TableCell): TableCellProps = {
    val p = q.as.toJsObject[TableCellProps]
    q.as.toJs.foreach(v => p.as = v)
    q.active.foreach(v => p.active = v)
    (q.className, q.clazz).toJs.foreach(v => p.className = v)
    q.collapsing.foreach(v => p.collapsing = v)
    q.content.toJs.foreach(v => p.content = v)
    q.disabled.foreach(v => p.disabled = v)
    q.error.foreach(v => p.error = v)
    q.icon.toJs.foreach(v => p.icon = v)
    q.negative.foreach(v => p.negative = v)
    q.positive.foreach(v => p.positive = v)
    q.selectable.foreach(v => p.selectable = v)
    q.singleLine.foreach(v => p.singleLine = v)
    q.textAlign.toJs.foreach(v => p.textAlign = v)
    q.verticalAlign.toJs.foreach(v => p.verticalAlign = v)
    q.warning.foreach(v => p.warning = v)
    q.width.toJs.foreach(v => p.width = v)
    p
  }

  private val component = JsComponent[TableCellProps, Children.Varargs, Null](RawComponent)

  def apply(c: ShorthandS[VdomNode]): TableCell = TableCell(content = c)
}
