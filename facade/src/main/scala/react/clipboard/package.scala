package react

import scala.scalajs.js
import japgolly.scalajs.react.Callback

package clipboard {
  // Options
  trait ClipboardOptions extends js.Object {
    var debug: js.UndefOr[Boolean]  = js.undefined
    var message: js.UndefOr[String] = js.undefined
    var format: js.UndefOr[String]  = js.undefined
  }

  object ClipboardOptions {
    def apply(
      debug:   js.UndefOr[Boolean] = js.undefined,
      message: js.UndefOr[String]  = js.undefined,
      format:  js.UndefOr[String]  = js.undefined
    ): ClipboardOptions = {
      val p = (new js.Object).asInstanceOf[ClipboardOptions]
      p.debug   = debug
      p.message = message
      p.format  = format
      p
    }

    val Default: ClipboardOptions =
      ClipboardOptions(false, "Copy to clipboard: #{key}, Enter")
  }
}

/**
  * External facade
  */
package object clipboard {
  // OnCopy callback
  type OnCopy = (String, Boolean) => Callback
}
