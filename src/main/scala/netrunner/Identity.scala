package netrunner

sealed trait Identity[F <: Faction] {
  def influence: Int
  def minCardDeck: Int
  def name: String
}

trait Runner[F <: RunnerFaction] extends Identity[F] {
  def link: Int
}

trait Corp[F <: CorpFaction] extends Identity[F]
