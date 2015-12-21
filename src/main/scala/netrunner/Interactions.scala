package netrunner

import netrunner.Interaction.InteractionST._

import scalaz.syntax.either._
import scalaz.syntax.bind._

object Interactions {
  def changeCorpCredits(n: Int) = Interaction[Unit] { game =>
    ((Game._corp ^|-> CorpBoard._credits).modify { c => List(c + n, 0).max }(game), ()).right
  }

  def changeCorpClicks(n: Int) = Interaction[Unit] { game =>
    ((Game._corp ^|-> CorpBoard._clicks).modify { c => List(c + n, 0).max }(game), ()).right
  }

  def addCorpCard(card: Card[CorpFaction]) = Interaction[Unit] { game =>
    ((Game._corp ^|-> CorpBoard._hq ^|-> HQ._cards).modify(card +: _)(game), ()).right
  }

  def corpDrawsCard = Interaction[Card[CorpFaction]] { game =>
    val lens = (Game._corp ^|-> CorpBoard._rd ^|-> RD._cards)
    lens.get(game) match {
      case Nil =>
        "Need at least one card to draw".left
      case (h :: t) =>
        (lens.set(t)(game), h).right
    }
  }

  def checkClick = for {
    cs <- gets(_.corp.clicks)
    _ <- Interaction.fromDis(cs.right.ensure("Need at least one click")(_ > 0))
  } yield ()

  val corpClicksCoin = checkClick >> (changeCorpCredits(1) >> changeCorpClicks(-1))

  val corpClicksCard = checkClick >> (changeCorpClicks(-1) >> (corpDrawsCard >>= addCorpCard))
}
