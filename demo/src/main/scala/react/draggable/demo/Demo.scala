package react.draggable.demo

import scala.scalajs.js
import scala.scalajs.js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.raw.JsNumber
import org.scalajs.dom
import org.scalajs.dom.MouseEvent
import react.virtualized._
import react.draggable._
import react.common.Size
import Data.DataRow

object TableDemo {
  def datum(data:     List[DataRow])(i: Int) = data(i % data.length)
  def rowheight(data: List[DataRow])(i: Int) = datum(data)(i).size

  final case class Widths(index:              Double, name:        Double, random:        Double)
  final case class Props(useDynamicRowHeight: Boolean, sortBy:     String, s:             Size)
  final case class State(sortDirection:       SortDirection, data: List[DataRow], widths: Widths)

  def headerRenderer(rs: (String, JsNumber) => Callback)(
    columnData:          DataRow,
    dataKey:             String,
    disableSort:         Option[Boolean],
    label:               VdomNode,
    sortByParam:         Option[String],
    sortDirection:       SortDirection
  ): VdomNode =
    React.Fragment.withKey(dataKey)(
      <.div(
        ^.cls := "ReactVirtualized__Table__headerTruncatedText",
        label
      ),
      Draggable(
        Draggable.props(
          axis                     = Axis.X,
          defaultClassName         = "DragHandle",
          defaultClassNameDragging = "DragHandleActive",
          onDrag                   = (ev: MouseEvent, d: DraggableData) => rs(dataKey, d.deltaX),
          position                 = ControlPosition(0)
        ),
        <.span(^.cls := "DragHandleIcon", "⋮")
      )
    )

  def rowClassName(i: Int): String = i match {
    case x if x < 0      => "headerRow"
    case x if x % 2 == 0 => "evenRow"
    case _               => "oddRow"
  }

  implicit class JsNumberOps(val d: JsNumber) extends AnyVal {
    def toDouble = (d: Any) match {
      case d: Double => d
    }
  }

  val component = ScalaComponent
    .builder[Props]("TableDemo")
    .initialState(State(SortDirection.ASC, Data.generateRandomList, Widths(0.1, 0.4, 0.6)))
    .renderPS { ($, props, state) =>
      def resizeRow(k: String, dx: JsNumber): Callback =
        $.modState { s =>
          val percentDelta = dx.toDouble / props.s.width.toDouble
          k match {
            case "index" =>
              s.copy(
                widths = s.widths.copy(s.widths.index + percentDelta,
                                       s.widths.name - percentDelta,
                                       s.widths.random - percentDelta)
              )
            case "name" =>
              s.copy(
                widths = s.widths.copy(s.widths.index + percentDelta,
                                       s.widths.name + percentDelta,
                                       s.widths.random - percentDelta)
              )
            case "randow" =>
              s.copy(
                widths = s.widths.copy(s.widths.index + percentDelta,
                                       s.widths.name + percentDelta,
                                       s.widths.random + percentDelta)
              )
          }
        }

      def sort(index: String, sortDirection: SortDirection): Callback = {
        val sorted = state.data.sortBy(_.index)
        $.setState(
          state.copy(data          = if (sortDirection == SortDirection.ASC) sorted else sorted.reverse,
                     sortDirection = sortDirection)
        )
      }
      val columns = List(
        Column(
          Column.props((props.s.width.toDouble * state.widths.index).toInt,
                       "index",
                       label          = "Index",
                       disableSort    = false,
                       headerRenderer = headerRenderer(resizeRow))
        ),
        Column(
          Column.props((props.s.width.toDouble * state.widths.name).toInt,
                       "name",
                       label          = "Full Name",
                       disableSort    = false,
                       headerRenderer = headerRenderer(resizeRow))
        ),
        Column(
          Column.props(
            (props.s.width.toDouble * state.widths.random).toInt,
            "random",
            disableSort = true,
            className   = "exampleColumn",
            label       = "The description label is so long it will be truncated",
            flexGrow    = 1,
            cellRenderer =
              (cellData: DataRow, _: js.Any, _: String, _: js.Any, _: Int) => cellData.toString,
            headerRenderer = headerRenderer(resizeRow)
          )
        )
      )
      val t = Table(
        Table.props(
          disableHeader    = false,
          noRowsRenderer   = () => <.div(^.cls := "noRows", "No rows"),
          overscanRowCount = 10,
          rowClassName     = rowClassName _,
          height           = 270,
          rowCount         = 1000,
          rowHeight        = if (props.useDynamicRowHeight) rowheight(state.data) _ else 40,
          onRowClick       = x => Callback.log(x),
          width            = props.s.width,
          rowGetter        = datum(state.data),
          headerClassName  = "headerColumn",
          sort             = sort _,
          sortBy           = props.sortBy,
          sortDirection    = state.sortDirection,
          headerHeight     = 30
        ),
        columns: _*
      )
      t.mapMounted(_.raw.scrollToRow(20))
      t
    }
    .build

  def apply(p: Props) = component(p)
}

@JSExportTopLevel("Demo")
object Demo {
  @JSImport("react-virtualized/styles.css", JSImport.Default)
  @js.native
  object ReactVirtualizedStyles extends js.Object

  @JSExport
  def main(): Unit = {
    // needed to load the styles
    ReactVirtualizedStyles
    val container = Option(dom.document.getElementById("root")).getOrElse {
      val elem = dom.document.createElement("div")
      elem.id = "root"
      dom.document.body.appendChild(elem)
      elem
    }
    val tableF = (s: Size) => TableDemo(TableDemo.Props(true, "index", s)).vdomElement

    AutoSizer(AutoSizer.props(tableF, disableHeight = true)).renderIntoDOM(container)
    ()
  }
}
