package react.semanticui.collections.form

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import react.common._
import react.semanticui._
import react.semanticui.{ raw => suiraw }
import org.scalajs.dom
import japgolly.scalajs.react.vdom.TagMod

final case class Form(
  as:                     js.UndefOr[AsC] = js.undefined,
  action:                 js.UndefOr[String] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  error:                  js.UndefOr[Boolean] = js.undefined,
  inverted:               js.UndefOr[Boolean] = js.undefined,
  loading:                js.UndefOr[Boolean] = js.undefined,
  onSubmitE:              js.UndefOr[Form.OnSubmitE] = js.undefined,
  onSubmit:               js.UndefOr[Callback] = js.undefined,
  reply:                  js.UndefOr[Boolean] = js.undefined,
  size:                   js.UndefOr[SemanticSize] = js.undefined,
  success:                js.UndefOr[Boolean] = js.undefined,
  unstackable:            js.UndefOr[Boolean] = js.undefined,
  warning:                js.UndefOr[Boolean] = js.undefined,
  widths:                 js.UndefOr[FormWidths] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[Form.FormProps, Form] {
  override protected def cprops                     = Form.props(this)
  override protected val component                  = Form.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object Form {
  final type ReactFormEvent = ReactEventFrom[dom.Node]
  type OnRawSubmit          = js.Function2[ReactFormEvent, FormProps, Unit]
  type OnSubmitE            = (ReactFormEvent, FormProps) => Callback

  @js.native
  @JSImport("semantic-ui-react", "Form")
  object RawComponent extends js.Object

  @js.native
  trait FormProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: js.UndefOr[AsT] = js.native

    /** The HTML form action */
    var action: js.UndefOr[String] = js.native

    /** Primary content. */
    var children: js.UndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: js.UndefOr[String] = js.native

    /** Automatically show any error Message children. */
    var error: js.UndefOr[Boolean] = js.native

    /** A form can have its color inverted for contrast. */
    var inverted: js.UndefOr[Boolean] = js.native

    /** Automatically show a loading indicator. */
    var loading: js.UndefOr[Boolean] = js.native

    /** The HTML form submit handler. */
    var onSubmit: js.UndefOr[OnRawSubmit] = js.native

    /** A comment can contain a form to reply to a comment. This may have arbitrary content. */
    var reply: js.UndefOr[Boolean] = js.native

    /** A form can vary in size. */
    var size: js.UndefOr[suiraw.SemanticSIZES] = js.native

    /** Automatically show any success Message children. */
    var success: js.UndefOr[Boolean] = js.native

    /** A form can prevent itself from stacking on mobile. */
    var unstackable: js.UndefOr[Boolean] = js.native

    /** Automatically show any warning Message children. */
    var warning: js.UndefOr[Boolean] = js.native

    /** Forms can automatically divide fields to be equal width. */
    var widths: js.UndefOr[String] = js.native
  }

  def props(q: Form): FormProps =
    rawprops(
      q.as,
      q.action,
      q.className,
      q.clazz,
      q.error,
      q.inverted,
      q.loading,
      q.onSubmitE,
      q.onSubmit,
      q.reply,
      q.size,
      q.success,
      q.unstackable,
      q.warning,
      q.widths
    )

  def rawprops(
    as:          js.UndefOr[AsC] = js.undefined,
    action:      js.UndefOr[String] = js.undefined,
    className:   js.UndefOr[String] = js.undefined,
    clazz:       js.UndefOr[Css] = js.undefined,
    error:       js.UndefOr[Boolean] = js.undefined,
    inverted:    js.UndefOr[Boolean] = js.undefined,
    loading:     js.UndefOr[Boolean] = js.undefined,
    onSubmitE:   js.UndefOr[Form.OnSubmitE] = js.undefined,
    onSubmit:    js.UndefOr[Callback] = js.undefined,
    reply:       js.UndefOr[Boolean] = js.undefined,
    size:        js.UndefOr[SemanticSize] = js.undefined,
    success:     js.UndefOr[Boolean] = js.undefined,
    unstackable: js.UndefOr[Boolean] = js.undefined,
    warning:     js.UndefOr[Boolean] = js.undefined,
    widths:      js.UndefOr[FormWidths] = js.undefined
  ): FormProps = {
    val p = as.toJsObject[FormProps]
    as.toJs.foreach(v => p.as = v)
    action.foreach(v => p.action = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    error.foreach(v => p.error = v)
    inverted.foreach(v => p.inverted = v)
    loading.foreach(v => p.loading = v)
    (onSubmitE, onSubmit).toJs.foreach(v => p.onSubmit = v)
    reply.foreach(v => p.reply = v)
    size.toJs.foreach(v => p.size = v)
    success.foreach(v => p.success = v)
    unstackable.foreach(v => p.unstackable = v)
    warning.foreach(v => p.warning = v)
    widths.toJs.foreach(v => p.widths = v)
    p
  }

  private lazy val component =
    JsComponent[FormProps, Children.Varargs, Null](RawComponent)

  val Default: Form = Form()

  val defaultProps: FormProps = props(Default)

  def apply(modifiers: TagMod*): Form =
    Form(modifiers = modifiers)
}
