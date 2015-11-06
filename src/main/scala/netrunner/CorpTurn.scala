package netrunner

class CorpTurn {
  def start(corp: Corp, deck: Seq[Card with CorpFaction]): CorpGame = {
    val (hand, rdCards) = deck.splitAt(5)
    CorpGame(hq = HQ(hand, Nil), rd = RD(rdCards, Nil))
  }

}
