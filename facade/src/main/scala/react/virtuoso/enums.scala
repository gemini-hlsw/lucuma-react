package react.virtuoso

sealed abstract class ScrollBehavior(val raw: ScrollBehavior.Raw)
object ScrollBehavior {
  type Raw = String

  case object Auto    extends ScrollBehavior("auto")
  case object Smooth  extends ScrollBehavior("smooth")
  case object Instant extends ScrollBehavior("instant")
}

sealed abstract class Align(val raw: Align.Raw)
object Align {
  type Raw = String

  case object Start  extends Align("start")
  case object Center extends Align("center")
  case object End    extends Align("end")
}
