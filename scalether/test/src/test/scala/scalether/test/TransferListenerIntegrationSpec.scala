package scalether.test

import java.math.BigInteger

import org.ethereum.cats.implicits._
import org.ethereum.blockchain.poller.tries.implicits._
import org.ethereum.blockchain.block.{BlockListenService, BlockListenerImpl}
import org.ethereum.blockchain.state.VarState
import org.ethereum.blockchain.transfer.{Transfer, TransferListenService, TransferListener}
import org.ethereum.rpc.{JsonConverter, ManualTag}
import org.ethereum.rpc.tries.ScalajHttpTransport
import org.scalatest.FlatSpec
import scalether.core.json.EthereumJacksonModule
import scalether.core.{Ethereum, Parity}
import scalether.listener.{EthereumBlockchain, SimpleEthereumBlockchain}

import scala.util.{Failure, Try}

class TransferListenerIntegrationSpec extends FlatSpec {
  val transport = new ScalajHttpTransport("http://ether-ropsten:8545", new JsonConverter(new EthereumJacksonModule))
  val ethereum = new Ethereum(transport)
  val parity = new Parity(transport)
  val blockchain = new EthereumBlockchain(ethereum, parity)
  val simple = new SimpleEthereumBlockchain(ethereum)

  "TranferListenService" should "listen for transfers" taggedAs ManualTag in {

    val transferListenService = new TransferListenService(blockchain, 2, TestTransferListener, new VarState[BigInteger, Try](None))
    val blockListenService = new BlockListenService(blockchain, new BlockListenerImpl(transferListenService), new VarState[BigInteger, Try](None))

    for (_ <- 1 to 100) {
      blockListenService.check() match {
        case Failure(th) => th.printStackTrace()
        case _ =>
      }
      Thread.sleep(1000)
    }
  }

  it should "listen to transfers using simple blockchain (no traces)" taggedAs ManualTag in {
    val transferListenService = new TransferListenService(simple, 2, TestTransferListener, new VarState[BigInteger, Try](None))
    val blockListenService = new BlockListenService(simple, new BlockListenerImpl(transferListenService), new VarState[BigInteger, Try](None))

    for (_ <- 1 to 100) {
      blockListenService.check() match {
        case Failure(th) => th.printStackTrace()
        case _ =>
      }
      Thread.sleep(1000)
    }
  }

  it should "notify about transfers in selected block" taggedAs ManualTag in {
    val transferListenService = new TransferListenService(blockchain, 1, TestTransferListener, new VarState[BigInteger, Try](None))

    val start = System.currentTimeMillis()
    transferListenService.fetchAndNotify(BigInteger.valueOf(5000099))(BigInteger.valueOf(5000099))
    println(s"took: ${System.currentTimeMillis() - start}ms")
  }
}

object TestTransferListener extends TransferListener[Try] {
  override def onTransfer(t: Transfer, confirmations: Int, confirmed: Boolean): Try[Unit] = Try {
    println(s"$t confirmations=$confirmations confirmed=$confirmed")
  }
}
