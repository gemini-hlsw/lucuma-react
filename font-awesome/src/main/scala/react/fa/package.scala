// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.fa

import react.common.EnumValue

enum Family(val prefix: String):
  case Solid   extends Family("fas")
  case Regular extends Family("far")
  case Light   extends Family("fal")
  case Thin    extends Family("fat")
  case Duotone extends Family("fad")

object Family:
  given EnumValue[Family]           = EnumValue.toLowerCaseString
  def fromString(f: String): Family = f match
    case "far" => Regular
    case "fal" => Light
    case "fad" => Duotone
    case "fat" => Thin
    case _     => Solid

enum Flip:
  case Horizontal, Vertical, Both

object Flip:
  given EnumValue[Flip] = EnumValue.toLowerCaseString

enum IconSize:
  case XS2, XS, SM, LG, XL, XL2, X1, X2, X3, X4, X5, X6, X7, X8, X9, X10

object IconSize:
  given EnumValue[IconSize] = EnumValue.instance {
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

enum Pull:
  case Left, Right

object Pull:
  given EnumValue[Pull] = EnumValue.toLowerCaseString

enum Rotation:
  case Rotate90, Rotate180, Rotate270

object Rotation:
  given EnumValue[Rotation] = EnumValue.toLowerCaseString
