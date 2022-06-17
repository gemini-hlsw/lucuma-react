package react.semanticui.collections.form

import scala.scalajs.js
import js.|
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import react.common._
import react.semanticui._
import react.semanticui.{ raw => suiraw }
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.vdom.VdomNode

final case class FormGroup(
  as:                     js.UndefOr[AsC] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  content:                js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
  grouped:                js.UndefOr[Boolean] = js.undefined,
  inline:                 js.UndefOr[Boolean] = js.undefined,
  unstackable:            js.UndefOr[Boolean] = js.undefined,
  widths:                 js.UndefOr[SemanticWidth | FormWidths] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[FormGroup.FormGroupProps, FormGroup] {
  override protected def cprops                     = FormGroup.props(this)
  override protected val component                  = FormGroup.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object FormGroup {

  @js.native
  @JSImport("semantic-ui-react", "FormGroup")
  object RawComponent extends js.Object

  @js.native
  trait FormGroupProps extends js.Object {
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

    var grouped: js.UndefOr[Boolean] = js.native

    /** Multiple fields may be inline in a row. */
    var inline: js.UndefOr[Boolean] = js.native

    /** A form group can prevent itself from stacking on mobile. */
    var unstackable: js.UndefOr[Boolean] = js.native

    /**
     * Fields Groups can specify their width in grid columns or automatically divide fields to be
     * equal width.
     */
    var widths: js.UndefOr[String] = js.native
  }

  def props(q: FormGroup): FormGroupProps =
    rawprops(
      q.as,
      q.className,
      q.clazz,
      q.content,
      q.grouped,
      q.inline,
      q.unstackable,
      q.widths
    )

  def rawprops(
    as:          js.UndefOr[AsC] = js.undefined,
    className:   js.UndefOr[String] = js.undefined,
    clazz:       js.UndefOr[Css] = js.undefined,
    content:     js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
    grouped:     js.UndefOr[Boolean] = js.undefined,
    inline:      js.UndefOr[Boolean] = js.undefined,
    unstackable: js.UndefOr[Boolean] = js.undefined,
    widths:      js.UndefOr[SemanticWidth | FormWidths] = js.undefined
  ): FormGroupProps = {
    val p = as.toJsObject[FormGroupProps]
    as.toJs.foreach(v => p.as = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    content.toJs.foreach(v => p.content = v)
    grouped.foreach(v => p.grouped = v)
    inline.foreach(v => p.inline = v)
    unstackable.foreach(v => p.unstackable = v)
    widths
      .map {
        (_: Any) match {
          case w: FormWidths => w.toJs
          case w             => w.asInstanceOf[SemanticWidth].toJs
        }
      }
      .foreach(v => p.widths = v)
    p
  }

  private val component =
    JsComponent[FormGroupProps, Children.Varargs, Null](RawComponent)

  def apply(modifiers: TagMod*): FormGroup =
    new FormGroup(modifiers = modifiers)
}
