// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.fa

import japgolly.scalajs.react.vdom.VdomNode
import react.common.EnumValue
import react.semanticui.ShorthandS
import react.semanticui.ShorthandSB
import react.semanticui.elements.icon.Icon

import scala.scalajs.js.UndefOr

sealed trait Family extends Product with Serializable {
  val prefix: String
}
object Family {
  implicit val enumValue: EnumValue[Family] = EnumValue.toLowerCaseString
  case object Solid   extends Family {
    val prefix = "fas"
  }
  case object Regular extends Family {
    val prefix = "far"
  }
  case object Light   extends Family {
    val prefix = "fal"
  }
  case object Thin    extends Family {
    val prefix = "fat"
  }
  case object Duotone extends Family {
    val prefix = "fad"
  }

  def fromString(f: String): Family = f match {
    case "far" => Regular
    case "fal" => Light
    case "fad" => Duotone
    case "fat" => Thin
    case _     => Solid
  }
}

sealed trait Flip extends Product with Serializable
object Flip {
  implicit val enumValue: EnumValue[Flip] = EnumValue.toLowerCaseString
  case object Horizontal extends Flip
  case object Vertical   extends Flip
  case object Both       extends Flip
}

sealed trait IconSize extends Product with Serializable
object IconSize {
  implicit val enumValue: EnumValue[IconSize] = EnumValue.instance {
    case XS2 => "2xs"
    case XS  => "xs"
    case SM  => "sm"
    case LG  => "lg"
    case XL  => "xl"
    case XL2 => "2xl"
    case X1  => "1x"
    case X2  => "2x"
    case X3  => "3x"
    case X4  => "4x"
    case X5  => "5x"
    case X6  => "6x"
    case X7  => "7x"
    case X8  => "8x"
    case X9  => "9x"
    case X10 => "10x";
  }
  case object XS2 extends IconSize
  case object XS  extends IconSize
  case object SM  extends IconSize
  case object LG  extends IconSize
  case object XL  extends IconSize
  case object XL2 extends IconSize
  case object X1  extends IconSize
  case object X2  extends IconSize
  case object X3  extends IconSize
  case object X4  extends IconSize
  case object X5  extends IconSize
  case object X6  extends IconSize
  case object X7  extends IconSize
  case object X8  extends IconSize
  case object X9  extends IconSize
  case object X10 extends IconSize;
}

sealed trait Pull extends Product with Serializable
object Pull {
  implicit val enumValue: EnumValue[Pull] = EnumValue.toLowerCaseString
  case object Left  extends Pull
  case object Right extends Pull
}

sealed trait Rotation extends Product with Serializable
object Rotation {
  case object Rotate90  extends Rotation
  case object Rotate180 extends Rotation
  case object Rotate270 extends Rotation
}

// Conversion to simplify usage with semantic ui
// use typealiases as
// https://github.com/lampepfl/dotty/issues/15316#issuecomment-1140847190
private type SBIcon = UndefOr[ShorthandSB[Icon]]
private type SIcon  = UndefOr[ShorthandS[Icon]]

given Conversion[FontAwesomeIcon, SBIcon] = _.render
given Conversion[FontAwesomeIcon, SIcon]  = _.render
