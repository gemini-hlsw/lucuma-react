package react.semanticui.elements.flag

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import react.common._
import react.semanticui._
import japgolly.scalajs.react.vdom.TagMod

final case class Flag(
  as:                     js.UndefOr[AsC] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  name:                   js.UndefOr[String] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPA[Flag.FlagProps, Flag] {
  override protected def cprops                     = Flag.props(this)
  override protected val component                  = Flag.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object Flag {
  @js.native
  @JSImport("semantic-ui-react", "Flag")
  private[semanticui] object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait FlagProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: js.UndefOr[AsT] = js.native

    /** Additional classes. */
    var className: js.UndefOr[String] = js.native

    /** Flag name, can use the two digit country code, the full name, or a common alias. */
    var name: js.UndefOr[String] = js.native
  }

  def props(
    q: Flag
  ): FlagProps = {
    val p = q.as.toJsObject[FlagProps]
    q.as.toJs.foreach(v => p.as = v)
    (q.className, q.clazz).toJs.foreach(v => p.className = v)
    q.name.foreach(v => p.name = v)
    p
  }

  private val component =
    JsComponent[FlagProps, Children.None, Null](RawComponent)

  def apply(name: String): Flag = new Flag(name = name)
}
