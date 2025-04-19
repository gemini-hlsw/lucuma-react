// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.common

import cats.*

import scala.scalajs.js

@js.native
trait Size extends js.Object:
  var height: Double
  var width: Double

object Size:
  def apply(height: Double, width: Double): Size =
    val p = (new js.Object).asInstanceOf[Size]
    p.height = height
    p.width = width
    p

  given Eq[Size] = Eq.by(x => (x.width, x.height))
