// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import cats.Id
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import react.common.*

import scalajs.js

case class Accordion(
  id:           js.UndefOr[String] = js.undefined,
  activeIndex:  js.UndefOr[Int] = js.undefined,
  expandIcon:   js.UndefOr[String] = js.undefined,
  collapseIcon: js.UndefOr[String] = js.undefined,
  clazz:        js.UndefOr[Css] = js.undefined,
  onTabOpen:    js.UndefOr[Int => Callback] = js.undefined,
  onTabClose:   js.UndefOr[Int => Callback] = js.undefined,
  onTabChange:  js.UndefOr[Int => Callback] = js.undefined,
  tabs:         List[AccordionTab] = List.empty
) extends ReactFnProps[AccordionBase](AccordionBase.component)
    with AccordionBase {

  override val rawActiveIndex = activeIndex.map(_.toDouble)
  override val multiple       = false

  def apply(tab: AccordionTab, moreTabs: AccordionTab*): Accordion =
    copy(tabs = (tabs :+ tab) ++ moreTabs)
}
