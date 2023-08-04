// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.common.arb

import lucuma.react.common._
import org.scalacheck.Arbitrary
import org.scalacheck.Arbitrary._
import org.scalacheck.Cogen

trait ArbSize {
  implicit val arbSize: Arbitrary[Size] = Arbitrary {
    for {
      w <- arbitrary[Double]
      h <- arbitrary[Double]
    } yield Size(w, h)
  }

  implicit val cogenSize: Cogen[Size] =
    Cogen[(Double, Double)].contramap(x => (x.width, x.height))
}

object ArbSize extends ArbSize
