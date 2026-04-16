// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.pragmaticdnd

import japgolly.scalajs.react.feature.Context
import lucuma.react.pragmaticdnd.facade.DropTargetRecord

final case class DragAndDropScope[S, T](
  context:  Context.Provided[DragAndDropContext],
  dragging: Option[Data[S]],
  dragOver: List[DropTargetRecord[T]]
)
