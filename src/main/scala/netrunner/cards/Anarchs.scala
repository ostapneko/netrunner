package netrunner.cards

import netrunner.Card.RunnerCard
import netrunner.Event


trait AnarchCard extends RunnerCard {
  override def faction = netrunner.Anarchs
}

object Anarchs {
  case class DejaVu(number: Int) extends AnarchCard {
    val cost = 2
    val title = "Déjà Vu"
    val influence = 2
    val `type` = Event
  }
}
