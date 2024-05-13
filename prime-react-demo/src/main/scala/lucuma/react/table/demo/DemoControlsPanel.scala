// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table.demo

import cats.syntax.all.*
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import japgolly.scalajs.react.vdom.svg_<^.< as <<
import japgolly.scalajs.react.vdom.svg_<^.^ as ^^
import lucuma.react.common.*
import lucuma.react.fa.FAIcon
import lucuma.react.fa.FontAwesome
import lucuma.react.fa.FontAwesomeIcon
import lucuma.react.fa.IconSize
import lucuma.react.fa.LayeredIcon
import lucuma.react.primereact.*
import lucuma.react.primereact.tooltip.*

import scala.scalajs.js.annotation.JSImport

import scalajs.js

case class DemoControlsPanel() extends ReactFnProps[DemoControlsPanel](DemoControlsPanel.component)

object DemoControlsPanel {
  val startOffset = VdomAttr("startOffset")

  object Icons:
    @js.native
    @JSImport("@fortawesome/free-solid-svg-icons", "faBan")
    private val faBan: FAIcon = js.native

    @js.native
    @JSImport("@fortawesome/free-solid-svg-icons", "faPause")
    private val faPause: FAIcon = js.native

    FontAwesome.library.add(faBan, faPause)

    inline def Ban = FontAwesomeIcon(faBan)

    inline def Pause = FontAwesomeIcon(faPause)

    val CancelPause = LayeredIcon()(Pause.withInverse(), Ban.withSize(IconSize.LG)).withFixedWidth()

  def mouseEntered(msg: String) = ^.onMouseEnter --> Callback.log(s"Mouse entered: $msg")

  private val component = ScalaFnComponent
    .withHooks[DemoControlsPanel]
    .useState("Text to edit")         // for InputText
    .useState("Resizeable TextArea")  // for InputTextarea
    .useState(0d)                     // For InputNumber
    .useState(List("10", "20", "30")) // for Chips
    .useState(2)                      // for Dropdown
    .useState(3.some)                 // for DropdownOptional
    .useState(List(1, 3))             // for MultiSelect
    .useState(false)                  // for InputSwitch
    .useState(false)                  // for Checkbox
    .useState((25.0, 75.0))           // for SliderRange
    .useState(0.0)                    // for Slider
    .useState(10.0)                   // for Knob
    .useState(0.0)                    // for Knob (readonly)
    .useState(11.0)                   // for Nigel Tufnel Knob
    .useState(1)                      // for SelectButton
    .useState(2.some)                 // for SelectButtonOptional
    .useState(List(1, 3))             // for SelectButtonMultiple
    .useState(false)                  // for ToggleButton
    .useState(50)                     // for ProgressBar
    .useState("option-1")             // for RadioButton
    .render {
      (
        _,
        inputText,
        inputTextarea,
        inputNumber,
        chips,
        dropdown,
        dropdownOptional,
        multiselect,
        inputSwitch,
        checkbox,
        rangeSlider,
        slider,
        knob,
        knobReadonly,
        nigelTufnel,
        selectButton,
        selectButtonOptional,
        selectButtonMultiple,
        toggleButton,
        progressBar,
        radioButton
      ) =>
        val options: List[SelectItem[Int]] =
          List(
            SelectItem(1, "Option 1"),
            SelectItem(2, "Option 2"),
            SelectItem(3, "Option 3"),
            SelectItem(4, "Option 4", disabled = true),
            SelectItem(5, "Option 5")
          )

        val groupedOptions: SelectItemGroups[Int] = SelectItemGroups(groups =
          List(
            SelectItemGroup(label = "Odd Numbers",
                            options = List(SelectItem(1, "Option 1"),
                                           SelectItem(3, "Option 3"),
                                           SelectItem(5, "Option 5")
                            )
            ),
            SelectItemGroup(label = <.span("Even Numbers", ^.cls := "pi pi-bolt"),
                            options = List(SelectItem(2, "Option 2"), SelectItem(4, "Option 4"))
            )
          )
        )

        val decreaseProgress: Callback = {
          val current = progressBar.value
          if current > 0 then progressBar.setState(current - 1) else Callback.empty
        }

        val increaseProgress: Callback = {
          val current = progressBar.value
          if current < 100 then progressBar.setState(current + 1) else Callback.empty
        }

        def progressTemplate(v: Double): VdomNode =
          if (v < 50) "Less than half"
          else if (v > 50) "More than half"
          else "Half way"

        def decreaseKnob: Callback = {
          val current = knobReadonly.value
          if current > -10 then knobReadonly.setState(current - 1) else Callback.empty
        }

        def increaseKnob: Callback = {
          val current = knobReadonly.value
          if current < 10 then knobReadonly.setState(current + 1) else Callback.empty
        }

        Panel(
          header = "Some Controls"
        )(
          <.div(
            DemoStyles.VerticalStack,
            "Form controls are below the divider",
            Divider(
              position = Divider.Position.HorizontalCenter,
              borderType = Divider.BorderType.Dashed
            )("dashed horizontal divider"),
            <.div(
              DemoStyles.FormColumn,
              <.label("InputText",           ^.htmlFor := "input-text", DemoStyles.FormFieldLabel),
              InputText(
                id = "input-text",
                value = inputText.value,
                placeholder = "No text",
                onChange = e => inputText.setState(e.target.value),
                clazz = DemoStyles.FormField,
                onFocus = e => Callback.log(s"Input Text focused. Value: ${e.target.value}"),
                onBlur = e => Callback.log(s"Input Text blurred. Value: ${e.target.value}")
              ).withMods(mouseEntered("InputText")),
              <.label("InputTextarea",       ^.htmlFor := "input-text-area", DemoStyles.FormFieldLabel),
              InputTextarea()(
                ^.id                        := "input-text-area",
                ^.value                     := inputTextarea.value,
                ^.rows                      := 6,
                ^.onChange ==> { (e: ReactEventFromTextArea) =>
                  inputTextarea.setState(e.target.value)
                },
                DemoStyles.FormField,
                mouseEntered("InputTextarea"),
                ^.onFocus ==> { (e: ReactEventFromTextArea) =>
                  Callback.log(s"Focused TextArea: ${e.target.value}")
                }
              ),
              <.label("InputNumber",         ^.htmlFor := "input-number", DemoStyles.FormFieldLabel),
              InputNumber(
                id = "input-number",
                value = inputNumber.value,
                onValueChange = e =>
                  Callback.log(e) *>
                    e.valueOption
                      .map(inputNumber.setState)
                      .getOrElse(Callback.empty),
                min = 0,
                max = 100,
                mode = InputNumber.Mode.Decimal
              ).withMods(DemoStyles.FormField, mouseEntered("InputNumber")),
              <.label("Chips",               ^.htmlFor := "chips", DemoStyles.FormFieldLabel),
              Chips(
                id = "chips",
                value = chips.value,
                onChange = v => chips.setState(v),
                clazz = DemoStyles.FormField,
                separator = ","
              ).withMods(mouseEntered("Chips")),
              <.label("Dropdown",            ^.htmlFor := "dropdown", DemoStyles.FormFieldLabel),
              Dropdown(
                id = "dropdown",
                value = dropdown.value,
                options = options,
                onChange = a => dropdown.setState(a),
                clazz = DemoStyles.FormField
              ).withMods(mouseEntered("Dropdown")),
              <.label("DropdownOptional",
                      ^.htmlFor             := "dropdown-optional",
                      DemoStyles.FormFieldLabel
              ),
              DropdownOptional(
                id = "dropdown-optional",
                value = dropdownOptional.value,
                options = options,
                showClear = true,
                onChange = a => dropdownOptional.setState(a),
                clazz = DemoStyles.FormField
              ).withMods(mouseEntered("DropdownOptional")),
              <.label("MultiSelect",         ^.htmlFor := "multiselect", DemoStyles.FormFieldLabel),
              MultiSelect(
                id = "multiselect",
                value = multiselect.value,
                options = options,
                onChange = a => multiselect.setState(a),
                display = MultiSelect.Display.Chip,
                filter = true,
                itemTemplate = si => s"This is: ${si.label}",
                clazz = DemoStyles.FormField
              ).withMods(mouseEntered("MultiSelect")),
              <.label("Grouped MultiSelect", ^.htmlFor := "multiselect", DemoStyles.FormFieldLabel),
              MultiSelect(
                id = "grouped-multiselect",
                value = multiselect.value,
                options = groupedOptions,
                onChange = a => multiselect.setState(a),
                display = MultiSelect.Display.Chip,
                showSelectAll = false,
                clazz = DemoStyles.FormField
              ),
              <.label("InputSwitch",         ^.htmlFor := "input-switch", DemoStyles.FormFieldLabel),
              InputSwitch(
                inputId = "input-switch",
                checked = inputSwitch.value,
                onChange = b => inputSwitch.setState(b)
              )(mouseEntered("InputSwitch")),
              <.label("Checkbox",            ^.htmlFor := "checkbox", DemoStyles.FormFieldLabel),
              Checkbox(
                inputId = "checkbox",
                checked = checkbox.value,
                onChange = b => checkbox.setState(b)
              )(mouseEntered("Checkbox")),
              <.label("Slider",              ^.htmlFor := "slider", DemoStyles.FormFieldLabel),
              <.span(
                DemoStyles.FormField,
                Slider(id = "slider",
                       value = slider.value,
                       onChange = t => slider.setState(t),
                       min = -10.0,
                       max = 10.0,
                       step = 2,
                       orientation = Layout.Vertical
                )(mouseEntered("Slider")),
                slider.value
              ),
              <.label("SliderRange",         ^.htmlFor := "slider-range", DemoStyles.FormFieldLabel),
              <.span(
                DemoStyles.FormField,
                SliderRange(id = "slider-range",
                            value = rangeSlider.value,
                            onChange = t => rangeSlider.setState(t)
                )(mouseEntered("SliderRange")),
                s"[${rangeSlider.value._1}, ${rangeSlider.value._2}]"
              ),
              <.label("Knob (steps of 5)",   ^.htmlFor := "knob", DemoStyles.FormFieldLabel),
              Knob(id = "knob", value = knob.value, step = 5, size = 75, onChange = knob.setState)(
                mouseEntered("Knob")
              ),
              <.label("Knob for Progress",   ^.htmlFor := "readonly-knob", DemoStyles.FormFieldLabel),
              <.div(
                DemoStyles.HorizontalStack,
                Button(label = "-", onClick = decreaseKnob),
                Knob(id = "readonly-knob",
                     value = knobReadonly.value,
                     min = -5,
                     max = 5,
                     readOnly = true
                ),
                Button(label = "+", onClick = increaseKnob)
              ),
              <.label("Nigel Tufnel Control",
                      ^.htmlFor             := "nigel-tufnel",
                      DemoStyles.FormFieldLabel
              ),
              Knob(id = "nigel-tufnel",
                   value = nigelTufnel.value,
                   max = 11,
                   onChange = nigelTufnel.setState,
                   strokeWidth = 5,
                   valueTemplate = "Vol: {value}"
              ),
              <.label("SelectButton",        ^.htmlFor := "select-button", DemoStyles.FormFieldLabel),
              SelectButton(
                id = "select-button",
                value = selectButton.value,
                options = options,
                onChange = selectButton.setState
              )(mouseEntered("SelectButton")),
              <.label("SelectButtonOptional",
                      ^.htmlFor             := "select-button-optional",
                      DemoStyles.FormFieldLabel
              ),
              SelectButtonOptional(
                id = "select-button-optional",
                value = selectButtonOptional.value,
                options = options,
                onChange = selectButtonOptional.setState
              )(mouseEntered("SelectButtonOptional")),
              <.label("SelectButtonMultiple",
                      ^.htmlFor             := "select-button-multiple",
                      DemoStyles.FormFieldLabel
              ),
              SelectButtonMultiple(
                id = "select-button-multiple",
                value = selectButtonMultiple.value,
                options = options,
                onChange = selectButtonMultiple.setState,
                itemTemplate = si =>
                  if (si.disabled.getOrElse(false)) <.s(si.label.toOption)
                  else <.span(si.label.toOption)
              )(mouseEntered("SelectButtonMultiple")),
              <.label("ToggleButton", DemoStyles.FormFieldLabel),
              <.span(DemoStyles.FormField)(
                ToggleButton(
                  onLabel = "On",
                  offLabel = "Off",
                  onIcon = "pi pi-thumbs-up",
                  offIcon = "pi pi-thumbs-down",
                  iconPos = ToggleButton.IconPosition.Right,
                  checked = toggleButton.value,
                  onChange = toggleButton.setState
                )
              ),
              <.label("Layered Icon Button", DemoStyles.FormFieldLabel),
              Button(icon = Icons.CancelPause),
              <.label("InputGroup", DemoStyles.FormFieldLabel),
              InputGroup(
                InputGroup.Addon("$"),
                InputText(id = "price", placeholder = "Price"),
                InputGroup.Addon(".00")
              ),
              <.label(
                "ProgressBar 1",
                ^.htmlFor                   := "progress-indeterminate",
                DemoStyles.FormFieldLabel
              ),
              ProgressBar(id = "progress-indeterminate", mode = ProgressBar.Mode.Indeterminate),
              <.div(
                DemoStyles.FormField,
                DemoStyles.HorizontalStack,
                Button(onClick = decreaseProgress, label = "Decrease Progress"),
                Button(onClick = increaseProgress,
                       label = "Increase Progress",
                       iconPos = Button.IconPosition.Right
                )
              ),
              <.label("ProgressBar 2",       ^.htmlFor := "progress-vanilla", DemoStyles.FormFieldLabel),
              ProgressBar(id = "progress-vanilla", value = progressBar.value)(
                mouseEntered("ProgressBar")
              ),
              <.label("ProgressBar 3",       ^.htmlFor := "progress-template", DemoStyles.FormFieldLabel),
              ProgressBar(id = "progress-template",
                          value = progressBar.value,
                          displayValueTemplate = progressTemplate
              ),
              <.label("ProgressSpinner", DemoStyles.FormFieldLabel),
              ProgressSpinner(clazz = DemoStyles.FormField)(mouseEntered("ProgressSpinner")),
              <.label("Tags", DemoStyles.FormFieldLabel),
              <.span(
                DemoStyles.FormField,
                DemoStyles.HorizontalStack,
                Tag(value = "Vanilla").withMods(mouseEntered("Vanilla Tag")),
                Tag(value = "Info", severity = Tag.Severity.Info)
                  .withMods(mouseEntered("Info Tag")),
                Tag(value = "Danger", severity = Tag.Severity.Danger),
                Tag(value = "Success", severity = Tag.Severity.Success),
                Tag(value = "Warning", severity = Tag.Severity.Warning, icon = "pi pi-bolt"),
                Tag(value = "Well Rounded Danger", severity = Tag.Severity.Danger, rounded = true)
              ),
              <.div(
                DemoStyles.HorizontalStack,
                RadioButton("option 1",
                            id = "option-1",
                            name = "option",
                            checked = radioButton.value === "option-1",
                            onChange = (a, c) => radioButton.setState(a).when_(c)
                ),
                <.label("Option 1", ^.htmlFor := "option-1", DemoStyles.FormFieldLabel),
                RadioButton("option 2",
                            id = "option-2",
                            name = "option",
                            checked = radioButton.value === "option-2",
                            onChange = (a, c) => radioButton.setState(a).when_(c)
                ),
                <.label("Option 2", ^.htmlFor := "option-2", DemoStyles.FormFieldLabel)
              ),
              <.label("Message", DemoStyles.FormFieldLabel),
              <.span(DemoStyles.FormField)(
                Message(severity = Message.Severity.Error, text = "This is an error message"),
                <.br,
                Message(severity = Message.Severity.Info, text = "This is an info message"),
                <.br,
                Message(
                  severity = Message.Severity.Success,
                  text = "This is a success message"
                ),
                <.br,
                Message(
                  severity = Message.Severity.Warning,
                  text = "This is a warning message"
                )(mouseEntered("Warning Message")),
                <.br,
                Message(
                  text = "This is a message with a custom icon",
                  icon = "pi pi-bolt"
                )
              ),
              <.label("Tooltip", DemoStyles.FormFieldLabel),
              <.span(DemoStyles.FormField) {
                val TooltipTarget = Css("tooltip-target")

                React.Fragment(
                  Tooltip(targetCss = TooltipTarget, position = Tooltip.Position.Top)(
                    <.h1("I am a big tooltip")
                  ),
                  <.span(TooltipTarget)("I am a <span>. Hover over me to see a tooltip."),
                  <.br,
                  <.br,
                  <.span(TooltipTarget)("I am another <span>. Hover over me to see a tooltip too.")
                    .withTooltipOptions(
                      content = "I'm another tooltip!",
                      position = Tooltip.Position.Bottom
                    ),
                  <.br,
                  <<.svg(
                    ^.width  := "300px",
                    ^.height := "130px",
                    ^.xmlns  := "http://www.w3.org/2000/svg"
                  )(
                    <<.path(
                      ^.id           := "lineAC",
                      ^^.d           := "M 30 180 q 150 -250 300 0",
                      ^^.stroke      := "blue",
                      ^^.strokeWidth := "2",
                      ^^.fill        := "none"
                    ),
                    <<.text(TooltipTarget, ^^.fill := "red", ^^.fontSize := "24px")(
                      <<.textPath(^.href := "#lineAC", startOffset := "80")(
                        "It works with SVG too!"
                      )
                    ).withTooltipOptions(
                      content = "I'm an SVG tooltip!",
                      mouseTrack = true
                    )
                  ),
                  <.br,
                  Tooltip.Fragment(
                    position = Tooltip.Position.Top,
                    content = <.h4(^.color.yellow)("I'm a tooltip in Tooltip.Fragment")
                  )(<.span("I'm a span with my own, non-shared, tooltip")),
                  <.br,
                  <.br,
                  <.span("I'm another span with my own, non-shared, tooltip").withTooltip(
                    position = Tooltip.Position.Bottom,
                    content = <.h4(^.color.teal)("I'm a tooltip rendered with .withTooltip")
                  )
                )
              }
            )
          )
        )
    }
}
