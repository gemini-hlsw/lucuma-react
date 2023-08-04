// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.primereact

import japgolly.scalajs.react.*
import japgolly.scalajs.react.facade.React.Node
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.common.*

import scalajs.js.annotation.JSImport
import scalajs.js

case class TabPanel(
  header:                 js.UndefOr[VdomNode] = js.undefined,
  disabled:               js.UndefOr[Boolean] = js.undefined,
  closable:               js.UndefOr[Boolean] = js.undefined, // if tab can be removed. default: false
  leftIcon:               js.UndefOr[String] =
    js.undefined, // left of header. Is applied as a CSS class (like primeicons)
  rightIcon:              js.UndefOr[String] =
    js.undefined, // right of header. Is applied as a CSS class (like primeicons)
  clazz:                  js.UndefOr[Css] = js.undefined,
  headerClass:            js.UndefOr[Css] = js.undefined,
  contentClass:           js.UndefOr[Css] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPAC[TabPanel.TabPanelProps, TabPanel] {
  override protected def cprops    = TabPanel.props(this)
  override protected val component = TabPanel.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
  def apply(mods:                      TagMod*)     = addModifiers(mods)
}

object TabPanel {
  @js.native
  @JSImport("primereact/tabview/tabview.esm", "TabPanel")
  object RawTabPanel extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait TabPanelProps extends js.Object {
    var header: js.UndefOr[Node]             = js.native
    var disabled: js.UndefOr[Boolean]        = js.native
    var closable: js.UndefOr[Boolean]        = js.native
    var leftIcon: js.UndefOr[String]         = js.native
    var rightIcon: js.UndefOr[String]        = js.native
    var className: js.UndefOr[String]        = js.native
    var headerClassName: js.UndefOr[String]  = js.native
    var contentClassName: js.UndefOr[String] = js.native
  }

  def props(q: TabPanel): TabPanelProps = {
    val p = (new js.Object).asInstanceOf[TabPanelProps]
    q.header.foreach(v => p.header = v.rawNode)
    q.disabled.foreach(v => p.disabled = v)
    q.closable.foreach(v => p.closable = v)
    q.leftIcon.foreach(v => p.leftIcon = v)
    q.rightIcon.foreach(v => p.rightIcon = v)
    q.clazz.foreach(v => p.className = v.htmlClass)
    q.headerClass.foreach(v => p.headerClassName = v.htmlClass)
    q.contentClass.foreach(v => p.contentClassName = v.htmlClass)
    p
  }

  private val component = JsFnComponent[TabPanelProps, Children.Varargs](RawTabPanel)
}
