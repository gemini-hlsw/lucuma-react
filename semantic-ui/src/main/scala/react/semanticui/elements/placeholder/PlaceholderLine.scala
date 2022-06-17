package react.semanticui.elements.placeholder

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import react.common._
import react.semanticui._
import japgolly.scalajs.react.vdom.TagMod

final case class PlaceholderLine(
  as:                     js.UndefOr[AsC] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  length:                 js.UndefOr[LineLength] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPA[
      PlaceholderLine.PlaceholderLineProps,
      PlaceholderLine
    ] {
  override protected def cprops                     = PlaceholderLine.props(this)
  override protected val component                  = PlaceholderLine.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object PlaceholderLine {
  @js.native
  @JSImport("semantic-ui-react", "PlaceholderLine")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait PlaceholderLineProps extends js.Object {
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

    /** A line can specify how long its contents should appear. */
    var length: js.UndefOr[String]
  }

  def props(
    q: PlaceholderLine
  ): PlaceholderLineProps = {
    val p = q.as.toJsObject[PlaceholderLineProps]
    q.as.toJs.foreach(v => p.as = v)
    (q.className, q.clazz).toJs.foreach(v => p.className = v)
    q.length.toJs.foreach(v => p.length = v)
    p
  }

  private val component =
    JsFnComponent[PlaceholderLineProps, Children.None](RawComponent)

  val Default: PlaceholderLine = PlaceholderLine()

  val defaultProps: PlaceholderLineProps = props(Default)

  def apply(modifiers: TagMod*): PlaceholderLine =
    new PlaceholderLine(modifiers = modifiers)
}
