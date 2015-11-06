package netrunner

sealed trait Identity { self: Faction =>
  def influence: Int
  def minCardDeck: Int
  def name: String
}

trait Runner extends Identity { self: RunnerFaction =>
  def link: Int
}

trait Corp extends Identity { self: CorpFaction =>
}
