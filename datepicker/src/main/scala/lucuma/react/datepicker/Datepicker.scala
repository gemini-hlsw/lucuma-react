// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.datepicker

import japgolly.scalajs.react.*
import japgolly.scalajs.react.component.Js.ComponentWithFacade
import japgolly.scalajs.react.facade.React.Node as RawNode
import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.common.*
import lucuma.react.common.style.Css
import org.scalajs.dom.Element

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

// Prior to version 7 of react-datepicker, we were using typescript types from @types/react-datepicker to generate
// a facade with scalablytyped.  However, starting with version 7, the react-datepicker is written in typescript and so
// provides it's own types.  Apparently, these types are not readable by human or scalablytyped, so we had to roll our own facade.
// There were few breaking changes in version 7, so I'm including, commented out below, the code
// generated from the old @types as a potential aid in adding additional methods to the facade.
case class DatepickerRef(
  ref: Ref.ToJsComponent[
    Datepicker.Props,
    Null,
    facade.React.Component[Datepicker.Props, Null] & Datepicker.Facade
  ]
) {
  def setOpen(open: Boolean): Callback =
    ref.get.map(_.fold(())(_.raw.setOpen(open)))
}
case class Datepicker(
  onChange:          Option[js.Date] => Callback,
  selected:          Option[js.Date],
  id:                js.UndefOr[String] = js.undefined,
  minDate:           js.UndefOr[js.Date] = js.undefined,
  maxDate:           js.UndefOr[js.Date] = js.undefined,
  dateFormat:        js.UndefOr[String] = js.undefined,
  readonly:          js.UndefOr[Boolean] = js.undefined,
  className:         js.UndefOr[Css] = js.undefined,
  calendarClassName: js.UndefOr[Css] = js.undefined,
  showTimeInput:     js.UndefOr[Boolean] = js.undefined,
  customTimeInput:   js.UndefOr[VdomElement] = js.undefined,
  modifiers:         Seq[TagMod] = Seq.empty
) extends GenericComponentPACF[Datepicker.Props, Datepicker, Datepicker.Facade]:
  override protected def cprops    = Datepicker.props(this)
  override protected val component = Datepicker.component

  override def addModifiers(modifiers: Seq[TagMod]): Datepicker =
    copy(modifiers = this.modifiers ++ modifiers)

  def apply(mods: TagMod*) = addModifiers(mods)

object Datepicker {
  // Datepicker blows up with dates outside of these ranges.
  val MinSupportedDate: js.Date = new js.Date(js.Date.parse("1800-01-01T00:00:00Z"))
  val MaxSupportedDate: js.Date = new js.Date(js.Date.parse("9999-12-31T23:59:59"))

  @js.native
  @JSImport("react-datepicker", JSImport.Default)
  object RawDatepicker extends js.Object

  @js.native
  trait Facade extends js.Object {
    def setOpen(open: Boolean): Unit = js.native
  }

  @js.native
  trait Props extends js.Object {
    // We are not supporting multi or range select, so we'll only ever have one date.
    var onChange
      : js.Function2[js.UndefOr[js.Date], js.UndefOr[ReactEventFrom[js.Any & Element]], Unit]
    var selected: js.UndefOr[js.Date | Null]
    var id: js.UndefOr[String]
    var minDate: js.UndefOr[js.Date]
    var maxDate: js.UndefOr[js.Date]
    var dateFormat: js.UndefOr[String]
    var readOnly: js.UndefOr[Boolean]
    var className: js.UndefOr[String]
    var calendarClassName: js.UndefOr[String]
    var showTimeInput: js.UndefOr[Boolean]
    var customTimeInput: js.UndefOr[RawNode]
  }

  private def props(dp: Datepicker): Props = {
    val r = (new js.Object).asInstanceOf[Props]
    r.onChange = (t, _) => dp.onChange(t.toOption).runNow()
    r.selected = dp.selected.orNull
    r.minDate = dp.minDate.getOrElse(MinSupportedDate)
    r.maxDate = dp.maxDate.getOrElse(MaxSupportedDate)
    dp.id.foreach(r.id = _)
    dp.dateFormat.foreach(r.dateFormat = _)
    dp.readonly.foreach(r.readOnly = _)
    dp.className.foreach(v => r.className = v.htmlClass)
    dp.calendarClassName.foreach(v => r.calendarClassName = v.htmlClass)
    dp.showTimeInput.foreach(r.showTimeInput = _)
    dp.customTimeInput.foreach(v => r.customTimeInput = v.rawNode)
    r
  }

  val component = JsComponent[Props, Children.Varargs, Null](RawDatepicker).addFacade[Facade]

}

// package lucuma.typed.reactDatepicker.components

// import lucuma.typed.StBuildingComponent
// import lucuma.typed.dateFns.mod.Locale
// import lucuma.typed.reactDatepicker.anon.Children
// import lucuma.typed.reactDatepicker.anon.Code
// import lucuma.typed.reactDatepicker.anon.End
// import lucuma.typed.reactDatepicker.mod.CalendarContainerProps
// import lucuma.typed.reactDatepicker.mod.HighlightDates
// import lucuma.typed.reactDatepicker.mod.Holiday
// import lucuma.typed.reactDatepicker.mod.ReactDatePickerCustomHeaderProps
// import lucuma.typed.reactDatepicker.mod.ReactDatePickerProps
// import lucuma.typed.reactDatepicker.mod.default
// import lucuma.typed.reactDatepicker.reactDatepickerStrings.scroll
// import lucuma.typed.reactDatepicker.reactDatepickerStrings.select
// import lucuma.typed.reactPopper.mod.Modifier
// import lucuma.typed.reactPopper.mod.StrictModifierNames
// import lucuma.typed.std.ShadowRoot
// import japgolly.scalajs.react.Callback
// import japgolly.scalajs.react.ReactEventFrom
// import japgolly.scalajs.react.ReactFocusEventFrom
// import japgolly.scalajs.react.ReactKeyboardEventFrom
// import japgolly.scalajs.react.ReactMouseEventFrom
// import japgolly.scalajs.react.facade.Empty
// import japgolly.scalajs.react.facade.JsNumber
// import japgolly.scalajs.react.facade.React.Node
// import japgolly.scalajs.react.vdom.VdomElement
// import japgolly.scalajs.react.vdom.VdomNode
// import org.scalajs.dom.Element
// import org.scalajs.dom.Event
// import org.scalajs.dom.HTMLDivElement
// import org.scalajs.dom.HTMLInputElement
// import org.scalablytyped.runtime.StObject
// import scala.scalajs.js
// import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

// object ReactDatepicker {

//   inline def apply[CustomModifierNames /* <: String */, WithRange /* <: js.UndefOr[Boolean] */](
//     onChange: (/* import warning: importer.ImportType#apply Failed type conversion: WithRange extends false | undefined ? std.Date | null : [std.Date | null, std.Date | null] */ js.Any, js.UndefOr[ReactEventFrom[Any & Element]]) => Callback
//   ): Builder[CustomModifierNames, WithRange] = {
//     val __props = js.Dynamic.literal(onChange = js.Any.fromFunction2((t0: /* import warning: importer.ImportType#apply Failed type conversion: WithRange extends false | undefined ? std.Date | null : [std.Date | null, std.Date | null] */ js.Any, t1: js.UndefOr[ReactEventFrom[Any & Element]]) => (onChange(t0, t1)).runNow()))
//     new Builder[CustomModifierNames, WithRange](js.Array(this.component, __props.asInstanceOf[ReactDatePickerProps[CustomModifierNames, WithRange]]))
//   }

//   @JSImport("react-datepicker", JSImport.Default)
//   @js.native
//   val component: js.Object = js.native

//   @scala.inline
//   open class Builder[CustomModifierNames /* <: String */, WithRange /* <: js.UndefOr[Boolean] */] (val args: js.Array[Any])
//     extends AnyVal
//        with StBuildingComponent[default[CustomModifierNames, WithRange]] {

//     inline def adjustDateOnChange(value: Boolean): this.type = set("adjustDateOnChange", value.asInstanceOf[js.Any])

//     inline def allowSameDay(value: Boolean): this.type = set("allowSameDay", value.asInstanceOf[js.Any])

//     inline def ariaDescribedBy(value: String): this.type = set("ariaDescribedBy", value.asInstanceOf[js.Any])

//     inline def ariaInvalid(value: String): this.type = set("ariaInvalid", value.asInstanceOf[js.Any])

//     inline def ariaLabelClose(value: String): this.type = set("ariaLabelClose", value.asInstanceOf[js.Any])

//     inline def ariaLabelledBy(value: String): this.type = set("ariaLabelledBy", value.asInstanceOf[js.Any])

//     inline def ariaRequired(value: String): this.type = set("ariaRequired", value.asInstanceOf[js.Any])

//     inline def autoComplete(value: String): this.type = set("autoComplete", value.asInstanceOf[js.Any])

//     inline def autoFocus(value: Boolean): this.type = set("autoFocus", value.asInstanceOf[js.Any])

//     inline def calendarClassName(value: String): this.type = set("calendarClassName", value.asInstanceOf[js.Any])

//     inline def calendarContainer(value: /* props */ CalendarContainerProps => Node): this.type = set("calendarContainer", js.Any.fromFunction1(value))

//     inline def calendarIconClassname(value: String): this.type = set("calendarIconClassname", value.asInstanceOf[js.Any])

//     inline def calendarStartDay(value: Double): this.type = set("calendarStartDay", value.asInstanceOf[js.Any])

//     inline def chooseDayAriaLabelPrefix(value: String): this.type = set("chooseDayAriaLabelPrefix", value.asInstanceOf[js.Any])

//     inline def className(value: String): this.type = set("className", value.asInstanceOf[js.Any])

//     inline def clearButtonClassName(value: String): this.type = set("clearButtonClassName", value.asInstanceOf[js.Any])

//     inline def clearButtonTitle(value: String): this.type = set("clearButtonTitle", value.asInstanceOf[js.Any])

//     inline def closeOnScroll(value: Boolean | (js.Function1[/* e */ Event, Boolean])): this.type = set("closeOnScroll", value.asInstanceOf[js.Any])

//     inline def closeOnScrollFunction1(value: /* e */ Event => Boolean): this.type = set("closeOnScroll", js.Any.fromFunction1(value))

//     inline def customInput(value: VdomNode): this.type = set("customInput", value.rawNode.asInstanceOf[js.Any])

//     inline def customInputNull: this.type = set("customInput", null)

//     inline def customInputRef(value: String): this.type = set("customInputRef", value.asInstanceOf[js.Any])

//     inline def customInputVarargs(value: (Empty | String | JsNumber | japgolly.scalajs.react.facade.React.Element)*): this.type = set("customInput", js.Array(value*))

//     inline def customInputVdomElement(value: VdomElement): this.type = set("customInput", value.rawElement.asInstanceOf[js.Any])

//     inline def customTimeInput(value: VdomNode): this.type = set("customTimeInput", value.rawNode.asInstanceOf[js.Any])

//     inline def customTimeInputNull: this.type = set("customTimeInput", null)

//     inline def customTimeInputVarargs(value: (Empty | String | JsNumber | japgolly.scalajs.react.facade.React.Element)*): this.type = set("customTimeInput", js.Array(value*))

//     inline def customTimeInputVdomElement(value: VdomElement): this.type = set("customTimeInput", value.rawElement.asInstanceOf[js.Any])

//     inline def dateFormat(value: String | js.Array[String]): this.type = set("dateFormat", value.asInstanceOf[js.Any])

//     inline def dateFormatCalendar(value: String): this.type = set("dateFormatCalendar", value.asInstanceOf[js.Any])

//     inline def dateFormatVarargs(value: String*): this.type = set("dateFormat", js.Array(value*))

//     inline def dayClassName(value: /* date */ js.Date => String | Null): this.type = set("dayClassName", js.Any.fromFunction1(value))

//     inline def disabled(value: Boolean): this.type = set("disabled", value.asInstanceOf[js.Any])

//     inline def disabledDayAriaLabelPrefix(value: String): this.type = set("disabledDayAriaLabelPrefix", value.asInstanceOf[js.Any])

//     inline def disabledKeyboardNavigation(value: Boolean): this.type = set("disabledKeyboardNavigation", value.asInstanceOf[js.Any])

//     inline def dropdownMode(value: scroll | select): this.type = set("dropdownMode", value.asInstanceOf[js.Any])

//     inline def enableTabLoop(value: Boolean): this.type = set("enableTabLoop", value.asInstanceOf[js.Any])

//     inline def endDate(value: js.Date): this.type = set("endDate", value.asInstanceOf[js.Any])

//     inline def endDateNull: this.type = set("endDate", null)

//     inline def excludeDateIntervals(value: js.Array[End]): this.type = set("excludeDateIntervals", value.asInstanceOf[js.Any])

//     inline def excludeDateIntervalsVarargs(value: End*): this.type = set("excludeDateIntervals", js.Array(value*))

//     inline def excludeDates(value: js.Array[js.Date]): this.type = set("excludeDates", value.asInstanceOf[js.Any])

//     inline def excludeDatesVarargs(value: js.Date*): this.type = set("excludeDates", js.Array(value*))

//     inline def excludeScrollbar(value: Boolean): this.type = set("excludeScrollbar", value.asInstanceOf[js.Any])

//     inline def excludeTimes(value: js.Array[js.Date]): this.type = set("excludeTimes", value.asInstanceOf[js.Any])

//     inline def excludeTimesVarargs(value: js.Date*): this.type = set("excludeTimes", js.Array(value*))

//     inline def filterDate(value: /* date */ js.Date => Boolean): this.type = set("filterDate", js.Any.fromFunction1(value))

//     inline def filterTime(value: /* date */ js.Date => Boolean): this.type = set("filterTime", js.Any.fromFunction1(value))

//     inline def fixedHeight(value: Boolean): this.type = set("fixedHeight", value.asInstanceOf[js.Any])

//     inline def focusSelectedMonth(value: Boolean): this.type = set("focusSelectedMonth", value.asInstanceOf[js.Any])

//     inline def forceShowMonthNavigation(value: Boolean): this.type = set("forceShowMonthNavigation", value.asInstanceOf[js.Any])

//     inline def formatWeekDay(value: /* day */ String => Node): this.type = set("formatWeekDay", js.Any.fromFunction1(value))

//     inline def formatWeekNumber(value: /* date */ js.Date => String | Double): this.type = set("formatWeekNumber", js.Any.fromFunction1(value))

//     inline def highlightDates(value: js.Array[HighlightDates | js.Date]): this.type = set("highlightDates", value.asInstanceOf[js.Any])

//     inline def highlightDatesVarargs(value: (HighlightDates | js.Date)*): this.type = set("highlightDates", js.Array(value*))

//     inline def holidays(value: js.Array[Holiday]): this.type = set("holidays", value.asInstanceOf[js.Any])

//     inline def holidaysVarargs(value: Holiday*): this.type = set("holidays", js.Array(value*))

//     inline def icon(value: String | japgolly.scalajs.react.facade.React.Element): this.type = set("icon", value.asInstanceOf[js.Any])

//     inline def iconVdomElement(value: VdomElement): this.type = set("icon", value.rawElement.asInstanceOf[js.Any])

//     inline def id(value: String): this.type = set("id", value.asInstanceOf[js.Any])

//     inline def includeDateIntervals(value: js.Array[End]): this.type = set("includeDateIntervals", value.asInstanceOf[js.Any])

//     inline def includeDateIntervalsVarargs(value: End*): this.type = set("includeDateIntervals", js.Array(value*))

//     inline def includeDates(value: js.Array[js.Date]): this.type = set("includeDates", value.asInstanceOf[js.Any])

//     inline def includeDatesVarargs(value: js.Date*): this.type = set("includeDates", js.Array(value*))

//     inline def includeTimes(value: js.Array[js.Date]): this.type = set("includeTimes", value.asInstanceOf[js.Any])

//     inline def includeTimesVarargs(value: js.Date*): this.type = set("includeTimes", js.Array(value*))

//     inline def injectTimes(value: js.Array[js.Date]): this.type = set("injectTimes", value.asInstanceOf[js.Any])

//     inline def injectTimesVarargs(value: js.Date*): this.type = set("injectTimes", js.Array(value*))

//     inline def `inline`(value: Boolean): this.type = set("inline", value.asInstanceOf[js.Any])

//     inline def isClearable(value: Boolean): this.type = set("isClearable", value.asInstanceOf[js.Any])

//     inline def locale(value: String | Locale): this.type = set("locale", value.asInstanceOf[js.Any])

//     inline def maxDate(value: js.Date): this.type = set("maxDate", value.asInstanceOf[js.Any])

//     inline def maxDateNull: this.type = set("maxDate", null)

//     inline def maxTime(value: js.Date): this.type = set("maxTime", value.asInstanceOf[js.Any])

//     inline def minDate(value: js.Date): this.type = set("minDate", value.asInstanceOf[js.Any])

//     inline def minDateNull: this.type = set("minDate", null)

//     inline def minTime(value: js.Date): this.type = set("minTime", value.asInstanceOf[js.Any])

//     inline def monthAriaLabelPrefix(value: String): this.type = set("monthAriaLabelPrefix", value.asInstanceOf[js.Any])

//     inline def monthClassName(value: /* date */ js.Date => String | Null): this.type = set("monthClassName", js.Any.fromFunction1(value))

//     inline def monthsShown(value: Double): this.type = set("monthsShown", value.asInstanceOf[js.Any])

//     inline def name(value: String): this.type = set("name", value.asInstanceOf[js.Any])

//     inline def nextMonthAriaLabel(value: String): this.type = set("nextMonthAriaLabel", value.asInstanceOf[js.Any])

//     inline def nextMonthButtonLabel(value: String | Node): this.type = set("nextMonthButtonLabel", value.asInstanceOf[js.Any])

//     inline def nextMonthButtonLabelNull: this.type = set("nextMonthButtonLabel", null)

//     inline def nextMonthButtonLabelVarargs(value: (Empty | String | JsNumber | japgolly.scalajs.react.facade.React.Element)*): this.type = set("nextMonthButtonLabel", js.Array(value*))

//     inline def nextMonthButtonLabelVdomElement(value: VdomElement): this.type = set("nextMonthButtonLabel", value.rawElement.asInstanceOf[js.Any])

//     inline def nextYearAriaLabel(value: String): this.type = set("nextYearAriaLabel", value.asInstanceOf[js.Any])

//     inline def nextYearButtonLabel(value: String | Node): this.type = set("nextYearButtonLabel", value.asInstanceOf[js.Any])

//     inline def nextYearButtonLabelNull: this.type = set("nextYearButtonLabel", null)

//     inline def nextYearButtonLabelVarargs(value: (Empty | String | JsNumber | japgolly.scalajs.react.facade.React.Element)*): this.type = set("nextYearButtonLabel", js.Array(value*))

//     inline def nextYearButtonLabelVdomElement(value: VdomElement): this.type = set("nextYearButtonLabel", value.rawElement.asInstanceOf[js.Any])

//     inline def onBlur(value: /* event */ ReactFocusEventFrom[HTMLInputElement] => Callback): this.type = set("onBlur", js.Any.fromFunction1((t0: /* event */ ReactFocusEventFrom[HTMLInputElement]) => value(t0).runNow()))

//     inline def onCalendarClose(value: Callback): this.type = set("onCalendarClose", value.toJsFn)

//     inline def onCalendarOpen(value: Callback): this.type = set("onCalendarOpen", value.toJsFn)

//     inline def onChangeRaw(value: /* event */ ReactFocusEventFrom[HTMLInputElement] => Callback): this.type = set("onChangeRaw", js.Any.fromFunction1((t0: /* event */ ReactFocusEventFrom[HTMLInputElement]) => value(t0).runNow()))

//     inline def onClickOutside(value: /* event */ ReactMouseEventFrom[HTMLDivElement] => Callback): this.type = set("onClickOutside", js.Any.fromFunction1((t0: /* event */ ReactMouseEventFrom[HTMLDivElement]) => value(t0).runNow()))

//     inline def onDayMouseEnter(value: /* date */ js.Date => Callback): this.type = set("onDayMouseEnter", js.Any.fromFunction1((t0: /* date */ js.Date) => value(t0).runNow()))

//     inline def onFocus(value: /* event */ ReactFocusEventFrom[HTMLInputElement] => Callback): this.type = set("onFocus", js.Any.fromFunction1((t0: /* event */ ReactFocusEventFrom[HTMLInputElement]) => value(t0).runNow()))

//     inline def onInputClick(value: Callback): this.type = set("onInputClick", value.toJsFn)

//     inline def onInputError(value: /* err */ Code => Callback): this.type = set("onInputError", js.Any.fromFunction1((t0: /* err */ Code) => value(t0).runNow()))

//     inline def onKeyDown(value: /* event */ ReactKeyboardEventFrom[HTMLDivElement] => Callback): this.type = set("onKeyDown", js.Any.fromFunction1((t0: /* event */ ReactKeyboardEventFrom[HTMLDivElement]) => value(t0).runNow()))

//     inline def onMonthChange(value: /* date */ js.Date => Callback): this.type = set("onMonthChange", js.Any.fromFunction1((t0: /* date */ js.Date) => value(t0).runNow()))

//     inline def onMonthMouseLeave(value: Callback): this.type = set("onMonthMouseLeave", value.toJsFn)

//     inline def onSelect(value: (/* date */ js.Date, /* event */ js.UndefOr[ReactEventFrom[Any & Element]]) => Callback): this.type = set("onSelect", js.Any.fromFunction2((t0: /* date */ js.Date, t1: /* event */ js.UndefOr[ReactEventFrom[Any & Element]]) => (value(t0, t1)).runNow()))

//     inline def onWeekSelect(
//       value: (/* firstDayOfWeek */ js.Date, /* weekNumber */ String | Double, /* event */ js.UndefOr[ReactEventFrom[Any & Element]]) => Callback
//     ): this.type = set("onWeekSelect", js.Any.fromFunction3((t0: /* firstDayOfWeek */ js.Date, t1: /* weekNumber */ String | Double, t2: /* event */ js.UndefOr[ReactEventFrom[Any & Element]]) => (value(t0, t1, t2)).runNow()))

//     inline def onYearChange(value: /* date */ js.Date => Callback): this.type = set("onYearChange", js.Any.fromFunction1((t0: /* date */ js.Date) => value(t0).runNow()))

//     inline def open(value: Boolean): this.type = set("open", value.asInstanceOf[js.Any])

//     inline def openToDate(value: js.Date): this.type = set("openToDate", value.asInstanceOf[js.Any])

//     inline def peekNextMonth(value: Boolean): this.type = set("peekNextMonth", value.asInstanceOf[js.Any])

//     inline def placeholderText(value: String): this.type = set("placeholderText", value.asInstanceOf[js.Any])

//     inline def popperClassName(value: String): this.type = set("popperClassName", value.asInstanceOf[js.Any])

//     inline def popperContainer(value: /* props */ Children => Node): this.type = set("popperContainer", js.Any.fromFunction1(value))

//     inline def popperModifiers(value: js.Array[Modifier[StrictModifierNames | CustomModifierNames, js.Object]]): this.type = set("popperModifiers", value.asInstanceOf[js.Any])

//     inline def popperModifiersVarargs(value: (Modifier[StrictModifierNames | CustomModifierNames, js.Object])*): this.type = set("popperModifiers", js.Array(value*))

//     inline def popperPlacement(
//       value: /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify Popper.Placement */ Any
//     ): this.type = set("popperPlacement", value.asInstanceOf[js.Any])

//     inline def popperProps(value: js.Object): this.type = set("popperProps", value.asInstanceOf[js.Any])

//     inline def portalHost(value: ShadowRoot): this.type = set("portalHost", value.asInstanceOf[js.Any])

//     inline def portalId(value: String): this.type = set("portalId", value.asInstanceOf[js.Any])

//     inline def preventOpenOnFocus(value: Boolean): this.type = set("preventOpenOnFocus", value.asInstanceOf[js.Any])

//     inline def previousMonthAriaLabel(value: String): this.type = set("previousMonthAriaLabel", value.asInstanceOf[js.Any])

//     inline def previousMonthButtonLabel(value: String | Node): this.type = set("previousMonthButtonLabel", value.asInstanceOf[js.Any])

//     inline def previousMonthButtonLabelNull: this.type = set("previousMonthButtonLabel", null)

//     inline def previousMonthButtonLabelVarargs(value: (Empty | String | JsNumber | japgolly.scalajs.react.facade.React.Element)*): this.type = set("previousMonthButtonLabel", js.Array(value*))

//     inline def previousMonthButtonLabelVdomElement(value: VdomElement): this.type = set("previousMonthButtonLabel", value.rawElement.asInstanceOf[js.Any])

//     inline def previousYearAriaLabel(value: String): this.type = set("previousYearAriaLabel", value.asInstanceOf[js.Any])

//     inline def previousYearButtonLabel(value: String | Node): this.type = set("previousYearButtonLabel", value.asInstanceOf[js.Any])

//     inline def previousYearButtonLabelNull: this.type = set("previousYearButtonLabel", null)

//     inline def previousYearButtonLabelVarargs(value: (Empty | String | JsNumber | japgolly.scalajs.react.facade.React.Element)*): this.type = set("previousYearButtonLabel", js.Array(value*))

//     inline def previousYearButtonLabelVdomElement(value: VdomElement): this.type = set("previousYearButtonLabel", value.rawElement.asInstanceOf[js.Any])

//     inline def readOnly(value: Boolean): this.type = set("readOnly", value.asInstanceOf[js.Any])

//     inline def renderCustomHeader(value: /* params */ ReactDatePickerCustomHeaderProps => Node): this.type = set("renderCustomHeader", js.Any.fromFunction1(value))

//     inline def renderDayContents(value: (/* dayOfMonth */ Double, /* date */ js.UndefOr[js.Date]) => Node): this.type = set("renderDayContents", js.Any.fromFunction2(value))

//     inline def renderMonthContent(value: (/* monthIndex */ Double, /* shortMonthText */ String, /* fullMonthText */ String) => Node): this.type = set("renderMonthContent", js.Any.fromFunction3(value))

//     inline def required(value: Boolean): this.type = set("required", value.asInstanceOf[js.Any])

//     inline def scrollableMonthYearDropdown(value: Boolean): this.type = set("scrollableMonthYearDropdown", value.asInstanceOf[js.Any])

//     inline def scrollableYearDropdown(value: Boolean): this.type = set("scrollableYearDropdown", value.asInstanceOf[js.Any])

//     inline def selected(value: js.Date): this.type = set("selected", value.asInstanceOf[js.Any])

//     inline def selectedNull: this.type = set("selected", null)

//     inline def selectsEnd(value: Boolean): this.type = set("selectsEnd", value.asInstanceOf[js.Any])

//     inline def selectsRange(value: WithRange): this.type = set("selectsRange", value.asInstanceOf[js.Any])

//     inline def selectsStart(value: Boolean): this.type = set("selectsStart", value.asInstanceOf[js.Any])

//     inline def shouldCloseOnSelect(value: Boolean): this.type = set("shouldCloseOnSelect", value.asInstanceOf[js.Any])

//     inline def showDisabledMonthNavigation(value: Boolean): this.type = set("showDisabledMonthNavigation", value.asInstanceOf[js.Any])

//     inline def showFourColumnMonthYearPicker(value: Boolean): this.type = set("showFourColumnMonthYearPicker", value.asInstanceOf[js.Any])

//     inline def showFullMonthYearPicker(value: Boolean): this.type = set("showFullMonthYearPicker", value.asInstanceOf[js.Any])

//     inline def showIcon(value: Boolean): this.type = set("showIcon", value.asInstanceOf[js.Any])

//     inline def showMonthDropdown(value: Boolean): this.type = set("showMonthDropdown", value.asInstanceOf[js.Any])

//     inline def showMonthYearDropdown(value: Boolean): this.type = set("showMonthYearDropdown", value.asInstanceOf[js.Any])

//     inline def showMonthYearPicker(value: Boolean): this.type = set("showMonthYearPicker", value.asInstanceOf[js.Any])

//     inline def showPopperArrow(value: Boolean): this.type = set("showPopperArrow", value.asInstanceOf[js.Any])

//     inline def showPreviousMonths(value: Boolean): this.type = set("showPreviousMonths", value.asInstanceOf[js.Any])

//     inline def showQuarterYearPicker(value: Boolean): this.type = set("showQuarterYearPicker", value.asInstanceOf[js.Any])

//     inline def showTimeInput(value: Boolean): this.type = set("showTimeInput", value.asInstanceOf[js.Any])

//     inline def showTimeSelect(value: Boolean): this.type = set("showTimeSelect", value.asInstanceOf[js.Any])

//     inline def showTimeSelectOnly(value: Boolean): this.type = set("showTimeSelectOnly", value.asInstanceOf[js.Any])

//     inline def showTwoColumnMonthYearPicker(value: Boolean): this.type = set("showTwoColumnMonthYearPicker", value.asInstanceOf[js.Any])

//     inline def showWeekNumbers(value: Boolean): this.type = set("showWeekNumbers", value.asInstanceOf[js.Any])

//     inline def showWeekPicker(value: Boolean): this.type = set("showWeekPicker", value.asInstanceOf[js.Any])

//     inline def showYearDropdown(value: Boolean): this.type = set("showYearDropdown", value.asInstanceOf[js.Any])

//     inline def showYearPicker(value: Boolean): this.type = set("showYearPicker", value.asInstanceOf[js.Any])

//     inline def startDate(value: js.Date): this.type = set("startDate", value.asInstanceOf[js.Any])

//     inline def startDateNull: this.type = set("startDate", null)

//     inline def startOpen(value: Boolean): this.type = set("startOpen", value.asInstanceOf[js.Any])

//     inline def strictParsing(value: Boolean): this.type = set("strictParsing", value.asInstanceOf[js.Any])

//     inline def tabIndex(value: Double): this.type = set("tabIndex", value.asInstanceOf[js.Any])

//     inline def timeCaption(value: String): this.type = set("timeCaption", value.asInstanceOf[js.Any])

//     inline def timeClassName(value: /* date */ js.Date => String | Null): this.type = set("timeClassName", js.Any.fromFunction1(value))

//     inline def timeFormat(value: String): this.type = set("timeFormat", value.asInstanceOf[js.Any])

//     inline def timeInputLabel(value: String): this.type = set("timeInputLabel", value.asInstanceOf[js.Any])

//     inline def timeIntervals(value: Double): this.type = set("timeIntervals", value.asInstanceOf[js.Any])

//     inline def title(value: String): this.type = set("title", value.asInstanceOf[js.Any])

//     inline def todayButton(value: VdomNode): this.type = set("todayButton", value.rawNode.asInstanceOf[js.Any])

//     inline def todayButtonNull: this.type = set("todayButton", null)

//     inline def todayButtonVarargs(value: (Empty | String | JsNumber | japgolly.scalajs.react.facade.React.Element)*): this.type = set("todayButton", js.Array(value*))

//     inline def todayButtonVdomElement(value: VdomElement): this.type = set("todayButton", value.rawElement.asInstanceOf[js.Any])

//     inline def toggleCalendarOnIconClick(value: Boolean): this.type = set("toggleCalendarOnIconClick", value.asInstanceOf[js.Any])

//     inline def useShortMonthInDropdown(value: Boolean): this.type = set("useShortMonthInDropdown", value.asInstanceOf[js.Any])

//     inline def useWeekdaysShort(value: Boolean): this.type = set("useWeekdaysShort", value.asInstanceOf[js.Any])

//     inline def value(value: String): this.type = set("value", value.asInstanceOf[js.Any])

//     inline def weekAriaLabelPrefix(value: String): this.type = set("weekAriaLabelPrefix", value.asInstanceOf[js.Any])

//     inline def weekDayClassName(value: /* date */ js.Date => String | Null): this.type = set("weekDayClassName", js.Any.fromFunction1(value))

//     inline def weekLabel(value: String): this.type = set("weekLabel", value.asInstanceOf[js.Any])

//     inline def withPortal(value: Boolean): this.type = set("withPortal", value.asInstanceOf[js.Any])

//     inline def wrapperClassName(value: String): this.type = set("wrapperClassName", value.asInstanceOf[js.Any])

//     inline def yearDropdownItemNumber(value: Double): this.type = set("yearDropdownItemNumber", value.asInstanceOf[js.Any])

//     inline def yearItemNumber(value: Double): this.type = set("yearItemNumber", value.asInstanceOf[js.Any])
//   }

//   type Props[CustomModifierNames /* <: String */, WithRange /* <: js.UndefOr[Boolean] */] = ReactDatePickerProps[CustomModifierNames, WithRange]

//   def withProps[CustomModifierNames /* <: String */, WithRange /* <: js.UndefOr[Boolean] */](p: ReactDatePickerProps[CustomModifierNames, WithRange]): Builder[CustomModifierNames, WithRange] = new Builder[CustomModifierNames, WithRange](js.Array(this.component, p.asInstanceOf[js.Any]))
// }
