// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table.facade

import japgolly.scalajs.react.facade.React.{ComponentType, Node}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** Mirror of the v9 `Renderable<TProps>` type. */
type Renderable[TProps <: js.Object] = Node | ComponentType[TProps]

/**
 * Native binding to the `@tanstack/react-table/flex-render` subpath export.
 *
 * We bind `./flex-render` directly rather than the generated `distFlexRenderMod_`, because that
 * module's `@JSImport` uses `./dist/FlexRender`, which both Node's `exports` enforcement and Vite's
 * resolver reject (`@tanstack/react-table` only exports `./flex-render`).
 */
@js.native
@JSImport("@tanstack/react-table/flex-render", JSImport.Namespace)
object FlexRenderRaw extends js.Object:
  def flexRender[TProps <: js.Object](comp: Renderable[TProps], props: TProps): Node = js.native
