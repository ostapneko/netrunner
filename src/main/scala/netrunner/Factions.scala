package netrunner

sealed trait Faction
sealed trait CorpFaction extends Faction
case object NBN extends CorpFaction
case object Weyland extends CorpFaction
case object Jinteki extends CorpFaction
case object HB extends CorpFaction
case object NeutralCorp extends CorpFaction

sealed trait RunnerFaction extends Faction
case object Shapers extends RunnerFaction
case object Criminals extends RunnerFaction
case object Anarchs extends RunnerFaction
case object NeutralRunner extends RunnerFaction
