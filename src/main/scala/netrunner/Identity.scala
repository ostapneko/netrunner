package netrunner

sealed trait Identity { self: Faction =>
  def credits: Int
  def clicks: Int
  def influence: Int
  def minCardDeck: Int
  def name: String
}

trait Runner extends Identity { self: RunnerFaction =>
  def tags: Int
  def brainDamages: Int
}

trait Corp extends Identity { self: CorpFaction =>
  def badPub: Int
}
