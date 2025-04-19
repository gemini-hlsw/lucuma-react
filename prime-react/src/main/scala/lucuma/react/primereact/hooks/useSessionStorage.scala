// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.primereact.hooks

import japgolly.scalajs.react.HookResult
import japgolly.scalajs.react.callback.Callback

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("primereact/hooks", "useSessionStorage")
private def useSessionStorageJs[A](initial: A, key: String): js.Tuple2[A, js.Function1[A, Unit]] =
  js.native

def useSessionStorage[A](initial: A, key: String): HookResult[UseStorage[A]] = {
  val arr = useSessionStorageJs(initial, key)
  HookResult:
    UseStorage(arr._1, a => Callback(arr._2(a)))
}
