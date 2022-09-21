// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.primereact

import react.common.Css
import reactST.StBuildingComponent

import scalajs.js

extension [C <: js.Object, B <: StBuildingComponent[C]](b: B)
  def applyOrNot[A](a: js.UndefOr[A], f: (B, A) => B): B = a.fold(b)(a => f(b, a))
