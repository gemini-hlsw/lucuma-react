package react.semanticui.addons.confirm

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.VdomNode
import react.common._
import react.semanticui._
import react.semanticui.elements.button.Button
import react.semanticui.elements.icon._
import react.semanticui.{ raw => suiraw }
import react.semanticui.modules.modal._
import japgolly.scalajs.react.vdom.TagMod

final case class Confirm(
  as:                     js.UndefOr[AsC] = js.undefined,
  basic:                  js.UndefOr[Boolean] = js.undefined,
  cancelButton:           js.UndefOr[ShorthandS[Button]] = js.undefined,
  centered:               js.UndefOr[Boolean] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  closeIcon:              js.UndefOr[Icon] = js.undefined,
  closeOnDimmerClick:     js.UndefOr[Boolean] = js.undefined,
  closeOnDocumentClick:   js.UndefOr[Boolean] = js.undefined,
  confirmButton:          js.UndefOr[ShorthandS[Button]] = js.undefined,
  content:                js.UndefOr[ShorthandS[ModalContent]] = js.undefined,
  defaultOpen:            js.UndefOr[Boolean] = js.undefined,
  dimmer:                 js.UndefOr[Dimmer] = js.undefined,
  eventPool:              js.UndefOr[String] = js.undefined,
  header:                 js.UndefOr[ShorthandS[ModalHeader]] = js.undefined,
  onActionClickE:         js.UndefOr[Modal.OnActionClick] = js.undefined,
  onActionClick:          js.UndefOr[Callback] = js.undefined,
  onCancelE:              js.UndefOr[Confirm.OnCancel] = js.undefined,
  onCancel:               js.UndefOr[Callback] = js.undefined,
  onCloseE:               js.UndefOr[Modal.OnClose] = js.undefined,
  onClose:                js.UndefOr[Callback] = js.undefined,
  onConfirmE:             js.UndefOr[Confirm.OnConfirm] = js.undefined,
  onConfirm:              js.UndefOr[Callback] = js.undefined,
  onOpenE:                js.UndefOr[Modal.OnOpen] = js.undefined,
  onOpen:                 js.UndefOr[Callback] = js.undefined,
  onMountE:               js.UndefOr[Modal.OnMount] = js.undefined,
  onMount:                js.UndefOr[Callback] = js.undefined,
  open:                   js.UndefOr[Boolean] = js.undefined,
  size:                   js.UndefOr[ModalSize] = js.undefined,
  style:                  js.UndefOr[Style] = js.undefined,
  trigger:                js.UndefOr[VdomNode] = js.undefined,
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
    var cancelButton: js.UndefOr[suiraw.SemanticShorthandItemS[Button.ButtonProps]] = js.native

    /** The OK button text. */
    var confirmButton: js.UndefOr[suiraw.SemanticShorthandItemS[Button.ButtonProps]] = js.native

    /**
     * Called when the Modal is closed without clicking confirm.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props.
     */
    var onCancel: js.UndefOr[RawOnCancel] = js.native

    /**
     * Called when the OK button is clicked.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props.
     */
    var onConfirm: js.UndefOr[RawOnCancel] = js.native

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
    as:                   js.UndefOr[AsC] = js.undefined,
    basic:                js.UndefOr[Boolean] = js.undefined,
    cancelButton:         js.UndefOr[ShorthandS[Button]] = js.undefined,
    centered:             js.UndefOr[Boolean] = js.undefined,
    className:            js.UndefOr[String] = js.undefined,
    clazz:                js.UndefOr[Css] = js.undefined,
    closeIcon:            js.UndefOr[Icon] = js.undefined,
    closeOnDimmerClick:   js.UndefOr[Boolean] = js.undefined,
    closeOnDocumentClick: js.UndefOr[Boolean] = js.undefined,
    confirmButton:        js.UndefOr[ShorthandS[Button]] = js.undefined,
    content:              js.UndefOr[ShorthandS[ModalContent]] = js.undefined,
    defaultOpen:          js.UndefOr[Boolean] = js.undefined,
    dimmer:               js.UndefOr[Dimmer] = js.undefined,
    eventPool:            js.UndefOr[String] = js.undefined,
    header:               js.UndefOr[ShorthandS[ModalHeader]] = js.undefined,
    onActionClickE:       js.UndefOr[Modal.OnActionClick] = js.undefined,
    onActionClick:        js.UndefOr[Callback] = js.undefined,
    onCancelE:            js.UndefOr[Confirm.OnCancel] = js.undefined,
    onCancel:             js.UndefOr[Callback] = js.undefined,
    onCloseE:             js.UndefOr[Modal.OnClose] = js.undefined,
    onClose:              js.UndefOr[Callback] = js.undefined,
    onConfirmE:           js.UndefOr[Confirm.OnConfirm] = js.undefined,
    onConfirm:            js.UndefOr[Callback] = js.undefined,
    onOpenE:              js.UndefOr[Modal.OnOpen] = js.undefined,
    onOpen:               js.UndefOr[Callback] = js.undefined,
    onMountE:             js.UndefOr[Modal.OnMount] = js.undefined,
    onMount:              js.UndefOr[Callback] = js.undefined,
    open:                 js.UndefOr[Boolean] = js.undefined,
    size:                 js.UndefOr[ModalSize] = js.undefined,
    style:                js.UndefOr[Style] = js.undefined,
    trigger:              js.UndefOr[VdomNode] = js.undefined
  ): ConfirmProps = {
    val p = as.toJsObject[ConfirmProps]
    as.toJs.foreach(v => p.as = v)
    basic.foreach(v => p.basic = v)
    cancelButton.toJs.foreach(v => p.cancelButton = v)
    centered.foreach(v => p.centered = v)
    (className, clazz).toJs.foreach(v => p.className = v)
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
