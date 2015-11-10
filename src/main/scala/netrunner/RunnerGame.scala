package netrunner

import netrunner.Card.RunnerCard

case class RunnerGame(
  identity: Identity[RunnerFaction],
  clicks: Int = 4,
  credits: Int = 5,
  grip: Set[RunnerCard],
  heap: Set[RunnerCard] = Set.empty,
  stack: Seq[RunnerCard],
  installed: Set[RunnerCard] = Set.empty,
  scoredAgendas: Set[Agenda] = Set.empty,
  tags: Int = 0,
  brainDamages: Int = 0,
  link: Int
  ) extends Game[RunnerFaction]
