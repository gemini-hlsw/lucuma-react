// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import cats.Eq
import cats.syntax.all.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import react.common.*
import react.primereact.SelectItem.*
import reactST.primereact.components.{MultiSelect => CMultiSelect}
import reactST.primereact.multiselectMultiselectMod.MultiSelectAllParams
import reactST.primereact.multiselectMultiselectMod.MultiSelectChangeParams
import reactST.primereact.multiselectMultiselectMod.MultiSelectDisplayType
import reactST.primereact.multiselectMultiselectMod.MultiSelectFilterParams
import reactST.primereact.tooltipTooltipoptionsMod.{TooltipOptions => CTooltipOptions}

import scalajs.js
import scalajs.js.JSConverters.*

case class MultiSelect[A](
  id:                 js.UndefOr[String] = js.undefined,
  name:               js.UndefOr[String] = js.undefined,
  value:              List[A] = List.empty,
  options:            List[SelectItem[A]] = List.empty,
  clazz:              js.UndefOr[Css] = js.undefined,
  panelClass:         js.UndefOr[Css] = js.undefined,
  scrollHeight:       js.UndefOr[String] = js.undefined,
  placeholder:        js.UndefOr[String] = js.undefined,
  fixedPlaceholder:   js.UndefOr[Boolean] = js.undefined,
  disabled:           js.UndefOr[Boolean] = js.undefined,
  showClear:          js.UndefOr[Boolean] = js.undefined,
  filter:             js.UndefOr[Boolean] = js.undefined,
  resetFilterOnHide:  js.UndefOr[Boolean] = js.undefined,
  dataKey:            js.UndefOr[String] = js.undefined,
  inputId:            js.UndefOr[String] = js.undefined,
  tooltip:            js.UndefOr[String] = js.undefined,
  tooltipOptions:     js.UndefOr[TooltipOptions] = js.undefined,
  itemTemplate:       js.UndefOr[A => VdomNode] = js.undefined,
  // filterTemplate:      js.UndefOr[A => VdomNode] = js.undefined,
  // optionGroupTemplate:      js.UndefOr[A => VdomNode] = js.undefined,
  // selectedItemTemplate:      js.UndefOr[A => VdomNode] = js.undefined,
  // panelHeaderTemplate:      js.UndefOr[A => VdomNode] = js.undefined,
  // panelFooterTemplate:      js.UndefOr[A => VdomNode] = js.undefined,
  maxSelectedLabels:  js.UndefOr[Int] = js.undefined,
  selectionLimit:     js.UndefOr[Int] = js.undefined,
  selectedItemsLabel: js.UndefOr[String] = js.undefined,
  ariaLabelledBy:     js.UndefOr[String] = js.undefined,
  display:            js.UndefOr[MultiSelect.Display] = js.undefined,
  dropdownIcon:       js.UndefOr[String] = js.undefined,
  removeIcon:         js.UndefOr[String] = js.undefined,
  showSelectAll:      js.UndefOr[Boolean] = js.undefined,
  selectAll:          js.UndefOr[Boolean] = js.undefined,
  onChange:           js.UndefOr[List[A] => Callback] = js.undefined,
  onChangeE:          js.UndefOr[(List[A], ReactEvent) => Callback] = js.undefined, // called after onChange
  onFocus:            js.UndefOr[Callback] = js.undefined,
  onFocusE:           js.UndefOr[ReactFocusEvent => Callback] = js.undefined,
  onBlur:             js.UndefOr[Callback] = js.undefined,
  onBlurE:            js.UndefOr[ReactFocusEvent => Callback] = js.undefined,
  onShow:             js.UndefOr[Callback] = js.undefined,
  onHide:             js.UndefOr[Callback] = js.undefined,
  onFilter:           js.UndefOr[String => Callback] = js.undefined,
  onFilterE:          js.UndefOr[(String, ReactEvent) => Callback] = js.undefined,
  onSelectAll:        js.UndefOr[Boolean => Callback] = js.undefined,
  onSelectAllE:       js.UndefOr[(Boolean, ReactEvent) => Callback] = js.undefined,
  modifiers:          Seq[TagMod] = Seq.empty
)(using val eqA:      Eq[A])
    extends ReactFnProps(MultiSelect.component):

  def addModifiers(modifiers: Seq[TagMod]) = copy(modifiers = this.modifiers ++ modifiers)
  def withMods(mods:          TagMod*)     = addModifiers(mods)

  private val optionsWithIndex: List[(SelectItem[A], Int)] = options.zipWithIndex

  private val getter: js.Array[Int] =
    value.map(optionsWithIndex.indexOfOption).flattenOption.toJSArray

  private def finder(i: Int): A =
    optionsWithIndex
      .findByIndexOption(i)
      .getOrElse(options(0).value) // called by onChange, so should exist

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

  private val filterHandler: js.UndefOr[MultiSelectFilterParams => Callback] =
    ((props.onFilter.toOption, props.onFilterE.toOption) match
      case (Some(onFilter), Some(onFilterE)) =>
        (
          (e: MultiSelectFilterParams) => onFilter(e.filter) >> onFilterE(e.filter, e.originalEvent)
        ).some
      case (Some(onFilter), None)            => ((e: MultiSelectFilterParams) => onFilter(e.filter)).some
      case (None, Some(onFilterE))           =>
        ((e: MultiSelectFilterParams) => onFilterE(e.filter, e.originalEvent)).some
      case _                                 => none
    ).orUndefined

  private val selectAllHandler: js.UndefOr[MultiSelectAllParams => Callback] =
    ((props.onSelectAll.toOption, props.onSelectAllE.toOption) match
      case (Some(onSelectAll), Some(onSelectAllE)) =>
        (
          (e: MultiSelectAllParams) =>
            onSelectAll(e.checked) >> onSelectAllE(e.checked, e.originalEvent)
        ).some
      case (Some(onSelectAll), None)               => ((e: MultiSelectAllParams) => onSelectAll(e.checked)).some
      case (None, Some(onSelectAllE))              =>
        ((e: MultiSelectAllParams) => onSelectAllE(e.checked, e.originalEvent)).some
      case _                                       => none
    ).orUndefined

object MultiSelect:
  enum Display(val toJs: MultiSelectDisplayType):
    case Comma extends Display(MultiSelectDisplayType.comma)
    case Chip  extends Display(MultiSelectDisplayType.chip)

  private def componentBuilder[A] = ScalaFnComponent[MultiSelect[A]] { props =>
    import props.eqA

    val changeHandler: MultiSelectChangeParams => Callback =
      parms =>
        val a = parms.value.asInstanceOf[js.Array[Int]].toList.map(props.finder)
        props.onChange.toOption.map(_(a)).getOrElse(Callback.empty) >>
          props.onChangeE.toOption.map(_(a, parms.originalEvent)).getOrElse(Callback.empty)

    CMultiSelect
      .value(props.getter)
      .options(props.optionsWithIndex.raw)
      .onChange(changeHandler)
      .applyOrNot(props.id, _.id(_))
      .applyOrNot(props.name, _.name(_))
      .applyOrNot(props.clazz, (c, p) => c.className(p.htmlClass))
      .applyOrNot(props.panelClass, (c, p) => c.panelClassName(p.htmlClass))
      .applyOrNot(props.scrollHeight, _.scrollHeight(_))
      .applyOrNot(props.placeholder, _.placeholder(_))
      .applyOrNot(props.fixedPlaceholder, _.fixedPlaceholder(_))
      .applyOrNot(props.disabled, _.disabled(_))
      .applyOrNot(props.showClear, _.showClear(_))
      .applyOrNot(props.filter, _.filter(_))
      .applyOrNot(props.resetFilterOnHide, _.resetFilterOnHide(_))
      .applyOrNot(props.dataKey, _.dataKey(_))
      .applyOrNot(props.inputId, _.inputId(_))
      .applyOrNot(props.tooltip, _.tooltip(_))
      .applyOrNot(props.tooltipOptions, (c, p) => c.tooltipOptions(p.asInstanceOf[CTooltipOptions]))
      .applyOrNot(
        props.itemTemplate,
        (c, fn) => c.itemTemplateFunction1(i => fn(props.finder(i.asInstanceOf[Int])).rawNode)
      )
      .applyOrNot(props.maxSelectedLabels, _.maxSelectedLabels(_))
      .applyOrNot(props.selectionLimit, _.selectionLimit(_))
      .applyOrNot(props.selectedItemsLabel, _.selectedItemsLabel(_))
      .applyOrNot(props.ariaLabelledBy, _.ariaLabelledBy(_))
      .applyOrNot(props.display, (c, p) => c.display(p.toJs))
      .applyOrNot(props.dropdownIcon, _.dropdownIcon(_))
      .applyOrNot(props.removeIcon, _.removeIcon(_))
      .applyOrNot(props.showSelectAll, _.showSelectAll(_))
      .applyOrNot(props.selectAll, _.selectAll(_))
      .applyOrNot(props.focusHandler, _.onFocus(_))
      .applyOrNot(props.blurHandler, _.onBlur(_))
      .applyOrNot(props.onShow, _.onShow(_))
      .applyOrNot(props.onHide, _.onHide(_))
      .applyOrNot(props.filterHandler, _.onFilter(_))
      .applyOrNot(props.selectAllHandler, _.onSelectAll(_))(props.modifiers.toTagMod)
  }

  private val component = componentBuilder[Any]
