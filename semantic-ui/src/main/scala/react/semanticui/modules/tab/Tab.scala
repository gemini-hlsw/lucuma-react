package react.semanticui.modules.tab

import scala.scalajs.js
import js.annotation._
import js.|
import js.JSConverters._
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.VdomNode
import react.common._
import react.semanticui.collections.menu.Menu
import react.semanticui.collections.menu.MenuItem
import react.semanticui.collections.grid.Grid
import react.semanticui._
import japgolly.scalajs.react.vdom.TagMod

final case class Pane private (
  pane:     js.UndefOr[TabPane],
  menuItem: js.UndefOr[String | MenuItem],
  render:   js.UndefOr[Tab.PaneRender]
)

object Pane {
  def apply(menuItem: js.UndefOr[String | MenuItem], render: => VdomNode): Pane =
    new Pane(pane = js.undefined, menuItem = menuItem, render = () => render.rawNode)
  def apply(menuItem: js.UndefOr[String | MenuItem], pane: TabPane): Pane       =
    new Pane(pane = pane, menuItem = menuItem, render = js.undefined)
}

final case class Tab(
  as:                     js.UndefOr[AsC] = js.undefined,
  defaultActiveIndex:     js.UndefOr[Double | String] = js.undefined,
  activeIndex:            js.UndefOr[Double | String] = js.undefined,
  menu:                   js.UndefOr[Menu] = js.undefined,
  menuPosition:           js.UndefOr[TabMenuPosition] = js.undefined,
  grid:                   js.UndefOr[Grid] = js.undefined,
  onTabChangeE:           js.UndefOr[Tab.OnTabChange] = js.undefined,
  onTabChange:            js.UndefOr[Tab.TabProps => Callback] = js.undefined,
  panes:                  List[Pane] = Nil,
  renderActiveOnly:       js.UndefOr[Boolean] = js.undefined,
  vertical:               js.UndefOr[Boolean] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPA[Tab.TabProps, Tab] {
  override protected def cprops                     = Tab.props(this)
  override protected val component                  = Tab.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object Tab {
  type RawOnTabChange = js.Function2[ReactMouseEvent, TabProps, Unit]
  type OnTabChange    = (ReactMouseEvent, TabProps) => Callback
  type RawPaneRender  = js.Function0[React.Node]
  type PaneRender     = () => React.Node

  trait RawPane extends js.Object {
    var pane: js.UndefOr[TabPane.TabPaneProps]
    var menuItem: js.UndefOr[String | MenuItem.MenuItemProps]
    var render: js.UndefOr[RawPaneRender]
  }

  object RawPane {
    def fromPane(q: Pane): RawPane = {
      val p = (new js.Object).asInstanceOf[RawPane]
      q.pane.map(_.props).foreach(v => p.pane = v)
      q.menuItem
        .map[String | MenuItem.MenuItemProps](d =>
          (d: Any) match {
            case s: String   => s
            case m: MenuItem => m.props
            case _           => sys.error("Shouldn't happen")
          }
        )
        .foreach(v => p.menuItem = v)
      q.render
        .map { f =>
          val r: js.Function0[React.Node] = f
          r
        }
        .foreach(v => p.render = v)
      p
    }
  }

  @js.native
  @JSImport("semantic-ui-react", "Tab")
  object RawComponent extends js.Object

  @js.native
  trait TabProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: js.UndefOr[AsT] = js.native

    /** The initial activeIndex. */
    var defaultActiveIndex: js.UndefOr[Double | String]

    /** Index of the currently active tab. */
    var activeIndex: js.UndefOr[Double | String]

    /** Shorthand props for the Menu. */
    var menu: js.UndefOr[Menu.MenuProps]

    /** Align vertical menu */
    var menuPosition: js.UndefOr[String]

    /** Shorthand props for the Grid. */
    var grid: js.UndefOr[Grid.GridProps]

    /**
     * Called on tab change.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {data}
     *   data - The proposed new Tab.Pane.
     * @param {activeIndex}
     *   data.activeIndex - The new proposed activeIndex.
     * @param {panes}
     *   data.panes - Props of the new proposed active pane.
     */
    var onTabChange: js.UndefOr[RawOnTabChange]

    /**
     * Array of objects describing each Menu.Item and Tab.Pane: { menuItem: 'Home', render: () =>
     * <Tab.Pane>Welcome!</Tab.Pane>, } or { menuItem: 'Home', pane: 'Welcome', }
     */
    var panes: js.UndefOr[js.Array[RawPane]] = js.native

    /** A Tab can render only active pane. */
    var renderActiveOnly: js.UndefOr[Boolean]

    var vertical: js.UndefOr[Boolean]
  }

  def props(
    q: Tab
  ): TabProps = {
    val p = q.as.toJsObject[TabProps]
    q.as.toJs.foreach(v => p.as = v)
    q.defaultActiveIndex.foreach(v => p.defaultActiveIndex = v)
    q.activeIndex.foreach(v => p.activeIndex = v)
    q.menu.map(_.props).foreach(v => p.menu = v)
    q.menuPosition.toJs.foreach(v => p.menuPosition = v)
    q.grid.map(_.props).foreach(v => p.grid = v)
    q.onTabChangeE.toJs
      .orElse[RawOnTabChange](q.onTabChange.map(t => (_: ReactEvent, b: TabProps) => t(b).runNow()))
      .foreach(v => p.onTabChange = v)
    p.panes = q.panes.map(RawPane.fromPane(_)).toJSArray
    q.renderActiveOnly.foreach(v => p.renderActiveOnly = v)
    q.vertical.foreach(v => p.vertical = v)
    p
  }

  private val component =
    JsComponent[TabProps, Children.None, Null](RawComponent)
}
