// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.semanticui

opaque type MyUndefOr[+A] = A | Unit

object MyUndefOr:
  val undefined: MyUndefOr[Nothing] = ()

  extension [A](myUndefOr: MyUndefOr[A])
    def foreach(f: A => Unit): Unit =
      myUndefOr match
        case Unit => ()
        case a: A @unchecked => f(a)

  implicit def fromAny[A](a: A): MyUndefOr[A] = a
