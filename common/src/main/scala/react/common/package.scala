package react

import scala.scalajs.js
import scala.scalajs.js.|
import js.JSConverters._

package object common {

  def merge(a: js.Object, b: js.Object): js.Object = {
    val m = js.Dictionary.empty[js.Any]
    val c = a.asInstanceOf[js.Dictionary[js.Any]]
    for {
      p <- js.Object.getOwnPropertyNames(a)
      v <- c.get(p)
    } yield m(p) = v

    val d = b.asInstanceOf[js.Dictionary[js.Any]]
    for {
      p <- js.Object.getOwnPropertyNames(b)
      v <- d.get(p)
    } yield m(p) = v

    m.asInstanceOf[js.Object]
  }
}

package common {

  trait EnumValue[A] {
    def value(a: A): String
  }

  object EnumValue {
    @inline final def apply[A](implicit ev: EnumValue[A]): EnumValue[A] = ev

    def instance[A](f: A => String): EnumValue[A] =
      new EnumValue[A] {
        def value(a: A): String = f(a)
      }

    def toLowerCaseString[A]: EnumValue[A] =
      new EnumValue[A] {
        def value(a: A): String = a.toString.toLowerCase
      }

  }

  trait EnumValueB[A] {
    def value(a: A): Boolean | String
  }

  object EnumValueB {
    @inline final def apply[A](implicit ev: EnumValueB[A]): EnumValueB[A] = ev

    def instance[A](f: A => Boolean | String): EnumValueB[A] =
      new EnumValueB[A] {
        def value(a: A): Boolean | String = f(a)
      }

    def toLowerCaseStringT[A](trueValue: A): EnumValueB[A] =
      new EnumValueB[A] {

        def value(a: A): Boolean | String = a match {
          case b if b == trueValue => true
          case _                   => a.toString.toLowerCase
        }
      }

    def toLowerCaseStringF[A](falseValue: A): EnumValueB[A] =
      new EnumValueB[A] {

        def value(a: A): Boolean | String = a match {
          case b if b == falseValue => false
          case _                    => a.toString.toLowerCase
        }
      }

    def toLowerCaseStringTF[A](trueValue: A, falseValue: A): EnumValueB[A] =
      new EnumValueB[A] {

        def value(a: A): Boolean | String = a match {
          case b if b == trueValue  => true
          case b if b == falseValue => false
          case _                    => a.toString.toLowerCase
        }
      }

  }

  /**
    * Simple class to represent styles
    */
  final case class Style(styles: Map[String, String | Int])

  object Style {

    def toJsObject(style: Style): js.Object =
      style.styles.toJSDictionary.asInstanceOf[js.Object]

    def fromJsObject(o: js.Object): Style = {
      val xDict = o.asInstanceOf[js.Dictionary[String | Int]]
      val map   = (for ((prop, value) <- xDict) yield prop -> value).toMap
      Style(map)
    }
  }

}
