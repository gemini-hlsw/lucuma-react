package react.semanticui.modules.modal

import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.VdomNode
import japgolly.scalajs.react.facade.React
import react.common._
import react.semanticui._
import react.semanticui.{ raw => suiraw }
import japgolly.scalajs.react.vdom.TagMod

final case class ModalContent(
  as:                     js.UndefOr[AsC] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  content:                js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
  image:                  js.UndefOr[Boolean] = js.undefined,
  scrolling:              js.UndefOr[Boolean] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPAC[ModalContent.ModalContentProps, ModalContent] {
  override protected def cprops                     = ModalContent.props(this)
  override protected val component                  = ModalContent.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object ModalContent {
  @js.native
  @JSImport("semantic-ui-react", "ModalContent")
  object RawComponent extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait ModalContentProps extends js.Object {
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
    var content: js.UndefOr[suiraw.SemanticShorthandContent] = js.native

    /** A modal can contain image content. */
    var image: js.UndefOr[Boolean] = js.native

    /** A modal can use the entire size of the screen. */
    var scrolling: js.UndefOr[Boolean] = js.native
  }

  def props(q: ModalContent): ModalContentProps =
    rawprops(q.as, q.className, q.clazz, q.content, q.image, q.scrolling)

  def rawprops(
    as:        js.UndefOr[AsC] = js.undefined,
    className: js.UndefOr[String] = js.undefined,
    clazz:     js.UndefOr[Css] = js.undefined,
    content:   js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
    image:     js.UndefOr[Boolean] = js.undefined,
    scrolling: js.UndefOr[Boolean] = js.undefined
  ): ModalContentProps = {
    val p = as.toJsObject[ModalContentProps]
    as.toJs.foreach(v => p.as = v)
    (className, clazz).toJs.foreach(v => p.className = v)
    content.toJs.foreach(v => p.content = v)
    image.foreach(v => p.image = v)
    scrolling.foreach(v => p.scrolling = v)
    p
  }

  private val component =
    JsFnComponent[ModalContentProps, Children.Varargs](RawComponent)

  def apply(modifiers: TagMod*): ModalContent =
    new ModalContent(modifiers = modifiers)
}
