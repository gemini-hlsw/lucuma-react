// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.collections.grid

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import react.common._
import react.semanticui._
import react.semanticui.{raw => suiraw}
import japgolly.scalajs.react.vdom.TagMod

final case class GridColumn(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  color:                  MyUndefOr[SemanticColor] = MyUndefOr.undefined,
  computer:               MyUndefOr[SemanticWidth] = MyUndefOr.undefined,
  floated:                MyUndefOr[SemanticFloat] = MyUndefOr.undefined,
  largeScreen:            MyUndefOr[SemanticWidth] = MyUndefOr.undefined,
  mobile:                 MyUndefOr[SemanticWidth] = MyUndefOr.undefined,
  only:                   MyUndefOr[GridOnly] = MyUndefOr.undefined,
  stretched:              MyUndefOr[Boolean] = MyUndefOr.undefined,
  tablet:                 MyUndefOr[SemanticWidth] = MyUndefOr.undefined,
  textAlign:              MyUndefOr[SemanticTextAlignment] = MyUndefOr.undefined,
  verticalAlign:          MyUndefOr[SemanticVerticalAlignment] = MyUndefOr.undefined,
  widescreen:             MyUndefOr[SemanticWidth] = MyUndefOr.undefined,
  width:                  MyUndefOr[SemanticWidth] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPAC[GridColumn.GridColumnProps, GridColumn] {
  override protected def cprops                     = GridColumn.props(this)
  override protected val component                  = GridColumn.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object GridColumn {
  @js.native
  @JSImport("semantic-ui-react", "GridColumn")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait GridColumnProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: MyUndefOr[AsT] = js.native

    /** Primary content. */
    var children: MyUndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: MyUndefOr[String] = js.native

    /** A grid column can be colored. */
    var color: MyUndefOr[suiraw.SemanticCOLORS] = js.native

    /** A column can specify a width for a computer. */
    var computer: MyUndefOr[suiraw.SemanticWIDTHS] = js.native // | 'equal'

    /** A column can sit flush against the left or right edge of a row. */
    var floated: MyUndefOr[suiraw.SemanticFLOATS] = js.native

    /** A column can specify a width for a large screen device. */
    var largeScreen: MyUndefOr[suiraw.SemanticWIDTHS] = js.native // | 'equal'

    /** A column can specify a width for a mobile device. */
    var mobile: MyUndefOr[suiraw.SemanticWIDTHS] = js.native // | 'equal'

    /** A column can appear only for a specific device, or screen sizes. */
    var only: MyUndefOr[String] = js.native

    /** An can stretch its contents to take up the entire grid or row height. */
    var stretched: MyUndefOr[Boolean] = js.native

    /** A column can specify a width for a tablet device. */
    var tablet: MyUndefOr[suiraw.SemanticWIDTHS] = js.native // | 'equal'

    /** A row can specify its text alignment. */
    var textAlign: MyUndefOr[suiraw.SemanticTEXTALIGNMENTS] = js.native

    /** A column can specify its vertical alignment to have all its columns vertically centered. */
    var verticalAlign: MyUndefOr[suiraw.SemanticVERTICALALIGNMENTS] = js.native

    /** A column can specify a width for a wide screen device. */
    var widescreen: MyUndefOr[suiraw.SemanticWIDTHS] = js.native // | 'equal'

    /** Represents width of column. */
    var width: MyUndefOr[suiraw.SemanticWIDTHS] = js.native // | 'equal'
  }

  def props(q: GridColumn): GridColumnProps =
    rawprops(
      q.as,
      q.className,
      q.clazz,
      q.color,
      q.computer,
      q.floated,
      q.largeScreen,
      q.mobile,
      q.only,
      q.stretched,
      q.tablet,
      q.textAlign,
      q.verticalAlign,
      q.widescreen,
      q.width
    )

  def rawprops(
    as:            MyUndefOr[AsC] = MyUndefOr.undefined,
    className:     MyUndefOr[String] = MyUndefOr.undefined,
    clazz:         MyUndefOr[Css] = MyUndefOr.undefined,
    color:         MyUndefOr[SemanticColor] = MyUndefOr.undefined,
    computer:      MyUndefOr[SemanticWidth] = MyUndefOr.undefined,
    floated:       MyUndefOr[SemanticFloat] = MyUndefOr.undefined,
    largeScreen:   MyUndefOr[SemanticWidth] = MyUndefOr.undefined,
    mobile:        MyUndefOr[SemanticWidth] = MyUndefOr.undefined,
    only:          MyUndefOr[GridOnly] = MyUndefOr.undefined,
    stretched:     MyUndefOr[Boolean] = MyUndefOr.undefined,
    tablet:        MyUndefOr[SemanticWidth] = MyUndefOr.undefined,
    textAlign:     MyUndefOr[SemanticTextAlignment] = MyUndefOr.undefined,
    verticalAlign: MyUndefOr[SemanticVerticalAlignment] = MyUndefOr.undefined,
    widescreen:    MyUndefOr[SemanticWidth] = MyUndefOr.undefined,
    width:         MyUndefOr[SemanticWidth] = MyUndefOr.undefined
  ): GridColumnProps = {
    val p = as.toJsObject[GridColumnProps]
    as.toJs.foreach(v => p.as = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    color.toJs.foreach(v => p.color = v)
    computer.toJs.foreach(v => p.computer = v)
    floated.toJs.foreach(v => p.floated = v)
    largeScreen.toJs.foreach(v => p.largeScreen = v)
    mobile.toJs.foreach(v => p.mobile = v)
    only.toJs.foreach(v => p.only = v)
    stretched.foreach(v => p.stretched = v)
    tablet.toJs.foreach(v => p.tablet = v)
    textAlign.toJs.foreach(v => p.textAlign = v)
    verticalAlign.toJs.foreach(v => p.verticalAlign = v)
    widescreen.toJs.foreach(v => p.widescreen = v)
    width.toJs.foreach(v => p.width = v)
    p
  }

  private val component =
    JsFnComponent[GridColumnProps, Children.Varargs](RawComponent)

  def apply(modifiers: TagMod*): GridColumn =
    new GridColumn(modifiers = modifiers)
}
