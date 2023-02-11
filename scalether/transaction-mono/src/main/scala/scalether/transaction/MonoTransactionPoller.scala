package scalether.transaction

import org.ethereum.blockchain.poller.mono.implicits._
import org.ethereum.cats.mono.implicits._
import org.ethereum.rpc.domain.Word
import reactor.core.publisher.Mono
import scalether.core.MonoEthereum
import scalether.domain.response.TransactionReceipt

class MonoTransactionPoller(ethereum: MonoEthereum) extends TransactionPoller[Mono](ethereum) {
  override def waitForTransaction(txHash: Mono[Word]): Mono[TransactionReceipt] =
    super.waitForTransaction(txHash)
}
