package react

package clipboard

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import react.common._
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/**
  * Text to be copied to clipboard
  * Optional callback, will be called when text is copied
  * Optional copy-to-clipboard options.
  */
final case class CopyToClipboard(
  text:    String,
  onCopy:  OnCopy = (_, _) => Callback.empty,
  options: Option[ClipboardOptions] = None
) extends ReactPropsWithChildren {
  @inline def render: Seq[CtorType.ChildArg] => VdomElement =
    CopyToClipboard.component(this)
}

object CopyToClipboard {
  @js.native
  @JSImport("copy-to-clipboard", JSImport.Namespace)
  object JsCopy extends js.Function2[String, ClipboardOptions, Boolean] {
    def apply(
      text:    String,
      options: ClipboardOptions = ClipboardOptions.Default
    ): Boolean =
      js.native
  }

  private def copy(p: CopyToClipboard): Callback =
    CallbackTo[Boolean](
      JsCopy(p.text, p.options.getOrElse(ClipboardOptions.Default))
    ) >>= { r =>
      p.onCopy(p.text, r)
    }

  private val component = ScalaComponent
    .builder[CopyToClipboard]("CopyToClipboard")
    .render_PC((p, c) => <.div(^.onClick --> copy(p), c))
    .build
}
