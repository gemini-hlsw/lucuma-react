// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import org.scalajs.dom.HTMLElement
import react.common.*
import reactST.primereact.components.{Dialog => CDialog}
import reactST.primereact.dialogMod.DialogAppendToType
import reactST.primereact.primereactStrings

import scalajs.js

case class Dialog(
  onHide:          Callback,                                                     // use to set `visible` state/view to false
  visible:         Boolean,
  id:              js.UndefOr[String] = js.undefined,
  header:          js.UndefOr[VdomNode] = js.undefined,
  footer:          js.UndefOr[VdomNode] = js.undefined,
  position:        js.UndefOr[DialogPosition] = js.undefined,                    // default: center
  modal:           js.UndefOr[Boolean] = js.undefined,                           // default: false
  resizable:       js.UndefOr[Boolean] = js.undefined,                           // default: true
  draggable:       js.UndefOr[Boolean] = js.undefined,                           // default: true
  keepInViewport:  js.UndefOr[Boolean] = js.undefined,                           // default: true
  showHeader:      js.UndefOr[Boolean] = js.undefined,                           // default: true
  closable:        js.UndefOr[Boolean] = js.undefined,                           // default: true
  closeOnEscape:   js.UndefOr[Boolean] = js.undefined,                           // default: true
  dismissableMask: js.UndefOr[Boolean] = js.undefined,                           // default: false
  maximizable:     js.UndefOr[Boolean] = js.undefined,                           // default: false
  blockScroll:     js.UndefOr[Boolean] = js.undefined,                           // default: false
  focusOnShow:     js.UndefOr[Boolean] = js.undefined,                           // default: true
  maximized:       js.UndefOr[Boolean] = js.undefined,                           // default: false
  clazz:           js.UndefOr[Css] = js.undefined,
  headerClass:     js.UndefOr[Css] = js.undefined,
  contentClass:    js.UndefOr[Css] = js.undefined,
  maskClass:       js.UndefOr[Css] = js.undefined,
  appendTo:        js.UndefOr[Dialog.SelfPosition | HTMLElement] = js.undefined, // default: document.body
  baseZIndex:      js.UndefOr[Double] = js.undefined                             // default: 0
) extends ReactFnPropsWithChildren[Dialog](Dialog.component)

object Dialog {
  type SelfPosition = primereactStrings.self

  private val component =
    ScalaFnComponent
      .withHooks[Dialog]
      .withPropsChildren
      .render { (props, children) =>
        CDialog(props.onHide)
          .visible(props.visible)
          .applyOrNot(props.id, _.id(_))
          .applyOrNot(props.header, (c, p) => c.header(p.rawNode))
          .applyOrNot(props.footer, (c, p) => c.footer(p.rawNode))
          .applyOrNot(props.position, (c, p) => c.position(p.value))
          .applyOrNot(props.modal, _.modal(_))
          .applyOrNot(props.resizable, _.resizable(_))
          .applyOrNot(props.draggable, _.draggable(_))
          .applyOrNot(props.keepInViewport, _.keepInViewport(_))
          .applyOrNot(props.showHeader, _.showHeader(_))
          .applyOrNot(props.closable, _.closable(_))
          .applyOrNot(props.closeOnEscape, _.closeOnEscape(_))
          .applyOrNot(props.dismissableMask, _.dismissableMask(_))
          .applyOrNot(props.maximizable, _.maximizable(_))
          .applyOrNot(props.blockScroll, _.blockScroll(_))
          .applyOrNot(props.focusOnShow, _.focusOnShow(_))
          .applyOrNot(props.maximized, _.maximized(_))
          .applyOrNot(props.clazz, (c, p) => c.className(p.htmlClass))
          .applyOrNot(props.headerClass, (c, p) => c.headerClassName(p.htmlClass))
          .applyOrNot(props.contentClass, (c, p) => c.contentClassName(p.htmlClass))
          .applyOrNot(props.maskClass, (c, p) => c.maskClassName(p.htmlClass))
          .applyOrNot(props.appendTo, _.appendTo(_))
          .applyOrNot(props.baseZIndex, _.baseZIndex(_))(children)
      }
}
