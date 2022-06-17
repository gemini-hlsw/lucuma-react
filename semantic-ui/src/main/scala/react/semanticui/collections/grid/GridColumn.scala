package react.semanticui.collections.grid

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import react.common._
import react.semanticui._
import react.semanticui.{ raw => suiraw }
import japgolly.scalajs.react.vdom.TagMod

final case class GridColumn(
  as:                     js.UndefOr[AsC] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  color:                  js.UndefOr[SemanticColor] = js.undefined,
  computer:               js.UndefOr[SemanticWidth] = js.undefined,
  floated:                js.UndefOr[SemanticFloat] = js.undefined,
  largeScreen:            js.UndefOr[SemanticWidth] = js.undefined,
  mobile:                 js.UndefOr[SemanticWidth] = js.undefined,
  only:                   js.UndefOr[GridOnly] = js.undefined,
  stretched:              js.UndefOr[Boolean] = js.undefined,
  tablet:                 js.UndefOr[SemanticWidth] = js.undefined,
  textAlign:              js.UndefOr[SemanticTextAlignment] = js.undefined,
  verticalAlign:          js.UndefOr[SemanticVerticalAlignment] = js.undefined,
  widescreen:             js.UndefOr[SemanticWidth] = js.undefined,
  width:                  js.UndefOr[SemanticWidth] = js.undefined,
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
    var as: js.UndefOr[AsT] = js.native

    /** Primary content. */
    var children: js.UndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: js.UndefOr[String] = js.native

    /** A grid column can be colored. */
    var color: js.UndefOr[suiraw.SemanticCOLORS] = js.native

    /** A column can specify a width for a computer. */
    var computer: js.UndefOr[suiraw.SemanticWIDTHS] = js.native // | 'equal'

    /** A column can sit flush against the left or right edge of a row. */
    var floated: js.UndefOr[suiraw.SemanticFLOATS] = js.native

    /** A column can specify a width for a large screen device. */
    var largeScreen: js.UndefOr[suiraw.SemanticWIDTHS] = js.native // | 'equal'

    /** A column can specify a width for a mobile device. */
    var mobile: js.UndefOr[suiraw.SemanticWIDTHS] = js.native // | 'equal'

    /** A column can appear only for a specific device, or screen sizes. */
    var only: js.UndefOr[String] = js.native

    /** An can stretch its contents to take up the entire grid or row height. */
    var stretched: js.UndefOr[Boolean] = js.native

    /** A column can specify a width for a tablet device. */
    var tablet: js.UndefOr[suiraw.SemanticWIDTHS] = js.native // | 'equal'

    /** A row can specify its text alignment. */
    var textAlign: js.UndefOr[suiraw.SemanticTEXTALIGNMENTS] = js.native

    /** A column can specify its vertical alignment to have all its columns vertically centered. */
    var verticalAlign: js.UndefOr[suiraw.SemanticVERTICALALIGNMENTS] = js.native

    /** A column can specify a width for a wide screen device. */
    var widescreen: js.UndefOr[suiraw.SemanticWIDTHS] = js.native // | 'equal'

    /** Represents width of column. */
    var width: js.UndefOr[suiraw.SemanticWIDTHS] = js.native // | 'equal'
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
    as:            js.UndefOr[AsC] = js.undefined,
    className:     js.UndefOr[String] = js.undefined,
    clazz:         js.UndefOr[Css] = js.undefined,
    color:         js.UndefOr[SemanticColor] = js.undefined,
    computer:      js.UndefOr[SemanticWidth] = js.undefined,
    floated:       js.UndefOr[SemanticFloat] = js.undefined,
    largeScreen:   js.UndefOr[SemanticWidth] = js.undefined,
    mobile:        js.UndefOr[SemanticWidth] = js.undefined,
    only:          js.UndefOr[GridOnly] = js.undefined,
    stretched:     js.UndefOr[Boolean] = js.undefined,
    tablet:        js.UndefOr[SemanticWidth] = js.undefined,
    textAlign:     js.UndefOr[SemanticTextAlignment] = js.undefined,
    verticalAlign: js.UndefOr[SemanticVerticalAlignment] = js.undefined,
    widescreen:    js.UndefOr[SemanticWidth] = js.undefined,
    width:         js.UndefOr[SemanticWidth] = js.undefined
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
