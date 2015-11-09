package netrunner

trait Identity[F <: Faction] {
  def influence: Int
  def minCardDeck: Int
  def name: String
  def `type`: IdentityType[F]
}

sealed trait IdentityType[F <: Faction] {
  def faction: F
}
case class Runner[F <: Faction](link: Int, faction: F) extends IdentityType[F]
case class Corp[F <: Faction](faction: F) extends IdentityType[F]
