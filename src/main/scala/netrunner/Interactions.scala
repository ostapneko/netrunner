package netrunner

import netrunner.Interaction.InteractionST._

import scalaz.syntax.either._

object Interactions {
  def changeCorpCredits(n: Int) = Interaction[Unit] { game =>
    ((Game._corp ^|-> CorpBoard._credits).modify { c => List(c + n, 0).max }(game), ()).right
  }

  def changeCorpClicks(n: Int) = Interaction[Unit] { game =>
    ((Game._corp ^|-> CorpBoard._clicks).modify { c => List(c + n, 0).max }(game), ()).right
  }

  val corpTakesCoin = for {
    cs <- gets(_.corp.clicks)
    res <- Interaction.fromDis(cs.right.ensure("Need at least one click")(_ > 0))
    _ <- changeCorpCredits(1)
    _ <- changeCorpClicks(-1)
  } yield ()
}
