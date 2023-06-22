// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.common.implicits

import cats.*
import cats.syntax.all.*

import scala.scalajs.js

given jsUndefOrEq[A: Eq]: Eq[js.UndefOr[A]] =
  Eq.instance { (a, b) =>
    (a.toOption, b.toOption) match {
      case (Some(a), Some(b)) => a === b
      case _                  => false
    }
  }

given Eq[js.Object] = Eq.instance { (a, b) =>
  val aDict = a.asInstanceOf[js.Dictionary[js.Any]]
  val bDict = b.asInstanceOf[js.Dictionary[js.Any]]
  aDict.keySet == bDict.keySet &&
  aDict.keySet.forall(key => aDict(key) === bDict(key))
}

given Show[js.Object] = Show.show { a =>
  val aDict = a.asInstanceOf[js.Dictionary[Any]]
  aDict.keySet.map(key => s"$key=${aDict(key)}").mkString("{", ",", "}")
}

given jsAnyEq: Eq[js.Any] = Eq.instance { (a, b) =>
  (a, b) match {
    case (a: js.Array[?], b: js.Array[?]) =>
      a.length == b.length &&
      a.zip(b).forall { x =>
        jsAnyEq.eqv(x._1.asInstanceOf[js.Any], x._2.asInstanceOf[js.Any])
      }

    case _
        if a.asInstanceOf[js.Dynamic].constructor == js.constructorOf[js.Object] &&
          b.asInstanceOf[js.Dynamic].constructor == js.constructorOf[js.Object] =>
      val aDict = a.asInstanceOf[js.Dictionary[js.Any]]
      val bDict = b.asInstanceOf[js.Dictionary[js.Any]]
      aDict.keySet == bDict.keySet &&
      aDict.keySet.forall(key => aDict(key) === bDict(key))

    case _ =>
      a == b
  }
}
