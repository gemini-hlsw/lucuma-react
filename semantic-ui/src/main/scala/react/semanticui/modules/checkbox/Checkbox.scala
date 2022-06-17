package react.semanticui.modules.checkbox

import scala.scalajs.js
import js.annotation._
import js.|
import japgolly.scalajs.react._
import react.common._
import react.semanticui._
import react.semanticui.{ raw => suiraw }
import japgolly.scalajs.react.vdom.TagMod
import react.semanticui.elements.label.Label

final case class Checkbox(
  as:                     js.UndefOr[AsC] = js.undefined,
  checked:                js.UndefOr[Boolean] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  defaultChecked:         js.UndefOr[Boolean] = js.undefined,
  defaultIndeterminate:   js.UndefOr[Boolean] = js.undefined,
  disabled:               js.UndefOr[Boolean] = js.undefined,
  fitted:                 js.UndefOr[Boolean] = js.undefined,
  id:                     js.UndefOr[Double | String] = js.undefined,
  indeterminate:          js.UndefOr[Boolean] = js.undefined,
  label:                  js.UndefOr[ShorthandS[Label]] = js.undefined,
  name:                   js.UndefOr[String] = js.undefined,
  onChangeE:              js.UndefOr[Checkbox.Event] = js.undefined,
  onChange:               js.UndefOr[Boolean => Callback] = js.undefined,
  onClickE:               js.UndefOr[Checkbox.Event] = js.undefined,
  onClick:                js.UndefOr[Callback] = js.undefined,
  onMouseDownE:           js.UndefOr[Checkbox.Event] = js.undefined,
  onMouseDown:            js.UndefOr[Callback] = js.undefined,
  onMouseUpE:             js.UndefOr[Checkbox.Event] = js.undefined,
  onMouseUp:              js.UndefOr[Callback] = js.undefined,
  radio:                  js.UndefOr[Boolean] = js.undefined,
  readOnly:               js.UndefOr[Boolean] = js.undefined,
  slider:                 js.UndefOr[Boolean] = js.undefined,
  tabIndex:               js.UndefOr[Double | String] = js.undefined,
  toggle:                 js.UndefOr[Boolean] = js.undefined,
  tpe:                    js.UndefOr[CheckboxType] = CheckboxType.Checkbox,
  value:                  js.UndefOr[String | Double] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPA[Checkbox.CheckboxProps, Checkbox] {
  override protected def cprops                     = Checkbox.props(this)
  override val component                            = Checkbox.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object Checkbox {
  type Event    = (ReactMouseEvent, CheckboxProps) => Callback
  type RawEvent = js.Function2[ReactMouseEvent, CheckboxProps, Unit]

  @js.native
  @JSImport("semantic-ui-react", "Checkbox")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait CheckboxProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: js.UndefOr[AsT] = js.native

    /** Whether or not checkbox is checked. */
    var checked: js.UndefOr[Boolean] = js.native

    /** Additional classes. */
    var className: js.UndefOr[String] = js.native

    /** The initial value of checked. */
    var defaultChecked: js.UndefOr[Boolean] = js.native

    /** Whether or not checkbox is indeterminate. */
    var defaultIndeterminate: js.UndefOr[Boolean] = js.native

    /** A checkbox can appear disabled and be unable to change states */
    var disabled: js.UndefOr[Boolean] = js.native

    /** Removes padding for a label. Auto applied when there is no label. */
    var fitted: js.UndefOr[Boolean] = js.native

    /** A unique identifier. */
    var id: js.UndefOr[Double | String] = js.native

    /** Whether or not checkbox is indeterminate. */
    var indeterminate: js.UndefOr[Boolean] = js.native

    /** The text of the associated label element. */
    var label: js.UndefOr[suiraw.SemanticShorthandItemS[Label.LabelProps]] = js.native

    /** The HTML input name. */
    var name: js.UndefOr[String] = js.native

    /**
     * Called when the user attempts to change the checked state.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props and proposed checked/indeterminate state.
     */
    var onChange: js.UndefOr[RawEvent] = js.native

    /**
     * Called when the checkbox or label is clicked.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props and current checked/indeterminate state.
     */
    var onClick: js.UndefOr[RawEvent] = js.native

    /**
     * Called when the user presses down on the mouse.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props and current checked/indeterminate state.
     */
    var onMouseDown: js.UndefOr[RawEvent] = js.native

    /**
     * Called when the user releases the mouse.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props and current checked/indeterminate state.
     */
    var onMouseUp: js.UndefOr[RawEvent] = js.native

    /** Format as a radio element. This means it is an exclusive option. */
    var radio: js.UndefOr[Boolean] = js.native

    /** A checkbox can be read-only and unable to change states. */
    var readOnly: js.UndefOr[Boolean] = js.native

    /** Format to emphasize the current selection state. */
    var slider: js.UndefOr[Boolean] = js.native

    /** A checkbox can receive focus. */
    var tabIndex: js.UndefOr[Double | String] = js.native

    /** Format to show an on or off choice. */
    var toggle: js.UndefOr[Boolean] = js.native

    /** HTML input type, either checkbox or radio. */
    var `type`: js.UndefOr[String] = js.native

    /** The HTML input value. */
    var value: js.UndefOr[String | Double] = js.native
  }

  def props(q: Checkbox): CheckboxProps =
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
    as:                   js.UndefOr[AsC] = js.undefined,
    checked:              js.UndefOr[Boolean] = js.undefined,
    className:            js.UndefOr[String] = js.undefined,
    clazz:                js.UndefOr[Css] = js.undefined,
    defaultChecked:       js.UndefOr[Boolean] = js.undefined,
    defaultIndeterminate: js.UndefOr[Boolean] = js.undefined,
    disabled:             js.UndefOr[Boolean] = js.undefined,
    fitted:               js.UndefOr[Boolean] = js.undefined,
    id:                   js.UndefOr[Double | String] = js.undefined,
    indeterminate:        js.UndefOr[Boolean] = js.undefined,
    label:                js.UndefOr[ShorthandS[Label]] = js.undefined,
    name:                 js.UndefOr[String] = js.undefined,
    onChangeE:            js.UndefOr[Event] = js.undefined,
    onChange:             js.UndefOr[Boolean => Callback] = js.undefined,
    onClickE:             js.UndefOr[Event] = js.undefined,
    onClick:              js.UndefOr[Callback] = js.undefined,
    onMouseDownE:         js.UndefOr[Event] = js.undefined,
    onMouseDown:          js.UndefOr[Callback] = js.undefined,
    onMouseUpE:           js.UndefOr[Event] = js.undefined,
    onMouseUp:            js.UndefOr[Callback] = js.undefined,
    radio:                js.UndefOr[Boolean] = js.undefined,
    readOnly:             js.UndefOr[Boolean] = js.undefined,
    slider:               js.UndefOr[Boolean] = js.undefined,
    tabIndex:             js.UndefOr[Double | String] = js.undefined,
    toggle:               js.UndefOr[Boolean] = js.undefined,
    tpe:                  js.UndefOr[CheckboxType] = js.undefined,
    value:                js.UndefOr[String | Double] = js.undefined
  ): CheckboxProps = {
    val p = as.toJsObject[CheckboxProps]
    as.toJs.foreach(v => p.as = v)
    checked.foreach(v => p.checked = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    defaultChecked.foreach(v => p.defaultChecked = v)
    defaultIndeterminate.foreach(v => p.defaultIndeterminate = v)
    disabled.foreach(v => p.disabled = v)
    fitted.foreach(v => p.fitted = v)
    id.foreach(v => p.id = v)
    indeterminate.foreach(v => p.indeterminate = v)
    label.toJs.foreach(v => p.label = v)
    name.foreach(v => p.name = v)
    onChangeE.toJs
      .map(v => p.onChange = v)
      .orElse(
        onChange.toJs.foreach(v =>
          p.onChange =
            ((_: ReactMouseEvent, cp: CheckboxProps) => cp.checked.foreach(v(_))): RawEvent
        )
      )
    (onClickE, onClick).toJs.foreach(v => p.onClick = v)
    (onMouseDownE, onMouseDown).toJs.foreach(v => p.onMouseDown = v)
    (onMouseUpE, onMouseUp).toJs.foreach(v => p.onMouseUp = v)
    radio.foreach(v => p.radio = v)
    readOnly.foreach(v => p.readOnly = v)
    slider.foreach(v => p.slider = v)
    tabIndex.foreach(v => p.tabIndex = v)
    toggle.foreach(v => p.toggle = v)
    p.`type` = tpe.toJs
    value.foreach(v => p.value = v)
    p
  }

  private val component =
    JsComponent[CheckboxProps, Children.None, Null](RawComponent)

  val Default: Checkbox = Checkbox()

  val defaultProps: CheckboxProps = props(Default)

  def apply(modifiers: TagMod*): Checkbox =
    new Checkbox(modifiers = modifiers)
}
