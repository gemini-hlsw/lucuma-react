// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import lucuma.react.SizePx
import reactST.{tanstackTableCore => raw}

import scalajs.js
import scalajs.js.JSConverters.*

case class ColumnSizingInfo(
  columnSizingStart: List[(ColumnId, SizePx)],
  deltaOffset:       Option[SizePx],
  deltaPercentage:   Option[Double],
  isResizingColumn:  Option[ColumnId],
  startOffset:       Option[SizePx],
  startSize:         Option[SizePx]
):
  def toJs: raw.mod.ColumnSizingInfoState = raw.mod
    .ColumnSizingInfoState(
      columnSizingStart = columnSizingStart
        .map((colId, size) => js.Tuple2(colId.value, size.value.toDouble))
        .toJSArray,
      isResizingColumn =
        isResizingColumn.map(_.value).getOrElse(raw.tanstackTableCoreBooleans.`false`)
    )
    .applyOrNull(deltaOffset, (c, p) => c.setDeltaOffset(p.value), _.setDeltaOffsetNull)
    .applyOrNull(deltaPercentage, (c, p) => c.setDeltaPercentage(p), _.setDeltaPercentageNull)
    .applyOrNull(startOffset, (c, p) => c.setStartOffset(p.value), _.setStartOffsetNull)
    .applyOrNull(startSize, (c, p) => c.setStartSize(p.value), _.setStartSizeNull)

object ColumnSizingInfo:
  private[table] def fromJs(rawValue: raw.mod.ColumnSizingInfoState): ColumnSizingInfo =
    ColumnSizingInfo(
      rawValue.columnSizingStart.toList.map(tuple => ColumnId(tuple._1) -> SizePx(tuple._2.toInt)),
      rawValue.deltaOffset.nullToOption.map(v => SizePx(v.toInt)),
      rawValue.deltaPercentage.nullToOption,
      rawValue.isResizingColumn match
        case v if v == raw.tanstackTableCoreBooleans.`false` => None
        case col                                             => Some(ColumnId(col.asInstanceOf[String]))
      ,
      rawValue.startOffset.nullToOption.map(v => SizePx(v.toInt)),
      rawValue.startSize.nullToOption.map(v => SizePx(v.toInt))
    )
