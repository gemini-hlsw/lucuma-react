package react.semanticui.elements.input

import scala.scalajs.js
import js.annotation._
import js.|
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.VdomNode
import react.common._
import react.semanticui.{ raw => suiraw }
import react.semanticui._

import react.semanticui.elements.icon.Icon
import japgolly.scalajs.react.vdom.TagMod
import react.semanticui.elements.label.Label

final case class Input(
  as:                     js.UndefOr[AsC] = js.undefined,
  action:                 js.UndefOr[ShorthandSB[VdomNode]] = js.undefined,
  actionPosition:         js.UndefOr[ActionPosition] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  disabled:               js.UndefOr[Boolean] = js.undefined,
  error:                  js.UndefOr[Boolean] = js.undefined,
  fluid:                  js.UndefOr[Boolean] = js.undefined,
  focus:                  js.UndefOr[Boolean] = js.undefined,
  icon:                   js.UndefOr[ShorthandSB[Icon]] = js.undefined,
  iconPosition:           js.UndefOr[IconPosition] = js.undefined,
  input:                  js.UndefOr[VdomNode] = js.undefined,
  inverted:               js.UndefOr[Boolean] = js.undefined,
  label:                  js.UndefOr[ShorthandS[Label]] = js.undefined,
  labelPosition:          js.UndefOr[LabelPosition] = js.undefined,
  loading:                js.UndefOr[Boolean] = js.undefined,
  onChangeE:              js.UndefOr[Input.OnChange] = js.undefined,
  onChange:               js.UndefOr[Callback] = js.undefined,
  size:                   js.UndefOr[SemanticSize] = js.undefined,
  tabIndex:               js.UndefOr[String | Double] = js.undefined,
  transparent:            js.UndefOr[Boolean] = js.undefined,
  tpe:                    js.UndefOr[String] = js.undefined,
  value:                  js.UndefOr[String] = js.undefined,
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
    var as: js.UndefOr[AsT] = js.native

    /** An Input can be formatted to alert the user to an action they may perform. */
    var action: js.UndefOr[suiraw.SemanticShorthandContentB] = js.native

    /** An action can appear along side an Input on the left or right. */
    var actionPosition: js.UndefOr[String] = js.native

    /** Primary content. */
    var children: js.UndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: js.UndefOr[String] = js.native

    /** An Input field can show that it is disabled. */
    var disabled: js.UndefOr[Boolean] = js.native

    /** An Input field can show the data contains errors. */
    var error: js.UndefOr[Boolean] = js.native

    /** Take on the size of its container. */
    var fluid: js.UndefOr[Boolean] = js.native

    /** An Input field can show a user is currently interacting with it. */
    var focus: js.UndefOr[Boolean] = js.native

    /** Optional Icon to display inside the Input. */
    var icon: js.UndefOr[suiraw.SemanticShorthandItemSB[Icon.IconProps]] = js.native

    /** An Icon can appear inside an Input on the left or right. */
    var iconPosition: js.UndefOr[String]

    /** Shorthand for creating the HTML Input. */
    var input: js.UndefOr[suiraw.SemanticShorthandContent] = js.native

    /** Format to appear on dark backgrounds. */
    var inverted: js.UndefOr[Boolean] = js.native

    /** Optional Label to display along side the Input. */
    var label: js.UndefOr[suiraw.SemanticShorthandItemS[Label.LabelProps]] = js.native

    /** A Label can appear outside an Input on the left or right. */
    var labelPosition: js.UndefOr[String] = js.native

    /** An Icon Input field can show that it is currently loading data. */
    var loading: js.UndefOr[Boolean] = js.native

    /**
     * Called on change.
     *
     * @param {ChangeEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props and a proposed value.
     */
    var onChange: js.UndefOr[js.Function1[ReactEventFromInput, Unit]]

    /** An Input can vary in size. */
    var size: js.UndefOr[suiraw.SemanticSIZES] = js.native

    /** An Input can receive focus. */
    var tabIndex: js.UndefOr[String | Double] = js.native

    /** Transparent Input has no background. */
    var transparent: js.UndefOr[Boolean] = js.native

    /** The HTML input type. */
    var `type`: js.UndefOr[String] = js.native

    /** The HTML value. */
    var value: js.UndefOr[String] = js.native
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
