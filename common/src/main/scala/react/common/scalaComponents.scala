package react.common

import japgolly.scalajs.react.CtorType
import japgolly.scalajs.react.vdom.VdomElement

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
