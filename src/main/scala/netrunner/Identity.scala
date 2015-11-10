package netrunner

sealed trait Identity[+F <: Faction] {
  def influence: Int
  def minCardDeck: Int
  def name: String
  def faction: F
}

case class CorpIdentity[+F <: CorpFaction](influence: Int, minCardDeck: Int, name: String, faction: F) extends Identity[F]
case class RunnerIdentity[+F <: RunnerFaction](influence: Int, minCardDeck: Int, name: String, faction: F, link: Int) extends Identity[F]
