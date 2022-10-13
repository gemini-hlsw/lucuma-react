// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import cats.Eq
import cats.derived.*
import cats.syntax.all.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import org.scalajs.dom.*
import react.common.*
import reactST.primereact.components.{Sidebar => CSidebar}
import reactST.primereact.primereactStrings.bottom
import reactST.primereact.primereactStrings.left
import reactST.primereact.primereactStrings.right
import reactST.primereact.primereactStrings.top

import scalajs.js
import scalajs.js.JSConverters.*

case class Sidebar(
  visible:     Boolean,
  onHide:      Callback,
  id:          js.UndefOr[String] = js.undefined,
  position:    js.UndefOr[Sidebar.Position] = js.undefined, // default: Left
  size:        js.UndefOr[Sidebar.Size] = js.undefined,     // default: Small, but without CSS class
  dismissable: js.UndefOr[Boolean] =
    js.undefined, // whether to dismiss with outside click. default: true
  closeOnEscape: js.UndefOr[Boolean] = js.undefined,                    // default: true
  modal:         js.UndefOr[Boolean] = js.undefined,                    // add a modal layer behind sidebar? default: true
  fullScreen:    js.UndefOr[Boolean] = js.undefined,                    // adds a close icon. default: false
  blockScroll:   js.UndefOr[Boolean] = js.undefined,                    // block scrolling of document. default: false
  showCloseIcon: js.UndefOr[Boolean] = js.undefined,                    // default: true
  icons:         js.UndefOr[List[Button]] = js.undefined,               // Should be buttons with an icon but no label
  baseZIndex:    js.UndefOr[Int] = js.undefined,                        // Base zIndex to use when layering. default: 0
  appendTo:      js.UndefOr[SelfPosition | HTMLElement] = js.undefined, // default: document.body
  clazz:         js.UndefOr[Css] = js.undefined,
  maskClass:     js.UndefOr[Css] = js.undefined,
  onShow:        js.UndefOr[Callback] = js.undefined,
  modifiers:     Seq[TagMod] = Seq.empty
) extends ReactFnPropsWithChildren[Sidebar](Sidebar.component) {
  def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)

  // You can add content to the sidebar as children without this method, but if you want
  // to add event handlers or other attributes, you'll need to use the modifiers.
  def withMods(mods: TagMod*) = addModifiers(mods)
}

object Sidebar {
  enum Position(val value: bottom | left | right | top) derives Eq:
    case Bottom extends Position(bottom)
    case Left   extends Position(left)
    case Right  extends Position(right)
    case Top    extends Position(top)

  enum Size(val cls: Css) derives Eq:
    case Small  extends Size(PrimeStyles.SidebarSmall)
    case Medium extends Size(PrimeStyles.SidebarMedium)
    case Large  extends Size(PrimeStyles.SidebarLarge)

  private val component =
    ScalaFnComponent.withHooks[Sidebar].withPropsChildren.render { (props, children) =>
      val fullCss = props.clazz.toOption.orEmpty |+| props.size.toOption.map(_.cls).orEmpty

      CSidebar(props.onHide)
        .visible(props.visible)
        .applyOrNot(props.id, _.id(_))
        .applyOrNot(props.position, (c, p) => c.position(p.value))
        .applyOrNot(props.dismissable, _.dismissable(_))
        .applyOrNot(props.closeOnEscape, _.closeOnEscape(_))
        .applyOrNot(props.modal, _.modal(_))
        .applyOrNot(props.fullScreen, _.fullScreen(_))
        .applyOrNot(props.blockScroll, _.blockScroll(_))
        .applyOrNot(props.showCloseIcon, _.showCloseIcon(_))
        .applyOrNot(
          props.icons,
          (c, p) =>
            c.icons(
              p.map(b => b.copy(clazz = b.clazz.toOption.orEmpty |+| PrimeStyles.SidebarIcon))
                .toReactFragment
                .rawNode
            )
        )
        .applyOrNot(props.baseZIndex, _.baseZIndex(_))
        .applyOrNot(props.appendTo, _.appendTo(_))
        .applyOrNot(fullCss, (c, p) => c.className(p.htmlClass))
        .applyOrNot(props.maskClass, (c, p) => c.maskClassName(p.htmlClass))
        .applyOrNot(props.onShow, _.onShow(_))(
          props.modifiers.toTagMod,
          children
        )
    }

}
