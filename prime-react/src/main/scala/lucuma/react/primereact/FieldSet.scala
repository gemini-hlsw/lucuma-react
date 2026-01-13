// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.primereact

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.common.*
import lucuma.typed.primereact.components.Fieldset as CFieldSet

import scalajs.js

final case class FieldSet(
  id:           js.UndefOr[String] = js.undefined,
  legend:       js.UndefOr[VdomNode] = js.undefined,
  toggleable:   js.UndefOr[Boolean] =
    js.undefined, // Defines if panel can be expanded and collapsed. default: false
  collapsed:    js.UndefOr[Boolean] =
    js.undefined, // I think this is just the initial state. default: false
  clazz:        js.UndefOr[Css] = js.undefined,
  collapseIcon: js.UndefOr[Icon] = js.undefined,
  expandIcon:   js.UndefOr[Icon] = js.undefined,
  onCollapse:   js.UndefOr[Callback] = js.undefined,
  onExpand:     js.UndefOr[Callback] = js.undefined,
  onToggle:     js.UndefOr[Boolean => Callback] = js.undefined,
  modifiers:    Seq[TagMod] = Seq.empty
) extends ReactFnPropsWithChildren[FieldSet](FieldSet.component):
  def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)

  // You can add content to the fieldset as children without this method, but if you want
  // to add event handlers or other attributes, you'll need to use the modifiers.
  def withMods(mods: TagMod*) = addModifiers(mods)

object FieldSet:
  private val component =
    ScalaFnComponent
      .withHooks[FieldSet]
      .withPropsChildren
      .render: (props, children) =>
        CFieldSet
          .applyOrNot(props.id, _.id(_))
          .applyOrNot(props.legend, (c, p) => c.legend(p.rawNode))
          .applyOrNot(props.toggleable, _.toggleable(_))
          .applyOrNot(props.collapsed, _.collapsed(_))
          .applyOrNot(props.clazz, (c, p) => c.className(p.htmlClass))
          .applyOrNot(props.collapseIcon, (c, p) => c.collapseIcon(p.toPrime))
          .applyOrNot(props.expandIcon, (c, p) => c.expandIcon(p.toPrime))
          .applyOrNot(props.onCollapse, (c, p) => c.onCollapse(_ => p))
          .applyOrNot(props.onExpand, (c, p) => c.onExpand(_ => p))
          .applyOrNot(props.onToggle, (c, p) => c.onToggle(toggleParms => p(toggleParms.value)))(
            props.modifiers.toTagMod,
            children
          )
