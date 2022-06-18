// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.modules.popup

import scala.scalajs.js
import js.annotation._
import js.|
import js.JSConverters._
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.VdomNode
import react.common._
import react.semanticui._
import react.semanticui.sizes._
import react.semanticui.{raw => suiraw}
import japgolly.scalajs.react.vdom.TagMod

final case class Popup(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  basic:                  MyUndefOr[Boolean] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  content:                MyUndefOr[ShorthandS[PopupContent]] = MyUndefOr.undefined,
  disabled:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  eventsEnabled:          MyUndefOr[Boolean] = MyUndefOr.undefined,
  flowing:                MyUndefOr[Boolean] = MyUndefOr.undefined,
  header:                 MyUndefOr[ShorthandS[PopupHeader]] = MyUndefOr.undefined,
  hideOnScroll:           MyUndefOr[Boolean] = MyUndefOr.undefined,
  hoverable:              MyUndefOr[Boolean] = MyUndefOr.undefined,
  inverted:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  offset:                 MyUndefOr[(Double, Double)] = MyUndefOr.undefined,
  on:                     MyUndefOr[PopupOn | List[PopupOn]] = MyUndefOr.undefined,
  onCloseE:               MyUndefOr[Popup.OnClose] = MyUndefOr.undefined,
  onClose:                MyUndefOr[Callback] = MyUndefOr.undefined,
  onOpenE:                MyUndefOr[Popup.OnOpen] = MyUndefOr.undefined,
  onOpen:                 MyUndefOr[Callback] = MyUndefOr.undefined,
  pinned:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  position:               MyUndefOr[PopupPosition] = MyUndefOr.undefined,
  positionFixed:          MyUndefOr[Boolean] = MyUndefOr.undefined,
  popper:                 MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
  popperModifiers:        MyUndefOr[js.Object] = MyUndefOr.undefined,
  size:                   MyUndefOr[SemanticSize] = MyUndefOr.undefined,
  style:                  MyUndefOr[Style] = MyUndefOr.undefined,
  trigger:                MyUndefOr[VdomNode] = MyUndefOr.undefined,
  wide:                   MyUndefOr[PopupWide] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[Popup.PopupProps, Popup] {
  override protected def cprops                     = Popup.props(this)
  override protected val component                  = Popup.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object Popup {
  // TODO Implement this if needed but it needs a facade for Popper
  // @js.native
  // trait PopperOffsetFunctionParams extends js.Object
  // private type RawPoperOffset = js.Function1[PopperOffsetFunctionParams, js.Array[Double]]
  private type RawOnClose = js.Function2[ReactMouseEvent, PopupProps, Unit]
  type OnClose            = (ReactMouseEvent, PopupProps) => Callback
  private type RawOnOpen  = RawOnClose
  type OnOpen             = OnClose

  @js.native
  @JSImport("semantic-ui-react", "Popup")
  object RawComponent extends js.Object

  @js.native
  trait PopupProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: MyUndefOr[AsT] = js.native

    /** Display the popup without the pointing arrow */
    var basic: MyUndefOr[Boolean] = js.native

    /** Primary content. */
    var children: MyUndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: MyUndefOr[String] = js.native

    /** Simple text content for the popover. */
    var content: MyUndefOr[suiraw.SemanticShorthandItemS[PopupContent.PopupContentProps]] =
      js.native

    /** Existing element the pop-up should be bound to. */
    // context?: object | React.RefObject<HTMLElement>

    /** A disabled popup only renders its trigger. */
    var disabled: MyUndefOr[Boolean] = js.native

    /** Enables the Popper.js event listeners. */
    var eventsEnabled: MyUndefOr[Boolean] = js.native

    /** A flowing Popup has no maximum width and continues to flow to fit its content. */
    var flowing: MyUndefOr[Boolean] = js.native

    /** Header displayed above the content in bold. */
    var header: MyUndefOr[suiraw.SemanticShorthandItemS[PopupHeader.PopupHeaderProps]] = js.native

    /** Hide the Popup when scrolling the window. */
    var hideOnScroll: MyUndefOr[Boolean] = js.native

    /** Whether the popup should not close on hover. */
    var hoverable: MyUndefOr[Boolean] = js.native

    /** Invert the colors of the popup */
    var inverted: MyUndefOr[Boolean] = js.native

    /**
     * Offset values in px unit to apply to rendered popup. The basic offset accepts an array with
     * two numbers in the form [skidding, distance]:
     *   - `skidding` displaces the Popup along the reference element
     *   - `distance` displaces the Popup away from, or toward, the reference element in the
     *     direction of its placement. A positive number displaces it further away, while a negative
     *     number lets it overlap the reference.
     *
     * @see
     *   https://popper.js.org/docs/v2/modifiers/offset/
     */
    var offset: MyUndefOr[js.Array[Double]] = js.native

    /** Events triggering the popup. */
    var on: MyUndefOr[String | js.Array[String]] = js.native

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
    // onMount?: (nothing: null, data: PopupProps) => void

    /**
     * Called when an open event happens.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props.
     */
    var onOpen: MyUndefOr[RawOnOpen] = js.native

    /**
     * Called when the portal is unmounted from the DOM.
     *
     * @param {null}
     * @param {object}
     *   data - All props.
     */
    // onUnmount?: (nothing: null, data: PopupProps) => void

    /**
     * Disables automatic repositioning of the component, it will always be placed according to the
     * position value.
     */
    var pinned: MyUndefOr[Boolean] = js.native

    /** Position for the popover. */
    var position: MyUndefOr[String] = js.native

    /** Tells `Popper.js` to use the `position: fixed` strategy to position the popover. */
    var positionFixed: MyUndefOr[Boolean] = js.native

    /** A wrapping element for an actual content that will be used for positioning. */
    var popper: MyUndefOr[suiraw.SemanticShorthandContent] = js.native

    /** An object containing custom settings for the Popper.js modifiers. */
    var popperModifiers: MyUndefOr[js.Object] = js.native

    /** A popup can have dependencies which update will schedule a position update. */
    // popperDependencies?: any[]

    /** Popup size. */
    var size: MyUndefOr[suiraw.SemanticSIZES] = js.native

    /** Custom Popup style. */
    var style: MyUndefOr[js.Object] = js.native

    /** Element to be rendered in-place where the popup is defined. */
    var trigger: MyUndefOr[React.Node] = js.native

    /** Popup width. */
    var wide: MyUndefOr[Boolean | String] = js.native
  }

  def props(q: Popup): PopupProps =
    rawprops(
      q.as,
      q.basic,
      q.className,
      q.clazz,
      q.content,
      q.disabled,
      q.eventsEnabled,
      q.flowing,
      q.header,
      q.hideOnScroll,
      q.hoverable,
      q.inverted,
      q.offset,
      q.on,
      q.onCloseE,
      q.onClose,
      q.onOpenE,
      q.onOpen,
      q.pinned,
      q.position,
      q.positionFixed,
      q.popper,
      q.popperModifiers,
      q.size,
      q.style,
      q.trigger,
      q.wide
    )

  def rawprops(
    as:              MyUndefOr[AsC] = MyUndefOr.undefined,
    basic:           MyUndefOr[Boolean] = MyUndefOr.undefined,
    className:       MyUndefOr[String] = MyUndefOr.undefined,
    clazz:           MyUndefOr[Css] = MyUndefOr.undefined,
    content:         MyUndefOr[ShorthandS[PopupContent]] = MyUndefOr.undefined,
    disabled:        MyUndefOr[Boolean] = MyUndefOr.undefined,
    eventsEnabled:   MyUndefOr[Boolean] = MyUndefOr.undefined,
    flowing:         MyUndefOr[Boolean] = MyUndefOr.undefined,
    header:          MyUndefOr[ShorthandS[PopupHeader]] = MyUndefOr.undefined,
    hideOnScroll:    MyUndefOr[Boolean] = MyUndefOr.undefined,
    hoverable:       MyUndefOr[Boolean] = MyUndefOr.undefined,
    inverted:        MyUndefOr[Boolean] = MyUndefOr.undefined,
    offset:          MyUndefOr[(Double, Double)] = MyUndefOr.undefined,
    on:              MyUndefOr[PopupOn | List[PopupOn]] = MyUndefOr.undefined,
    onCloseE:        MyUndefOr[OnClose] = MyUndefOr.undefined,
    onClose:         MyUndefOr[Callback] = MyUndefOr.undefined,
    onOpenE:         MyUndefOr[OnOpen] = MyUndefOr.undefined,
    onOpen:          MyUndefOr[Callback] = MyUndefOr.undefined,
    pinned:          MyUndefOr[Boolean] = MyUndefOr.undefined,
    position:        MyUndefOr[PopupPosition] = MyUndefOr.undefined,
    positionFixed:   MyUndefOr[Boolean] = MyUndefOr.undefined,
    popper:          MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
    popperModifiers: MyUndefOr[js.Object] = MyUndefOr.undefined,
    size:            MyUndefOr[SemanticSize] = MyUndefOr.undefined,
    style:           MyUndefOr[Style] = MyUndefOr.undefined,
    trigger:         MyUndefOr[VdomNode] = MyUndefOr.undefined,
    wide:            MyUndefOr[PopupWide] = MyUndefOr.undefined
  ): PopupProps = {
    val p = as.toJsObject[PopupProps]
    as.toJs.foreach(v => p.as = v)
    basic.foreach(v => p.basic = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    content.toJs.foreach(v => p.content = v)
    disabled.foreach(v => p.disabled = v)
    eventsEnabled.foreach(v => p.eventsEnabled = v)
    flowing.foreach(v => p.flowing = v)
    header.toJs.foreach(v => p.header = v)
    hideOnScroll.foreach(v => p.hideOnScroll = v)
    hoverable.foreach(v => p.hoverable = v)
    inverted.foreach(v => p.inverted = v)
    offset.foreach(v => p.offset = js.Array(v._1, v._2))
    on.map[String | js.Array[String]] { x =>
      (x: Any) match {
        case p: PopupOn => p.toJs
        case p          => p.asInstanceOf[List[PopupOn]].map(_.toJs).toJSArray
      }
    }.foreach(v => p.on = v)
    (onCloseE, onClose).toJs.foreach(v => p.onClose = v)
    (onOpenE, onOpen).toJs.foreach(v => p.onOpen = v)
    pinned.foreach(v => p.pinned = v)
    position.toJs.foreach(v => p.position = v)
    positionFixed.foreach(v => p.positionFixed = v)
    popper.toJs.foreach(v => p.popper = v)
    popperModifiers.foreach(v => p.popperModifiers = v)
    size.toJs.foreach(v => p.size = v)
    style.map(_.toJsObject).foreach(v => p.style = v)
    trigger.toJs.foreach(v => p.trigger = v)
    wide.toJs.foreach(v => p.wide = v)
    p
  }

  private val component =
    JsComponent[PopupProps, Children.Varargs, Null](RawComponent)

  def apply(modifiers: TagMod*): Popup =
    new Popup(modifiers = modifiers)
}
