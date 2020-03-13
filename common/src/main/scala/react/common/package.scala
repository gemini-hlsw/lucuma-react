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
  type GenericFnComponentPC[P <: js.Object] =
    GenericFnComponentC[P, CtorType.PropsAndChildren, Unit]
  type GenericFnComponentPA[P <: js.Object] = GenericFnComponentA[P, CtorType.Props, Unit]
  type GenericFnComponentPAC[P <: js.Object] =
    GenericFnComponentAC[P, CtorType.PropsAndChildren, Unit]

  type GenericComponentP[P <: js.Object]  = GenericJsComponent[P, CtorType.Props, Unit]
  type GenericComponentPC[P <: js.Object] = GenericJsComponentC[P, CtorType.PropsAndChildren, Unit]
  type GenericComponentPA[P <: js.Object] = GenericJsComponentA[P, CtorType.Props, Unit]
  type GenericComponentPAC[P <: js.Object] =
    GenericJsComponentAC[P, CtorType.PropsAndChildren, Unit]

  implicit class GenericFnComponentPCOps[P <: js.Object](val c: GenericFnComponentPC[P])
      extends AnyVal {
    def apply(children: VdomNode*): GenericFnComponentPC[P] = c.withChildren(children)
  }

  implicit class GenericFnComponentPAOps[P <: js.Object](val c: GenericFnComponentPA[P])
      extends AnyVal {
    def apply(modifiers: TagMod*): GenericFnComponentPA[P] = c.addModifiers(modifiers)
  }

  implicit class GenericFnComponentPACOps[P <: js.Object](val c: GenericFnComponentPAC[P])
      extends AnyVal {
    def apply(modifiers: TagMod*): GenericFnComponentPAC[P] = c.addModifiers(modifiers)
  }

  implicit class GenericComponentPCOps[P <: js.Object](val c: GenericComponentPC[P])
      extends AnyVal {
    def apply(children: VdomNode*): GenericComponentPC[P] = c.withChildren(children)
  }

  implicit class GenericComponentPAOps[P <: js.Object](val c: GenericComponentPA[P])
      extends AnyVal {
    def apply(modifiers: TagMod*): GenericComponentPA[P] = c.addModifiers(modifiers)
  }

  implicit class GenericComponentPACOps[P <: js.Object](val c: GenericComponentPAC[P])
      extends AnyVal {
    def apply(modifiers: TagMod*): GenericComponentPAC[P] = c.addModifiers(modifiers)
  }
  // End JS Components
}
