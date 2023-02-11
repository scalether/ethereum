package scalether.test

import org.ethereum.cats.implicits._
import scalether.core.Ethereum
import scalether.domain.implicits._
import org.ethereum.blockchain.poller.tries.implicits._
import org.ethereum.rpc.JsonConverter
import org.ethereum.rpc.tries.ScalajHttpTransport
import scalether.domain.Address
import org.scalether.utils
import scalether.core.json.EthereumJacksonModule
import scalether.sync.SemaphoreTrySynchronizer
import scalether.transaction._

import scala.util.Try

trait IntegrationSpec {
  val ethereum = new Ethereum(new ScalajHttpTransport("http://localhost:8545", new JsonConverter(new EthereumJacksonModule)))
  println(ethereum.ethBlockNumber())
  println(ethereum.ethGetBalance(Address("0x7acbc0b5c51017dc659a19f257bb3e462309b626"), "latest"))
  val sender = new SigningTransactionSender[Try](
    ethereum,
    new SimpleNonceProvider[Try](ethereum),
    new SemaphoreTrySynchronizer(),
    utils.Numeric.toBigInt("0x00120de4b1518cf1f16dc1b02f6b4a8ac29e870174cb1d8575f578480930250a"),
    2000000,
    new ValGasPriceProvider[Try](10),
    1
  )
  val poller:TransactionPoller[Try] = new TransactionPoller(ethereum)
}
