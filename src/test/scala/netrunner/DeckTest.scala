package netrunner

import netrunner.Card.CorpCard
import org.scalatest.FreeSpec

import scalaz.syntax.validation._

class DeckTest extends FreeSpec {
  val corp = CorpIdentity(
    minCardDeck = 5,
    name = "test identity",
    influence = 3,
    faction = Jinteki
  )

  def jinCard(n: Int, _title: String) = new CorpCard(
    number = n,
    cost = 1,
    title = _title,
    influence = 3,
    faction = Jinteki,
    `type` = Operation
  )

  def nbnCard(n: Int, _title: String) = new CorpCard(
    number = n,
    cost = 1,
    title = _title,
    influence = 3,
    faction = NBN,
    `type` = Operation
  )

  "A deck" - {
    "is invalid when" - {
      "it has less cards than the identity permits" in {
        val deck = Deck[CorpFaction](corp, Set())
        assertResult(
          LessThanMinCards(corp.minCardDeck, 0).failureNel
        )(deck.validate)
      }

      "it has more cards from other factions than the identity permits" in {
        val nbnCards: Set[CorpCard] = Range(1, 5).map { n => nbnCard(n, "card") }.toSet
        val deck = Deck[CorpFaction](corp, nbnCards + jinCard(1, "card"))
        assertResult(
          TooMuchInfluence(3, 12).failureNel
        )(deck.validate)
      }
    }
  }
}
