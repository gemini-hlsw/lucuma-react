package react.table.demo

import scala.scalajs.js.annotation.JSExportTopLevel

import org.scalajs.dom
import scala.scalajs.js
import js.annotation._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import reactST.reactTable.mod._

@JSExportTopLevel("DemoMain")
object DemoMain {

  def logit(message: String, obj: Any) = dom.console.log(message, obj.asInstanceOf[js.Any])
  trait RowData extends js.Object {
    val make: String
    val model: String
    val year: Int
  }

  object RowData {
    def apply(make: String, model: String, year: Int): RowData =
      js.Dynamic.literal("make" -> make, "model" -> model, "year" -> year).asInstanceOf[RowData]
  }

  val rowData =
    js.Array(RowData("Fender", "Stratocaster", 2019),
             RowData("Gibson", "Les Paul", 1958),
             RowData("Fender", "Telecaster", 1971)
    )

  def column(accessor: String): Column[RowData] =
    js.Dynamic
      .literal("Header" -> accessor, "accessor" -> accessor)
      .asInstanceOf[ColumnWithLooseAccessor[RowData]]

  val columns: js.Array[Column[RowData]] = js.Array(column("make"), column("model"), column("year"))

  def propsToAttrs(obj: js.Object): TagMod =
    js.Object.getOwnPropertyNames(obj).toTagMod { k =>
      val value = js.Object.getOwnPropertyDescriptor(obj, k).value.toString()
      println(s"Key: $k, Value: $value")
      logit("Value:", value)
      VdomAttr(k) := value
    }

  val component =
    ScalaFnComponent[Unit] { _ =>
      val options       = UseTableOptions[RowData](columns, rowData)
      val tableInstance = useTable(options)
      val bodyProps     = tableInstance.getTableBodyProps()

      logit("Table instance: ", tableInstance)
      logit("Rows:", tableInstance.rows)
      logit("Body props:", bodyProps)

      val headerRows = tableInstance.headers.toTagMod { col =>
        <.th(propsToAttrs(col.getHeaderProps()), col.render("Header"))
      }
      val rows       = tableInstance.rows.toTagMod { rd =>
        tableInstance.prepareRow(rd)
        val cells = rd.cells.toTagMod { cell =>
          <.td(propsToAttrs(cell.getCellProps()), cell.render("Cell"))
        }
        <.tr(propsToAttrs(rd.getRowProps()), cells)
      }

      <.table(<.thead(<.tr(headerRows)), <.tbody(propsToAttrs(bodyProps), rows))
    }

  @JSExport
  def main(): Unit = {

    val container = Option(dom.document.getElementById("root")).getOrElse {
      val elem = dom.document.createElement("div")
      elem.id = "root"
      dom.document.body.appendChild(elem)
      elem
    }

    component().renderIntoDOM(container)

    ()
  }
}
