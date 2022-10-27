// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

enum BuiltInSorting(private[table] val toJs: String):
  case Alphanumeric              extends BuiltInSorting("alphanumeric")
  case AlphanumericCaseSensitive extends BuiltInSorting("alphanumericCaseSensitive")
  case Text                      extends BuiltInSorting("text")
  case TextCaseSensitive         extends BuiltInSorting("textCaseSensitive")
  case Datetime                  extends BuiltInSorting("datetime")
  case Basic                     extends BuiltInSorting("basic")

object BuiltInSorting:
  private[table] def fromJs(rawValue: String): BuiltInSorting =
    BuiltInSorting.values.find(_.toJs == rawValue).get
