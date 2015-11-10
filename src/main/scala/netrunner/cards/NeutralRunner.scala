package netrunner.cards

import netrunner.{Card, Event}

object NeutralRunner {
  type N = netrunner.NeutralRunner
  val N = netrunner.NeutralRunner

  def Infiltration(number: Int) = Card[N](
    number = number,
    cost = 0,
    influence = 0,
    faction = N,
    `type` = Event,
    title = "Infiltration"
  )
}
