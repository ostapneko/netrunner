package netrunner

object Interactions {
  object Corp {
    def changeCredit(n: Int) = Interaction[CorpFaction, Unit] { game =>
      ((Game._corp ^|-> CorpBoard._credits).modify(_ + n)(game), ())
    }

    def changeClicks(n: Int) = Interaction[CorpFaction, Unit] { game =>
      ((Game._corp ^|-> CorpBoard._clicks).modify(_ + n)(game), ())
    }

    val takeCoin = for {
      _ <- changeCredit(-1)
      _ <- changeClicks(-1)
    } yield ()
  }
}
