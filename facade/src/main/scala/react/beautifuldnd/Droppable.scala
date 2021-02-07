package react.beautifuldnd

import japgolly.scalajs.react._
import japgolly.scalajs.react.{ raw => Raw }
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom.html
import scala.scalajs.js
import scala.scalajs.js.annotation.{ JSImport, JSName }

case class Provided(innerRef: TagMod, droppableProps: TagMod, placeholder: TagMod)

object Provided {
  def apply(provided: Droppable.ProvidedJS): Provided =
    Provided(
      TagMod.fn(_.addRefFn(provided.innerRef)),
      TagMod.fn(_.addAttrsObject(provided.droppableProps)),
      provided.placeholder.toOption.whenDefined(identity)
    )
}

object Droppable {
  @js.native
  @JSImport("react-beautiful-dnd", "Droppable")
  object RawComponent extends js.Object

  // https://github.com/atlassian/react-beautiful-dnd/blob/master/docs/api/droppable.md

  type DroppableProps = js.Object

  @js.native
  protected[beautifuldnd] trait ProvidedJS extends js.Object {
    val innerRef: Raw.React.RefFn[html.Element]
    val droppableProps: DroppableProps
    val placeholder: js.UndefOr[Raw.React.Node]
  }

  @js.native
  trait StateSnapshot extends js.Object {
    val isDraggingOver: Boolean
    val draggingOverWith: js.UndefOr[DraggableId]
    val draggingFromThisWith: js.UndefOr[DraggableId]
    val isUsingPlaceholder: Boolean
  }

  @js.native
  trait Props extends js.Object {
    var droppableId: DroppableId
    @JSName("type") var tpe: js.UndefOr[TypeId]
    var mode: js.UndefOr[DroppableMode]
    var isDropDisabled: js.UndefOr[Boolean]
    var isCombineEnabled: js.UndefOr[Boolean]
    var direction: js.UndefOr[Direction]
    var ignoreContainerClipping: js.UndefOr[Boolean]
    var renderClone: js.UndefOr[DraggableChildrenFn]
    var getContainerForClone: js.UndefOr[Unit => html.Element]
    var children: js.Function2[ProvidedJS, StateSnapshot, Raw.React.Node]
  }
  object Props {
    def apply(
      droppableId:             DroppableId,
      tpe:                     js.UndefOr[TypeId] = js.undefined,
      mode:                    js.UndefOr[DroppableMode] = js.undefined,
      isDropDisabled:          js.UndefOr[Boolean] = js.undefined,
      isCombineEnabled:        js.UndefOr[Boolean] = js.undefined,
      direction:               js.UndefOr[Direction] = js.undefined,
      ignoreContainerClipping: js.UndefOr[Boolean] = js.undefined,
      renderClone:             js.UndefOr[DraggableChildrenFn] = js.undefined,
      getContainerForClone:    js.UndefOr[Unit => html.Element] = js.undefined,
      children:                (Provided, StateSnapshot) => VdomNode
    ): Props = {
      val p = (new js.Object).asInstanceOf[Props]
      p.droppableId = droppableId
      p.tpe = tpe
      p.mode = mode
      p.isDropDisabled = isDropDisabled
      p.isCombineEnabled = isCombineEnabled
      p.direction = direction
      p.ignoreContainerClipping = ignoreContainerClipping
      p.renderClone = renderClone
      p.getContainerForClone = getContainerForClone
      p.children = (p, ss) => children(Provided(p), ss).rawNode
      p
    }
  }

  val component = JsComponent.force[Props, Children.None, Null](RawComponent)

  def apply(
    droppableId:             DroppableId,
    tpe:                     js.UndefOr[TypeId] = js.undefined,
    mode:                    js.UndefOr[DroppableMode] = js.undefined,
    isDropDisabled:          js.UndefOr[Boolean] = js.undefined,
    isCombineEnabled:        js.UndefOr[Boolean] = js.undefined,
    direction:               js.UndefOr[Direction] = js.undefined,
    ignoreContainerClipping: js.UndefOr[Boolean] = js.undefined,
    renderClone:             js.UndefOr[DraggableChildrenFn] = js.undefined,
    getContainerForClone:    js.UndefOr[Unit => html.Element] = js.undefined
  )(children:                (Provided, StateSnapshot) => VdomNode) =
    component(
      Props(droppableId,
            tpe,
            mode,
            isDropDisabled,
            isCombineEnabled,
            direction,
            ignoreContainerClipping,
            renderClone,
            getContainerForClone,
            children
      )
    )
}
