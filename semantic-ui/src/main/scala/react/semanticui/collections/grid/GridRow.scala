// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.collections.grid

import scala.scalajs.js
import js.annotation._
import js.|
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import react.common._
import react.semanticui._
import react.semanticui.{raw => suiraw}
import japgolly.scalajs.react.vdom.TagMod

final case class GridRow(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  centered:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  color:                  MyUndefOr[SemanticColor] = MyUndefOr.undefined,
  columns:                MyUndefOr[SemanticWidth | GridColumns] = MyUndefOr.undefined,
  divided:                MyUndefOr[Boolean] = MyUndefOr.undefined,
  only:                   MyUndefOr[GridOnly] = MyUndefOr.undefined,
  reversed:               MyUndefOr[GridReversed] = MyUndefOr.undefined,
  stretched:              MyUndefOr[Boolean] = MyUndefOr.undefined,
  textAlign:              MyUndefOr[SemanticTextAlignment] = MyUndefOr.undefined,
  verticalAlign:          MyUndefOr[SemanticVerticalAlignment] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPAC[GridRow.GridRowProps, GridRow] {
  override protected def cprops                     = GridRow.props(this)
  override protected val component                  = GridRow.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object GridRow {
  @js.native
  @JSImport("semantic-ui-react", "GridRow")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait GridRowProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: MyUndefOr[AsT] = js.native

    /** A row can have its columns centered. */
    var centered: MyUndefOr[Boolean] = js.native

    /** Primary content. */
    var children: MyUndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: MyUndefOr[String] = js.native

    /** A grid row can be colored. */
    var color: MyUndefOr[suiraw.SemanticCOLORS] = js.native

    /** Represents column count per line in Row. */
    var columns: MyUndefOr[String] = js.native // | 'equal'

    /** A row can have dividers between its columns. */
    var divided: MyUndefOr[Boolean] = js.native

    /** A row can appear only for a specific device, or screen sizes. */
    var only: MyUndefOr[String] = js.native

    /** A  row can specify that its columns should reverse order at different device sizes. */
    var reversed: MyUndefOr[String] = js.native

    /** An can stretch its contents to take up the entire column height. */
    var stretched: MyUndefOr[Boolean] = js.native

    /** A row can specify its text alignment. */
    var textAlign: MyUndefOr[suiraw.SemanticTEXTALIGNMENTS] = js.native

    /** A row can specify its vertical alignment to have all its columns vertically centered. */
    var verticalAlign: MyUndefOr[suiraw.SemanticVERTICALALIGNMENTS] = js.native
  }

  def props(q: GridRow): GridRowProps =
    rawprops(q.as,
             q.centered,
             q.className,
             q.clazz,
             q.color,
             q.columns,
             q.divided,
             q.only,
             q.reversed,
             q.stretched,
             q.textAlign,
             q.verticalAlign
    )

  def rawprops(
    as:            MyUndefOr[AsC] = MyUndefOr.undefined,
    centered:      MyUndefOr[Boolean] = MyUndefOr.undefined,
    className:     MyUndefOr[String] = MyUndefOr.undefined,
    clazz:         MyUndefOr[Css] = MyUndefOr.undefined,
    color:         MyUndefOr[SemanticColor] = MyUndefOr.undefined,
    columns:       MyUndefOr[SemanticWidth | GridColumns] = MyUndefOr.undefined,
    divided:       MyUndefOr[Boolean] = MyUndefOr.undefined,
    only:          MyUndefOr[GridOnly] = MyUndefOr.undefined,
    reversed:      MyUndefOr[GridReversed] = MyUndefOr.undefined,
    stretched:     MyUndefOr[Boolean] = MyUndefOr.undefined,
    textAlign:     MyUndefOr[SemanticTextAlignment] = MyUndefOr.undefined,
    verticalAlign: MyUndefOr[SemanticVerticalAlignment] = MyUndefOr.undefined
  ): GridRowProps = {
    val p = as.toJsObject[GridRowProps]
    as.toJs.foreach(v => p.as = v)
    centered.foreach(v => p.centered = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    color.toJs.foreach(v => p.color = v)
    columns
      .map((_: Any) match {
        case s: GridColumns => s.toJs
        case s              => s.asInstanceOf[SemanticWidth].toJs
      })
      .foreach(v => p.columns = v)
    divided.foreach(v => p.divided = v)
    only.toJs.foreach(v => p.only = v)
    reversed.toJs.foreach(v => p.reversed = v)
    stretched.foreach(v => p.stretched = v)
    textAlign.toJs.foreach(v => p.textAlign = v)
    verticalAlign.toJs.foreach(v => p.verticalAlign = v)
    p
  }

  private val component =
    JsFnComponent[GridRowProps, Children.Varargs](RawComponent)

  def apply(modifiers: TagMod*): GridRow =
    new GridRow(modifiers = modifiers)
}
