package netrunner

import netrunner.Interaction.InteractionT

import scalaz._
import scalaz.syntax.either._

trait Interaction[A] extends InteractionT[Game, A]

object Interaction {
  type Disj[T] = String \/ T
  type InteractionT[S, A] = StateT[Disj, S, A]
  val InteractionST = MonadState[InteractionT, Game]
  def apply[A](f: Game => Disj[(Game, A)]) = StateT[Disj, Game, A](f)
  def fromDis[A](d: Disj[A]) = StateT[Disj, Game, A](game => d.map(game -> _))

  implicit class DisjunctionAssert[T](disj: String \/ T) {
    def assert(p: T => Boolean)(err: T => String): String \/ T = disj match {
      case \/-(v) if p(v) => \/-(v)
      case \/-(v) => err(v).left
      case -\/(v) =>  v.left
    }
  }
}
