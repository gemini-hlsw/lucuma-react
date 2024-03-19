// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.common.style

import cats.*
import cats.syntax.all.*
import japgolly.scalajs.react.Reusability
import japgolly.scalajs.react.vdom.html_<^.*

import scala.annotation.targetName
import scala.scalajs.js

import js.JSConverters.*

sealed trait StyleExtractor[A] {
  def extract(s: Style, key: String): Option[A]
}

given StyleExtractor[Int] = new StyleExtractor[Int] {
  override def extract(s: Style, key: String): Option[Int] =
    s.get(key).flatMap { x =>
      (x: Any) match {
        case x: Int => Some(x)
        case _      => None
      }
    }
}

given StyleExtractor[String] = new StyleExtractor[String] {
  override def extract(s: Style, key: String): Option[String] =
    s.get(key).flatMap { x =>
      (x: Any) match {
        case x: String => Some(x)
        case _         => None
      }
    }
}

/**
 * Simple class to represent styles
 */
opaque type Style = Map[String, String | Int]

object Style {
  extension (styles: Style)
    inline def value: Map[String, String | Int] = styles

    inline def isEmpty: Boolean = styles.isEmpty

    inline def nonEmpty: Boolean = styles.nonEmpty

    @targetName("extToJsObject")
    inline def toJsObject: js.Object =
      styles.toJSDictionary.asInstanceOf[js.Object]

    inline def extract[A](key: String)(using S: StyleExtractor[A]): Option[A] =
      S.extract(styles, key)

    inline def remove(key: String): Style =
      if (styles.contains(key)) Style(styles - key) else styles

    inline def when_(pred: => Boolean): Style =
      if (pred) styles else Style.Empty

    inline def unless_(pred: => Boolean): Style =
      if (pred) Style.Empty else styles

  inline def apply(styles: Map[String, String | Int]): Style = styles

  def fromJsObject(o: js.Object): Style = {
    val xDict = o.asInstanceOf[js.Dictionary[String | Int]]
    val map   = (for ((prop, value) <- xDict) yield prop -> value).toMap
    Style(map)
  }

  val Empty: Style = Map.empty

  given Eq[String | Int] = Eq.instance { (a, b) =>
    (a: Any, b: Any) match {
      case (a: String, b: String) => a === b
      case (a: Int, b: Int)       => a === b
      case _                      => false
    }
  }

  given Eq[Style]     = cats.kernel.instances.map.catsKernelStdEqForMap
  given Monoid[Style] = new Monoid[Style] {
    override val empty: Style                       = Style(Map.empty)
    override def combine(a: Style, b: Style): Style =
      Style(a ++ b)
  }
}

/**
 * type to represent css class
 */
opaque type Css = List[String]

object Css:
  extension (cn: (js.UndefOr[String], js.UndefOr[Css]))
    def cssToJs: js.UndefOr[String] =
      (cn._1.toOption, cn._2.toOption) match {
        case (cn @ Some(_), None) => cn.orUndefined
        case (None, cz @ Some(_)) => cz.map(_.htmlClass).orUndefined
        case (Some(cs), Some(cz)) => s"$cs ${cz.htmlClass}"
        case _                    => js.undefined
      }

  extension (css: Css)
    inline def isEmpty: Boolean = css.isEmpty

    inline def nonEmpty: Boolean = css.nonEmpty

    inline def htmlClass: String = css.mkString(" ")

    inline def value: List[String] = css

    inline def when_(pred: => Boolean): Css =
      if (pred) css else Nil

    inline def unless_(pred: => Boolean): Css =
      when_(!pred)

    def querySelector: String = css.mkString(".", ".", "")

  inline def apply(css: List[String]): Css = css

  inline def apply(css: String): Css = List(css)

  val Empty: Css = Nil

  private def conversion(s: Css): TagMod = ^.className := s.htmlClass

  given Conversion[Css, TagMod] = conversion

  given Conversion[List[Css], TagMod] = (s: List[Css]) => s.map(conversion).toTagMod

  given Order[Css]       = Order.by(_.htmlClass)
  given Reusability[Css] = Reusability.by(_.htmlClass)
  given Monoid[Css]      = Monoid.instance(Nil, (a, b) => a ::: b)
end Css
