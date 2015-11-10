package netrunner

import netrunner.Card.CorpCard

class CorpTurn {
  def start[F <: CorpFaction](identity: CorpIdentity[F], deck: Seq[CorpCard]): CorpGame = {
    val (hand, rdCards) = deck.splitAt(5)
    CorpGame(identity = identity, hq = HQ(hand, Nil), rd = RD(rdCards, Nil))
  }

}
