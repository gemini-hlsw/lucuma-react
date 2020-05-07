package react.atlasKit.tree.demo

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom.document
import react.atlasKit.tree.Tree
import react.atlasKit.tree.Tree.RenderItemParams

// Adaptation of https://atlaskit.atlassian.com/examples/confluence/tree/drag-and-drop
object TreeDemo {
  final case class Props()

  class Backend($ : BackendScope[Props, Unit]) {
    println($) // Remove unused warning

    def renderItem(params: RenderItemParams[DemoData]) =
      <.div(params.provided.innerRef, params.provided.draggableProps)(
        params.item.data.map(_.title).getOrElse[String]("???")
      )

    def render(): VdomElement =
      <.div(
        <.div(^.height := "600px", ^.width := "1000px")(
          Tree(tree = treeWithTwoBranches, renderItem = renderItem _)
        )
      )
  }

  val component = ScalaComponent
    .builder[Props]("TreeDemo")
    .backend(new Backend(_))
    .renderBackend
    .build

  def apply(p: Props) = component(p)

}

object MainDemo {
  val component = ScalaComponent
    .builder[Unit]("Demo")
    .stateless
    .render_P { _ =>
      <.div(
        TreeDemo(TreeDemo.Props()).vdomElement
      )
    }
    .build

  def main(args: Array[String]): Unit = {
    component().renderIntoDOM(document.getElementById("root"))
    ()
  }
}
