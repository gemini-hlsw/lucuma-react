// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.primereact

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.common.*
import lucuma.react.fa.FontAwesomeIcon
import lucuma.typed.primereact.components.{ConfirmPopup => CConfirmPopup}
import lucuma.typed.primereact.confirmpopupConfirmpopupMod.ConfirmPopupProps
import lucuma.typed.primereact.confirmpopupConfirmpopupMod.{confirmPopup => rawConfirmPopup}
import org.scalajs.dom.HTMLElement

import scalajs.js

// This component is setup to be used via the `confirmPopup` method. But to use that,
// you need an empty `ConfirmPop()` on the page.
case class ConfirmPopup() extends ReactFnProps[ConfirmPopup](ConfirmPopup.component)

object ConfirmPopup {
  // To use this method, you need an empty `ConfirmPopup()` somewhere on the page.
  // Note: `onHide` does not seem to be called for clicking outside of the window.
  // Note: `dismissable = false` does not prevent closing when clicking outside window.
  def confirmPopup(
    target:      HTMLElement,
    message:     js.UndefOr[VdomNode] = js.undefined,
    icon:        js.UndefOr[FontAwesomeIcon] = js.undefined,
    footer:      js.UndefOr[VdomNode] = js.undefined,
    dismissable: js.UndefOr[Boolean] = js.undefined, // default: true
    acceptLabel: js.UndefOr[String] = js.undefined,  // default: Yes
    rejectLabel: js.UndefOr[String] = js.undefined,  // default: No
    acceptIcon:  js.UndefOr[Icon] = js.undefined,
    rejectIcon:  js.UndefOr[Icon] = js.undefined,
    acceptClass: js.UndefOr[Css] = js.undefined,
    rejectClass: js.UndefOr[Css] = js.undefined,
    clazz:       js.UndefOr[Css] = js.undefined,
    onHide:      js.UndefOr[ConfirmDialogHideParm => Callback] = js.undefined,
    accept:      js.UndefOr[Callback] = js.undefined,
    reject:      js.UndefOr[Callback] = js.undefined
  ): ConfirmDialogReturn = {
    val props     = ConfirmPopupProps().setTarget(target)
    message.foreach(v => props.setMessage(v.rawNode))
    icon.foreach(v => props.setIcon(v.raw))
    footer.foreach(v => props.setFooter(v.rawNode))
    dismissable.foreach(v => props.setDismissable(v))
    acceptLabel.foreach(v => props.setAcceptLabel(v))
    rejectLabel.foreach(v => props.setRejectLabel(v))
    acceptIcon.foreach(v => props.setAcceptIcon(v.toPrime))
    rejectIcon.foreach(v => props.setRejectIcon(v.toPrime))
    acceptClass.foreach(v => props.setAcceptClassName(v.htmlClass))
    rejectClass.foreach(v => props.setRejectClassName(v.htmlClass))
    clazz.foreach(v => props.setClassName(v.htmlClass))
    onHide.foreach(v => props.setOnHide(s => v(ConfirmDialogHideParm.fromString(s))))
    accept.foreach(v => props.setAccept(v))
    reject.foreach(v => props.setReject(v))
    val rawReturn = rawConfirmPopup(props)
    return ConfirmDialogReturn(
      Callback(rawReturn.show()),
      Callback(rawReturn.hide())
    )
  }

  private val component = ScalaFnComponent[ConfirmPopup] { _ =>
    CConfirmPopup()
  }
}
