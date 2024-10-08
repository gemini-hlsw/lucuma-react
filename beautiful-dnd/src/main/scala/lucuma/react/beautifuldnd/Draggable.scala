// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.beautifuldnd

import japgolly.scalajs.react.*
import japgolly.scalajs.react.facade as Raw
import japgolly.scalajs.react.vdom.html_<^.*
import org.scalajs.dom.html

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.annotation.JSName

// https://github.com/atlassian/react-beautiful-dnd/blob/master/docs/api/draggable.md
object Draggable {
  @js.native
  @JSImport("@atlaskit/pragmatic-drag-and-drop-react-beautiful-dnd-migration", "Draggable")
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
        TagMod.fn(_.addAttrsObject(provided.draggableProps, allowAttr = _ != "style")),
        provided.draggableProps.style.toOption.whenDefined(draggableStyle =>
          TagMod.fn(_.addStylesObject(draggableStyle))
        ),
        provided.dragHandleProps.toOption.whenDefined(dragHandleProps =>
          TagMod.fn(_.addAttrsObject(dragHandleProps))
        )
      )
  }

  type Render = (Provided, StateSnapshot, Rubric) => VdomNode

  protected[react] type RenderJS = js.Function3[
    Draggable.ProvidedJS,
    Draggable.StateSnapshot,
    Draggable.Rubric,
    Raw.React.Node | Null
  ]

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
  trait Location extends js.Object {
    val droppableId: DroppableId
    val index: Int
  }

  @js.native
  trait Rubric extends js.Object {
    val draggableId: DraggableId
    @JSName("type") val tpe: TypeId
    val source: DraggableLocation
  }

  @js.native
  trait Props extends js.Object {
    var draggableId: DraggableId
    var index: Int
    var children: RenderJS
    var isDragDisabled: js.UndefOr[Boolean]
    var disableInteractiveElementBlocking: js.UndefOr[Boolean]
    var shouldRespectForcePress: js.UndefOr[Boolean]
  }
  object Props {
    def apply(
      draggableId:                       DraggableId,
      index:                             Int,
      children:                          Render,
      isDragDisabled:                    js.UndefOr[Boolean] = js.undefined,
      disableInteractiveElementBlocking: js.UndefOr[Boolean] = js.undefined,
      shouldRespectForcePress:           js.UndefOr[Boolean] = js.undefined
    ): Props = {
      val p = (new js.Object).asInstanceOf[Props]
      p.draggableId = draggableId
      p.index = index
      p.children = (p, ss, r) => children(Provided(p), ss, r).rawNode
      isDragDisabled.foreach(p.isDragDisabled = _)
      disableInteractiveElementBlocking.foreach(p.disableInteractiveElementBlocking = _)
      shouldRespectForcePress.foreach(p.shouldRespectForcePress = _)
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
  )(children: Render) =
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
