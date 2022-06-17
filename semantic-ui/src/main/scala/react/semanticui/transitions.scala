package react.semanticui

import react.common.EnumValue

object transitions {
  sealed trait SemanticTransition extends Product with Serializable
  object SemanticTransition {
    implicit val enumValue: EnumValue[SemanticTransition] = EnumValue.instance {

      case Browse         => "browse"
      case BrowseRight    => "browse right"
      case Drop           => "drop"
      case Fade           => "fade"
      case FadeUp         => "fade up"
      case FadeDown       => "fade down"
      case FadeLeft       => "fade left"
      case FadeRight      => "fade right"
      case FlyUp          => "fly up"
      case FlyDown        => "fly down"
      case FlyLeft        => "fly left"
      case FlyRight       => "fly right"
      case HorizontalFlip => "horizontal flip"
      case VerticalFlip   => "vertical flip"
      case Scale          => "scale"
      case SlideUp        => "slide up"
      case SlideDown      => "slide down"
      case SlideLeft      => "slide left"
      case SlideRight     => "slide right"
      case SwingUp        => "swing up"
      case SwingDown      => "swing down"
      case SwingLeft      => "swing left"
      case SwingRight     => "swing right"
      case Zoom           => "zoom"
      case Jiggle         => "jiggle"
      case Flash          => "flash"
      case Shake          => "shake"
      case Pulse          => "pulse"
      case Tada           => "tada"
      case Bounce         => "bounce"
      case Glow           => "glow"
    }
  }

  case object Browse         extends SemanticTransition
  case object BrowseRight    extends SemanticTransition
  case object Drop           extends SemanticTransition
  case object Fade           extends SemanticTransition
  case object FadeUp         extends SemanticTransition
  case object FadeDown       extends SemanticTransition
  case object FadeLeft       extends SemanticTransition
  case object FadeRight      extends SemanticTransition
  case object FlyUp          extends SemanticTransition
  case object FlyDown        extends SemanticTransition
  case object FlyLeft        extends SemanticTransition
  case object FlyRight       extends SemanticTransition
  case object HorizontalFlip extends SemanticTransition
  case object VerticalFlip   extends SemanticTransition
  case object Scale          extends SemanticTransition
  case object SlideUp        extends SemanticTransition
  case object SlideDown      extends SemanticTransition
  case object SlideLeft      extends SemanticTransition
  case object SlideRight     extends SemanticTransition
  case object SwingUp        extends SemanticTransition
  case object SwingDown      extends SemanticTransition
  case object SwingLeft      extends SemanticTransition
  case object SwingRight     extends SemanticTransition
  case object Zoom           extends SemanticTransition
  case object Jiggle         extends SemanticTransition
  case object Flash          extends SemanticTransition
  case object Shake          extends SemanticTransition
  case object Pulse          extends SemanticTransition
  case object Tada           extends SemanticTransition
  case object Bounce         extends SemanticTransition
  case object Glow           extends SemanticTransition
}
