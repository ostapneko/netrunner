package netrunner

import netrunner.Card.CorpCard

import scala.language.{existentials, implicitConversions}

sealed trait Server {
  def ices: Seq[Ice]
}
trait CentralServer extends Server {
  def cards: Seq[CorpCard]
}

case class RemoteServer(mainCard: CorpCard, upgrades: Seq[Upgrade], ices: Seq[Ice]) extends Server
case class HQ(cards: Seq[CorpCard], ices: Seq[Ice] = Nil) extends CentralServer
case class Archives(cards: Seq[CorpCard], ices: Seq[Ice] = Nil) extends CentralServer
case class RD(cards: Seq[CorpCard], ices: Seq[Ice] = Nil) extends CentralServer

case class CorpGame(clicks: Int = 3,
                    credits: Int = 5,
                    hq: HQ,
                    archives: Archives = Archives(Nil, Nil),
                    rd: RD,
                    remoteServers: Set[RemoteServer] = Set.empty,
                    scoredAgendas: Set[Agenda] = Set.empty,
                    badPub: Int = 0) extends Game
