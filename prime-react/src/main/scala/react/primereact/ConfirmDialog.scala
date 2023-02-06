// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import cats.syntax.all.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import org.scalablytyped.runtime.StObject
import react.common.*
import react.fa.FontAwesomeIcon
import lucuma.typed.primereact.components.{ConfirmDialog => CConfirmDialog}
import lucuma.typed.primereact.confirmdialogConfirmdialogMod.ConfirmDialogProps
import lucuma.typed.primereact.confirmdialogConfirmdialogMod.{
  ConfirmDialogReturn => RawConfirmDialogReturn
}
import lucuma.typed.primereact.confirmdialogConfirmdialogMod.{confirmDialog => rawConfirmDialog}

import scalajs.js

// This component is setup to be used via the `confirmDialog` method. But to use that,
// you need an empty `ConfirmDialog()` on the page.
case class ConfirmDialog(
) extends ReactFnProps[ConfirmDialog](ConfirmDialog.component)

object ConfirmDialog {

  // To use this method, you need an empty `ConfirmDialog()` somewhere on the page.
  // Big Note: According to the documentation and the typescript types, the
  // `confirmDialog` function is supposed to work like the `confirmPopup` function
  // and return an object with `show` and `hide` methods and you call `show`
  // to open it. However.... If you do that, clicking on ANY button on the page,
  // as well as many other controls will open the dialog, rendering it useless.
  // However, just calling the `confirmDialog` function (wrapped in a callback)
  // seems to work just fine.
  def confirmDialog(
    message:     js.UndefOr[VdomNode] = js.undefined,
    icon:        js.UndefOr[FontAwesomeIcon] = js.undefined,
    header:      js.UndefOr[VdomNode] = js.undefined,
    footer:      js.UndefOr[VdomNode] = js.undefined,
    acceptLabel: js.UndefOr[String] = js.undefined, // default: Yes
    rejectLabel: js.UndefOr[String] = js.undefined, // default: No
    acceptIcon:  js.UndefOr[FontAwesomeIcon | String] = js.undefined,
    rejectIcon:  js.UndefOr[FontAwesomeIcon | String] = js.undefined,
    acceptClass: js.UndefOr[Css] = js.undefined,
    rejectClass: js.UndefOr[Css] = js.undefined,
    clazz:       js.UndefOr[Css] = js.undefined,
    position:    js.UndefOr[DialogPosition] = js.undefined,
    onHide:      js.UndefOr[ConfirmDialogHideParm => Callback] = js.undefined,
    accept:      js.UndefOr[Callback] = js.undefined,
    reject:      js.UndefOr[Callback] = js.undefined
  ): Callback =
    Callback {
      val props = ConfirmDialogProps()
      message.foreach(v => props.setMessage(v.rawNode))
      icon.foreach(v => props.setIcon(v.raw))
      // header not in the ST facade
      header.foreach(v => StObject.set(props, "header", v.rawNode))
      footer.foreach(v => props.setFooter(v.rawNode))
      acceptLabel.foreach(v => props.setAcceptLabel(v))
      rejectLabel.foreach(v => props.setRejectLabel(v))
      acceptIcon.foreach(v => props.setAcceptIcon(v.toPrime))
      rejectIcon.foreach(v => props.setRejectIcon(v.toPrime))
      acceptClass.foreach(v => props.setAcceptClassName(v.htmlClass))
      rejectClass.foreach(v => props.setRejectClassName(v.htmlClass))
      clazz.foreach(v => props.setClassName(v.htmlClass))

      // position not in the ST facade, either
      position.foreach(v => StObject.set(props, "position", v.value))
      onHide.foreach(v => props.setOnHide(s => v(ConfirmDialogHideParm.fromString(s))))
      accept.foreach(v => props.setAccept(v))
      reject.foreach(v => props.setReject(v))

      val rawReturn = rawConfirmDialog(props)
    }
    // In case this ever starts working as advertised, remove the Callback wrapper above
    // and uncomment the line below.
    // ConfirmDialogReturn(Callback(rawReturn.show()), Callback(rawReturn.hide()))

  private val component = ScalaFnComponent[ConfirmDialog] { props =>
    CConfirmDialog()
  }
}
