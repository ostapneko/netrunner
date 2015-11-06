package netrunner

object RunnerTurn {
  def start(runner: Runner, deck: Seq[Card with RunnerFaction]): RunnerGame = {
    val (grip, stack) = deck.splitAt(5)
    RunnerGame( grip = grip.toSet, stack = stack, link = runner.link)
  }
}
