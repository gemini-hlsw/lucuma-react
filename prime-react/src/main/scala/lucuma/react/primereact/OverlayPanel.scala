// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.primereact

import cats.syntax.all.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.CtorType.PropsAndChildren
import japgolly.scalajs.react.component.Js.ComponentWithFacade
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.common.*
import org.scalajs.dom.*

import scalajs.js
import scalajs.js.annotation.JSImport

case class OverlayPanelRef(
  ref: Ref.ToJsComponent[
    OverlayPanel.OverlayPanelProps,
    Null,
    facade.React.Component[OverlayPanel.OverlayPanelProps, Null] & OverlayPanel.Facade
  ]
) {
  def toggle: ReactEvent => Callback = e => ref.get.map(_.fold(())(_.raw.toggle(e)))
  def show: ReactEvent => Callback   = e => ref.get.map(_.fold(())(_.raw.show(e)))
  def hide: Callback                 = ref.get.map(_.foldMap(_.raw.hide))
}

case class OverlayPanel(
  id:            js.UndefOr[String],
  closeOnEscape: js.UndefOr[Boolean],
  dismissable:   js.UndefOr[Boolean],
  onHide:        js.UndefOr[Callback],
  onShow:        js.UndefOr[Callback],
  modifiers:     Seq[TagMod]
) extends GenericComponentPACF[OverlayPanel.OverlayPanelProps, OverlayPanel, OverlayPanel.Facade]:
  override protected def cprops = OverlayPanel.props(this)
  override protected val component: ComponentWithFacade[OverlayPanel.OverlayPanelProps,
                                                        Null,
                                                        OverlayPanel.Facade,
                                                        PropsAndChildren
  ] =
    OverlayPanel.component

  override def addModifiers(modifiers: Seq[TagMod]): OverlayPanel =
    copy(modifiers = this.modifiers ++ modifiers)

  def withMods(mods: TagMod*) = addModifiers(mods)

object OverlayPanel {
  @js.native
  @JSImport("primereact/overlaypanel/overlaypanel.esm", "OverlayPanel")
  object RawOverlayPanel extends js.Object

  @js.native
  trait Facade extends js.Object {
    def show(event:   ReactEvent): Unit = js.native
    def hide: Unit                      = js.native
    def toggle(event: ReactEvent): Unit = js.native
  }

  @js.native
  trait OverlayPanelProps extends js.Object {
    var closeOnEscape: js.UndefOr[Boolean]                              = js.native
    var dismissable: js.UndefOr[Boolean]                                = js.native
    var onHide: js.UndefOr[js.Function1[ReactEventFrom[Element], Unit]] = js.native
    var onShow: js.UndefOr[js.Function1[ReactEventFrom[Element], Unit]] = js.native
  }

  private def props(pm: OverlayPanel): OverlayPanelProps = {
    val r = (new js.Object).asInstanceOf[OverlayPanelProps]
    pm.closeOnEscape.foreach(v => r.closeOnEscape = v)
    pm.dismissable.foreach(v => r.dismissable = v)
    pm.onHide.foreach(v => r.onHide = _ => v.runNow())
    pm.onShow.foreach(v => r.onShow = _ => v.runNow())
    r
  }

  def apply(
    id:            js.UndefOr[String] = js.undefined,
    closeOnEscape: js.UndefOr[Boolean] = js.undefined, // default: true
    dismissable:   js.UndefOr[Boolean] = js.undefined, // default: true
    onHide:        js.UndefOr[Callback] = js.undefined,
    onShow:        js.UndefOr[Callback] = js.undefined,
    modifiers:     Seq[TagMod] = Seq.empty
  )(content: TagMod*): OverlayPanel =
    new OverlayPanel(id, closeOnEscape, dismissable, onHide, onShow, modifiers ++ content)

  val component =
    JsComponent[OverlayPanelProps, Children.Varargs, Null](RawOverlayPanel).addFacade[Facade]

}
