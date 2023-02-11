package org.ethereum.blockchain.transfer

import java.math.BigInteger
import org.ethereum.cats.mono.implicits._
import org.ethereum.blockchain.poller.mono.implicits._
import org.ethereum.blockchain.MonoBlockchain
import org.ethereum.blockchain.state.{MonoState, MonoStateAdapter}
import reactor.core.publisher.Mono

class MonoTransferListenService(blockchain: MonoBlockchain, confidence: Int, listener: MonoTransferListener, state: MonoState[BigInteger]) {
  private val scala = new TransferListenService[Mono](blockchain, confidence, new MonoTransferListenerAdapter(listener), new MonoStateAdapter[BigInteger](state))

  def check(block: BigInteger): Mono[Void] =
    scala.check(block).`then`()
}
