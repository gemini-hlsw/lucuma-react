// Copyright (c) 2016-2025 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.common

import japgolly.scalajs.react.*
import japgolly.scalajs.react.component.Generic
import japgolly.scalajs.react.component.Js.*

import scala.scalajs.js

private def merge(a: js.Object, b: js.Object): js.Object = {
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

private def filterProps[P <: js.Object](p: P, name: String, names: String*): P = {
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

export syntax.all.{*, given}
