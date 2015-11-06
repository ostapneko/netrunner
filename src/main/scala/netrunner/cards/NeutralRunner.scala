package netrunner.cards

import netrunner.{NeutralRunner, Event, Card}

object NeutralRunner {
  def infiltration(n: Int) = new Card with NeutralRunner with Event {
    override def number = n
    override def cost = 0
    override def title = "Infiltration"
    override def influence = 0
  }
}
