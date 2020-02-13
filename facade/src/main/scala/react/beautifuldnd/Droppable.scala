package react.beautifuldnd

import japgolly.scalajs.react._
import japgolly.scalajs.react.{raw => Raw}
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom.html
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

case class DroppableProvided(innerRef: TagMod, droppableProps: TagMod, placeholder: TagMod)

object DroppableProvided {
  def apply(provided: Droppable.DroppableProvidedJS): DroppableProvided =
    DroppableProvided(
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
  protected[beautifuldnd] trait DroppableProvidedJS extends js.Object {
    val innerRef: Raw.React.RefFn[html.Element]
    val droppableProps: DroppableProps
    val placeholder: js.UndefOr[Raw.React.Node]
  }

  @js.native
  trait DroppableStateSnapshotJS extends js.Object {
    val isDraggingOver: Boolean
  }
  
  @js.native
  trait Props extends js.Object {
    var droppableId: DroppableId
    var children: js.Function2[DroppableProvidedJS, DroppableStateSnapshotJS, Raw.React.Node]
  }

  def props(
    droppableId: DroppableId,
    children: (DroppableProvided, DroppableStateSnapshotJS) => VdomNode
  ): Props = {
    val p = (new js.Object).asInstanceOf[Props]
    p.droppableId = droppableId
    p.children = (p, ss) => children(DroppableProvided(p), ss).rawNode
    p
  }

  val component = JsComponent.force[Props, Children.None, Null](RawComponent)

  def apply(
    droppableId: DroppableId
  )(children: (DroppableProvided, DroppableStateSnapshotJS) => VdomNode) = {
    component(props(droppableId, children))
  }
}