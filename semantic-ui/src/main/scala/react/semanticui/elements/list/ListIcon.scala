package react.semanticui.elements.list

import japgolly.scalajs.react._
import react.common._
import react.semanticui._
import react.semanticui.{ raw => suiraw }
import react.semanticui.elements.icon._
import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react.vdom.TagMod

final case class ListIcon(
  as:                     js.UndefOr[AsC] = js.undefined,
  bordered:               js.UndefOr[Boolean] = js.undefined,
  circular:               js.UndefOr[Boolean] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  color:                  js.UndefOr[SemanticColor] = js.undefined,
  corner:                 js.UndefOr[IconCorner] = js.undefined,
  disabled:               js.UndefOr[Boolean] = js.undefined,
  fitted:                 js.UndefOr[Boolean] = js.undefined,
  flipped:                js.UndefOr[IconFlip] = js.undefined,
  inverted:               js.UndefOr[Boolean] = js.undefined,
  link:                   js.UndefOr[Boolean] = js.undefined,
  loading:                js.UndefOr[Boolean] = js.undefined,
  name:                   js.UndefOr[suiraw.SemanticICONS] = js.undefined,
  rotated:                js.UndefOr[IconRotated] = js.undefined,
  size:                   js.UndefOr[SemanticSize] = js.undefined,
  ariaLabel:              js.UndefOr[String] = js.undefined,
  verticalAlign:          js.UndefOr[SemanticVerticalAlignment] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPA[ListIcon.ListIconProps, ListIcon] {
  override protected def cprops                     = ListIcon.props(this)
  override protected val component                  = ListIcon.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object ListIcon {
  @js.native
  @JSImport("semantic-ui-react", "ListIcon")
  object RawComponent extends js.Object

  @js.native
  trait ListIconProps extends Icon.IconProps {

    /** An element inside a list can be vertically aligned. */
    var verticalAlign: js.UndefOr[suiraw.SemanticVERTICALALIGNMENTS] = js.native
  }

  def props(
    q: ListIcon
  ): ListIconProps = {
    val p = q.as.toJsObject[ListIconProps]
    q.as.toJs.foreach(v => p.as = v)
    q.bordered.foreach(v => p.bordered = v)
    q.circular.foreach(v => p.circular = v)
    (q.className, q.clazz).toJs.foreach(v => p.className = v)
    q.color.toJs.foreach(v => p.color = v)
    q.corner.toJs.foreach(v => p.corner = v)
    q.disabled.foreach(v => p.disabled = v)
    q.fitted.foreach(v => p.fitted = v)
    q.flipped.toJs.foreach(v => p.flipped = v)
    q.inverted.foreach(v => p.inverted = v)
    q.link.foreach(v => p.link = v)
    q.loading.foreach(v => p.loading = v)
    q.name.foreach(v => p.name = v)
    q.rotated.toJs.foreach(v => p.rotated = v)
    q.size.toJs.foreach(v => p.size = v)
    p.`aria-label` = q.ariaLabel
    q.verticalAlign.toJs.foreach(v => p.verticalAlign = v)
    p
  }

  private val component =
    JsComponent[ListIconProps, Children.None, Null](RawComponent)

  def apply(name: String): ListIcon =
    new ListIcon(as = js.undefined,
                 bordered = js.undefined,
                 circular = js.undefined,
                 className = js.undefined,
                 clazz = js.undefined,
                 color = js.undefined,
                 corner = js.undefined,
                 disabled = js.undefined,
                 fitted = js.undefined,
                 flipped = js.undefined,
                 inverted = js.undefined,
                 link = js.undefined,
                 loading = js.undefined,
                 name = name,
                 rotated = js.undefined,
                 size = js.undefined,
                 ariaLabel = js.undefined,
                 verticalAlign = js.undefined
    )
}
