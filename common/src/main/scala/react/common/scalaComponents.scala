package react.common

import japgolly.scalajs.react._
import japgolly.scalajs.react.component.Scala
import scalajs.js

sealed trait ReactRender[Props, CT[-p, +u] <: CtorType[p, u]] {
  protected[common] val props: Props

  val ctor: CT[Props, Scala.Unmounted[Props, _, _]]

  @inline def apply(
    first: CtorType.ChildArg,
    rest:  CtorType.ChildArg*
  )(implicit
    ev:    CT[Props, Scala.Unmounted[Props, _, _]] <:< CtorType.PropsAndChildren[
      Props,
      Scala.Unmounted[Props, _, _]
    ]
  ): Scala.Unmounted[Props, _, _] =
    ctor.applyGeneric(props)((first +: rest): _*)

  @inline val toUnmounted: Scala.Unmounted[Props, _, _] = ctor.applyGeneric(props)()
}

sealed trait CtorWithProps[Props, CT[-p, +u] <: CtorType[p, u]] extends ReactRender[Props, CT] {
  protected type CloneType[-P, +U] = ctor.This[P, U]
  protected type CloneTypePU       =
    CloneType[Props, Scala.Unmounted[Props, _, _]]

  protected def clone(
    newCtor: CloneTypePU
  ): CtorWithProps[Props, CloneType] =
    new CtorWithProps[Props, CloneType] {
      override lazy val ctor                    = newCtor
      override protected[common] lazy val props = CtorWithProps.this.props
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

sealed trait ReactComponentProps[Props, CT[-p, +u] <: CtorType[p, u]]
    extends CtorWithProps[Props, CT] {
  val component: Scala.Component[Props, _, _, CT]

  protected[common] lazy val props: Props = this.asInstanceOf[Props]

  override lazy val ctor: CT[Props, Scala.Unmounted[Props, _, _]] = component.ctor

  private def copyComponent(
    newComponent: Scala.Component[Props, _, _, CT]
  ): ReactComponentProps[Props, CT] =
    new ReactComponentProps[Props, CT] {
      override lazy val component               = newComponent
      override protected[common] lazy val props = ReactComponentProps.this.props
    }

  def withRef[S, B](ref: Ref.Handle[ScalaComponent.RawMounted[Props, S, B]]) =
    copyComponent(
      component
        .asInstanceOf[Scala.Component[Props, S, B, CT]]
        .withRef(ref)
    )

  def withOptionalRef[S, B](ref: Option[Ref.Handle[ScalaComponent.RawMounted[Props, S, B]]]) =
    copyComponent(
      component
        .asInstanceOf[Scala.Component[Props, S, B, CT]]
        .withOptionalRef(ref)
    )
}

class ReactProps[Props](val component: Scala.Component[Props, _, _, CtorType.Props])
    extends ReactComponentProps[Props, CtorType.Props]

class ReactPropsWithChildren[Props](
  val component: Scala.Component[Props, _, _, CtorType.PropsAndChildren]
) extends ReactComponentProps[Props, CtorType.PropsAndChildren]
