// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.collections.form

import scala.scalajs.js
import scala.scalajs.js.|
import js.annotation._
import japgolly.scalajs.react._
import react.common._
import react.semanticui._
import react.semanticui.elements.label.Label
import react.semanticui.{raw => suiraw}
import react.semanticui.addons.textarea.TextArea
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.vdom.VdomNode

final case class FormTextArea(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  content:                MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
  control:                MyUndefOr[String] = MyUndefOr.undefined,
  disabled:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  error:                  MyUndefOr[ShorthandB[Label]] = MyUndefOr.undefined,
  inline:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  label:                  MyUndefOr[ShorthandS[Label]] = MyUndefOr.undefined,
  onChange:               MyUndefOr[Callback] = MyUndefOr.undefined,
  onChangeE:              MyUndefOr[TextArea.Event] = MyUndefOr.undefined,
  onInput:                MyUndefOr[Callback] = MyUndefOr.undefined,
  onInputE:               MyUndefOr[TextArea.Event] = MyUndefOr.undefined,
  required:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  rows:                   MyUndefOr[Int | String] = MyUndefOr.undefined,
  tpe:                    MyUndefOr[String] = MyUndefOr.undefined,
  value:                  MyUndefOr[String | Double] = MyUndefOr.undefined,
  width:                  MyUndefOr[SemanticWidth] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPA[FormTextArea.FormFieldProps, FormTextArea] {
  override protected def cprops                     = FormTextArea.props(this)
  override protected val component                  = FormTextArea.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object FormTextArea {

  @js.native
  @JSImport("semantic-ui-react", "FormTextArea")
  object RawComponent extends js.Object

  @js.native
  trait FormFieldProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: MyUndefOr[AsT] = js.native

    /** Additional classes. */
    var className: MyUndefOr[String] = js.native

    /** Shorthand for primary content. */
    var content: MyUndefOr[suiraw.SemanticShorthandContent] =
      MyUndefOr.undefined

    /**
     * A form control component (i.e. Dropdown) or HTML tagName (i.e. 'input'). Extra FormTextArea
     * props are passed to the control component. Mutually exclusive with children.
     */
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

    /** Passed to the control component (i.e. <input type='password' />) */
    var `type`: MyUndefOr[String] = js.native

    /** A field can specify its width in grid columns */
    var width: MyUndefOr[suiraw.SemanticWIDTHS] = js.native // | 'equal'

    /**
     * Called on change.
     *
     * @param {SyntheticEvent}
     *   event - The React SyntheticEvent object
     * @param {object}
     *   data - All props and the event value.
     */
    var onChange: MyUndefOr[TextArea.RawEvent] = MyUndefOr.undefined

    /**
     * Called on input.
     *
     * @param {SyntheticEvent}
     *   event - The React SyntheticEvent object
     * @param {object}
     *   data - All props and the event value.
     */
    var onInput: MyUndefOr[TextArea.RawEvent] = MyUndefOr.undefined

    /** Indicates row count for a TextArea. */
    var rows: MyUndefOr[Double | String] = MyUndefOr.undefined

    /** The value of the textarea. */
    var value: MyUndefOr[Double | String] = MyUndefOr.undefined
  }

  def props(q: FormTextArea): FormFieldProps =
    rawprops(
      q.as,
      q.className,
      q.clazz,
      q.content,
      q.control,
      q.disabled,
      q.error,
      q.inline,
      q.label,
      q.onChange,
      q.onChangeE,
      q.onInput,
      q.onInputE,
      q.required,
      q.rows,
      q.tpe,
      q.value,
      q.width
    )

  def rawprops(
    as:        MyUndefOr[AsC] = MyUndefOr.undefined,
    className: MyUndefOr[String] = MyUndefOr.undefined,
    clazz:     MyUndefOr[Css] = MyUndefOr.undefined,
    content:   MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
    control:   MyUndefOr[String] = MyUndefOr.undefined,
    disabled:  MyUndefOr[Boolean] = MyUndefOr.undefined,
    error:     MyUndefOr[ShorthandB[Label]] = MyUndefOr.undefined,
    inline:    MyUndefOr[Boolean] = MyUndefOr.undefined,
    label:     MyUndefOr[ShorthandS[Label]] = MyUndefOr.undefined,
    onChange:  MyUndefOr[Callback] = MyUndefOr.undefined,
    onChangeE: MyUndefOr[TextArea.Event] = MyUndefOr.undefined,
    onInput:   MyUndefOr[Callback] = MyUndefOr.undefined,
    onInputE:  MyUndefOr[TextArea.Event] = MyUndefOr.undefined,
    required:  MyUndefOr[Boolean] = MyUndefOr.undefined,
    rows:      MyUndefOr[Int | String] = MyUndefOr.undefined,
    tpe:       MyUndefOr[String] = MyUndefOr.undefined,
    value:     MyUndefOr[String | Double] = MyUndefOr.undefined,
    width:     MyUndefOr[SemanticWidth] = MyUndefOr.undefined
  ): FormFieldProps = {
    val p = as.toJsObject[FormFieldProps]
    as.toJs.foreach(v => p.as = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    content.toJs.foreach(v => p.content = v)
    control.foreach(v => p.control = v)
    disabled.foreach(v => p.disabled = v)
    error.toJs.foreach(v => p.error = v)
    inline.foreach(v => p.inline = v)
    label.toJs.foreach(v => p.label = v)
    required.foreach(v => p.required = v)
    p.`type` = tpe
    width.toJs.foreach(v => p.width = v)
    (onChangeE, onChange).toJs.foreach(v => p.onChange = v)
    (onInputE, onInput).toJs.foreach(v => p.onInput = v)
    rows.foreach(v => p.rows = v)
    value.foreach(v => p.value = v)
    p
  }

  private val component =
    JsComponent[FormFieldProps, Children.None, Null](RawComponent)

  def apply(modifiers: TagMod*): FormTextArea =
    FormTextArea(modifiers = modifiers)
}
