// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.primereact.hooks

import japgolly.scalajs.react.HookResult
import japgolly.scalajs.react.callback.Callback

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("primereact/hooks", "useDebounce")
private def useDebounceJs[A](
  initial:     A,
  delayMillis: Double
): js.Tuple3[A, A, js.Function1[A, Unit]] = js.native

case class UseDebounce[A](value: A, debouncedValue: A, set: A => Callback)

def useDebounce[A](initial: A, delayMillis: Int): HookResult[UseDebounce[A]] = {
  val arr = useDebounceJs(initial, delayMillis.toDouble)
  HookResult:
    UseDebounce(arr._1, arr._2, a => Callback(arr._3(a)))
}
