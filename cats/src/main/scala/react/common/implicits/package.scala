package react

package common

import scala.scalajs.js
import scala.scalajs.js.|
import cats._
import cats.syntax.all._

package implicits {
  trait ReactCatsImplicits {
    implicit def jsUndefOrEq[A: Eq]: Eq[js.UndefOr[A]] =
      Eq.instance { (a, b) =>
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

    implicit val styleMemberEq: Eq[String | Int] = Eq.instance { (a, b) =>
      (a: Any, b: Any) match {
        case (a: String, b: String) => a === b
        case (a: Int, b: Int)       => a === b
        case _                      => false
      }
    }
    implicit val styleEq: Eq[Style]              = Eq.by(_.styles)
    implicit val styleShow: Show[Style]          =
      Show.show(_.styles.map { case (k, v) => s"$k=$v " }.mkString("Style(", ";", ")"))
    implicit val styleMonoid: Monoid[Style]      = new Monoid[Style] {
      override val empty: Style                       = Style(Map.empty)
      override def combine(a: Style, b: Style): Style =
        Style(a.styles ++ b.styles)
    }

    implicit val cssOrder: Order[Css] = Order.by(_.htmlClass)

    implicit val cssShow: Show[Css] =
      Show.show(_.htmlClass)

    implicit val cssMonoid: Monoid[Css] =
      Monoid[List[String]].imap(Css.apply)(_.htmlClasses)

    implicit val sizeEq: Eq[Size] = Eq.by(x => (x.width, x.height))

  }
}

package object implicits extends ReactCatsImplicits
