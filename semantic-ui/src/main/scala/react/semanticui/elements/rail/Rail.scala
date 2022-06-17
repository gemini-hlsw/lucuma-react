package react.semanticui.elements.rail

import scala.scalajs.js
import scala.scalajs.js.|
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.VdomNode
import react.common._
import react.semanticui.{ raw => suiraw }
import react.semanticui._
import japgolly.scalajs.react.vdom.TagMod

final case class Rail(
  as:                     js.UndefOr[AsC] = js.undefined,
  attached:               js.UndefOr[Boolean] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  close:                  js.UndefOr[RailClose] = js.undefined,
  content:                js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
  dividing:               js.UndefOr[Boolean] = js.undefined,
  internal:               js.UndefOr[Boolean] = js.undefined,
  position:               js.UndefOr[SemanticFloat],
  size:                   js.UndefOr[SemanticSize] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPAC[Rail.RailProps, Rail] {
  override protected def cprops                     = Rail.props(this)
  override protected val component                  = Rail.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object Rail {
  @js.native
  @JSImport("semantic-ui-react", "Rail")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait RailProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: js.UndefOr[AsT] = js.native

    /** A rail can appear attached to the main viewport. */
    var attached: js.UndefOr[Boolean] = js.native

    /** Primary content. */
    var children: js.UndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: js.UndefOr[String] = js.native

    /** A rail can appear closer to the main viewport. */
    var close: js.UndefOr[Boolean | String] = js.native

    /** Shorthand for primary content. */
    var content: js.UndefOr[suiraw.SemanticShorthandContent] = js.native

    /** A rail can create a division between itself and a container. */
    var dividing: js.UndefOr[Boolean] = js.native

    /** A rail can attach itself to the inside of a container. */
    var internal: js.UndefOr[Boolean] = js.native

    /** A rail can be presented on the left or right side of a container. */
    var position: js.UndefOr[suiraw.SemanticFLOATS] = js.native

    /** A rail can have different sizes. */
    var size: js.UndefOr[suiraw.SemanticSIZES] = js.native
  }

  def props(
    q: Rail
  ): RailProps =
    rawprops(
      q.as,
      q.attached,
      q.className,
      q.clazz,
      q.close,
      q.content,
      q.dividing,
      q.internal,
      q.position,
      q.size
    )

  def rawprops(
    as:        js.UndefOr[AsC] = js.undefined,
    attached:  js.UndefOr[Boolean] = js.undefined,
    className: js.UndefOr[String] = js.undefined,
    clazz:     js.UndefOr[Css] = js.undefined,
    close:     js.UndefOr[RailClose] = js.undefined,
    content:   js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
    dividing:  js.UndefOr[Boolean] = js.undefined,
    internal:  js.UndefOr[Boolean] = js.undefined,
    position:  js.UndefOr[SemanticFloat] = js.undefined,
    size:      js.UndefOr[SemanticSize] = js.undefined
  ): RailProps = {
    val p = as.toJsObject[RailProps]
    attached.foreach(v => p.attached = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    close.toJs.foreach(v => p.close = v)
    content.toJs.foreach(v => p.content = v)
    dividing.foreach(v => p.dividing = v)
    internal.foreach(v => p.internal = v)
    position.toJs.foreach(v => p.position = v)
    size.toJs.foreach(v => p.size = v)
    p
  }

  private val component =
    JsFnComponent[RailProps, Children.Varargs](RawComponent)

  def apply(position: SemanticFloat, modifiers: TagMod*): Rail =
    new Rail(position = position, modifiers = modifiers)

}
