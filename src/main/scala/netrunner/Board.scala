package netrunner

import monocle.macros.GenLens
import netrunner.Card.{RunnerCard, CorpCard}

trait Board[Side <: Faction] {
  def identity: Identity[Side]
  def clicks: Int
  def credits: Int
}

sealed trait Server {
  def ices: Seq[Ice]
}
trait CentralServer extends Server {
  def cards: Seq[CorpCard]
}

case class RemoteServer(mainCard: CorpCard, upgrades: Seq[Upgrade], ices: Seq[Ice]) extends Server
case class HQ(cards: Seq[CorpCard], ices: Seq[Ice] = Nil) extends CentralServer
object HQ {
  val _cards = GenLens[HQ](_.cards)
}
case class Archives(cards: Seq[CorpCard], ices: Seq[Ice] = Nil) extends CentralServer
case class RD(cards: Seq[CorpCard], ices: Seq[Ice] = Nil) extends CentralServer
object RD {
  val _cards = GenLens[RD](_.cards)
}

case class CorpBoard(
  identity: Identity[CorpFaction],
  clicks: Int = 3,
  credits: Int = 5,
  hq: HQ,
  archives: Archives = Archives(Nil, Nil),
  rd: RD,
  remoteServers: Set[RemoteServer] = Set.empty,
  scoredAgendas: Set[Agenda] = Set.empty,
  badPub: Int = 0) extends Board[CorpFaction]

object CorpBoard {
  val _identity = GenLens[CorpBoard](_.identity)
  val _clicks = GenLens[CorpBoard](_.clicks)
  val _credits = GenLens[CorpBoard](_.credits)
  val _hq = GenLens[CorpBoard](_.hq)
  val _archives = GenLens[CorpBoard](_.archives)
  val _rd = GenLens[CorpBoard](_.rd)
  val _remoteServers = GenLens[CorpBoard](_.remoteServers)
  val _scoredAgendas = GenLens[CorpBoard](_.scoredAgendas)
  val _badPub = GenLens[CorpBoard](_.badPub)

  def fromDeck(deck: Deck[CorpFaction]) = {
    val cards = deck.cards.toList
    val (hand, rest) = cards.splitAt(5)
    CorpBoard(
      identity = deck.identity,
      hq = HQ(hand),
      rd = RD(rest)
    )
  }
}

case class RunnerBoard(
  identity: Identity[RunnerFaction],
  clicks: Int = 4,
  credits: Int = 5,
  grip: Seq[RunnerCard],
  heap: Seq[RunnerCard] = Seq.empty,
  stack: Seq[RunnerCard],
  installed: Set[RunnerCard] = Set.empty,
  scoredAgendas: Set[Agenda] = Set.empty,
  tags: Int = 0,
  brainDamages: Int = 0
  ) extends Board[RunnerFaction]

object RunnerBoard {
  val _identity = GenLens[RunnerBoard](_.identity)
  val _clicks = GenLens[RunnerBoard](_.clicks)
  val _credits = GenLens[RunnerBoard](_.credits)
  val _grip = GenLens[RunnerBoard](_.grip)
  val _heap = GenLens[RunnerBoard](_.heap)
  val _stack = GenLens[RunnerBoard](_.stack)
  val _installed = GenLens[RunnerBoard](_.installed)
  val _scoredAgendas = GenLens[RunnerBoard](_.scoredAgendas)
  val _tags = GenLens[RunnerBoard](_.tags)
  val _brainDamages = GenLens[RunnerBoard](_.brainDamages)

  def fromDeck(deck: Deck[RunnerFaction]) = {
    val cards = deck.cards.toList
    val (hand, rest) = cards.splitAt(5)
    RunnerBoard(
      identity = deck.identity,
      grip = hand,
      stack = rest
    )
  }
}
