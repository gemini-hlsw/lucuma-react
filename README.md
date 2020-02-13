# scalajs-react-beautiful-dnd

Facade to use [`react-beautiful-dnd`](https://github.com/atlassian/react-beautiful-dnd) in [`scalajs-react`](https://github.com/japgolly/scalajs-react).

## Usage

```scala
  import react.beautifuldnd._

  case class Item(id: String, content: String)

  def onDragEnd(result: DropResult, provided: ResponderProvided): Callback = ???

  def getListStyle(isDraggingOver: Boolean): TagMod = ???

  def getItemStyle(isDragging: Boolean, draggableStyle: TagMod): TagMod = TagMod(
    ...,
    draggableStyle
  )

  DragDropContext(onDragEnd = onDragEnd)(
    Droppable("droppable-1"){ case (provided, snapshot) =>
      <.div(
        provided.innerRef, 
        provided.droppableProps,
        getListStyle(snapshot.isDraggingOver)
      )(
        state.list.zipWithIndex.toTagMod{ case (item, index) =>
          Draggable(item.id, index) { case (provided, snapshot, rubric) =>
            <.div(
              provided.innerRef,                       
              provided.draggableProps, 
              provided.dragHandleProps,
              getItemStyle(snapshot.isDragging, provided.draggableStyle)
            )(
              item.content
            )
          }
        },
        provided.placeholder
      )
    }
  )   
```

- Run `sbt restartWDS` and navigate to `localhost:8080` for a demo.

- `SensorAPI` not yet typed.