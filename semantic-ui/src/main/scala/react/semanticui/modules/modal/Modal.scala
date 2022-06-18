// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

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
import react.semanticui.{raw => suiraw}
import react.semanticui.elements.button.Button

final case class Modal(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  actions:                MyUndefOr[Seq[ShorthandS[Button]]] = MyUndefOr.undefined,
  basic:                  MyUndefOr[Boolean] = MyUndefOr.undefined,
  centered:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  closeIcon:              MyUndefOr[ShorthandS[Icon]] = MyUndefOr.undefined,
  closeOnDimmerClick:     MyUndefOr[Boolean] = MyUndefOr.undefined,
  closeOnDocumentClick:   MyUndefOr[Boolean] = MyUndefOr.undefined,
  closeOnEscape:          MyUndefOr[Boolean] = MyUndefOr.undefined,
  content:                MyUndefOr[ShorthandS[ModalContent]] = MyUndefOr.undefined,
  defaultOpen:            MyUndefOr[Boolean] = MyUndefOr.undefined,
  dimmer:                 MyUndefOr[Dimmer | ModalDimmer] = MyUndefOr.undefined,
  eventPool:              MyUndefOr[String] = MyUndefOr.undefined,
  header:                 MyUndefOr[ShorthandS[ModalHeader]] = MyUndefOr.undefined,
  onActionClickE:         MyUndefOr[Modal.OnActionClick] = MyUndefOr.undefined,
  onActionClick:          MyUndefOr[Callback] = MyUndefOr.undefined,
  onCloseE:               MyUndefOr[Modal.OnClose] = MyUndefOr.undefined,
  onClose:                MyUndefOr[Callback] = MyUndefOr.undefined,
  onOpenE:                MyUndefOr[Modal.OnOpen] = MyUndefOr.undefined,
  onOpen:                 MyUndefOr[Callback] = MyUndefOr.undefined,
  onMountE:               MyUndefOr[Modal.OnMount] = MyUndefOr.undefined,
  onMount:                MyUndefOr[Callback] = MyUndefOr.undefined,
  open:                   MyUndefOr[Boolean] = MyUndefOr.undefined,
  size:                   MyUndefOr[ModalSize] = MyUndefOr.undefined,
  style:                  MyUndefOr[Style] = MyUndefOr.undefined,
  trigger:                MyUndefOr[VdomNode] = MyUndefOr.undefined,
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
    var as: MyUndefOr[AsT] = js.native

    /** Shorthand for Modal.Actions. Typically an array of button shorthand. */
    var actions: MyUndefOr[suiraw.SemanticShorthandArray[Button.ButtonProps]] =
      js.native

    /** A Modal can reduce its complexity */
    var basic: MyUndefOr[Boolean] = js.native

    /** A modal can be vertically centered in the viewport */
    var centered: MyUndefOr[Boolean] = js.native

    /** Primary content. */
    var children: MyUndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: MyUndefOr[String] = js.native

    /** Icon. */
    var closeIcon: MyUndefOr[suiraw.SemanticShorthandItemS[IconProps]]

    /** Whether or not the Modal should close when the dimmer is clicked. */
    var closeOnDimmerClick: MyUndefOr[Boolean] = js.native

    /** Whether or not the Modal should close when the document is clicked. */
    var closeOnDocumentClick: MyUndefOr[Boolean] = js.native

    /** Whether or not the Modal should close when escape is pressed. */
    var closeOnEscape: MyUndefOr[Boolean] = js.native

    /** A Modal can be passed content via shorthand. */
    var content: MyUndefOr[suiraw.SemanticShorthandItemS[ModalContent.ModalContentProps]] =
      js.native

    /** Initial value of open. */
    var defaultOpen: MyUndefOr[Boolean] = js.native

    /** A modal can appear in a dimmer. */
    var dimmer
      : MyUndefOr[Boolean | String | suiraw.SemanticShorthandItemS[ModalDimmer.ModalDimmerProps]] =
      js.native

    /** Event pool namespace that is used to handle component events */
    var eventPool: MyUndefOr[String] = js.native

    /** A Modal can be passed header via shorthand. */
    var header: MyUndefOr[suiraw.SemanticShorthandItemS[ModalHeader.ModalHeaderProps]] = js.native

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
    var onActionClick: MyUndefOr[RawOnActionClick] = js.native

    /**
     * Called when a close event happens.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props.
     */
    var onClose: MyUndefOr[RawOnClose] = js.native

    /**
     * Called when the portal is mounted on the DOM.
     *
     * @param {null}
     * @param {object}
     *   data - All props.
     */
    var onMount: MyUndefOr[RawOnMount] = js.native

    /**
     * Called when an open event happens.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props.
     */
    var onOpen: MyUndefOr[RawOnClose] = js.native

    /**
     * Called when the portal is unmounted from the DOM.
     *
     * @param {null}
     * @param {object}
     *   data - All props.
     */
    var onUnmount: MyUndefOr[RawOnUnmount] = js.native

    /** Controls whether or not the Modal is displayed. */
    var open: MyUndefOr[Boolean] = js.native

    /** A modal can vary in size. */
    // size?: 'mini' | 'tiny' | 'small' | 'large' | 'fullscreen'
    var size: MyUndefOr[String] = js.native

    /** Custom styles. */
    var style: MyUndefOr[js.Object] = js.native

    /** Element to be rendered in-place where the portal is defined. */
    var trigger: MyUndefOr[React.Node] = js.native
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
    as:                   MyUndefOr[AsC] = MyUndefOr.undefined,
    actions:              MyUndefOr[Seq[ShorthandS[Button]]] = MyUndefOr.undefined,
    basic:                MyUndefOr[Boolean] = MyUndefOr.undefined,
    centered:             MyUndefOr[Boolean] = MyUndefOr.undefined,
    className:            MyUndefOr[String] = MyUndefOr.undefined,
    clazz:                MyUndefOr[Css] = MyUndefOr.undefined,
    closeIcon:            MyUndefOr[ShorthandS[Icon]] = MyUndefOr.undefined,
    closeOnDimmerClick:   MyUndefOr[Boolean] = MyUndefOr.undefined,
    closeOnDocumentClick: MyUndefOr[Boolean] = MyUndefOr.undefined,
    closeOnEscape:        MyUndefOr[Boolean] = MyUndefOr.undefined,
    content:              MyUndefOr[ShorthandS[ModalContent]] = MyUndefOr.undefined,
    defaultOpen:          MyUndefOr[Boolean] = MyUndefOr.undefined,
    dimmer:               MyUndefOr[Dimmer | ModalDimmer] = MyUndefOr.undefined,
    eventPool:            MyUndefOr[String] = MyUndefOr.undefined,
    header:               MyUndefOr[ShorthandS[ModalHeader]] = MyUndefOr.undefined,
    onActionClickE:       MyUndefOr[OnActionClick] = MyUndefOr.undefined,
    onActionClick:        MyUndefOr[Callback] = MyUndefOr.undefined,
    onCloseE:             MyUndefOr[OnClose] = MyUndefOr.undefined,
    onClose:              MyUndefOr[Callback] = MyUndefOr.undefined,
    onOpenE:              MyUndefOr[OnOpen] = MyUndefOr.undefined,
    onOpen:               MyUndefOr[Callback] = MyUndefOr.undefined,
    onMountE:             MyUndefOr[OnMount] = MyUndefOr.undefined,
    onMount:              MyUndefOr[Callback] = MyUndefOr.undefined,
    open:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
    size:                 MyUndefOr[ModalSize] = MyUndefOr.undefined,
    style:                MyUndefOr[Style] = MyUndefOr.undefined,
    trigger:              MyUndefOr[VdomNode] = MyUndefOr.undefined
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
