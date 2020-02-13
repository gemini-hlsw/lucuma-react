package react.beautifuldnd

import japgolly.scalajs.react._
import japgolly.scalajs.react.{raw => Raw}
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom.html
import scala.scalajs.js
import scala.scalajs.js.|
import scala.scalajs.js.annotation.JSImport

// https://github.com/atlassian/react-beautiful-dnd/blob/master/docs/api/draggable.md
object Draggable {
  @js.native
  @JSImport("react-beautiful-dnd", "Draggable")
  object RawComponent extends js.Object

  @js.native
  trait DraggableProps extends js.Object {
    val style: js.Object | Null
  }

  type DragHandleProps = js.Object | Null

  @js.native
  protected[beautifuldnd] trait ProvidedJS extends js.Object {
    val innerRef: Raw.React.RefFn[html.Element]
    val draggableProps: DraggableProps
    val dragHandleProps: DragHandleProps
  }

  case class Provided(innerRef: TagMod, draggableProps: TagMod, draggableStyle: TagMod, dragHandleProps: TagMod)

  object Provided {
    def apply(provided: Draggable.ProvidedJS): Provided =
      Provided(
        TagMod.fn(_.addRefFn(provided.innerRef)),
        TagMod.fn(_.addAttrsObject(provided.draggableProps)),
        provided.draggableProps.style.toOption.whenDefined( draggableStyle =>
          TagMod.fn(_.addStylesObject(draggableStyle))
        ),
        provided.dragHandleProps.toOption.whenDefined( dragHandleProps =>
          TagMod.fn(_.addAttrsObject(dragHandleProps))
        )
      )
  }

  @js.native
  trait StateSnapshotJS extends js.Object {
    val isDragging: Boolean
  }

  @js.native
  trait RubricJS extends js.Object {

  }

  @js.native
  trait Props extends js.Object {
    var draggableId: DraggableId
    var index: Int
    var children: js.Function3[ProvidedJS, StateSnapshotJS, RubricJS, Raw.React.Node]
  }

  def props(
    draggableId: DraggableId,
    index: Int,
    children: (Provided, StateSnapshotJS, RubricJS) => VdomNode
  ): Props = {
    val p = (new js.Object).asInstanceOf[Props]
    p.draggableId = draggableId
    p.index = index
    p.children = (p, ss, r) => children(Provided(p), ss, r).rawNode
    p
  }

  val component = JsComponent[Props, Children.None, Null](RawComponent)

  def apply(
    draggableId: DraggableId,
    index: Int    
  )(children: (Provided, StateSnapshotJS, RubricJS) => VdomNode) = {
    component.withKey(draggableId)(props(draggableId, index, children))
  }
}