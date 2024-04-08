// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.primereact

import cats.Eq
import cats.derived.*
import cats.syntax.all.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.facade.SyntheticEvent
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.common.ReactFnProps
import lucuma.typed.primereact.components.Tree as CTree
import lucuma.typed.primereact.primereactStrings.checkbox
import lucuma.typed.primereact.primereactStrings.multiple
import lucuma.typed.primereact.primereactStrings.single
import lucuma.typed.primereact.treeTreeMod.TreeDragDropEvent
import lucuma.typed.primereact.treeTreeMod.TreeNodeTemplateOptions
import lucuma.typed.primereact.treenodeTreenodeMod.TreeNode as CTreeNode
import org.scalablytyped.runtime.StringDictionary
import org.scalajs.dom.Element

import scalajs.js
import scalajs.js.JSConverters.*

case class Tree[A](
  value:         Seq[Tree.Node[A]],
  nodeTemplate:  (A, TreeNodeTemplateOptions) => VdomNode,
  expandedKeys:  js.UndefOr[Set[Tree.Id]] = js.undefined,
  onToggle:      js.UndefOr[Set[Tree.Id] => Callback] = js.undefined,
  selectionMode: js.UndefOr[Tree.SelectionMode] = js.undefined,
  loading:       js.UndefOr[Boolean] = js.undefined,
  onSelect:      js.UndefOr[(A, SyntheticEvent[Element]) => Callback] = js.undefined,
  dragDropScope: js.UndefOr[String] = js.undefined,
  onDragDrop:    js.UndefOr[Tree.DragDropEvent[A] => Callback] = js.undefined
) extends ReactFnProps(Tree.component)

object Tree {

  private def componentBuilder[A] = ScalaFnComponent[Tree[A]] { props =>
    CTree
      .value(props.value.map(_.toJsNode).toJSArray)
      .applyOrNot(props.selectionMode, (c, p) => c.selectionMode(p.value))
      .nodeTemplate((c, o) =>
        props
          .nodeTemplate(c.asInstanceOf[CTreeNode].data.asInstanceOf[Tree.Node[A]].data, o)
          .rawNode
      )
      .applyOrNot(
        props.expandedKeys,
        (c, p) => c.expandedKeys(StringDictionary(p.map(k => k.value -> true).toSeq*))
      )
      .applyOrNot(
        props.onToggle,
        (c, p) => c.onToggle(e => p(e.value.filter(_._2).keySet.toSet))
      )
      .applyOrNot(props.loading, _.loading(_))
      .applyOrNot(
        props.onSelect,
        (c, p) =>
          c.onSelect(e =>
            p(e.node.asInstanceOf[CTreeNode].data.asInstanceOf[Tree.Node[A]].data, e.originalEvent)
          )
      )
      .applyOrNot(props.dragDropScope, _.dragdropScope(_))
      .applyOrNot(props.onDragDrop, (c, p) => c.onDragDrop(e => p(Tree.DragDropEvent(e))))

  }

  protected val component = componentBuilder[Any]

  enum SelectionMode(val value: single | multiple | checkbox) derives Eq:
    case Single   extends SelectionMode(single)
    case Multiple extends SelectionMode(multiple)
    case Checkbox extends SelectionMode(checkbox)

  opaque type Id = String
  object Id:
    inline def apply(value: String): Id                  = value
    extension (opaqueValue: Id) inline def value: String = opaqueValue

  case class Node[+A](
    id:       Tree.Id,
    data:     A,
    label:    js.UndefOr[String] = js.undefined,
    icon:     js.UndefOr[Icon] = js.undefined,
    children: Seq[Node[A]] = Seq.empty[Node[A]]
  ):
    private[Tree] def toJsNode: CTreeNode =
      val cNode = CTreeNode()
        .setId(id.value)
        .setKey(id.value)
        .setData(this)
        .setIcon(icon.map(_.toPrimeWithClass(PrimeStyles.TreeIcon)))
        .setChildren(children.toJSArray.map(_.toJsNode))
      cNode.label = label

      cNode

  object Node:
    def apply[A](c: CTreeNode): Node[A] =
      val node = c.data.asInstanceOf[Node[A]]
      Node(
        id = node.id,
        data = node.data,
        label = c.label,
        icon = node.icon,
        children = c.children.toList
          .flatMap(_.toSeq)
          .map(n => Node(n.asInstanceOf[CTreeNode]))
      )

  case class DragDropEvent[A](
    dragNode:      Node[A],
    dropNode:      Option[Node[A]],
    dropIndex:     Int,
    value:         Seq[Node[A]],
    originalEvent: TreeDragDropEvent
  )

  object DragDropEvent:
    def apply[A](e: TreeDragDropEvent): DragDropEvent[A] =
      val value    = e.value.toSeq.asInstanceOf[Seq[CTreeNode]].map(Node(_))
      val dragNode = Node(e.dragNode.asInstanceOf[CTreeNode])
      val dropNode = Option(e.dropNode).map(_.asInstanceOf[CTreeNode]).map(Node(_))

      // the dropIndex of the event gives the index of the parent if a node is dropped *on* a node instead of the new index inside of the dropped node
      // To get the correct index, we have to find the index of the dropped node in the list of updated nodes
      def findIndexOfNode(nodes: Seq[Node[A]]): Option[Int] = nodes.indexOf(dragNode) match
        case -1 => nodes.collectFirstSome(n => findIndexOfNode(n.children))
        case i  => i.some

      val dropIndex = findIndexOfNode(value).getOrElse(e.dropIndex.toInt)

      DragDropEvent(
        dragNode = dragNode,
        dropNode = dropNode,
        dropIndex = dropIndex,
        value = value,
        originalEvent = e
      )

}
