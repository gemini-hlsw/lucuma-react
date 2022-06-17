package react.semanticui

import scala.scalajs.js
import js.JSConverters._
import react.common.EnumValue

object widths {
  sealed trait SemanticWidth extends Product with Serializable
  object SemanticWidth {
    implicit val enumValue: EnumValue[SemanticWidth] = EnumValue.toLowerCaseString
  }

  case object One      extends SemanticWidth
  case object Two      extends SemanticWidth
  case object Three    extends SemanticWidth
  case object Four     extends SemanticWidth
  case object Five     extends SemanticWidth
  case object Six      extends SemanticWidth
  case object Seven    extends SemanticWidth
  case object Eight    extends SemanticWidth
  case object Nine     extends SemanticWidth
  case object Ten      extends SemanticWidth
  case object Eleven   extends SemanticWidth
  case object Twelve   extends SemanticWidth
  case object Thirteen extends SemanticWidth
  case object Fourteen extends SemanticWidth
  case object Fifteen  extends SemanticWidth
  case object Sixteen  extends SemanticWidth

  def widthOf(i: Int): js.UndefOr[SemanticWidth] =
    allWidths.get(i).orUndefined

  val allWidths: Map[Int, SemanticWidth] =
    Map(
      1  -> One,
      2  -> Two,
      3  -> Three,
      4  -> Four,
      5  -> Five,
      6  -> Six,
      7  -> Seven,
      8  -> Eight,
      9  -> Nine,
      10 -> Ten,
      11 -> Eleven,
      12 -> Twelve,
      13 -> Thirteen,
      14 -> Fourteen,
      15 -> Fifteen,
      16 -> Sixteen
    )
}
