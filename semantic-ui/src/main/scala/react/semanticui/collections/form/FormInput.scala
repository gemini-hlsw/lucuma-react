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
import react.semanticui.elements.icon._
import react.semanticui.elements.input._
import react.semanticui.{raw => suiraw}
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.vdom.VdomNode

final case class FormInput(
  action:                 MyUndefOr[ShorthandSB[VdomNode]] = MyUndefOr.undefined,
  actionPosition:         MyUndefOr[ActionPosition] = MyUndefOr.undefined,
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  content:                MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
  control:                MyUndefOr[String] = MyUndefOr.undefined,
  disabled:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  error:                  MyUndefOr[ShorthandB[Label]] = MyUndefOr.undefined,
  fluid:                  MyUndefOr[Boolean] = MyUndefOr.undefined,
  focus:                  MyUndefOr[Boolean] = MyUndefOr.undefined,
  icon:                   MyUndefOr[ShorthandSB[Icon]] = MyUndefOr.undefined,
  iconPosition:           MyUndefOr[IconPosition] = MyUndefOr.undefined,
  inline:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  input:                  MyUndefOr[VdomNode] = MyUndefOr.undefined,
  inverted:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  label:                  MyUndefOr[ShorthandS[Label]] = MyUndefOr.undefined,
  labelPosition:          MyUndefOr[LabelPosition] = MyUndefOr.undefined,
  loading:                MyUndefOr[Boolean] = MyUndefOr.undefined,
  onChange:               MyUndefOr[Callback] = MyUndefOr.undefined,
  onChangeE:              MyUndefOr[Input.OnChange] = MyUndefOr.undefined,
  required:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  size:                   MyUndefOr[SemanticSize] = MyUndefOr.undefined,
  tabIndex:               MyUndefOr[String | Double] = MyUndefOr.undefined,
  tpe:                    MyUndefOr[String] = MyUndefOr.undefined,
  transparent:            MyUndefOr[Boolean] = MyUndefOr.undefined,
  width:                  MyUndefOr[SemanticWidth] = MyUndefOr.undefined,
  value:                  MyUndefOr[String] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPA[FormInput.FormInputProps, FormInput] {
  override protected def cprops                     = FormInput.props(this)
  override protected val component                  = FormInput.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object FormInput {

  @js.native
  @JSImport("semantic-ui-react", "FormInput")
  object RawComponent extends js.Object

  @js.native
  trait FormInputProps extends js.Object {
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
     * A form control component (i.e. Dropdown) or HTML tagName (i.e. 'input'). Extra FormInput
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

    /** Passed to the control component (i.e. <input type='password' />) */
    var `type`: MyUndefOr[String] = js.native

    /** A field can specify its width in grid columns */
    var width: MyUndefOr[suiraw.SemanticWIDTHS] = js.native // | 'equal'

    /** An Input can be formatted to alert the user to an action they may perform. */
    var action: MyUndefOr[suiraw.SemanticShorthandContentB] = js.native

    /** An action can appear along side an Input on the left or right. */
    var actionPosition: MyUndefOr[String] = js.native

    /** Primary content. */
    var children: MyUndefOr[React.Node] = js.native

    /** Take on the size of its container. */
    var fluid: MyUndefOr[Boolean] = js.native

    /** An Input field can show a user is currently interacting with it. */
    var focus: MyUndefOr[Boolean] = js.native

    /** Optional Icon to display inside the Input. */
    var icon: MyUndefOr[suiraw.SemanticShorthandItemSB[Icon.IconProps]] = js.native

    /** An Icon can appear inside an Input on the left or right. */
    var iconPosition: MyUndefOr[String]

    /** Shorthand for creating the HTML Input. */
    var input: MyUndefOr[suiraw.SemanticShorthandContent] = js.native

    /** Format to appear on dark backgrounds. */
    var inverted: MyUndefOr[Boolean] = js.native

    /** A Label can appear outside an Input on the left or right. */
    var labelPosition: MyUndefOr[String] = js.native

    /** An Icon Input field can show that it is currently loading data. */
    var loading: MyUndefOr[Boolean] = js.native

    /**
     * Called on change.
     *
     * @param {ChangeEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props and a proposed value.
     */
    var onChange: MyUndefOr[js.Function1[ReactEventFromInput, Unit]]

    /** An Input can vary in size. */
    var size: MyUndefOr[suiraw.SemanticSIZES] = js.native

    /** An Input can receive focus. */
    var tabIndex: MyUndefOr[String | Double] = js.native

    /** Transparent Input has no background. */
    var transparent: MyUndefOr[Boolean] = js.native

    /** The HTML value. */
    var value: MyUndefOr[String] = js.native
  }

  def props(q: FormInput): FormInputProps =
    rawprops(
      q.action,
      q.actionPosition,
      q.as,
      q.className,
      q.clazz,
      q.content,
      q.control,
      q.disabled,
      q.error,
      q.fluid,
      q.focus,
      q.icon,
      q.iconPosition,
      q.inline,
      q.input,
      q.inverted,
      q.label,
      q.labelPosition,
      q.loading,
      q.onChange,
      q.onChangeE,
      q.required,
      q.size,
      q.tabIndex,
      q.tpe,
      q.transparent,
      q.width,
      q.value
    )

  def rawprops(
    action:         MyUndefOr[ShorthandSB[VdomNode]] = MyUndefOr.undefined,
    actionPosition: MyUndefOr[ActionPosition] = MyUndefOr.undefined,
    as:             MyUndefOr[AsC] = MyUndefOr.undefined,
    className:      MyUndefOr[String] = MyUndefOr.undefined,
    clazz:          MyUndefOr[Css] = MyUndefOr.undefined,
    content:        MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
    control:        MyUndefOr[String] = MyUndefOr.undefined,
    disabled:       MyUndefOr[Boolean] = MyUndefOr.undefined,
    error:          MyUndefOr[ShorthandB[Label]] = MyUndefOr.undefined,
    fluid:          MyUndefOr[Boolean] = MyUndefOr.undefined,
    focus:          MyUndefOr[Boolean] = MyUndefOr.undefined,
    icon:           MyUndefOr[ShorthandSB[Icon]] = MyUndefOr.undefined,
    iconPosition:   MyUndefOr[IconPosition] = MyUndefOr.undefined,
    inline:         MyUndefOr[Boolean] = MyUndefOr.undefined,
    input:          MyUndefOr[VdomNode] = MyUndefOr.undefined,
    inverted:       MyUndefOr[Boolean] = MyUndefOr.undefined,
    label:          MyUndefOr[ShorthandS[Label]] = MyUndefOr.undefined,
    labelPosition:  MyUndefOr[LabelPosition] = MyUndefOr.undefined,
    loading:        MyUndefOr[Boolean] = MyUndefOr.undefined,
    onChange:       MyUndefOr[Callback] = MyUndefOr.undefined,
    onChangeE:      MyUndefOr[Input.OnChange] = MyUndefOr.undefined,
    required:       MyUndefOr[Boolean] = MyUndefOr.undefined,
    size:           MyUndefOr[SemanticSize] = MyUndefOr.undefined,
    tabIndex:       MyUndefOr[String | Double] = MyUndefOr.undefined,
    tpe:            MyUndefOr[String] = MyUndefOr.undefined,
    transparent:    MyUndefOr[Boolean] = MyUndefOr.undefined,
    width:          MyUndefOr[SemanticWidth] = MyUndefOr.undefined,
    value:          MyUndefOr[String] = MyUndefOr.undefined
  ): FormInputProps = {
    val p = as.toJsObject[FormInputProps]
    as.toJs.foreach(v => p.as = v)
    action.toJs.foreach(v => p.action = v)
    actionPosition.toJs.foreach(v => p.actionPosition = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    disabled.foreach(v => p.disabled = v)
    error.toJs.foreach(v => p.error = v)
    fluid.foreach(v => p.fluid = v)
    focus.foreach(v => p.focus = v)
    icon.toJs.foreach(v => p.icon = v)
    iconPosition.toJs.foreach(v => p.iconPosition = v)
    input.toJs.foreach(v => p.input = v)
    inverted.foreach(v => p.inverted = v)
    label.toJs.foreach(v => p.label = v)
    labelPosition.toJs.foreach(v => p.labelPosition = v)
    loading.foreach(v => p.loading = v)
    (onChangeE, onChange).toJs.foreach(v => p.onChange = v)
    size.toJs.foreach(v => p.size = v)
    tabIndex.foreach(v => p.tabIndex = v)
    transparent.foreach(v => p.transparent = v)
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
    value.foreach(v => p.value = v)
    p
  }

  private val component =
    JsComponent[FormInputProps, Children.None, Null](RawComponent)

  def apply(modifiers: TagMod*): FormInput =
    FormInput(modifiers = modifiers)
}
