package react.semanticui.modules.modal

import scala.scalajs.js
import js.annotation._
import js.|
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.VdomNode
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.TagMod
import react.common._
import react.semanticui._
import react.semanticui.elements.icon.Icon
import react.semanticui.elements.icon.Icon.IconProps
import react.semanticui.{ raw => suiraw }
import react.semanticui.elements.button.Button

final case class Modal(
  as:                     js.UndefOr[AsC] = js.undefined,
  actions:                js.UndefOr[Seq[ShorthandS[Button]]] = js.undefined,
  basic:                  js.UndefOr[Boolean] = js.undefined,
  centered:               js.UndefOr[Boolean] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  closeIcon:              js.UndefOr[ShorthandS[Icon]] = js.undefined,
  closeOnDimmerClick:     js.UndefOr[Boolean] = js.undefined,
  closeOnDocumentClick:   js.UndefOr[Boolean] = js.undefined,
  closeOnEscape:          js.UndefOr[Boolean] = js.undefined,
  content:                js.UndefOr[ShorthandS[ModalContent]] = js.undefined,
  defaultOpen:            js.UndefOr[Boolean] = js.undefined,
  dimmer:                 js.UndefOr[Dimmer | ModalDimmer] = js.undefined,
  eventPool:              js.UndefOr[String] = js.undefined,
  header:                 js.UndefOr[ShorthandS[ModalHeader]] = js.undefined,
  onActionClickE:         js.UndefOr[Modal.OnActionClick] = js.undefined,
  onActionClick:          js.UndefOr[Callback] = js.undefined,
  onCloseE:               js.UndefOr[Modal.OnClose] = js.undefined,
  onClose:                js.UndefOr[Callback] = js.undefined,
  onOpenE:                js.UndefOr[Modal.OnOpen] = js.undefined,
  onOpen:                 js.UndefOr[Callback] = js.undefined,
  onMountE:               js.UndefOr[Modal.OnMount] = js.undefined,
  onMount:                js.UndefOr[Callback] = js.undefined,
  open:                   js.UndefOr[Boolean] = js.undefined,
  size:                   js.UndefOr[ModalSize] = js.undefined,
  style:                  js.UndefOr[Style] = js.undefined,
  trigger:                js.UndefOr[VdomNode] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[Modal.ModalProps, Modal] {
  override protected def cprops                     = Modal.props(this)
  override protected val component                  = Modal.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object Modal {
  type RawOnActionClick = js.Function2[ReactMouseEvent, Modal.ModalProps, Unit]
  type OnActionClick    = (ReactMouseEvent, Modal.ModalProps) => Callback
  type RawOnClose       = js.Function2[ReactMouseEvent, Modal.ModalProps, Unit]
  type OnClose          = (ReactMouseEvent, Modal.ModalProps) => Callback
  type RawOnOpen        = js.Function2[ReactMouseEvent, Modal.ModalProps, Unit]
  type OnOpen           = (ReactMouseEvent, Modal.ModalProps) => Callback
  type RawOnMount       = js.Function2[Unit, Modal.ModalProps, Unit]
  type OnMount          = Modal.ModalProps => Callback
  type RawOnUnmount     = js.Function2[Unit, Modal.ModalProps, Unit]
  type OnUnmount        = Modal.ModalProps => Callback

  @js.native
  @JSImport("semantic-ui-react", "Modal")
  object RawComponent extends js.Object

  @js.native
  trait ModalProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: js.UndefOr[AsT] = js.native

    /** Shorthand for Modal.Actions. Typically an array of button shorthand. */
    var actions: js.UndefOr[suiraw.SemanticShorthandArray[Button.ButtonProps]] =
      js.native

    /** A Modal can reduce its complexity */
    var basic: js.UndefOr[Boolean] = js.native

    /** A modal can be vertically centered in the viewport */
    var centered: js.UndefOr[Boolean] = js.native

    /** Primary content. */
    var children: js.UndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: js.UndefOr[String] = js.native

    /** Icon. */
    var closeIcon: js.UndefOr[suiraw.SemanticShorthandItemS[IconProps]]

    /** Whether or not the Modal should close when the dimmer is clicked. */
    var closeOnDimmerClick: js.UndefOr[Boolean] = js.native

    /** Whether or not the Modal should close when the document is clicked. */
    var closeOnDocumentClick: js.UndefOr[Boolean] = js.native

    /** Whether or not the Modal should close when escape is pressed. */
    var closeOnEscape: js.UndefOr[Boolean] = js.native

    /** A Modal can be passed content via shorthand. */
    var content: js.UndefOr[suiraw.SemanticShorthandItemS[ModalContent.ModalContentProps]] =
      js.native

    /** Initial value of open. */
    var defaultOpen: js.UndefOr[Boolean] = js.native

    /** A modal can appear in a dimmer. */
    var dimmer
      : js.UndefOr[Boolean | String | suiraw.SemanticShorthandItemS[ModalDimmer.ModalDimmerProps]] =
      js.native

    /** Event pool namespace that is used to handle component events */
    var eventPool: js.UndefOr[String] = js.native

    /** A Modal can be passed header via shorthand. */
    var header: js.UndefOr[suiraw.SemanticShorthandItemS[ModalHeader.ModalHeaderProps]] = js.native

    /** The node where the modal should mount. Defaults to document.body. */
    // mountNode?: any

    /**
     * Action onClick handler when using shorthand `actions`.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props.
     */
    var onActionClick: js.UndefOr[RawOnActionClick] = js.native

    /**
     * Called when a close event happens.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props.
     */
    var onClose: js.UndefOr[RawOnClose] = js.native

    /**
     * Called when the portal is mounted on the DOM.
     *
     * @param {null}
     * @param {object}
     *   data - All props.
     */
    var onMount: js.UndefOr[RawOnMount] = js.native

    /**
     * Called when an open event happens.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props.
     */
    var onOpen: js.UndefOr[RawOnClose] = js.native

    /**
     * Called when the portal is unmounted from the DOM.
     *
     * @param {null}
     * @param {object}
     *   data - All props.
     */
    var onUnmount: js.UndefOr[RawOnUnmount] = js.native

    /** Controls whether or not the Modal is displayed. */
    var open: js.UndefOr[Boolean] = js.native

    /** A modal can vary in size. */
    // size?: 'mini' | 'tiny' | 'small' | 'large' | 'fullscreen'
    var size: js.UndefOr[String] = js.native

    /** Custom styles. */
    var style: js.UndefOr[js.Object] = js.native

    /** Element to be rendered in-place where the portal is defined. */
    var trigger: js.UndefOr[React.Node] = js.native
  }

  def props(q: Modal): ModalProps =
    rawprops(
      q.as,
      q.actions,
      q.basic,
      q.centered,
      q.className,
      q.clazz,
      q.closeIcon,
      q.closeOnDimmerClick,
      q.closeOnDocumentClick,
      q.closeOnEscape,
      q.content,
      q.defaultOpen,
      q.dimmer,
      q.eventPool,
      q.header,
      q.onActionClickE,
      q.onActionClick,
      q.onCloseE,
      q.onClose,
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
    actions:              js.UndefOr[Seq[ShorthandS[Button]]] = js.undefined,
    basic:                js.UndefOr[Boolean] = js.undefined,
    centered:             js.UndefOr[Boolean] = js.undefined,
    className:            js.UndefOr[String] = js.undefined,
    clazz:                js.UndefOr[Css] = js.undefined,
    closeIcon:            js.UndefOr[ShorthandS[Icon]] = js.undefined,
    closeOnDimmerClick:   js.UndefOr[Boolean] = js.undefined,
    closeOnDocumentClick: js.UndefOr[Boolean] = js.undefined,
    closeOnEscape:        js.UndefOr[Boolean] = js.undefined,
    content:              js.UndefOr[ShorthandS[ModalContent]] = js.undefined,
    defaultOpen:          js.UndefOr[Boolean] = js.undefined,
    dimmer:               js.UndefOr[Dimmer | ModalDimmer] = js.undefined,
    eventPool:            js.UndefOr[String] = js.undefined,
    header:               js.UndefOr[ShorthandS[ModalHeader]] = js.undefined,
    onActionClickE:       js.UndefOr[OnActionClick] = js.undefined,
    onActionClick:        js.UndefOr[Callback] = js.undefined,
    onCloseE:             js.UndefOr[OnClose] = js.undefined,
    onClose:              js.UndefOr[Callback] = js.undefined,
    onOpenE:              js.UndefOr[OnOpen] = js.undefined,
    onOpen:               js.UndefOr[Callback] = js.undefined,
    onMountE:             js.UndefOr[OnMount] = js.undefined,
    onMount:              js.UndefOr[Callback] = js.undefined,
    open:                 js.UndefOr[Boolean] = js.undefined,
    size:                 js.UndefOr[ModalSize] = js.undefined,
    style:                js.UndefOr[Style] = js.undefined,
    trigger:              js.UndefOr[VdomNode] = js.undefined
  ): ModalProps = {
    val p = as.toJsObject[ModalProps]
    as.toJs.foreach(v => p.as = v)
    actions.toJs.foreach(v => p.actions = v)
    basic.foreach(v => p.basic = v)
    centered.foreach(v => p.centered = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    closeIcon.toJs.foreach(v => p.closeIcon = v)
    closeOnDimmerClick.foreach(v => p.closeOnDimmerClick = v)
    closeOnDocumentClick.foreach(v => p.closeOnDocumentClick = v)
    closeOnEscape.foreach(v => p.closeOnEscape = v)
    content.toJs.foreach(v => p.content = v)
    defaultOpen.foreach(v => p.defaultOpen = v)
    dimmer.foreach { v =>
      (v: Any) match {
        case x: Dimmer => x.toJs
        case x         => x
      }
    }

    eventPool.foreach(v => p.eventPool = v)
    header.toJs.foreach(v => p.header = v)
    (onActionClickE, onActionClick).toJs.foreach(v => p.onActionClick = v)
    (onCloseE, onClose).toJs.foreach(v => p.onClose = v)
    (onMountE, onMount).toJs
      .map[RawOnMount](f => (_, p: Modal.ModalProps) => f(p))
      .foreach(v => p.onMount = v)
    open.foreach(v => p.open = v)
    size.toJs.foreach(v => p.size = v)
    style.map(_.toJsObject).foreach(v => p.style = v)
    trigger.toJs.foreach(v => p.trigger = v)
    p
  }

  private val component =
    JsComponent[ModalProps, Children.Varargs, Null](RawComponent)

  def apply(modifiers: TagMod*): Modal =
    new Modal(modifiers = modifiers)
}
