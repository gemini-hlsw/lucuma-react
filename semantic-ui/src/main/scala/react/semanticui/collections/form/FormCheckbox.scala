// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

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
import react.semanticui.{raw => suiraw}
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.vdom.VdomNode

final case class FormCheckbox(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  checked:                MyUndefOr[Boolean] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  content:                MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
  control:                MyUndefOr[String] = MyUndefOr.undefined,
  defaultChecked:         MyUndefOr[Boolean] = MyUndefOr.undefined,
  defaultIndeterminate:   MyUndefOr[Boolean] = MyUndefOr.undefined,
  disabled:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  error:                  MyUndefOr[ShorthandB[Label]] = MyUndefOr.undefined,
  fitted:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  id:                     MyUndefOr[Double | String] = MyUndefOr.undefined,
  indeterminate:          MyUndefOr[Boolean] = MyUndefOr.undefined,
  inline:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  label:                  MyUndefOr[ShorthandS[Label]] = MyUndefOr.undefined,
  name:                   MyUndefOr[String] = MyUndefOr.undefined,
  onChange:               MyUndefOr[Boolean => Callback] = MyUndefOr.undefined,
  onChangeE:              MyUndefOr[Checkbox.Event] = MyUndefOr.undefined,
  onClick:                MyUndefOr[Callback] = MyUndefOr.undefined,
  onClickE:               MyUndefOr[Checkbox.Event] = MyUndefOr.undefined,
  onMouseDown:            MyUndefOr[Callback] = MyUndefOr.undefined,
  onMouseDownE:           MyUndefOr[Checkbox.Event] = MyUndefOr.undefined,
  onMouseUp:              MyUndefOr[Callback] = MyUndefOr.undefined,
  onMouseUpE:             MyUndefOr[Checkbox.Event] = MyUndefOr.undefined,
  radio:                  MyUndefOr[Boolean] = MyUndefOr.undefined,
  readOnly:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  required:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  slider:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  tabIndex:               MyUndefOr[Double | String] = MyUndefOr.undefined,
  toggle:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  tpe:                    MyUndefOr[CheckboxType] = CheckboxType.Checkbox,
  value:                  MyUndefOr[String | Double] = MyUndefOr.undefined,
  width:                  MyUndefOr[SemanticWidth] = MyUndefOr.undefined,
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
    var as: MyUndefOr[AsT] = js.native

    /** Primary content. */
    var children: MyUndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: MyUndefOr[String] = js.native

    /** Shorthand for primary content. */
    var content: MyUndefOr[suiraw.SemanticShorthandContent] =
      MyUndefOr.undefined

    /**
     * A form control component (i.e. Dropdown) or HTML tagName (i.e. 'input'). Extra FormField
     * props are passed to the control component. Mutually exclusive with children.
     */
    // control?: any
    var control: MyUndefOr[String]

    /** Individual fields may be disabled. */
    var disabled: MyUndefOr[Boolean] = js.native

    /** Individual fields may display an error state along with a message. */
    var error: MyUndefOr[suiraw.SemanticShorthandItemB[Label.LabelProps]] = js.native

    /** A field can have its label next to instead of above it. */
    var inline: MyUndefOr[Boolean] = js.native

    /** Mutually exclusive with children. */
    var label: MyUndefOr[suiraw.SemanticShorthandItemS[Label.LabelProps]] = js.native

    /** A field can show that input is mandatory.  Requires a label. */
    var required: MyUndefOr[Boolean] = js.native

    /** A field can specify its width in grid columns */
    var width: MyUndefOr[suiraw.SemanticWIDTHS] = js.native // | 'equal'

    /** Whether or not checkbox is checked. */
    var checked: MyUndefOr[Boolean] = js.native

    /** The initial value of checked. */
    var defaultChecked: MyUndefOr[Boolean] = js.native

    /** Whether or not checkbox is indeterminate. */
    var defaultIndeterminate: MyUndefOr[Boolean] = js.native

    /** Removes padding for a label. Auto applied when there is no label. */
    var fitted: MyUndefOr[Boolean] = js.native

    /** A unique identifier. */
    var id: MyUndefOr[Double | String] = js.native

    /** Whether or not checkbox is indeterminate. */
    var indeterminate: MyUndefOr[Boolean] = js.native

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
    var onChange: MyUndefOr[Checkbox.RawEvent] = js.native

    /**
     * Called when the checkbox or label is clicked.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props and current checked/indeterminate state.
     */
    var onClick: MyUndefOr[Checkbox.RawEvent] = js.native

    /**
     * Called when the user presses down on the mouse.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props and current checked/indeterminate state.
     */
    var onMouseDown: MyUndefOr[Checkbox.RawEvent] = js.native

    /**
     * Called when the user releases the mouse.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props and current checked/indeterminate state.
     */
    var onMouseUp: MyUndefOr[Checkbox.RawEvent] = js.native

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
    as:                   MyUndefOr[AsC] = MyUndefOr.undefined,
    checked:              MyUndefOr[Boolean] = MyUndefOr.undefined,
    className:            MyUndefOr[String] = MyUndefOr.undefined,
    clazz:                MyUndefOr[Css] = MyUndefOr.undefined,
    content:              MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
    control:              MyUndefOr[String] = MyUndefOr.undefined,
    defaultChecked:       MyUndefOr[Boolean] = MyUndefOr.undefined,
    defaultIndeterminate: MyUndefOr[Boolean] = MyUndefOr.undefined,
    disabled:             MyUndefOr[Boolean] = MyUndefOr.undefined,
    error:                MyUndefOr[ShorthandB[Label]] = MyUndefOr.undefined,
    fitted:               MyUndefOr[Boolean] = MyUndefOr.undefined,
    id:                   MyUndefOr[Double | String] = MyUndefOr.undefined,
    indeterminate:        MyUndefOr[Boolean] = MyUndefOr.undefined,
    inline:               MyUndefOr[Boolean] = MyUndefOr.undefined,
    label:                MyUndefOr[ShorthandS[Label]] = MyUndefOr.undefined,
    name:                 MyUndefOr[String] = MyUndefOr.undefined,
    onChange:             MyUndefOr[Boolean => Callback] = MyUndefOr.undefined,
    onChangeE:            MyUndefOr[Checkbox.Event] = MyUndefOr.undefined,
    onClick:              MyUndefOr[Callback] = MyUndefOr.undefined,
    onClickE:             MyUndefOr[Checkbox.Event] = MyUndefOr.undefined,
    onMouseDown:          MyUndefOr[Callback] = MyUndefOr.undefined,
    onMouseDownE:         MyUndefOr[Checkbox.Event] = MyUndefOr.undefined,
    onMouseUp:            MyUndefOr[Callback] = MyUndefOr.undefined,
    onMouseUpE:           MyUndefOr[Checkbox.Event] = MyUndefOr.undefined,
    radio:                MyUndefOr[Boolean] = MyUndefOr.undefined,
    readOnly:             MyUndefOr[Boolean] = MyUndefOr.undefined,
    required:             MyUndefOr[Boolean] = MyUndefOr.undefined,
    slider:               MyUndefOr[Boolean] = MyUndefOr.undefined,
    tabIndex:             MyUndefOr[Double | String] = MyUndefOr.undefined,
    toggle:               MyUndefOr[Boolean] = MyUndefOr.undefined,
    tpe:                  MyUndefOr[CheckboxType] = CheckboxType.Checkbox,
    value:                MyUndefOr[String | Double] = MyUndefOr.undefined,
    width:                MyUndefOr[SemanticWidth] = MyUndefOr.undefined
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
