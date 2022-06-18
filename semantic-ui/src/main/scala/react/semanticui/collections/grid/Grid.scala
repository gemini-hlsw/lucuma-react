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

final case class Grid(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  celled:                 MyUndefOr[GridCelled] = MyUndefOr.undefined,
  centered:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  columns:                MyUndefOr[SemanticWidth] = MyUndefOr.undefined,
  container:              MyUndefOr[Boolean] = MyUndefOr.undefined,
  divided:                MyUndefOr[GridDivided] = MyUndefOr.undefined,
  doubling:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  inverted:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  padded:                 MyUndefOr[GridPadded] = MyUndefOr.undefined,
  relaxed:                MyUndefOr[GridRelaxed] = MyUndefOr.undefined,
  reversed:               MyUndefOr[GridReversed] = MyUndefOr.undefined,
  stackable:              MyUndefOr[Boolean] = MyUndefOr.undefined,
  stretched:              MyUndefOr[Boolean] = MyUndefOr.undefined,
  textAlign:              MyUndefOr[SemanticTextAlignment] = MyUndefOr.undefined,
  verticalAlign:          MyUndefOr[SemanticVerticalAlignment] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPAC[Grid.GridProps, Grid] {
  override protected def cprops                     = Grid.props(this)
  override protected val component                  = Grid.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object Grid {
  @js.native
  @JSImport("semantic-ui-react", "Grid")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait GridProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: MyUndefOr[AsT] = js.native

    /** A grid can have rows divided into cells. */
    var celled: MyUndefOr[Boolean | String] = js.native

    /** A grid can have its columns centered. */
    var centered: MyUndefOr[Boolean] = js.native

    /** Primary content. */
    var children: MyUndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: MyUndefOr[String] = js.native

    /** Represents column count per row in Grid. */
    var columns: MyUndefOr[suiraw.SemanticWIDTHS] = js.native // | 'equal'

    /** A grid can be combined with a container to use avaiable layout and alignment. */
    var container: MyUndefOr[Boolean] = js.native

    /** A grid can have dividers between its columns. */
    var divided: MyUndefOr[Boolean | String] = js.native

    /** A grid can double its column width on tablet and mobile sizes. */
    var doubling: MyUndefOr[Boolean] = js.native

    /** A grid's colors can be inverted. */
    var inverted: MyUndefOr[Boolean] = js.native

    /** A grid can preserve its vertical and horizontal gutters on first and last columns. */
    var padded: MyUndefOr[Boolean | String] = js.native

    /** A grid can increase its gutters to allow for more negative space. */
    var relaxed: MyUndefOr[Boolean | String] = js.native

    /** A grid can specify that its columns should reverse order at different device sizes. */
    var reversed: MyUndefOr[String] = js.native

    /** A grid can have its columns stack on-top of each other after reaching mobile breakpoints. */
    var stackable: MyUndefOr[Boolean] = js.native

    /** An can stretch its contents to take up the entire grid height. */
    var stretched: MyUndefOr[Boolean] = js.native

    /** A grid can specify its text alignment. */
    var textAlign: MyUndefOr[suiraw.SemanticTEXTALIGNMENTS] = js.native

    /** A grid can specify its vertical alignment to have all its columns vertically centered. */
    var verticalAlign: MyUndefOr[suiraw.SemanticVERTICALALIGNMENTS] = js.native
  }

  def props(q: Grid): GridProps =
    rawprops(
      q.as,
      q.celled,
      q.centered,
      q.className,
      q.clazz,
      q.columns,
      q.container,
      q.divided,
      q.doubling,
      q.inverted,
      q.padded,
      q.relaxed,
      q.reversed,
      q.stackable,
      q.stretched,
      q.textAlign,
      q.verticalAlign
    )

  def rawprops(
    as:            MyUndefOr[AsC] = MyUndefOr.undefined,
    celled:        MyUndefOr[GridCelled] = MyUndefOr.undefined,
    centered:      MyUndefOr[Boolean] = MyUndefOr.undefined,
    className:     MyUndefOr[String] = MyUndefOr.undefined,
    clazz:         MyUndefOr[Css] = MyUndefOr.undefined,
    columns:       MyUndefOr[SemanticWidth] = MyUndefOr.undefined,
    container:     MyUndefOr[Boolean] = MyUndefOr.undefined,
    divided:       MyUndefOr[GridDivided] = MyUndefOr.undefined,
    doubling:      MyUndefOr[Boolean] = MyUndefOr.undefined,
    inverted:      MyUndefOr[Boolean] = MyUndefOr.undefined,
    padded:        MyUndefOr[GridPadded] = MyUndefOr.undefined,
    relaxed:       MyUndefOr[GridRelaxed] = MyUndefOr.undefined,
    reversed:      MyUndefOr[GridReversed] = MyUndefOr.undefined,
    stackable:     MyUndefOr[Boolean] = MyUndefOr.undefined,
    stretched:     MyUndefOr[Boolean] = MyUndefOr.undefined,
    textAlign:     MyUndefOr[SemanticTextAlignment] = MyUndefOr.undefined,
    verticalAlign: MyUndefOr[SemanticVerticalAlignment] = MyUndefOr.undefined
  ): GridProps = {
    val p = as.toJsObject[GridProps]
    as.toJs.foreach(v => p.as = v)
    celled.toJs.foreach(v => p.celled = v)
    centered.foreach(v => p.centered = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    columns.toJs.foreach(v => p.columns = v)
    container.foreach(v => p.container = v)
    divided.toJs.foreach(v => p.divided = v)
    doubling.foreach(v => p.doubling = v)
    inverted.foreach(v => p.inverted = v)
    padded.toJs.foreach(v => p.padded = v)
    relaxed.toJs.foreach(v => p.relaxed = v)
    reversed.toJs.foreach(v => p.reversed = v)
    stackable.foreach(v => p.stackable = v)
    stretched.foreach(v => p.stretched = v)
    textAlign.toJs.foreach(v => p.textAlign = v)
    verticalAlign.toJs.foreach(v => p.verticalAlign = v)
    p
  }

  private val component =
    JsFnComponent[GridProps, Children.Varargs](RawComponent)

  val Default: Grid = Grid()

  val defaultProps: GridProps = props(Default)

  def apply(modifiers: TagMod*): Grid =
    new Grid(modifiers = modifiers)
}
