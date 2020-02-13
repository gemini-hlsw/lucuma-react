package react.beautifuldnd

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.VdomNode
import scala.scalajs.js
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

  val component = JsComponent[Props, Children.Varargs, Null](RawComponent)

  def apply(
    onDragEnd: (DropResult, ResponderProvided) => Callback
  )(children: VdomNode*) = component(props(onDragEnd))(children:_*)
}