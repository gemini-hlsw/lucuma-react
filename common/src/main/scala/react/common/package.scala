package react

import japgolly.scalajs.react.CtorType
import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.VdomNode
import japgolly.scalajs.react.component.Generic
import japgolly.scalajs.react.component.Js.RawMounted
import japgolly.scalajs.react.component.Js.UnmountedWithRawType
import japgolly.scalajs.react.vdom.TagMod
import scala.scalajs.js
import react.common.syntax.AllSyntax

package object common extends AllSyntax {

  def merge(a: js.Object, b: js.Object): js.Object = {
    val m = js.Dictionary.empty[js.Any]
    val c = a.asInstanceOf[js.Dictionary[js.Any]]
    for {
      p <- js.Object.getOwnPropertyNames(a)
      v <- c.get(p)
    } yield m(p) = v

    val d = b.asInstanceOf[js.Dictionary[js.Any]]
    for {
      p <- js.Object.getOwnPropertyNames(b)
      v <- d.get(p)
    } yield m(p) = v

    m.asInstanceOf[js.Object]
  }

  def filterProps[P <: js.Object](p: P, name: String, names: String*): P = {
    val o = js.Dictionary.empty[js.Any]
    val c = p.asInstanceOf[js.Dictionary[js.Any]]
    for {
      k <- js.Object.getOwnPropertyNames(p) if name != k && !names.contains(k)
      v <- c.get(k)
    } yield o(k) = v
    o.asInstanceOf[P]
  }

  val Style = style.Style
  val Css   = style.Css
  type Style = style.Style
  type Css   = style.Css

  // Begin Scala Components
  implicit def props2Component(p: ReactProps): VdomElement = p.render

  implicit def propsWithChildren2Component(p: ReactPropsWithChildren): RenderWithChildren =
    new RenderWithChildren(p)

  implicit def propsWithEmptyChildren2Component(p: ReactPropsWithChildren): VdomElement =
    p.render(Seq.empty)
  // End Scala Components

  // Begin JS Components
  type RenderFn[P]             = Generic.Unmounted[P, Unit]
  type Render[P <: js.Object]  = UnmountedWithRawType[P, Null, RawMounted[P, Null]]
  type RenderFnC[P]            = CtorType.ChildrenArgs => RenderFn[P]
  type RenderC[P <: js.Object] = CtorType.ChildrenArgs => Render[P]

  type GenericFnComponentP[P <: js.Object] = GenericFnComponent[P, CtorType.Props, Unit]
  type GenericFnComponentPC[P <: js.Object, A] =
    GenericFnComponentC[P, CtorType.PropsAndChildren, Unit, A]
  type GenericFnComponentPA[P <: js.Object, A] = GenericFnComponentA[P, CtorType.Props, Unit, A]
  type GenericFnComponentPAC[P <: js.Object, A] =
    GenericFnComponentAC[P, CtorType.PropsAndChildren, Unit, A]

  type GenericComponentP[P <: js.Object] = GenericJsComponent[P, CtorType.Props, Unit]
  type GenericComponentPC[P <: js.Object, A] =
    GenericJsComponentC[P, CtorType.PropsAndChildren, Unit, A]
  type GenericComponentPA[P <: js.Object, A] = GenericJsComponentA[P, CtorType.Props, Unit, A]
  type GenericComponentPAC[P <: js.Object, A] =
    GenericJsComponentAC[P, CtorType.PropsAndChildren, Unit, A]

  implicit class GenericFnComponentPCOps[P <: js.Object, A](val c: GenericFnComponentPC[P, A])
      extends AnyVal {
    def apply(children: VdomNode*): A = c.withChildren(children)
  }

  implicit class GenericFnComponentPAOps[P <: js.Object, A](val c: GenericFnComponentPA[P, A])
      extends AnyVal {
    def apply(modifiers: TagMod*): A = c.addModifiers(modifiers)
  }

  implicit class GenericFnComponentPACOps[P <: js.Object, A](val c: GenericFnComponentPAC[P, A])
      extends AnyVal {
    def apply(modifiers: TagMod*): A = c.addModifiers(modifiers)
  }

  implicit class GenericComponentPCOps[P <: js.Object, A](val c: GenericComponentPC[P, A])
      extends AnyVal {
    def apply(children: VdomNode*): A = c.withChildren(children)
  }

  implicit class GenericComponentPAOps[P <: js.Object, A](val c: GenericComponentPA[P, A])
      extends AnyVal {
    def apply(modifiers: TagMod*): A = c.addModifiers(modifiers)
  }

  implicit class GenericComponentPACOps[P <: js.Object, A](val c: GenericComponentPAC[P, A])
      extends AnyVal {
    def apply(modifiers: TagMod*): A = c.addModifiers(modifiers)
  }
  // End JS Components
}
