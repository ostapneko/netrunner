package netrunner.cards

import netrunner.{Jinteki, CorpIdentity}

object CorpIdentities {
  val PersonalEvolution = CorpIdentity(
    influence = 15,
    minCardDeck = 45,
    name = "Jinteki: Personal Evolution",
    faction = Jinteki
  )
}
