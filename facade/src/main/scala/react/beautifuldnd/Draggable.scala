package react.beautifuldnd

import japgolly.scalajs.react._
import japgolly.scalajs.react.{raw => Raw}
import japgolly.scalajs.react.vdom._
import org.scalajs.dom.html
import scala.scalajs.js
import scala.scalajs.js.|
import scala.scalajs.js.annotation.JSImport

object Draggable {
  @js.native
  @JSImport("react-beautiful-dnd", "Draggable")
  object RawComponent extends js.Object

  type DraggableProps = js.Object

  type DragHandleProps = js.Object | Null

  @js.native
  trait DraggableProvided extends js.Object {
    // val innerRef: js.UndefOr[HTMLElement]
    val innerRef: Raw.React.RefFn[html.Element]
    val draggableProps: DraggableProps
    val dragHandleProps: DragHandleProps
  }

  @js.native
  trait DraggableStateSnapshot extends js.Object {

  }

    @js.native
  trait DraggableRubric extends js.Object {

  }

  // https://github.com/atlassian/react-beautiful-dnd/blob/master/docs/api/draggable.md
  @js.native
  trait Props extends js.Object {
    var draggableId: DraggableId
    var index: Int
    var children: js.Function3[DraggableProvided, DraggableStateSnapshot, DraggableRubric, Raw.React.Node]
  }

  def props(
    draggableId: DraggableId,
    index: Int,
    children: (DraggableProvided, DraggableStateSnapshot, DraggableRubric) => VdomNode
  ): Props = {
    val p = (new js.Object).asInstanceOf[Props]
    p.draggableId = draggableId
    p.index = index
    p.children = (p, ss, r) => children(p, ss, r).rawNode
    p
  }

  val component = JsComponent[Props, Children.None, Null](RawComponent)

  def apply(
    draggableId: DraggableId,
    index: Int    
  )(children: (DraggableProvided, DraggableStateSnapshot, DraggableRubric) => VdomNode) = {
    component.withKey(draggableId)(props(draggableId, index, children))
  }
}