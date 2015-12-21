package netrunner

import org.scalatest.FreeSpec
import scalaz.syntax.either._
import scalaz.\/-

class InteractionsTest extends FreeSpec {
  val corpDeck = Deck[CorpFaction](
    identity = cards.CorpIdentities.PersonalEvolution,
    cards = Range(1, 46).map(cards.NeutralCorp.HedgeFund).toSet
  )
  val runnerDeck = Deck[RunnerFaction](
    identity = cards.RunnerIdentities.Noise,
    cards = Range(1, 46).map(cards.NeutralRunner.Infiltration).toSet
  )

  val game = Game(CorpBoard.fromDeck(corpDeck), RunnerBoard.fromDeck(runnerDeck))

  "changeCorpCredits" - {
    "change the number of corp credit if able" in {
      val newCreds = Interactions.changeCorpCredits(1).run(game).map(
        _._1.corp.credits
      )
      assertResult(6.right)(newCreds)
    }

    "leaves 0 credit when more credits than available are taken" in {
      val newCreds = Interactions.changeCorpCredits(-10).run(game).map(
        _._1.corp.credits
      )
      assertResult(0.right)(newCreds)
    }
  }

  "changeCorpClicks" - {
    "change the number of corp click if able" in {
      val newCreds = Interactions.changeCorpClicks(1).run(game).map(
        _._1.corp.clicks
      )
      assertResult(4.right)(newCreds)
    }

    "leaves 0 click when more clicks than available are taken" in {
      val newCreds = Interactions.changeCorpClicks(-10).run(game).map(
        _._1.corp.clicks
      )
      assertResult(0.right)(newCreds)
    }
  }

  "corpClicksCoin" - {
    "takes 1 coin for 1 click, if able" in {
      val newCredsAndClick = Interactions.corpClicksCoin.run(game).map { case (g, _) =>
        (g.corp.credits, g.corp.clicks)
      }

      assertResult((6, 2).right)(newCredsAndClick)
    }

    "fails if the corp has no click" in {
      val corpNoClick = (Game._corp ^|-> CorpBoard._clicks set 0)(game)
      assert(Interactions.corpClicksCoin.run(corpNoClick).isLeft)
    }
  }

  "corpClicksCards" - {
    "takes 1 card for 1 click, if able" in {
      val \/-(newGame) = Interactions.corpClicksCard.exec(game)

      assertResult(2)(newGame.corp.clicks)
      assertResult(6)(newGame.corp.hq.cards.size)
      assertResult(game.corp.rd.cards.size - 1)(newGame.corp.rd.cards.size)
    }

    "fails if the R&D is empty" in {
      val emptyRDGame = (Game._corp ^|-> CorpBoard._rd ^|-> RD._cards).set(Nil)(game)

      assert(Interactions.corpClicksCard.run(emptyRDGame).isLeft)
    }
  }
}
