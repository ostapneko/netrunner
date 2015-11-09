package netrunner

import netrunner.Card.CorpCard

class CorpTurn {
  def start(corp: Corp[CorpFaction], deck: Seq[CorpCard]): CorpGame = {
    val (hand, rdCards) = deck.splitAt(5)
    CorpGame(hq = HQ(hand, Nil), rd = RD(rdCards, Nil))
  }

}
