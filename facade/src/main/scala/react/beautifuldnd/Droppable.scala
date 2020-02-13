package react.beautifuldnd

import japgolly.scalajs.react._
import japgolly.scalajs.react.{raw => Raw}
import japgolly.scalajs.react.vdom._
import org.scalajs.dom.html
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

object Droppable {
  @js.native
  @JSImport("react-beautiful-dnd", "Droppable")
  object RawComponent extends js.Object

  // https://github.com/atlassian/react-beautiful-dnd/blob/master/docs/api/droppable.md

  type DroppableProps = js.Object

  @js.native
  trait DroppableProvided extends js.Object {
    // val innerRef: js.UndefOr[HTMLElement]
    val innerRef: Raw.React.RefFn[html.Element]
    val droppableProps: DroppableProps
    val placeholder: js.UndefOr[Raw.React.Node]
  }

  @js.native
  trait DroppableStateSnapshot extends js.Object {

  }
  
  @js.native
  trait Props extends js.Object {
    var droppableId: DroppableId
    var children: js.Function2[DroppableProvided, DroppableStateSnapshot, Raw.React.Node]
  }

  def props(
    droppableId: DroppableId,
    children: (DroppableProvided, DroppableStateSnapshot) => VdomNode
  ): Props = {
    val p = (new js.Object).asInstanceOf[Props]
    p.droppableId = droppableId
    p.children = (p, ss) => children(p, ss).rawNode
    p
  }

  val component = JsComponent.force[Props, Children.None, Null](RawComponent)

  def apply(
    droppableId: DroppableId
  )(children: (DroppableProvided, DroppableStateSnapshot) => VdomNode) = {
    component(props(droppableId, children))
  }
}