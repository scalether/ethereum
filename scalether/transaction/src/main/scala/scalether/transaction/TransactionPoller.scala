package scalether.transaction

import cats.Monad
import cats.implicits._
import org.ethereum.blockchain.poller.Poller
import org.ethereum.rpc.domain.Word
import scalether.core.Ethereum
import scalether.domain.response.TransactionReceipt

import scala.language.higherKinds

class TransactionPoller[F[_]](val ethereum: Ethereum[F])
                             (implicit f: Monad[F], poller: Poller[F]) {

  def waitForTransaction(txHash: F[Word]): F[TransactionReceipt] = for {
    hash <- txHash
    result <- poller.poll(1000)(pollForTransaction(hash))
  } yield result

  private def pollForTransaction(txHash: Word): F[Option[TransactionReceipt]] =
    ethereum.ethGetTransactionReceipt(txHash)
}
