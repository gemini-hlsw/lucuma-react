// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.elements.label

import scala.scalajs.js
import js.annotation._
import js.|
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import react.common._
import react.semanticui._
import react.semanticui.{raw => suiraw}

import react.semanticui.elements.icon.Icon
import react.semanticui.elements.icon.Icon.IconProps
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.vdom.VdomNode

final case class Label(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  active:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  attached:               MyUndefOr[LabelAttached] = MyUndefOr.undefined,
  basic:                  MyUndefOr[Boolean] = MyUndefOr.undefined,
  child:                  MyUndefOr[React.Node] = MyUndefOr.undefined,
  circular:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  color:                  MyUndefOr[SemanticColor] = MyUndefOr.undefined,
  content:                MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
  corner:                 MyUndefOr[LabelCorner] = MyUndefOr.undefined,
  detail:                 MyUndefOr[LabelDetail] = MyUndefOr.undefined,
  empty:                  MyUndefOr[Boolean] = MyUndefOr.undefined,
  floating:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  horizontal:             MyUndefOr[Boolean] = MyUndefOr.undefined,
  icon:                   MyUndefOr[ShorthandS[Icon]] = MyUndefOr.undefined,
  image:                  MyUndefOr[Boolean] = MyUndefOr.undefined,
  onClickE:               MyUndefOr[Label.OnClick] = MyUndefOr.undefined,
  onClick:                MyUndefOr[Callback] = MyUndefOr.undefined,
  onRemove:               MyUndefOr[Label.OnClick] = MyUndefOr.undefined,
  pointing:               MyUndefOr[LabelPointing] = MyUndefOr.undefined,
  prompt:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  removeIcon:             MyUndefOr[ShorthandS[Icon]] = MyUndefOr.undefined,
  ribbon:                 MyUndefOr[LabelRibbon] = MyUndefOr.undefined,
  size:                   MyUndefOr[SemanticSize] = MyUndefOr.undefined,
  tag:                    MyUndefOr[Boolean] = MyUndefOr.undefined,
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

    var as: MyUndefOr[AsT]                                                             = js.native
    var active: MyUndefOr[Boolean]                                                     = js.native
    var attached: MyUndefOr[String]                                                    = js.native
    var basic: MyUndefOr[Boolean]                                                      = js.native
    var children: MyUndefOr[React.Node]                                                = js.native
    var circular: MyUndefOr[Boolean]                                                   = js.native
    var className: MyUndefOr[String]                                                   = js.native
    var color: MyUndefOr[suiraw.SemanticCOLORS]                                        = js.native
    var content: MyUndefOr[suiraw.SemanticShorthandContent]                            = js.native
    var corner: MyUndefOr[Boolean | String]                                            = js.native
    var detail: MyUndefOr[suiraw.SemanticShorthandItemS[LabelDetail.LabelDetailProps]] =
      js.native
    var empty: MyUndefOr[js.Any]                                                       = js.native
    var floating: MyUndefOr[Boolean]                                                   = js.native
    var horizontal: MyUndefOr[Boolean]                                                 = js.native
    var icon: MyUndefOr[suiraw.SemanticShorthandItemS[IconProps]]                      =
      js.native
    var image: MyUndefOr[js.Any]                                                       = js.native
    var onClick: MyUndefOr[js.Function2[ReactMouseEvent, LabelProps, Unit]]            =
      js.native
    var onRemove: MyUndefOr[js.Function2[ReactMouseEvent, LabelProps, Unit]]           =
      js.native
    var pointing: MyUndefOr[Boolean | String]                                          = js.native

    /** A label can prompt for an error in your forms. */
    var prompt: MyUndefOr[Boolean]
    var removeIcon: MyUndefOr[suiraw.SemanticShorthandItemS[IconProps]] =
      js.native
    var ribbon: MyUndefOr[Boolean | String]                             = js.native
    var size: MyUndefOr[suiraw.SemanticSIZES]                           = js.native
    var tag: MyUndefOr[Boolean]                                         = js.native
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
