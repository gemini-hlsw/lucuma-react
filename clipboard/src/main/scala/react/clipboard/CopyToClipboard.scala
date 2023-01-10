// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react

package clipboard

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import react.common._

import scala.language.implicitConversions
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/**
 * Text to be copied to clipboard Optional callback, will be called when text is copied Optional
 * copy-to-clipboard options.
 */
final case class CopyToClipboard(
  text:    String,
  onCopy:  OnCopy = (_, _) => Callback.empty,
  options: Option[ClipboardOptions] = None
) extends ReactPropsWithChildren(CopyToClipboard.component) {
  @inline def render: Seq[CtorType.ChildArg] => VdomElement =
    CopyToClipboard.component(this)
}

object CopyToClipboard {
  type Props = CopyToClipboard

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
    ) >>= { r => p.onCopy(p.text, r) }

  val component = ScalaComponent
    .builder[Props]("CopyToClipboard")
    .render_PC((p, c) => <.div(^.onClick --> copy(p), c))
    .build
}
