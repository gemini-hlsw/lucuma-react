package react

import scala.scalajs.js
import japgolly.scalajs.react.Callback

/**
 * External facade
 */
package object clipboard {
  type OnCopy = (String, Boolean) => Callback

  private[clipboard] object raw {
    type RawOnCopy = js.Function2[String, Boolean, Unit]
  }
}
