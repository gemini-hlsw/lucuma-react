// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import cats.syntax.option.*

import scalajs.js

/**
 * @typaram
 *   F The type of the filter value.
 */
enum BuiltInFilter[F](private[table] val toJs: String):
  case IncludesString          extends BuiltInFilter[String]("includesString")
  case IncludesStringSensitive extends BuiltInFilter[String]("includesStringSensitive")
  case EqualsString            extends BuiltInFilter[String]("equalsString")
  case EqualsStringSensitive   extends BuiltInFilter[String]("equalsStringSensitive")
  case ArrIncludes             extends BuiltInFilter[js.Array[?]]("arrIncludes")
  case ArrIncludesAll          extends BuiltInFilter[js.Array[?]]("arrIncludesAll")
  case ArrIncludesSome         extends BuiltInFilter[js.Array[?]]("arrIncludesSome")
  case Equals                  extends BuiltInFilter[Any]("equals")
  case WeakEquals              extends BuiltInFilter[Any]("weakEquals")
  case InNumberRange
      extends BuiltInFilter[js.Tuple2[js.UndefOr[Double], js.UndefOr[Double]]]("inNumberRange")

object BuiltInFilter:
  private[table] def fromJs(rawValue: String): BuiltInFilter[?] =
    BuiltInFilter.values.find(_.toJs == rawValue).get
