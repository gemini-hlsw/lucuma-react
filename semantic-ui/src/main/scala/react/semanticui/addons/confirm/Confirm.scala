// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.addons.confirm

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.VdomNode
import react.common._
import react.semanticui._
import react.semanticui.elements.button.Button
import react.semanticui.elements.icon._
import react.semanticui.{raw => suiraw}
import react.semanticui.modules.modal._
import japgolly.scalajs.react.vdom.TagMod

final case class Confirm(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  basic:                  MyUndefOr[Boolean] = MyUndefOr.undefined,
  cancelButton:           MyUndefOr[ShorthandS[Button]] = MyUndefOr.undefined,
  centered:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  closeIcon:              MyUndefOr[Icon] = MyUndefOr.undefined,
  closeOnDimmerClick:     MyUndefOr[Boolean] = MyUndefOr.undefined,
  closeOnDocumentClick:   MyUndefOr[Boolean] = MyUndefOr.undefined,
  confirmButton:          MyUndefOr[ShorthandS[Button]] = MyUndefOr.undefined,
  content:                MyUndefOr[ShorthandS[ModalContent]] = MyUndefOr.undefined,
  defaultOpen:            MyUndefOr[Boolean] = MyUndefOr.undefined,
  dimmer:                 MyUndefOr[Dimmer] = MyUndefOr.undefined,
  eventPool:              MyUndefOr[String] = MyUndefOr.undefined,
  header:                 MyUndefOr[ShorthandS[ModalHeader]] = MyUndefOr.undefined,
  onActionClickE:         MyUndefOr[Modal.OnActionClick] = MyUndefOr.undefined,
  onActionClick:          MyUndefOr[Callback] = MyUndefOr.undefined,
  onCancelE:              MyUndefOr[Confirm.OnCancel] = MyUndefOr.undefined,
  onCancel:               MyUndefOr[Callback] = MyUndefOr.undefined,
  onCloseE:               MyUndefOr[Modal.OnClose] = MyUndefOr.undefined,
  onClose:                MyUndefOr[Callback] = MyUndefOr.undefined,
  onConfirmE:             MyUndefOr[Confirm.OnConfirm] = MyUndefOr.undefined,
  onConfirm:              MyUndefOr[Callback] = MyUndefOr.undefined,
  onOpenE:                MyUndefOr[Modal.OnOpen] = MyUndefOr.undefined,
  onOpen:                 MyUndefOr[Callback] = MyUndefOr.undefined,
  onMountE:               MyUndefOr[Modal.OnMount] = MyUndefOr.undefined,
  onMount:                MyUndefOr[Callback] = MyUndefOr.undefined,
  open:                   MyUndefOr[Boolean] = MyUndefOr.undefined,
  size:                   MyUndefOr[ModalSize] = MyUndefOr.undefined,
  style:                  MyUndefOr[Style] = MyUndefOr.undefined,
  trigger:                MyUndefOr[VdomNode] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPA[Confirm.ConfirmProps, Confirm] {
  override protected def cprops                     = Confirm.props(this)
  override protected val component                  = Confirm.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object Confirm {
  type RawOnCancel  = js.Function2[ReactMouseEvent, Confirm.ConfirmProps, Unit]
  type OnCancel     = (ReactMouseEvent, Confirm.ConfirmProps) => Callback
  type RawOnConfirm = RawOnCancel
  type OnConfirm    = OnCancel

  @js.native
  @JSImport("semantic-ui-react", "Confirm")
  object RawComponent extends js.Object

  @js.native
  trait ConfirmProps extends Modal.ModalProps {

    /** The cancel button text. */
    var cancelButton: MyUndefOr[suiraw.SemanticShorthandItemS[Button.ButtonProps]] = js.native

    /** The OK button text. */
    var confirmButton: MyUndefOr[suiraw.SemanticShorthandItemS[Button.ButtonProps]] = js.native

    /**
     * Called when the Modal is closed without clicking confirm.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props.
     */
    var onCancel: MyUndefOr[RawOnCancel] = js.native

    /**
     * Called when the OK button is clicked.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props.
     */
    var onConfirm: MyUndefOr[RawOnCancel] = js.native

  }

  def props(q: Confirm): ConfirmProps =
    rawprops(
      q.as,
      q.basic,
      q.cancelButton,
      q.centered,
      q.className,
      q.clazz,
      q.closeIcon,
      q.closeOnDimmerClick,
      q.closeOnDocumentClick,
      q.confirmButton,
      q.content,
      q.defaultOpen,
      q.dimmer,
      q.eventPool,
      q.header,
      q.onActionClickE,
      q.onActionClick,
      q.onCancelE,
      q.onCancel,
      q.onCloseE,
      q.onClose,
      q.onConfirmE,
      q.onConfirm,
      q.onOpenE,
      q.onOpen,
      q.onMountE,
      q.onMount,
      q.open,
      q.size,
      q.style,
      q.trigger
    )

  def rawprops(
    as:                   MyUndefOr[AsC] = MyUndefOr.undefined,
    basic:                MyUndefOr[Boolean] = MyUndefOr.undefined,
    cancelButton:         MyUndefOr[ShorthandS[Button]] = MyUndefOr.undefined,
    centered:             MyUndefOr[Boolean] = MyUndefOr.undefined,
    className:            MyUndefOr[String] = MyUndefOr.undefined,
    clazz:                MyUndefOr[Css] = MyUndefOr.undefined,
    closeIcon:            MyUndefOr[Icon] = MyUndefOr.undefined,
    closeOnDimmerClick:   MyUndefOr[Boolean] = MyUndefOr.undefined,
    closeOnDocumentClick: MyUndefOr[Boolean] = MyUndefOr.undefined,
    confirmButton:        MyUndefOr[ShorthandS[Button]] = MyUndefOr.undefined,
    content:              MyUndefOr[ShorthandS[ModalContent]] = MyUndefOr.undefined,
    defaultOpen:          MyUndefOr[Boolean] = MyUndefOr.undefined,
    dimmer:               MyUndefOr[Dimmer] = MyUndefOr.undefined,
    eventPool:            MyUndefOr[String] = MyUndefOr.undefined,
    header:               MyUndefOr[ShorthandS[ModalHeader]] = MyUndefOr.undefined,
    onActionClickE:       MyUndefOr[Modal.OnActionClick] = MyUndefOr.undefined,
    onActionClick:        MyUndefOr[Callback] = MyUndefOr.undefined,
    onCancelE:            MyUndefOr[Confirm.OnCancel] = MyUndefOr.undefined,
    onCancel:             MyUndefOr[Callback] = MyUndefOr.undefined,
    onCloseE:             MyUndefOr[Modal.OnClose] = MyUndefOr.undefined,
    onClose:              MyUndefOr[Callback] = MyUndefOr.undefined,
    onConfirmE:           MyUndefOr[Confirm.OnConfirm] = MyUndefOr.undefined,
    onConfirm:            MyUndefOr[Callback] = MyUndefOr.undefined,
    onOpenE:              MyUndefOr[Modal.OnOpen] = MyUndefOr.undefined,
    onOpen:               MyUndefOr[Callback] = MyUndefOr.undefined,
    onMountE:             MyUndefOr[Modal.OnMount] = MyUndefOr.undefined,
    onMount:              MyUndefOr[Callback] = MyUndefOr.undefined,
    open:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
    size:                 MyUndefOr[ModalSize] = MyUndefOr.undefined,
    style:                MyUndefOr[Style] = MyUndefOr.undefined,
    trigger:              MyUndefOr[VdomNode] = MyUndefOr.undefined
  ): ConfirmProps = {
    val p = as.toJsObject[ConfirmProps]
    as.toJs.foreach(v => p.as = v)
    basic.foreach(v => p.basic = v)
    cancelButton.toJs.foreach(v => p.cancelButton = v)
    centered.foreach(v => p.centered = v)
    (className.toJsUndefOr, clazz.toJsUndefOr).toJs.foreach(v => p.className = v)
    closeIcon.map(_.props).foreach(v => p.closeIcon = v)
    closeOnDimmerClick.foreach(v => p.closeOnDimmerClick = v)
    closeOnDocumentClick.foreach(v => p.closeOnDocumentClick = v)
    confirmButton.toJs.foreach(v => p.confirmButton = v)
    content.toJs.foreach(v => p.content = v)
    defaultOpen.foreach(v => p.defaultOpen = v)
    dimmer.toJs.foreach(v => p.dimmer = v)
    eventPool.foreach(v => p.eventPool = v)
    header.toJs.foreach(v => p.header = v)
    (onActionClickE, onActionClick).toJs.foreach(v => p.onActionClick = v)
    (onCancelE, onCancel).toJs.foreach(v => p.onCancel = v)
    (onCloseE, onClose).toJs.foreach(v => p.onClose = v)
    (onConfirmE, onConfirm).toJs.foreach(v => p.onConfirm = v)
    (onMountE, onMount).toJs
      .map[Modal.RawOnMount](f => (_, p: Modal.ModalProps) => f(p))
      .foreach(v => p.onMount = v)
    open.foreach(v => p.open = v)
    size.toJs.foreach(v => p.size = v)
    style.map(_.toJsObject).foreach(v => p.style = v)
    trigger.toJs.foreach(v => p.trigger = v)
    p
  }

  private val component =
    JsComponent[ConfirmProps, Children.None, Null](RawComponent)

  def apply(modifiers: TagMod*): Confirm =
    new Confirm(modifiers = modifiers)
}
