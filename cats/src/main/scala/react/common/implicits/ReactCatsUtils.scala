package react

package common

import scala.scalajs.js
import scala.scalajs.js.|
import japgolly.scalajs.react.raw.JsNumber
import cats._
import cats.implicits._
import react.common.style._

package implicits {

  trait ReactCatsImplicits {
    implicit def jsUndefOrEq[A: Eq]: Eq[js.UndefOr[A]] = Eq.instance { (a, b) =>
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

    implicit val jsNumberEq: Eq[JsNumber] = Eq.instance { (a, b) =>
      (a: Any, b: Any) match {
        case (a: Double, b: Double) => a === b
        case (a: Int, b: Int)       => a === b
        case (a: Long, b: Long)     => a === b
        case (a: Float, b: Float)   => a === b
        case (a: Short, b: Short)   => a === b
        case (a: Byte, b: Byte)     => a === b
        case _                      => false
      }
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

    implicit val styleMemberEq: Eq[String | Int] = Eq.instance { (a, b) =>
      (a: Any, b: Any) match {
        case (a: String, b: String) => a === b
        case (a: Int, b: Int)       => a === b
        case _                      => false
      }
    }
    implicit val styleEq: Eq[Style] = Eq.by(_.styles)
    implicit val styleShow: Show[Style] =
      Show.show(_.styles.map { case (k, v) => s"$k=$v " }.mkString("Style(", ";", ")"))

    implicit val cssEq: Eq[Css] = Eq.by(_.htmlClass)
    implicit val cssShow: Show[Css] =
      Show.show(x => x: String)

    implicit val cssMonoid: Monoid[Css] =
      Monoid[List[String]].imap(Css.apply)(_.htmlClasses)
  }

}

package object implicits extends ReactCatsImplicits
