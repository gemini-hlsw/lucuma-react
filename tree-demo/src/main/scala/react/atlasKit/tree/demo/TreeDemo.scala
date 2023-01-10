// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.atlasKit.tree.demo

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom.document
import react.atlasKit.tree.Tree
import react.atlasKit.tree.Tree.RenderItemParams

import scala.scalajs.js.annotation._

// Adaptation of https://atlaskit.atlassian.com/examples/confluence/tree/drag-and-drop
object TreeDemo {
  final case class Props[A](tree: Tree.Data[A], render: RenderItemParams[A] => VdomNode)
  final case class State[A](tree: Tree.Data[A])

  class Backend[A]($ : BackendScope[Props[A], State[A]]) {
    def onDragEnd(source: Tree.Position, destination: Option[Tree.Position]): Callback =
      destination
        .map(dest => $.modState(state => State(Tree.moveItemOnTree(state.tree, source, dest))))
        .getOrEmpty

    def render(props: Props[A], state: State[A]): VdomElement =
      <.div(
        <.div(^.height := "600px", ^.width := "1000px")(
          Tree[A](tree = state.tree,
                  renderItem = props.render,
                  onDragEnd = onDragEnd _,
                  isDragEnabled = true
          )
        )
      )
  }

  val component = ScalaComponent
    .builder[Props[Any]]("TreeDemo")
    .initialStateFromProps(props => State(props.tree))
    .backend(new Backend[Any](_))
    .renderBackend
    .build

  def apply[A](p: Props[A]) = component(p.asInstanceOf[Props[Any]])

}

@JSExportTopLevel("Demo")
object Demo {
  def renderItem(params: RenderItemParams[DemoData]) =
    <.div(params.provided.innerRef,
          params.provided.draggableProps,
          params.provided.dragHandleProps
    )(
      params.item.data.map(_.title).getOrElse[String]("???")
    )

  val component = ScalaComponent
    .builder[Unit]("Demo")
    .stateless
    .render_P { _ =>
      <.div(
        TreeDemo(TreeDemo.Props(treeWithTwoBranches, renderItem _)).vdomElement
      )
    }
    .build

  @JSExport
  def main(): Unit = {
    val container = Option(document.getElementById("root")).getOrElse {
      val elem = document.createElement("div")
      elem.id = "root"
      document.body.appendChild(elem)
      elem
    }
    component().renderIntoDOM(container)
    ()
  }
}
