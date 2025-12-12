// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.primereact

import japgolly.scalajs.react.vdom.VdomNode
import lucuma.typed.StBuildingComponent
import lucuma.typed.primereact.primereactStrings.self
import org.scalajs.dom.HTMLElement

import scalajs.js

// This seems to be necessary for the compiler to pick up ST's implicit conversion.
given [A <: js.Object]: Conversion[StBuildingComponent[A], VdomNode] with
  def apply(a: StBuildingComponent[A]): VdomNode = StBuildingComponent.make(a)

enum AppendTo(val toJs: self | HTMLElement):
  case Self                          extends AppendTo(self)
  case Element(element: HTMLElement) extends AppendTo(element)
