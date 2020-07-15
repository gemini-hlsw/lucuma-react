package react.highcharts

import scala.scalajs.js
import scala.scalajs.js.JSConverters._

object implicits {
  implicit def doubleTupleList2Data[T](
      list: List[(Double, Double)]
  ): js.Array[Chart.Data] =
    list.map(t => js.Tuple2(t._1, t._2).asInstanceOf[Chart.Data]).toJSArray
}
