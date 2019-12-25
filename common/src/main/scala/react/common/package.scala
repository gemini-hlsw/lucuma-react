package react

import japgolly.scalajs.react.CtorType
import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.VdomNode
import japgolly.scalajs.react.component.Generic
import japgolly.scalajs.react.component.Js.RawMounted
import japgolly.scalajs.react.component.Js.UnmountedWithRawType
import scala.scalajs.js
import scala.scalajs.js.|

package object common {
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

  implicit def props2Component(p: ReactProps): VdomElement = p.render

  implicit def propsWithChildren2Component(p: ReactPropsWithChildren): RenderWithChildren =
    new RenderWithChildren(p)

  implicit def propsWithEmptyChildren2Component(p: ReactPropsWithChildren): VdomElement =
    p.render(Seq.empty)

  type RenderFn[P]            = Generic.Unmounted[P, Unit]
  type Render[P <: js.Object] = UnmountedWithRawType[P, Null, RawMounted[P, Null]]
  type RenderFnC[P]           = CtorType.ChildrenArgs => RenderFn[P]
  type RenderC[P <: js.Object] =
    CtorType.ChildrenArgs => Render[P]
  type GenericFnComponentPC[P <: js.Object] =
    GenericFnComponentC[P, CtorType.PropsAndChildren, Unit]
  type GenericFnComponentP[P <: js.Object] = GenericFnComponent[P, CtorType.Props, Unit]
  type GenericComponentPC[P <: js.Object]  = GenericComponentC[P, CtorType.PropsAndChildren, Unit]

  implicit def gProps2FnUnmountedPA[P <: js.Object](
    p: GenericFnComponentPC[P]
  ): RenderFn[P] =
    p.render

  implicit def gProps2UnmountedPC[P <: js.Object](
    p: GenericComponentPC[P]
  ): Render[P] =
    p.render

  implicit def gProps2VdomPC[P <: js.Object](
    p: GenericComponentPC[P]
  ): VdomNode =
    p.render

  implicit def gFnProps2VdomP[P <: js.Object](
    p: GenericFnComponentP[P]
  ): Render[P] =
    p.render

  val Style = style.Style
  val Css   = style.Css
  type Style = style.Style
  type Css   = style.Css
}

package common {
  trait EnumValue[A] {
    def value(a: A): String
  }

  object EnumValue {
    @inline final def apply[A](implicit ev: EnumValue[A]): EnumValue[A] = ev

    def instance[A](f: A => String): EnumValue[A] =
      new EnumValue[A] {
        def value(a: A): String = f(a)
      }

    def toLowerCaseString[A]: EnumValue[A] =
      new EnumValue[A] {
        def value(a: A): String = a.toString.toLowerCase
      }
  }

  trait EnumValueB[A] {
    def value(a: A): Boolean | String
  }

  object EnumValueB {
    @inline final def apply[A](implicit ev: EnumValueB[A]): EnumValueB[A] = ev

    def instance[A](f: A => Boolean | String): EnumValueB[A] =
      new EnumValueB[A] {
        def value(a: A): Boolean | String = f(a)
      }

    def toLowerCaseStringT[A](trueValue: A): EnumValueB[A] =
      new EnumValueB[A] {
        def value(a: A): Boolean | String = a match {
          case b if b == trueValue => true
          case _                   => a.toString.toLowerCase
        }
      }

    def toLowerCaseStringF[A](falseValue: A): EnumValueB[A] =
      new EnumValueB[A] {
        def value(a: A): Boolean | String = a match {
          case b if b == falseValue => false
          case _                    => a.toString.toLowerCase
        }
      }

    def toLowerCaseStringTF[A](trueValue: A, falseValue: A): EnumValueB[A] =
      new EnumValueB[A] {
        def value(a: A): Boolean | String = a match {
          case b if b == trueValue  => true
          case b if b == falseValue => false
          case _                    => a.toString.toLowerCase
        }
      }
  }

  trait ReactProps {
    @inline def render: VdomElement
  }

  class RenderWithChildren(p: ReactPropsWithChildren) {
    def apply(first: CtorType.ChildArg, rest: CtorType.ChildArg*): VdomElement =
      p.render(first +: rest)
  }

  trait ReactPropsWithChildren {
    @inline def render: Seq[CtorType.ChildArg] => VdomElement
  }

  trait GenericFnComponent[P <: js.Object, CT[-p, +u] <: CtorType[p, u], U] {
    @inline def render: Render[P]
  }

  trait GenericFnComponentC[P <: js.Object, CT[-p, +u] <: CtorType[p, u], U] {
    val children: CtorType.ChildrenArgs = Seq.empty
    @inline def renderWith: RenderFnC[P]
    @inline def render: RenderFn[P] = renderWith(children)
  }

  trait GenericComponentC[P <: js.Object, CT[-p, +u] <: CtorType[p, u], U] {
    val children: CtorType.ChildrenArgs = Seq.empty
    @inline def renderWith: RenderC[P]
    @inline def render: Render[P] = renderWith(children)
  }

}
