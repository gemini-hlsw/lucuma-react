// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import cats.syntax.all.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import react.common.*
import lucuma.typed.primereact.chipsChipsMod.ChipsAddEvent
import lucuma.typed.primereact.chipsChipsMod.ChipsChangeEvent
import lucuma.typed.primereact.chipsChipsMod.ChipsRemoveEvent
import lucuma.typed.primereact.components.{Chips => CChips}
import lucuma.typed.primereact.tooltipTooltipoptionsMod.{TooltipOptions => CTooltipOptions}

import scalajs.js
import scalajs.js.JSConverters.*

case class Chips(
  id:             js.UndefOr[String] = js.undefined,
  inputId:        js.UndefOr[String] = js.undefined,
  name:           js.UndefOr[String] = js.undefined,
  placeholder:    js.UndefOr[String] = js.undefined,
  value:          List[String] = List.empty,
  max:            js.UndefOr[Int] = js.undefined,
  disabled:       js.UndefOr[Boolean] = js.undefined,
  readOnly:       js.UndefOr[Boolean] = js.undefined,
  removable:      js.UndefOr[Boolean] = js.undefined,
  clazz:          js.UndefOr[Css] = js.undefined,
  tooltip:        js.UndefOr[String] = js.undefined,
  tooltipOptions: js.UndefOr[TooltipOptions] = js.undefined,
  ariaLabelledBy: js.UndefOr[String] = js.undefined,
  allowDuplicate: js.UndefOr[Boolean] = js.undefined,
  separator:      js.UndefOr[String] = js.undefined, // Currently only possible value is ","
  itemTemplate:   js.UndefOr[String => VdomNode] = js.undefined,
  keyfilter:      js.UndefOr[String] = js.undefined,
  addOnBlur:      js.UndefOr[Boolean] = js.undefined,
  onChange:       js.UndefOr[List[String] => Callback] = js.undefined,
  onChangeE:      js.UndefOr[(List[String], ReactEvent) => Callback] =
    js.undefined, // called after onChange
  onAdd:     js.UndefOr[String => Callback] = js.undefined,
  onAddE:    js.UndefOr[(String, ReactEvent) => Callback] = js.undefined, // called after onAdd
  onRemove:  js.UndefOr[String => Callback] = js.undefined,
  onRemoveE: js.UndefOr[(String, ReactEvent) => Callback] = js.undefined, // called after onRemove
  onFocus:   js.UndefOr[Callback] = js.undefined,
  onFocusE:  js.UndefOr[ReactFocusEvent => Callback] = js.undefined,      // called after onFocus
  onBlur:    js.UndefOr[Callback] = js.undefined,
  onBlurE:   js.UndefOr[ReactFocusEvent => Callback] = js.undefined,      // called after onBlur
  onKeyDown: js.UndefOr[ReactKeyboardEvent => Callback] = js.undefined,
  modifiers: Seq[TagMod] = Seq.empty
) extends ReactFnProps(Chips.component):
  def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
  def withMods(mods:          TagMod*)     = addModifiers(mods)

  private val addHandler: js.UndefOr[ChipsAddEvent => Callback] =
    ((props.onAdd.toOption, props.onAddE.toOption) match
      case (Some(onAdd), Some(onAddE)) =>
        (
          (e: ChipsAddEvent) =>
            onAdd(e.value.asInstanceOf[String]) >>
              onAddE(e.value.asInstanceOf[String], e.originalEvent)
        ).some
      case (Some(onAdd), None)         => ((e: ChipsAddEvent) => onAdd(e.value.asInstanceOf[String])).some
      case (None, Some(onAddE))        =>
        ((e: ChipsAddEvent) => onAddE(e.value.asInstanceOf[String], e.originalEvent)).some
      case _                           => none
    ).orUndefined

  private val removeHandler: js.UndefOr[ChipsRemoveEvent => Callback] =
    ((props.onRemove.toOption, props.onRemoveE.toOption) match
      case (Some(onRemove), Some(onRemoveE)) =>
        (
          (e: ChipsRemoveEvent) =>
            onRemove(e.value.asInstanceOf[String]) >>
              onRemoveE(e.value.asInstanceOf[String], e.originalEvent)
        ).some
      case (Some(onRemove), None)            =>
        ((e: ChipsRemoveEvent) => onRemove(e.value.asInstanceOf[String])).some
      case (None, Some(onRemoveE))           =>
        ((e: ChipsRemoveEvent) => onRemoveE(e.value.asInstanceOf[String], e.originalEvent)).some
      case _                                 => none
    ).orUndefined

  private val focusHandler: js.UndefOr[ReactFocusEvent => Callback] =
    ((props.onFocus.toOption, props.onFocusE.toOption) match
      case (Some(onFocus), Some(onFocusE)) => ((e: ReactFocusEvent) => onFocus >> onFocusE(e)).some
      case (Some(onFocus), None)           => ((_: ReactFocusEvent) => onFocus).some
      case (None, Some(onFocusE))          => ((e: ReactFocusEvent) => onFocusE(e)).some
      case _                               => none
    ).orUndefined

  private val blurHandler: js.UndefOr[ReactFocusEvent => Callback] =
    ((props.onBlur.toOption, props.onBlurE.toOption) match
      case (Some(onBlur), Some(onBlurE)) => ((e: ReactFocusEvent) => onBlur >> onBlurE(e)).some
      case (Some(onBlur), None)          => ((_: ReactFocusEvent) => onBlur).some
      case (None, Some(onBlurE))         => ((e: ReactFocusEvent) => onBlurE(e)).some
      case _                             => none
    ).orUndefined

object Chips:
  private val component = ScalaFnComponent[Chips] { props =>
    val changeHandler: ChipsChangeEvent => Callback =
      parms =>
        val a = parms.value.asInstanceOf[js.Array[String]].toList
        props.onChange.toOption.map(_(a)).getOrElse(Callback.empty) >>
          props.onChangeE.toOption.map(_(a, parms.originalEvent.get)).getOrElse(Callback.empty)

    CChips
      .value(props.value.toJSArray)
      .onChange(changeHandler)
      .applyOrNot(props.id, _.id(_))
      .applyOrNot(props.inputId, _.inputId(_))
      .applyOrNot(props.name, _.name(_))
      .applyOrNot(props.placeholder, _.placeholder(_))
      .applyOrNot(props.max, _.max(_))
      .applyOrNot(props.disabled, _.disabled(_))
      .applyOrNot(props.readOnly, _.readOnly(_))
      .applyOrNot(props.removable, _.removable(_))
      .applyOrNot(props.clazz, (c, p) => c.className(p.htmlClass))
      .applyOrNot(props.tooltip, _.tooltip(_))
      .applyOrNot(props.tooltipOptions, (c, p) => c.tooltipOptions(p.asInstanceOf[CTooltipOptions]))
      .applyOrNot(props.ariaLabelledBy, _.ariaLabelledBy(_))
      .applyOrNot(props.allowDuplicate, _.allowDuplicate(_))
      .applyOrNot(props.separator, _.separator(_))
      .applyOrNot(
        props.itemTemplate,
        (c, fn) => c.itemTemplate(v => fn(v.asInstanceOf[String]).rawNode)
      )
      .applyOrNot(props.keyfilter, _.keyfilter(_))
      .applyOrNot(props.addOnBlur, _.addOnBlur(_))
      .applyOrNot(props.addHandler, _.onAdd(_))
      .applyOrNot(props.removeHandler, _.onRemove(_))
      .applyOrNot(props.focusHandler, _.onFocus(_))
      .applyOrNot(props.blurHandler, _.onBlur(_))
      .applyOrNot(props.onKeyDown, _.onKeyDown(_))(props.modifiers.toTagMod)
  }
