// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

enum Visibility(val value: Boolean):
  case Visible extends Visibility(true)
  case Hidden  extends Visibility(false)

object Visibility:
  def fromVisible(v: Boolean): Visibility =
    if (v) Visible else Hidden
