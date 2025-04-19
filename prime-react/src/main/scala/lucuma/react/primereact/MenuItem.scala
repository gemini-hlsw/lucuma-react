// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.primereact

import cats.syntax.all.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.facade.React.Node
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.common.*

import scalajs.js
import scalajs.js.JSConverters.*

trait MenuItem extends js.Object

object MenuItem {

  val MenuSeparator: MenuItem = new MenuItem {
    val separator: Boolean = true
  }

  trait Separator extends MenuItem {
    var separator: Boolean
  }

  val Separator: MenuItem = js.Dynamic.literal(separator = true).asInstanceOf[MenuItem]

  // Not all properties work with all menu types.
  trait SubMenu extends MenuItem {
    val label: String
    val items: List[MenuItem]
    var icon: js.UndefOr[Any]         = js.undefined // Only seems to show for some menu types
    var disabled: js.UndefOr[Boolean] = js.undefined
    var expanded: js.UndefOr[Boolean] =
      js.undefined // Visibility of submenu - not sure how to use this.
    var visible: js.UndefOr[Boolean]  = js.undefined
    var className: js.UndefOr[String] = js.undefined
  }

  def SubMenu(
    label:    String,
    icon:     js.UndefOr[Icon] = js.undefined,
    disabled: js.UndefOr[Boolean] = js.undefined,
    expanded: js.UndefOr[Boolean] = js.undefined,
    visible:  js.UndefOr[Boolean] = js.undefined,
    clazz:    js.UndefOr[Css] = js.undefined
  )(items: MenuItem*): MenuItem = {
    val sm = js.Dynamic.literal(label = label, items = items.toJSArray).asInstanceOf[SubMenu]
    icon.foreach(i => sm.icon = i.toPrimeWithClass(PrimeStyles.MenuItemIcon))
    disabled.foreach(v => sm.disabled = v)
    expanded.foreach(v => sm.expanded = v)
    visible.foreach(v => sm.visible = v)
    clazz.foreach(v => sm.className = v.htmlClass)
    sm
  }

  trait Item extends MenuItem {
    val label: String
    var icon: js.UndefOr[Any]                        = js.undefined
    var command: js.UndefOr[js.Function1[Any, Unit]] = js.undefined
    var url: js.UndefOr[String]                      = js.undefined
    var disabled: js.UndefOr[Boolean]                = js.undefined
    var visible: js.UndefOr[Boolean]                 = js.undefined
    var target: js.UndefOr[String]                   = js.undefined // where to open linked document
    var className: js.UndefOr[String]                = js.undefined
  }

  // Not all properties work with all menu types.
  def Item(
    label:    String,
    icon:     js.UndefOr[Icon] = js.undefined,
    command:  js.UndefOr[Callback] = js.undefined,
    url:      js.UndefOr[String] = js.undefined,
    disabled: js.UndefOr[Boolean] = js.undefined,
    visible:  js.UndefOr[Boolean] = js.undefined,
    target:   js.UndefOr[String] = js.undefined,
    clazz:    js.UndefOr[Css] = js.undefined
  ): Item = {
    val l = js.Dynamic.literal(label = label).asInstanceOf[Item]
    icon.foreach(i => l.icon = i.toPrimeWithClass(PrimeStyles.MenuItemIcon))
    url.foreach(u => l.url = u)
    command.foreach(c => l.command = _ => c.runNow())
    disabled.foreach(v => l.disabled = v)
    visible.foreach(v => l.visible = v)
    target.foreach(v => l.target = v)
    clazz.foreach(v => l.className = v.htmlClass)
    l
  }

  trait Custom extends MenuItem {
    val node: js.Function2[Any, Any, Node]
    var className: js.UndefOr[String] = js.undefined
  }

  def Custom(
    node:  VdomNode,
    clazz: js.UndefOr[Css] = js.undefined
  ): MenuItem =
    val className = (clazz.toOption.orEmpty |+| PrimeStyles.MenuItemLink).htmlClass
    js.Dynamic
      .literal(template = (_: Any, _: Any) => node.rawNode, className = className)
      .asInstanceOf[Custom]
}
