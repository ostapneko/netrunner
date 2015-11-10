package netrunner

import netrunner.Card.RunnerCard

object RunnerTurn {
  def start[F <: RunnerFaction](identity: RunnerIdentity[F], deck: Seq[RunnerCard]): RunnerGame = {
    val (grip, stack) = deck.splitAt(5)
    RunnerGame(identity = identity, grip = grip.toSet, stack = stack, link = identity.link)
  }
}
