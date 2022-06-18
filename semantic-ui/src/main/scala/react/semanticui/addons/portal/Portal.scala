// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.addons.portal

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.VdomNode
import japgolly.scalajs.react.facade.React
import react.common._
import japgolly.scalajs.react.vdom.TagMod

final case class Portal(
  closeOnDocumentClick:     MyUndefOr[Boolean] = MyUndefOr.undefined,
  closeOnEscape:            MyUndefOr[Boolean] = MyUndefOr.undefined,
  closeOnPortalMouseLeave:  MyUndefOr[Boolean] = MyUndefOr.undefined,
  closeOnTriggerBlur:       MyUndefOr[Boolean] = MyUndefOr.undefined,
  closeOnTriggerClick:      MyUndefOr[Boolean] = MyUndefOr.undefined,
  closeOnTriggerMouseLeave: MyUndefOr[Boolean] = MyUndefOr.undefined,
  defaultOpen:              MyUndefOr[Boolean] = MyUndefOr.undefined,
  eventPool:                MyUndefOr[String] = MyUndefOr.undefined,
  mouseEnterDelay:          MyUndefOr[Double] = MyUndefOr.undefined,
  mouseLeaveDelay:          MyUndefOr[Double] = MyUndefOr.undefined,
  onCloseE:                 MyUndefOr[Portal.OnClose] = MyUndefOr.undefined,
  onClose:                  MyUndefOr[Callback] = MyUndefOr.undefined,
  onMountE:                 MyUndefOr[Portal.OnMount] = MyUndefOr.undefined,
  onMount:                  MyUndefOr[Callback] = MyUndefOr.undefined,
  onOpenE:                  MyUndefOr[Portal.OnOpen] = MyUndefOr.undefined,
  onOpen:                   MyUndefOr[Callback] = MyUndefOr.undefined,
  onUnmountE:               MyUndefOr[Portal.OnUnmount] = MyUndefOr.undefined,
  onUnmount:                MyUndefOr[Callback] = MyUndefOr.undefined,
  open:                     MyUndefOr[Boolean] = MyUndefOr.undefined,
  openOnTriggerClick:       MyUndefOr[Boolean] = MyUndefOr.undefined,
  openOnTriggerFocus:       MyUndefOr[Boolean] = MyUndefOr.undefined,
  openOnTriggerMouseEnter:  MyUndefOr[Boolean] = MyUndefOr.undefined,
  trigger:                  MyUndefOr[VdomNode] = MyUndefOr.undefined,
  override val modifiers:   Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[Portal.PortalProps, Portal] {
  override protected def cprops                     = Portal.props(this)
  override protected val component                  = Portal.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object Portal {
  type RawOnClose   = js.Function2[ReactMouseEvent, Portal.PortalProps, Unit]
  type OnClose      = (ReactMouseEvent, Portal.PortalProps) => Callback
  type RawOnOpen    = js.Function2[ReactMouseEvent, Portal.PortalProps, Unit]
  type OnOpen       = (ReactMouseEvent, Portal.PortalProps) => Callback
  type RawOnMount   = js.Function2[Any, Portal.PortalProps, Unit]
  type OnMount      = (Any, Portal.PortalProps) => Callback
  type RawOnUnmount = js.Function2[Any, Portal.PortalProps, Unit]
  type OnUnmount    = (Any, Portal.PortalProps) => Callback

  @js.native
  @JSImport("semantic-ui-react", "Portal")
  object RawComponent extends js.Object

  @js.native
  trait PortalProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** Primary content. */
    var children: MyUndefOr[React.Node] = js.native

    /** Controls whether or not the portal should close on a click outside. */
    var closeOnDocumentClick: MyUndefOr[Boolean] = MyUndefOr.undefined

    /** Controls whether or not the portal should close when escape is pressed is displayed. */
    var closeOnEscape: MyUndefOr[Boolean] = MyUndefOr.undefined

    /**
     * Controls whether or not the portal should close when mousing out of the portal. NOTE: This
     * will prevent `closeOnTriggerMouseLeave` when mousing over the gap from the trigger to the
     * portal.
     */
    var closeOnPortalMouseLeave: MyUndefOr[Boolean] = MyUndefOr.undefined

    /** Controls whether or not the portal should close on blur of the trigger. */
    var closeOnTriggerBlur: MyUndefOr[Boolean] = MyUndefOr.undefined

    /** Controls whether or not the portal should close on click of the trigger. */
    var closeOnTriggerClick: MyUndefOr[Boolean] = MyUndefOr.undefined

    /** Controls whether or not the portal should close when mousing out of the trigger. */
    var closeOnTriggerMouseLeave: MyUndefOr[Boolean] = MyUndefOr.undefined

    /** Initial value of open. */
    var defaultOpen: MyUndefOr[Boolean] = MyUndefOr.undefined

    /** Event pool namespace that is used to handle component events. */
    var eventPool: MyUndefOr[String] = MyUndefOr.undefined

    /** The node where the portal should mount. */
    var mountNode: MyUndefOr[Any] = MyUndefOr.undefined

    /** Milliseconds to wait before opening on mouse over */
    var mouseEnterDelay: MyUndefOr[Double] = MyUndefOr.undefined

    /** Milliseconds to wait before closing on mouse leave */
    var mouseLeaveDelay: MyUndefOr[Double] = MyUndefOr.undefined

    /**
     * Called when a close event happens
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props.
     */
    var onClose: MyUndefOr[RawOnClose] = MyUndefOr.undefined

    /**
     * Called when the portal is mounted on the DOM
     *
     * @param {null}
     * @param {object}
     *   data - All props.
     */
    var onMount: MyUndefOr[RawOnMount] = js.native

    /**
     * Called when an open event happens
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props.
     */
    var onOpen: MyUndefOr[RawOnOpen] = MyUndefOr.undefined

    /**
     * Called when the portal is unmounted from the DOM
     *
     * @param {null}
     * @param {object}
     *   data - All props.
     */
    var onUnmount: MyUndefOr[RawOnUnmount]

    /** Controls whether or not the portal is displayed. */
    var open: MyUndefOr[Boolean] = MyUndefOr.undefined

    /** Controls whether or not the portal should open when the trigger is clicked. */
    var openOnTriggerClick: MyUndefOr[Boolean] = MyUndefOr.undefined

    /** Controls whether or not the portal should open on focus of the trigger. */
    var openOnTriggerFocus: MyUndefOr[Boolean] = MyUndefOr.undefined

    /** Controls whether or not the portal should open when mousing over the trigger. */
    var openOnTriggerMouseEnter: MyUndefOr[Boolean] = MyUndefOr.undefined

    /** Element to be rendered in-place where the portal is defined. */
    var trigger: MyUndefOr[React.Node] = MyUndefOr.undefined

    // /** Called with a ref to the trigger node. */
    // var triggerRef: MyUndefOr[Ref]
  }

  def props(q: Portal): PortalProps =
    rawprops(
      q.closeOnDocumentClick,
      q.closeOnEscape,
      q.closeOnPortalMouseLeave,
      q.closeOnTriggerBlur,
      q.closeOnTriggerClick,
      q.closeOnTriggerMouseLeave,
      q.defaultOpen,
      q.eventPool,
      q.mouseEnterDelay,
      q.mouseLeaveDelay,
      q.onCloseE,
      q.onClose,
      q.onMountE,
      q.onMount,
      q.onOpenE,
      q.onOpen,
      q.onUnmountE,
      q.onUnmount,
      q.open,
      q.openOnTriggerClick,
      q.openOnTriggerFocus,
      q.openOnTriggerMouseEnter,
      q.trigger
    )

  def rawprops(
    closeOnDocumentClick:     MyUndefOr[Boolean] = MyUndefOr.undefined,
    closeOnEscape:            MyUndefOr[Boolean] = MyUndefOr.undefined,
    closeOnPortalMouseLeave:  MyUndefOr[Boolean] = MyUndefOr.undefined,
    closeOnTriggerBlur:       MyUndefOr[Boolean] = MyUndefOr.undefined,
    closeOnTriggerClick:      MyUndefOr[Boolean] = MyUndefOr.undefined,
    closeOnTriggerMouseLeave: MyUndefOr[Boolean] = MyUndefOr.undefined,
    defaultOpen:              MyUndefOr[Boolean] = MyUndefOr.undefined,
    eventPool:                MyUndefOr[String] = MyUndefOr.undefined,
    mouseEnterDelay:          MyUndefOr[Double] = MyUndefOr.undefined,
    mouseLeaveDelay:          MyUndefOr[Double] = MyUndefOr.undefined,
    onCloseE:                 MyUndefOr[Portal.OnClose] = MyUndefOr.undefined,
    onClose:                  MyUndefOr[Callback] = MyUndefOr.undefined,
    onMountE:                 MyUndefOr[Portal.OnMount] = MyUndefOr.undefined,
    onMount:                  MyUndefOr[Callback] = MyUndefOr.undefined,
    onOpenE:                  MyUndefOr[Portal.OnOpen] = MyUndefOr.undefined,
    onOpen:                   MyUndefOr[Callback] = MyUndefOr.undefined,
    onUnmountE:               MyUndefOr[Portal.OnUnmount] = MyUndefOr.undefined,
    onUnmount:                MyUndefOr[Callback] = MyUndefOr.undefined,
    open:                     MyUndefOr[Boolean] = MyUndefOr.undefined,
    openOnTriggerClick:       MyUndefOr[Boolean] = MyUndefOr.undefined,
    openOnTriggerFocus:       MyUndefOr[Boolean] = MyUndefOr.undefined,
    openOnTriggerMouseEnter:  MyUndefOr[Boolean] = MyUndefOr.undefined,
    trigger:                  MyUndefOr[VdomNode] = MyUndefOr.undefined
  ): PortalProps = {
    val p = (new js.Object).asInstanceOf[PortalProps]
    closeOnDocumentClick.foreach(v => p.closeOnDocumentClick = v)
    closeOnEscape.foreach(v => p.closeOnEscape = v)
    closeOnPortalMouseLeave.foreach(v => p.closeOnPortalMouseLeave = v)
    closeOnTriggerBlur.foreach(v => p.closeOnTriggerBlur = v)
    closeOnTriggerClick.foreach(v => p.closeOnTriggerClick = v)
    closeOnTriggerMouseLeave.foreach(v => p.closeOnTriggerMouseLeave = v)
    defaultOpen.foreach(v => p.defaultOpen = v)
    eventPool.foreach(v => p.eventPool = v)
    mouseEnterDelay.foreach(v => p.mouseEnterDelay = v)
    mouseLeaveDelay.foreach(v => p.mouseLeaveDelay = v)
    (onCloseE, onClose).toJs.foreach(v => p.onClose = v)
    (onMountE, onMount).toJs.foreach(v => p.onMount = v)
    (onOpenE, onOpen).toJs.foreach(v => p.onOpen = v)
    (onUnmountE, onUnmount).toJs.foreach(v => p.onUnmount = v)
    open.foreach(v => p.open = v)
    openOnTriggerClick.foreach(v => p.openOnTriggerClick = v)
    openOnTriggerFocus.foreach(v => p.openOnTriggerFocus = v)
    openOnTriggerMouseEnter.foreach(v => p.openOnTriggerMouseEnter = v)
    trigger.toJs.foreach(v => p.trigger = v)
    p
  }

  private val component =
    JsComponent[PortalProps, Children.Varargs, Null](RawComponent)

  def apply(modifiers: TagMod*): Portal =
    new Portal(modifiers = modifiers)
}
