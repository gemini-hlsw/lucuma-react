// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import react.common.*
import reactST.primereact.components.{Panel => CPanel}

import scalajs.js

case class Panel(
  id:         js.UndefOr[String] = js.undefined,
  header:     js.UndefOr[VdomNode] = js.undefined,
  toggleable: js.UndefOr[Boolean] =
    js.undefined, // Defines if panel can be expanded and collapsed. default: false
  collapsed: js.UndefOr[Boolean] =
    js.undefined, // If toggleable is true, need to set this via onToggle. default: false
  className:  js.UndefOr[String] = js.undefined,
  clazz:      js.UndefOr[Css] = js.undefined,
  onCollapse: js.UndefOr[Callback] = js.undefined,           // see comment above
  onExpand:   js.UndefOr[Callback] = js.undefined,           // see comment above
  onToggle:   js.UndefOr[Boolean => Callback] = js.undefined // see comment above
) extends ReactFnPropsWithChildren[Panel](Panel.component)

object Panel {
  private val component =
    ScalaFnComponent
      .withHooks[Panel]
      .withPropsChildren
      .render { (props, children) =>
        CPanel
          .applyOrNot(props.id, _.id(_))
          .applyOrNot(props.header, (c, p) => c.header(p.rawNode))
          .applyOrNot(props.toggleable, _.toggleable(_))
          .applyOrNot(props.collapsed, _.collapsed(_))
          .applyOrNot((props.className, props.clazz).toJs, _.className(_))
          .applyOrNot(props.onCollapse, (c, p) => c.onCollapse(_ => p))
          .applyOrNot(props.onExpand, (c, p) => c.onExpand(_ => p))
          .applyOrNot(props.onToggle, (c, p) => c.onToggle(toggleParms => p(toggleParms.value)))(
            children
          )
      }
}
