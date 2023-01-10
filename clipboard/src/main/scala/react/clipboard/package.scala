// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react

import japgolly.scalajs.react.Callback

import scala.scalajs.js

package clipboard {
  // Options
  trait ClipboardOptions extends js.Object {
    var debug: js.UndefOr[Boolean]                     = js.undefined
    var message: js.UndefOr[String]                    = js.undefined
    var format: js.UndefOr[String]                     = js.undefined
    var onCopy: js.UndefOr[ClipboardOptions.OnCopyRaw] = js.undefined
  }

  object ClipboardOptions {
    type OnCopyRaw = js.Function1[js.Object, Unit]
    type OnCopy    = js.Object => Callback

    def apply(
      debug:   js.UndefOr[Boolean] = js.undefined,
      message: js.UndefOr[String] = js.undefined,
      format:  js.UndefOr[String] = js.undefined,
      onCopy:  js.UndefOr[OnCopy] = js.undefined
    ): ClipboardOptions = {
      val p = (new js.Object).asInstanceOf[ClipboardOptions]
      debug.foreach(v => p.debug = v)
      message.foreach(v => p.message = v)
      format.foreach(v => p.format = v)
      onCopy.foreach(v => p.onCopy = ((c: js.Object) => v(c).runNow()): OnCopyRaw)
      p
    }

    val Default: ClipboardOptions =
      ClipboardOptions(false, "Copy to clipboard: #{key}, Enter")
  }
}

/**
 * External facade
 */
package object clipboard  {
  // OnCopy callback
  type OnCopy = (String, Boolean) => Callback
}
