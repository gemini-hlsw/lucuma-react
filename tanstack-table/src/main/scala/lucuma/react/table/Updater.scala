// Copyright (c) 2016-2023 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package lucuma.react.table

import lucuma.typed.{tanstackTableCore => raw}

import scalajs.js

enum Updater[T]:
  case Set(value: T)   extends Updater[T]
  case Mod(fn: T => T) extends Updater[T]

  def toJs: raw.buildLibTypesMod.Updater[T] = this match
    case Set(value) => value
    case Mod(fn)    => fn

object Updater:
  def fromJs[T](rawValue: raw.buildLibTypesMod.Updater[T]): Updater[T] =
    js.typeOf(rawValue) match
      case "function" => Mod(rawValue.asInstanceOf[js.Function1[T, T]])
      case _          => Set(rawValue.asInstanceOf[T])
