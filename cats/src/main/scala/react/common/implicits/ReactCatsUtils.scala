package react

package common

import scala.scalajs.js
import _root_.cats.Eq
import _root_.cats.Show
import _root_.cats.syntax.eq._

package implicits {

  trait ReactCatsImplicits {
    implicit def jsUndefOr[A: Eq]: Eq[js.UndefOr[A]] = Eq.instance { (a, b) =>
      (a.toOption, b.toOption) match {
        case (Some(a), Some(b)) => a === b
        case _                  => false
      }
    }

    implicit val jsObjEq: Eq[js.Object] = Eq.instance { (a, b) =>
      val aDict = a.asInstanceOf[js.Dictionary[js.Any]]
      val bDict = b.asInstanceOf[js.Dictionary[js.Any]]
      (aDict.keySet == bDict.keySet) &&
      aDict.keySet.forall(key => aDict(key) === bDict(key))
    }

    implicit val jsShow: Show[js.Object] = Show.show { a =>
      val aDict = a.asInstanceOf[js.Dictionary[Any]]
      aDict.keySet.map(key => s"$key=${aDict(key)}").mkString("{", ",", "}")
    }

    implicit val jsAnyEq: Eq[js.Any] = Eq.instance { (a, b) =>
      (a, b) match {
        case (a: js.Array[_], b: js.Array[_]) =>
          a.length == b.length &&
            a.zip(b).forall { x =>
              jsAnyEq.eqv(x._1.asInstanceOf[js.Any], x._2.asInstanceOf[js.Any])
            }

        case _
            if a.asInstanceOf[js.Dynamic].constructor == js.constructorOf[js.Object] &&
              b.asInstanceOf[js.Dynamic].constructor == js.constructorOf[js.Object] =>
          val aDict = a.asInstanceOf[js.Dictionary[js.Any]]
          val bDict = b.asInstanceOf[js.Dictionary[js.Any]]
          (aDict.keySet == bDict.keySet) &&
          aDict.keySet.forall(key => aDict(key) === bDict(key))

        case _ =>
          a == b
      }
    }
  }
}

package object implicits extends ReactCatsImplicits
