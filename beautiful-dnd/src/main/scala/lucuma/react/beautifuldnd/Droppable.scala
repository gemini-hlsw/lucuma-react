// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.beautifuldnd

import japgolly.scalajs.react.*
import japgolly.scalajs.react.facade as Raw
import japgolly.scalajs.react.vdom.html_<^.*
import org.scalajs.dom.html

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.annotation.JSName

case class Provided(innerRef: TagMod, droppableProps: TagMod, placeholder: TagMod)

object Provided {
  def apply(provided: Droppable.ProvidedJS): Provided =
    Provided(
      TagMod.fn(_.addRefFn(provided.innerRef)),
      TagMod.fn(_.addAttrsObject(provided.droppableProps)),
      js.|.undefOr2ops[Raw.React.Node](provided.placeholder).toOption.whenDefined(identity)
    )
}

object Droppable {
  @js.native
  @JSImport("@atlaskit/pragmatic-drag-and-drop-react-beautiful-dnd-migration", "Droppable")
  object RawComponent extends js.Object

  // https://github.com/atlassian/react-beautiful-dnd/blob/master/docs/api/droppable.md

  type DroppableProps = js.Object

  @js.native
  protected[beautifuldnd] trait ProvidedJS extends js.Object {
    val innerRef: Raw.React.RefFn[html.Element]
    val droppableProps: DroppableProps
    val placeholder: js.UndefOr[Raw.React.Node]
  }

  @js.native
  trait StateSnapshot extends js.Object {
    val isDraggingOver: Boolean
    val draggingOverWith: js.UndefOr[DraggableId]
    val draggingFromThisWith: js.UndefOr[DraggableId]
    val isUsingPlaceholder: Boolean
  }

  @js.native
  trait Props extends js.Object {
    var droppableId: DroppableId
    @JSName("type") var tpe: js.UndefOr[TypeId]
    var mode: js.UndefOr[DroppableMode]
    var isDropDisabled: js.UndefOr[Boolean]
    var isCombineEnabled: js.UndefOr[Boolean]
    var direction: js.UndefOr[Direction]
    var ignoreContainerClipping: js.UndefOr[Boolean]
    var renderClone: js.UndefOr[Draggable.RenderJS]
    var getContainerForClone: js.UndefOr[Unit => html.Element]
    var children: js.Function2[ProvidedJS, StateSnapshot, Raw.React.Node]
  }
  object Props {
    def apply(
      droppableId:             DroppableId,
      tpe:                     js.UndefOr[TypeId] = js.undefined,
      mode:                    js.UndefOr[DroppableMode] = js.undefined,
      isDropDisabled:          js.UndefOr[Boolean] = js.undefined,
      isCombineEnabled:        js.UndefOr[Boolean] = js.undefined,
      direction:               js.UndefOr[Direction] = js.undefined,
      ignoreContainerClipping: js.UndefOr[Boolean] = js.undefined,
      renderClone:             js.UndefOr[Draggable.Render] = js.undefined,
      getContainerForClone:    js.UndefOr[Unit => html.Element] = js.undefined,
      children:                (Provided, StateSnapshot) => VdomNode
    ): Props = {
      val p = (new js.Object).asInstanceOf[Props]
      p.droppableId = droppableId
      tpe.foreach(p.tpe = _)
      js.|.undefOr2ops[DroppableMode](mode).foreach(p.mode = _)
      isDropDisabled.foreach(p.isDropDisabled = _)
      isCombineEnabled.foreach(p.isCombineEnabled = _)
      js.|.undefOr2ops[Direction](direction).foreach(p.direction = _)
      ignoreContainerClipping.foreach(p.ignoreContainerClipping = _)
      renderClone.foreach(f =>
        p.renderClone = (
          (
            p,
            ss,
            r
          ) => f(Draggable.Provided(p), ss, r).rawNode
        ): Draggable.RenderJS
      )
      getContainerForClone.foreach(p.getContainerForClone = _)
      p.children = (p, ss) => children(Provided(p), ss).rawNode
      p
    }
  }

  val component = JsComponent.force[Props, Children.None, Null](RawComponent)

  def apply(
    droppableId:             DroppableId,
    tpe:                     js.UndefOr[TypeId] = js.undefined,
    mode:                    js.UndefOr[DroppableMode] = js.undefined,
    isDropDisabled:          js.UndefOr[Boolean] = js.undefined,
    isCombineEnabled:        js.UndefOr[Boolean] = js.undefined,
    direction:               js.UndefOr[Direction] = js.undefined,
    ignoreContainerClipping: js.UndefOr[Boolean] = js.undefined,
    renderClone:             js.UndefOr[Draggable.Render] = js.undefined,
    getContainerForClone:    js.UndefOr[Unit => html.Element] = js.undefined
  )(children: (Provided, StateSnapshot) => VdomNode) =
    component(
      Props(droppableId,
            tpe,
            mode,
            isDropDisabled,
            isCombineEnabled,
            direction,
            ignoreContainerClipping,
            renderClone,
            getContainerForClone,
            children
      )
    )
}
