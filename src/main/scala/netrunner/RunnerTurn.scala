package netrunner

import netrunner.Card.RunnerCard

object RunnerTurn {
  def start[F <: RunnerFaction](runner: Runner[F], deck: Seq[RunnerCard]): RunnerGame = {
    val (grip, stack) = deck.splitAt(5)
    RunnerGame( grip = grip.toSet, stack = stack, link = runner.link)
  }
}
