package netrunner
import scala.language.implicitConversions

sealed trait Server {
  def ices: Seq[Ice]
}
trait CentralServer extends Server {
  def cards: Seq[Card]
}

trait MainCard
object MainCard {
  implicit def agendaToMainCard(agenda: Agenda): MainCard = new MainCard {}
  implicit def assetsToMainCard(asset: Asset): MainCard = new MainCard {}
}

case class RemoteServer[T: MainCard](mainCard: Option[T], upgrades: Seq[Upgrade], ices: Seq[Ice]) extends Server
case class HQ(cards: Seq[Card with CorpFaction], ices: Seq[Ice] = Nil) extends CentralServer
case class Archives(cards: Set[Card with CorpFaction], ices: Seq[Ice] = Nil) extends CentralServer
case class RD(cards: Seq[Card with CorpFaction], ices: Seq[Ice] = Nil) extends CentralServer

case class CorpGame(clicks: Int = 3,
                    credits: Int = 5,
                    hq: HQ,
                    archives: Archives = Nil,
                    rd: RD,
                    remoteServers: Set[RemoteServer] = Nil,
                    scoredAgendas: Set[Agenda] = Nil,
                    badPub: Int = 0) extends Game
