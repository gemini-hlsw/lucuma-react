// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react

import japgolly.scalajs.react._
import japgolly.scalajs.react.component.Generic
import japgolly.scalajs.react.component.Js._
import japgolly.scalajs.react.component.Scala
import japgolly.scalajs.react.component.ScalaFn
import japgolly.scalajs.react.component.ScalaForwardRef
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.VdomNode

import scala.language.implicitConversions
import scala.scalajs.js

package object common extends react.common.syntax.AllSyntax {

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
  @inline implicit def props2Component[Props, S, B, CT[-p, +u] <: CtorType[p, u]](
    p: ReactRender[Props, CT, Scala.Unmounted[Props, S, B]]
  ): VdomElement =
    p.toUnmounted

  implicit class PropsWithChildren2Component[Props, S, B](
    p: ReactRender[Props, CtorType.PropsAndChildren, Scala.Unmounted[Props, S, B]]
  ) {
    @inline def apply(first: CtorType.ChildArg, rest: CtorType.ChildArg*): VdomElement =
      p(first, rest: _*)
  }

  @inline implicit def fnProps2Component[Props, CT[-p, +u] <: CtorType[p, u]](
    p: ReactRender[Props, CT, ScalaFn.Unmounted[Props]]
  ): VdomElement =
    p.toUnmounted

  implicit class FnPropsWithChildren2Component[Props](
    p: ReactRender[Props, CtorType.PropsAndChildren, ScalaFn.Unmounted[Props]]
  ) {
    @inline def apply(first: CtorType.ChildArg, rest: CtorType.ChildArg*): VdomElement =
      p(first, rest: _*)
  }

  @inline implicit def propsForwardRef2Component[Props, R, CT[-p, +u] <: CtorType[p, u]](
    p: ReactRender[Props, CT, ScalaForwardRef.Unmounted[Props, R]]
  ): VdomElement =
    p.toUnmounted

  implicit class PropsForwardRefWithChildren2Component[Props, R](
    p: ReactRender[Props, CtorType.PropsAndChildren, ScalaForwardRef.Unmounted[Props, R]]
  ) {
    @inline def apply(first: CtorType.ChildArg, rest: CtorType.ChildArg*): VdomElement =
      p(first, rest: _*)
  }
  // End Scala Components

  // Begin JS Components
  type RenderFn[P]                              = Generic.Unmounted[P, Unit]
  type Render[P <: js.Object]                   = Unmounted[P, Null]
  type RenderF[P <: js.Object, F <: js.Object]  = UnmountedWithFacade[P, Null, F]
  type RenderFnC[P]                             = CtorType.ChildrenArgs => RenderFn[P]
  type RenderC[P <: js.Object]                  = CtorType.ChildrenArgs => Render[P]
  type RenderCF[P <: js.Object, F <: js.Object] = CtorType.ChildrenArgs => RenderF[P, F]

  type GenericFnComponentP[P <: js.Object]      = GenericFnComponent[P, CtorType.Props, Unit]
  type GenericFnComponentPC[P <: js.Object, A]  =
    GenericFnComponentC[P, CtorType.PropsAndChildren, Unit, A]
  type GenericFnComponentPA[P <: js.Object, A]  = GenericFnComponentA[P, CtorType.Props, Unit, A]
  type GenericFnComponentPAC[P <: js.Object, A] =
    GenericFnComponentAC[P, CtorType.PropsAndChildren, Unit, A]

  type GenericComponentP[P <: js.Object]      = GenericJsComponent[P, CtorType.Props, Unit]
  type GenericComponentPC[P <: js.Object, A]  =
    GenericJsComponentC[P, CtorType.PropsAndChildren, Unit, A]
  type GenericComponentPA[P <: js.Object, A]  = GenericJsComponentA[P, CtorType.Props, Unit, A]
  type GenericComponentPAC[P <: js.Object, A] =
    GenericJsComponentAC[P, CtorType.PropsAndChildren, Unit, A]

  type GenericComponentPF[P <: js.Object, F <: js.Object]      =
    GenericJsComponentF[P, CtorType.Props, Unit, F]
  type GenericComponentPCF[P <: js.Object, A, F <: js.Object]  =
    GenericJsComponentCF[P, CtorType.PropsAndChildren, Unit, A, F]
  type GenericComponentPAF[P <: js.Object, A, F <: js.Object]  =
    GenericJsComponentAF[P, CtorType.Props, Unit, A, F]
  type GenericComponentPACF[P <: js.Object, A, F <: js.Object] =
    GenericJsComponentACF[P, CtorType.PropsAndChildren, Unit, A, F]

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
}
