package netrunner

import scalaz.NonEmptyList

sealed trait IceType
case object Sentry extends IceType
case object Barrier extends IceType
case object CodeGate extends IceType
case class Misc(name: String) extends IceType

trait Card {
  def number: Int
  def cost: Int
  def title: String
}

trait Agenda { self: CorpFaction =>
  def points: Int
}

trait Asset { self: CorpFaction =>
  def trashCost: Int
}

trait Upgrade { self: CorpFaction =>
  def trashCost: Int
}

trait Operation { self: CorpFaction => }

trait Ice { self: CorpFaction =>
  def strength: Int
  def types: NonEmptyList[IceType]
}

trait Resource { self: RunnerFaction => }

trait Hardware { self: RunnerFaction => }

trait Event { self: RunnerFaction => }

trait Program { self: RunnerFaction =>
  def memCost: Int
}

trait Icebreaker extends Program { self: RunnerFaction =>
  def strength: Int
}
