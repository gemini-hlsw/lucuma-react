package react.common

import japgolly.scalajs.react._
import japgolly.scalajs.react.component.Scala
import scalajs.js
import japgolly.scalajs.react.component.ScalaFn
import japgolly.scalajs.react.vdom.VdomElement

sealed trait ReactRender[Props, S, B, CT[-p, +u] <: CtorType[p, u]] {
  protected[common] def props: Props

  def ctor: CT[Props, Scala.Unmounted[Props, S, B]]

  @inline def apply(
    first: CtorType.ChildArg,
    rest:  CtorType.ChildArg*
  )(implicit
    ev:    CT[Props, Scala.Unmounted[Props, S, B]] <:< CtorType.PropsAndChildren[
      Props,
      Scala.Unmounted[Props, S, B]
    ]
  ): Scala.Unmounted[Props, S, B] =
    ctor.applyGeneric(props)((first +: rest): _*)

  @inline val toUnmounted: Scala.Unmounted[Props, S, B] = ctor.applyGeneric(props)()
}

sealed trait CtorWithProps[Props, S, B, CT[-p, +u] <: CtorType[p, u]]
    extends ReactRender[Props, S, B, CT] {
  protected type CloneType[-P, +U] = CT[P, U] //CT[Props, Scala.Unmounted[Props, S, B]]#This[P, U]
  // ctor.This[P, U]
  protected type CloneTypePU       =
    CloneType[Props, Scala.Unmounted[Props, S, B]]

  protected def clone(
    newCtor: CloneTypePU
  ): CtorWithProps[Props, S, B, CloneType] =
    new CtorWithProps[Props, S, B, CloneType] {
      override lazy val ctor                    = newCtor
      override protected[common] lazy val props = CtorWithProps.this.props
    }

  def withKey(key: Key) =
    clone(ctor.withKey(key).asInstanceOf[CloneTypePU])

  final def withKey(k: Long) =
    clone(ctor.withKey(k).asInstanceOf[CloneTypePU])

  def addMod(f: CtorType.ModFn) =
    clone(ctor.addMod(f).asInstanceOf[CloneTypePU])

  final def withRawProp(name: String, value: js.Any) =
    clone(ctor.withRawProp(name, value).asInstanceOf[CloneTypePU])
}

sealed trait ReactComponentProps[Props, S, B, CT[-p, +u] <: CtorType[p, u]]
    extends CtorWithProps[Props, S, B, CT] {
  def component: Scala.Component[Props, S, B, CT]

  protected[common] def props: Props = this.asInstanceOf[Props]

  override lazy val ctor: CT[Props, Scala.Unmounted[Props, S, B]] = component.ctor

  private def copyComponent(
    newComponent: Scala.Component[Props, S, B, CT]
  ): ReactComponentProps[Props, S, B, CT] =
    new ReactComponentProps[Props, S, B, CT] {
      override lazy val component               = newComponent
      override protected[common] lazy val props = ReactComponentProps.this.props
    }

  def withRef(ref: Ref.Handle[ScalaComponent.RawMounted[Props, S, B]]) =
    copyComponent(component.withRef(ref))

  def withOptionalRef(ref: Option[Ref.Handle[ScalaComponent.RawMounted[Props, S, B]]]) =
    copyComponent(component.withOptionalRef(ref))
}

class ReactProps[Props, S, B](val component: Scala.Component[Props, S, B, CtorType.Props])
    extends ReactComponentProps[Props, S, B, CtorType.Props]

class ReactPropsWithChildren[Props, S, B](
  val component: Scala.Component[Props, S, B, CtorType.PropsAndChildren]
) extends ReactComponentProps[Props, S, B, CtorType.PropsAndChildren]

sealed trait ReactFnComponentProps[Props, CT[-p, +u] <: CtorType[p, u]] {
  // extends CtorWithProps[Props, CT] {
  val component: ScalaFn.Component[Props, CT]

  protected[common] lazy val props: Props = this.asInstanceOf[Props]
}

class ReactFnProps[Props](val component: ScalaFn.Component[Props, CtorType.Props])
    extends ReactFnComponentProps[Props, CtorType.Props]

object ReactFnProps {
  implicit def render[Props, CT[-p, +u] <: CtorType[p, u]](
    props: ReactFnComponentProps[Props, CT]
  ): VdomElement = props.component.applyGeneric(props.props)().vdomElement
}

class ReactFnPropsWithChildren[Props](
  val component: ScalaFn.Component[Props, CtorType.PropsAndChildren]
) extends ReactFnComponentProps[Props, CtorType.PropsAndChildren]
