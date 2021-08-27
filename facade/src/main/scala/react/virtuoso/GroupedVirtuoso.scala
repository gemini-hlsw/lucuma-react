package react.virtuoso

import japgolly.scalajs.react.component.Js
import japgolly.scalajs.react.facade.JsNumber
import japgolly.scalajs.react.vdom._
import japgolly.scalajs.react.{ CtorType, _ }
import react.common._
import react.virtuoso.raw._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|
import scala.scalajs.js.JSConverters._

final case class GroupedVirtuoso[D](
  totalCount:              js.UndefOr[Int] = js.undefined,
  data:                    js.UndefOr[js.Array[D]] = js.undefined,
  overscan:                js.UndefOr[Int | OverScan] = js.undefined,
  topItemCount:            js.UndefOr[Int] = js.undefined,
  initialTopMostItemIndex: js.UndefOr[Int] = js.undefined,
  initialScrollTop:        js.UndefOr[Int] = js.undefined,
  initialItemCount:        js.UndefOr[Int] = js.undefined,
  itemContent:             js.UndefOr[(Int, Int, D) => VdomNode] = js.undefined,
  computeItemKey:          js.UndefOr[Int => Key] = js.undefined,
  defaultItemHeight:       js.UndefOr[Double] = js.undefined,
  fixedItemHeight:         js.UndefOr[Double] = js.undefined,
  headerFooterTag:         js.UndefOr[String] = js.undefined,
  firstItemIndex:          js.UndefOr[Int] = js.undefined,
  isScrolling:             js.UndefOr[Boolean => Callback] = js.undefined,
  endReached:              js.UndefOr[Int => Callback] = js.undefined,
  startReached:            js.UndefOr[Int => Callback] = js.undefined,
  rangeChanged:            js.UndefOr[ListRange => Callback] = js.undefined,
  atBottomStateChange:     js.UndefOr[Boolean => Callback] = js.undefined,
  atTopStateChange:        js.UndefOr[Boolean => Callback] = js.undefined,
  totalListHeightChanged:  js.UndefOr[Double => Callback] = js.undefined,
  alignToBottom:           js.UndefOr[Boolean] = js.undefined,
  useWindowScroll:         js.UndefOr[Boolean] = js.undefined,
  groupContent:            js.UndefOr[Int => VdomNode] = js.undefined,
  groupCounts:             List[Int] = List(Int.MaxValue), // It would be cool if this was a NonEmptyList
  override val modifiers:  Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[GroupedVirtuoso.GroupedVirtuosoProps[D], GroupedVirtuoso[D]] {
  override protected def cprops    = GroupedVirtuoso.props(this)
  override protected val component = GroupedVirtuoso.component[D]
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
  def apply(mods:                      TagMod*)     = addModifiers(mods)
}

object GroupedVirtuoso {
  type BoolToUnit = js.Function1[Boolean, Unit]

  @js.native
  @JSImport("react-virtuoso", "GroupedVirtuoso")
  object RawComponent extends js.Object

  @js.native
  trait GroupedVirtuosoProps[D] extends js.Object {

    /**
     * The total amount of items to be rendered.
     */
    var totalCount: js.UndefOr[JsNumber] = js.native

    /**
     * The data items to be rendered. If data is set, the total count will be inferred from the
     * length of the array.
     */
    var data: js.UndefOr[js.Array[D]] = js.native

    /**
     * Increases the visual window which is used to calculate the rendered items with the specified
     * **amount in pixels**. Effectively, this makes the component "chunk" the rendering of new
     * items by renderng more items than the necessary, but reducing the re-renders on scroll.
     * Setting { main: number, reverse: number } lets you extend the list in both the main and the
     * reverse scrollable directions.
     */
    var overscan: js.UndefOr[JsNumber | OverScan] = js.native

    /**
     * Set the amount of items to remain fixed at the top of the list.
     *
     * For a header that scrolls away when scrolling, check the `components.Header` property.
     */
    var topItemCount: js.UndefOr[JsNumber] = js.native

    /**
     * Set to a value between 0 and totalCount - 1 to make the list start scrolled to that item.
     */
    var initialTopMostItemIndex: js.UndefOr[JsNumber] = js.native

    /**
     * Set this value to offset the initial location of the list. Warning: using this property will
     * still run a render cycle at the scrollTop: 0 list window. If possible, avoid using it and
     * stick to `initialTopMostItemIndex` instead.
     */
    var initialScrollTop: js.UndefOr[JsNumber] = js.native

    /**
     * Use for server-side rendering - if set, the list will render the specified amount of items
     * regardless of the container / item size.
     */
    var initialItemCount: js.UndefOr[JsNumber] = js.native

    /**
     * Use the `components` property for advanced customization of the elements rendered by the
     * list.
     */
    // Facade not currently implementd for this.
    //components?: Components

    /**
     * Set the callback to specify the contents of the item.
     */
    var itemContent: js.UndefOr[js.Function3[Double, Double, D, facade.React.Node]] =
      js.native

    /**
     * If specified, the component will use the function to generate the `key` property for each
     * list item.
     */
    var computeItemKey: js.UndefOr[js.Function1[Double, Key]] = js.native

    /**
     * By default, the component assumes the default item height from the first rendered item
     * (rendering it as a "probe").
     *
     * If the first item turns out to be an outlier (very short or tall), the rest of the rendering
     * will be slower, as multiple passes of rendering should happen for the list to fill the
     * viewport.
     *
     * Setting `defaultItemHeight` causes the component to skip the "probe" rendering and use the
     * property value as default height instead.
     */
    var defaultItemHeight: js.UndefOr[JsNumber] = js.native

    /**
     * Can be used to improve performance if the rendered items are of known size. Setting it causes
     * the component to skip item measurements.
     */
    var fixedItemHeight: js.UndefOr[JsNumber] = js.native

    /**
     * Use to display placeholders if the user scrolls fast through the list.
     *
     * Set `components.ScrollSeekPlaceholder` to change the placeholder content.
     */
    // Facade not currently implementd for this.
    // scrollSeekConfiguration?: ScrollSeekConfiguration | false

    /**
     * If set to `true`, the list automatically scrolls to bottom if the total count is changed. Set
     * to `"smooth"` for an animated scrolling.
     *
     * By default, `followOutput` scrolls down only if the list is already at the bottom. To
     * implement an arbitrary logic behind that, pass a function:
     *
     * ```tsx
     * <Virtuoso
     * followOutput={(isAtBottom: boolean) => {
     *   if (expression) {
     *     return 'smooth' // can be 'auto' or false to avoid scrolling
     *   } else {
     *     return false
     *   }
     * }} />
     * ```
     */
    // Facade not currently implementd for this.
    // followOutput?: FollowOutput

    /**
     * Set to customize the wrapper tag for the header and footer components (default is `div`).
     */
    var headerFooterTag: js.UndefOr[String] = js.native

    /**
     * Use when implementing inverse infinite scrolling - decrease the value this property in
     * combination with `data` or `totalCount` to prepend items to the top of the list.
     *
     * Warning: the firstItemIndex should **be a positive number**, based on the total amount of
     * items to be displayed.
     */
    var firstItemIndex: js.UndefOr[JsNumber] = js.native

    /**
     * Called when the list starts/stops scrolling.
     */
    var isScrolling: js.UndefOr[js.Function1[Boolean, Unit]] = js.native

    /**
     * Gets called when the user scrolls to the end of the list. Receives the last item index as an
     * argument. Can be used to implement endless scrolling.
     */
    var endReached: js.UndefOr[js.Function1[Double, Unit]] = js.native

    /**
     * Called when the user scrolls to the start of the list.
     */
    var startReached: js.UndefOr[js.Function1[Double, Unit]] = js.native

    /**
     * Called with the new set of items each time the list items are rendered due to scrolling.
     */
    var rangeChanged: js.UndefOr[js.Function1[ListRange, Unit]] = js.native

    /**
     * Called with true / false when the list has reached the bottom / gets scrolled up. Can be used
     * to load newer items, like `tail -f`.
     */
    var atBottomStateChange: js.UndefOr[js.Function1[Boolean, Unit]] = js.native

    /**
     * Called with `true` / `false` when the list has reached the top / gets scrolled down.
     */
    var atTopStateChange: js.UndefOr[js.Function1[Boolean, Unit]] = js.native

    /**
     * Called when the total list height is changed due to new items or viewport resize.
     */
    var totalListHeightChanged: js.UndefOr[js.Function1[Double, Unit]] = js.native

    /**
     * Called with the new set of items each time the list items are rendered due to scrolling.
     */
    // Facade not currently implementd for this.
    // itemsRendered?: (items: ListItem<D>[]) => void

    /**
     * Setting `alignToBottom` to `true` aligns the items to the bottom of the list if the list is
     * shorter than the viewport. Use `followOutput` property to keep the list aligned when new
     * items are appended.
     */
    var alignToBottom: js.UndefOr[Boolean] = js.native

    /**
     * Experimental - uses the document scroller rather than wrapping the list in its own.
     */
    var useWindowScroll: js.UndefOr[Boolean] = js.native

    /*
     * Provides access to the root DOM element
     */
    // Facade not currently implementd for this.
    // scrollerRef?: (ref: HTMLElement | null) => any

    /*
     * Specifies how each each group header gets rendered. The callback receives the zero-based index of the group.
     */
    var groupContent: js.UndefOr[js.Function1[Double, facade.React.Node]] = js.undefined

    /*
     * Specifies the amount of items in each group (and, actually, how many groups are there).
     * For example, passing [20, 30] will display 2 groups with 20 and 30 items each.
     */
    var groupCounts: js.UndefOr[js.Array[Double]] = js.undefined
  }

  def props[D](q: GroupedVirtuoso[D]): GroupedVirtuosoProps[D] = {
    val p = (new js.Object).asInstanceOf[GroupedVirtuosoProps[D]]
    q.totalCount.foreach(v => p.totalCount = v)
    q.data.foreach(v => p.data = v)
    q.overscan.foreach(v => p.overscan = v)
    q.topItemCount.foreach(v => p.topItemCount = v)
    q.initialTopMostItemIndex.foreach(v => p.initialTopMostItemIndex = v)
    q.initialScrollTop.foreach(v => p.initialScrollTop = v)
    q.initialItemCount.foreach(v => p.initialItemCount = v)
    q.itemContent.foreach(v =>
      p.itemContent = (
        idx:      Double,
        groupIdx: Double,
        d:        D
      ) => v(idx.toInt, groupIdx.toInt, d).rawNode
    )
    q.computeItemKey.foreach(v => p.computeItemKey = (d: Double) => v(d.toInt))
    q.defaultItemHeight.foreach(v => p.defaultItemHeight = v)
    q.fixedItemHeight.foreach(v => p.fixedItemHeight = v)
    q.headerFooterTag.foreach(v => p.headerFooterTag = v)
    q.firstItemIndex.foreach(v => p.firstItemIndex = v)
    q.isScrolling.toJs.foreach(v => p.isScrolling = v)
    q.endReached.toJs.foreach(v => p.endReached = (d: Double) => v(d.toInt))
    q.startReached.toJs.foreach(v => p.startReached = (d: Double) => v(d.toInt))
    q.rangeChanged.toJs.foreach(v => p.rangeChanged = (lr: ListRange) => v(lr))
    q.atBottomStateChange.toJs.foreach(v => p.atBottomStateChange = v)
    q.atTopStateChange.toJs.foreach(v => p.atTopStateChange = v)
    q.totalListHeightChanged.toJs.foreach(v => p.totalListHeightChanged = v)
    q.alignToBottom.foreach(v => p.alignToBottom = v)
    q.useWindowScroll.foreach(v => p.useWindowScroll = v)
    q.groupContent.foreach(v => p.groupContent = (idx: Double) => v(idx.toInt).rawNode)
    p.groupCounts = q.groupCounts.map(_.toDouble).toJSArray
    p
  }

  private def component[D] =
    JsComponent[GroupedVirtuosoProps[D], Children.Varargs, Null](RawComponent)
}
