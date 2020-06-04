package react.beautifuldnd

import japgolly.scalajs.react._
import japgolly.scalajs.react.{ raw => Raw }
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
  protected[react] trait ProvidedJS extends js.Object {
    val innerRef: Raw.React.RefFn[html.Element]
    val draggableProps: DraggableProps
    val dragHandleProps: DragHandleProps
  }

  case class Provided(
    innerRef:        TagMod,
    draggableProps:  TagMod,
    draggableStyle:  TagMod,
    dragHandleProps: TagMod
  )

  object Provided {
    def apply(provided: Draggable.ProvidedJS): Provided =
      Provided(
        TagMod.fn(_.addRefFn(provided.innerRef)),
        TagMod.fn(_.addAttrsObject(provided.draggableProps)),
        provided.draggableProps.style.toOption.whenDefined(draggableStyle =>
          TagMod.fn(_.addStylesObject(draggableStyle))
        ),
        provided.dragHandleProps.toOption.whenDefined(dragHandleProps =>
          TagMod.fn(_.addAttrsObject(dragHandleProps))
        )
      )
  }

  @js.native // Actually, from css-box-model
  trait Position extends js.Object {
    val x: Int
    val y: Int
  }

  @js.native
  trait DropAnimation extends js.Object {
    val duration: Int
    val curve: String
    val moveTo: Position
    val opacity: js.UndefOr[Int]
    val scale: js.UndefOr[Int]
  }

  @js.native
  trait StateSnapshot extends js.Object {
    val isDragging: Boolean
    val isDropAnimating: Boolean
    val dropAnimation: js.UndefOr[DropAnimation]
    val draggingOver: js.UndefOr[DroppableId]
    val combineWith: js.UndefOr[DraggableId]
    val combineTargetFor: js.UndefOr[DraggableId]
    val mode: js.UndefOr[MovementMode]
  }

  @js.native
  trait Rubric extends js.Object {}

  @js.native
  trait Props extends js.Object {
    var draggableId: DraggableId
    var index: Int
    var children: DraggableChildrenFn
    var isDragDisabled: js.UndefOr[Boolean]
    var disableInteractiveElementBlocking: js.UndefOr[Boolean]
    var shouldRespectForcePress: js.UndefOr[Boolean]
  }
  object Props {
    def apply(
      draggableId:                       DraggableId,
      index:                             Int,
      children:                          (Provided, StateSnapshot, Rubric) => VdomNode,
      isDragDisabled:                    js.UndefOr[Boolean] = js.undefined,
      disableInteractiveElementBlocking: js.UndefOr[Boolean] = js.undefined,
      shouldRespectForcePress:           js.UndefOr[Boolean] = js.undefined
    ): Props = {
      val p = (new js.Object).asInstanceOf[Props]
      p.draggableId = draggableId
      p.index = index
      p.children = (p, ss, r) => children(Provided(p), ss, r).rawNode
      p.isDragDisabled = isDragDisabled
      p.disableInteractiveElementBlocking = disableInteractiveElementBlocking
      p.shouldRespectForcePress = shouldRespectForcePress
      p
    }
  }

  val component = JsComponent[Props, Children.None, Null](RawComponent)

  def apply(
    draggableId:                       DraggableId,
    index:                             Int,
    isDragDisabled:                    js.UndefOr[Boolean] = js.undefined,
    disableInteractiveElementBlocking: js.UndefOr[Boolean] = js.undefined,
    shouldRespectForcePress:           js.UndefOr[Boolean] = js.undefined
  )(children:                          (Provided, StateSnapshot, Rubric) => VdomNode) =
    component.withKey(draggableId)(
      Props(
        draggableId,
        index,
        children,
        isDragDisabled,
        disableInteractiveElementBlocking,
        shouldRespectForcePress
      )
    )
}
