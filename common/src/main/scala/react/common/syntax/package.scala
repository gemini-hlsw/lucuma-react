// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.common.syntax

import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.VdomNode
import react.common.*

import scala.language.implicitConversions
import scala.scalajs.js
import scala.scalajs.js.|

trait EnumValueSyntax {
  extension [A](a: A)(using ev: EnumValue[A]) inline def toJs: String = ev.value(a)

  implicit def syntaxEnumValueUndef[A: EnumValue](a: js.UndefOr[A]): EnumValueUndefOps[A] =
    new EnumValueUndefOps(a)

  implicit def syntaxEnumValueB[A: EnumValueB](a: A): EnumValueOpsB[A] =
    new EnumValueOpsB(a)

  implicit def syntaxEnumValueUndefB[A: EnumValueB](a: js.UndefOr[A]): EnumValueUndefOpsB[A] =
    new EnumValueUndefOpsB(a)
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

trait CallbackPairSyntax extends CallbackSyntax {
  implicit def syntaxCallbackPair1[A](
    a: (js.UndefOr[A => Callback], js.UndefOr[Callback])
  ): CallbackPairOps1[A] =
    new CallbackPairOps1(a._1, a._2)

  implicit def syntaxCallbackPair2[A, B](
    a: (js.UndefOr[(A, B) => Callback], js.UndefOr[Callback])
  ): CallbackPairOps2[A, B] =
    new CallbackPairOps2(a._1, a._2)
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

trait CallbackSyntax {
  implicit def callbackOps(c: js.UndefOr[Callback]): CallbackOps =
    new CallbackOps(c)

  implicit def callbackOps1[A](c: js.UndefOr[A => Callback]): CallbackOps1[A] =
    new CallbackOps1(c)

  implicit def callbackOps2[A, B](c: js.UndefOr[(A, B) => Callback]): CallbackOps2[A, B] =
    new CallbackOps2(c)

  final class CallbackPairOps1[A](a: js.UndefOr[A => Callback], b: js.UndefOr[Callback]) {
    def toJs: js.UndefOr[js.Function1[A, Unit]] = a.toJs.orElse(b.toJs1)
  }

  final class CallbackPairOps2[A, B](a: js.UndefOr[(A, B) => Callback], b: js.UndefOr[Callback]) {
    def toJs: js.UndefOr[js.Function2[A, B, Unit]] = a.toJs.orElse(b.toJs2)
  }
}

trait RenderSyntax {
  // Render(Fn) conversions
  implicit def GenericFnComponentP2RenderFn[P <: js.Object](
    p: GenericFnComponentP[P]
  ): RenderFn[P] =
    p.render

  implicit def GenericFnComponentPC2RenderFn[P <: js.Object](
    p: GenericFnComponentPC[P, ?]
  ): RenderFn[P] =
    p.render

  implicit def GenericFnComponentPA2RenderFn[P <: js.Object](
    p: GenericFnComponentPA[P, ?]
  ): RenderFn[P] =
    p.render

  implicit def GenericFnComponentPAC2RenderFn[P <: js.Object](
    p: GenericFnComponentPAC[P, ?]
  ): RenderFn[P] =
    p.render

  // Render conversions
  implicit def GenericComponentP2Render[P <: js.Object](
    p: GenericComponentP[P]
  ): Render[P] =
    p.render

  implicit def GenericComponentPC2Render[P <: js.Object](
    p: GenericComponentPC[P, ?]
  ): Render[P] =
    p.render

  implicit def GenericComponentPA2Render[P <: js.Object](
    p: GenericComponentPA[P, ?]
  ): Render[P] =
    p.render

  implicit def GenericComponentPAC2Render[P <: js.Object](
    p: GenericComponentPAC[P, ?]
  ): Render[P] =
    p.render
}

trait VdomSyntax {
  // FnComponent to VdomNode conversions
  implicit def GenericFnComponentP2VdomNode[P <: js.Object](
    p: GenericFnComponentP[P]
  ): VdomNode =
    p.render

  implicit def GenericFnComponentPC2VdomNode[P <: js.Object](
    p: GenericFnComponentPC[P, ?]
  ): VdomNode =
    p.render

  implicit def GenericFnComponentPA2VdomNode[P <: js.Object](
    p: GenericFnComponentPA[P, ?]
  ): VdomNode =
    p.render

  implicit def GenericFnComponentPAC2VdomNode[P <: js.Object](
    p: GenericFnComponentPAC[P, ?]
  ): VdomNode =
    p.render

  // Component 2 VdomNode conversions
  implicit def GenericComponentP2VdomNode[P <: js.Object](
    p: GenericComponentP[P]
  ): VdomNode =
    p.render

  implicit def GenericComponentPC2VdomNode[P <: js.Object](
    p: GenericComponentPC[P, ?]
  ): VdomNode =
    p.render

  implicit def GenericComponentPA2VdomNode[P <: js.Object](
    p: GenericComponentPA[P, ?]
  ): VdomNode =
    p.render

  implicit def GenericComponentPAC2VdomNode[P <: js.Object](
    p: GenericComponentPAC[P, ?]
  ): VdomNode =
    p.render

  // Facade component 2 VdomNode conversions
  implicit def GenericComponentPF2VdomNode[P <: js.Object, F <: js.Object](
    p: GenericComponentPF[P, F]
  ): VdomNode =
    p.render

  implicit def GenericComponentPCF2VdomNode[P <: js.Object, F <: js.Object](
    p: GenericComponentPCF[P, ?, F]
  ): VdomNode =
    p.render

  implicit def GenericComponentPAF2VdomNode[P <: js.Object, F <: js.Object](
    p: GenericComponentPAF[P, ?, F]
  ): VdomNode =
    p.render

  implicit def GenericComponentPACF2VdomNode[P <: js.Object, F <: js.Object](
    p: GenericComponentPACF[P, ?, F]
  ): VdomNode =
    p.render

  // FnComponent to js.UndefOr[VdomNode] conversions
  implicit def GenericFnComponentP2UndefVdomNode[P <: js.Object](
    p: GenericFnComponentP[P]
  ): js.UndefOr[VdomNode] =
    p.render: VdomNode

  implicit def GenericFnComponentPC2UndefVdomNode[P <: js.Object](
    p: GenericFnComponentPC[P, ?]
  ): js.UndefOr[VdomNode] =
    p.render: VdomNode

  implicit def GenericFnComponentPA2UndefVdomNode[P <: js.Object](
    p: GenericFnComponentPA[P, ?]
  ): js.UndefOr[VdomNode] =
    p.render: VdomNode

  implicit def GenericFnComponentPAC2UndefVdomNode[P <: js.Object](
    p: GenericFnComponentPAC[P, ?]
  ): js.UndefOr[VdomNode] =
    p.render: VdomNode

  // Component to js.UndefOr[VdomNode] conversions
  implicit def GenericComponentP2UndefVdomNode[P <: js.Object](
    p: GenericComponentP[P]
  ): js.UndefOr[VdomNode] =
    p.render: VdomNode

  implicit def GenericComponentPC2UndefVdomNode[P <: js.Object](
    p: GenericComponentPC[P, ?]
  ): js.UndefOr[VdomNode] =
    p.render: VdomNode

  implicit def GenericComponentPA2UndefVdomNode[P <: js.Object](
    p: GenericComponentPA[P, ?]
  ): js.UndefOr[VdomNode] =
    p.render: VdomNode

  implicit def GenericComponentPAC2UndefVdomNode[P <: js.Object](
    p: GenericComponentPAC[P, ?]
  ): js.UndefOr[VdomNode] =
    p.render: VdomNode

  // Facade Component to js.UndefOr[VdomNode] conversions
  implicit def GenericComponentPF2UndefVdomNode[P <: js.Object, F <: js.Object](
    p: GenericComponentPF[P, F]
  ): js.UndefOr[VdomNode] =
    p.render: VdomNode

  implicit def GenericComponentPCF2UndefVdomNode[P <: js.Object, F <: js.Object](
    p: GenericComponentPCF[P, ?, F]
  ): js.UndefOr[VdomNode] =
    p.render: VdomNode

  implicit def GenericComponentPAF2UndefVdomNode[P <: js.Object, F <: js.Object](
    p: GenericComponentPAF[P, ?, F]
  ): js.UndefOr[VdomNode] =
    p.render: VdomNode

  implicit def GenericComponentPACF2UndefVdomNode[P <: js.Object, F <: js.Object](
    p: GenericComponentPACF[P, ?, F]
  ): js.UndefOr[VdomNode] =
    p.render: VdomNode

  // End VdomNode conversions
}

object all       extends AllSyntax
object vdom      extends VdomSyntax
object render    extends RenderSyntax
object enumValue extends EnumValueSyntax
object callback  extends CallbackSyntax with CallbackPairSyntax

trait AllSyntax extends EnumValueSyntax with CallbackPairSyntax with RenderSyntax with VdomSyntax
