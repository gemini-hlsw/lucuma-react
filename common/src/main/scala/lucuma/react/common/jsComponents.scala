// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.common

import japgolly.scalajs.react.CtorType
import japgolly.scalajs.react.Ref
import japgolly.scalajs.react.component.Js
import japgolly.scalajs.react.component.JsFn
import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.vdom.VdomBuilder
import japgolly.scalajs.react.vdom.VdomNode

import scala.scalajs.js

class AttrsBuilder(p: js.Object) extends VdomBuilder.ToJs {
  props = p

  def toJs(attrs: Seq[TagMod]): Unit = {
    attrs.foreach(_.applyTo(this))
    addKeyToProps()
    addClassNameToProps()
    addStyleToProps()
  }
}

trait GenericFnComponent[P <: js.Object, CT[-p, +u] <: CtorType[p, u], U] {
  protected def cprops: P
  def render: RenderFn[P]
}

object GenericFnComponent:
  given [P <: js.Object, CT[-p, +u] <: CtorType[p, u], U]
    : Conversion[GenericFnComponent[P, CT, U], RenderFn[P]] =
    _.render

  given [P <: js.Object, CT[-p, +u] <: CtorType[p, u], U]
    : Conversion[GenericFnComponent[P, CT, U], VdomNode] =
    _.render

  given [P <: js.Object, CT[-p, +u] <: CtorType[p, u], U]
    : Conversion[GenericFnComponent[P, CT, U], js.UndefOr[VdomNode]] =
    _.render

trait GenericFnComponentC[P <: js.Object, CT[-p, +u] <: CtorType[p, u], U, A] {
  protected def cprops: P
  val children: CtorType.ChildrenArgs
  def withChildren(children: CtorType.ChildrenArgs): A

  def renderWith: RenderFnC[P]
  def render: RenderFn[P] = renderWith(children)
}

object GenericFnComponentC:
  extension [P <: js.Object, CT[-p, +u] <: CtorType[p, u], U, A](
    c: GenericFnComponentC[P, CT, U, A]
  ) def apply(children: VdomNode*): A = c.withChildren(children)

  given [P <: js.Object, CT[-p, +u] <: CtorType[p, u], U, A]
    : Conversion[GenericFnComponentC[P, CT, U, A], RenderFn[P]] =
    _.render

  given [P <: js.Object, CT[-p, +u] <: CtorType[p, u], U, A]
    : Conversion[GenericFnComponentC[P, CT, U, A], VdomNode] =
    _.render

  given [P <: js.Object, CT[-p, +u] <: CtorType[p, u], U, A]
    : Conversion[GenericFnComponentC[P, CT, U, A], js.UndefOr[VdomNode]] =
    _.render

trait Passthrough[P <: js.Object] {
  protected def cprops: P
  val modifiers: Seq[TagMod]
}

trait PassthroughA[P <: js.Object] extends Passthrough[P] {
  def rawProps: P = {
    val props   = cprops
    val builder = new AttrsBuilder(props)
    builder.toJs(modifiers)
    props
  }
}

trait PassthroughAC[P <: js.Object] extends Passthrough[P] {
  def rawModifiers: (P, List[VdomNode]) = {
    val props   = cprops
    val builder = new AttrsBuilder(props)
    builder.toJs(modifiers)
    (props, builder.childrenAsVdomNodes)
  }
}

trait GenericFnComponentA[P <: js.Object, CT[-p, +u] <: CtorType[p, u], U, A]
    extends PassthroughA[P] {
  protected val component: JsFn.Component[P, CT]
  def addModifiers(modifiers: Seq[TagMod]): A

  inline def render: RenderFn[P] = component.applyGeneric(rawProps)()
}

object GenericFnComponentA:
  extension [P <: js.Object, CT[-p, +u] <: CtorType[p, u], U, A](
    c: GenericFnComponentA[P, CT, U, A]
  ) def apply(modifiers: TagMod*): A = c.addModifiers(modifiers)

  given [P <: js.Object, CT[-p, +u] <: CtorType[p, u], U, A]
    : Conversion[GenericFnComponentA[P, CT, U, A], RenderFn[P]] =
    _.render

  given [P <: js.Object, CT[-p, +u] <: CtorType[p, u], U, A]
    : Conversion[GenericFnComponentA[P, CT, U, A], VdomNode] =
    _.render

  given [P <: js.Object, CT[-p, +u] <: CtorType[p, u], U, A]
    : Conversion[GenericFnComponentA[P, CT, U, A], js.UndefOr[VdomNode]] =
    _.render

trait GenericFnComponentAC[P <: js.Object, CT[-p, +u] <: CtorType[p, u], U, A]
    extends PassthroughAC[P] {
  protected val component: JsFn.Component[P, CT]
  def addModifiers(modifiers: Seq[TagMod]): A

  inline def render: RenderFn[P] = {
    val (props, children) = rawModifiers
    component.applyGeneric(props)(children*)
  }
}

object GenericFnComponentAC:
  extension [P <: js.Object, CT[-p, +u] <: CtorType[p, u], U, A](
    c: GenericFnComponentAC[P, CT, U, A]
  ) def apply(modifiers: TagMod*): A = c.addModifiers(modifiers)

  given [P <: js.Object, CT[-p, +u] <: CtorType[p, u], U, A]
    : Conversion[GenericFnComponentAC[P, CT, U, A], RenderFn[P]] =
    _.render

  given [P <: js.Object, CT[-p, +u] <: CtorType[p, u], U, A]
    : Conversion[GenericFnComponentAC[P, CT, U, A], VdomNode] =
    _.render

  given [P <: js.Object, CT[-p, +u] <: CtorType[p, u], U, A]
    : Conversion[GenericFnComponentAC[P, CT, U, A], js.UndefOr[VdomNode]] =
    _.render

trait GenericJsComponent[P <: js.Object, CT[-p, +u] <: CtorType[p, u], U] { self =>
  protected def cprops: P
  protected val component: Js.Component[P, Null, CT]

  def rawProps: P              = cprops
  inline def render: Render[P] = component.applyGeneric(rawProps)()

  private def copyComponent(
    newComponent: Js.Component[P, Null, CT]
  ): GenericJsComponent[P, CT, U] =
    new GenericJsComponent[P, CT, U] {
      override protected def cprops: P = self.cprops
      override protected val component = newComponent
    }

  def withRef(ref: Ref.Handle[Js.RawMounted[P, Null]]): GenericJsComponent[P, CT, U] =
    copyComponent(self.component.withRef(ref))

  def withOptionalRef(
    ref: Option[Ref.Handle[Js.RawMounted[P, Null]]]
  ): GenericJsComponent[P, CT, U] =
    copyComponent(self.component.withOptionalRef(ref))
}

object GenericJsComponent:
  given [P <: js.Object, CT[-p, +u] <: CtorType[p, u], U]
    : Conversion[GenericJsComponent[P, CT, U], Render[P]] =
    _.render

  given [P <: js.Object, CT[-p, +u] <: CtorType[p, u], U]
    : Conversion[GenericJsComponent[P, CT, U], VdomNode] =
    _.render

  given [P <: js.Object, CT[-p, +u] <: CtorType[p, u], U]
    : Conversion[GenericJsComponent[P, CT, U], js.UndefOr[VdomNode]] =
    _.render

trait GenericJsComponentC[P <: js.Object, CT[-p, +u] <: CtorType[p, u], U, A] { self =>
  protected def cprops: P
  val children: CtorType.ChildrenArgs
  def withChildren(children: CtorType.ChildrenArgs): A
  protected val component: Js.Component[P, Null, CT]

  def rawProps: P                   = cprops
  inline def renderWith: RenderC[P] = component.applyGeneric(rawProps)
  inline def render: Render[P]      = renderWith(children)

  private def copyComponent(
    newComponent: Js.Component[P, Null, CT]
  ): GenericJsComponentC[P, CT, U, A] =
    new GenericJsComponentC[P, CT, U, A] {
      override protected def cprops: P = self.cprops
      override val children            = self.children
      override def withChildren(children: CtorType.ChildrenArgs): A = self.withChildren(children)
      override protected val component = newComponent
    }

  def withRef(ref: Ref.Handle[Js.RawMounted[P, Null]]): GenericJsComponentC[P, CT, U, A] =
    copyComponent(self.component.withRef(ref))

  def withOptionalRef(
    ref: Option[Ref.Handle[Js.RawMounted[P, Null]]]
  ): GenericJsComponentC[P, CT, U, A] =
    copyComponent(self.component.withOptionalRef(ref))
}

object GenericJsComponentC:
  extension [P <: js.Object, CT[-p, +u] <: CtorType[p, u], U, A](
    c: GenericJsComponentC[P, CT, U, A]
  ) def apply(children: VdomNode*): A = c.withChildren(children)

  given [P <: js.Object, CT[-p, +u] <: CtorType[p, u], U, A]
    : Conversion[GenericJsComponentC[P, CT, U, A], Render[P]] =
    _.render

  given [P <: js.Object, CT[-p, +u] <: CtorType[p, u], U, A]
    : Conversion[GenericJsComponentC[P, CT, U, A], VdomNode] =
    _.render

  given [P <: js.Object, CT[-p, +u] <: CtorType[p, u], U, A]
    : Conversion[GenericJsComponentC[P, CT, U, A], js.UndefOr[VdomNode]] =
    _.render

trait GenericJsComponentA[P <: js.Object, CT[-p, +u] <: CtorType[p, u], U, A]
    extends PassthroughA[P] { self =>
  protected val component: Js.Component[P, Null, CT]
  def addModifiers(modifiers: Seq[TagMod]): A

  inline def render: Render[P] = component.applyGeneric(rawProps)()

  private def copyComponent(
    newComponent: Js.Component[P, Null, CT]
  ): GenericJsComponentA[P, CT, U, A] =
    new GenericJsComponentA[P, CT, U, A] {
      override protected def cprops: P                                = self.cprops
      override val modifiers: Seq[japgolly.scalajs.react.vdom.TagMod] = self.modifiers
      override protected val component                                = newComponent
      override def addModifiers(modifiers: Seq[TagMod]): A = self.addModifiers(modifiers)
    }

  def withRef(ref: Ref.Handle[Js.RawMounted[P, Null]]): GenericJsComponentA[P, CT, U, A] =
    copyComponent(self.component.withRef(ref))

  def withOptionalRef(
    ref: Option[Ref.Handle[Js.RawMounted[P, Null]]]
  ): GenericJsComponentA[P, CT, U, A] =
    copyComponent(self.component.withOptionalRef(ref))
}

object GenericJsComponentA:
  extension [P <: js.Object, CT[-p, +u] <: CtorType[p, u], U, A](
    c: GenericJsComponentA[P, CT, U, A]
  ) def apply(modifiers: TagMod*): A = c.addModifiers(modifiers)

  given [P <: js.Object, CT[-p, +u] <: CtorType[p, u], U, A]
    : Conversion[GenericJsComponentA[P, CT, U, A], Render[P]] =
    _.render

  given [P <: js.Object, CT[-p, +u] <: CtorType[p, u], U, A]
    : Conversion[GenericJsComponentA[P, CT, U, A], VdomNode] =
    _.render

  given [P <: js.Object, CT[-p, +u] <: CtorType[p, u], U, A]
    : Conversion[GenericJsComponentA[P, CT, U, A], js.UndefOr[VdomNode]] =
    _.render

trait GenericJsComponentAC[P <: js.Object, CT[-p, +u] <: CtorType[p, u], U, A]
    extends PassthroughAC[P] { self =>
  protected val component: Js.Component[P, Null, CT]
  def addModifiers(modifiers: Seq[TagMod]): A

  inline def render: Render[P] = {
    val (props, children) = rawModifiers
    component.applyGeneric(props)(children*)
  }

  private def copyComponent(
    newComponent: Js.Component[P, Null, CT]
  ): GenericJsComponentAC[P, CT, U, A] =
    new GenericJsComponentAC[P, CT, U, A] {
      override protected def cprops: P                                = self.cprops
      override val modifiers: Seq[japgolly.scalajs.react.vdom.TagMod] = self.modifiers
      override protected val component                                = newComponent
      override def addModifiers(modifiers: Seq[TagMod]): A = self.addModifiers(modifiers)
    }

  def withRef(ref: Ref.Handle[Js.RawMounted[P, Null]]): GenericJsComponentAC[P, CT, U, A] =
    copyComponent(self.component.withRef(ref))

  def withOptionalRef(
    ref: Option[Ref.Handle[Js.RawMounted[P, Null]]]
  ): GenericJsComponentAC[P, CT, U, A] =
    copyComponent(self.component.withOptionalRef(ref))
}

object GenericJsComponentAC:
  extension [P <: js.Object, CT[-p, +u] <: CtorType[p, u], U, A](
    c: GenericJsComponentAC[P, CT, U, A]
  ) def apply(modifiers: TagMod*): A = c.addModifiers(modifiers)

  given [P <: js.Object, CT[-p, +u] <: CtorType[p, u], U, A]
    : Conversion[GenericJsComponentAC[P, CT, U, A], Render[P]] =
    _.render

  given [P <: js.Object, CT[-p, +u] <: CtorType[p, u], U, A]
    : Conversion[GenericJsComponentAC[P, CT, U, A], VdomNode] =
    _.render

  given [P <: js.Object, CT[-p, +u] <: CtorType[p, u], U, A]
    : Conversion[GenericJsComponentAC[P, CT, U, A], js.UndefOr[VdomNode]] =
    _.render

trait GenericJsComponentF[P <: js.Object, CT[-p, +u] <: CtorType[p, u], U, F <: js.Object] { self =>
  protected def cprops: P
  protected val component: Js.ComponentWithFacade[P, Null, F, CT]

  def rawProps: P                  = cprops
  inline def render: RenderF[P, F] = component.applyGeneric(rawProps)()

  private def copyComponent(
    newComponent: Js.ComponentWithFacade[P, Null, F, CT]
  ): GenericJsComponentF[P, CT, U, F] =
    new GenericJsComponentF[P, CT, U, F] {
      override protected def cprops: P = self.cprops
      override protected val component = newComponent
    }

  def withRef(ref: Ref.Handle[Js.RawMounted[P, Null] & F]): GenericJsComponentF[P, CT, U, F] =
    copyComponent(self.component.withRef(ref))

  def withOptionalRef(
    ref: Option[Ref.Handle[Js.RawMounted[P, Null] & F]]
  ): GenericJsComponentF[P, CT, U, F] =
    copyComponent(self.component.withOptionalRef(ref))
}

object GenericJsComponentF:
  given [P <: js.Object, CT[-p, +u] <: CtorType[p, u], U, F <: js.Object]
    : Conversion[GenericJsComponentF[P, CT, U, F], VdomNode] = _.render

  given [P <: js.Object, CT[-p, +u] <: CtorType[p, u], U, F <: js.Object]
    : Conversion[GenericJsComponentF[P, CT, U, F], js.UndefOr[VdomNode]] = _.render

trait GenericJsComponentCF[P <: js.Object, CT[-p, +u] <: CtorType[p, u], U, A, F <: js.Object] {
  self =>
  protected def cprops: P
  val children: CtorType.ChildrenArgs
  def withChildren(children: CtorType.ChildrenArgs): A
  protected val component: Js.ComponentWithFacade[P, Null, F, CT]

  def rawProps: P                       = cprops
  inline def renderWith: RenderCF[P, F] = component.applyGeneric(rawProps)
  inline def render: RenderF[P, F]      = renderWith(children)

  private def copyComponent(
    newComponent: Js.ComponentWithFacade[P, Null, F, CT]
  ): GenericJsComponentCF[P, CT, U, A, F] =
    new GenericJsComponentCF[P, CT, U, A, F] {
      override protected def cprops: P = self.cprops
      override val children            = self.children
      override def withChildren(children: CtorType.ChildrenArgs): A = self.withChildren(children)
      override protected val component = newComponent
    }

  def withRef(
    ref: Ref.Handle[Js.RawMounted[P, Null] & F]
  ): GenericJsComponentCF[P, CT, U, A, F] =
    copyComponent(self.component.withRef(ref))

  def withOptionalRef(
    ref: Option[Ref.Handle[Js.RawMounted[P, Null] & F]]
  ): GenericJsComponentCF[P, CT, U, A, F] =
    copyComponent(self.component.withOptionalRef(ref))
}

object GenericJsComponentCF:
  given [P <: js.Object, CT[-p, +u] <: CtorType[p, u], U, A, F <: js.Object]
    : Conversion[GenericJsComponentCF[P, CT, U, A, F], VdomNode] = _.render

  given [P <: js.Object, CT[-p, +u] <: CtorType[p, u], U, A, F <: js.Object]
    : Conversion[GenericJsComponentCF[P, CT, U, A, F], js.UndefOr[VdomNode]] = _.render

trait GenericJsComponentAF[P <: js.Object, CT[-p, +u] <: CtorType[p, u], U, A, F <: js.Object]
    extends PassthroughA[P] { self =>
  protected val component: Js.ComponentWithFacade[P, Null, F, CT]
  def addModifiers(modifiers: Seq[TagMod]): A

  inline def render: RenderF[P, F] = component.applyGeneric(rawProps)()

  private def copyComponent(
    newComponent: Js.ComponentWithFacade[P, Null, F, CT]
  ): GenericJsComponentAF[P, CT, U, A, F] =
    new GenericJsComponentAF[P, CT, U, A, F] {
      override protected def cprops: P                                = self.cprops
      override val modifiers: Seq[japgolly.scalajs.react.vdom.TagMod] = self.modifiers
      override protected val component                                = newComponent
      override def addModifiers(modifiers: Seq[TagMod]): A = self.addModifiers(modifiers)
    }

  def withRef(
    ref: Ref.Handle[Js.RawMounted[P, Null] & F]
  ): GenericJsComponentAF[P, CT, U, A, F] =
    copyComponent(self.component.withRef(ref))

  def withOptionalRef(
    ref: Option[Ref.Handle[Js.RawMounted[P, Null] & F]]
  ): GenericJsComponentAF[P, CT, U, A, F] =
    copyComponent(self.component.withOptionalRef(ref))
}

object GenericJsComponentAF:
  given [P <: js.Object, CT[-p, +u] <: CtorType[p, u], U, A, F <: js.Object]
    : Conversion[GenericJsComponentAF[P, CT, U, A, F], VdomNode] = _.render

  given [P <: js.Object, CT[-p, +u] <: CtorType[p, u], U, A, F <: js.Object]
    : Conversion[GenericJsComponentAF[P, CT, U, A, F], js.UndefOr[VdomNode]] = _.render

trait GenericJsComponentACF[P <: js.Object, CT[-p, +u] <: CtorType[p, u], U, A, F <: js.Object]
    extends PassthroughAC[P] { self =>
  protected val component: Js.ComponentWithFacade[P, Null, F, CT]
  def addModifiers(modifiers: Seq[TagMod]): A

  inline def render: RenderF[P, F] = {
    val (props, children) = rawModifiers
    component.applyGeneric(props)(children*)
  }

  private def copyComponent(
    newComponent: Js.ComponentWithFacade[P, Null, F, CT]
  ): GenericJsComponentACF[P, CT, U, A, F] =
    new GenericJsComponentACF[P, CT, U, A, F] {
      override protected def cprops: P                                = self.cprops
      override val modifiers: Seq[japgolly.scalajs.react.vdom.TagMod] = self.modifiers
      override protected val component                                = newComponent
      override def addModifiers(modifiers: Seq[TagMod]): A = self.addModifiers(modifiers)
    }

  def withRef(
    ref: Ref.Handle[Js.RawMounted[P, Null] & F]
  ): GenericJsComponentACF[P, CT, U, A, F] =
    copyComponent(self.component.withRef(ref))

  def withOptionalRef(
    ref: Option[Ref.Handle[Js.RawMounted[P, Null] & F]]
  ): GenericJsComponentACF[P, CT, U, A, F] =
    copyComponent(self.component.withOptionalRef(ref))
}

object GenericJsComponentACF:
  given [P <: js.Object, CT[-p, +u] <: CtorType[p, u], U, A, F <: js.Object]
    : Conversion[GenericJsComponentACF[P, CT, U, A, F], VdomNode] = _.render

  given [P <: js.Object, CT[-p, +u] <: CtorType[p, u], U, A, F <: js.Object]
    : Conversion[GenericJsComponentACF[P, CT, U, A, F], js.UndefOr[VdomNode]] = _.render
