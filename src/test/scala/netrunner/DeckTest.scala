package netrunner

import netrunner.Card.CorpCard
import org.scalatest.FreeSpec

import scalaz.syntax.validation._

class DeckTest extends FreeSpec {
  val corp = new Identity[CorpFaction] {
    override def minCardDeck: Int = 5
    override def name: String = "test identity"
    override def influence: Int = 3
    override def `type` = Corp(Jinteki)
  }

  def jinCard(n: Int, _title: String) = new CorpCard {
    override def number = n
    override def cost = 1
    override def title = _title
    override def influence = 3
    override def faction = Jinteki
    override def `type` = Operation
  }

  def nbnCard(n: Int, _title: String) = new CorpCard {
    override def number = n
    override def cost = 1
    override def title = _title
    override def influence = 3
    override def faction = NBN
    override def `type` = Operation
  }

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
