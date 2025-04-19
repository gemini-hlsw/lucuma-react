// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.common.syntax

import japgolly.scalajs.react.Callback
import lucuma.react.common.*

import scala.annotation.targetName
import scala.scalajs.js

trait EnumValueSyntax {
  // These can't be extension methods because their signatures clash.
  // extension [A](a: A)(using ev: EnumValue[A]) inline def toJs: String = ev.value(a)

  given [A: EnumValue]: Conversion[A, EnumValueOps[A]] =
    new EnumValueOps(_)

  given [A: EnumValue]: Conversion[js.UndefOr[A], EnumValueUndefOps[A]] =
    new EnumValueUndefOps(_)

  given [A: EnumValueB]: Conversion[A, EnumValueOpsB[A]] =
    new EnumValueOpsB(_)

  given [A: EnumValueB]: Conversion[js.UndefOr[A], EnumValueUndefOpsB[A]] =
    new EnumValueUndefOpsB(_)
}

final class EnumValueOps[A] protected[syntax] (a: A)(using ev: EnumValue[A]):
  inline def toJs: String = ev.value(a)

final class EnumValueUndefOps[A] protected[syntax] (a: js.UndefOr[A])(using ev: EnumValue[A]):
  def toJs: js.UndefOr[String] = a.map(ev.value)

final class EnumValueOpsB[A] protected[syntax] (a: A)(using ev: EnumValueB[A]):
  def toJs: Boolean | String = ev.value(a)

final class EnumValueUndefOpsB[A] protected[syntax] (a: js.UndefOr[A])(using ev: EnumValueB[A]):
  def toJs: js.UndefOr[Boolean | String] = a.map(ev.value)

// Some useful conversions
trait CallbackSyntax {

  extension (c: js.UndefOr[Callback])
    def toJs: js.UndefOr[js.Function0[Unit]] = c.map(x => () => x.runNow())

  extension [A](c: js.UndefOr[A => Callback])
    @targetName("toJs1")
    def toJs: js.UndefOr[js.Function1[A, Unit]] = c.map(x => (a: A) => x(a).runNow())

  extension [A, B](c: js.UndefOr[(A, B) => Callback])
    @targetName("toJs2")
    def toJs: js.UndefOr[js.Function2[A, B, Unit]] = c.map(x => (a: A, b: B) => x(a, b).runNow())
}

trait AllSyntax extends EnumValueSyntax with CallbackSyntax

object all       extends AllSyntax
object enumValue extends EnumValueSyntax
object callback  extends CallbackSyntax
