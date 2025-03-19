// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.primereact.hooks

import japgolly.scalajs.react.HookResult
import japgolly.scalajs.react.callback.Callback

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("primereact/hooks", "useInterval")
private def useIntervalJs[A](
  callback:    js.Function0[Unit],
  delayMillis: Double,
  active:      js.UndefOr[Boolean]
): Unit = js.native

def useInterval(
  callback:    Callback,
  delayMillis: Int,
  active:      js.UndefOr[Boolean] = js.undefined
): HookResult[Unit] =
  HookResult(useIntervalJs(() => callback.runNow(), delayMillis, active))
