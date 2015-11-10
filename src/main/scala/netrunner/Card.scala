package netrunner

import scalaz.NonEmptyList

sealed trait IceType
case object Sentry extends IceType
case object Barrier extends IceType
case object CodeGate extends IceType
case class Misc(name: String) extends IceType

case class Card[+F <: Faction](
  number: Int,
  cost: Int,
  title: String,
  influence: Int,
  faction: F,
  `type`: CardType[F]
)

object Card {
 type CorpCard = Card[CorpFaction]
 type RunnerCard = Card[RunnerFaction]
}


sealed trait CardType[+Side <: Faction]

case class Agenda(point: Int) extends CardType[AnyCorp]
case class Asset(trashCost: Int) extends CardType[AnyCorp]
case class Upgrade(trashCost: Int) extends CardType[AnyCorp]
case object Operation extends CardType[AnyCorp]
case class Ice(strength: Int, types: NonEmptyList[IceType]) extends CardType[AnyCorp]

case object Resource extends CardType[AnyRunner]
case object Hardware extends CardType[AnyRunner]
case object Event extends CardType[AnyRunner]
sealed trait ProgramType extends CardType[AnyRunner] {
  def memCost: Int
}
case class Icebreaker(memCost: Int, strength: Int) extends ProgramType
case class Program(memCost: Int) extends ProgramType
