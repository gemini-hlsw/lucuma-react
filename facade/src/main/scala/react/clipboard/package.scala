package react

import scala.scalajs.js
import japgolly.scalajs.react.Callback

package clipboard {
  // Options
  trait ClipboardOptions extends js.Object {
    var debug: Boolean
    var message: String
  }
  object ClipboardOptions {
    def apply(debug: Boolean, message: String): ClipboardOptions = {
      val p = (new js.Object).asInstanceOf[ClipboardOptions]
      p.debug = debug
      p.message = message
      p
    }

    val Default: ClipboardOptions = ClipboardOptions(false, "Copy to clipboard: #{key}, Enter")
  }

}
/**
 * External facade
 */
package object clipboard {
  // OnCopy callback
  type OnCopy = (String, Boolean) => Callback
}
