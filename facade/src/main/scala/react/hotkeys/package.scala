package react

import scalajs.js
import js.|
import js.JSConverters._
import japgolly.scalajs.react._

package hotkeys {
  sealed abstract class LogLevel(val name: String)
  object LogLevel {
    final case object Verbose extends LogLevel("verbose")
    final case object Debug extends LogLevel("debug")
    final case object Info extends LogLevel("info")
    final case object Warn extends LogLevel("warn")
    final case object Error extends LogLevel("error")
    final case object None extends LogLevel("none")
  }

  sealed abstract class KeyInstance(val action: String)
  final case object KeyPress extends KeyInstance("keypress")
  final case object KeyDown extends KeyInstance("keydown")
  final case object KeyUp extends KeyInstance("keyup")

  @js.native
  trait KeyEvent extends js.Object {
    val sequence: js.UndefOr[String] = js.native
    val action: js.UndefOr[String]   = js.native
  }
  object KeyEvent {
    def apply(sequence: String, instance: KeyInstance): KeyEvent =
      js.Dynamic.literal(sequence = sequence, action = instance.action).asInstanceOf[KeyEvent]
  }
  @js.native
  trait KeySequence extends KeyEvent {
    val name: js.UndefOr[String]                = js.native
    val sequences: js.UndefOr[js.Array[KeySeq]] = js.native
  }
  object KeySequence {
    def apply(name: String, sequence: String): KeySequence =
      js.Dynamic
        .literal(name = name, sequence = sequence)
        .asInstanceOf[KeySequence]

    def apply(name: String, sequence: String, action: String): KeySequence =
      js.Dynamic
        .literal(name = name, sequence = sequence, action = action)
        .asInstanceOf[KeySequence]

    def apply(name: String, sequences: Seq[KeySeq]): KeySequence =
      js.Dynamic
        .literal(name = name, sequences = sequences.toJSArray)
        .asInstanceOf[KeySequence]

    def apply(name: String, sequences: Seq[KeySeq], action: String): KeySequence =
      js.Dynamic
        .literal(name = name, sequences = sequences.toJSArray, action = action)
        .asInstanceOf[KeySequence]
  }
}

package object hotkeys {
  type KeySeq  = String | KeyEvent
  type KeySpec = KeySeq | js.Array[String] | KeySequence

  type Handler = js.Function1[ReactKeyboardEvent, Unit]

  implicit def seqToKeySpec(seq: Seq[String]): KeySpec =
    seq.toJSArray

  implicit class StringKeyEventOps(s: String) {
    def on[I <: KeyInstance](instance: I): KeyEvent = KeyEvent(s, instance)
  }

  implicit def unitFunctionToHandler(f: () => Unit): Handler =
    _ => f()
}
