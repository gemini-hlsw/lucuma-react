// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table.demo

import japgolly.scalajs.react.Reusability

case class Details(year: Int, pickups: Int, color: String)
object Details {
  given Reusability[Details] = Reusability.by_==
}

case class Guitar(id: Int, make: String, model: String, details: Details)

object Guitar {
  given Reusability[Guitar] = Reusability.by_==
}

case class Person(id: Int, first: String, last: String, age: Int)

object Person {
  given Reusability[Person] = Reusability.by_==
}
