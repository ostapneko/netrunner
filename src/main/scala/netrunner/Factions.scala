package netrunner

sealed trait Faction
sealed trait CorpFaction extends Faction
sealed trait NBN extends CorpFaction
sealed trait Weyland extends CorpFaction
sealed trait Jinteki extends CorpFaction
sealed trait HB extends CorpFaction
sealed trait NeutralCorp extends CorpFaction
case object NBN extends NBN
case object Weyland extends Weyland
case object Jinteki extends Jinteki
case object HB extends HB
case object NeutralCorp extends NeutralCorp

sealed trait RunnerFaction extends Faction
sealed trait Shapers extends RunnerFaction
sealed trait Criminals extends RunnerFaction
sealed trait Anarchs extends RunnerFaction
sealed trait NeutralRunner extends RunnerFaction
case object Shapers extends Shapers
case object Criminals extends Criminals
case object Anarchs extends Anarchs
case object NeutralRunner extends NeutralRunner
