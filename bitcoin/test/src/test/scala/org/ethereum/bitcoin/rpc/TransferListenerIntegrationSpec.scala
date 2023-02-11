package org.ethereum.bitcoin.rpc

import java.math.BigInteger

import org.ethereum.bitcoin.rpc.listener.BitcoinBlockchain
import org.ethereum.blockchain.block.{BlockListenService, BlockListener}
import org.ethereum.blockchain.state.VarState
import org.ethereum.blockchain.transfer.{Transfer, TransferListenService, TransferListener}
import org.ethereum.rpc.ManualTag
import org.scalatest.FlatSpec
import reactor.core.publisher.Mono

class TransferListenerIntegrationSpec extends FlatSpec with IntegrationSpec {
  val blockchain = new BitcoinBlockchain(bitcoind, restBitcoind)

  "TranferListenService" should "listen for transfers" taggedAs ManualTag in {

    val transferListenService = new TransferListenService(blockchain, 2, TestTransferListener, new VarState[BigInteger, Mono](None))
    val blockListenService = new BlockListenService(blockchain, new TestBlockListener(transferListenService), new VarState[BigInteger, Mono](None))

    for (_ <- 1 to 100) {
      blockListenService.check().block()
      Thread.sleep(1000)
    }
  }

  it should "listen for many blocks" taggedAs ManualTag in {
    val service = new TransferListenService(blockchain, 1, TestTransferListener, new VarState[BigInteger, Mono](Some(BigInteger.valueOf(1291706))))
    service.check(BigInteger.valueOf(1293127)).block()
  }

  it should "notify about transfers in selected block" taggedAs ManualTag in {
    val transferListenService = new TransferListenService(blockchain, 1, TestTransferListener, new VarState[BigInteger, Mono](None))

    val start = System.currentTimeMillis()
    transferListenService.fetchAndNotify(BigInteger.valueOf(5000099))(BigInteger.valueOf(5000099))
    println(s"took: ${System.currentTimeMillis() - start}ms")
  }
}

class TestBlockListener(service: TransferListenService[Mono]) extends BlockListener[Mono] {
  override def onBlock(block: BigInteger): Mono[Unit] = {
    val start = System.currentTimeMillis()
    val result = service.check(block)
    println(s"block: $block took: ${System.currentTimeMillis() - start}ms")
    result
  }
}

object TestTransferListener extends TransferListener[Mono] {
  override def onTransfer(t: Transfer, confirmations: Int, confirmed: Boolean): Mono[Unit] = {
    println(t)
    Mono.just()
  }
}
