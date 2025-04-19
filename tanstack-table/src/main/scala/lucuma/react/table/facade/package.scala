// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table.facade

type UndefinedPriorityJs = UndefinedPriorityJs.First.type | UndefinedPriorityJs.Last.type |
  UndefinedPriorityJs.Tied.type | UndefinedPriorityJs.Min.type | UndefinedPriorityJs.Max.type

object UndefinedPriorityJs:
  val First: "first" = "first"
  val Last: "last"   = "last"
  val Tied: false    = false
  val Min: -1        = -1
  val Max: 1         = 1
