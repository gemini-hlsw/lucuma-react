package react.semanticui.collections.form

import scala.scalajs.js
import scala.scalajs.js.|
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import react.common._
import react.semanticui._
import react.semanticui.elements.label.Label
import react.semanticui.modules.checkbox._
import react.semanticui.{ raw => suiraw }
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.vdom.VdomNode

final case class FormCheckbox(
  as:                     js.UndefOr[AsC] = js.undefined,
  checked:                js.UndefOr[Boolean] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  content:                js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
  control:                js.UndefOr[String] = js.undefined,
  defaultChecked:         js.UndefOr[Boolean] = js.undefined,
  defaultIndeterminate:   js.UndefOr[Boolean] = js.undefined,
  disabled:               js.UndefOr[Boolean] = js.undefined,
  error:                  js.UndefOr[ShorthandB[Label]] = js.undefined,
  fitted:                 js.UndefOr[Boolean] = js.undefined,
  id:                     js.UndefOr[Double | String] = js.undefined,
  indeterminate:          js.UndefOr[Boolean] = js.undefined,
  inline:                 js.UndefOr[Boolean] = js.undefined,
  label:                  js.UndefOr[ShorthandS[Label]] = js.undefined,
  name:                   js.UndefOr[String] = js.undefined,
  onChange:               js.UndefOr[Boolean => Callback] = js.undefined,
  onChangeE:              js.UndefOr[Checkbox.Event] = js.undefined,
  onClick:                js.UndefOr[Callback] = js.undefined,
  onClickE:               js.UndefOr[Checkbox.Event] = js.undefined,
  onMouseDown:            js.UndefOr[Callback] = js.undefined,
  onMouseDownE:           js.UndefOr[Checkbox.Event] = js.undefined,
  onMouseUp:              js.UndefOr[Callback] = js.undefined,
  onMouseUpE:             js.UndefOr[Checkbox.Event] = js.undefined,
  radio:                  js.UndefOr[Boolean] = js.undefined,
  readOnly:               js.UndefOr[Boolean] = js.undefined,
  required:               js.UndefOr[Boolean] = js.undefined,
  slider:                 js.UndefOr[Boolean] = js.undefined,
  tabIndex:               js.UndefOr[Double | String] = js.undefined,
  toggle:                 js.UndefOr[Boolean] = js.undefined,
  tpe:                    js.UndefOr[CheckboxType] = CheckboxType.Checkbox,
  value:                  js.UndefOr[String | Double] = js.undefined,
  width:                  js.UndefOr[SemanticWidth] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[FormCheckbox.FormCheckboxProps, FormCheckbox] {
  override protected def cprops                     = FormCheckbox.props(this)
  override protected val component                  = FormCheckbox.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object FormCheckbox {

  @js.native
  @JSImport("semantic-ui-react", "FormCheckbox")
  object RawComponent extends js.Object

  @js.native
  // This should be FormCheckboxProps extends FormField.FormFieldProps with Checkbox.CheckboxProps
  // But it collides with scala multiple inheritane rules, thus we just copy the props from both
  trait FormCheckboxProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: js.UndefOr[AsT] = js.native

    /** Primary content. */
    var children: js.UndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: js.UndefOr[String] = js.native

    /** Shorthand for primary content. */
    var content: js.UndefOr[suiraw.SemanticShorthandContent] =
      js.undefined

    /**
     * A form control component (i.e. Dropdown) or HTML tagName (i.e. 'input'). Extra FormField
     * props are passed to the control component. Mutually exclusive with children.
     */
    // control?: any
    var control: js.UndefOr[String]

    /** Individual fields may be disabled. */
    var disabled: js.UndefOr[Boolean] = js.native

    /** Individual fields may display an error state along with a message. */
    var error: js.UndefOr[suiraw.SemanticShorthandItemB[Label.LabelProps]] = js.native

    /** A field can have its label next to instead of above it. */
    var inline: js.UndefOr[Boolean] = js.native

    /** Mutually exclusive with children. */
    var label: js.UndefOr[suiraw.SemanticShorthandItemS[Label.LabelProps]] = js.native

    /** A field can show that input is mandatory.  Requires a label. */
    var required: js.UndefOr[Boolean] = js.native

    /** A field can specify its width in grid columns */
    var width: js.UndefOr[suiraw.SemanticWIDTHS] = js.native // | 'equal'

    /** Whether or not checkbox is checked. */
    var checked: js.UndefOr[Boolean] = js.native

    /** The initial value of checked. */
    var defaultChecked: js.UndefOr[Boolean] = js.native

    /** Whether or not checkbox is indeterminate. */
    var defaultIndeterminate: js.UndefOr[Boolean] = js.native

    /** Removes padding for a label. Auto applied when there is no label. */
    var fitted: js.UndefOr[Boolean] = js.native

    /** A unique identifier. */
    var id: js.UndefOr[Double | String] = js.native

    /** Whether or not checkbox is indeterminate. */
    var indeterminate: js.UndefOr[Boolean] = js.native

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
    var onChange: js.UndefOr[Checkbox.RawEvent] = js.native

    /**
     * Called when the checkbox or label is clicked.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props and current checked/indeterminate state.
     */
    var onClick: js.UndefOr[Checkbox.RawEvent] = js.native

    /**
     * Called when the user presses down on the mouse.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props and current checked/indeterminate state.
     */
    var onMouseDown: js.UndefOr[Checkbox.RawEvent] = js.native

    /**
     * Called when the user releases the mouse.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props and current checked/indeterminate state.
     */
    var onMouseUp: js.UndefOr[Checkbox.RawEvent] = js.native

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

  def props(q: FormCheckbox): FormCheckboxProps =
    rawprops(
      q.as,
      q.checked,
      q.className,
      q.clazz,
      q.content,
      q.control,
      q.defaultChecked,
      q.defaultIndeterminate,
      q.disabled,
      q.error,
      q.fitted,
      q.id,
      q.indeterminate,
      q.inline,
      q.label,
      q.name,
      q.onChange,
      q.onChangeE,
      q.onClick,
      q.onClickE,
      q.onMouseDown,
      q.onMouseDownE,
      q.onMouseUp,
      q.onMouseUpE,
      q.radio,
      q.readOnly,
      q.required,
      q.slider,
      q.tabIndex,
      q.toggle,
      q.tpe,
      q.value,
      q.width
    )

  def rawprops(
    as:                   js.UndefOr[AsC] = js.undefined,
    checked:              js.UndefOr[Boolean] = js.undefined,
    className:            js.UndefOr[String] = js.undefined,
    clazz:                js.UndefOr[Css] = js.undefined,
    content:              js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
    control:              js.UndefOr[String] = js.undefined,
    defaultChecked:       js.UndefOr[Boolean] = js.undefined,
    defaultIndeterminate: js.UndefOr[Boolean] = js.undefined,
    disabled:             js.UndefOr[Boolean] = js.undefined,
    error:                js.UndefOr[ShorthandB[Label]] = js.undefined,
    fitted:               js.UndefOr[Boolean] = js.undefined,
    id:                   js.UndefOr[Double | String] = js.undefined,
    indeterminate:        js.UndefOr[Boolean] = js.undefined,
    inline:               js.UndefOr[Boolean] = js.undefined,
    label:                js.UndefOr[ShorthandS[Label]] = js.undefined,
    name:                 js.UndefOr[String] = js.undefined,
    onChange:             js.UndefOr[Boolean => Callback] = js.undefined,
    onChangeE:            js.UndefOr[Checkbox.Event] = js.undefined,
    onClick:              js.UndefOr[Callback] = js.undefined,
    onClickE:             js.UndefOr[Checkbox.Event] = js.undefined,
    onMouseDown:          js.UndefOr[Callback] = js.undefined,
    onMouseDownE:         js.UndefOr[Checkbox.Event] = js.undefined,
    onMouseUp:            js.UndefOr[Callback] = js.undefined,
    onMouseUpE:           js.UndefOr[Checkbox.Event] = js.undefined,
    radio:                js.UndefOr[Boolean] = js.undefined,
    readOnly:             js.UndefOr[Boolean] = js.undefined,
    required:             js.UndefOr[Boolean] = js.undefined,
    slider:               js.UndefOr[Boolean] = js.undefined,
    tabIndex:             js.UndefOr[Double | String] = js.undefined,
    toggle:               js.UndefOr[Boolean] = js.undefined,
    tpe:                  js.UndefOr[CheckboxType] = CheckboxType.Checkbox,
    value:                js.UndefOr[String | Double] = js.undefined,
    width:                js.UndefOr[SemanticWidth] = js.undefined
  ): FormCheckboxProps = {
    val p = as.toJsObject[FormCheckboxProps]
    (className, clazz).toJs.foreach(v => p.className = v)
    onChangeE.toJs
      .map(v => p.onChange = v)
      .orElse(
        onChange.toJs.foreach(v =>
          p.onChange = (
            (
              _:  ReactMouseEvent,
              cp: Checkbox.CheckboxProps
            ) => cp.checked.foreach(v(_))
          ): Checkbox.RawEvent
        )
      )
    (onClickE, onClick).toJs.foreach(v => p.onClick = v)
    (onMouseDownE, onMouseDown).toJs.foreach(v => p.onMouseDown = v)
    (onMouseUpE, onMouseUp).toJs.foreach(v => p.onMouseUp = v)
    as.toJs.foreach(v => p.as = v)
    checked.foreach(v => p.checked = v)
    content.toJs.foreach(v => p.content = v)
    control.foreach(v => p.control = v)
    defaultChecked.foreach(v => p.defaultChecked = v)
    defaultIndeterminate.foreach(v => p.defaultIndeterminate = v)
    disabled.foreach(v => p.disabled = v)
    error.toJs.foreach(v => p.error = v)
    fitted.foreach(v => p.fitted = v)
    id.foreach(v => p.id = v)
    indeterminate.foreach(v => p.indeterminate = v)
    inline.foreach(v => p.inline = v)
    label.toJs.foreach(v => p.label = v)
    name.foreach(v => p.name = v)
    p.`type` = tpe.toJs
    radio.foreach(v => p.radio = v)
    readOnly.foreach(v => p.readOnly = v)
    required.foreach(v => p.required = v)
    slider.foreach(v => p.slider = v)
    tabIndex.foreach(v => p.tabIndex = v)
    toggle.foreach(v => p.toggle = v)
    value.foreach(v => p.value = v)
    width.toJs.foreach(v => p.width = v)
    p
  }

  private val component =
    JsComponent[FormCheckboxProps, Children.Varargs, Null](RawComponent)

  def apply(modifiers: TagMod*): FormCheckbox =
    FormCheckbox(modifiers = modifiers)
}
