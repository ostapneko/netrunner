package netrunner

import monocle.macros.GenLens

case class Game(corp: CorpBoard, runner: RunnerBoard)

case object Game {
  val _corp = GenLens[Game](_.corp)
  val _runner = GenLens[Game](_.runner)
}
