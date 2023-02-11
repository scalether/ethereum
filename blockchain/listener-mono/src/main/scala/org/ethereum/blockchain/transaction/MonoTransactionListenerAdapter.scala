package org.ethereum.blockchain.transaction

import java.math.BigInteger

import org.ethereum.rpc.domain.Bytes
import reactor.core.publisher.Mono

class MonoTransactionListenerAdapter(listener: MonoTransactionListener) extends TransactionListener[Mono] {
  override def onTransaction(transactionHash: Bytes, blockNumber: BigInteger, confirmations: Int, confirmed: Boolean): Mono[Unit] =
    listener.onTransaction(transactionHash, blockNumber, confirmations, confirmed)
      .`then`(Mono.just())
}
