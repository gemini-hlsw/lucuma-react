// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.collections.form

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.vdom.VdomNode
import react.common._
import react.semanticui._
import react.semanticui.addons.textarea.TextArea
import react.semanticui.elements.label.Label
import react.semanticui.{raw => suiraw}

import scala.scalajs.js
import scala.scalajs.js.|

import js.annotation._

final case class FormTextArea(
  as:                     js.UndefOr[AsC] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  content:                js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
  control:                js.UndefOr[String] = js.undefined,
  disabled:               js.UndefOr[Boolean] = js.undefined,
  error:                  js.UndefOr[ShorthandB[Label]] = js.undefined,
  inline:                 js.UndefOr[Boolean] = js.undefined,
  label:                  js.UndefOr[ShorthandS[Label]] = js.undefined,
  onChange:               js.UndefOr[Callback] = js.undefined,
  onChangeE:              js.UndefOr[TextArea.Event] = js.undefined,
  onInput:                js.UndefOr[Callback] = js.undefined,
  onInputE:               js.UndefOr[TextArea.Event] = js.undefined,
  required:               js.UndefOr[Boolean] = js.undefined,
  rows:                   js.UndefOr[Int | String] = js.undefined,
  tpe:                    js.UndefOr[String] = js.undefined,
  value:                  js.UndefOr[String | Double] = js.undefined,
  width:                  js.UndefOr[SemanticWidth] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPA[FormTextArea.FormFieldProps, FormTextArea] {
  override protected def cprops    = FormTextArea.props(this)
  override protected val component = FormTextArea.component
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
    var as: js.UndefOr[AsT] = js.native

    /** Additional classes. */
    var className: js.UndefOr[String] = js.native

    /** Shorthand for primary content. */
    var content: js.UndefOr[suiraw.SemanticShorthandContent] =
      js.native

    /**
     * A form control component (i.e. Dropdown) or HTML tagName (i.e. 'input'). Extra FormTextArea
     * props are passed to the control component. Mutually exclusive with children.
     */
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

    /** Passed to the control component (i.e. <input type='password' />) */
    var `type`: js.UndefOr[String] = js.native

    /** A field can specify its width in grid columns */
    var width: js.UndefOr[suiraw.SemanticWIDTHS] = js.native // | 'equal'

    /**
     * Called on change.
     *
     * @param {SyntheticEvent}
     *   event - The React SyntheticEvent object
     * @param {object}
     *   data - All props and the event value.
     */
    var onChange: js.UndefOr[TextArea.RawEvent] = js.native

    /**
     * Called on input.
     *
     * @param {SyntheticEvent}
     *   event - The React SyntheticEvent object
     * @param {object}
     *   data - All props and the event value.
     */
    var onInput: js.UndefOr[TextArea.RawEvent] = js.native

    /** Indicates row count for a TextArea. */
    var rows: js.UndefOr[Int | String] = js.native

    /** The value of the textarea. */
    var value: js.UndefOr[Double | String] = js.native
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
    as:        js.UndefOr[AsC] = js.undefined,
    className: js.UndefOr[String] = js.undefined,
    clazz:     js.UndefOr[Css] = js.undefined,
    content:   js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
    control:   js.UndefOr[String] = js.undefined,
    disabled:  js.UndefOr[Boolean] = js.undefined,
    error:     js.UndefOr[ShorthandB[Label]] = js.undefined,
    inline:    js.UndefOr[Boolean] = js.undefined,
    label:     js.UndefOr[ShorthandS[Label]] = js.undefined,
    onChange:  js.UndefOr[Callback] = js.undefined,
    onChangeE: js.UndefOr[TextArea.Event] = js.undefined,
    onInput:   js.UndefOr[Callback] = js.undefined,
    onInputE:  js.UndefOr[TextArea.Event] = js.undefined,
    required:  js.UndefOr[Boolean] = js.undefined,
    rows:      js.UndefOr[Int | String] = js.undefined,
    tpe:       js.UndefOr[String] = js.undefined,
    value:     js.UndefOr[String | Double] = js.undefined,
    width:     js.UndefOr[SemanticWidth] = js.undefined
  ): FormFieldProps = {
    val p = as.toJsObject[FormFieldProps]
    as.toJs.foreachUnchecked(v => p.as = v)
    (className, clazz).cssToJs.foreach(v => p.className = v)
    content.toJs.foreachUnchecked(v => p.content = v)
    control.foreach(v => p.control = v)
    disabled.foreach(v => p.disabled = v)
    CompToPropsB(error).toJs.foreachUnchecked(v => p.error = v)
    inline.foreach(v => p.inline = v)
    CompToPropsS(label).toJs.foreachUnchecked(v => p.label = v)
    required.foreach(v => p.required = v)
    p.`type` = tpe
    width.toJs.foreach(v => p.width = v)
    (onChangeE, onChange).toJs.foreach(v => p.onChange = v)
    (onInputE, onInput).toJs.foreach(v => p.onInput = v)
    rows.foreachUnchecked(v => p.rows = v)
    value.foreachUnchecked(v => p.value = v)
    p
  }

  private val component =
    JsComponent[FormFieldProps, Children.None, Null](RawComponent)

  def apply(modifiers: TagMod*): FormTextArea =
    FormTextArea(modifiers = modifiers)
}
