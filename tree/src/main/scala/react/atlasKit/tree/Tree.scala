// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.atlasKit.tree

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.VdomNode
import japgolly.scalajs.react.{facade => Raw}
import react.beautifuldnd.Draggable

import scala.scalajs.js.annotation.JSImport

import scalajs.js
import js.JSConverters._

object Tree {
  @js.native
  @JSImport("@atlaskit/tree", JSImport.Default)
  object RawComponent extends js.Object

  @js.native
  @JSImport("@atlaskit/tree", "moveItemOnTree")
  object moveItemOnTree extends js.Object {
    def apply[A](tree: Data[A], source: Position, destination: Position): Data[A] = js.native
  }

  type ItemId      = String
  type Path        = js.Array[ItemId]
  type OnExpand    = js.Function2[ItemId, Path, Unit]
  type OnCollapse  = js.Function2[ItemId, Path, Unit]
  type OnDragStart = js.Function1[ItemId, Unit]
  type OnDragEnd   = js.Function2[Position, js.UndefOr[Position], Unit]

  @js.native
  trait Item[A] extends js.Any {
    var id: ItemId
    var children: js.Array[ItemId]
    var hasChildren: js.UndefOr[Boolean]
    var isExpanded: js.UndefOr[Boolean]
    var isChildrenLoading: js.UndefOr[Boolean]
    var data: js.UndefOr[A]
  }
  object Item {
    def apply[A](
      id:                ItemId,
      children:          Seq[ItemId],
      hasChildren:       js.UndefOr[Boolean] = js.undefined,
      isExpanded:        js.UndefOr[Boolean] = js.undefined,
      isChildrenLoading: js.UndefOr[Boolean] = js.undefined,
      data:              js.UndefOr[A] = js.undefined
    ): Item[A] = {
      val p = (new js.Object).asInstanceOf[Item[A]]
      p.id = id
      p.children = children.toJSArray
      hasChildren.foreach(v => p.hasChildren = v)
      isExpanded.foreach(v => p.isExpanded = v)
      isChildrenLoading.foreach(v => p.isChildrenLoading = v)
      data.foreach(v => p.data = v)
      p
    }
  }

  @js.native
  trait Data[A] extends js.Any {
    var rootId: ItemId
    var items: js.Dictionary[Item[A]]
  }
  object Data {
    def apply[A](
      rootId: ItemId,
      items:  Map[String, Item[A]]
    ): Data[A] = {
      val p = (new js.Object).asInstanceOf[Data[A]]
      p.rootId = rootId
      p.items = items.toJSDictionary
      p
    }
  }

  case class RenderItemParams[A](
    item:       Item[A],
    depth:      Int,
    onExpand:   ItemId => Callback,
    onCollapse: ItemId => Callback,
    provided:   Draggable.Provided,
    snapshot:   Draggable.StateSnapshot
  )

  object RenderItemParams {
    def apply[A](params: RenderItemParamsJS[A]): RenderItemParams[A] =
      RenderItemParams(
        params.item,
        params.depth,
        id => Callback(params.onExpand(id)),
        id => Callback(params.onCollapse(id)),
        Draggable.Provided(params.provided),
        params.snapshot
      )
  }

  @js.native
  trait RenderItemParamsJS[A] extends js.Any {

    /** Item to be rendered */
    val item: Item[A]

    /** The depth of the item on the tree. 0 means root level. */
    val depth: Int

    /** Function to call when a parent item needs to be expanded */
    val onExpand: js.Function1[ItemId, Unit]

    /** Function to call when a parent item needs to be collapsed */
    val onCollapse: js.Function1[ItemId, Unit]

    /** Couple of Props to be spread into the rendered React.Components and DOM elements */
    /**
     * More info: https://github.com/atlassian/react-beautiful-dnd#children-function-render-props
     */
    val provided: Draggable.ProvidedJS

    /** Couple of state variables */
    /**
     * More info: https://github.com/atlassian/react-beautiful-dnd#2-snapshot-draggablestatesnapshot
     */
    val snapshot: Draggable.StateSnapshot
  }

  @js.native
  trait Position extends js.Any {
    val parentId: ItemId
    val index: Int
  }

  @js.native
  trait Props[A] extends js.Object {
    var tree: Data[A]
    var renderItem: js.Function1[RenderItemParamsJS[A], Raw.React.Node]
    var onExpand: js.UndefOr[OnExpand]
    var onCollapse: js.UndefOr[OnCollapse]
    var onDragStart: js.UndefOr[OnDragStart]
    var onDragEnd: js.UndefOr[OnDragEnd]
    var offsetPerLevel: js.UndefOr[Int]
    var isDragEnabled: js.UndefOr[Boolean]
    var isNestingEnabled: js.UndefOr[Boolean]
  }
  object Props {
    def apply[A](
      tree:             Data[A],
      renderItem:       RenderItemParams[A] => VdomNode,
      onExpand:         js.UndefOr[(ItemId, Path) => Callback] = js.undefined,
      onCollapse:       js.UndefOr[(ItemId, Path) => Callback] = js.undefined,
      onDragStart:      js.UndefOr[ItemId => Callback] = js.undefined,
      onDragEnd:        js.UndefOr[(Position, Option[Position]) => Callback] = js.undefined,
      offsetPerLevel:   js.UndefOr[Int] = js.undefined,
      isDragEnabled:    js.UndefOr[Boolean] = js.undefined,
      isNestingEnabled: js.UndefOr[Boolean] = js.undefined
    ): Props[A] = {
      val p = (new js.Object).asInstanceOf[Props[A]]
      p.tree = tree
      p.renderItem = (params: RenderItemParamsJS[A]) => renderItem(RenderItemParams(params)).rawNode
      onExpand.foreach(v =>
        p.onExpand = ((itemId: ItemId, path: Path) => v(itemId, path).runNow()): OnExpand
      )
      onCollapse.foreach(v =>
        p.onCollapse = ((itemId: ItemId, path: Path) => v(itemId, path).runNow()): OnCollapse
      )
      onDragStart.foreach(v =>
        p.onDragStart = ((itemId: ItemId) => v(itemId).runNow()): OnDragStart
      )
      onDragEnd.foreach(v =>
        p.onDragEnd = (
          (
            source:      Position,
            destination: js.UndefOr[Position]
          ) => v(source, destination.toOption).runNow()
        ): OnDragEnd
      )
      offsetPerLevel.foreach(v => p.offsetPerLevel = v)
      isDragEnabled.foreach(v => p.isDragEnabled = v)
      isNestingEnabled.foreach(v => p.isNestingEnabled = v)
      p
    }
  }

  val component = JsComponent[Props[Any], Children.None, Null](RawComponent)

  def apply[A](
    tree:             Data[A],
    renderItem:       RenderItemParams[A] => VdomNode,
    onExpand:         js.UndefOr[(ItemId, Path) => Callback] = js.undefined,
    onCollapse:       js.UndefOr[(ItemId, Path) => Callback] = js.undefined,
    onDragStart:      js.UndefOr[ItemId => Callback] = js.undefined,
    onDragEnd:        js.UndefOr[(Position, Option[Position]) => Callback] = js.undefined,
    offsetPerLevel:   js.UndefOr[Int] = js.undefined,
    isDragEnabled:    js.UndefOr[Boolean] = js.undefined,
    isNestingEnabled: js.UndefOr[Boolean] = js.undefined
  ) =
    component(
      Props(
        tree,
        renderItem,
        onExpand,
        onCollapse,
        onDragStart,
        onDragEnd,
        offsetPerLevel,
        isDragEnabled,
        isNestingEnabled
      ).asInstanceOf[Props[Any]]
    )
}
