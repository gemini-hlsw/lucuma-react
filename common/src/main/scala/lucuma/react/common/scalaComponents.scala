// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.common

import japgolly.scalajs.react.*
import japgolly.scalajs.react.component.Scala
import japgolly.scalajs.react.component.ScalaFn
import japgolly.scalajs.react.component.ScalaForwardRef
import japgolly.scalajs.react.vdom.VdomElement
import japgolly.scalajs.react.vdom.VdomNode
import sourcecode.FullName

import scalajs.js

sealed trait ReactRender[Props, CT[-p, +u] <: CtorType[p, u], U] {
  protected[common] def props: Props

  def ctor: CT[Props, U]

  inline def apply(
    first: CtorType.ChildArg,
    rest:  CtorType.ChildArg*
  )(using CT[Props, U] <:< CtorType.PropsAndChildren[Props, U]): U =
    ctor.applyGeneric(props)((first +: rest)*)

  inline def toUnmounted: U = ctor.applyGeneric(props)()
}

sealed trait CtorWithProps[Props, CT[-p, +u] <: CtorType[p, u], U]
    extends ReactRender[Props, CT, U] { self =>

  protected def clone[CT0[-p, +u] <: CtorType[p, u]](
    newCtor: CT0[Props, U]
  ): CtorWithProps[Props, CT0, U] =
    new CtorWithProps[Props, CT0, U] {
      override lazy val ctor                    = newCtor
      override protected[common] lazy val props = self.props
    }

  def withKey(key: Key) =
    clone(ctor.withKey(key))

  final def withKey(k: Long) =
    clone(ctor.withKey(k))

  def addMod(f: CtorType.ModFn) =
    clone(ctor.addMod(f))

  final def withRawProp(name: String, value: js.Any) =
    clone(ctor.withRawProp(name, value))
}

sealed trait ReactComponentProps[Props, S, B, CT[-p, +u] <: CtorType[p, u]]
    extends CtorWithProps[Props, CT, Scala.Unmounted[Props, S, B]] { self =>
  def component: Scala.Component[Props, S, B, CT]

  protected[common] def props: Props = this.asInstanceOf[Props]

  override lazy val ctor: CT[Props, Scala.Unmounted[Props, S, B]] = component.ctor

  private def copyComponent(
    newComponent: Scala.Component[Props, S, B, CT]
  ): ReactComponentProps[Props, S, B, CT] =
    new ReactComponentProps[Props, S, B, CT] {
      override lazy val component               = newComponent
      override protected[common] lazy val props = self.props
    }

  def withRef(ref: Ref.Handle[ScalaComponent.RawMounted[Props, S, B]]) =
    copyComponent(component.withRef(ref))

  def withOptionalRef(ref: Option[Ref.Handle[ScalaComponent.RawMounted[Props, S, B]]]) =
    copyComponent(component.withOptionalRef(ref))
}

object ReactComponentProps:
  given [Props, S, B, CT[-p, +u] <: CtorType[p, u]]
    : Conversion[ReactComponentProps[Props, S, B, CT], VdomElement] =
    _.toUnmounted

  given [Props, S, B, CT[-p, +u] <: CtorType[p, u]]
    : Renderable[ReactComponentProps[Props, S, B, CT]] =
    Renderable(c => summon[Renderable[VdomNode]](c.toUnmounted))

class ReactProps[Props, S, B](val component: Scala.Component[Props, S, B, CtorType.Props])
    extends ReactComponentProps[Props, S, B, CtorType.Props]

class ReactPropsWithChildren[Props, S, B](
  val component: Scala.Component[Props, S, B, CtorType.PropsAndChildren]
) extends ReactComponentProps[Props, S, B, CtorType.PropsAndChildren]

object ReactPropsWithChildren:
  extension [Props, S, B](p: ReactPropsWithChildren[Props, S, B])
    inline def apply(first: CtorType.ChildArg, rest: CtorType.ChildArg*): VdomElement =
      p(first, rest*)

sealed trait ReactFnComponentProps[Props, CT[-p, +u] <: CtorType[p, u]](using name: FullName)
    extends CtorWithProps[Props, CT, ScalaFn.Unmounted[Props]] {
  val component: ScalaFn.Component[Props, CT]

  // Set the displayName for the component. This is used by React.
  // Inspired by https://github.com/japgolly/scalajs-react/issues/1087
  component.raw.asInstanceOf[js.Dynamic].displayName = name.value.stripSuffix(".component")

  protected[common] lazy val props: Props = this.asInstanceOf[Props]
}

object ReactFnComponentProps:
  given [Props, CT[-p, +u] <: CtorType[p, u]]
    : Conversion[ReactFnComponentProps[Props, CT], VdomElement] =
    _.toUnmounted

  given [Props, CT[-p, +u] <: CtorType[p, u]]: Renderable[ReactFnComponentProps[Props, CT]] =
    Renderable(c => summon[Renderable[VdomNode]](c.toUnmounted))

abstract class ReactFnProps[Props](val component: ScalaFn.Component[Props, CtorType.Props])(using
  FullName
) extends ReactFnComponentProps[Props, CtorType.Props] {
  override lazy val ctor: CtorType.Props[Props, ScalaFn.Unmounted[Props]] =
    component.ctor
}

abstract class ReactFnPropsWithChildren[Props](
  val component: ScalaFn.Component[Props, CtorType.PropsAndChildren]
)(using FullName)
    extends ReactFnComponentProps[Props, CtorType.PropsAndChildren] {
  override lazy val ctor: CtorType.PropsAndChildren[Props, ScalaFn.Unmounted[Props]] =
    component.ctor
}

object ReactFnPropsWithChildren:
  extension [Props](p: ReactFnPropsWithChildren[Props])
    inline def apply(first: CtorType.ChildArg, rest: CtorType.ChildArg*): VdomElement =
      p(first, rest*)

sealed trait ReactComponentPropsForwardRef[Props, R, CT[-p, +u] <: CtorType[p, u]]
    extends CtorWithProps[Props, CT, ScalaForwardRef.Unmounted[Props, R]] { self =>
  val component: ScalaForwardRef.Component[Props, R, CT]

  protected[common] lazy val props: Props = this.asInstanceOf[Props]

  override lazy val ctor: CT[Props, ScalaForwardRef.Unmounted[Props, R]] = component.ctor

  private def copyComponent(
    newComponent: ScalaForwardRef.Component[Props, R, CT]
  ): ReactComponentPropsForwardRef[Props, R, CT] =
    new ReactComponentPropsForwardRef[Props, R, CT] {
      override val component                    = newComponent
      override protected[common] lazy val props = self.props
    }

  def withRef(ref: Ref.Handle[R]) =
    copyComponent(component.withRef(ref).asInstanceOf[ScalaForwardRef.Component[Props, R, CT]])

  def withOptionalRef(ref: Option[Ref.Handle[R]]) =
    copyComponent(
      component
        .withOptionalRef(ref)
        .asInstanceOf[ScalaForwardRef.Component[Props, R, CT]]
    )
}

object ReactComponentPropsForwardRef:
  given [Props, R, CT[-p, +u] <: CtorType[p, u]]
    : Conversion[ReactComponentPropsForwardRef[Props, R, CT], VdomElement] =
    _.toUnmounted

  given [Props, R, CT[-p, +u] <: CtorType[p, u]]
    : Renderable[ReactComponentPropsForwardRef[Props, R, CT]] =
    Renderable(c => summon[Renderable[VdomNode]](c.toUnmounted))

class ReactPropsForwardRef[Props, R](
  val component: ScalaForwardRef.Component[Props, R, CtorType.Props]
) extends ReactComponentPropsForwardRef[Props, R, CtorType.Props]

class ReactPropsForwardRefWithChildren[Props, R](
  val component: ScalaForwardRef.Component[Props, R, CtorType.PropsAndChildren]
) extends ReactComponentPropsForwardRef[Props, R, CtorType.PropsAndChildren]

object ReactPropsForwardRefWithChildren:
  extension [Props, R](p: ReactPropsForwardRefWithChildren[Props, R])
    inline def apply(first: CtorType.ChildArg, rest: CtorType.ChildArg*): VdomElement =
      p(first, rest*)
