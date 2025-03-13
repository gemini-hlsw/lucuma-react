// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import cats.syntax.option.*

import scalajs.js

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

// enum FilterFn[T, TM, F, FM](val toJs: BuiltInFilterFn | rawFilter.FilterFn[T]):
//   case IncludesString
//       extends FilterFn[Any, Any, String, Nothing](rawCore.tanstackTableCoreStrings.includesString)
//   case IncludesStringSensitive
//       extends FilterFn[Any, Any, String, Nothing](
//         rawCore.tanstackTableCoreStrings.includesStringSensitive
//       )
//   case EqualsString
//       extends FilterFn[Any, Any, String, Nothing](rawCore.tanstackTableCoreStrings.equalsString)
//   // case EqualsStringSensitive
//   //     extends FilterFn[Any, Any, String, Nothing](
//   //       rawCore.tanstackTableCoreStrings.equalsStringSensitive
//   //     )
//   case ArrIncludes
//       extends FilterFn[Any, Any, js.Array[?], Nothing](rawCore.tanstackTableCoreStrings.arrIncludes)
//   case ArrIncludesAll
//       extends FilterFn[Any, Any, js.Array[?], Nothing](
//         rawCore.tanstackTableCoreStrings.arrIncludesAll
//       )
//   case ArrIncludesSome
//       extends FilterFn[Any, Any, js.Array[?], Nothing](
//         rawCore.tanstackTableCoreStrings.arrIncludesSome
//       )
//   case Equals extends FilterFn[Any, Any, Any, Nothing](rawCore.tanstackTableCoreStrings.equals)
//   case WeakEquals
//       extends FilterFn[Any, Any, Any, Nothing](rawCore.tanstackTableCoreStrings.weakEquals)
//   case InNumberRange
//       extends FilterFn[Any, Any, js.Tuple2[Double, Double], Nothing](
//         rawCore.tanstackTableCoreStrings.inNumberRange
//       )
//   case Custom[T, TM, F, FM](filterFn: (Row[T, TM], ColumnId, F, FM => Unit) => Boolean)
//       extends FilterFn(FilterFn.toJs(filterFn))

// object FilterFn:
//   private def toJs[T, TM, F, FM](
//     filterFn: (Row[T, TM], ColumnId, F, FM => Unit) => Boolean
//   ): rawFilter.FilterFn[T] = {
//     val fn: js.Function4[raw.Row[T], String, Any, js.Function1[FilterMeta, Unit], Boolean] =
//       (
//         row:         raw.Row[T],
//         colId:       String,
//         filterValue: Any,
//         addMeta:     js.Function1[FilterMeta, Unit]
//       ) =>
//         filterFn(
//           Row(row),
//           ColumnId(colId),
//           filterValue.asInstanceOf[F],
//           (m: FM) => addMeta(m.asInstanceOf[FilterMeta])
//         )

//     val p: rawFilter.FilterFn[T] = fn.asInstanceOf[rawFilter.FilterFn[T]]
//     // resolveFilterValue?: TransformFilterValueFn<TData>
//     // autoRemove?: ColumnFilterAutoRemoveTestFn<TData>

//     p
//   }

//   def fromString(s: String): Option[FilterFn[?, ?, ?, Nothing]] =
//     if (s == rawCore.tanstackTableCoreStrings.includesString.toString)
//       FilterFn.IncludesString.some
//     else if (s == rawCore.tanstackTableCoreStrings.includesStringSensitive.toString)
//       FilterFn.IncludesStringSensitive.some
//     else if (s == rawCore.tanstackTableCoreStrings.equalsString.toString)
//       FilterFn.EqualsString.some
//     else if (s == rawCore.tanstackTableCoreStrings.arrIncludes.toString)
//       FilterFn.ArrIncludes.some
//     else if (s == rawCore.tanstackTableCoreStrings.arrIncludesAll.toString)
//       FilterFn.ArrIncludesAll.some
//     else if (s == rawCore.tanstackTableCoreStrings.arrIncludesSome.toString)
//       FilterFn.ArrIncludesSome.some
//     else if (s == rawCore.tanstackTableCoreStrings.equals.toString)
//       FilterFn.Equals.some
//     else if (s == rawCore.tanstackTableCoreStrings.weakEquals.toString)
//       FilterFn.WeakEquals.some
//     else if (s == rawCore.tanstackTableCoreStrings.inNumberRange.toString)
//       FilterFn.InNumberRange.some
//     else
//       none

//   def fromJs[T, TM, F, FM](v: BuiltInFilterFn | rawFilter.FilterFn[T]): FilterFn[T, TM, F, FM] =
//     js.typeOf(v) match
//       case "string" => BuiltInSorting.fromJs(v.asInstanceOf[String])
//       case fn       =>
//         (rowA: Row[T, TM], rowB: Row[T, TM], colId: ColumnId) =>
//           fn.asInstanceOf[raw.buildLibFeaturesRowSortingMod.SortingFn[T]](
//             rowA.toJs,
//             rowB.toJs,
//             colId.value
//           ).toInt

//   given [T, TM, F, FM]
//     : Conversion[(Row[T, TM], ColumnId, F, FM => Unit) => Boolean, FilterFn.Custom[T, TM, F, FM]] =
//     FilterFn.Custom[T, TM, F, FM](_)

// // trait FilterFn[T, TM, F, FM] extends ((Row[T, TM], ColumnId, F, FM => Unit) => Boolean) { self =>
// // // resolveFilterValue?: TransformFilterValueFn<TData>
// // // autoRemove?: ColumnFilterAutoRemoveTestFn<TData>

// //   def toJs: rawFilter.FilterFn[T] =
// //     val fn: js.Function4[raw.Row[T], String, Any, js.Function1[FilterMeta, Unit], Boolean] =
// //       (
// //         row:         raw.Row[T],
// //         colId:       String,
// //         filterValue: Any,
// //         addMeta:     js.Function1[FilterMeta, Unit]
// //       ) =>
// //         self.apply(
// //           Row(row),
// //           ColumnId(colId),
// //           filterValue.asInstanceOf[F],
// //           (m: FM) => addMeta(m.asInstanceOf[FilterMeta])
// //         )

// //     val p: rawFilter.FilterFn[T] = fn.asInstanceOf[rawFilter.FilterFn[T]]
// //     // resolveFilterValue?: TransformFilterValueFn<TData>
// //     // autoRemove?: ColumnFilterAutoRemoveTestFn<TData>

// //     p
// // }

// // object FilterFn:
// //   given [T, TM, F, FM]
// //     : Conversion[(Row[T, TM], ColumnId, F, FM => Unit) => Boolean, FilterFn[T, TM, F, FM]] =
// //     fn =>
// //       new FilterFn[T, TM, F, FM] {
// //         override def apply(
// //           row:         Row[T, TM],
// //           colId:       ColumnId,
// //           filterValue: F,
// //           addMeta:     FM => Unit
// //         ): Boolean =
// //           fn(row, colId, filterValue, addMeta)
// //       }

// //   def fromJs[T](fn: rawFilter.FilterFn[T]): FilterFn[T, Any, Any, Any] =
// //     val p: (Row[T], ColumnId, Any, Any => Unit) => Boolean =
// //       (row: Row[T], colId: ColumnId, filterValue: Any, addMeta: Any => Unit) =>
