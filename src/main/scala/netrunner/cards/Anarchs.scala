package netrunner.cards

import netrunner.{Card, Anarchs, Event}

object Anarchs {
  def dejaVu(n: Int) = new Card with Anarchs with Event {
    override def number = n
    override val cost = 2
    override def title = "Déjà Vu"
    override def influence = 2
  }

  def demolitionRun(n: Int) = new Card with Anarchs with Event {
    override def number = n
    override def cost = 2
    override def title = "Demolition Run"
    override def influence = 2
  }
}
