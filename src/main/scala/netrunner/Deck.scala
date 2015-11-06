package netrunner

import scalaz.{NonEmptyList, ValidationNel}
import scalaz.syntax.validation._

trait DeckError extends IllegalStateException
case class LessThanMinCards(min: Int, actual: Int) extends DeckError {
  override def getMessage: String = s"The deck has $actual cards, but should have at least $min"
}

case class Deck[Side <: Faction, F <: Side](identity: Identity[F], cards: Set[Card with Side]) {
  def validate: ValidationNel[DeckError, Set[Card with Side]] =
    cards.successNel.ensure(
      NonEmptyList(LessThanMinCards(identity.minCardDeck, cards.size))
    )(_.size >= identity.minCardDeck)
}
