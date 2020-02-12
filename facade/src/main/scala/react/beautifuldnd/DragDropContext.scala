package react.beautifuldnd

import japgolly.scalajs.react._
// import japgolly.scalajs.react.component.Js.RawMounted
// import japgolly.scalajs.react.component.Js.UnmountedMapped
// import japgolly.scalajs.react.vdom.VdomElement
// import japgolly.scalajs.react.internal.Effect.Id
import scala.scalajs.js
// import scala.scalajs.js.|
import scala.scalajs.js.annotation.JSImport

// https://github.com/atlassian/react-beautiful-dnd/blob/master/docs/api/drag-drop-context.md
// https://github.com/atlassian/react-beautiful-dnd/blob/master/docs/guides/responders.md
object DragDropContext {
  @js.native
  @JSImport("react-beautiful-dnd", "DragDropContext")
  object RawComponent extends js.Object

  type OnDragEndResponder = js.Function2[DropResult, ResponderProvided, Unit]
  
  @js.native
  trait Props extends js.Object {
    var onDragEnd: OnDragEndResponder
  }

  def props(
    onDragEnd: (DropResult, ResponderProvided) => Callback
  ): Props = {
    val p = (new js.Object).asInstanceOf[Props]
    p.onDragEnd = (r, p) => onDragEnd(r, p).runNow()
    p
  }

  // object Props {
    // implicit class PropsOps(p: Props) {
      // @inline def render: VdomElement = component.apply(p)()
    // }
  // }  

  /*private*/ val component = JsComponent[Props, Children.Varargs, Null](RawComponent)

  // def apply(p: Props) = component(p) _

  // def apply(p: Props): Seq[CtorType.ChildArg] => UnmountedMapped[Id, Props, Null, RawMounted[Props, Null], Props, Null] =
    // component.apply(p) _
}