// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.primereact

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.common.*
import lucuma.typed.primereact.components.Accordion as CAccordion

import scalajs.js

private[primereact] trait AccordionBase {
  val id: js.UndefOr[String]
  val rawActiveIndex: js.UndefOr[Double | js.Array[Double]]
  val multiple: js.UndefOr[Boolean]
  val expandIcon: js.UndefOr[String]   // default: "pi pi-chevron-right"
  val collapseIcon: js.UndefOr[String] // default: "pi pi-chevron-down"
  val clazz: js.UndefOr[Css]
  val onTabOpen: js.UndefOr[Int => Callback]
  val onTabClose: js.UndefOr[Int => Callback]
  val rawOnTabChange: js.UndefOr[Double | js.Array[Double] => Callback]
  val tabs: List[AccordionTab]
  val modifiers: Seq[TagMod]
}

object AccordionBase {
  private[primereact] val component =
    ScalaFnComponent[AccordionBase] { props =>
      CAccordion
        .applyOrNot(props.id, _.id(_))
        .applyOrNot(props.rawActiveIndex, _.activeIndex(_))
        .applyOrNot(props.multiple, _.multiple(_))
        .applyOrNot(props.expandIcon, _.expandIcon(_))
        .applyOrNot(props.collapseIcon, _.collapseIcon(_))
        .applyOrNot(props.clazz, (c, p) => c.className(p.htmlClass))
        .applyOrNot(props.onTabOpen, (c, p) => c.onTabOpen(e => p(e.index.toInt)))
        .applyOrNot(props.onTabClose, (c, p) => c.onTabClose(e => p(e.index.toInt)))
        .applyOrNot(props.rawOnTabChange, (c, p) => c.onTabChange(e => p(e.index)))(
          props.modifiers.toTagMod,
          props.tabs.toTagMod
        )
    }
}
