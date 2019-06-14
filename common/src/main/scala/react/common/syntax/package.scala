package react.common

import scala.scalajs.js
import scala.scalajs.js.|
import scala.scalajs.js.JSConverters._
import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.raw.JsNumber
import japgolly.scalajs.react.raw.React
import japgolly.scalajs.react.vdom.VdomNode
import react.common.style._

package syntax {

  trait EnumValueSyntax {
    implicit def syntaxEnumValue[A: EnumValue](a: A): EnumValueOps[A] =
      new EnumValueOps(a)

    implicit def syntaxEnumValue[A: EnumValue](a: js.UndefOr[A]): EnumValueUndefOps[A] =
      new EnumValueUndefOps(a)

    implicit def syntaxEnumValueB[A: EnumValueB](a: A): EnumValueOpsB[A] =
      new EnumValueOpsB(a)

    implicit def syntaxEnumValueB[A: EnumValueB](a: js.UndefOr[A]): EnumValueUndefOpsB[A] =
      new EnumValueUndefOpsB(a)

  }

  final class EnumValueOps[A](a: A)(implicit ev: EnumValue[A]) {
    def toJs: String = ev.value(a)
  }

  final class EnumValueUndefOps[A](a: js.UndefOr[A])(implicit ev: EnumValue[A]) {

    def toJs: js.UndefOr[String] =
      a.map { ev.value }
  }

  final class EnumValueOpsB[A](a: A)(implicit ev: EnumValueB[A]) {
    def toJs: Boolean | String = ev.value(a)
  }

  final class EnumValueUndefOpsB[A](a: js.UndefOr[A])(implicit ev: EnumValueB[A]) {

    def toJs: js.UndefOr[Boolean | String] =
      a.map { ev.value }
  }

  trait CallbackPairSyntax {
    implicit def syntaxCallbackPair1[A](a: (js.UndefOr[A => Callback], js.UndefOr[Callback])): CallbackPairOps1[A] =
      new CallbackPairOps1(a._1, a._2)

    implicit def syntaxCallbackPair2[A, B](
        a: (js.UndefOr[(A, B) => Callback], js.UndefOr[Callback])): CallbackPairOps2[A, B] =
      new CallbackPairOps2(a._1, a._2)
  }

  final class CallbackPairOps1[A](a: js.UndefOr[A => Callback], b: js.UndefOr[Callback]) {
    def toJs: js.UndefOr[js.Function1[A, Unit]] = a.toJs.orElse(b.toJs1)
  }

  final class CallbackPairOps2[A, B](a: js.UndefOr[(A, B) => Callback], b: js.UndefOr[Callback]) {
    def toJs: js.UndefOr[js.Function2[A, B, Unit]] = a.toJs.orElse(b.toJs2)
  }

  trait StyleSyntax {
    implicit def styePairU(a: (js.UndefOr[String], js.UndefOr[Css])): ClassnameCssOps =
      new ClassnameCssOps(a)

    implicit def styePair(a: (String, Css)): ClassnameCssOps =
      new ClassnameCssOps((a._1: js.UndefOr[String], a._2: js.UndefOr[Css]))
  }

  final class ClassnameCssOps(cn: (js.UndefOr[String], js.UndefOr[Css])) {

    def toJs: js.UndefOr[String] = (cn._1.toOption, cn._2.toOption) match {
      case (cn @ Some(_), None) => cn.orUndefined
      case (None, cz @ Some(_)) => cz.map(x => x: String).orUndefined
      case (Some(cs), Some(cz)) => s"$cs ${cz: String}"
      case _                    => ""
    }

  }

}

package object syntax extends EnumValueSyntax with CallbackPairSyntax with StyleSyntax {
  implicit class StyleOps(val s: Style) extends AnyVal {
    def toJsObject: js.Object = Style.toJsObject(s)
  }

  // Some useful conversions
  implicit class VdomToRaw(val node: VdomNode) extends AnyVal {
    def toRaw: React.Node = node.rawNode
  }

  implicit class VdomNodeOps(val c: js.UndefOr[VdomNode]) extends AnyVal {
    def toJs: js.UndefOr[React.Node] = c.map(_.rawNode)
  }

  implicit class CallbackOps(val c: js.UndefOr[Callback]) extends AnyVal {
    def toJs: js.UndefOr[js.Function0[Unit]]              = c.map(x => () => x.runNow())
    def toJs1[A]: js.UndefOr[js.Function1[A, Unit]]       = c.map(x => (_: A) => x.runNow())
    def toJs2[A, B]: js.UndefOr[js.Function2[A, B, Unit]] = c.map(x => (_: A, _: B) => x.runNow())
  }

  implicit class CallbackOps1[A](val c: js.UndefOr[A => Callback]) extends AnyVal {
    def toJs: js.UndefOr[js.Function1[A, Unit]] = c.map(x => (a: A) => x(a).runNow())
  }

  implicit class CallbackOps2[A, B](val c: js.UndefOr[(A, B) => Callback]) extends AnyVal {
    def toJs: js.UndefOr[js.Function2[A, B, Unit]] = c.map(x => (a: A, b: B) => x(a, b).runNow())
  }

  implicit class JsNumberOps(val d: JsNumber) extends AnyVal {

    // Some uglies for js union types
    def toDouble: Double = (d: Any) match {
      case d: Float  => d.toDouble
      case d: Double => d
      case d: Byte   => d.toDouble
      case d: Short  => d.toDouble
      case d: Int    => d.toDouble
    }

    // Some uglies for js union types
    def toInt: Int = (d: Any) match {
      case d: Float  => d.toInt
      case d: Double => d.toInt
      case d: Byte   => d.toInt
      case d: Short  => d.toInt
      case d: Int    => d
    }
  }
}
