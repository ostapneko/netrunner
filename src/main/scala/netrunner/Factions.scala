package netrunner

sealed trait Faction
sealed trait CorpFaction extends Faction
trait NBN extends CorpFaction
trait Weyland extends CorpFaction
trait Jinteki extends CorpFaction
trait HB extends CorpFaction
trait NeutralCorp extends CorpFaction

sealed trait RunnerFaction extends Faction
trait Shapers extends RunnerFaction
trait Criminals extends RunnerFaction
trait Anarchs extends RunnerFaction
trait NeutralRunner extends RunnerFaction
