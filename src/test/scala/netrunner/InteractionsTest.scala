package netrunner

import org.scalatest.FreeSpec
import scalaz.syntax.either._

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

  "corpTakesCoin" - {
    "takes 1 coin for 1 click, if able" in {
      val newCredsAndClick = Interactions.corpTakesCoin.run(game).map { case (g, _) =>
        (g.corp.credits, g.corp.clicks)
      }

      assertResult((6, 2).right)(newCredsAndClick)
    }

    "fails if the corp has no click" in {
      val corpNoClick = (Game._corp ^|-> CorpBoard._clicks set 0)(game)
      assert(Interactions.corpTakesCoin.run(corpNoClick).isLeft)
    }
  }
}
