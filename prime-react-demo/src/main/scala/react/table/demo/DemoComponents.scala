// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.table.demo

import cats.syntax.all._
import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import react.common.*
import react.primereact.*

object DemoComponents {
  val component =
    ScalaFnComponent
      .withHooks[Unit]
      .useState(false)                 // show dialog
      .useState(false)                 // panel collapsed
      .useState("Text to edit")        // for InputText
      .useState("Resizeable TextArea") // for InputTextarea
      .useState(2)                     // for Dropdown
      .useState(3.some)                // for DropdownOptional
      .useState(false)                 // for InputSwitch
      .useState(false)                 // for Checkbox
      .useState((25.0, 75.0))          // for SliderRange
      .useState(0.0)                   // for Slider
      .useState(1)                     // for SelectButton
      .useState(2.some)                // for SelectButtonOptional
      .useState(List(1, 3))            // for SelectButtonMultiple
      .useState(50)                    // for ProgressBar
      .render {
        (
          _,
          showDialog,
          panelCollapsed,
          inputText,
          inputTextarea,
          dropdown,
          dropdownOptional,
          inputSwitch,
          checkbox,
          rangeSlider,
          slider,
          selectButton,
          selectButtonOptional,
          selectButtonMultiple,
          progressBar
        ) =>
          val options: List[SelectItem[Int]] =
            List(
              SelectItem(1, "Option 1"),
              SelectItem(2, "Option 2"),
              SelectItem(3, "Option 3"),
              SelectItem(4, "Option 4", disabled = true),
              SelectItem(5, "Option 5")
            )

          val decreaseProgress: Callback            = {
            val current = progressBar.value
            if current > 0 then progressBar.setState(current - 1) else Callback.empty
          }
          val increaseProgress: Callback            = {
            val current = progressBar.value
            if current < 100 then progressBar.setState(current + 1) else Callback.empty
          }
          def progressTemplate(v: Double): VdomNode =
            if (v < 50) "Less than half"
            else if (v > 50) "More than half"
            else "Half way"

          <.div(
            DemoStyles.VerticalStack,
            Card(
              title = "I am a Card title",
              subTitle = "And subtitle",
              header = "The card header could be an image?",
              footer = Button(
                onClick = showDialog.setState(true),
                Button.Size.Small,
                label = "Show Dialog",
                severity = Button.Severity.Warning
              )
            )("Card Contents"),
            Panel(
              header = React.Fragment(<.div("A collapsable Panel containing a Splitter"),
                                      <.small("* See the console for event information")
              ),
              toggleable = true,
              collapsed = panelCollapsed.value,
              onCollapse = Callback.log("Panel onCollapse"),
              onExpand = Callback.log("Panel onExpand"),
              onToggle = b => Callback.log(s"Panel onToggle($b)") >> panelCollapsed.setState(b)
            )(
              Splitter(onResizeEnd =
                (left, right) => Callback.log(s"Splitter onResizeEnd($left, $right)")
              )(
                SplitterPanel(size = 30)(
                  "Splitter left side panel"
                ),
                SplitterPanel(size = 70)(
                  "Splitter right side panel"
                )
              )
            ),
            Panel(
              header = "Panel for Controls"
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
                  <.label("InputText", ^.htmlFor    := "input-text", DemoStyles.FormFieldLabel),
                  InputText(id = "input-text",
                            value = inputText.value,
                            placeholder = "No text",
                            onChange = e => inputText.setState(e.target.value),
                            clazz = DemoStyles.FormField
                  ),
                  <.label("InputTextarea",
                          ^.htmlFor                 := "input-text-area",
                          DemoStyles.FormFieldLabel
                  ),
                  InputTextarea(id = "input-text-area",
                                value = inputTextarea.value,
                                rows = 6,
                                onChange = e => inputTextarea.setState(e.target.value),
                                clazz = DemoStyles.FormField
                  ),
                  <.label("Dropdown", ^.htmlFor     := "dropdown", DemoStyles.FormFieldLabel),
                  Dropdown(
                    id = "dropdown",
                    value = dropdown.value,
                    options = options,
                    onChange = a => dropdown.setState(a),
                    clazz = DemoStyles.FormField
                  ),
                  <.label("DropdownOptional",
                          ^.htmlFor                 := "dropdown-optional",
                          DemoStyles.FormFieldLabel
                  ),
                  DropdownOptional(
                    id = "dropdown-optional",
                    value = dropdownOptional.value,
                    options = options,
                    showClear = true,
                    onChange = a => dropdownOptional.setState(a),
                    clazz = DemoStyles.FormField
                  ),
                  <.label("InputSwitch", ^.htmlFor  := "input-switch", DemoStyles.FormFieldLabel),
                  InputSwitch(
                    inputId = "input-switch",
                    checked = inputSwitch.value,
                    onChange = b => inputSwitch.setState(b)
                  ),
                  <.label("Checkbox", ^.htmlFor     := "checkbox", DemoStyles.FormFieldLabel),
                  Checkbox(
                    inputId = "checkbox",
                    checked = checkbox.value,
                    onChange = b => checkbox.setState(b)
                  ),
                  <.label("Slider", ^.htmlFor       := "slider", DemoStyles.FormFieldLabel),
                  <.span(
                    DemoStyles.FormField,
                    Slider(id = "slider",
                           value = slider.value,
                           onChange = t => slider.setState(t),
                           min = -10.0,
                           max = 10.0,
                           step = 2,
                           orientation = Layout.Vertical
                    ),
                    slider.value
                  ),
                  <.label("SliderRange", ^.htmlFor  := "slider-range", DemoStyles.FormFieldLabel),
                  <.span(
                    DemoStyles.FormField,
                    SliderRange(id = "slider-range",
                                value = rangeSlider.value,
                                onChange = t => rangeSlider.setState(t)
                    ),
                    s"[${rangeSlider.value._1}, ${rangeSlider.value._2}]"
                  ),
                  <.label("SelectButton", ^.htmlFor := "select-button", DemoStyles.FormFieldLabel),
                  SelectButton(
                    id = "select-button",
                    value = selectButton.value,
                    options = options,
                    onChange = selectButton.setState
                  ),
                  <.label("SelectButtonOptional",
                          ^.htmlFor                 := "select-button-optional",
                          DemoStyles.FormFieldLabel
                  ),
                  SelectButtonOptional(
                    id = "select-button-optional",
                    value = selectButtonOptional.value,
                    options = options,
                    onChange = selectButtonOptional.setState
                  ),
                  <.label("SelectButtonMultiple",
                          ^.htmlFor                 := "select-button-multiple",
                          DemoStyles.FormFieldLabel
                  ),
                  SelectButtonMultiple(
                    id = "select-button-multiple",
                    value = selectButtonMultiple.value,
                    options = options,
                    onChange = selectButtonMultiple.setState,
                    itemTemplate = si =>
                      if (si.disabled.getOrElse(false)) <.s(si.label)
                      else <.span(si.label)
                  ),
                  <.label("ProgressBar 1",
                          ^.htmlFor                 := "progress-indeterminate",
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
                  <.label("ProgressBar 2",
                          ^.htmlFor                 := "progress-vanilla",
                          DemoStyles.FormFieldLabel
                  ),
                  ProgressBar(id = "progress-vanilla", value = progressBar.value),
                  <.label("ProgressBar 3",
                          ^.htmlFor                 := "progress-template",
                          DemoStyles.FormFieldLabel
                  ),
                  ProgressBar(id = "progress-template",
                              value = progressBar.value,
                              displayValueTemplate = progressTemplate
                  ),
                  <.label("Tags", DemoStyles.FormFieldLabel),
                  <.span(
                    DemoStyles.FormField,
                    DemoStyles.HorizontalStack,
                    Tag(value = "Vanilla"),
                    Tag(value = "Info", severity = Tag.Severity.Info),
                    Tag(value = "Danger", severity = Tag.Severity.Danger),
                    Tag(value = "Success", severity = Tag.Severity.Success),
                    Tag(value = "Warning", severity = Tag.Severity.Warning),
                    Tag(value = "Well Rounded Danger",
                        severity = Tag.Severity.Danger,
                        rounded = true
                    )
                  )
                )
              )
            ),
            Dialog(
              onHide = showDialog.setState(false),
              visible = showDialog.value,
              header = "A Dialog",
              footer = Button(onClick = showDialog.setState(false),
                              size = Button.Size.Small,
                              rounded = true,
                              outlined = true
              )("Close Me"),
              position = Dialog.Position.Top,
              dismissableMask = true,
              draggable = true
            )("You can drag me around!")
          )
      }
}
