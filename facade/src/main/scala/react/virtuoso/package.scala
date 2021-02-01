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
}
