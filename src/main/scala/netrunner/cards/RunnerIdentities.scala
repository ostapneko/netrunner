package netrunner.cards

import netrunner.{Anarchs, RunnerIdentity}

object RunnerIdentities {
  val Noise = RunnerIdentity(
    influence = 15,
    minCardDeck = 45,
    name = "Noise: Hacker Extraordinaire",
    faction = Anarchs,
    link = 0
  )
}
