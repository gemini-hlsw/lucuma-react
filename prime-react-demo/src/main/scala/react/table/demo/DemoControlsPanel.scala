// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.table.demo

import cats.syntax.all._
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import react.common.*
import react.primereact.*

case class DemoControlsPanel() extends ReactFnProps[DemoControlsPanel](DemoControlsPanel.component)

object DemoControlsPanel {
  def mouseEntered(msg: String) = ^.onMouseEnter --> Callback.log(s"Mouse entered: $msg")

  private val component = ScalaFnComponent
    .withHooks[DemoControlsPanel]
    .useState("Text to edit")        // for InputText
    .useState("Resizeable TextArea") // for InputTextarea
    .useState(2)                     // for Dropdown
    .useState(3.some)                // for DropdownOptional
    .useState(false)                 // for InputSwitch
    .useState(false)                 // for Checkbox
    .useState((25.0, 75.0))          // for SliderRange
    .useState(0.0)                   // for Slider
    .useState(10.0)                  // for Knob
    .useState(0.0)                   // for Knob (readonly)
    .useState(11.0)                  // for Nigel Tufnel Knob
    .useState(1)                     // for SelectButton
    .useState(2.some)                // for SelectButtonOptional
    .useState(List(1, 3))            // for SelectButtonMultiple
    .useState(false)                 // for ToggleButton
    .useState(50)                    // for ProgressBar
    .useState("option-1")            // for RadioButton
    .render {
      (
        _,
        inputText,
        inputTextarea,
        dropdown,
        dropdownOptional,
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
              <.label("InputText", ^.htmlFor         := "input-text", DemoStyles.FormFieldLabel),
              InputText(
                id = "input-text",
                value = inputText.value,
                placeholder = "No text",
                onChange = e => inputText.setState(e.target.value),
                clazz = DemoStyles.FormField,
                onFocus = e => Callback.log(s"Input Text focused. Value: ${e.target.value}"),
                onBlur = e => Callback.log(s"Input Text blurred. Value: ${e.target.value}")
              ).withMods(mouseEntered("InputText")),
              <.label("InputTextarea", ^.htmlFor     := "input-text-area", DemoStyles.FormFieldLabel),
              InputTextarea(id = "input-text-area",
                            value = inputTextarea.value,
                            rows = 6,
                            onChange = e => inputTextarea.setState(e.target.value),
                            clazz = DemoStyles.FormField
              ).withMods(mouseEntered("InputTextarea"),
                         ^.onFocus ==> { (e: ReactFocusEventFromInput) =>
                           Callback.log(s"Focused TextArea: ${e.target.value}")
                         }
              ),
              <.label("Dropdown", ^.htmlFor          := "dropdown", DemoStyles.FormFieldLabel),
              Dropdown(
                id = "dropdown",
                value = dropdown.value,
                options = options,
                onChange = a => dropdown.setState(a),
                clazz = DemoStyles.FormField
              ).withMods(mouseEntered("Dropdown")),
              <.label("DropdownOptional",
                      ^.htmlFor                      := "dropdown-optional",
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
              <.label("InputSwitch", ^.htmlFor       := "input-switch", DemoStyles.FormFieldLabel),
              InputSwitch(
                inputId = "input-switch",
                checked = inputSwitch.value,
                onChange = b => inputSwitch.setState(b)
              )(mouseEntered("InputSwitch")),
              <.label("Checkbox", ^.htmlFor          := "checkbox", DemoStyles.FormFieldLabel),
              Checkbox(
                inputId = "checkbox",
                checked = checkbox.value,
                onChange = b => checkbox.setState(b)
              )(mouseEntered("Checkbox")),
              <.label("Slider", ^.htmlFor            := "slider", DemoStyles.FormFieldLabel),
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
              <.label("SliderRange", ^.htmlFor       := "slider-range", DemoStyles.FormFieldLabel),
              <.span(
                DemoStyles.FormField,
                SliderRange(id = "slider-range",
                            value = rangeSlider.value,
                            onChange = t => rangeSlider.setState(t)
                )(mouseEntered("SliderRange")),
                s"[${rangeSlider.value._1}, ${rangeSlider.value._2}]"
              ),
              <.label("Knob (steps of 5)", ^.htmlFor := "knob", DemoStyles.FormFieldLabel),
              Knob(id = "knob", value = knob.value, step = 5, size = 75, onChange = knob.setState)(
                mouseEntered("Knob")
              ),
              <.label("Knob for Progress", ^.htmlFor := "readonly-knob", DemoStyles.FormFieldLabel),
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
                      ^.htmlFor                      := "nigel-tufnel",
                      DemoStyles.FormFieldLabel
              ),
              Knob(id = "nigel-tufnel",
                   value = nigelTufnel.value,
                   max = 11,
                   onChange = nigelTufnel.setState,
                   strokeWidth = 5,
                   valueTemplate = "Vol: {value}"
              ),
              <.label("SelectButton", ^.htmlFor      := "select-button", DemoStyles.FormFieldLabel),
              SelectButton(
                id = "select-button",
                value = selectButton.value,
                options = options,
                onChange = selectButton.setState
              )(mouseEntered("SelectButton")),
              <.label("SelectButtonOptional",
                      ^.htmlFor                      := "select-button-optional",
                      DemoStyles.FormFieldLabel
              ),
              SelectButtonOptional(
                id = "select-button-optional",
                value = selectButtonOptional.value,
                options = options,
                onChange = selectButtonOptional.setState
              )(mouseEntered("SelectButtonOptional")),
              <.label("SelectButtonMultiple",
                      ^.htmlFor                      := "select-button-multiple",
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
              <.label("ProgressBar 1",
                      ^.htmlFor                      := "progress-indeterminate",
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
              <.label("ProgressBar 2", ^.htmlFor     := "progress-vanilla", DemoStyles.FormFieldLabel),
              ProgressBar(id = "progress-vanilla", value = progressBar.value)(
                mouseEntered("ProgressBar")
              ),
              <.label("ProgressBar 3", ^.htmlFor     := "progress-template", DemoStyles.FormFieldLabel),
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
              )
            )
          )
        )
    }
}
