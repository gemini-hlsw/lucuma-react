// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react

import japgolly.scalajs.react._

import scalajs.js
import js.|
import js.JSConverters._

package hotkeys {
  sealed abstract class LogLevel(val name: String)
  object LogLevel {
    case object Verbose extends LogLevel("verbose")
    case object Debug   extends LogLevel("debug")
    case object Info    extends LogLevel("info")
    case object Warn    extends LogLevel("warn")
    case object Error   extends LogLevel("error")
    case object None    extends LogLevel("none")
  }

  sealed abstract class KeyInstance(val action: String)
  case object KeyPress extends KeyInstance("keypress")
  case object KeyDown  extends KeyInstance("keydown")
  case object KeyUp    extends KeyInstance("keyup")

  @js.native
  trait KeyEvent    extends js.Object {
    val sequence: js.UndefOr[String] = js.native
    val action: js.UndefOr[String]   = js.native
  }
  object KeyEvent {
    def apply(sequence: String, instance: KeyInstance): KeyEvent =
      js.Dynamic.literal(sequence = sequence, action = instance.action).asInstanceOf[KeyEvent]
  }
  @js.native
  trait KeySequence extends KeyEvent  {
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

  object KeyMap {
    def apply(keyMap: (String, KeySpec)*): KeyMap =
      Map(keyMap: _*)
  }

  object Handlers {
    def apply(handlers: (String, Handler)*): Handlers =
      Map(handlers: _*)
  }
}

package object hotkeys {
  type KeySeq   = String | KeyEvent
  type KeySpec  = KeySeq | js.Array[String] | KeySequence
  type KeyMap   = Map[String, KeySpec]
  type Handler  = ReactKeyboardEvent => Callback
  type Handlers = Map[String, Handler]

  implicit def seqToKeySpec(seq: Seq[String]): KeySpec =
    seq.toJSArray

  implicit class StringKeyEventOps(s: String) {
    def on[I <: KeyInstance](instance: I): KeyEvent = KeyEvent(s, instance)
  }

  implicit def callbackToHandler(cb: Callback): Handler =
    _ => cb

  implicit class HandlersOps(h: Handlers) {
    def toJs: js.Object =
      h.view
        .mapValues[js.Function1[ReactKeyboardEvent, Unit]](f => e => f(e).runNow())
        .toMap
        .toJSDictionary
        .asInstanceOf[js.Object]
  }

  implicit class KeyMapOps(km: KeyMap) {
    def toJs: js.Object =
      km.toJSDictionary
        .asInstanceOf[js.Object]
  }
}
