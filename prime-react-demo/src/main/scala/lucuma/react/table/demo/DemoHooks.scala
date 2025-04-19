// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table.demo

import japgolly.scalajs.react.*
import japgolly.scalajs.react.vdom.html_<^.*
import lucuma.react.primereact.Button
import lucuma.react.primereact.InputSwitch
import lucuma.react.primereact.InputText
import lucuma.react.primereact.Panel
import lucuma.react.primereact.hooks.*
import org.scalajs.dom

object DemoHooks:
  val component = ScalaFnComponent[Unit]: _ =>
    for
      debounced     <- useDebounce("hello", 500)
      seconds       <- useState(0)
      secondsActive <- useState(true)
      _             <- useInterval(seconds.modState(_ + 1), 1000, secondsActive.value)
      timeout       <- useState("")
      timeoutActive <- useState(false)
      _             <- useTimeout(timeout.setState("TIMEOUT!"), 1000, timeoutActive.value)
      localValue    <- useLocalStorage("localValue", "local-storage-demo")
      sessionValue  <- useSessionStorage("sessionValue", "session-storage-demo")
    yield Panel(header = "Hooks")(
      <.div(DemoStyles.VerticalStack)(
        <.div(DemoStyles.FormColumn)(
          <.label("useDebounce (500ms)", ^.htmlFor := "useDebounce", DemoStyles.FormFieldLabel),
          InputText(
            id = "useDebounce",
            value = debounced.value,
            onChange = e => debounced.set(e.target.value)
          ),
          <.label("Debounced value:",    ^.htmlFor := "useDebounceValue", DemoStyles.FormFieldLabel),
          <.span(^.id                              := "useDebounceValue", debounced.debouncedValue),
          <.label("useInterval (1s)",    ^.htmlFor := "useInterval", DemoStyles.FormFieldLabel),
          <.span(^.id                              := "useInterval", seconds.value),
          <.label("",                    ^.htmlFor := "useIntervalActive", DemoStyles.FormFieldLabel),
          InputSwitch(
            id = "useIntervalActive",
            checked = secondsActive.value,
            onChange = secondsActive.setState(_)
          ),
          <.label("useTimeout (1s)",     ^.htmlFor := "useTimeout", DemoStyles.FormFieldLabel),
          <.span(^.id                              := "useTimeout", timeout.value),
          <.label("",                    ^.htmlFor := "useTimeoutActive", DemoStyles.FormFieldLabel),
          InputSwitch(
            id = "useTimeoutActive",
            checked = timeoutActive.value,
            onChange = timeoutActive.setState(_) >> timeout.setState("")
          ),
          <.label("useLocalStorage",     ^.htmlFor := "useLocalStorage", DemoStyles.FormFieldLabel),
          InputText(
            id = "useLocalStorage",
            value = localValue.value,
            onChange = e => localValue.set(e.target.value)
          ),
          <.label("",                    ^.htmlFor := "useLocalStorageRefresh", DemoStyles.FormFieldLabel),
          <.span(^.id := "useLocalStorageRefresh")(
            Button(label = "Refresh Page", onClick = Callback(dom.window.location.reload()))
          ),
          <.label("useSessionStorage",   ^.htmlFor := "useSessionStorage", DemoStyles.FormFieldLabel),
          InputText(
            id = "useSessionStorage",
            value = sessionValue.value,
            onChange = e => sessionValue.set(e.target.value)
          ),
          <.label("",                    ^.htmlFor := "useSessionStorageRefresh", DemoStyles.FormFieldLabel),
          <.span(^.id := "useSessionStorageRefresh")(
            Button(label = "Refresh Page", onClick = Callback(dom.window.location.reload()))
          )
        )
      )
    )
