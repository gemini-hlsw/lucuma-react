package react.semanticui.modules.dropdown

import scala.scalajs.js
import js.annotation._
import js.|
import japgolly.scalajs.react._
import react.semanticui._
import react.common._
import japgolly.scalajs.react.vdom.TagMod

final case class DropdownSearchInput(
  as:                     js.UndefOr[AsC] = js.undefined,
  autoComplete:           js.UndefOr[String] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  tabIndex:               js.UndefOr[String | Double] = js.undefined,
  tpe:                    js.UndefOr[String] = js.undefined,
  value:                  js.UndefOr[Double | String] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPA[DropdownSearchInput.DropdownSearchInputProps, DropdownSearchInput] {
  override protected def cprops                     = DropdownSearchInput.props(this)
  override val component                            = DropdownSearchInput.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object DropdownSearchInput {
  @js.native
  @JSImport("semantic-ui-react", "DropdownSearchInput")
  object RawComponent extends js.Object

  @js.native
  trait DropdownSearchInputProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: js.UndefOr[AsT] = js.native

    /** An input can have the auto complete. */
    var autoComplete: js.UndefOr[String] = js.native

    /** Additional classes. */
    var className: js.UndefOr[String] = js.native

    /** An input can receive focus. */
    var tabIndex: js.UndefOr[String | Double] = js.native

    /** The HTML input type. */
    var `type`: js.UndefOr[String] = js.native

    /** Stored value. */
    var value: js.UndefOr[Double | String] = js.native
  }

  def props(q: DropdownSearchInput): DropdownSearchInputProps =
    rawprops(q.as, q.autoComplete, q.className, q.clazz, q.tabIndex, q.tpe, q.value)

  def rawprops(
    as:           js.UndefOr[AsC] = js.undefined,
    autoComplete: js.UndefOr[String] = js.undefined,
    className:    js.UndefOr[String] = js.undefined,
    clazz:        js.UndefOr[Css] = js.undefined,
    tabIndex:     js.UndefOr[String | Double] = js.undefined,
    `type`:       js.UndefOr[String] = js.undefined,
    value:        js.UndefOr[Double | String] = js.undefined
  ): DropdownSearchInputProps = {
    val p = as.toJsObject[DropdownSearchInputProps]
    as.toJs.foreach(v => p.as = v)
    autoComplete.foreach(v => p.autoComplete = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    tabIndex.foreach(v => p.tabIndex = v)
    p.`type` = `type`
    value.foreach(v => p.value = v)
    p
  }

  private val component =
    JsComponent[DropdownSearchInputProps, Children.None, Null](RawComponent)

  def apply(modifiers: TagMod*): DropdownSearchInput =
    new DropdownSearchInput(modifiers = modifiers)
}
