// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.elements.input

import scala.scalajs.js
import js.annotation._
import js.|
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.VdomNode
import react.common._
import react.semanticui.{raw => suiraw}
import react.semanticui._

import react.semanticui.elements.icon.Icon
import japgolly.scalajs.react.vdom.TagMod
import react.semanticui.elements.label.Label

final case class Input(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  action:                 MyUndefOr[ShorthandSB[VdomNode]] = MyUndefOr.undefined,
  actionPosition:         MyUndefOr[ActionPosition] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  disabled:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  error:                  MyUndefOr[Boolean] = MyUndefOr.undefined,
  fluid:                  MyUndefOr[Boolean] = MyUndefOr.undefined,
  focus:                  MyUndefOr[Boolean] = MyUndefOr.undefined,
  icon:                   MyUndefOr[ShorthandSB[Icon]] = MyUndefOr.undefined,
  iconPosition:           MyUndefOr[IconPosition] = MyUndefOr.undefined,
  input:                  MyUndefOr[VdomNode] = MyUndefOr.undefined,
  inverted:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  label:                  MyUndefOr[ShorthandS[Label]] = MyUndefOr.undefined,
  labelPosition:          MyUndefOr[LabelPosition] = MyUndefOr.undefined,
  loading:                MyUndefOr[Boolean] = MyUndefOr.undefined,
  onChangeE:              MyUndefOr[Input.OnChange] = MyUndefOr.undefined,
  onChange:               MyUndefOr[Callback] = MyUndefOr.undefined,
  size:                   MyUndefOr[SemanticSize] = MyUndefOr.undefined,
  tabIndex:               MyUndefOr[String | Double] = MyUndefOr.undefined,
  transparent:            MyUndefOr[Boolean] = MyUndefOr.undefined,
  tpe:                    MyUndefOr[String] = MyUndefOr.undefined,
  value:                  MyUndefOr[String] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[Input.InputProps, Input] {
  override protected def cprops                     = Input.props(this)
  override protected val component                  = Input.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object Input {
  type OnChange = ReactEventFromInput => Callback

  @js.native
  @JSImport("semantic-ui-react", "Input")
  object RawComponent extends js.Object

  @js.native
  trait InputProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: MyUndefOr[AsT] = js.native

    /** An Input can be formatted to alert the user to an action they may perform. */
    var action: MyUndefOr[suiraw.SemanticShorthandContentB] = js.native

    /** An action can appear along side an Input on the left or right. */
    var actionPosition: MyUndefOr[String] = js.native

    /** Primary content. */
    var children: MyUndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: MyUndefOr[String] = js.native

    /** An Input field can show that it is disabled. */
    var disabled: MyUndefOr[Boolean] = js.native

    /** An Input field can show the data contains errors. */
    var error: MyUndefOr[Boolean] = js.native

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

    /** Optional Label to display along side the Input. */
    var label: MyUndefOr[suiraw.SemanticShorthandItemS[Label.LabelProps]] = js.native

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

    /** The HTML input type. */
    var `type`: MyUndefOr[String] = js.native

    /** The HTML value. */
    var value: MyUndefOr[String] = js.native
  }

  def props(
    q: Input
  ): InputProps = {
    val p = q.as.toJsObject[InputProps]
    q.as.toJs.foreach(v => p.as = v)
    q.action.toJs.foreach(v => p.action = v)
    q.actionPosition.toJs.foreach(v => p.actionPosition = v)
    (q.className, q.clazz).toJs.foreach(v => p.className = v)
    q.disabled.foreach(v => p.disabled = v)
    q.error.foreach(v => p.error = v)
    q.fluid.foreach(v => p.fluid = v)
    q.focus.foreach(v => p.focus = v)
    q.icon.toJs.foreach(v => p.icon = v)
    q.iconPosition.toJs.foreach(v => p.iconPosition = v)
    q.input.toJs.foreach(v => p.input = v)
    q.inverted.foreach(v => p.inverted = v)
    q.label.toJs.foreach(v => p.label = v)
    q.labelPosition.toJs.foreach(v => p.labelPosition = v)
    q.loading.foreach(v => p.loading = v)
    (q.onChangeE, q.onChange).toJs.foreach(v => p.onChange = v)
    q.size.toJs.foreach(v => p.size = v)
    q.tabIndex.foreach(v => p.tabIndex = v)
    q.transparent.foreach(v => p.transparent = v)
    p.`type` = q.tpe
    q.value.foreach(v => p.value = v)
    p
  }

  private val component =
    JsComponent[InputProps, Children.Varargs, Null](RawComponent)

  def apply(modifiers: TagMod*): Input =
    new Input(modifiers = modifiers)
}
