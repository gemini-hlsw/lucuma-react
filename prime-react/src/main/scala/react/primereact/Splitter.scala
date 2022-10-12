// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import react.common.*
import reactST.primereact.components.{Splitter => CSplitter}

import scalajs.js

case class Splitter(
  id:           js.UndefOr[String] = js.undefined,
  clazz:        js.UndefOr[Css] = js.undefined,
  layout:       js.UndefOr[Layout] = js.undefined, // default: Horizontal
  gutterSize:   js.UndefOr[Int] = js.undefined,    // size of divider in px, default: 4
  stateKey:     js.UndefOr[String] = js.undefined, // key for stateful storage
  stateStorage: js.UndefOr[StateStorage] =
    js.undefined, // provide `stateKey` to enable statefulness. Default: Session
  onResizeEnd: js.UndefOr[(Double, Double) => Callback] = js.undefined
)(val panel1:  SplitterPanel, val panel2: SplitterPanel)
    extends ReactFnProps[Splitter](Splitter.component)

object Splitter {
  private val component = ScalaFnComponent[Splitter] { props =>
    CSplitter
      .applyOrNot(props.id, _.id(_))
      .applyOrNot(props.clazz, (c, p) => c.className(p.htmlClass))
      .applyOrNot(props.layout, (c, p) => c.layout(p.value))
      .applyOrNot(props.gutterSize, (c, p) => c.gutterSize(p.toDouble))
      .applyOrNot(props.stateKey, _.stateKey(_))
      .applyOrNot(props.stateStorage, (c, p) => c.stateStorage(p.value))
      .applyOrNot(props.onResizeEnd, (c, p) => c.onResizeEnd(rp => p(rp.sizes(0), rp.sizes(1))))(
        props.panel1,
        props.panel2
      )
  }
}
