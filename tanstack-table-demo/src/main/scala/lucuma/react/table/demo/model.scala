// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table.demo

import japgolly.scalajs.react.Reusability

case class Details(year: Int, pickups: Int, color: String)

case class Guitar(id: Int, make: String, model: String, details: Details)

case class Person(id: Int, first: String, last: String, age: Int)
