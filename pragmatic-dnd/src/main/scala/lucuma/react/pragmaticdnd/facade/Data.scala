// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.pragmaticdnd.facade

import scalajs.js

@js.native
trait Data[D] extends js.Object:
  var value: D

object Data:
  def apply[D](value: D): Data[D] =
    val p = (new js.Object).asInstanceOf[Data[D]]
    p.value = value
    p

  given [D]: Conversion[D, Data[D]] with
    def apply(d: D): Data[D] = Data(d)

  given [D]: Conversion[Data[D], D] with
    def apply(data: Data[D]): D = data.value
