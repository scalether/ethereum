package scalether.transaction

import cats.Id
import org.ethereum.blockchain.poller.ids.implicits._
import org.ethereum.cats.implicits._
import org.ethereum.rpc.domain.Word
import scalether.core.IdEthereum
import scalether.domain.response.TransactionReceipt

class IdTransactionPoller(ethereum: IdEthereum) extends TransactionPoller[Id](ethereum) {
  override def waitForTransaction(txHash: Word): TransactionReceipt =
    super.waitForTransaction(txHash)
}
