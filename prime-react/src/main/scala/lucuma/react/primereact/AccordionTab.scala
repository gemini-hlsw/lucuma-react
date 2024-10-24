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

case class AccordionTab(
  header:                 js.UndefOr[VdomNode] = js.undefined,
  disabled:               js.UndefOr[Boolean] = js.undefined,
  clazz:                  js.UndefOr[Css] = js.undefined,
  headerClass:            js.UndefOr[Css] = js.undefined,
  contentClass:           js.UndefOr[Css] = js.undefined,
  override val modifiers: Seq[TagMod] = Seq.empty
) extends GenericFnComponentPAC[AccordionTab.AccordionTabProps, AccordionTab] {
  override protected def cprops                     = AccordionTab.props(this)
  override protected val component                  = AccordionTab.component
  override def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
  def apply(mods:                      TagMod*)     = addModifiers(mods)
}

object AccordionTab {
  @js.native
  @JSImport("primereact/accordion/accordion.esm", "AccordionTab")
  object RawAccordionTab extends js.Function1[js.Any, js.Any] {
    def apply(i: js.Any): js.Any = js.native
  }

  @js.native
  trait AccordionTabProps extends js.Object {
    var header: js.UndefOr[Node]             = js.native
    var disabled: js.UndefOr[Boolean]        = js.native
    var className: js.UndefOr[String]        = js.native
    var headerClassName: js.UndefOr[String]  = js.native
    var contentClassName: js.UndefOr[String] = js.native
  }

  def props(q: AccordionTab): AccordionTabProps = {
    val p = (new js.Object).asInstanceOf[AccordionTabProps]
    q.header.foreach(v => p.header = v.rawNode)
    q.disabled.foreach(v => p.disabled = v)
    q.clazz.foreach(v => p.className = v.htmlClass)
    q.headerClass.foreach(v => p.headerClassName = v.htmlClass)
    q.contentClass.foreach(v => p.contentClassName = v.htmlClass)
    p
  }

  private val component = JsFnComponent[AccordionTabProps, Children.Varargs](RawAccordionTab)
}
