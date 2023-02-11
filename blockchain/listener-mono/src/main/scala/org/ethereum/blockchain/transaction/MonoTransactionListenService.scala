package org.ethereum.blockchain.transaction

import java.math.BigInteger

import org.ethereum.cats.mono.implicits._
import org.ethereum.blockchain.poller.mono.implicits._
import org.ethereum.blockchain.MonoBlockchain
import org.ethereum.blockchain.state.{MonoState, MonoStateAdapter}
import reactor.core.publisher.Mono

class MonoTransactionListenService(blockchain: MonoBlockchain, confidence: Int, listener: MonoTransactionListener, state: MonoState[BigInteger]) {
  private val scala = new TransactionListenService[Mono](blockchain, confidence, new MonoTransactionListenerAdapter(listener), new MonoStateAdapter[BigInteger](state))

  def check(block: BigInteger): Mono[Void] =
    scala.check(block).`then`()
}
