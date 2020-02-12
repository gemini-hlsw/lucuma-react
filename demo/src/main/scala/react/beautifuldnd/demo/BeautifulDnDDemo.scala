package react.beautifuldnd.demo

import cats._
import cats.implicits._
import japgolly.scalajs.react._
import japgolly.scalajs.react.{raw => Raw}
import japgolly.scalajs.react.vdom.Builder
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.internal.JsUtil
import org.scalajs.dom.document
import react.beautifuldnd._

import scala.scalajs.js

object BeautifulDnDDemo {

  final case class Props()
  final case class State(list: List[String] = List("one", "two", "three", "four"))

  implicit object CallbackMonoid extends Monoid[Callback] {
    def empty: Callback = Callback.empty
    def combine(x: Callback, y: Callback): Callback = x *> y
  }

  import scala.scalajs.js.|
  implicit class OrNullOps[A](a: A | Null) {
    def toOption: Option[A] = 
      if( a == null)
        None
      else
        Some(a.asInstanceOf[A])
  }

  /*def callbackFnRef[A <: TopNode](refFn: Raw.React.RefFn[A])(implicit ev: Null <:< A): TagOf.RefArg[A] =
    TagOf.RefArg.set(
      new Ref.Set[A] {
        val set: CallbackKleisli[Option[A], Unit] = 
          CallbackKleisli((oi: Option[A]) => Callback{
            println(s"SETTING REF TO $oi")
            refFn(oi.orUndefined.orNull[A])
          })

        def contramap[B](f: B => A): Ref.Set[B] = ???

        def narrow[B <: A]: Ref.Set[B] = ???
      }
    )*/

  implicit class BuilderOps(b: Builder) {
    def addAttrsObject(o: js.Object): Unit =
      for ((k, v) <- JsUtil.objectIterator(o)) b.addAttr(k, v)

    def addRefFn[A](refFn: Raw.React.RefFn[A]): Unit =
      b.addAttr("ref", refFn)
  }

  class Backend($: BackendScope[Props, State]) {
    def reorder[A](source: Int, destination: Int)(list: List[A]): List[A] = {
      val removed = list(source)
      val intermediate = list.take(source) ++ list.drop(source + 1)
      (intermediate.take(destination) :+ removed) ++ intermediate.drop(destination)
    }

    def onDragEnd(result: DropResult, provided: ResponderProvided): Callback = {
      println(js.JSON.stringify(result))

      result.destination.toOption.foldMap[Callback]{ destination =>
        $.modState(s => State(reorder(result.source.index, destination.index)(s.list)))
      }
    }
    

    def render(p: Props, s: State): VdomElement =
      <.div(
        <.div(^.height := "600px", ^.width := "1000px")(
          DragDropContext.component(DragDropContext.props(onDragEnd = onDragEnd))(
            Droppable(Droppable.props("droppableList")){ case (provided, snapshot) =>
              // println(provided.innerRef)
              <.div(
                TagMod.fn(_.addRefFn(provided.innerRef)),
                TagMod.fn(_.addAttrsObject(provided.droppableProps))
              )(
                  <.b("Good to go:"),
                  s.list.zipWithIndex.toTagMod{ case (item, index) =>
                    Draggable(Draggable.props(draggableId=item, index=index)) { case (provided, snapshot, rubric) =>
                      <.div(//.withRef(callbackFnRef(provided.innerRef))(
                        TagMod.fn(_.addRefFn(provided.innerRef)),
                        TagMod.fn(_.addAttrsObject(provided.draggableProps)),
                        Option(provided.dragHandleProps.asInstanceOf[js.Object]).whenDefined( dragHandleProps =>
                          TagMod.fn(_.addAttrsObject(dragHandleProps))
                        )
                      )(
                        item
                      )
                    }
                  },
                  provided.placeholder
              )
            }
          )  
        )
      )
  }

  val component = ScalaComponent
    .builder[Props]("BeautifulDnDDemo ")
    .initialState(State())
    .backend(new Backend(_))
    .renderBackend
    .build

  def apply(p: Props) = component(p)
}

object AgGridDemo {
  val component = ScalaComponent
    .builder[Unit]("Demo")
    .stateless
    .render_P { _ =>
      <.div(
        BeautifulDnDDemo(BeautifulDnDDemo.Props()).vdomElement
      )
    }
    .build

  def main(args: Array[String]): Unit = {
    component().renderIntoDOM(document.getElementById("root"))
    ()
  }
}