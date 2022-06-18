// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.addons.radio

import scala.scalajs.js
import js.annotation._
import js.|
import japgolly.scalajs.react._
import react.common._
import react.semanticui._
import react.semanticui.modules.checkbox._
import japgolly.scalajs.react.vdom.TagMod

final case class Radio(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  checked:                MyUndefOr[Boolean] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  defaultChecked:         MyUndefOr[Boolean] = MyUndefOr.undefined,
  defaultIndeterminate:   MyUndefOr[Boolean] = MyUndefOr.undefined,
  disabled:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  fitted:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  id:                     MyUndefOr[Double | String] = MyUndefOr.undefined,
  indeterminate:          MyUndefOr[Boolean] = MyUndefOr.undefined,
  label:                  MyUndefOr[String] = MyUndefOr.undefined,
  name:                   MyUndefOr[String] = MyUndefOr.undefined,
  onChangeE:              MyUndefOr[Radio.Event] = MyUndefOr.undefined,
  onChange:               MyUndefOr[Callback] = MyUndefOr.undefined,
  onClickE:               MyUndefOr[Radio.Event] = MyUndefOr.undefined,
  onClick:                MyUndefOr[Callback] = MyUndefOr.undefined,
  onMouseDownE:           MyUndefOr[Radio.Event] = MyUndefOr.undefined,
  onMouseDown:            MyUndefOr[Callback] = MyUndefOr.undefined,
  onMouseUpE:             MyUndefOr[Radio.Event] = MyUndefOr.undefined,
  onMouseUp:              MyUndefOr[Callback] = MyUndefOr.undefined,
  radio:                  MyUndefOr[Boolean] = MyUndefOr.undefined,
  readOnly:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  slider:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  tabIndex:               MyUndefOr[Double | String] = MyUndefOr.undefined,
  toggle:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  tpe:                    MyUndefOr[CheckboxType] = CheckboxType.Radio,
  value:                  MyUndefOr[String | Double] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPA[Radio.RadioProps, Radio] {
  override protected def cprops                     = Radio.props(this)
  override protected val component                  = Radio.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object Radio {
  type Event            = (ReactMouseEvent, RadioProps) => Callback
  private type RawEvent = js.Function2[ReactMouseEvent, RadioProps, Unit]

  @js.native
  @JSImport("semantic-ui-react", "Radio")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait RadioProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: MyUndefOr[AsT] = js.native

    /** Whether or not checkbox is checked. */
    var checked: MyUndefOr[Boolean] = js.native

    /** Additional classes. */
    var className: MyUndefOr[String] = js.native

    /** The initial value of checked. */
    var defaultChecked: MyUndefOr[Boolean] = js.native

    /** Whether or not checkbox is indeterminate. */
    var defaultIndeterminate: MyUndefOr[Boolean] = js.native

    /** A checkbox can appear disabled and be unable to change states */
    var disabled: MyUndefOr[Boolean] = js.native

    /** Removes padding for a label. Auto applied when there is no label. */
    var fitted: MyUndefOr[Boolean] = js.native

    /** A unique identifier. */
    var id: MyUndefOr[Double | String] = js.native

    /** Whether or not checkbox is indeterminate. */
    var indeterminate: MyUndefOr[Boolean] = js.native

    /** The text of the associated label element. */
    var label: MyUndefOr[String] = js.native

    /** The HTML input name. */
    var name: MyUndefOr[String] = js.native

    /**
     * Called when the user attempts to change the checked state.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props and proposed checked/indeterminate state.
     */
    var onChange: MyUndefOr[RawEvent] = js.native

    /**
     * Called when the checkbox or label is clicked.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props and current checked/indeterminate state.
     */
    var onClick: MyUndefOr[RawEvent] = js.native

    /**
     * Called when the user presses down on the mouse.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props and current checked/indeterminate state.
     */
    var onMouseDown: MyUndefOr[RawEvent] = js.native

    /**
     * Called when the user releases the mouse.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props and current checked/indeterminate state.
     */
    var onMouseUp: MyUndefOr[RawEvent] = js.native

    /** Format as a radio element. This means it is an exclusive option. */
    var radio: MyUndefOr[Boolean] = js.native

    /** A checkbox can be read-only and unable to change states. */
    var readOnly: MyUndefOr[Boolean] = js.native

    /** Format to emphasize the current selection state. */
    var slider: MyUndefOr[Boolean] = js.native

    /** A checkbox can receive focus. */
    var tabIndex: MyUndefOr[Double | String] = js.native

    /** Format to show an on or off choice. */
    var toggle: MyUndefOr[Boolean] = js.native

    /** HTML input type, either checkbox or radio. */
    var `type`: MyUndefOr[String] = js.native

    /** The HTML input value. */
    var value: MyUndefOr[String | Double] = js.native
  }

  def props(q: Radio): RadioProps =
    rawprops(
      q.as,
      q.checked,
      q.className,
      q.clazz,
      q.defaultChecked,
      q.defaultIndeterminate,
      q.disabled,
      q.fitted,
      q.id,
      q.indeterminate,
      q.label,
      q.name,
      q.onChangeE,
      q.onChange,
      q.onClickE,
      q.onClick,
      q.onMouseDownE,
      q.onMouseDown,
      q.onMouseUpE,
      q.onMouseUp,
      q.radio,
      q.readOnly,
      q.slider,
      q.tabIndex,
      q.toggle,
      q.tpe,
      q.value
    )

  def rawprops(
    as:                   MyUndefOr[AsC] = MyUndefOr.undefined,
    checked:              MyUndefOr[Boolean] = MyUndefOr.undefined,
    className:            MyUndefOr[String] = MyUndefOr.undefined,
    clazz:                MyUndefOr[Css] = MyUndefOr.undefined,
    defaultChecked:       MyUndefOr[Boolean] = MyUndefOr.undefined,
    defaultIndeterminate: MyUndefOr[Boolean] = MyUndefOr.undefined,
    disabled:             MyUndefOr[Boolean] = MyUndefOr.undefined,
    fitted:               MyUndefOr[Boolean] = MyUndefOr.undefined,
    id:                   MyUndefOr[Double | String] = MyUndefOr.undefined,
    indeterminate:        MyUndefOr[Boolean] = MyUndefOr.undefined,
    label:                MyUndefOr[String] = MyUndefOr.undefined,
    name:                 MyUndefOr[String] = MyUndefOr.undefined,
    onChangeE:            MyUndefOr[Event] = MyUndefOr.undefined,
    onChange:             MyUndefOr[Callback] = MyUndefOr.undefined,
    onClickE:             MyUndefOr[Event] = MyUndefOr.undefined,
    onClick:              MyUndefOr[Callback] = MyUndefOr.undefined,
    onMouseDownE:         MyUndefOr[Event] = MyUndefOr.undefined,
    onMouseDown:          MyUndefOr[Callback] = MyUndefOr.undefined,
    onMouseUpE:           MyUndefOr[Event] = MyUndefOr.undefined,
    onMouseUp:            MyUndefOr[Callback] = MyUndefOr.undefined,
    radio:                MyUndefOr[Boolean] = MyUndefOr.undefined,
    readOnly:             MyUndefOr[Boolean] = MyUndefOr.undefined,
    slider:               MyUndefOr[Boolean] = MyUndefOr.undefined,
    tabIndex:             MyUndefOr[Double | String] = MyUndefOr.undefined,
    toggle:               MyUndefOr[Boolean] = MyUndefOr.undefined,
    `type`:               MyUndefOr[CheckboxType] = MyUndefOr.undefined,
    value:                MyUndefOr[String | Double] = MyUndefOr.undefined
  ): RadioProps = {
    val p = as.toJsObject[RadioProps]
    as.toJs.foreach(v => p.as = v)
    checked.foreach(v => p.checked = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    defaultChecked.foreach(v => p.defaultChecked = v)
    defaultIndeterminate.foreach(v => p.defaultIndeterminate = v)
    disabled.foreach(v => p.disabled = v)
    fitted.foreach(v => p.fitted = v)
    id.foreach(v => p.id = v)
    indeterminate.foreach(v => p.indeterminate = v)
    label.foreach(v => p.label = v)
    name.foreach(v => p.name = v)
    (onChangeE, onChange).toJs.foreach(v => p.onChange = v)
    (onClickE, onClick).toJs.foreach(v => p.onClick = v)
    (onMouseDownE, onMouseDown).toJs.foreach(v => p.onMouseDown = v)
    (onMouseUpE, onMouseUp).toJs.foreach(v => p.onMouseUp = v)
    radio.foreach(v => p.radio = v)
    readOnly.foreach(v => p.readOnly = v)
    slider.foreach(v => p.slider = v)
    tabIndex.foreach(v => p.tabIndex = v)
    toggle.foreach(v => p.toggle = v)
    p.`type` = `type`.toJs
    value.foreach(v => p.value = v)
    p
  }

  private val component =
    JsComponent[RadioProps, Children.None, Null](RawComponent)

  val Default: Radio = Radio()

  val defaultProps: RadioProps = props(Default)

  def apply(modifiers: TagMod*): Radio =
    new Radio(modifiers = modifiers)
}
