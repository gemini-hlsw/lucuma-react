// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui.modules.dropdown

import scala.scalajs.js
import js.annotation._
import js.|
import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.html_<^._
import react.common._
import react.semanticui.{raw => suiraw}
import react.semanticui.raw._
import react.semanticui.elements.icon.Icon
import react.semanticui.elements.icon.Icon.IconProps

import react.semanticui.elements.flag.Flag.FlagProps
import react.semanticui.elements.flag.Flag
import react.semanticui.elements.label.Label.LabelProps
import react.semanticui.elements.label.Label
import react.semanticui.elements.image.Image.ImageProps
import react.semanticui.elements.image.Image
import react.semanticui._

final case class DropdownItem(
  as:                     MyUndefOr[AsC] = MyUndefOr.undefined,
  active:                 MyUndefOr[Boolean] = MyUndefOr.undefined,
  className:              MyUndefOr[String] = MyUndefOr.undefined,
  clazz:                  MyUndefOr[Css] = MyUndefOr.undefined,
  content:                MyUndefOr[ShorthandS[VdomNode]] = MyUndefOr.undefined,
  description:            MyUndefOr[String] = MyUndefOr.undefined,
  disabled:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  flag:                   MyUndefOr[ShorthandS[Flag]] = MyUndefOr.undefined,
  icon:                   MyUndefOr[ShorthandS[Icon]] = MyUndefOr.undefined,
  image:                  MyUndefOr[ShorthandS[Image]] = MyUndefOr.undefined,
  label:                  MyUndefOr[ShorthandS[Label]] = MyUndefOr.undefined,
  onClickE:               MyUndefOr[DropdownItem.OnClick] = MyUndefOr.undefined,
  onClick:                MyUndefOr[Callback] = MyUndefOr.undefined,
  selected:               MyUndefOr[Boolean] = MyUndefOr.undefined,
  text:                   MyUndefOr[String] = MyUndefOr.undefined,
  value:                  MyUndefOr[Boolean | Double | String] = MyUndefOr.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericComponentPAC[DropdownItem.DropdownItemProps, DropdownItem] {
  override protected def cprops                     = DropdownItem.props(this)
  override protected val component                  = DropdownItem.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
}

object DropdownItem {
  type OnClick = (ReactMouseEvent, DropdownItemProps) => Callback

  @js.native
  @JSImport("semantic-ui-react", "DropdownItem")
  object RawComponent extends js.Object

  @js.native
  trait DropdownItemProps extends js.Object {
    @JSBracketAccess
    def apply(key: String): js.Any = js.native

    @JSBracketAccess
    def update(key: String, v: js.Any): Unit = js.native

    /** An element type to render as (string or function). */
    var as: MyUndefOr[AsT] = js.native

    /** Style as the currently chosen item. */
    var active: MyUndefOr[Boolean] = js.native

    /** Primary content. */
    var children: MyUndefOr[React.Node] = js.native

    /** Additional classes. */
    var className: MyUndefOr[String] = js.native

    /** Shorthand for primary content. */
    var content: MyUndefOr[SemanticShorthandContent] = js.native

    /** Additional text with less emphasis. */
    var description: MyUndefOr[suiraw.SemanticShorthandItemS[String]] = js.native

    /** A dropdown item can be disabled. */
    var disabled: MyUndefOr[Boolean] = js.native

    /** Shorthand for Flag. */
    var flag: MyUndefOr[suiraw.SemanticShorthandItemS[FlagProps]] = js.native

    /** Shorthand for Icon. */
    var icon: MyUndefOr[suiraw.SemanticShorthandItemS[IconProps]] = js.native

    /** Shorthand for Image. */
    var image: MyUndefOr[suiraw.SemanticShorthandItemS[ImageProps]] = js.native

    /** Shorthand for Label. */
    var label: MyUndefOr[suiraw.SemanticShorthandItemS[LabelProps]] = js.native

    /**
     * Called on click.
     *
     * @param {SyntheticEvent}
     *   event - React's original SyntheticEvent.
     * @param {object}
     *   data - All props.
     */
    var onClick: MyUndefOr[js.Function2[ReactMouseEvent, DropdownItemProps, Unit]] = js.native

    /**
     * The item currently selected by keyboard shortcut. This is not the active item.
     */
    var selected: MyUndefOr[Boolean] = js.native

    /** Display text. */
    var text: MyUndefOr[SemanticShorthandContent] = js.native

    /** Stored value. */
    var value: MyUndefOr[Boolean | Double | String] = js.native
  }

  def props(
    q: DropdownItem
  ): DropdownItemProps = {
    val p = q.as.toJsObject[DropdownItemProps]
    q.as.toJs.foreach(v => p.as = v)
    q.active.foreach(v => p.active = v)
    (q.className, q.clazz).toJs.foreach(v => p.className = v)
    q.content.toJs.foreach(v => p.content = v)
    q.description.foreach(v => p.description = v)
    q.disabled.foreach(v => p.disabled = v)
    q.flag.toJs.foreach(v => p.flag = v)
    q.icon.toJs.foreach(v => p.icon = v)
    q.image.toJs.foreach(v => p.image = v)
    q.label.toJs.foreach(v => p.label = v)
    (q.onClickE, q.onClick).toJs.foreach(v => p.onClick = v)
    q.selected.foreach(v => p.selected = v)
    q.text.foreach(v => p.text = v)
    q.value.foreach(v => p.value = v)
    p
  }

  private val component =
    JsComponent[DropdownItemProps, Children.Varargs, Null](RawComponent)

  def apply(v: Boolean | Double | String): DropdownItem =
    new DropdownItem(value = v, modifiers = Seq(v.toString))

}
