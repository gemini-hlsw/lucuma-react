package react.semanticui.modules

import react.common.EnumValue

package sidebar {
  sealed trait SidebarAnimation extends Product with Serializable
  object SidebarAnimation {
    implicit val enumValue: EnumValue[SidebarAnimation] = EnumValue.instance {
      case Overlay    => "overlay"
      case Push       => "push"
      case ScaleDown  => "scale down"
      case Uncover    => "uncover"
      case SlideOut   => "slide out"
      case SlideAlong => "slide along"
    }
    case object Overlay extends SidebarAnimation
    case object Push       extends SidebarAnimation
    case object ScaleDown  extends SidebarAnimation
    case object Uncover    extends SidebarAnimation
    case object SlideOut   extends SidebarAnimation
    case object SlideAlong extends SidebarAnimation
  }

  sealed trait SidebarDirection extends Product with Serializable
  object SidebarDirection {
    implicit val enumValue: EnumValue[SidebarDirection] = EnumValue.toLowerCaseString

    case object Top    extends SidebarDirection
    case object Right  extends SidebarDirection
    case object Bottom extends SidebarDirection
    case object Left   extends SidebarDirection
  }

  sealed trait SidebarWidth extends Product with Serializable
  object SidebarWidth {
    implicit val enumValue: EnumValue[SidebarWidth] = EnumValue.instance {
      case VeryThin => "very thin"
      case Thin     => "thin"
      case Wide     => "wide"
      case VeryWide => "very wide"
    }
    case object VeryThin extends SidebarWidth
    case object Thin     extends SidebarWidth
    case object Wide     extends SidebarWidth
    case object VeryWide extends SidebarWidth
  }
}

package object sidebar
