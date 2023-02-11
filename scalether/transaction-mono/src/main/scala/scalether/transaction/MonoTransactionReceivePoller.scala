package scalether.transaction

import org.ethereum.blockchain.poller.mono.implicits._
import org.ethereum.cats.mono.implicits._
import org.ethereum.rpc.domain.Word
import reactor.core.publisher.Mono
import scalether.core.MonoEthereum
import scalether.domain.response.Transaction

class MonoTransactionReceivePoller(ethereum: MonoEthereum) extends TransactionReceivePoller[Mono](ethereum) {
  override def receiveTransaction(txHash: Word): Mono[Transaction] = super.receiveTransaction(txHash)
}
