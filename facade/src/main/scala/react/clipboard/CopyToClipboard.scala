package react
package clipboard

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.VdomNode
import japgolly.scalajs.react.vdom.html_<^._
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

object CopyToClipboard {
  @js.native
  @JSImport("copy-to-clipboard", JSImport.Namespace)
  object JsCopy extends js.Function2[String, ClipboardOptions, Boolean] {
    def apply(text: String, options: ClipboardOptions = ClipboardOptions.Default): Boolean = js.native
  }

  /** Text to be copied to clipboard */
  /** Optional callback, will be called when text is copied */
  /** Optional copy-to-clipboard options. */
  final case class Props(
    text: String,
    onCopy: OnCopy,
    options: ClipboardOptions
  )

  def props(
    text: String,
    onCopy: OnCopy = (_, _) => Callback.empty,
    options: Option[ClipboardOptions] = None
  ): Props = new Props(
    text,
    onCopy,
    options.getOrElse(ClipboardOptions.Default)
  )

  private def copy(p: Props): Callback =
    CallbackTo[Boolean](JsCopy(p.text, p.options)) >>= {r => p.onCopy(p.text, r)}

  private val component = ScalaComponent.builder[Props]("CopyToClipboard")
    .render_PC((p, c) =>
      <.div(
        ^.onClick --> copy(p),
        c)
    )
    .build

  def apply(p: Props, children: VdomNode) = component(p)((children))
}
