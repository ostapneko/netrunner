package netrunner.cards

import netrunner.{Operation, Card}

object NeutralCorp {
  type N = netrunner.NeutralCorp
  val N = netrunner.NeutralCorp

  def HedgeFund(number: Int) = Card[N](
    number = number,
    cost = 5,
    influence = 0,
    faction = N,
    `type` = Operation,
    title = "Hedge Fund"
  )

}
