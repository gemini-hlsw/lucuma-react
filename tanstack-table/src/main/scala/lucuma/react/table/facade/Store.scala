// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table.facade

import scala.scalajs.js

/**
 * Minimal typing of the TanStack `Store` (v9's reactive state backbone) for the subset the facade
 * exposes: reading the current state and subscribing to changes. Mirrors `@tanstack/store`'s
 * `Store<T>` / `Subscription`.
 *
 * The table's `store` carries the full `TableState`; `subscribe`'s callback receives the new state
 * value and the returned `Subscription` provides `unsubscribe()`.
 */
@js.native
trait Store[T] extends js.Object:
  def state: T                                                     = js.native
  def setState(updater:       js.Any): Unit                        = js.native
  def subscribe(observerOrFn: js.Function1[T, Unit]): Subscription = js.native

@js.native
trait Subscription extends js.Object:
  def unsubscribe(): Unit = js.native
