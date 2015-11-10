package netrunner

import scala.language.existentials
import scalaz.syntax.validation._
import scalaz.{Failure, Success, ValidationNel}

trait DeckError extends IllegalStateException
case class LessThanMinCards(min: Int, actual: Int) extends DeckError {
  override def getMessage: String = s"The deck has $actual cards, but should have at least $min"
}

case class TooMuchInfluence(max: Int, actual: Int) extends DeckError {
  override def getMessage: String = s"The deck has $actual influence, but should have at most $max"
}

case class Deck[F <: Faction](identity: Identity[F], cards: Set[Card[F]]) {
  import Deck._
  private def sizeCheck(cards: Set[Card[F]]): Boolean =
    cards.size >= identity.minCardDeck

  private def usedInfluence(cards: Set[Card[F]]) =
    cards.toSeq.collect { case c if c.faction != identity.faction => c.influence }.sum

  private def influenceCheck(cards: Set[Card[F]]): Boolean =
    usedInfluence(cards) <= identity.influence

  def validate: ValidationNel[DeckError, Set[Card[F]]] =
    cards.successNel[DeckError]
    .assert(sizeCheck)(_ => LessThanMinCards(identity.minCardDeck, cards.size))
    .assert(influenceCheck)(cs => TooMuchInfluence(identity.influence, usedInfluence(cs)))
}

case object Deck {
  type DeckValidation[T] = ValidationNel[DeckError, T]
  implicit class ValidationAssert[T](validation: DeckValidation[T]) {
    def assert(p: T => Boolean)(err: T => DeckError): DeckValidation[T] = validation match {
      case Success(v) if p(v) => Success(v)
      case Success(v) => err(v).failureNel
      case Failure(v) =>  Failure(v)
    }
  }
}
