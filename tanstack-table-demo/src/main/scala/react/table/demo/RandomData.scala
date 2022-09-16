// Copyright (c) 2016-2022 Association of Universities for Research in Astronomy, Inc. (AURA)
// For license information see LICENSE or https://opensource.org/licenses/BSD-3-Clause

package react.table.demo

import lucuma.react.table.Expandable

import scala.util.Random.nextInt

// word lists and code adapted from https://github.com/bmarcot/haiku
object RandomData {
  def getRandElt[A](xs: List[A]): A = xs.apply(nextInt(xs.size))

  def getAge: Int = nextInt(110)

  def newPerson(id: Int) = Person(id, getRandElt(adjs), getRandElt(nouns), getAge)

  def newExpandablePerson(id: Int) =
    Expandable(newPerson(id))
      .withSubRows((0 to nextInt(5)).toList.map(newPerson).map(Expandable.apply))

  def randomPeople(count: Int) = (0 to count).map(newPerson).toList

  def randomExpandablePeople(count: Int) = (0 to count).map(newExpandablePerson).toList

  // format: off
  val adjs = List("autumn", "hidden", "bitter", "misty", "silent",
    "empty", "dry", "dark", "summer", "icy", "delicate", "quiet", "white", "cool",
    "spring", "winter", "patient", "twilight", "dawn", "crimson", "wispy",
    "weathered", "blue", "billowing", "broken", "cold", "damp", "falling",
    "frosty", "green", "long", "late", "lingering", "bold", "little", "morning",
    "muddy", "old", "red", "rough", "still", "small", "sparkling", "throbbing",
    "shy", "wandering", "withered", "wild", "black", "holy", "solitary",
    "fragrant", "aged", "snowy", "proud", "floral", "restless", "divine",
    "polished", "purple", "lively", "nameless", "puffy", "fluffy",
    "calm", "young", "golden", "avenging", "ancestral", "ancient", "argent",
    "reckless", "daunting", "short", "rising", "strong", "timber", "tumbling",
    "silver", "dusty", "celestial", "cosmic", "crescent", "double", "far", "half",
    "inner", "milky", "northern", "southern", "eastern", "western", "outer",
    "terrestrial", "huge", "deep", "epic", "titanic", "mighty", "powerful")

  val nouns = List("waterfall", "river", "breeze", "moon", "rain",
    "wind", "sea", "morning", "snow", "lake", "sunset", "pine", "shadow", "leaf",
    "dawn", "glitter", "forest", "hill", "cloud", "meadow", "glade",
    "bird", "brook", "butterfly", "bush", "dew", "dust", "field",
    "flower", "firefly", "feather", "grass", "haze", "mountain", "night", "pond",
    "darkness", "snowflake", "silence", "sound", "sky", "shape", "surf",
    "thunder", "violet", "wildflower", "wave", "water", "resonance",
    "sun", "wood", "dream", "cherry", "tree", "fog", "frost", "voice", "paper",
    "frog", "smoke", "star", "sierra", "castle", "fortress", "tiger", "day",
    "sequoia", "cedar", "wrath", "blessing", "spirit", "nova", "storm", "burst",
    "protector", "drake", "dragon", "knight", "fire", "king", "jungle", "queen",
    "giant", "elemental", "throne", "game", "weed", "stone", "apogee", "bang",
    "cluster", "corona", "cosmos", "equinox", "horizon", "light", "nebula",
    "solstice", "spectrum", "universe", "magnitude", "parallax")
  // format: on
}
