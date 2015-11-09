package netrunner.cards

import netrunner.Card.RunnerCard
import netrunner.Event

trait NeutralCard extends RunnerCard {
  override def faction = netrunner.NeutralRunner
}

object NeutralRunner {
  case class Infiltration(number: Int) extends NeutralCard {
    val cost = 0
    val title = "Infiltration"
    val influence = 0
    val `type` = Event
  }
}
