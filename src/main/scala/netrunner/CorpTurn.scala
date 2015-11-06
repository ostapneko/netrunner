package netrunner

class CorpTurn {
  def start[F <: CorpFaction](corp: Corp[F], deck: Seq[Card with F]): CorpGame = {
    val (hand, rdCards) = deck.splitAt(5)
    CorpGame(hq = HQ(hand, Nil), rd = RD(rdCards, Nil))
  }

}
