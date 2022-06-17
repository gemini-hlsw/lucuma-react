package react.semanticui.elements.button

import scala.scalajs.js
import js.annotation._
import js.|
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.TagMod
import react.semanticui._
import react.common._

final case class ButtonOr(
  as:                     js.UndefOr[AsC] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  text:                   js.UndefOr[Double | String] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPA[ButtonOr.ButtonOrProps, ButtonOr] {
  override protected def cprops                     = ButtonOr.props(this)
  override protected val component                  = ButtonOr.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object ButtonOr {
  @js.native
  @JSImport("semantic-ui-react", "ButtonOr")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }
  @js.native
  trait ButtonOrProps extends js.Object                    {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: js.UndefOr[AsT] = js.native

    /** Additional classes. */
    var className: js.UndefOr[String] = js.native

    /** Or buttons can have their text localized, or adjusted by using the text prop. */
    var text: js.UndefOr[Double | String] = js.native
  }

  def props(
    q: ButtonOr
  ): ButtonOrProps =
    rawprops(q.as, q.className, q.clazz, q.text)

  def rawprops(
    as:        js.UndefOr[AsC] = js.undefined,
    className: js.UndefOr[String] = js.undefined,
    clazz:     js.UndefOr[Css] = js.undefined,
    text:      js.UndefOr[Double | String] = js.undefined
  ): ButtonOrProps = {
    val p = as.toJsObject[ButtonOrProps]
    as.toJs.foreach(v => p.as = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    text.foreach(v => p.text = v)
    p
  }

  private val component =
    JsComponent[ButtonOrProps, Children.None, Null](RawComponent)

  def apply(modifiers: TagMod*): ButtonOr =
    new ButtonOr(modifiers = modifiers)
}
