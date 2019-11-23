package react.common

import japgolly.scalajs.react.CtorType
import japgolly.scalajs.react.vdom.html_<^.VdomElement

trait ReactProps {
  @inline def render: VdomElement
}

object ReactProps {
  implicit def props2Component(p: ReactProps): VdomElement = p.render
}

class RenderWithChildren(p: ReactPropsWithChildren) {

  def apply(first: CtorType.ChildArg, rest: CtorType.ChildArg*): VdomElement =
    p.render(first +: rest)
}

trait ReactPropsWithChildren {
  @inline def render: Seq[CtorType.ChildArg] => VdomElement
}

object ReactPropsWithChildren {
  implicit def propsWithChildren2Component(p: ReactPropsWithChildren): RenderWithChildren = new RenderWithChildren(p)

  implicit def propsWithEmptyChildren2Component(p: ReactPropsWithChildren): VdomElement = p.render(Seq.empty)
}
