package netrunner

import scala.language.implicitConversions

trait Installable

object Installable {
  implicit def resource2Installable(resource: Resource): Installable = new Installable {}
  implicit def hardware2Installable(hardware: Hardware): Installable = new Installable {}
  implicit def program2Installable(program: Program): Installable = new Installable {}
}

case class RunnerGame(clicks: Int = 4,
                      credits: Int = 5,
                      grip: Set[Card with RunnerFaction],
                      heap: Set[Card with RunnerFaction] = Nil,
                      stack: Seq[Card with RunnerFaction],
                      installed: Set[Installable] = Nil,
                      scoredAgendas: Set[Agenda] = Nil,
                      tags: Int = 0,
                      brainDamages: Int = 0,
                      link: Int
                      ) extends Game
