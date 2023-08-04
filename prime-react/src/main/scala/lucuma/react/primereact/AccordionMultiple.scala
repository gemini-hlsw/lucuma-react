// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.primereact

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.common.*

import scalajs.js
import scalajs.js.JSConverters.*

// Accordion which allows multiple tabs to be open.
case class AccordionMultiple(
  id:            js.UndefOr[String] = js.undefined,
  activeIndices: js.UndefOr[List[Int]] = js.undefined,
  expandIcon:    js.UndefOr[String] = js.undefined,
  collapseIcon:  js.UndefOr[String] = js.undefined,
  className:     js.UndefOr[String] = js.undefined,
  clazz:         js.UndefOr[Css] = js.undefined,
  onTabOpen:     js.UndefOr[Int => Callback] = js.undefined,
  onTabClose:    js.UndefOr[Int => Callback] = js.undefined,
  onTabChange:   js.UndefOr[List[Int] => Callback] = js.undefined,
  tabs:          List[AccordionTab] = List.empty,
  modifiers:     Seq[TagMod] = Seq.empty
) extends ReactFnProps[AccordionBase](AccordionBase.component)
    with AccordionBase {

  def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
  def withMods(mods:          TagMod*)     = addModifiers(mods)

  override val rawActiveIndex = activeIndices.map(_.map(_.toDouble).toJSArray)
  override val rawOnTabChange =
    onTabChange.map(
      _.compose[Double | js.Array[Double]](_.asInstanceOf[js.Array[Int]].toList)
    )
  override val multiple       = true

  def apply(tab: AccordionTab, moreTabs: AccordionTab*): AccordionMultiple =
    copy(tabs = (tabs :+ tab) ++ moreTabs)
}
