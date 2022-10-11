// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import react.common.*
import reactST.primereact.components.{Toolbar => CToolbar}

import scalajs.js

case class Toolbar(
  id:    js.UndefOr[String] = js.undefined,
  clazz: js.UndefOr[Css] = js.undefined,
  left:  js.UndefOr[VdomNode] = js.undefined,
  right: js.UndefOr[VdomNode] = js.undefined
) extends ReactFnProps[Toolbar](Toolbar.component)

object Toolbar {
  private val component = ScalaFnComponent[Toolbar] { props =>
    CToolbar
      .applyOrNot(props.id, _.id(_))
      .applyOrNot(props.clazz, (c, p) => c.className(p.htmlClass))
      .applyOrNot(props.left, (c, p) => c.left(p.rawNode))
      .applyOrNot(props.right, (c, p) => c.right(p.rawNode))
  }
}
