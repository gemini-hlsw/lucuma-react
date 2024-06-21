// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import lucuma.react.table.facade.UndefinedPriorityJs

enum UndefinedPriority(private[table] val toJs: UndefinedPriorityJs):
  case First extends UndefinedPriority(UndefinedPriorityJs.First)
  case Last  extends UndefinedPriority(UndefinedPriorityJs.Last)
  case Tied  extends UndefinedPriority(UndefinedPriorityJs.Tied)
  case Min   extends UndefinedPriority(UndefinedPriorityJs.Min)
  case Max   extends UndefinedPriority(UndefinedPriorityJs.Max)

object UndefinedPriority:
  private[table] def fromJs(rawValue: UndefinedPriorityJs): UndefinedPriority =
    (rawValue: Any) match
      case UndefinedPriorityJs.First => First
      case UndefinedPriorityJs.Last  => Last
      case UndefinedPriorityJs.Tied  => Tied
      case UndefinedPriorityJs.Min   => Min
      case UndefinedPriorityJs.Max   => Max
