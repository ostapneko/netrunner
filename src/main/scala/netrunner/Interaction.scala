package netrunner

import scalaz.State

trait Interaction[F <: Faction, A] extends State[Game, A]

object Interaction {
  def apply[F <: Faction, A](f: Game => (Game, A)) = new State[Game, A] {
    override def apply(game: Game) = f(game)
  }
}
