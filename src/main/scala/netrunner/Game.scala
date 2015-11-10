package netrunner

trait Game[Side <: Faction] {
  def identity: Identity[Side]
  def clicks: Int
  def credits: Int
}
