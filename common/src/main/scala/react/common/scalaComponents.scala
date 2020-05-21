package react.common

import japgolly.scalajs.react._
import japgolly.scalajs.react.component.Scala
import japgolly.scalajs.react.vdom.VdomElement
import scalajs.js

sealed trait ReactRender[Props, CT[-p, +u] <: CtorType[p, u]] {
  type R

  protected[common] val props: Props

  val render: Props => R

  val toVdomElement: VdomElement
}
sealed trait PropsRender[Props]            extends ReactRender[Props, CtorType.Props]            {
  override type R = VdomElement

  override lazy val toVdomElement: VdomElement = render(props)
}
sealed trait PropsAndChildrenRender[Props] extends ReactRender[Props, CtorType.PropsAndChildren] {
  override type R = CtorType.ChildrenArgs => VdomElement

  override lazy val toVdomElement: VdomElement = render(props)(Seq.empty)

  @inline def apply(first: CtorType.ChildArg, rest: CtorType.ChildArg*): VdomElement =
    render(props)(first +: rest)

}

trait ReactComponentProps[Props, CT[-p, +u] <: CtorType[p, u]] extends ReactRender[Props, CT] {
  val component: Scala.Component[Props, _, _, CT]

  protected[common] val props: Props = this.asInstanceOf[Props]

  type UnmountedType[-P, +U] =
    CT[P, U]#This[P, U]
  type UnmountedTypePU       =
    UnmountedType[Props, Scala.Unmounted[Props, _, _]]

  private def toUnmounted(
    newUnmounted: UnmountedTypePU
  ): UnmountedReactComponentProps[Props, UnmountedType] =
    new UnmountedReactComponentProps[Props, UnmountedType] {
      type R = ReactComponentProps.this.R

      override val unmounted               = newUnmounted
      override protected[common] val props = ReactComponentProps.this.props
      @inline override val render          = ReactComponentProps.this.render
      @inline override val toVdomElement   = ReactComponentProps.this.toVdomElement
    }

  def withKey(key: Key) =
    toUnmounted(component.withKey(key))

  final def withKey(k: Long) =
    toUnmounted(component.withKey(k))

  def addMod(f: CtorType.ModFn) =
    toUnmounted(component.addMod(f))

  final def withRawProp(name: String, value: js.Any) =
    toUnmounted(component.withRawProp(name, value))

  private def copy(
    newComponent: Scala.Component[Props, _, _, CT]
  ): ReactComponentProps[Props, CT] =
    new ReactComponentProps[Props, CT] {
      type R = ReactComponentProps.this.R

      override val component               = newComponent
      override protected[common] val props = ReactComponentProps.this.props
      @inline override val render          = ReactComponentProps.this.render
      @inline override val toVdomElement   = ReactComponentProps.this.toVdomElement
    }

  def withRef[S, B](ref: Ref.Handle[ScalaComponent.RawMounted[Props, S, B]]) =
    copy(
      ReactComponentProps.this.component
        .asInstanceOf[Scala.Component[Props, S, B, CT]]
        .withRef(ref)
    )

  def withOptionalRef[S, B](ref: Option[Ref.Handle[ScalaComponent.RawMounted[Props, S, B]]]) =
    copy(
      ReactComponentProps.this.component
        .asInstanceOf[Scala.Component[Props, S, B, CT]]
        .withOptionalRef(ref)
    )
}

trait UnmountedReactComponentProps[Props, CT[-p, +u] <: CtorType[p, u]]
    extends ReactRender[Props, CT] {
  val unmounted: CT[Props, Scala.Unmounted[Props, _, _]]

  protected[common] val props: Props

  type CopyType[-P, +U] =
    UnmountedReactComponentProps.this.unmounted.This[P, U]
  type CopyTypePU       =
    CopyType[Props, Scala.Unmounted[Props, _, _]]

  private def copy(
    newUnmounted: CopyTypePU
  ): UnmountedReactComponentProps[Props, CopyType] =
    new UnmountedReactComponentProps[Props, CopyType] {
      type R = UnmountedReactComponentProps.this.R

      override val unmounted               = newUnmounted
      override protected[common] val props = UnmountedReactComponentProps.this.props
      @inline override val render          = UnmountedReactComponentProps.this.render
      @inline override val toVdomElement   = UnmountedReactComponentProps.this.toVdomElement
    }

  def withKey(key: Key) =
    copy(UnmountedReactComponentProps.this.unmounted.withKey(key))

  final def withKey(k: Long) =
    copy(UnmountedReactComponentProps.this.unmounted.withKey(k))

  def addMod(f: CtorType.ModFn) =
    copy(UnmountedReactComponentProps.this.unmounted.addMod(f))

  final def withRawProp(name: String, value: js.Any) =
    copy(UnmountedReactComponentProps.this.unmounted.withRawProp(name, value))
}

class ReactProps[Props](val component: Scala.Component[Props, _, _, CtorType.Props])
    extends ReactComponentProps[Props, CtorType.Props]
    with PropsRender[Props] {
  override lazy val render: Props => VdomElement = component.apply
}

class ReactPropsWithChildren[Props](
  val component: Scala.Component[Props, _, _, CtorType.PropsAndChildren]
) extends ReactComponentProps[Props, CtorType.PropsAndChildren]
    with PropsAndChildrenRender[Props] {
  override lazy val render: Props => CtorType.ChildrenArgs => VdomElement = component.apply
}
