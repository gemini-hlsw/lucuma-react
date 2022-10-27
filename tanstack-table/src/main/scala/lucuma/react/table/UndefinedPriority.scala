// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import lucuma.react.table.facade.UndefinedPriorityJs

enum UndefinedPriority(private[table] val toJs: UndefinedPriorityJs):
  case Tied   extends UndefinedPriority(false)
  case Higher extends UndefinedPriority(-1)
  case Lower  extends UndefinedPriority(1)

object UndefinedPriority:
  private[table] def fromJs(rawValue: UndefinedPriorityJs): UndefinedPriority =
    rawValue match
      case _: false => UndefinedPriority.Tied
      case _: -1    => UndefinedPriority.Higher
      case _: 1     => UndefinedPriority.Lower
