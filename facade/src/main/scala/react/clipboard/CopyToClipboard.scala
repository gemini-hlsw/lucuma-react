package react
package clipboard

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.VdomNode
// import japgolly.scalajs.react.raw.ReactNode
import japgolly.scalajs.react.component.Js.{RawMounted, UnmountedMapped}
import japgolly.scalajs.react.internal.Effect.Id

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

import raw._
import japgolly.scalajs.react.Callback

object CopyToClipboard {
  @js.native
  @JSImport("react-copy-to-clipboard", JSImport.Namespace, "CopyToClipboard")
  object RawComponent extends js.Object

  @js.native
  trait Props extends js.Object {
    /** Text to be copied to clipboard */
    var text: String = js.native
    /** Optional callback, will be called when text is copied */
    var onCopy: RawOnCopy = js.native
//     options: PropTypes.shape({
//       debug: PropTypes.bool,
//       message: PropTypes.string
  }

  def props(
    text: String,
    onCopy: OnCopy = (_, _) => Callback.empty
  ): Props = {
    val p = (new js.Object).asInstanceOf[Props]
    p.text = text
    p.onCopy = (s, b) => onCopy(s, b).runNow
    p
  }

  private val component = JsComponent[Props, Children.Varargs, Null](RawComponent)

  def apply(p: Props, children: VdomNode*): UnmountedMapped[Id, Props, Null, RawMounted, Props, Null] = component(p)(children: _*)
}
