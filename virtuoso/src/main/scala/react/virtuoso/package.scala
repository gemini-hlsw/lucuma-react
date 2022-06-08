package react

import scalajs.js

package object virtuoso {

  object raw {
    trait OverScan extends js.Object {
      val main: Int
      val reverse: Int
    }

    object OverScan {
      def apply(main: Int, reverse: Int): OverScan =
        js.Dynamic.literal("main" -> main, "reverse" -> reverse).asInstanceOf[OverScan]
    }

    trait ListRange extends js.Object {
      val startIndex: Double
      val endIndex: Double
    }
  }

  // Eliminate some type ascriptions. Incidentally, it also catches errors when
  // forgetting to call .toJs for a function that returns a Callback, whereas direct ascription
  // to js.Function[A, Unit] hides the error.
  protected[virtuoso] implicit def FuncOps1[A, B](f: A => B): js.UndefOr[js.Function1[A, B]] =
    f: js.Function1[A, B]
  protected[virtuoso] implicit def FuncOps2[A, B, C](
    f: (A, B) => C
  ): js.UndefOr[js.Function2[A, B, C]] =
    f: js.Function2[A, B, C]
  protected[virtuoso] implicit def FuncOps3[A, B, C, D](
    f: (A, B, C) => D
  ): js.UndefOr[js.Function3[A, B, C, D]] =
    f: js.Function3[A, B, C, D]
}
