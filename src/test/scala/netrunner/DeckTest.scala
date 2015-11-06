package netrunner

import org.scalatest.FreeSpec

import scalaz.syntax.validation._

class DeckTest extends FreeSpec {
  val corp = new Corp[Jinteki] {
    override def minCardDeck: Int = 5
    override def name: String = "test identity"
    override def influence: Int = 3
  }

  def jinCard(n: Int, _title: String) = new Card with Jinteki with Operation {
    override def number = n
    override def cost = 1
    override def title = _title
    override def influence = 3
  }

  def nbnCard(n: Int, _title: String) = new Card with NBN with Operation {
    override def number = n
    override def cost = 1
    override def title = _title
    override def influence = 3
  }

  "A deck" - {
    "is invalid when" - {
      "it has less cards than the identity permits" in {
        val deck = Deck[CorpFaction, Jinteki](corp, Set())
        assertResult(
          LessThanMinCards(corp.minCardDeck, 0).failureNel
        )(deck.validate)
      }
    }
  }
}
