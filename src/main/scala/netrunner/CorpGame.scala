package netrunner
import scala.language.{existentials, implicitConversions}

sealed trait Server {
  def ices: Seq[Ice]
}
trait CentralServer extends Server {
  def cards: Seq[Card with CorpFaction]
}

trait MainCard[T]
object MainCard {
  implicit def agendaToMainCard(agenda: Agenda): MainCard[Agenda] = new MainCard[Agenda] {}
  implicit def assetsToMainCard(asset: Asset): MainCard[Asset] = new MainCard[Asset] {}
}

case class RemoteServer[T: MainCard](mainCard: Option[T], upgrades: Seq[Upgrade], ices: Seq[Ice]) extends Server
case class HQ(cards: Seq[Card with CorpFaction], ices: Seq[Ice] = Nil) extends CentralServer
case class Archives(cards: Seq[Card with CorpFaction], ices: Seq[Ice] = Nil) extends CentralServer
case class RD(cards: Seq[Card with CorpFaction], ices: Seq[Ice] = Nil) extends CentralServer

case class CorpGame(clicks: Int = 3,
                    credits: Int = 5,
                    hq: HQ,
                    archives: Archives = Archives(Nil, Nil),
                    rd: RD,
                    remoteServers: Set[RemoteServer[T] forSome { type T }] = Set.empty,
                    scoredAgendas: Set[Agenda] = Set.empty,
                    badPub: Int = 0) extends Game
