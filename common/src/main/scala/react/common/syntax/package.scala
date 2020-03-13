package react.common

import scala.scalajs.js
import scala.scalajs.js.|
import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.raw.JsNumber
import japgolly.scalajs.react.raw.React
import japgolly.scalajs.react.vdom.VdomNode

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
      a.map(ev.value)
  }

  final class EnumValueOpsB[A](a: A)(implicit ev: EnumValueB[A]) {
    def toJs: Boolean | String = ev.value(a)
  }

  final class EnumValueUndefOpsB[A](a: js.UndefOr[A])(implicit ev: EnumValueB[A]) {
    def toJs: js.UndefOr[Boolean | String] =
      a.map(ev.value)
  }

  trait CallbackPairSyntax {
    implicit def syntaxCallbackPair1[A](
      a: (js.UndefOr[A => Callback], js.UndefOr[Callback])
    ): CallbackPairOps1[A] =
      new CallbackPairOps1(a._1, a._2)

    implicit def syntaxCallbackPair2[A, B](
      a: (js.UndefOr[(A, B) => Callback], js.UndefOr[Callback])
    ): CallbackPairOps2[A, B] =
      new CallbackPairOps2(a._1, a._2)
  }

  final class CallbackPairOps1[A](a: js.UndefOr[A => Callback], b: js.UndefOr[Callback]) {
    def toJs: js.UndefOr[js.Function1[A, Unit]] = a.toJs.orElse(b.toJs1)
  }

  final class CallbackPairOps2[A, B](a: js.UndefOr[(A, B) => Callback], b: js.UndefOr[Callback]) {
    def toJs: js.UndefOr[js.Function2[A, B, Unit]] = a.toJs.orElse(b.toJs2)
  }

  final class VdomOps(val node: VdomNode) extends AnyVal {
    def toRaw: React.Node = node.rawNode
  }

  final class VdomUndefOps(val c: js.UndefOr[VdomNode]) extends AnyVal {
    def toJs: js.UndefOr[React.Node] = c.map(_.rawNode)
  }

  // Some useful conversions
  final class CallbackOps(val c: js.UndefOr[Callback]) extends AnyVal {
    def toJs: js.UndefOr[js.Function0[Unit]]              = c.map(x => () => x.runNow())
    def toJs1[A]: js.UndefOr[js.Function1[A, Unit]]       = c.map(x => (_: A) => x.runNow())
    def toJs2[A, B]: js.UndefOr[js.Function2[A, B, Unit]] = c.map(x => (_: A, _: B) => x.runNow())
  }

  final class CallbackOps1[A](val c: js.UndefOr[A => Callback]) extends AnyVal {
    def toJs: js.UndefOr[js.Function1[A, Unit]] = c.map(x => (a: A) => x(a).runNow())
  }

  final class CallbackOps2[A, B](val c: js.UndefOr[(A, B) => Callback]) extends AnyVal {
    def toJs: js.UndefOr[js.Function2[A, B, Unit]] = c.map(x => (a: A, b: B) => x(a, b).runNow())
  }

  final class JsNumberOps(val d: JsNumber) extends AnyVal {
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

  trait AllSyntax extends EnumValueSyntax with CallbackPairSyntax with style.StyleSyntax {
    implicit def vdomOps(node: VdomNode): VdomOps =
      new VdomOps(node)

    implicit def vdomUndefOps(node: js.UndefOr[VdomNode]): VdomUndefOps =
      new VdomUndefOps(node)

    implicit def callbackOps(c: js.UndefOr[Callback]): CallbackOps =
      new CallbackOps(c)

    implicit def callbackOps1[A](c: js.UndefOr[A => Callback]): CallbackOps1[A] =
      new CallbackOps1(c)

    implicit def callbackOps2[A, B](c: js.UndefOr[(A, B) => Callback]): CallbackOps2[A, B] =
      new CallbackOps2(c)

    implicit def jsNumberOps(d: JsNumber): JsNumberOps =
      new JsNumberOps(d)

    // Start Render(Fn) conversions
    implicit def gFnProps2VdomP[P <: js.Object](
      p: GenericFnComponentP[P]
    ): Render[P] =
      p.render

    implicit def gProps2FnUnmountedPA[P <: js.Object](
      p: GenericFnComponentPC[P, _]
    ): RenderFn[P] =
      p.render

    implicit def gFnProps2VdomP[P <: js.Object](
      p: GenericFnComponentPA[P, _]
    ): RenderFn[P] =
      p.render

    implicit def gProps2FnUnmountedPA[P <: js.Object](
      p: GenericFnComponentPAC[P, _]
    ): RenderFn[P] =
      p.render

    implicit def gProps2VdomP[P <: js.Object](
      p: GenericComponentP[P]
    ): Render[P] =
      p.render

    implicit def gProps2UnmountedPC[P <: js.Object](
      p: GenericComponentPC[P, _]
    ): Render[P] =
      p.render

    implicit def gProps2VdomPA[P <: js.Object](
      p: GenericComponentPA[P, _]
    ): Render[P] =
      p.render

    implicit def gProps2UnmountedPAC[P <: js.Object](
      p: GenericComponentPAC[P, _]
    ): Render[P] =
      p.render
    // End Render(Fn) conversions

    // Start VdomNode conversions
    implicit def gFnProps2VdomNodeP[P <: js.Object](
      p: GenericFnComponentP[P]
    ): VdomNode =
      p.render

    implicit def gFnProps2VdomNodePC[P <: js.Object](
      p: GenericFnComponentPC[P, _]
    ): VdomNode =
      p.render

    implicit def gProps2VdomNodeP[P <: js.Object](
      p: GenericComponentP[P]
    ): VdomNode =
      p.render

    implicit def gProps2VdomNodePC[P <: js.Object](
      p: GenericComponentPC[P, _]
    ): VdomNode =
      p.render

    implicit def gProps2VdomNodePA[P <: js.Object](
      p: GenericComponentPA[P, _]
    ): VdomNode =
      p.render

    implicit def gProps2VdomNodePAC[P <: js.Object](
      p: GenericComponentPAC[P, _]
    ): VdomNode =
      p.render
    // End VdomNode conversions
  }
}

package object syntax extends AllSyntax
