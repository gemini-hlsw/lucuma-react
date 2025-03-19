// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.primereact.hooks

import japgolly.scalajs.react.HookResult
import japgolly.scalajs.react.callback.Callback

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("primereact/hooks", "useTimeout")
private def useTimeoutJs[A](
  callback:    js.Function0[Unit],
  delayMillis: Double,
  active:      js.UndefOr[Boolean]
): Unit = js.native

def useTimeout(
  callback:    Callback,
  delayMillis: Int,
  active:      js.UndefOr[Boolean] = js.undefined
): HookResult[Unit] =
  HookResult(useTimeoutJs(() => callback.runNow(), delayMillis, active))
