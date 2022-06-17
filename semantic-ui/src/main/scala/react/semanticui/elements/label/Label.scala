package react.semanticui.elements.label

import scala.scalajs.js
import js.annotation._
import js.|
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import react.common._
import react.semanticui._
import react.semanticui.{ raw => suiraw }

import react.semanticui.elements.icon.Icon
import react.semanticui.elements.icon.Icon.IconProps
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.vdom.VdomNode

final case class Label(
  as:                     js.UndefOr[AsC] = js.undefined,
  active:                 js.UndefOr[Boolean] = js.undefined,
  attached:               js.UndefOr[LabelAttached] = js.undefined,
  basic:                  js.UndefOr[Boolean] = js.undefined,
  child:                  js.UndefOr[React.Node] = js.undefined,
  circular:               js.UndefOr[Boolean] = js.undefined,
  className:              js.UndefOr[String] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  color:                  js.UndefOr[SemanticColor] = js.undefined,
  content:                js.UndefOr[ShorthandS[VdomNode]] = js.undefined,
  corner:                 js.UndefOr[LabelCorner] = js.undefined,
  detail:                 js.UndefOr[LabelDetail] = js.undefined,
  empty:                  js.UndefOr[Boolean] = js.undefined,
  floating:               js.UndefOr[Boolean] = js.undefined,
  horizontal:             js.UndefOr[Boolean] = js.undefined,
  icon:                   js.UndefOr[ShorthandS[Icon]] = js.undefined,
  image:                  js.UndefOr[Boolean] = js.undefined,
  onClickE:               js.UndefOr[Label.OnClick] = js.undefined,
  onClick:                js.UndefOr[Callback] = js.undefined,
  onRemove:               js.UndefOr[Label.OnClick] = js.undefined,
  pointing:               js.UndefOr[LabelPointing] = js.undefined,
  prompt:                 js.UndefOr[Boolean] = js.undefined,
  removeIcon:             js.UndefOr[ShorthandS[Icon]] = js.undefined,
  ribbon:                 js.UndefOr[LabelRibbon] = js.undefined,
  size:                   js.UndefOr[SemanticSize] = js.undefined,
  tag:                    js.UndefOr[Boolean] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[Label.LabelProps, Label] {
  override protected def cprops                     = Label.props(this)
  override protected val component                  = Label.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object Label {
  type OnClick = (ReactMouseEvent, LabelProps) => Callback

  @js.native
  @JSImport("semantic-ui-react", "Label")
  object RawComponent extends js.Object

  @js.native
  trait LabelProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    var as: js.UndefOr[AsT]                                                             = js.native
    var active: js.UndefOr[Boolean]                                                     = js.native
    var attached: js.UndefOr[String]                                                    = js.native
    var basic: js.UndefOr[Boolean]                                                      = js.native
    var children: js.UndefOr[React.Node]                                                = js.native
    var circular: js.UndefOr[Boolean]                                                   = js.native
    var className: js.UndefOr[String]                                                   = js.native
    var color: js.UndefOr[suiraw.SemanticCOLORS]                                        = js.native
    var content: js.UndefOr[suiraw.SemanticShorthandContent]                            = js.native
    var corner: js.UndefOr[Boolean | String]                                            = js.native
    var detail: js.UndefOr[suiraw.SemanticShorthandItemS[LabelDetail.LabelDetailProps]] =
      js.native
    var empty: js.UndefOr[js.Any]                                                       = js.native
    var floating: js.UndefOr[Boolean]                                                   = js.native
    var horizontal: js.UndefOr[Boolean]                                                 = js.native
    var icon: js.UndefOr[suiraw.SemanticShorthandItemS[IconProps]]                      =
      js.native
    var image: js.UndefOr[js.Any]                                                       = js.native
    var onClick: js.UndefOr[js.Function2[ReactMouseEvent, LabelProps, Unit]]            =
      js.native
    var onRemove: js.UndefOr[js.Function2[ReactMouseEvent, LabelProps, Unit]]           =
      js.native
    var pointing: js.UndefOr[Boolean | String]                                          = js.native

    /** A label can prompt for an error in your forms. */
    var prompt: js.UndefOr[Boolean]
    var removeIcon: js.UndefOr[suiraw.SemanticShorthandItemS[IconProps]] =
      js.native
    var ribbon: js.UndefOr[Boolean | String]                             = js.native
    var size: js.UndefOr[suiraw.SemanticSIZES]                           = js.native
    var tag: js.UndefOr[Boolean]                                         = js.native
  }

  def props(
    q: Label
  ): LabelProps = {
    val p = q.as.toJsObject[LabelProps]
    q.as.toJs.foreach(v => p.as = v)
    q.active.foreach(v => p.active = v)
    q.attached.toJs.foreach(v => p.attached = v)
    q.basic.foreach(v => p.basic = v)
    q.child.foreach(v => p.children = v)
    q.circular.foreach(v => p.circular = v)
    (q.className, q.clazz).toJs.foreach(v => p.className = v)
    q.color.toJs.foreach(v => p.color = v)
    q.content.toJs.foreach(v => p.content = v)
    q.corner.toJs.foreach(v => p.corner = v)
    q.detail.map(_.props).foreach(v => p.detail = v)
    q.empty.map(_.asInstanceOf[js.Any]).foreach(v => p.empty = v)
    q.floating.foreach(v => p.floating = v)
    q.horizontal.foreach(v => p.horizontal = v)
    q.icon.toJs.foreach(v => p.icon = v)
    q.image.map(_.asInstanceOf[js.Any]).foreach(v => p.image = v)
    (q.onClickE, q.onClick).toJs.foreach(v => p.onClick = v)
    q.onRemove.toJs.foreach(v => p.onRemove = v)
    q.pointing.toJs.foreach(v => p.pointing = v)
    q.prompt.foreach(v => p.prompt = v)
    q.removeIcon.toJs.foreach(v => p.removeIcon = v)
    q.ribbon.toJs.foreach(v => p.ribbon = v)
    q.size.toJs.foreach(v => p.size = v)
    q.tag.foreach(v => p.tag = v)
    p
  }

  private val component =
    JsComponent[LabelProps, Children.Varargs, Null](RawComponent)

  def apply(modifiers: TagMod*): Label =
    new Label(modifiers = modifiers)
}
