// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.collections.table

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.html_<^._
import react.common._
import react.semanticui.elements.icon.Icon
import react.semanticui.elements.icon.Icon.IconProps
import react.semanticui.{raw => suiraw}
import react.semanticui._

final case class TableCell(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  active:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  collapsing:             MyUndefOr[Boolean] = MyUndefOr.undefined,
  content:                MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
  disabled:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  error:                  MyUndefOr[Boolean] = MyUndefOr.undefined,
  icon:                   MyUndefOr[ShorthandS[Icon]] = MyUndefOr.undefined,
  negative:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  positive:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  selectable:             MyUndefOr[Boolean] = MyUndefOr.undefined,
  singleLine:             MyUndefOr[Boolean] = MyUndefOr.undefined,
  textAlign:              MyUndefOr[TableTextAlign] = MyUndefOr.undefined,
  verticalAlign:          MyUndefOr[SemanticVerticalAlignment] = MyUndefOr.undefined,
  warning:                MyUndefOr[Boolean] = MyUndefOr.undefined,
  width:                  MyUndefOr[SemanticWidth] = MyUndefOr.undefined,
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
    var as: MyUndefOr[AsT] = js.native

    /** Style as the currently chosen item. */
    var active: MyUndefOr[Boolean] = js.native

    /** Primary content. */
    var children: MyUndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: MyUndefOr[String] = js.native

    /** A table can be collapsing, taking up only as much space as its rows. */
    var collapsing: MyUndefOr[Boolean] = js.native

    /** Shorthand for primary content. */
    var content: MyUndefOr[suiraw.SemanticShorthandContent] = js.native

    /** A cell can be disabled. */
    var disabled: MyUndefOr[Boolean] = js.native

    /** A cell may call attention to an error or a negative value. */
    var error: MyUndefOr[Boolean] = js.native

    /** Add an Icon by name, props object, or pass an <Icon /> */
    var icon: MyUndefOr[suiraw.SemanticShorthandItemS[IconProps]] = js.native

    /** A cell may let a user know whether a value is bad. */
    var negative: MyUndefOr[Boolean] = js.native

    /** A cell may let a user know whether a value is good. */
    var positive: MyUndefOr[Boolean] = js.native

    /** A cell can be selectable. */
    var selectable: MyUndefOr[Boolean] = js.native

    /** A cell can specify that its contents should remain on a single line and not wrap. */
    var singleLine: MyUndefOr[Boolean] = js.native

    /** A table cell can adjust its text alignment. */
    var textAlign: MyUndefOr[String] = js.native

    /** A table cell can adjust its vertical alignment. */
    var verticalAlign: MyUndefOr[suiraw.SemanticVERTICALALIGNMENTS] = js.native

    /** A cell may warn a user. */
    var warning: MyUndefOr[Boolean] = js.native

    /** A table can specify the width of individual columns independently. */
    var width: MyUndefOr[suiraw.SemanticWIDTHS] = js.native
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
