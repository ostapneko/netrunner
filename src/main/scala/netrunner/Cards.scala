package netrunner

object Cards {
  case class NiseiMKII(number: Int) extends Card with Jinteki with Agenda {
    override val cost = 4
    override val title = "Nisei MK II"
    override val points = 2
  }
}
